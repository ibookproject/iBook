package MemberGUI;

import javax.swing.*;
import javax.swing.border.MatteBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import Book.Book;
import Controller.bookController;
import MenuGUI.LoginGUI;
import Panels.BookPanel;
import client.DBgenericObject;
import command.showAllCommand;




public class SearchBook extends JPanel {


	private static final long serialVersionUID = 1L;
	public JButton btnBack ;
	private LoginGUI screen;
	private JPanel pann;
	private ImageIcon backIcon;
	private JButton btnPostReview;
	private JLabel lblSearchBook;
	private ArrayList<Book> searchRes;
	private ArrayList<Book> books;
	public static JPanel panel;
	private JScrollPane scrollPaneMain;

	public SearchBook(LoginGUI screen,ArrayList<Book> books) {
		super();
		setForeground(Color.LIGHT_GRAY);
		this.screen=screen;
		pann=this;
		this.books=books;
		initialize();
	}
	/**
	 * 
	 *@author CoralCarmeli
	 *@return nothing
	 *@param nothing 
	 *initialize the SearchBook
	 **/
	private void initialize()
	{
		this.setSize(850, 625);
		this.setLayout(null);	
		
		backIcon =new ImageIcon("src/images/backIcon.png");
		btnPostReview = new JButton("Post Review");
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
	
		btnPostReview.setBounds(684, 11, 123, 25);
		add(btnPostReview);
		btnBack = new JButton(backIcon);// declaration of back button
		btnBack.setBounds(39, 52, 89, 23);
		add(btnBack);
		
		lblSearchBook = new JLabel("Search Book");
		lblSearchBook.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSearchBook.setBounds(355, 49, 175, 22);
		add(lblSearchBook);
		
		scrollPaneMain = new JScrollPane();
		scrollPaneMain.setBounds(51, 106, 756, 492);
		scrollPaneMain.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneMain.setAutoscrolls(true);
		add(scrollPaneMain);
		
		panel = new JPanel();
		panel.setIgnoreRepaint(true);
		panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		panel.setAutoscrolls(true);
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		scrollPaneMain.setViewportView(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
	}

	public void setList(ArrayList<Book> list)
	{	
		this.searchRes=list;
		if(searchRes!=null)
			
		for(Book b:searchRes)
			panel.add(new BookPanel(this.screen,b));
	}
}
