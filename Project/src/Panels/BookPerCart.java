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
import Book.Cart;
import Book.Domain;
import Controller.BookController;
import MenuGUI.LoginGUI;

import java.awt.Font;
import java.util.ArrayList;

public class BookPerCart extends JPanel{
	
	private LoginGUI screen;
	//private Book book;
	private JLabel lblNewTitle;
	private JLabel lblNewBookID;
	private JLabel lblBookID;
	private JLabel lblAutor;
	private JLabel lblNewAutor;
	private JLabel lblDate;
	private JLabel lblNewDate ;
	private JLabel lblTitle;
	
	public BookPerCart(LoginGUI screen,int bookId,String bookTitle, String bookAutor,String cartDate) {
		setBackground(Color.WHITE);
		setBorder(new MatteBorder(3, 3, 3, 3, (Color) Color.ORANGE));
		setPreferredSize(new Dimension(702, 59));
		setLayout(null);
		//book=b;
		
		lblBookID = new JLabel("Book ID:");
		lblBookID.setFont(new Font("VAGRounded BT", Font.BOLD, 21));
		lblBookID.setBounds(12, 13, 99, 23);
		add(lblBookID);
		
		lblNewTitle = new JLabel(bookTitle);
		lblNewTitle.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewTitle.setBounds(212, 9, 120, 33);
		add(lblNewTitle);
		
		lblNewBookID = new JLabel(Integer.toString(bookId));
		lblNewBookID.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewBookID.setBounds(109, 13, 57, 25);
		add(lblNewBookID);
		
		lblAutor = new JLabel("Author:");
		lblAutor.setFont(new Font("VAGRounded BT", Font.BOLD, 21));
		lblAutor.setBounds(344, 11, 76, 26);
		add(lblAutor);
		
		lblNewAutor = new JLabel(bookAutor);
		lblNewAutor.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewAutor.setBounds(421, 11, 116, 29);
		add(lblNewAutor);
		
		lblDate = new JLabel("Date:");
		lblDate.setFont(new Font("VAGRounded BT", Font.BOLD, 21));
		lblDate.setBounds(534, 13, 70, 22);
		add(lblDate);
		
		lblNewDate = new JLabel(cartDate);
		lblNewDate.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewDate.setBounds(602, 14, 100, 23);
		add(lblNewDate);
		
		lblTitle = new JLabel("Title:");
		lblTitle.setFont(new Font("VAGRounded BT", Font.BOLD, 21));
		lblTitle.setBounds(150, 13, 57, 23);
		add(lblTitle);
	}
	
/*	public int getBookID()
	{
		return book.getBookID();
	}*/
}
