package ManagmentGUI;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import javax.swing.ScrollPaneConstants;

import Book.Book;
import Book.Review;
import Controller.ReviewController;
import Controller.UserController;
import Controller.bookController;
import Panels.BookPanel;
import Panels.ReviewPanel;
import Panels.UserSubscriptionPanel;
import MenuGUI.LoginGUI;
import Role.User;
import Role.UserStatus;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.border.MatteBorder;

public class ConfirmationReviewGUI extends JPanel {

	private static final long serialVersionUID = 1L;
	public JButton btnBack ;
	private LoginGUI screen;
	private JPanel pann;
	private int Permission;
	private ImageIcon backIcon;
	private JLabel confirmLbl ;
	private JButton btnRemovePartReview;
	private JButton btnNotConfirm;
	private JButton btnConfirm ;
	private JLabel lblDate ;
	private JCheckBox chckbxBookName;
	private int reviewID;
	private int flagReviewChoose=0;
	private JScrollPane scrollPaneMain;
	private JPanel panel;
	private ArrayList<ReviewPanel> reviewPanels;
	
	public ConfirmationReviewGUI(LoginGUI screen,int permission) {
		super();
		this.screen=screen;
		pann=this;
		this.Permission=permission;
		initialize();
	}

	private void initialize() {
		
		this.setSize(850, 625);
		this.setLayout(null);	
		
		backIcon =new ImageIcon("src/images/backIcon.png");
		btnBack = new JButton(backIcon);// declaration of back button
		btnBack.setBounds(40, 35, 89, 23);
		add(btnBack);
		
		confirmLbl = new JLabel("Confirmation Review");
		confirmLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		confirmLbl.setBounds(355, 35, 175, 22);
		add(confirmLbl);
		
		scrollPaneMain = new JScrollPane();
		scrollPaneMain.setBounds(55, 86, 763, 414);
		scrollPaneMain.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneMain.setAutoscrolls(true);
		add(scrollPaneMain);
				
		panel = new JPanel();
		panel.setIgnoreRepaint(true);
		panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		panel.setAutoscrolls(true);
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		scrollPaneMain.setViewportView(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		showReviews();
		
		btnNotConfirm = new JButton("Not Confirm");
		btnNotConfirm.setBounds(375, 544, 109, 25);
		btnNotConfirm.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				JOptionPane.showMessageDialog(screen,"The review was Not confirmed\n", "Warning",JOptionPane.WARNING_MESSAGE);
				Review r=new Review();
				setReviewID();
				ReviewController.DeleteReview(r,"reviewID=\""+reviewID+"\"",screen.getClient());//delete from db
				if(reviewPanels!=null)
				{
					panel.removeAll();
					showReviews();
				}
				else
					JOptionPane.showMessageDialog(screen,"Sorry,there is no list to show!\n", "Warning",JOptionPane.WARNING_MESSAGE);
					
			}
		});
		add(btnNotConfirm);
		
		btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				setReviewID();
				Review r=new Review();
				boolean review=ReviewController.UpdateReviewContent(r,"reviewStatus=\""+1+"\"","reviewID=\""+reviewID+"\"" ,screen.getClient());/*needs to update the reviewStatus not content!*/
				if(review==false)
				{
					screen.setContentPane(pann);
					JOptionPane.showMessageDialog(screen,"Review was not Found,Not updated\n", "Warning",JOptionPane.WARNING_MESSAGE);
				}
			
				else
				{
					JOptionPane.showMessageDialog(screen,"Update Submitted Succsesfully!!\n", "Warning",JOptionPane.WARNING_MESSAGE);
					if(reviewPanels!=null)
					{
						panel.removeAll();
						showReviews();
					}
					else
						JOptionPane.showMessageDialog(screen,"Sorry,there is no list to show!\n", "Warning",JOptionPane.WARNING_MESSAGE);
				}
				
			}
		});
		btnConfirm.setBounds(238, 544, 109, 25);
		add(btnConfirm);
			
		
		btnRemovePartReview = new JButton("Remove Part Of Review");
		btnRemovePartReview.setBounds(536, 544, 187, 25);
		btnRemovePartReview.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				setReviewID();
////////////////////////button to back to confirmation review from remove part of review /////////////////////////////////////////////
				RemovePartReviewGUI Rpr=new RemovePartReviewGUI(screen,reviewID);
				Rpr.btnBack.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e) 
					{
						
						screen.setContentPane(pann);
					}
				});

////////////////////////button to back to confirmation review from remove part of review/////////////////////////////////////////////
			
				if(flagReviewChoose==1)
				{
					screen.setContentPane(Rpr);
					flagReviewChoose=0;
				}
			}
		});
		
		if(this.Permission==5){
			add(btnRemovePartReview);}
			
	
	}
	
	public void showReviews()
	{
		Review r=new Review();
		ArrayList<Review> Reviews= (ArrayList<Review>)ReviewController.SearchReviews("reviewID,reviewDate,reviewContent,reviewStatus,bookID",r,"reviewStatus=\""+0+"\"" ,screen.getClient());
		if(Reviews==null||Reviews.isEmpty())
		{
			screen.setContentPane(pann);
			JOptionPane.showMessageDialog(screen,"Review was not Found\n", "Warning",JOptionPane.WARNING_MESSAGE);
		}
	
		else
		{
			reviewPanels=new ArrayList<ReviewPanel>();
			for(int i=0;i<Reviews.size();i++)
			{
				reviewPanels.add(new ReviewPanel(this.screen,Reviews.get(i)));
				
				panel.add(reviewPanels.get(i));
			}
		}
	}
	public void setReviewID()
	{
		if(reviewPanels!=null)
			for(ReviewPanel r2:reviewPanels)
			{
				if(r2.getchbxChoose()==1)
				{
					reviewID=r2.getReview().getReviewID();
					flagReviewChoose=1;
				}
			}
	}
}