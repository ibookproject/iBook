
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
import Book.Domain;
import Controller.UserController;
import Controller.bookController;
import MenuGUI.LoginGUI;
import Role.User;
import Role.UserStatus;

import java.awt.Font;
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
	// private java.util.Date date;

	public BookStatisticsPanel(LoginGUI screen, Book b) {
		setBackground(Color.WHITE);
		setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(46, 139, 87)));
		setPreferredSize(new Dimension(577, 121));
		setLayout(null);
		JButton btnGetStatistics = new JButton("Get Statistics");

		btnGetStatistics.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
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

	}
}
// how to print the date right now:
// String dateRightNow = new
// SimpleDateFormat("yyyy/MM/dd").format(cal.getTime());

