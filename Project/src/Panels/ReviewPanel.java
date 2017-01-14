package Panels;


import javax.swing.JPanel;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.MatteBorder;

import java.awt.Color;

import javax.swing.border.LineBorder;
import javax.swing.JCheckBox;

import Book.Book;
import Book.Domain;
import Book.Review;
import Controller.bookController;
import MenuGUI.LoginGUI;

import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JTextArea;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.CompoundBorder;

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
	private int chbxChoose;
	
	public ReviewPanel(LoginGUI screen,Review r) {
		setBackground(Color.WHITE);
		setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new MatteBorder(1, 1, 1, 1, (Color) new Color(255, 200, 0))));
		setPreferredSize(new Dimension(731, 128));
		setLayout(null);
		review=r;
		
		lblDate = new JLabel("Date:");
		lblDate.setFont(new Font("VAGRounded BT", Font.BOLD, 17));
		lblDate.setBounds(46, 63, 103, 23);
		add(lblDate);
		
		Book b=new Book();
		ArrayList<Book> book=bookController.SearchBook("title", b, "bookID=\""+r.getBookID()+"\"", screen.getClient());
		lblNewTitle = new JLabel(book.get(0).getTitle());
		lblNewTitle.setBounds(168, 34, 93, 16);
		add(lblNewTitle);
		
		lblNewDate = new JLabel(r.getReviewDate());
		lblNewDate.setBounds(167, 68, 161, 16);
		add(lblNewDate);
		
		lblContent = new JLabel("Content:");
		lblContent.setFont(new Font("VAGRounded BT", Font.BOLD, 17));
		lblContent.setBounds(346, 32, 103, 16);
		add(lblContent);
		
		textAreaContent = new JTextArea();
		textAreaContent.setText(r.getReviewContent());
		textAreaContent.setBounds(431, 31, 265, 84);
		add(textAreaContent);
		
		lblBookTitle = new JLabel("Book Name:");
		lblBookTitle.setFont(new Font("VAGRounded BT", Font.BOLD, 17));
		lblBookTitle.setBounds(46, 27, 114, 23);
		add(lblBookTitle);
		
		chckbxChooseReview = new JCheckBox("");
		chckbxChooseReview.setBackground(Color.WHITE);
		chckbxChooseReview.setBounds(8, 47, 29, 25);
		add(chckbxChooseReview);
		
	
	}
	public int getchbxChoose()
	{
		if(chckbxChooseReview.isSelected())
			chbxChoose=1;
		else
			chbxChoose=0;
		return chbxChoose;
	}
	public Review getReview()
	{
		return review;
	}
}
