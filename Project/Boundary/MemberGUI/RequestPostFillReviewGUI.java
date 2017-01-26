package MemberGUI;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import Book.Book;
import Book.Review;
import Controller.ReviewController;
import Extras.Validation;
import Controller.BookController;
import MenuGUI.LoginGUI;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;

import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.border.LineBorder;
import javax.swing.UIManager;
/**
 * The class take care of the request post and fill the review- when the user wants to post a new review he comes to this window-
 * wrote his review and post this with the day of today-default
 * @author Sagi Entenberg
 */
public class RequestPostFillReviewGUI extends JPanel 
{
	private static final long serialVersionUID = 1L;
	public JButton btnBack ;
	private JTextField textFieldReviewDate;
	private int bookID;
	private JTextField textField;
	public LoginGUI screen;
	private Date date;
	private SearchBook pan;
	private int conterOfText;
	
	/**
	 * The constructor of RequestPostFillReviewGUI
	 * @param screen This is the main window-login
	 * @param bookId this is the ID of the specific book of the review the user want to post 
	 * @param sp this is the previous window which the user needs to return
	 * @author Sagi Entenberg
	 */
	public RequestPostFillReviewGUI(LoginGUI screen,int bookId,SearchBook sp) {
		super();
		bookID=bookId;
		pan=sp;
		setBorder(new LineBorder(UIManager.getColor("Button.background")));
		//this.bookID=bookId;
		this.screen=screen;
		initialize();
	}
	/**
	 * @param no parameters
	 * @return void
	 * This method initialize The window of RequestPostFillReviewGUI-put the components on the screen and set their functionality
	 * @author Sagi Entenberg
	 */

	private void initialize() 
	{	
		this.setSize(850, 625);
		this.setLayout(null);	
		ImageIcon backIcon =new ImageIcon("Extras/Images/backIcon.png");
		
		JLabel Counterlabel = new JLabel("50");
		Counterlabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Counterlabel.setHorizontalAlignment(SwingConstants.CENTER);
		Counterlabel.setForeground(Color.RED);
		Counterlabel.setBounds(717, 469, 27, 23);
		add(Counterlabel);
		btnBack = new JButton(backIcon);// declaration of back button
		btnBack.setBounds(39, 52, 89, 23);
		add(btnBack);
		
		JLabel lblFillAReview = new JLabel("Fill a review");
		lblFillAReview.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 22));
		lblFillAReview.setBounds(357, 67, 166, 83);
		add(lblFillAReview);
		
		
		JLabel lblReviewDate = new JLabel("Review Date:");
		lblReviewDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblReviewDate.setBounds(123, 238, 128, 19);
		add(lblReviewDate);
		
		JLabel lblReviewContent = new JLabel("Review Content:");
		lblReviewContent.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblReviewContent.setBounds(123, 263, 128, 29);
		add(lblReviewContent);
		
		textFieldReviewDate = new JTextField();
		textFieldReviewDate.setEditable(false);
		textFieldReviewDate.setBackground(UIManager.getColor("Button.background"));
		textFieldReviewDate.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(240, 240, 240)));
		textFieldReviewDate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldReviewDate.setEnabled(false);
		textFieldReviewDate.setBounds(263, 236, 89, 23);

		date = new Date();
		String txtDate = new SimpleDateFormat("yyyy/MM/dd").format(date);
		textFieldReviewDate.setText(txtDate);
		add(textFieldReviewDate);
		SimpleDateFormat dt = new SimpleDateFormat("yyyy/MM/dd"); 
		date = new Date(txtDate);
		
		/**
		 * This is the text area where the user needs to insert the content of his new review
		 */
		
		JTextArea textAreaReviewContent = new JTextArea();
		textAreaReviewContent.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 191, 255)));
		textAreaReviewContent.setLineWrap(true);
		textAreaReviewContent.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textAreaReviewContent.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent a)
			{
				char c = a.getKeyChar();
				String charToString = Character.toString(c);
				if(!Validation.ContentValidation(charToString))
					{
					a.consume();
					
					
					}
				if(textAreaReviewContent.getText().length()>=50)
					a.consume();
			
			}
			@Override
			public void keyPressed(KeyEvent pressedChar) {
				if((pressedChar.getKeyChar()==8)&&(conterOfText>0))
				{
					
					conterOfText--;
					if(conterOfText<=50)
						Counterlabel.setText(Integer.toString((50-conterOfText)));
				
				}
				else 
				{
					if((pressedChar.getKeyChar()!=8)&&conterOfText<50&&(pressedChar.getKeyCode()!=16))
					{
						char c = pressedChar.getKeyChar();
						String charToString = Character.toString(c);
						if(!Validation.ContentValidation(charToString))
							{
							JOptionPane.showMessageDialog(screen,"Incorrect input char\n", "Warning",JOptionPane.WARNING_MESSAGE);
							pressedChar.consume();
							
							
							}
						else
						{
							conterOfText++;
							Counterlabel.setText(Integer.toString((50-conterOfText)));
						}
					}
					
				}
			}
		
		});
		textAreaReviewContent.setBounds(263, 263, 431, 233);
		add(textAreaReviewContent);
		
		/**
		 * This button is the 'post' button- when the user press, there is some input valdition and than if its OK-
		 * insert the new review to DB
		 * @author Sagi Entenberg
		 * 
		 */	
		
		JButton btnPost = new JButton("Post");
		btnPost.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnPost.setBorder(new LineBorder(new Color(0, 191, 255), 2));
		btnPost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			//convert the current time to String
				Calendar time = Calendar.getInstance();
		        String timeRightNow = String.format("%1$tY/%1$tm/%1$td", time);

		        if(!(textAreaReviewContent.getText().equals(""))&&(conterOfText<=50))
				{
		        	
		        Review r = new Review(textAreaReviewContent.getText(),bookID,screen.getTempID());
		        Book b=new Book();
				ArrayList<Book> temp = BookController.SearchBook("bookID",b,"bookID=\""+bookID+"\"", screen.getClient());//call search book method from book controller
				if(temp==null)
					JOptionPane.showMessageDialog(screen,"not found any book result\n", "Warning",JOptionPane.WARNING_MESSAGE);
				else{
					if (JOptionPane.showConfirmDialog(null, "Are you sign on the review?", "WARNING",
					        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					    // yes option
					
		        boolean result = ReviewController.AddReview(r,screen.getClient());
				if (result==false)
					JOptionPane.showMessageDialog(screen,"Add Reviwe process FAILED ! ", "Warning",JOptionPane.WARNING_MESSAGE);
			 	else
			 	{
			 		JOptionPane.showMessageDialog(screen,"Add Reviwe process Done ! ", "",JOptionPane.INFORMATION_MESSAGE);
			 		screen.setContentPane(pan);
			 	}
				}//temp!=null
					else{
						JOptionPane.showMessageDialog(screen,"You must Sign on the review befor the post ! ", "",JOptionPane.INFORMATION_MESSAGE);
					}
				}//yes no option
			
				}//else if
		        else if(conterOfText>=50)
		        	JOptionPane.showMessageDialog(screen,"There are too many characters ! ", "Warning",JOptionPane.WARNING_MESSAGE);
		        else
		        	JOptionPane.showMessageDialog(screen,"Please insert Review content ! ", "Warning",JOptionPane.WARNING_MESSAGE);
			}
			
		});
		btnPost.setBounds(351, 529, 123, 40);
		add(btnPost);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(250, 128, 114)));
		btnNewButton.setEnabled(false);
		btnNewButton.setBounds(706, 467, 50, 29);
		add(btnNewButton);
		
		
	}
}
