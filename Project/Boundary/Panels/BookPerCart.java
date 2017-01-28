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
/**
 * 
 * @author Coral Carmeli
 * This panel presents the book in the cart of the requested user in the method User Statistics
 */
public class BookPerCart extends JPanel{
	
	private LoginGUI screen;
	private JLabel lblNewTitle;
	private JLabel lblNewBookID;
	private JLabel lblBookID;
	private JLabel lblAutor;
	private JLabel lblNewAutor;
	private JLabel lblDate;
	private JLabel lblNewDate ;
	private JLabel lblTitle;

	/**
	 * This is the constructor of the class BookPerCart-put the components on the screen and set their functionality
	 * @param screen This is the main window-login
	 * @param bookId this is the book id which book per cart is presented for
	 * @param bookTitle this is the book title which book per cart is presented for
	 * @param bookAutor  this is the book Author which book per cart is presented for
	 * @param cartDate this is the cart date which book per cart is presented for
	 * @author  Coral Carmeli
	 */
	public BookPerCart(LoginGUI screen,int bookId,String bookTitle, String bookAutor,String cartDate) {
		setBackground(Color.WHITE);
		setBorder(new MatteBorder(3, 3, 3, 3, (Color) Color.ORANGE));
		setPreferredSize(new Dimension(702, 59));
		setLayout(null);
		
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
		lblAutor.setBounds(321, 11, 77, 26);
		add(lblAutor);
		
		lblNewAutor = new JLabel(bookAutor);
		lblNewAutor.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewAutor.setBounds(407, 11, 130, 29);
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

}
