package Panels;


import javax.swing.JPanel;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.MatteBorder;

import java.awt.Color;

import javax.swing.border.LineBorder;
import javax.swing.JCheckBox;

import Book.Book;
import Book.Domain;
import Book.Review;
import Controller.BookController;
import ManagmentGUI.RemovePartReviewGUI;
import MenuGUI.LoginGUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JTextArea;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.CompoundBorder;

import client.DBgenericObject;

public class ReviewPanel extends JPanel{
	
	private LoginGUI screen;
	private Review review;
	private JLabel lblNewTitle;
	private JLabel lblNewDate;
	private JLabel lblDate;
	private JLabel lblContent;
	private JTextArea textAreaContent;
	private JLabel lblBookTitle;
	private JCheckBox chckbxChooseReview;
	private JButton btnRemovePartOf;
	//private int chbxChoose;
	private int reviewId;
	private int Permission;
	private JPanel pann;
	
	public ReviewPanel(LoginGUI screen,int reviewID,String reviewContent,String bookTitle,int permission,JPanel pan/*,String reviewDate*/) {
		setBackground(Color.WHITE);
		setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new MatteBorder(1, 1, 1, 1, (Color) new Color(255, 200, 0))));
		setPreferredSize(new Dimension(731, 128));
		setLayout(null);
		reviewId=reviewID;
		Permission=permission;
		pann=pan;
	//	review=r;
		
		lblDate = new JLabel("Date:");
		lblDate.setFont(new Font("VAGRounded BT", Font.BOLD, 17));
		lblDate.setBounds(46, 75, 103, 23);
		add(lblDate);
		
		//Book b=new Book();
	//	ArrayList<Book> book=BookController.SearchBook("title", b, "bookID=\""+r.getBookID()+"\"", screen.getClient());
		lblNewTitle = new JLabel(bookTitle);
		lblNewTitle.setBounds(155, 11, 93, 25);
		add(lblNewTitle);
		
		lblNewDate = new JLabel(""/*reviewDate*/);
		lblNewDate.setBounds(106, 82, 161, 16);
		add(lblNewDate);
		
		lblContent = new JLabel("Content:");
		lblContent.setFont(new Font("VAGRounded BT", Font.BOLD, 17));
		lblContent.setBounds(253, 13, 103, 16);
		add(lblContent);
		
		textAreaContent = new JTextArea();
		textAreaContent.setText(reviewContent);
		textAreaContent.setBounds(333, 13, 203, 102);
		add(textAreaContent);
		
		lblBookTitle = new JLabel("Book Name:");
		lblBookTitle.setFont(new Font("VAGRounded BT", Font.BOLD, 17));
		lblBookTitle.setBounds(46, 10, 114, 23);
		add(lblBookTitle);
		
		chckbxChooseReview = new JCheckBox("");
		chckbxChooseReview.setBackground(Color.WHITE);
		chckbxChooseReview.setBounds(8, 47, 29, 25);
		add(chckbxChooseReview);
		
		btnRemovePartOf = new JButton("Remove Part Of Review");
		btnRemovePartOf.setBounds(548, 47, 173, 25);
		btnRemovePartOf.addActionListener(new ActionListener() 
	{
		public void actionPerformed(ActionEvent e) 
		{
			//JOptionPane.showMessageDialog(screen,"okkkkkkkkkkkkkkkk", "Warning",JOptionPane.WARNING_MESSAGE);
			//setReviewID();
////////////////////////button to back to confirmation review from remove part of review /////////////////////////////////////////////
			RemovePartReviewGUI Rpr=new RemovePartReviewGUI(screen,reviewID);
			
			Rpr.btnBack.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) 
				{
					screen.setContentPane(pann);
				}
			});

////////////////////////button to back to confirmation review from remove part of review/////////////////////////////////////////////
		
		/*	if(flagReviewChoose==1)
			{*/
				screen.setContentPane(Rpr);
			//	flagReviewChoose=0;
			
		}
	});
	
	if(this.Permission==5){
		add(btnRemovePartOf);
		}
	
	}
	public int getchbxChoose()
	{
		if(chckbxChooseReview.isSelected())
			return 1;
		else
			return 0;
	}
	public int getReviewId()
	{
		return reviewId;
	}
}
