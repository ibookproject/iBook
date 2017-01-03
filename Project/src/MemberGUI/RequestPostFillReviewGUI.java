package MemberGUI;

import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;




public class RequestPostFillReviewGUI extends JPanel 
{
	private static final long serialVersionUID = 1L;
	public JButton btnBack ;
	private JTextField textFieldReviewDate;
	private JTextField textFieldBookID;
	private String bookID;
	
	public RequestPostFillReviewGUI(JFrame screen,String bookId) {
		super();
		this.bookID=bookId;
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
		textFieldReviewDate.setBounds(384, 138, 116, 22);
		add(textFieldReviewDate);
		textFieldReviewDate.setColumns(10);
		
		textFieldBookID = new JTextField();
		textFieldBookID.setBounds(384, 167, 116, 22);
		textFieldBookID.setText(bookID);
		add(textFieldBookID);
		textFieldBookID.setColumns(10);
		
		JTextPane textPaneReviewContent = new JTextPane();
		textPaneReviewContent.setBounds(384, 199, 323, 233);
		add(textPaneReviewContent);
		
		JButton btnPost = new JButton("Post");
		btnPost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnPost.setBounds(357, 509, 97, 25);
		add(btnPost);
	}
}
