package ManagmentGUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import client.DBSQLhandler;
import Book.Review;
import Controller.ReviewController;
import Controller.UserController;
import MenuGUI.LoginGUI;
import Role.User;
import Role.UserStatus;



public class RemovePartReviewGUI extends JPanel {

	private static final long serialVersionUID = 1L;
	public JButton btnBack;
	private LoginGUI screen;
	private JPanel pann;
	private Review r;
	private ReviewController r1;
	private int ReviewID;
	private String oldReview = new String();
	public RemovePartReviewGUI(LoginGUI screen,int reviewID) {
		super();
		this.screen=screen;
		pann=this;
		ReviewID=reviewID;
		initialize();
	}

	
	private void initialize() {
		
		this.setSize(850, 625);
		this.setLayout(null);	
		
		
		ImageIcon backIcon =new ImageIcon("src/images/backIcon.png");
		 btnBack = new JButton(backIcon);
		 btnBack.setBounds(39, 52, 89, 23);
		 add(btnBack);
		 
		 JLabel lblRemovePartOf = new JLabel("REMOVE PART OF REVIEW");
		 lblRemovePartOf.setFont(new Font("Tahoma", Font.PLAIN, 18));
		 lblRemovePartOf.setBounds(322, 79, 234, 16);
		 add(lblRemovePartOf);
		 
		 JTextArea textReview = new JTextArea();
		 textReview.setFont(new Font("Courier New", Font.PLAIN, 13));
		 r = new Review(1, null, null,0, 1);// create review
			ArrayList<Review> temp = (ArrayList<Review>) ReviewController.SearchReviews("reviewContent", r, "bookID", screen.getClient());
			
		 textReview.setText(temp.get(0).getReviewContent()); //need to get text review
		 oldReview=temp.get(0).getReviewContent();
		 textReview.setBounds(136, 172, 556, 301);
		 add(textReview);
		 JButton btnSubmit = new JButton("Submit");
		 btnSubmit.setBounds(369, 517, 97, 25);
		 add(btnSubmit);
		
		
			
			
		 btnSubmit.addActionListener(new ActionListener() {
			 
				public void actionPerformed(ActionEvent e) {
					
					if(!(oldReview.equals(textReview.getText())))
						{
						
						boolean result = ReviewController.UpdateReviewContent(r, "reviewContent=\""+textReview.getText()+"\"", "reviewID=\""+ReviewID+"\"",screen.getClient());
						 oldReview=textReview.getText();
						if(result)
							JOptionPane.showMessageDialog(screen,"The new Review update sucsseccfully", "Warning",JOptionPane.WARNING_MESSAGE);
						else JOptionPane.showMessageDialog(screen,"Update Review process FAILED", "Warning",JOptionPane.WARNING_MESSAGE);
						}
					else
						JOptionPane.showMessageDialog(screen,"The Review not changed", "Warning",JOptionPane.WARNING_MESSAGE);
					 
				}
			});

	
	
	}
	
}