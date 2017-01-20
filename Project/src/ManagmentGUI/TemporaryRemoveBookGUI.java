package ManagmentGUI;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.MatteBorder;

import Book.Book;
import Book.Review;
import Controller.BookController;
import Controller.ReviewController;
import MenuGUI.LoginGUI;
import Panels.BookStatisticsPanel;
import Panels.SearchReviewPanel;


/**
 * 
 * @author Sagi Entenberg
 *
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
	private JComboBox<Book> comboBoxOfBooks;
	
	private static final long serialVersionUID = 1L;
	public JButton btnBack ;
/**
 * 	
 * @param screen
 */
	public TemporaryRemoveBookGUI(LoginGUI screen) {
		super();
		bookId=-1;
		flag=0;
		pann=this;
		this.screen=screen;
		initialize();
	}

/**
 * initialize the frame
 */
	private void initialize() {
		
		this.setSize(850, 625);
		this.setLayout(null);	
		
		JLabel lblHeader = new JLabel("Temporary Remove Book");
		lblHeader.setFont(new Font("BN Elements", Font.BOLD, 20));
		lblHeader.setBounds(242, 32, 351, 53);
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
				 index=comboBoxOfBooks.getSelectedIndex();
				if (index!=-1)
					bookId=tempBooks.get(index).getBookID();
				else 
					bookId=-1;
				//System.out.println(bookId);
				
			}
		});
		
		comboBoxOfBooks.setBounds(387, 125, 441, 39);
		add(comboBoxOfBooks);
		

		JButton btnHide = new JButton("Hide");
		btnHide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Book b = new Book(bookId);
				
				if( tempBooks.get(0).isBookEnable()==1)
				{
					
					BookController.UpdateBook(b, "bookEnable=\""+0+"\"", "bookID=\""+tempBooks.get(0).getBookID()+"\"", screen.getClient());
					//System.out.println("hide");
					btnHide.setEnabled(false);
 				
				}
			}
		});
		btnHide.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnHide.setBounds(242, 505, 127, 49);
		add(btnHide);
		
		JButton btnShow = new JButton("Show");
		btnShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Book b = new Book(bookId);
				if( tempBooks.get(0).isBookEnable()==0)
				{
					
					BookController.UpdateBook(b, "bookEnable=\""+1+"\"", "bookID=\""+bookId+"\"", screen.getClient());
					//System.out.println("Show");
 					btnShow.setEnabled(false);
				}
			}
		});
		btnShow.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnShow.setBounds(515, 505, 127, 49);
		add(btnShow);
	
		
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				////////////////////////button to back panel from panel /////////////////////////////////////////////
			StatisticsBookReport sbr=new StatisticsBookReport(screen);
			sbr.btnBack.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						screen.setContentPane(pann);
					}
				////////////////////////button to back panel from panel/////////////////////////////////////////////
				});
			comboBoxOfBooks.removeAllItems();		
				if ((textFieldBookTitle.getText().isEmpty())&&(textFieldAuthor.getText().isEmpty()))
					JOptionPane.showMessageDialog(screen, "you must fill one field at least ", "Warning",
							JOptionPane.WARNING_MESSAGE);
				else
				{
					Book b = new Book(); // create
					if(textFieldBookTitle.getText().isEmpty())
				 		tempBooks = BookController.SearchBook("title,author,bookID",b, "author LIKE '%"+textFieldAuthor.getText().trim() +"%'", screen.getClient());
					else if(textFieldAuthor.getText().isEmpty())
				 		tempBooks = BookController.SearchBook("title,author,bookID",b, "title LIKE '%"+textFieldBookTitle.getText().trim() +"%'", screen.getClient());				
					else
					{
						tempBooks = BookController.SearchBook(
								"title,author,bookID", b, "title LIKE '%"+textFieldBookTitle.getText().trim() +"%'"
										+ " && " + "author LIKE '%"+textFieldAuthor.getText().trim() +"%'",screen.getClient());
					}
					if(tempBooks != null)
					{
						int i=0;
						for (Book bt : tempBooks)
						{
						//	comboBoxOfBooks.addItem("BookID:"+tempBooks.get(i).getBookID()+"   , Name: "+tempBooks.get(i).getTitle().trim() + "   , " +"Author: "+ tempBooks.get(i).getAuthor().trim());
							comboBoxOfBooks.addItem(bt);
							i++;
						}
					}
					else
						JOptionPane.showMessageDialog(screen, "The book is not found!", "Warning",
								JOptionPane.WARNING_MESSAGE);
					textFieldBookTitle.setText("");
					textFieldAuthor.setText("");
				}

			}
		});
		/*btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				
			 	 if(textFieldAutohr.getText().isEmpty()&&textFieldBook.getText().isEmpty())
				 {
		 				comboBoxOfBooks.removeAllItems();
		 		
						JOptionPane.showMessageDialog(screen,"you must fill the name/author of the book !! ", "Warning",JOptionPane.WARNING_MESSAGE);
				 }
				 else
				 {
				 	Book b = new Book(textFieldBook.getText().trim(),textFieldAutohr.getText().trim()); // create new book
				 	
				 	 if(textFieldAutohr.getText().isEmpty()==false&&textFieldBook.getText().isEmpty()==false)
				 	{
					 		tempBooks = BookController.SearchBook("title,author,bookID,bookEnable",b, "title LIKE '%"+textFieldBook.getText().trim() +"%'"+ " && "+"author LIKE '%"+textFieldAutohr.getText().trim()+"%'"+ "&&"+"bookEnable=\""+1+"\"", screen.getClient());
				 		 if(tempBooks==null)
				 		 {
				 			comboBoxOfBooks.removeAllItems();
			 		
								JOptionPane.showMessageDialog(screen,"no book results were found ", "Warning",JOptionPane.WARNING_MESSAGE);
								textFieldBook.setText("");textFieldAutohr.setText("");
				 		 }
				 		 else
				 		 {
				 			if( tempBooks.get(0).isBookEnable()==1){
				 				btnHide.setEnabled(true);
			 					btnShow.setEnabled(false);
				 			}
				 			else{
				 					btnHide.setEnabled(false);
				 					btnShow.setEnabled(true);
				 			}
				 				
				 				comboBoxOfBooks.removeAllItems();
							for(int i=0;i<tempBooks.size();i++)
								comboBoxOfBooks.addItem("BookID:"+tempBooks.get(i).getBookID()+"   ,Name: "+tempBooks.get(i).getTitle().trim() + " , " +"Author: "+ tempBooks.get(i).getAuthor().trim());
				 		 }	 
				 	}
				else if(textFieldAutohr.getText().isEmpty()&&textFieldBook.getText().isEmpty()==false)
			 	{
			 		tempBooks = BookController.SearchBook("title,author,bookID,bookEnable",b, "title=\""+textFieldBook.getText().trim() +"\"", screen.getClient());
			 		if(tempBooks==null)
			 		 {
							comboBoxOfBooks.removeAllItems();
			 			
							JOptionPane.showMessageDialog(screen,"no book results were found ", "Warning",JOptionPane.WARNING_MESSAGE);
							textFieldBook.setText("");
							textFieldAutohr.setText("");
			 		 }
			 		 else
			 		 {
			 			if( tempBooks.get(0).isBookEnable()==1){
			 				btnHide.setEnabled(true);
		 					btnShow.setEnabled(false);
			 				}
				 			else{
			 					btnHide.setEnabled(false);
			 					btnShow.setEnabled(true);	
				 			}
				 			
			 		if(comboBoxOfBooks.getSize() != null)	comboBoxOfBooks.removeAllItems();
						for(int i=0;i<tempBooks.size();i++)
							comboBoxOfBooks.addItem("BookID:"+tempBooks.get(i).getBookID()+"   ,Name: "+tempBooks.get(i).getTitle().trim() + " , " +"Author: "+ tempBooks.get(i).getAuthor().trim());
			 		 }
			 	}
			 	else if(textFieldAutohr.getText().isEmpty()==false&&textFieldBook.getText().isEmpty())
			 	{
			 		tempBooks = BookController.SearchBook("title,author,bookID,bookEnable",b, "author LIKE '%"+textFieldAutohr.getText().trim() +"%'"+ " && "+"bookEnable=\""+1+"\"", screen.getClient()); 
			 		if(tempBooks==null)
			 		 {
						 	comboBoxOfBooks.removeAllItems();
							JOptionPane.showMessageDialog(screen,"no book results were found ", "Warning",JOptionPane.WARNING_MESSAGE);
							textFieldBook.setText("");textFieldAutohr.setText("");
							comboBoxOfBooks.removeAllItems();
			 		 }
			 		 else
			 		 {
				 			if( tempBooks.get(0).isBookEnable()==1){
				 				btnHide.setEnabled(true);
			 					btnShow.setEnabled(false);
				 			}
			 			else{
			 					btnHide.setEnabled(false);
			 					btnShow.setEnabled(true);		
			 			}
			 			 if(comboBoxOfBooks.getSize() != null)	comboBoxOfBooks.removeAllItems();
			 			 	for(int i=0;i<tempBooks.size();i++)
			 			 		comboBoxOfBooks.addItem("BookID:"+tempBooks.get(i).getBookID()+"   ,Name: "+tempBooks.get(i).getTitle().trim() + " , " +"Author: "+ tempBooks.get(i).getAuthor().trim());
			 		 }
			 	}

			}}
			}); */
		btnSearch.setBounds(125, 288, 120, 39);
		add(btnSearch);

		ImageIcon backIcon =new ImageIcon("src/images/backIcon.png");
		btnBack = new JButton(backIcon);
		btnBack.setBounds(39, 52, 89, 23);
		add(btnBack);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 191, 255)));
		btnNewButton.setEnabled(false);
		btnNewButton.setBounds(12, 125, 351, 219);
		add(btnNewButton);
		
		

	}
}