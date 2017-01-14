package ManagmentGUI;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.MatteBorder;

import Book.Book;
import Book.Cart;

import java.util.InputMismatchException;

import Controller.CartController;
import Controller.UserController;
import Controller.bookController;
import MemberGUI.SearchBook;
import MenuGUI.LoginGUI;
import Panels.BookPerCart;
import Panels.ReviewPanel;
import Role.User;
import client.DBSQLhandler;
import client.DBgenericObject;

public class StatisticsUserReportGUI extends JPanel
{
	private static final long serialVersionUID = 1L;
	public JButton btnBack ;
	private LoginGUI screen;
	private JPanel pann;
	private ImageIcon backIcon;
	private JLabel lblDate;
	private JLabel userReportLbl;
	private JButton btnGetReports;
	private JLabel lblUserID;
	//private JTextArea txtReport;
	private JTextField textFieldID;
	private JTextField textFieldDate;
	private JScrollPane scrollPaneMain;
	private JPanel panel;
	private ArrayList<BookPerCart> booksPerCarts;
	
	public StatisticsUserReportGUI(LoginGUI screen) 
	{
		super();
		
		this.screen=screen;
		pann=this;
		
		initialize();
	}

	private void initialize() 
	{
		this.setSize(850, 625);
		this.setLayout(null);	
		
		userReportLbl = new JLabel("Statistics User Report");
		userReportLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		userReportLbl.setBounds(355, 49, 175, 22);
		add(userReportLbl);
		
		backIcon =new ImageIcon("src/images/backIcon.png");
		btnBack = new JButton(backIcon);// declaration of back button
		btnBack.setBounds(39, 52, 89, 23);
		add(btnBack);
		

		textFieldID = new JTextField();
		textFieldID.setBounds(320, 129, 116, 22);
		add(textFieldID);
		textFieldID.setColumns(10);

		
		lblUserID = new JLabel("User ID");
		lblUserID.setBounds(238, 133, 70, 14);
		add(lblUserID);
				
		lblDate = new JLabel("Date");
		lblDate.setBounds(238, 180, 48, 22);
		add(lblDate);
		
		textFieldDate = new JTextField();
		textFieldDate.setBounds(320, 180, 116, 21);
		add(textFieldDate);
		textFieldDate.setColumns(10);
		
		scrollPaneMain = new JScrollPane();
		scrollPaneMain.setBounds(55, 250, 731, 230);
		scrollPaneMain.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneMain.setAutoscrolls(true);
		add(scrollPaneMain);
				
		panel = new JPanel();
		panel.setIgnoreRepaint(true);
		panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		panel.setAutoscrolls(true);
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		btnGetReports = new JButton("Get report");
		btnGetReports.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				Cart t=new Cart();
				ArrayList<Cart> carts= (ArrayList<Cart>)CartController.SearchCart("userID,bookID,price,buyDate",t,"userID=\""+textFieldID.getText()+"\""/*&&status=\""+true+"\""*/,screen.getClient());
				if(carts==null||carts.isEmpty())
				{

					scrollPaneMain.setViewportView(panel);
					panel.removeAll();
					JOptionPane.showMessageDialog(screen,"There's nothing to show!\n", "Warning",JOptionPane.WARNING_MESSAGE);
				}
				else
				{
			/*	User u=new User();
				ArrayList<User> user= (ArrayList<User>)UserController.SearchUser("userID,password,firstName,lastName",u,"userID=\""+textFieldID.getText()+"\"" ,screen.getClient());
				if(user==null||user.isEmpty())
				{
					screen.setContentPane(pann);
					JOptionPane.showMessageDialog(screen,"User was not Found\n", "Warning",JOptionPane.WARNING_MESSAGE);
				}
			
				else
				{*/
					//JOptionPane.showMessageDialog(screen,"User Found\n", "Warning",JOptionPane.WARNING_MESSAGE);
					
						showBooks(carts);
						JOptionPane.showMessageDialog(screen,"Theres books to this user\n", "Warning",JOptionPane.WARNING_MESSAGE);
				}				
			}		
		});
		btnGetReports.setBounds(598, 129, 107, 23);
		add(btnGetReports);
	}
	public void showBooks(ArrayList<Cart> carts)
	{
		scrollPaneMain.setViewportView(panel);
		panel.removeAll();
		for(int i=0;i<carts.size();i++)
		{
			ArrayList<Book> books=new ArrayList<Book>();
			Book b=new Book();
			ArrayList<Book> booksSearch= (ArrayList<Book>)bookController.SearchBook("bookID,title,author",b,"bookID=\""+carts.get(i).getBookID()+"\"" ,screen.getClient());
			if(booksSearch==null||booksSearch.isEmpty())
			{
				JOptionPane.showMessageDialog(screen,"Book was NOT Found\n", "Warning",JOptionPane.WARNING_MESSAGE);
			} 
			else
			{
				books.add(booksSearch.get(0));
				panel.add(new BookPerCart(screen, booksSearch.get(0),carts.get(i)));
			}
		}
	}
}
