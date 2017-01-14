
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

import Book.Book;
import Book.Cart;
import Book.Domain;
import Controller.CartController;
import Controller.UserController;
import Controller.bookController;
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
	private ArrayList<Cart> searcRes;
	// private java.util.Date date;

	public BookStatisticsPanel(LoginGUI screen, Book b , JPanel pann) {
		setBackground(Color.WHITE);
		setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(46, 139, 87)));
		setPreferredSize(new Dimension(577, 121));
		setLayout(null);
		JButton btnGetStatistics = new JButton("Get Statistics");
		btnGetStatistics.setBounds(405, 87, 119, 23);
		add(btnGetStatistics);

		JLabel lblBookid = new JLabel("Book ID:");
		lblBookid.setBounds(20, 21, 147, 14);
		add(lblBookid);

		JLabel lblBookiddb = new JLabel(Integer.toString(b.getBookID()));
		lblBookiddb.setBounds(177, 21, 141, 14);
		add(lblBookiddb);

		JLabel lblBookTitle = new JLabel("Book Title:");
		lblBookTitle.setBounds(20, 51, 147, 14);
		add(lblBookTitle);

		JLabel lblBookTitledb = new JLabel(b.getTitle());
		lblBookTitledb.setBounds(177, 51, 141, 14);
		add(lblBookTitledb);

		JLabel lblAuthor = new JLabel("Author Name:");
		lblAuthor.setBounds(20, 81, 147, 14);
		add(lblAuthor);

		JLabel lblAuthordb = new JLabel(b.getAuthor());
		lblAuthordb.setBounds(177, 81, 141, 14);
		add(lblAuthordb);

		JLabel lblFromDate = new JLabel("From date:");
		lblFromDate.setBounds(360, 21, 87, 14);
		add(lblFromDate);

		txtFromDate = new JTextField();
		txtFromDate.setBounds(481, 18, 86, 20);
		add(txtFromDate);
		txtFromDate.setColumns(10);

		JLabel lblToDate = new JLabel("to date:");
		lblToDate.setBounds(360, 51, 87, 14);
		add(lblToDate);

		txtToDate = new JTextField();
		txtToDate.setBounds(481, 48, 86, 20);
		add(txtToDate);
		txtToDate.setColumns(10);
		
		btnGetStatistics.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				////////////////////////button to back panel from panel /////////////////////////////////////////////
				StatisticsBookReport sbr=new StatisticsBookReport(screen);
				sbr.btnBack.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							screen.setContentPane(pann);
						}});
				screen.setContentPane(sbr);//send to search book window
					//SELECT * FROM Orders
				//WHERE OrderDate BETWEEN #07/04/1996# AND #07/09/1996#;		
				Cart c = new Cart();
				searcRes = CartController.SearchCart("userID", c, "buyDate between '2001/01/01' AND '2017/05/05' ",screen.getClient());//call search book method from book controller
				int count=0;
				for(Cart ct:searcRes)
					count++;
				sbr.setResult(count, 0);
			}
			});

	}
}
// how to print the date right now:
// String dateRightNow = new
// SimpleDateFormat("yyyy/MM/dd").format(cal.getTime());

