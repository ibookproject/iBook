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
	private String oldReview = new String();
	public RemovePartReviewGUI(LoginGUI screen) {
		super();
		this.screen=screen;
		pann=this;

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
		 textReview.setText("OLD REVIEW"); //need to get text review
		 textReview.setBounds(136, 172, 556, 301);
		 add(textReview);
		 JButton btnSubmit = new JButton("Submit");
		 btnSubmit.setBounds(369, 517, 97, 25);
		 add(btnSubmit);
		// String oldReview =(textReview.getText());//string keep the old review for equal if change review
		 r = new Review(1, null, null,false, 1);// create review
			/*ArrayList<Review> temp = (ArrayList<Review>) ReviewController.SearchReviews("reviewContent", r, "bookID", screen.getClient());
			
			r = null;// reset the user that need to update*/
		 btnSubmit.addActionListener(new ActionListener() {
			 
				public void actionPerformed(ActionEvent e) {
					
					if(!(oldReview.equals(textReview.getText())))
						{
						boolean result = ReviewController.UpdateReviewContent(r, "reviewContent=\""+textReview.getText()+"\"", "reviewID=\""+2+"\"",screen.getClient());
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