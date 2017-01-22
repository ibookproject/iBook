package MemberGUI;

import javax.swing.*;
import javax.swing.border.MatteBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import Book.Book;
import Controller.BookController;
import Controller.UserController;
import MenuGUI.LoginGUI;
import Panels.BookPanel;
import Role.User;
import client.DBgenericObject;
import command.showAllCommand;

public class SearchBook extends JPanel {


	private static final long serialVersionUID = 1L;
	public JButton btnBack ;
	private LoginGUI screen;
	private ImageIcon backIcon;
	private JLabel lblSearchBook;
	private ArrayList<Book> searchRes;
	public static JPanel panel;
	private JScrollPane scrollPaneMain;
	private int userPrivelege=0;
	private User u;

	public SearchBook(LoginGUI screen,ArrayList<Book> books) {
		super();
		setForeground(Color.LIGHT_GRAY);
		this.screen=screen;
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
		btnBack = new JButton(backIcon);// declaration of back button
		btnBack.setBounds(39, 52, 89, 23);
		add(btnBack);
		
		lblSearchBook = new JLabel("Search Book");
		lblSearchBook.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSearchBook.setBounds(355, 49, 175, 22);
		add(lblSearchBook);
		
		scrollPaneMain = new JScrollPane();
		scrollPaneMain.getVerticalScrollBar().setUnitIncrement(16);
		scrollPaneMain.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneMain.setAutoscrolls(true);
		scrollPaneMain.setBounds(51, 106, 756, 451);
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
		
		u=new User();
		ArrayList<User> user= (ArrayList<User>) UserController.SearchUser("userID,privilege",u,"userID=\""+screen.getTempID()+"\"",screen.getClient());
		if((user!=null)&&(user.isEmpty()==false))
			userPrivelege=user.get(0).getPriviliege();
		
		for(Book b:searchRes)
		{
			panel.add(new BookPanel(this.screen,b,this,userPrivelege));
			panel.updateUI();
		}
	}
}
