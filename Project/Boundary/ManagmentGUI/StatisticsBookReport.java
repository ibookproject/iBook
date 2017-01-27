package ManagmentGUI;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.MatteBorder;

import Book.Book;
import Book.Domain;
import Controller.UserController;
import Extras.HistogramPanel;
import Controller.BookController;
import MenuGUI.LoginGUI;
import Panels.BookPanel;
import Panels.BookStatisticsPanel;
import Panels.UserSubscriptionPanel;
import Role.User;
import client.DBgenericObject;

import javax.swing.JTextArea;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
/**
 * The class shows the HISTOGRAMA panel for a specific book which get from the first panel of the statistics books.
 * @author  Almog Yamin 
 */
public class StatisticsBookReport extends JPanel {

	private static final long serialVersionUID = 1L;
	public JButton btnBack;
	private LoginGUI screen;
	private JPanel pann;
	private ArrayList<Book> tempBooks;
	private JLabel lblOrdersdb;
	private JLabel lblSearchersdb;
	private JProgressBar progressBar;
	private JPanel insidePanel;
	private JLabel lblResult;

	/**
	 * Constructor of the StatisticsBookReport class
	 * @param screen This is the main window-login extends JFrame
	 * @author  Almog Yamin
	 */	
	public StatisticsBookReport(LoginGUI screen) {
		super();
		this.screen = screen;
		pann = this;

		initialize();
	}
	/**
	 * This method initialize The statistics book report-put the components on the screen and set their functionality
	 * @author Sagi Entenberg
	 */
	private void initialize() {

		this.setSize(850, 625);
		this.setLayout(null);

		JLabel lblHeader = new JLabel("Statistics Book Report");
		lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeader.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 21));
		lblHeader.setBounds(325, 49, 240, 22);
		add(lblHeader);

		ImageIcon backIcon = new ImageIcon("Extras/Images/backIcon.png");
		btnBack = new JButton(backIcon);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnBack.setBounds(35, 25, 89, 30);
		add(btnBack);

		insidePanel = new JPanel();
		insidePanel.setBounds(241, 156, 430, 427);
		add(insidePanel);
		
		lblResult = new JLabel("");
		lblResult.setHorizontalAlignment(SwingConstants.CENTER);
		lblResult.setBounds(166, 96, 571, 33);
		add(lblResult);
		lblResult.setFont(new Font("Tahoma", Font.BOLD, 16));

	}
	/**
	 * setResult of the StatisticsBookReport class
	 * * this method build an HISTOGRAMA panel that shows the statistics book of chosen book (from other panel) by his searches and purchases
	 * @param purchase
	 * get from other panel the number of purchases of the book by dates
	 * @param searches
	 * get from other panel the number of searches of the book by dates
	 * @param bookid
	 * get from other panel the book id of the book statistics shown
	 * @author Almog Yamin
	 */
	public void setResult(int purchase, int searches , int bookid) {
		if (purchase != 0 || searches != 0) {
			HistogramPanel histogramPan = new HistogramPanel();
			histogramPan.addHistogramColumn("Purchase", purchase, Color.RED);
			histogramPan.addHistogramColumn("Searches", searches, Color.YELLOW);
			histogramPan.layoutHistogram();
			insidePanel.add(histogramPan);
			insidePanel.updateUI();
			Book b=new Book();
			ArrayList<Book> ab = new ArrayList<Book>();
			ab = BookController.SearchBook("title,author", b, "bookID =\"" + bookid + "\"" , screen.getClient());
			lblResult.setText("The Book: " + ab.get(0).getTitle() + "                BY: "+ab.get(0).getAuthor());
		}
		else
		{
			lblResult.setText("There is no purchase or searches to this book :(");
			lblResult.setForeground(Color.RED);
		}
	}
		
}
