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
import Controller.BookController;
import MemberGUI.RequestPostFillReviewGUI;
import MemberGUI.SearchBook;
import MenuGUI.LoginGUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JInternalFrame;
import javax.swing.ScrollPaneConstants;

public class BookPanel extends JPanel{
	
	private LoginGUI screen;
	//private Book book;
	private JLabel lblNewTitle;
	private JLabel lblNewLanguage;
	private JLabel lblLanguage;
	private JButton btnPostReview;
	private JLabel lblAutor;
	private JLabel lblNewAutor;
	private JLabel lblSummary;
	//private JLabel lblNewSummary ;
//	private int bookID;
	private SearchBook pann;
	private JLabel lblTitle;
	private JButton btnNewButton;
	private JTextArea textAreaSummary;
	
	public BookPanel(LoginGUI screen,Book b,SearchBook pan) {
		pann=pan;
		setBackground(Color.WHITE);
		setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(51, 102, 204)));
		setPreferredSize(new Dimension(731, 212));
		setLayout(null);
	//	book=b;
		
		btnPostReview = new JButton("Post Review");
		btnPostReview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
////////////////////////button to back panel from panel /////////////////////////////////////////////
				RequestPostFillReviewGUI Pfr=new RequestPostFillReviewGUI(screen,b.getBookID(),pan);
				Pfr.btnBack.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						screen.setContentPane(pann);
					}
////////////////////////button to back panel from panel/////////////////////////////////////////////
				});
				screen.setContentPane(Pfr);//
			}
		});
		btnPostReview.setBounds(601, 120, 117, 23);
		add(btnPostReview);
		
		
		btnNewButton = new JButton("Add to Cart");
		btnNewButton.setBounds(601, 156, 117, 25);
		add(btnNewButton);
		
		lblLanguage = new JLabel("Language:");
		lblLanguage.setFont(new Font("VAGRounded BT", Font.BOLD, 21));
		lblLanguage.setBounds(203, 41, 117, 37);
		add(lblLanguage);
		
		lblNewTitle = new JLabel(b.getTitle());
		lblNewTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewTitle.setBounds(69, 51, 122, 23);
		add(lblNewTitle);
		
		lblNewLanguage = new JLabel(b.getLanguage());
		lblNewLanguage.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLanguage.setBounds(317, 51, 109, 23);
		add(lblNewLanguage);
		
		lblAutor = new JLabel("Autor:");
		lblAutor.setFont(new Font("VAGRounded BT", Font.BOLD, 21));
		lblAutor.setBounds(424, 52, 70, 22);
		add(lblAutor);
		
		lblNewAutor = new JLabel(b.getAuthor());
		lblNewAutor.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewAutor.setBounds(495, 51, 104, 31);
		add(lblNewAutor);
		
		lblSummary = new JLabel("Summary:");
		lblSummary.setFont(new Font("VAGRounded BT", Font.BOLD, 21));
		lblSummary.setBounds(12, 80, 122, 37);
		add(lblSummary);
		
	/*	lblNewSummary = new JLabel(b.getSummary());
		lblNewSummary.setBounds(157, 81, 56, 16);
		add(lblNewSummary);*/
		
		lblTitle = new JLabel("Title:");
		lblTitle.setFont(new Font("VAGRounded BT", Font.BOLD, 21));
		lblTitle.setBounds(12, 48, 56, 23);
		add(lblTitle);
	//	add(textAreaSummary);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(124, 83, 289, 116);
		add(scrollPane);
		
		textAreaSummary = new JTextArea();
		textAreaSummary.setFont(new Font("Monospaced", Font.PLAIN, 17));
		textAreaSummary.setLineWrap(true);
		textAreaSummary.setWrapStyleWord(true);
		scrollPane.setViewportView(textAreaSummary);
		textAreaSummary.setRows(4);
		textAreaSummary.setBackground(new Color(153, 204, 255));
		textAreaSummary.setForeground(new Color(0, 0, 0));
		textAreaSummary.setText(b.getSummary());  
		
	
			
	}
}
