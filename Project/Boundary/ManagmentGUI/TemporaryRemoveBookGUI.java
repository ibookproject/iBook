package ManagmentGUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import Book.Book;
import Book.Review;
import Controller.BookController;
import MenuGUI.LoginGUI;
import javax.swing.SwingConstants;

/**
 * Temporary Remove Book GUI - this class is take care of hiding temporary the book  or set him available,there is a search of the book first
 * and than if the status of the book is now 1-the button 'hide' is presented to the user and if the status of the book is now 0-
 * the 'show' button presented to the user.
 * @author Sagi Entenberg
 * @author Almog Yamin
 */

public class TemporaryRemoveBookGUI extends JPanel {
	private JTextField textFieldAuthor;
	private JTextField textFieldBookTitle;
	private int bookId;
	private ArrayList<Book> tempBooks;
	private LoginGUI screen;
	private Review r;
	private JPanel pann;
	private ArrayList<Review> temp;
	private String titleBook;
	private int index;
	private int flag;
	private JButton btnShow;
	private JButton btnHide;
	private JButton btnSearch;
	private JComboBox<Book> comboBoxOfBooks;
	private Book b = new Book();

	private static final long serialVersionUID = 1L;
	public JButton btnBack;

	/**
	 * Constructor
	 * @param screen This is the main window-login extends JFrame
	 * 
	 */
	public TemporaryRemoveBookGUI(LoginGUI screen) {
		super();
		bookId = -1;
		flag = 0;
		pann = this;
		this.screen = screen;
		initialize();
	}

/**
 * @author  Sagi Entenberg
 * @param no parameters
 * @return void
 * This method initialize The window of TemporaryRemoveBookGUI-put the components on the screen and set their functionality	
 */
	
