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
import Controller.bookController;
import MenuGUI.LoginGUI;

import java.awt.Font;
import java.util.ArrayList;

public class BookPanel extends JPanel{
	private ArrayList<Book> Books;
	private LoginGUI screen;
	private Book book;
	private JLabel lblNewTitle;
	private JLabel lblNewLanguage;
	private JCheckBox chckbxBookTitle;
	private JLabel lblLanguage;
	private JButton btnPostReview;
	private JLabel lblAutor;
	private JLabel lblNewAutor;
	private JLabel lblSummary;
	private JLabel lblNewSummary ;
	
	public BookPanel(LoginGUI screen,Book b) {
		setBackground(Color.WHITE);
		setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(51, 102, 204)));
		setPreferredSize(new Dimension(731, 127));
		setLayout(null);
		book=b;
		
		btnPostReview = new JButton("Post Review");
		btnPostReview.setBounds(601, 51, 117, 23);
		add(btnPostReview);
		
		lblLanguage = new JLabel("Language:");
		lblLanguage.setFont(new Font("VAGRounded BT", Font.BOLD, 17));
		lblLanguage.setBounds(183, 49, 97, 23);
		add(lblLanguage);
		
		chckbxBookTitle = new JCheckBox("Title:");
		chckbxBookTitle.setFont(new Font("VAGRounded BT", Font.BOLD, 16));
		chckbxBookTitle.setBackground(Color.WHITE);
		chckbxBookTitle.setBounds(24, 50, 72, 25);
		add(chckbxBookTitle);
		
		lblNewTitle = new JLabel(b.getTitle());
		lblNewTitle.setBounds(97, 54, 56, 16);
		add(lblNewTitle);
		
		lblNewLanguage = new JLabel(b.getLanguage());
		lblNewLanguage.setBounds(292, 54, 56, 16);
		add(lblNewLanguage);
		
		lblAutor = new JLabel("Autor:");
		lblAutor.setFont(new Font("VAGRounded BT", Font.BOLD, 17));
		lblAutor.setBounds(346, 54, 56, 16);
		add(lblAutor);
		
		lblNewAutor = new JLabel(b.getAuthor());
		lblNewAutor.setBounds(415, 54, 56, 16);
		add(lblNewAutor);
		
		lblSummary = new JLabel("Summary:");
		lblSummary.setFont(new Font("VAGRounded BT", Font.BOLD, 17));
		lblSummary.setBounds(45, 77, 89, 20);
		add(lblSummary);
		
		lblNewSummary = new JLabel(b.getSummary());
		lblNewSummary.setBounds(157, 81, 56, 16);
		add(lblNewSummary);
		
			
	}
}
