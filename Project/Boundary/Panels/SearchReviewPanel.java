
package Panels;

import javax.swing.JPanel;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
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
import javax.swing.border.CompoundBorder;

public class SearchReviewPanel extends JPanel{
	private ArrayList<Book> Books;
	private LoginGUI screen;
	private JLabel lblAnswerfromserver;
	
	public SearchReviewPanel(LoginGUI screen,Review r,String titleBook) {
		setBackground(Color.WHITE);
		setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new MatteBorder(1, 1, 1, 1, (Color) new Color(255, 255, 0))));
		setPreferredSize(new Dimension(649, 136));
		setLayout(null);
		
		JLabel lblUserid = new JLabel("Book Name:");
		lblUserid.setFont(new Font("VAGRounded BT", Font.BOLD, 17));
		lblUserid.setBounds(28, 0, 134, 39);
		add(lblUserid);
		
		JLabel lblUderiddb = new JLabel(titleBook);
		lblUderiddb.setBounds(158, 9, 125, 25);
		add(lblUderiddb);
		
		JLabel lblFirstName = new JLabel("Review Date:");
		lblFirstName.setFont(new Font("VAGRounded BT", Font.BOLD, 17));
		lblFirstName.setBounds(28, 87, 125, 25);
		add(lblFirstName);
		

		JLabel lblFirstnamedb = new JLabel(r.getReviewDate().toString());
		lblFirstnamedb.setBounds(162, 86, 110, 31);
		add(lblFirstnamedb);
	
		lblAnswerfromserver = new JLabel("");
		lblAnswerfromserver.setBounds(415, 66, 152, 14);
		add(lblAnswerfromserver);
		
		JLabel lblContentOfThe = new JLabel("Review Content:");
		lblContentOfThe.setForeground(Color.BLACK);
		lblContentOfThe.setFont(new Font("VAGRounded BT", Font.BOLD, 17));
		lblContentOfThe.setBounds(383, 7, 152, 25);
		add(lblContentOfThe);
		
		
		String s=r.getReviewContent();
		/*
		String temp="";
		for(int i=0;i<s.length();i++)
		{
			if(i%120==0)
			{
				temp+= "\n";
				
			}
			temp+=s.charAt(i);
		}
		*/
		
		/*JTextArea textArea = new JTextArea();
		textArea.setBackground(Color.YELLOW);
		textArea.setForeground(Color.BLACK);
		textArea.setLineWrap(true);
		textArea.setText(s);
		
		textArea.setBounds(247, 25, 303, 98);
		add(textArea);*/
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(284, 36, 333, 87);
		add(scrollPane);
		JTextArea textArea = new JTextArea();
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		scrollPane.setViewportView(textArea);
		textArea.setRows(4);
		textArea.setBackground(Color.YELLOW);
		textArea.setForeground(new Color(0, 0, 0));
		textArea.setText(s);

			
	}
}



