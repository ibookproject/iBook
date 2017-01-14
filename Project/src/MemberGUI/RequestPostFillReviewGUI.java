package MemberGUI;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import Book.Book;
import Book.Review;
import Controller.ReviewController;
import Controller.bookController;
import MenuGUI.LoginGUI;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;




public class RequestPostFillReviewGUI extends JPanel 
{
	private static final long serialVersionUID = 1L;
	public JButton btnBack ;
	private JTextField textFieldReviewDate;
	private String bookID;
	private JTextField textField;
	public LoginGUI screen;
	private Date date;
	public RequestPostFillReviewGUI(LoginGUI screen,String bookId) {
		super();
		//this.bookID=bookId;
		this.screen=screen;
		initialize();
	}

	private void initialize() 
	{	
		this.setSize(850, 625);
		this.setLayout(null);	
		ImageIcon backIcon =new ImageIcon("src/images/backIcon.png");
		btnBack = new JButton(backIcon);// declaration of back button
		btnBack.setBounds(39, 52, 89, 23);
		add(btnBack);
		
		JLabel lblFillAReview = new JLabel("Fill a review");
		lblFillAReview.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblFillAReview.setBounds(384, 59, 290, 16);
		add(lblFillAReview);
		
		
		JLabel lblReviewDate = new JLabel("Review Date:");
		lblReviewDate.setBounds(271, 141, 81, 16);
		add(lblReviewDate);
		
		JLabel lblBookID = new JLabel("Book ID:");
		lblBookID.setBounds(271, 170, 56, 16);
		add(lblBookID);
		
		JLabel lblReviewContent = new JLabel("Review Content:");
		lblReviewContent.setBounds(271, 199, 94, 16);
		add(lblReviewContent);
		
		textFieldReviewDate = new JTextField();
		textFieldReviewDate.setEnabled(false);
		textFieldReviewDate.setBounds(384, 138, 116, 22);

		date = new Date();
		String txtDate = new SimpleDateFormat("yyyy/MM/dd").format(date);
		textFieldReviewDate.setText(txtDate);
		add(textFieldReviewDate);
		SimpleDateFormat dt = new SimpleDateFormat("yyyy/MM/dd"); 
		date = new Date(txtDate);
			
		JTextPane textPaneReviewContent = new JTextPane();
		textPaneReviewContent.setBounds(384, 199, 323, 233);
		add(textPaneReviewContent);
		
		JTextField textFieldBookID = new JTextField();
		textFieldBookID.setText("");
		textFieldBookID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		textFieldBookID.setBounds(384, 167, 116, 22);
		add(textFieldBookID);
		textFieldBookID.setColumns(10);
		
		JButton btnPost = new JButton("Post");
		btnPost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			//convert the current time to String
				Calendar time = Calendar.getInstance();
				//we can use also with Date object and insert it to Calander ********* we need to choose
				/*Date f  = new Date();
				time.setTime(f);*/
		        String timeRightNow = String.format("%1$tY/%1$tm/%1$td", time);

				//String txtDate = new SimpleDateFormat("yyyy/dd/MM").format(date);
		        if(textFieldBookID.getText().equals(""))
					JOptionPane.showMessageDialog(screen,"Please insert BookID ! ", "Warning",JOptionPane.WARNING_MESSAGE);
		        else if(!(textPaneReviewContent.getText().equals("")))
				{
		        Review r = new Review(timeRightNow,textPaneReviewContent.getText(),0,Integer.parseInt(textFieldBookID.getText()));
		        Book b=new Book();
				ArrayList<Book> temp = bookController.SearchBook("title,language,author,summary",b,"bookID=\""+Integer.parseInt(textFieldBookID.getText())+"", screen.getClient());//call search book method from book controller
				if(temp==null)
					JOptionPane.showMessageDialog(screen,"not found any book result\n", "Warning",JOptionPane.WARNING_MESSAGE);
				else{
		        boolean result = ReviewController.AddReview(r,screen.getClient());
				if (result==false)
					JOptionPane.showMessageDialog(screen,"Add Reviwe process FAILED ! ", "Warning",JOptionPane.WARNING_MESSAGE);
			 	else
			 	{
			 		JOptionPane.showMessageDialog(screen,"Add Reviwe process Done ! ", "Warning",JOptionPane.WARNING_MESSAGE);
			 	}
				}//temp!=null
				}//else if
		        else
		        	JOptionPane.showMessageDialog(screen,"Please insert Review content ! ", "Warning",JOptionPane.WARNING_MESSAGE);
			}
		});
		btnPost.setBounds(357, 509, 97, 25);
		add(btnPost);
		
		
	}
}
