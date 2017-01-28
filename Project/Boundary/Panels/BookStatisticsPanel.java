
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
import Extras.DatePicker;
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
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

/**
 * 
 * @author AlmoGSB
 *
 */
public class BookStatisticsPanel extends JPanel {
	private ArrayList<Book> Books;
	private LoginGUI screen;
	private JTextField txtFromDate;
	private JTextField txtToDate;
	private ArrayList<Cart> cartRes;
	private ArrayList<SearchToBook> searcRes;
	private Date from;
	private Date to;
	// private java.util.Date date;


	public BookStatisticsPanel(LoginGUI screen, Book b, JPanel pann) {
		setBackground(Color.WHITE);
		setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(46, 139, 87)));
		setPreferredSize(new Dimension(604, 175));
		setLayout(null);
		JButton btnGetStatistics = new JButton("Get Statistics");
		btnGetStatistics.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnGetStatistics.setBounds(376, 122, 176, 29);
		add(btnGetStatistics);

		JLabel lblBookid = new JLabel("Book ID:");
		lblBookid.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblBookid.setBounds(20, 31, 147, 14);
		add(lblBookid);

		JLabel lblBookiddb = new JLabel(Integer.toString(b.getBookID()));
		lblBookiddb.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblBookiddb.setBounds(134, 23, 141, 30);
		add(lblBookiddb);

		JLabel lblBookTitle = new JLabel("Book Title:");
		lblBookTitle.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblBookTitle.setBounds(20, 80, 147, 14);
		add(lblBookTitle);

		JLabel lblBookTitledb = new JLabel(b.getTitle());
		lblBookTitledb.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblBookTitledb.setBounds(134, 72, 141, 30);
		add(lblBookTitledb);

		JLabel lblAuthor = new JLabel("Author Name:");
		lblAuthor.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAuthor.setBounds(20, 129, 147, 14);
		add(lblAuthor);

		JLabel lblAuthordb = new JLabel(b.getAuthor());
		lblAuthordb.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAuthordb.setBounds(134, 122, 141, 30);
		add(lblAuthordb);

		JLabel lblFromDate = new JLabel("From date:");
		lblFromDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblFromDate.setBounds(269, 34, 87, 14);
		add(lblFromDate);

		txtFromDate = new JTextField();
		txtFromDate.setEditable(false);
		txtFromDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtFromDate.setBounds(355, 25, 97, 30);
		add(txtFromDate);
		txtFromDate.setColumns(10);

		JLabel lblToDate = new JLabel("To date:");
		lblToDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblToDate.setBounds(269, 71, 87, 14);
		add(lblToDate);

		txtToDate = new JTextField();
		txtToDate.setEditable(false);
		txtToDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtToDate.setBounds(355, 63, 97, 31);
		add(txtToDate);
		txtToDate.setColumns(10);

		// create button and there object
		JButton btnChooseFromDate = new JButton("Choose Date");
		btnChooseFromDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		// perform action listener
		btnChooseFromDate.addActionListener(new ActionListener() {
			// performed action
			public void actionPerformed(ActionEvent arg0) {
				// create frame new object f
				JFrame f = new JFrame();
				f.setLocation(400, 400);
				f.setBounds(200, 200, 200, 200);
				// set text which is collected by date picker i.e. set date
				String dfrom = new DatePicker(f).setPickedDate();
				txtFromDate.setText(dfrom);
				DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
				try {
					from = (Date) format.parse(dfrom);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		// set button bound
		btnChooseFromDate.setBounds(451, 25, 126, 30);
		// add button in contentPane
		add(btnChooseFromDate);

		JButton btnChooseToDate = new JButton("Choose Date");
		btnChooseToDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnChooseToDate.addActionListener(new ActionListener() {
			// performed action
			public void actionPerformed(ActionEvent arg0) {
				// create frame new object f
				JFrame f = new JFrame();
				f.setLocation(400, 400);
				f.setBounds(200, 200, 200, 200);
				// set text which is collected by date picker i.e. set date
				String dfrom = new DatePicker(f).setPickedDate();
				txtToDate.setText(dfrom);
				DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
				try {
					to = (Date) format.parse(dfrom);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnChooseToDate.setBounds(451, 63, 126, 31);
		add(btnChooseToDate);
		btnGetStatistics.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//////////////////////// button to back panel from panel
				//////////////////////// /////////////////////////////////////////////
				StatisticsBookReport sbr = new StatisticsBookReport(screen);
				sbr.btnBack.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						screen.setContentPane(pann);
					}
				});

				if ((!txtToDate.getText().equals("")) && (!txtFromDate.getText().equals(""))) {
					if (from.before(to)) {
						screen.setContentPane(sbr);// send to search book window
						int NumberOfSearches = 0;
						int NumberOfCarts = 0;
						int bookid = 0;
						Cart c = new Cart();
						SearchToBook stb = new SearchToBook();
						cartRes = CartController.SearchCart("bookID", c,
								"buyDate between '" + txtFromDate.getText() + "' AND '" + txtToDate.getText()
										+ "' && bookID=\"" + b.getBookID() + "\" && status=\"" + 1 + "\"",
								screen.getClient());// call search book method
													// from
													// book controller
						searcRes = BookController.SearchSearchToBook(
								"bookID,numberOfSearches", stb, "SearchDate between '" + txtFromDate.getText()
										+ "' AND '" + txtToDate.getText() + "' && bookID=\"" + b.getBookID() + "\"",
								screen.getClient());// call search book method
													// from
													// book controller
						if (cartRes != null) {
							NumberOfCarts = cartRes.size();
							bookid = cartRes.get(0).getBookID();
						}
						if (searcRes != null) {
							for (SearchToBook stb2 : searcRes)
								NumberOfSearches += stb2.getNumberOfSearches();
							bookid = searcRes.get(0).getBookID();
						}
						sbr.setResult(NumberOfCarts, NumberOfSearches, bookid);

					} else
						JOptionPane.showMessageDialog(screen, "'from' date need to be before 'to' date.\nTry again.",
								"Warning", JOptionPane.WARNING_MESSAGE);

				} else
					JOptionPane.showMessageDialog(screen, "One of the dates fields are empty.\nTry again", "Warning",
							JOptionPane.WARNING_MESSAGE);
			}
		});

	}
}
// how to print the date right now:
// String dateRightNow = new
// SimpleDateFormat("yyyy/MM/dd").format(cal.getTime());
