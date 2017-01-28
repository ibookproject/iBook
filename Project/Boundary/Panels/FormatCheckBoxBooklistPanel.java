
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
import Controller.BookController;
import MenuGUI.LoginGUI;
import Role.User;
import Role.UserStatus;

import java.awt.Font;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.DropMode;
import java.awt.SystemColor;
/**
 * @author hen saada 
 * This panel presents the details of each panel at  List at Format manager class 
 */
public class FormatCheckBoxBooklistPanel extends JPanel{
	private LoginGUI screen;
	private JLabel lblAnswerfromserver;
	public JCheckBox chckbxNewCheckBox ;
	public int DomainID;
	public Book book;
	
	
	/**
	 * This is the constructor of the class FormatManager-put the components on the screen and set their functionality
	 * @param screen This is the main window-login
	 * @param book is the Book which the panel shown his details
	 * @param DomainID save the domain id that this book is belong
	 * @author  hen saada
	 */
	public FormatCheckBoxBooklistPanel(LoginGUI screen,Book book,int DomainID) {
		this.book=book;
		this.DomainID=DomainID;
		setBackground(Color.WHITE);
		setBorder(new MatteBorder(3, 3, 3, 3, (Color) SystemColor.inactiveCaption));
		setPreferredSize(new Dimension(249, 38));
		setLayout(null);
	
		lblAnswerfromserver = new JLabel("");
		lblAnswerfromserver.setBounds(415, 66, 152, 14);
		add(lblAnswerfromserver);
		
		 chckbxNewCheckBox = new JCheckBox();
		 chckbxNewCheckBox.setBackground(SystemColor.inactiveCaptionBorder);
		chckbxNewCheckBox.setText("Title: " + book.getTitle() + "  Author: " + book.getAuthor());
		chckbxNewCheckBox.setBounds(6, 7, 237, 23);
		add(chckbxNewCheckBox);
		
	}
}



