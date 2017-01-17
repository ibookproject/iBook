
package Panels;

import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.MatteBorder;

import java.awt.Color;

import javax.swing.border.LineBorder;
import javax.swing.JCheckBox;
import javax.swing.JFrame;

import Book.Book;
import Book.Cart;
import Book.Domain;
import Book.SearchToBook;
import Controller.CartController;
import Controller.UserController;
import Controller.BookController;
import ManagmentGUI.StatisticsBookReport;
import ManagmentGUI.TemporaryRemoveBookGUI;
import MenuGUI.LoginGUI;
import Role.User;
import Role.UserStatus;

import java.awt.Font;
import java.awt.Panel;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.spi.CalendarNameProvider;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class BookStatisticsPanel extends JPanel {
	private ArrayList<Book> Books;
	private LoginGUI screen;
	private JTextField txtFromDate;
	private JTextField txtToDate;
	private ArrayList<Cart> cartRes;
	private ArrayList<SearchToBook> searcRes;
	// private java.util.Date date;

	public BookStatisticsPanel(LoginGUI screen, Book b , JPanel pann) {
		setBackground(Color.WHITE);
		setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(46, 139, 87)));
		setPreferredSize(new Dimension(577, 185));
		setLayout(null);
		JButton btnGetStatistics = new JButton("Get Statistics");
		btnGetStatistics.setBounds(397, 106, 119, 23);
		add(btnGetStatistics);

		JLabel lblBookid = new JLabel("Book ID:");
		lblBookid.setBounds(20, 31, 147, 14);
		add(lblBookid);

		JLabel lblBookiddb = new JLabel(Integer.toString(b.getBookID()));
		lblBookiddb.setBounds(118, 31, 141, 14);
		add(lblBookiddb);

		JLabel lblBookTitle = new JLabel("Book Title:");
		lblBookTitle.setBounds(20, 80, 147, 14);
		add(lblBookTitle);

		JLabel lblBookTitledb = new JLabel(b.getTitle());
		lblBookTitledb.setBounds(118, 80, 141, 14);
		add(lblBookTitledb);

		JLabel lblAuthor = new JLabel("Author Name:");
		lblAuthor.setBounds(20, 129, 147, 14);
		add(lblAuthor);

		JLabel lblAuthordb = new JLabel(b.getAuthor());
		lblAuthordb.setBounds(118, 129, 141, 14);
		add(lblAuthordb);

		JLabel lblFromDate = new JLabel("From date:");
		lblFromDate.setBounds(269, 31, 87, 14);
		add(lblFromDate);

		txtFromDate = new JTextField();
		txtFromDate.setEditable(false);
		txtFromDate.setBounds(366, 28, 86, 20);
		add(txtFromDate);
		txtFromDate.setColumns(10);

		JLabel lblToDate = new JLabel("to date:");
		lblToDate.setBounds(269, 66, 87, 14);
		add(lblToDate);

		txtToDate = new JTextField();
		txtToDate.setEditable(false);
		txtToDate.setBounds(366, 63, 86, 20);
		add(txtToDate);
		txtToDate.setColumns(10);
		
		//create button and there object
		JButton btnChooseFromDate = new JButton("Choose Date");
		//perform action listener
		btnChooseFromDate.addActionListener(new ActionListener() 
		{	
			//performed action
			public void actionPerformed(ActionEvent arg0) 
			{
				//create frame new object  f
				JFrame f = new JFrame();
				f.setLocation(400, 400);
				f.setBounds(200, 200, 200, 200);
				//set text which is collected by date picker i.e. set date 
				txtFromDate.setText(new DatePicker(f).setPickedDate());

			}
		});
		//set button bound
		btnChooseFromDate.setBounds(451, 28, 102, 20);
		//add button in contentPane
		add(btnChooseFromDate);
		
		JButton btnChooseToDate = new JButton("Choose Date");
		btnChooseToDate.addActionListener(new ActionListener() 
		{	
			//performed action
			public void actionPerformed(ActionEvent arg0) 
			{
				//create frame new object  f
				JFrame f = new JFrame();
				f.setLocation(400, 400);
				f.setBounds(200, 200, 200, 200);
				//set text which is collected by date picker i.e. set date 
				txtToDate.setText(new DatePicker(f).setPickedDate());
			}
		});
		btnChooseToDate.setBounds(451, 63, 102, 20);
		add(btnChooseToDate);
		btnGetStatistics.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				////////////////////////button to back panel from panel /////////////////////////////////////////////
				StatisticsBookReport sbr=new StatisticsBookReport(screen);
				sbr.btnBack.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							screen.setContentPane(pann);
						}});
				screen.setContentPane(sbr);//send to search book window	
				int NumberOfSearches=0;
				int NumberOfCarts = 0;
				Cart c = new Cart();
				SearchToBook stb = new SearchToBook();
				cartRes = CartController.SearchCart("userID", c, "buyDate between '"+ txtFromDate.getText() +"' AND '"+txtToDate.getText()+"' && bookID=\""+ b.getBookID()+ "\" && status=\""+ 1 + "\"",screen.getClient());//call search book method from book controller
				searcRes = BookController.SearchSearchToBook("numberOfSearches", stb, "SearchDate between '"+ txtFromDate.getText() +"' AND '"+txtToDate.getText()+"' && bookID=\""+ b.getBookID()+ "\"",screen.getClient());//call search book method from book controller
				if(cartRes != null)
					NumberOfCarts = cartRes.size();
				if(searcRes != null){
					for(SearchToBook stb2: searcRes)
						NumberOfSearches += stb2.getNumberOfSearches();	
				}		 
				sbr.setResult(NumberOfCarts, NumberOfSearches);
				/* HistogramPanel panel = new HistogramPanel();
			        panel.addHistogramColumn("Kokoriko", 100, Color.RED);
			        panel.addHistogramColumn("B", 690, Color.YELLOW);
			       
				sbr.add(panel);*/
			}
			});

	}
}
// how to print the date right now:
// String dateRightNow = new
// SimpleDateFormat("yyyy/MM/dd").format(cal.getTime());

