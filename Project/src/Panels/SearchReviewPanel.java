
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
import Controller.UserController;
import Controller.bookController;
import MenuGUI.LoginGUI;
import Role.User;
import Role.UserStatus;

import java.awt.Font;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class SearchReviewPanel extends JPanel{
	private ArrayList<Book> Books;
	private LoginGUI screen;
	private JLabel lblAnswerfromserver;
	
	public SearchReviewPanel(LoginGUI screen,Review r,String titleBook) {
		setBackground(Color.WHITE);
		setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(46, 139, 87)));
		setPreferredSize(new Dimension(577, 112));
		setLayout(null);
		
		JLabel lblUserid = new JLabel("book name:");
		lblUserid.setBounds(22, 11, 147, 14);
		add(lblUserid);
		
		JLabel lblUderiddb = new JLabel(titleBook);
		lblUderiddb.setBounds(179, 11, 141, 14);
		add(lblUderiddb);
		
		JLabel lblFirstName = new JLabel("Date post review:");
		lblFirstName.setBounds(22, 36, 147, 14);
		add(lblFirstName);
		
		JLabel lblFirstnamedb = new JLabel(r.getReviewDate().toString());
		lblFirstnamedb.setBounds(179, 36, 141, 14);
		add(lblFirstnamedb);
	
		lblAnswerfromserver = new JLabel("");
		lblAnswerfromserver.setBounds(415, 66, 152, 14);
		add(lblAnswerfromserver);
		
		JLabel lblContentOfThe = new JLabel("Content of the review:");
		lblContentOfThe.setBounds(330, 11, 199, 14);
		add(lblContentOfThe);
		
		JTextArea textArea = new JTextArea();
		textArea.setText(r.getReviewContent());
		textArea.setBounds(298, 31, 251, 66);
		add(textArea);

			
	}
}



