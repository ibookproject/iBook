/*package ManagmentGUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

import Book.Domain;
import Book.SubjectToBook;
import Controller.UserController;
import Controller.bookController;
import MenuGUI.LoginGUI;
import Role.User;

public class BookRateGUI extends JPanel {
	

	private static final long serialVersionUID = 1L;
	public JButton btnBack ;
	private LoginGUI screen;
	private JPanel pann;
	private JLabel lblChooseDomain;
	private JComboBox comboBoxChooseDomain;
	private JLabel lblChooseSubject;
	private JComboBox comboBoxChooseSubject;
	private JLabel lblChooseBook;
	private JRadioButton absolute_btn;
	private JRadioButton proportion_btn;
	private JButton btnShowButton;
	private JLabel bookRateLbl;
	private JComboBox<String> comboBoxBook;
	private ArrayList<Domain> Domains;
	private ArrayList<SubjectToBook> Subjects;
	
	public BookRateGUI(LoginGUI screen) 
	{
		super();
		this.screen=screen;
		pann=this;

		initialize();
	}

	private void initialize() {
	
		this.setSize(850, 625);
		this.setLayout(null);	
		ImageIcon backIcon =new ImageIcon("src/images/backIcon.png");
		btnBack = new JButton(backIcon);// declaration of back button
		btnBack.setBounds(39, 52, 89, 23);
		add(btnBack);
		
		bookRateLbl = new JLabel("Request Book Rate");
		bookRateLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		bookRateLbl.setBounds(320, 52, 175, 22);
		add(bookRateLbl);
		
		comboBoxBook = new JComboBox<String>();
		comboBoxBook.setBounds(335, 247, 107, 20);
		add(comboBoxBook);
		
		lblChooseDomain = new JLabel("Choose Domain :");
		lblChooseDomain.setBounds(335, 120, 116, 23);
		add(lblChooseDomain);
		
		comboBoxChooseDomain = new JComboBox();
		comboBoxChooseDomain.setBounds(335, 142, 101, 20);
		Domain d=new Domain("nature");
		Domains=bookController.getAllDomainTable(d, screen.getClient());
		for(int i=0;i<Domains.size();i++)
			comboBoxChooseDomain.addItem(Domains.get(i));
		add(comboBoxChooseDomain);
		
		comboBoxChooseDomain.getSelectedItem();
		
		
		lblChooseSubject = new JLabel("Choose Subject : ");
		lblChooseSubject.setBounds(335, 173, 107, 14);
		add(lblChooseSubject);
		
		Domain d1=new Domain(1,"nature");
		SubjectToBook s=new SubjectToBook(1,"temp");
		comboBoxChooseSubject = new JComboBox();
		comboBoxChooseSubject.setBounds(335, 190, 103, 20);
		ArrayList<SubjectToBook> Subjects= (ArrayList<SubjectToBook>)bookController.SearchSubject("nameSubject",s,"domainID=\""+d1.getDomainID()+"\"" ,screen.getClient());
		if(Subjects==null||Subjects.isEmpty())
		{
			screen.setContentPane(pann);
			JOptionPane.showMessageDialog(screen,"No Subject Found\n", "Warning",JOptionPane.WARNING_MESSAGE);
		}
	
		else
		{
			JOptionPane.showMessageDialog(screen,"Subject Found per Domain\n", "Warning",JOptionPane.WARNING_MESSAGE);
		}
		for(int i=0;i<Subjects.size();i++)
			comboBoxChooseSubject.addItem(Subjects.get(i));
		comboBoxChooseSubject.addActionListener(comboBoxBook);
		add(comboBoxChooseSubject);
		
		lblChooseBook = new JLabel("Choose Book :");
		lblChooseBook.setBounds(335, 222, 91, 23);
		add(lblChooseBook);
		
		absolute_btn = new JRadioButton("Absolute Rate");
		absolute_btn.setBounds(269, 315, 123, 25);
		add(absolute_btn);
		
		proportion_btn = new JRadioButton("Proportion Rate");
		proportion_btn.setBounds(422, 315, 123, 25);
		proportion_btn.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
			
			}
		});
		add(proportion_btn);
		
		
		btnShowButton = new JButton("Show");
		btnShowButton.setBounds(334, 406, 117, 22);
		btnShowButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
////////////////////////button to next window-Show /////////////////////////////////////////////
				ShowBookRate Sbr=new ShowBookRate(screen);
				Sbr.btnBack.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent e) 
					{
							screen.setContentPane(pann);
					}
/////////////////////////button to next window-Show/////////////////////////////////////////////
				});
		screen.setContentPane(Sbr);//send to search book window
			}
		});
		add(btnShowButton);
		}
}
*/

package ManagmentGUI;

import javax.naming.ldap.SortKey;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Book.Book;
import Book.Cart;
import Book.Subject;
import Controller.CartController;
import Controller.BookController;
import MenuGUI.LoginGUI;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.awt.event.ActionEvent;
import java.awt.Font;

import javax.swing.JRadioButton;

/**
 * @author  Coral Carmeli
 * 
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

	public BookRateGUI(LoginGUI screen) {
		super();
		this.screen=screen;
		bookId=-1;
		pann=this;
		initialize();
	}
	
	private void initialize() {

		this.setSize(850, 625);
		this.setLayout(null);	
		ImageIcon backIcon =new ImageIcon("src/images/backIcon.png");
		btnBack = new JButton(backIcon);// declaration of back button
		btnBack.setBounds(39, 52, 89, 23);
		add(btnBack);
		
		
		lblBookRate = new JLabel("Book Rate");
		lblBookRate.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblBookRate.setBounds(361, 33, 195, 46);
		add(lblBookRate);
		
		lblNameOfAuthor = new JLabel("name of author:");
		lblNameOfAuthor.setBounds(398, 92, 108, 19);
		add(lblNameOfAuthor);
		
		textFieldAutohr = new JTextField();
		textFieldAutohr.setBounds(504, 91, 86, 20);
		add(textFieldAutohr);
		textFieldAutohr.setColumns(10);
		
		lblNameOfBook = new JLabel("name of book:");
		lblNameOfBook.setBounds(213, 94, 111, 19);
		add(lblNameOfBook);
		
		textFieldBook = new JTextField();
		textFieldBook.setBounds(300, 90, 86, 20);
		add(textFieldBook);
		textFieldBook.setColumns(10);
		
		
		rdbtnAbsoluteRate = new JRadioButton("Absolute Rate");
		rdbtnAbsoluteRate.setBounds(278, 193, 123, 25);
		add(rdbtnAbsoluteRate);
		
		rdbtnProportionRate = new JRadioButton("Proportion Rate");
		rdbtnProportionRate.setBounds(468, 193, 123, 25);
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
		comboBoxChooseBook.setBounds(225, 141, 412, 20);
		add(comboBoxChooseBook);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				 if(textFieldBook.getText().isEmpty()&&textFieldAutohr.getText().isEmpty())
						JOptionPane.showMessageDialog(screen,"you must fill some of the field to search!! ", "Warning",JOptionPane.WARNING_MESSAGE);
				 else
				 {
					 
				 	Book b = new Book(); // create new book
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
		btnSearch.setBounds(645, 88, 89, 23);
		add(btnSearch);
		
		JButton btnSelect = new JButton("Select");
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



