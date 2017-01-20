package ManagmentGUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import client.DBSQLhandler;
import Book.Review;
import Controller.ReviewController;
import Controller.UserController;
import MenuGUI.LoginGUI;
import Role.User;
import Role.UserStatus;

import javax.swing.border.MatteBorder;

import java.awt.Color;


/**
 * 
 * @author Sagi Entenberg
 * build the window of Remove Part Review
 */
public class RemovePartReviewGUI extends JPanel {

	private static final long serialVersionUID = 1L;
	public JButton btnBack;
	private LoginGUI screen;
	private JPanel pann;
	private Review r;
	private ReviewController r1;
	private int ReviewID;
	private String oldReview = new String();
	private int conterOfText;
	
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
		
		 r = new Review();// create review
			ArrayList<Review> temp = (ArrayList<Review>) ReviewController.SearchReviews("reviewContent", r, ""+ "reviewID=\""+ReviewID+"\"", screen.getClient());
		ImageIcon backIcon =new ImageIcon("src/images/backIcon.png");

		JLabel Counterlabel = new JLabel("200");
		Counterlabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Counterlabel.setHorizontalAlignment(SwingConstants.CENTER);
		Counterlabel.setForeground(Color.RED);
		Counterlabel.setBounds(704, 450, 41, 23);
		add(Counterlabel);
		 btnBack = new JButton(backIcon);
		 btnBack.setBounds(39, 52, 89, 23);
		 add(btnBack);
		 
		 JLabel lblRemovePartOf = new JLabel("REMOVE PART OF REVIEW");
		 lblRemovePartOf.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		 lblRemovePartOf.setBounds(252, 79, 304, 57);
		 add(lblRemovePartOf);
		 
		 JTextArea textReview = new JTextArea();
		 textReview.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 191, 255)));
		 textReview.setLineWrap(true);
		 textReview.setFont(new Font("Courier New", Font.PLAIN, 20));
		 conterOfText=temp.get(0).getReviewContent().length();
		 Counterlabel.setText(Integer.toString((200-conterOfText)));
		 textReview.addKeyListener(new KeyAdapter() {
		 @Override
			public void keyTyped(KeyEvent a)
			{
			 char c = a.getKeyChar();
				String charToString = Character.toString(c);
				if(!ContentValidation(charToString))
					{
					a.consume();
					
					
					}
				if(textReview.getText().length()>=200)
					a.consume();
			
			}
		 
		 @Override
			public void keyPressed(KeyEvent pressedChar) {
				if((pressedChar.getKeyChar()==8)&&(conterOfText>0)&&(pressedChar.getKeyCode()!=16))
				{
					
					conterOfText--;
					if(conterOfText<=50)
						Counterlabel.setText(Integer.toString((200-conterOfText)));
					
				}
				else 
				{
					if((pressedChar.getKeyChar()!=8)&&conterOfText<200&&(pressedChar.getKeyCode()!=16))
					{
						char c = pressedChar.getKeyChar();
						String charToString = Character.toString(c);
						if(!ContentValidation(charToString))
							{
							JOptionPane.showMessageDialog(screen,"Incorrect input char\n", "Warning",JOptionPane.WARNING_MESSAGE);
							pressedChar.consume();
													
							}
						else
						{
						conterOfText++;
						Counterlabel.setText(Integer.toString((200-conterOfText)));
						}
					}
					//System.out.println(conterOfText);
				}
			}
		
		});
		
		 textReview.setText(temp.get(0).getReviewContent()); //need to get text review
		 oldReview=temp.get(0).getReviewContent();
		 textReview.setBounds(136, 172, 556, 301);
		 add(textReview);
		 JButton btnSubmit = new JButton("Submit");
		 btnSubmit.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 191, 255)));
		 btnSubmit.setFont(new Font("Tahoma", Font.BOLD, 16));
		 btnSubmit.setBounds(369, 517, 106, 37);
		 add(btnSubmit);
		 
		 JButton button = new JButton("");
		 button.setEnabled(false);
		 button.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(250, 128, 114)));
		 button.setBounds(698, 450, 50, 23);
		 add(button);
		
		
			
			
		 btnSubmit.addActionListener(new ActionListener() {
			 
				public void actionPerformed(ActionEvent e) {
					
					if(!(oldReview.equals(textReview.getText())))
						{
						
						boolean result = ReviewController.UpdateReviewContent(r, "reviewContent=\""+textReview.getText()+"\"", "reviewID=\""+ReviewID+"\"",screen.getClient());
						boolean result1 = ReviewController.UpdateReviewContent(r, "reviewStatus=\""+1+"\"", "reviewID=\""+ReviewID+"\"",screen.getClient());
						oldReview=textReview.getText();
						if(result)
						{
							JOptionPane.showMessageDialog(screen,"The new Review update sucsseccfully", "",JOptionPane.INFORMATION_MESSAGE);
							
						}
						else JOptionPane.showMessageDialog(screen,"Update Review process FAILED", "Warning",JOptionPane.WARNING_MESSAGE);
						}
					else
						JOptionPane.showMessageDialog(screen,"The Review not changed", "Warning",JOptionPane.WARNING_MESSAGE);
					 
				}
			});

	
	
	}
	public static boolean ContentValidation(String name) {
		boolean status = false;
		String namePattern = "[|%\"'&=]";
		Pattern pattern = Pattern.compile(namePattern);
		Matcher matcher = pattern.matcher(name);

		if (matcher.matches())
			status = false;
		else
			status = true;
		return status;
	}
	
}