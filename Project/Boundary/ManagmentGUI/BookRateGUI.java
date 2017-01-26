package ManagmentGUI;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Book.Book;
import Controller.BookController;
import MenuGUI.LoginGUI;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;

import javax.swing.JRadioButton;

/**
 * @author  Coral Carmeli
 * The class show the library manager the rate of the book according two options- 
 * 1.Absolute rate is against all the books in the library .
 * 2.Proportion rate is against the all books in the same domain.
 * The manager needs first to search the book he want to know details on it.
 */
public class BookRateGUI extends JPanel {

	private static final long serialVersionUID = 1L;
	public JButton btnBack ;
	private LoginGUI screen;
	private JPanel pann;
	private JTextField textFieldAutohr;
	private JTextField textFieldBook;
	private int bookId;
	private ArrayList<Book> tempBooks;
	private JPanel Mainpann;
	private JLabel lblBookRate;
	private JLabel lblNameOfAuthor;
	private JLabel lblNameOfBook;
	private JComboBox comboBoxChooseBook;
	private JRadioButton rdbtnAbsoluteRate;
	private JRadioButton rdbtnProportionRate ;
	/**
	 * Constructor of the BookRateGUI class
	 * @param screen This is the main window-login
	 * @author  Coral Carmeli
	 */	
	public BookRateGUI(LoginGUI screen) {
		super();
		this.screen=screen;
		bookId=-1;
		pann=this;
		initialize();
	}
	/**
	 * This method initialize The window of Book Rate-put the components on the screen and set their functionality
	 * @author  Coral Carmeli
	 */
	private void initialize() {

		this.setSize(850, 625);
		this.setLayout(null);	
		ImageIcon backIcon =new ImageIcon("Extras/Images/backIcon.png");
		btnBack = new JButton(backIcon);// declaration of back button
		btnBack.setBounds(39, 52, 89, 23);
		add(btnBack);
		
		
		lblBookRate = new JLabel("Book Rate");
		lblBookRate.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 21));
		lblBookRate.setBounds(361, 33, 117, 46);
		add(lblBookRate);
		
		lblNameOfAuthor = new JLabel("name of author:");
		lblNameOfAuthor.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNameOfAuthor.setBounds(415, 90, 131, 30);
		add(lblNameOfAuthor);
		
		textFieldAutohr = new JTextField();
		textFieldAutohr.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldAutohr.setBounds(550, 90, 86, 30);
		add(textFieldAutohr);
		textFieldAutohr.setColumns(10);
		
		lblNameOfBook = new JLabel("name of book:");
		lblNameOfBook.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNameOfBook.setBounds(161, 90, 117, 30);
		add(lblNameOfBook);
		
		textFieldBook = new JTextField();
		textFieldBook.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldBook.setBounds(285, 90, 86, 30);
		add(textFieldBook);
		textFieldBook.setColumns(10);
		
		
		rdbtnAbsoluteRate = new JRadioButton("Absolute Rate");
		rdbtnAbsoluteRate.setFont(new Font("Tahoma", Font.BOLD, 16));
		rdbtnAbsoluteRate.setBounds(225, 188, 146, 30);
		add(rdbtnAbsoluteRate);
		
		rdbtnProportionRate = new JRadioButton("Proportion Rate");
		rdbtnProportionRate.setFont(new Font("Tahoma", Font.BOLD, 16));
		rdbtnProportionRate.setBounds(468, 193, 157, 25);
		add(rdbtnProportionRate);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnAbsoluteRate);
		group.add(rdbtnProportionRate);
		
		comboBoxChooseBook = new JComboBox();
		comboBoxChooseBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index=comboBoxChooseBook.getSelectedIndex();
				if (index!=-1)
					bookId=tempBooks.get(index).getBookID();
				else
					bookId=-1;
			
				System.out.println(bookId);
			}
		});
		comboBoxChooseBook.setBounds(225, 141, 412, 30);
		add(comboBoxChooseBook);
		/**
		 * This button is the search button- The user need first to choose the book he wants to get a rate on him.
		 * The functionality of the button- first check the input-if the fields are empty,if not-check what
		 *  the user insert and do the search according that.
		 * @author  Coral Carmeli
		 */	
		JButton btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				 if(textFieldBook.getText().isEmpty()&&textFieldAutohr.getText().isEmpty())
						JOptionPane.showMessageDialog(screen,"you must fill some of the field to search!! ", "Warning",JOptionPane.WARNING_MESSAGE);
				 else
				 {
					 
				 	Book b = new Book();
				 	if(textFieldBook.getText().isEmpty()==false&&textFieldAutohr.getText().isEmpty())//just title inserted
				 		tempBooks = BookController.SearchBook("title,author,bookID",b, "title LIKE '%"+textFieldBook.getText().trim()+"%'", screen.getClient());
				 	else if(textFieldBook.getText().isEmpty()&&textFieldAutohr.getText().isEmpty()==false)	//just author inserted
				 		tempBooks = BookController.SearchBook("title,author,bookID",b,"author LIKE '%"+textFieldAutohr.getText().trim()+"%'", screen.getClient());
				 		
				 	else if(textFieldAutohr.getText().isEmpty()==false&&textFieldBook.getText().isEmpty()==false)//the 2 fields are not empty
				 		tempBooks = BookController.SearchBook("title,author,bookID",b, "title LIKE '%"+textFieldBook.getText().trim()+"%'" +" && "+"author LIKE '%"+textFieldAutohr.getText().trim()+"%'", screen.getClient());

					 if(tempBooks==null)
			 		 {
							JOptionPane.showMessageDialog(screen,"No book results were found ", "Warning",JOptionPane.WARNING_MESSAGE);
							textFieldBook.setText("");
							textFieldAutohr.setText("");
			 		 }
			 		 else
			 		 {
			 			 if(comboBoxChooseBook.getSize() != null)
			 				 comboBoxChooseBook.removeAllItems();
						 for(int i=0;i<tempBooks.size();i++)
							 comboBoxChooseBook.addItem("Name: "+tempBooks.get(i).getTitle().trim() + " , " +"Author: "+ tempBooks.get(i).getAuthor().trim());
			 		 }
				 	}
				 }
			}); 
		btnSearch.setBounds(687, 90, 89, 30);
		add(btnSearch);
		/**
		 * This button is the Select button- next,after the search-the user need to choose the kind of the rate and than when the 'select'
		 * button is pressed- the action is: check what is checked and show the specific details to the user.
		 * @author  Coral Carmeli
		 */	
		JButton btnSelect = new JButton("Select");
		btnSelect.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnSelect.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				
				if(bookId!=-1)
				{						
					if(rdbtnAbsoluteRate.isSelected())
						BookController.absoluteBookRate(bookId,screen.getClient(),screen);
					else
						if(rdbtnProportionRate.isSelected())
							BookController.propotionBookRate(bookId,screen.getClient(),screen);
						else
						JOptionPane.showMessageDialog(screen,"You need to choose kind of rate! ", "Warning",JOptionPane.WARNING_MESSAGE);
				}
				else 
					JOptionPane.showMessageDialog(screen,"there is no book to select ", "Warning",JOptionPane.WARNING_MESSAGE);

			}
		});
		btnSelect.setBounds(377, 250, 89, 23);
		add(btnSelect);
	}
}



