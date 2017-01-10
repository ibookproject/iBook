package MemberGUI;

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
import Controller.bookController;
import MenuGUI.LoginGUI;

import java.awt.Font;
import java.util.ArrayList;

public class BookPanel extends JPanel{
	private ArrayList<Book> Books;
	private LoginGUI screen;
	private Book b;
	
	public BookPanel(LoginGUI screen) {
		setBackground(Color.WHITE);
		setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(51, 102, 204)));
		setPreferredSize(new Dimension(731, 127));
		setLayout(null);
		
		JButton btnPostReview = new JButton("Post Review");
		btnPostReview.setBounds(601, 51, 117, 23);
		add(btnPostReview);
		
		JLabel lblLanguage = new JLabel("Language:");
		lblLanguage.setFont(new Font("VAGRounded BT", Font.BOLD, 17));
		lblLanguage.setBounds(183, 49, 97, 23);
		add(lblLanguage);
		
		JCheckBox chckbxBookTitle = new JCheckBox("Title:");
		chckbxBookTitle.setFont(new Font("VAGRounded BT", Font.BOLD, 16));
		chckbxBookTitle.setBackground(Color.WHITE);
		chckbxBookTitle.setBounds(24, 50, 72, 25);
		add(chckbxBookTitle);
		
		JLabel lblNewTitle = new JLabel("??");
		lblNewTitle.setBounds(97, 54, 56, 16);
		add(lblNewTitle);
		
		JLabel lblNewLanguage = new JLabel("??");
		lblNewLanguage.setBounds(292, 54, 56, 16);
		add(lblNewLanguage);
		
		JLabel lblAutor = new JLabel("Autor:");
		lblAutor.setFont(new Font("VAGRounded BT", Font.BOLD, 17));
		lblAutor.setBounds(346, 54, 56, 16);
		add(lblAutor);
		
		JLabel lblNewAutor = new JLabel("??");
		lblNewAutor.setBounds(415, 54, 56, 16);
		add(lblNewAutor);
		
		JLabel lblSummary = new JLabel("Summary:");
		lblSummary.setFont(new Font("VAGRounded BT", Font.BOLD, 17));
		lblSummary.setBounds(45, 77, 89, 20);
		add(lblSummary);
		
		JLabel lblNewSummary = new JLabel("??");
		lblNewSummary.setBounds(157, 81, 56, 16);
		add(lblNewSummary);
		b=new Book();
		Books=new ArrayList<Book>();
		Books=bookController.getAllBookTable(b, screen.getClient());
		//for(int i=0;i<Books.size();i++)
			
	}
}
