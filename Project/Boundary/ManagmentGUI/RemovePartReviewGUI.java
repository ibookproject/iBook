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

import Book.Review;
import Controller.ReviewController;
import MenuGUI.LoginGUI;
import javax.swing.border.MatteBorder;
import java.awt.Color;

/**
 * The class take care of the Remove part of the review-only the librarian can remove part of the review before he confirm/not confirm the review.
 * The librarian get the details of the review and update a new content.
 * When the librarian finish the removment he set the review status to 1.
 * @author  Sagi Entenberg
 * 
 */

public class RemovePartReviewGUI extends JPanel {

	private static final long serialVersionUID = 1L;
	public JButton btnBack;
	private LoginGUI screen;
	private JPanel pann;
	private Review review;
	private int ReviewID;
	private String oldReview = new String();
	private int conterOfText;

	/**
	 * Constructor of the RemovePartReviewGUI class
	 * @param screen This is the main window-login extends JFrame
	 * @param reviewID number of review ID
	 * @author  Sagi Entenberg
	 * 
	 */	
	public RemovePartReviewGUI(LoginGUI screen,int reviewID) {
		super();
		this.screen=screen;
		pann=this;
		ReviewID=reviewID;
		initialize();
	}
	/**
	 * This method initialize The window of remove part of review-put the components on the screen and set their functionality
	 * @author Sagi Entenberg
	 */
	
	private void initialize() {
		
		this.setSize(850, 625);
		this.setLayout(null);	
		
		review = new Review();// create review
			ArrayList<Review> temp = (ArrayList<Review>) ReviewController.SearchReviews("reviewContent", review, ""+ "reviewID=\""+ReviewID+"\"", screen.getClient());
		ImageIcon backIcon =new ImageIcon("Extras/Images/backIcon.png");

		JLabel Counterlabel = new JLabel("200");
		Counterlabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Counterlabel.setHorizontalAlignment(SwingConstants.CENTER);
		Counterlabel.setForeground(Color.RED);
		Counterlabel.setBounds(704, 450, 41, 23);
		add(Counterlabel);
		 btnBack = new JButton(backIcon);
		 btnBack.setBounds(35, 25, 89, 30);
		 add(btnBack);
		 
		 JLabel lblRemovePartOf = new JLabel("REMOVE PART OF REVIEW");
		 lblRemovePartOf.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 21));
		 lblRemovePartOf.setBounds(288, 40, 291, 40);
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
		 btnSubmit.setBounds(369, 517, 106, 30);
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
						
						boolean result = ReviewController.UpdateReviewContent(review, "reviewContent=\""+textReview.getText()+"\"", "reviewID=\""+ReviewID+"\"",screen.getClient());
						boolean result1 = ReviewController.UpdateReviewContent(review, "reviewStatus=\""+1+"\"", "reviewID=\""+ReviewID+"\"",screen.getClient());
						oldReview=textReview.getText();
						if(result&&result1)
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
	/**
	 * 
	 * @param name
	 * String from Text Field
	 * @return
	 * true or false if the String is legal
	 */
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