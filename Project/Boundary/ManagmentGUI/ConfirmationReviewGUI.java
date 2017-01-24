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
import Extras.Validation;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.InputMismatchException;

import javax.swing.JCheckBox;
import javax.swing.border.MatteBorder;

import client.DBgenericObject;

/**
 * @author  Coral Carmeli
 * 
 * The class take care of the confirm of the review- where the review status=0 its needed to 
 * confirm/not confirm(Qualified Editor) or remove Part of Review(Librarian/Library manager)
 * 
 */
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
		
		backIcon =new ImageIcon("Extras/Images/backIcon.png");
		btnBack = new JButton(backIcon);// declaration of back button
		btnBack.setBounds(40, 35, 89, 23);
		add(btnBack);
		
		confirmLbl = new JLabel("Confirmation Review");
		confirmLbl.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 21));
		confirmLbl.setBounds(355, 35, 219, 22);
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
		

		if(Permission>=5)
		{
			Review r=new Review();
			ReviewController.UpdateReviewContent(r,"reviewStatus=\""+0+"\"","reviewStatus=\""+-1+"\"",screen.getClient());/*needs to update the reviewStatus not content!*/
		}	

		try {
			showReviews();
		} catch (SQLException e2) {
			panel.removeAll();
			JOptionPane.showMessageDialog(screen,"Sorry,there is no list to show!\n", "Warning",JOptionPane.WARNING_MESSAGE);
		}
		
		btnNotConfirm = new JButton("Not Confirm");
		btnNotConfirm.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNotConfirm.setBounds(477, 544, 152, 30);
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
						try {
							showReviews();
						} catch (SQLException e1) {
							panel.removeAll();
							JOptionPane.showMessageDialog(screen,"Sorry,there is no list to show!\n", "Warning",JOptionPane.WARNING_MESSAGE);
						}
					}
				}
				else
					JOptionPane.showMessageDialog(screen,"Sorry,there is no list to show!\n", "Warning",JOptionPane.WARNING_MESSAGE);
					
			}
		});
		add(btnNotConfirm);
		
		btnConfirm = new JButton("Confirm");
		btnConfirm.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnConfirm.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(reviewPanels!=null||reviewPanels.isEmpty()==false)
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
						JOptionPane.showMessageDialog(screen,"The review was Confirmed\n", "Success",JOptionPane.INFORMATION_MESSAGE);	
						//panel.updateUI();
						panel.removeAll();
						try {
							showReviews();
						} catch (SQLException e1) {
							panel.removeAll();
							JOptionPane.showMessageDialog(screen,"Sorry,there is no list to show!\n", "Warning",JOptionPane.WARNING_MESSAGE);
							
						}
					}
					
				}
				else
					JOptionPane.showMessageDialog(screen,"Sorry,there is no list to show!\n", "Warning",JOptionPane.WARNING_MESSAGE);
			}
		});
		btnConfirm.setBounds(264, 544, 152, 30);
		add(btnConfirm);
	}
	
	
	/**
	 * @author  Coral Carmeli
	 * @param no parameters
	 * Show the reviews which the review status=0 
	 */
	public void showReviews() throws SQLException
	{
		ArrayList<DBgenericObject> joinAnswerReviewBook=new ArrayList<DBgenericObject>();
		joinAnswerReviewBook = ReviewController.searchJoinReviewBook(screen.getClient());
		if((joinAnswerReviewBook==null)||(joinAnswerReviewBook.isEmpty()))
		{
			panel.updateUI();
			//panel.removeAll();
			JOptionPane.showMessageDialog(screen,"There's nothing to show!", "Warning",JOptionPane.WARNING_MESSAGE);
		}
			
		
		else
		{
			System.out.print("joinAnswerReviewBook is not empty!");
			reviewPanels=new ArrayList<ReviewPanel>();
			for(int i=0;i<joinAnswerReviewBook.size();i++)
			{
				//send to reviewPanel:reviewID,reviewContent,BookTitle,permission,pann,ReviewDate
			
				Date d = (Date)(joinAnswerReviewBook.get(i).getValtoArray(3));
				String txtDate = new SimpleDateFormat("dd/MM/yyyy").format(d);
					reviewPanels.add(new ReviewPanel(this.screen,(int)joinAnswerReviewBook.get(i).getValtoArray(1),(String)joinAnswerReviewBook.get(i).getValtoArray(2),(String)joinAnswerReviewBook.get(i).getValtoArray(4),Permission,pann,txtDate));	
					panel.add(reviewPanels.get(i));
				
			}
		}
	}
}