	private void initialize() {

		this.setSize(850, 625);
		this.setLayout(null);

		JLabel lblHeader = new JLabel("Temporary Remove Book");
		lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeader.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 21));
		lblHeader.setBounds(325, 40, 279, 40);
		add(lblHeader);

		JLabel lblNameOfAuthor = new JLabel("name of author:");
		lblNameOfAuthor.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNameOfAuthor.setBounds(39, 206, 148, 57);
		add(lblNameOfAuthor);

		textFieldAuthor = new JTextField();
		textFieldAuthor.setBounds(180, 217, 164, 39);
		add(textFieldAuthor);
		textFieldAuthor.setColumns(10);

		JLabel lblNameOfBook = new JLabel("name of book:");
		lblNameOfBook.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNameOfBook.setBounds(39, 145, 164, 57);
		add(lblNameOfBook);

		textFieldBookTitle = new JTextField();
		textFieldBookTitle.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldBookTitle.setBounds(180, 155, 164, 39);
		add(textFieldBookTitle);
		textFieldBookTitle.setColumns(10);

		comboBoxOfBooks = new JComboBox();
		comboBoxOfBooks.setFont(new Font("Tahoma", Font.PLAIN, 16));

		comboBoxOfBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBoxOfBooks.getItemCount()!=0){
				comboBoxOfBooks.updateUI();
				if (comboBoxOfBooks.getItemAt(comboBoxOfBooks.getSelectedIndex()).isBookEnable() == 0) {
					btnShow.setEnabled(true);
					btnHide.setEnabled(false);
				} else {
					btnShow.setEnabled(false);
					btnHide.setEnabled(true);
				}
				}
			}
		});

		comboBoxOfBooks.setBounds(387, 125, 441, 39);
		add(comboBoxOfBooks);

		
		/**
		 * This button is the 'hide' button- when the user press,the status of the book is 
		 * set to 0 which say that the book is temporary not availabe
		 * @author Sagi Entenberg
		 * 
		 */	
		
		btnHide = new JButton("Hide");
		btnHide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookController.UpdateBook(b, "bookEnable=\"" + 0 + "\"",
						"bookID=\"" + comboBoxOfBooks.getItemAt(comboBoxOfBooks.getSelectedIndex()).getBookID() + "\"",
						screen.getClient());
				comboBoxOfBooks.getItemAt(comboBoxOfBooks.getSelectedIndex()).setBookEnable(0);
				comboBoxOfBooks.updateUI();
				btnHide.setEnabled(false);
				btnShow.setEnabled(true);

			}
		});
		btnHide.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnHide.setBounds(242, 505, 127, 49);
		add(btnHide);

		/**
		 * This button is the 'show' button- when the user press,the status of the book is 
		 * set to 1 which say that the book is now availabe
		 * @author  Almog Yamin
		 * 
		 */	
		
		btnShow = new JButton("Show");
		btnShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookController.UpdateBook(b, "bookEnable=\"" + 1 + "\"",
						"bookID=\"" + comboBoxOfBooks.getItemAt(comboBoxOfBooks.getSelectedIndex()).getBookID() + "\"",
						screen.getClient());
				comboBoxOfBooks.getItemAt(comboBoxOfBooks.getSelectedIndex()).setBookEnable(1);
				comboBoxOfBooks.updateUI();
				btnShow.setEnabled(false);
				btnHide.setEnabled(true);
			}
		});
		btnShow.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnShow.setBounds(515, 505, 127, 49);
		add(btnShow);

		/**
		 * This button is the 'search' button- when the user press, this is the search of the book
		 * @author Sagi Entenberg
		 * 
		 */	
		btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//////////////////////// button to back panel from panel
				//////////////////////// /////////////////////////////////////////////
				StatisticsBookReport sbr = new StatisticsBookReport(screen);
				sbr.btnBack.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						screen.setContentPane(pann);
					}
					//////////////////////// button to back panel from
					//////////////////////// panel/////////////////////////////////////////////
				});		
				comboBoxOfBooks.removeAllItems();
				if ((textFieldBookTitle.getText().isEmpty()) && (textFieldAuthor.getText().isEmpty()))
					JOptionPane.showMessageDialog(screen, "you must fill one field at least ", "Warning",
							JOptionPane.WARNING_MESSAGE);
				else {
					if (textFieldBookTitle.getText().isEmpty())
						tempBooks = BookController.SearchBook("title,author,bookID,bookEnable", b,
								"author LIKE '%" + textFieldAuthor.getText().trim() + "%'", screen.getClient());
					else if (textFieldAuthor.getText().isEmpty())
						tempBooks = BookController.SearchBook("title,author,bookID,bookEnable", b,
								"title LIKE '%" + textFieldBookTitle.getText().trim() + "%'", screen.getClient());
					else {
						tempBooks = BookController.SearchBook("title,author,bookID,bookEnable",
								b, "title LIKE '%" + textFieldBookTitle.getText().trim() + "%'" + " && "
										+ "author LIKE '%" + textFieldAuthor.getText().trim() + "%'",
								screen.getClient());
					}
					
					if (tempBooks != null) {
						int i = 0;
						
						for (Book bt : tempBooks) {
							comboBoxOfBooks.addItem(bt);
							i++;
						}
					} else
						JOptionPane.showMessageDialog(screen, "The book is not found!", "Warning",
								JOptionPane.WARNING_MESSAGE);
					textFieldBookTitle.setText("");
					textFieldAuthor.setText("");
				}
			}
			
		});
		btnSearch.setBounds(125, 288, 120, 30);
		add(btnSearch);

		ImageIcon backIcon = new ImageIcon("Extras/Images/backIcon.png");
		btnBack = new JButton(backIcon);
		btnBack.setBounds(35, 25, 89, 30);
		add(btnBack);

		JButton btnNewButton = new JButton("");
		btnNewButton.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 191, 255)));
		btnNewButton.setEnabled(false);
		btnNewButton.setBounds(12, 125, 351, 219);
		add(btnNewButton);

	}
}