package ManagmentGUI;


import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;

import Book.Book;
import Book.Review;
import Controller.ReviewController;
import Controller.UserController;
import Controller.bookController;
import MenuGUI.LoginGUI;
import Role.User;
import Role.UserStatus;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JCheckBox;




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
	private JLabel lblDateStr;
	private JTextArea textAreaReviewContent;
	private int reviewID;
	private int flag=0;
	
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
		btnBack.setBounds(39, 52, 89, 23);
		add(btnBack);
		
		confirmLbl = new JLabel("Confirmation Review");
		confirmLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		confirmLbl.setBounds(355, 49, 175, 22);
		add(confirmLbl);
	
		btnNotConfirm = new JButton("Not Confirm");
		btnNotConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(screen,"Request Denied-Not confirmed\n", "Warning",JOptionPane.WARNING_MESSAGE);
				Review r=new Review();
				ReviewController.DeleteReview(r,"reviewID=\""+reviewID+"\"",screen.getClient());
			}
		});
		btnNotConfirm.setBounds(388, 509, 109, 25);
		add(btnNotConfirm);
		
		btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Review r=new Review();
				boolean review=ReviewController.UpdateReviewContent(r,"reviewStatus=\""+1+"\"","reviewID=\""+reviewID+"\"" ,screen.getClient());/*needs to update the reviewStatus not content!*/
				if(review==false)
				{
					screen.setContentPane(pann);
					JOptionPane.showMessageDialog(screen,"Review was not Found,Not updated\n", "Warning",JOptionPane.WARNING_MESSAGE);
				}
			
				else
					JOptionPane.showMessageDialog(screen,"Update Submitted Succsesfully!!\n", "Warning",JOptionPane.WARNING_MESSAGE);
				
			}
		});
		btnConfirm.setBounds(230, 509, 109, 25);
		add(btnConfirm);
		
		Review r=new Review();
		ArrayList<Review> Reviews= (ArrayList<Review>)ReviewController.SearchReviews("reviewID,reviewDate,reviewContent,reviewStatus,bookID",r,"reviewStatus=\""+0+"\"" ,screen.getClient());
		if(Reviews==null||Reviews.isEmpty())
		{
			screen.setContentPane(pann);
			JOptionPane.showMessageDialog(screen,"Review was not Found\n", "Warning",JOptionPane.WARNING_MESSAGE);
		}
	
		else
			JOptionPane.showMessageDialog(screen,"Review Found\n", "Warning",JOptionPane.WARNING_MESSAGE);
		
		
		chckbxBookName = new JCheckBox("Book Name");
		chckbxBookName.setBounds(194, 159, 111, 25);
		
		chckbxBookName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				flag=1;
			}
		});

		Book b=new Book();
		ArrayList<Book> book= (ArrayList<Book>)bookController.SearchBook("title",b,"bookID=\""+Reviews.get(0).getBookID()+"\"" ,screen.getClient());
		if(book==null||book.isEmpty())
		{

			screen.setContentPane(pann);
			JOptionPane.showMessageDialog(screen,"User was not Found\n", "Warning",JOptionPane.WARNING_MESSAGE);
		}
	
		else
			JOptionPane.showMessageDialog(screen,"User Found\n", "Warning",JOptionPane.WARNING_MESSAGE);
	
		chckbxBookName.setText(book.get(0).getTitle());
		add(chckbxBookName);
		
		lblDate = new JLabel("Date:");
		lblDate.setBounds(214, 193, 74, 16);
		add(lblDate);
		
		lblDateStr = new JLabel(" ??-??-??");
		lblDateStr.setBounds(250, 193, 56, 16);
		lblDateStr.setText(Reviews.get(0).getReviewDate().toString());
		add(lblDateStr);
		
		textAreaReviewContent = new JTextArea();
		textAreaReviewContent.setText(Reviews.get(0).getReviewContent());
		textAreaReviewContent.setBounds(320, 160, 275, 49);
		add(textAreaReviewContent);
		
		
		reviewID=Reviews.get(0).getReviewID();
		
		btnRemovePartReview = new JButton("Remove Part Of Review");
		
		if(this.Permission==5){
		add(btnRemovePartReview);}
		
		btnRemovePartReview.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
////////////////////////button to back to confirmation review from remove part of review /////////////////////////////////////////////
			
				RemovePartReviewGUI Rpr=new RemovePartReviewGUI(screen,reviewID);
				Rpr.btnBack.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e) 
					{
						
						screen.setContentPane(pann);
					}
////////////////////////button to back to confirmation review from remove part of review/////////////////////////////////////////////
				});
		
				if(chckbxBookName.isSelected())
				{
					screen.setContentPane(Rpr);
				}
	
			}
		
		});
		
		
		btnRemovePartReview.setBounds(536, 509, 187, 25);
	
		
		/*JRadioButton rdbtnConfirm = new JRadioButton("Remove Part Of Review");
		rdbtnConfirm.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				
			}
		});
		rdbtnConfirm.setBounds(67, 545, 123, 25);
		add(rdbtnConfirm);*/
		
	
	}
}