
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
import javax.swing.DropMode;

public class FormatCheckBoxBooklistPanel extends JPanel{
	private LoginGUI screen;
	private JLabel lblAnswerfromserver;
	public JCheckBox chckbxNewCheckBox ;
	public int DomainID;
	public Book book;
	
	
	public FormatCheckBoxBooklistPanel(LoginGUI screen,Book book,int DomainID) {
		this.book=book;
		this.DomainID=DomainID;
		setBackground(Color.WHITE);
		setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(46, 139, 87)));
		setPreferredSize(new Dimension(249, 38));
		setLayout(null);
	
		lblAnswerfromserver = new JLabel("");
		lblAnswerfromserver.setBounds(415, 66, 152, 14);
		add(lblAnswerfromserver);
		
		 chckbxNewCheckBox = new JCheckBox();
		chckbxNewCheckBox.setText("Name: " + book.getTitle() + "  Author: " + book.getAuthor());
		chckbxNewCheckBox.setBounds(6, 7, 237, 23);
		add(chckbxNewCheckBox);
		
	}
}



