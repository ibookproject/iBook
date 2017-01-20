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
import Controller.BookController;
import Panels.BookPanel;
import Panels.ReviewPanel;
import Panels.UserSubscriptionPanel;
import MenuGUI.LoginGUI;
import Role.User;
import Role.UserStatus;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JCheckBox;
import javax.swing.border.MatteBorder;

import client.DBgenericObject;

public class ConfirmationReviewGUI extends JPanel {

	private static final long serialVersionUID = 1L;
	public JButton btnBack ;
	private LoginGUI screen;
	private JPanel pann;
	private int Permission;
	private ImageIcon backIcon;
	private JLabel confirmLbl ;
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
		scrollPaneMain.getVerticalScrollBar().setUnitIncrement(16);
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
				if(reviewPanels!=null)
				{
					Review r=new Review();
					for(ReviewPanel rp:reviewPanels)
					{
						if(rp.getchbxChoose()==1)
						{	
							ReviewController.DeleteReview(r,"reviewID=\""+rp.getReviewId()+"\"",screen.getClient());//delete from db
							flagReviewChoose=1;
						}
					}
					if(flagReviewChoose==1)
					{
						JOptionPane.showMessageDialog(screen,"The review was Not confirmed\n", "Warning",JOptionPane.WARNING_MESSAGE);	
						panel.removeAll();
						showReviews();
					}
					
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
				if(reviewPanels!=null)
				{
					Review r=new Review();
					for(ReviewPanel rp:reviewPanels)
					{
						if(rp.getchbxChoose()==1)
						{	
							ReviewController.UpdateReviewContent(r,"reviewStatus=\""+1+"\"","reviewID=\""+rp.getReviewId()+"\"" ,screen.getClient());/*needs to update the reviewStatus not content!*/
							flagReviewChoose=1;
						}
					}
					if(flagReviewChoose==1)
					{
						JOptionPane.showMessageDialog(screen,"The review was Confirmed\n", "Success",JOptionPane.OK_OPTION);	
						panel.removeAll();
						showReviews();
					}
					
				}
				else
					JOptionPane.showMessageDialog(screen,"Sorry,there is no list to show!\n", "Warning",JOptionPane.WARNING_MESSAGE);
			}
		});
		btnConfirm.setBounds(238, 544, 109, 25);
		add(btnConfirm);
	}
	
	public void showReviews()
	{
		ArrayList<DBgenericObject> joinAnswerReviewBook=new ArrayList<DBgenericObject>();
		try 
		{
			joinAnswerReviewBook = ReviewController.searchJoinReviewBook(screen.getClient());
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		if(joinAnswerReviewBook.isEmpty())
			JOptionPane.showMessageDialog(screen,"There's nothing to show!", "Warning",JOptionPane.WARNING_MESSAGE);
		
		else
		{
			System.out.print("joinAnswerReviewBook is not empty!");
			reviewPanels=new ArrayList<ReviewPanel>();
			for(int i=0;i<joinAnswerReviewBook.size();i++)
			{
				//send to reviewPanel:reviewID,reviewContent,BookTitle,ReviewDate
				reviewPanels.add(new ReviewPanel(this.screen,(int)joinAnswerReviewBook.get(i).getValtoArray(1),(String)joinAnswerReviewBook.get(i).getValtoArray(2),(String)joinAnswerReviewBook.get(i).getValtoArray(4),Permission,pann/*,(String)joinAnswerReviewBook.get(i).getValtoArray(3)*/));
				
				panel.add(reviewPanels.get(i));
			}
		}
	}
}