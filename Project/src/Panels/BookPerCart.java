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
	private Book book;
	private JLabel lblNewTitle;
	private JLabel lblNewBookID;
	private JLabel lblBookID;
	private JLabel lblAutor;
	private JLabel lblNewAutor;
	private JLabel lblDate;
	private JLabel lblNewDate ;
	private JLabel lblTitle;
	
	public BookPerCart(LoginGUI screen,Book b,Cart c) {
		setBackground(Color.WHITE);
		setBorder(new MatteBorder(3, 3, 3, 3, (Color) Color.ORANGE));
		setPreferredSize(new Dimension(702, 59));
		setLayout(null);
		book=b;
		
		lblBookID = new JLabel("Book ID:");
		lblBookID.setFont(new Font("VAGRounded BT", Font.BOLD, 17));
		lblBookID.setBounds(28, 13, 83, 23);
		add(lblBookID);
		
		lblNewTitle = new JLabel(b.getTitle());
		lblNewTitle.setBounds(295, 18, 56, 16);
		add(lblNewTitle);
		
		lblNewBookID = new JLabel(Integer.toString(b.getBookID()));
		lblNewBookID.setBounds(123, 18, 56, 16);
		add(lblNewBookID);
		
		lblAutor = new JLabel("Autor:");
		lblAutor.setFont(new Font("VAGRounded BT", Font.BOLD, 17));
		lblAutor.setBounds(406, 16, 56, 16);
		add(lblAutor);
		
		lblNewAutor = new JLabel(b.getAuthor());
		lblNewAutor.setBounds(474, 18, 56, 16);
		add(lblNewAutor);
		
		lblDate = new JLabel("Date:");
		lblDate.setFont(new Font("VAGRounded BT", Font.BOLD, 17));
		lblDate.setBounds(563, 14, 56, 20);
		add(lblDate);
		
		lblNewDate = new JLabel(c.getDate());
		lblNewDate.setBounds(619, 18, 56, 16);
		add(lblNewDate);
		
		lblTitle = new JLabel("Title:");
		lblTitle.setFont(new Font("VAGRounded BT", Font.BOLD, 17));
		lblTitle.setBounds(239, 16, 56, 16);
		add(lblTitle);
	}
	
	public int getBookID()
	{
		return book.getBookID();
	}
}
