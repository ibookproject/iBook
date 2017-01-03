package MemberGUI;

import java.awt.Font;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import Book.book;
import MenuGUI.LoginGUI;
import client.DBgenericObject;
import command.showAllCommand;




public class SearchBook extends JPanel {


	private static final long serialVersionUID = 1L;
	public JButton btnBack ;
	private LoginGUI screen;
	private JPanel pann;
	private ArrayList<book> searchRes;
	private JTextArea textArea;
	
	public SearchBook(LoginGUI screen) {
		super();
		setForeground(Color.LIGHT_GRAY);
		this.screen=screen;
		pann=this;
		initialize();
	}

	private void initialize()
	{
		this.setSize(850, 625);
		this.setLayout(null);	
		
		ImageIcon backIcon =new ImageIcon("src/images/backIcon.png");
		JButton btnPostReview = new JButton("Post Review");
		btnPostReview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
////////////////////////button to back panel from panel /////////////////////////////////////////////
				RequestPostFillReviewGUI Pfr=new RequestPostFillReviewGUI(screen,"ID1234");
				Pfr.btnBack.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						screen.setContentPane(pann);
					}
////////////////////////button to back panel from panel/////////////////////////////////////////////
				});
				screen.setContentPane(Pfr);//
			}
		});
	
		btnPostReview.setBounds(516, 231, 123, 25);
		add(btnPostReview);
		btnBack = new JButton(backIcon);// declaration of back button
		btnBack.setBounds(39, 52, 89, 23);
		add(btnBack);
		
		JLabel lblSearchBook = new JLabel("Search Book");
		lblSearchBook.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSearchBook.setBounds(355, 49, 175, 22);
		add(lblSearchBook);
		
	    textArea = new JTextArea();
		textArea.setBackground(Color.LIGHT_GRAY);
		textArea.setBounds(179, 121, 488, 456);
		add(textArea);
		
		

		
	
		
	}
	public void setList(ArrayList<book> list)
	{
		this.searchRes=list;
		textArea.setText(searchRes.toString());
	}
}
