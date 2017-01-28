package Panels;

import javax.swing.JPanel;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.MatteBorder;

import java.awt.Color;

import Book.Book;
import Book.Cart;
import Controller.CartController;
import MemberGUI.RequestPostFillReviewGUI;
import MemberGUI.SearchBook;
import MenuGUI.LoginGUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.SystemColor;
import javax.swing.UIManager;
/**
 * @author Coral Carmeli
 * This class presents the panel of the book which shown in the Search book method			
 */

public class BookPanel extends JPanel{
	
	private LoginGUI screen;
	private JLabel lblTitleDB;
	private JLabel lblLanguageDB;
	private JLabel lblLanguage;
	private JButton btnPostReview;
	private JLabel lblAuthor;
	private JLabel lblAuthorDB;
	private JLabel lblSummary;
	private SearchBook pann;
	private JLabel lblTitle;
	private JButton btnAddToCart;
	private JTextArea textAreaSummary;
	private ArrayList<Cart> carts;

	
	/**
	 * This is the constructor of the class BookPanel-put the components on the screen and set their functionality
	 * @param screen This is the main window-login
	 * @param b is the Book which the panel shown his details
	 * @param pan is the previous window of the search
	 * @param User is the permission of the user-if above or equal 2 its means the user is a reader and can see the buttons-'Post review' 
	 * and 'add to cart'
	 * @author  Coral Carmeli
	 */
	public BookPanel(LoginGUI screen,Book b,SearchBook pan,int User) {
		pann=pan;
		setBackground(Color.WHITE);
		setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(51, 102, 204)));
		setPreferredSize(new Dimension(731, 214));
		setLayout(null);
		/**
		 * This button is the Post Review button- when the user press on him he goes to a window there 
		 * he can ass a new review for the specific book
		 * @author  Coral Carmeli
		 * 
		 */	
		if(User>=2)
		{
		btnPostReview = new JButton("Post Review");
		btnPostReview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
////////////////////////button to back panel from panel /////////////////////////////////////////////
				RequestPostFillReviewGUI Pfr=new RequestPostFillReviewGUI(screen,b.getBookID(),pann);
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
		
		
		
		/**
		 * This Button 'Add to cart' insert a new record to the table 'Cart' according user ID and requested Book	
		 * @author Coral Carmeli	
		 */
		
		btnAddToCart = new JButton("Add to Cart");
		btnAddToCart.setBounds(601, 156, 117, 25);
		
		
		Calendar time = Calendar.getInstance();
        String timeRightNow = String.format("%1$tY/%1$tm/%1$td", time);
		Cart c=new Cart();
		carts=new ArrayList<Cart>();
		carts=CartController.SearchCart("userID,bookID,buyDate", c, "userID=\""+screen.getTempID()+"\" && bookID=\""+b.getBookID()+"\" && buyDate=\""+timeRightNow+"\"", screen.getClient());
		if(carts==null||carts.isEmpty())
			add(btnAddToCart);
		
		btnAddToCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

					if((CartController.AddToCart(new Cart(screen.getTempID(),b.getBookID(),0,timeRightNow), screen.getClient()))==true)
					{
						JOptionPane.showMessageDialog(screen,"Add new Record to The Cart Done! ", "",JOptionPane.INFORMATION_MESSAGE);
						remove(btnAddToCart);
					}
					else
						JOptionPane.showMessageDialog(screen,"The insert of a new record to the Cart was failed!", "Warning",JOptionPane.WARNING_MESSAGE);
				
					pann.updateUI();
			}
		});
		}
		lblLanguage = new JLabel("Language:");
		lblLanguage.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblLanguage.setBounds(10, 45, 117, 30);
		add(lblLanguage);
		
		lblTitleDB = new JLabel(b.getTitle());
		lblTitleDB.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTitleDB.setBounds(125, 15, 130, 30);
		add(lblTitleDB);
		
		lblLanguageDB = new JLabel(b.getLanguage());
		lblLanguageDB.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblLanguageDB.setBounds(125, 45, 130, 30);
		add(lblLanguageDB);
		
		lblAuthor = new JLabel("Author:");
		lblAuthor.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAuthor.setBounds(10, 75, 77, 30);
		add(lblAuthor);
		
		lblAuthorDB = new JLabel(b.getAuthor());
		lblAuthorDB.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAuthorDB.setBounds(125, 75, 130, 31);
		add(lblAuthorDB);
		
		lblSummary = new JLabel("Summary:");
		lblSummary.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSummary.setBounds(10, 105, 122, 30);
		add(lblSummary);
		
		lblTitle = new JLabel("Title:");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTitle.setBounds(10, 15, 56, 30);
		add(lblTitle);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setAutoscrolls(true);
		scrollPane.setBounds(125, 110, 289, 90);
		add(scrollPane);
		
		textAreaSummary = new JTextArea();
		textAreaSummary.setEditable(false);
		textAreaSummary.setFont(new Font("Monospaced", Font.PLAIN, 16));
		textAreaSummary.setLineWrap(true);
		textAreaSummary.setWrapStyleWord(true);
		scrollPane.setViewportView(textAreaSummary);
		textAreaSummary.setRows(4);
		textAreaSummary.setBackground(new Color(0, 250, 154));
		textAreaSummary.setForeground(new Color(0, 0, 0));
		textAreaSummary.setText(b.getSummary());  
	}
}
