
package ManagmentGUI;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.GridLayout;
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
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.MatteBorder;

import Book.Book;
import Book.Review;
import Controller.BookController;
import Controller.ReviewController;
import MenuGUI.LoginGUI;
import Panels.SearchReviewPanel;


/**
 * 
 * @author Sagi Entenberg
 *
 */

public class TemporaryRemoveBookGUI extends JPanel {
	private JTextField textFieldAutohr;
	private JTextField textFieldBook;
	private int bookId;
	private ArrayList<Book> tempBooks;
	private LoginGUI screen;
	private Review r;
	private JPanel pann;
	private ArrayList<Review> temp;
	private String titleBook;
	private int index;
	private int flag;
	
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
		
		JLabel lblNewLabel = new JLabel("Search Review's of book");
		lblNewLabel.setFont(new Font("BN Elements", Font.BOLD, 20));
		lblNewLabel.setBounds(242, 32, 351, 53);
		add(lblNewLabel);
		
		JLabel lblNameOfAuthor = new JLabel("name of author:");
		lblNameOfAuthor.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNameOfAuthor.setBounds(39, 236, 148, 57);
		add(lblNameOfAuthor);
		
		textFieldAutohr = new JTextField();
		textFieldAutohr.setBounds(180, 246, 127, 39);
		add(textFieldAutohr);
		textFieldAutohr.setColumns(10);
		
		JLabel lblNameOfBook = new JLabel("name of book:");
		lblNameOfBook.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNameOfBook.setBounds(39, 145, 164, 57);
		add(lblNameOfBook);
		
		textFieldBook = new JTextField();
		textFieldBook.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldBook.setBounds(180, 155, 127, 39);
		add(textFieldBook);
		textFieldBook.setColumns(10);
		
		
		JComboBox comboBoxOfBooks = new JComboBox();
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
		
		comboBoxOfBooks.setBounds(512, 154, 325, 39);
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
		btnHide.setBounds(76, 374, 127, 49);
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
		btnShow.setBounds(268, 374, 127, 49);
		add(btnShow);
	
		
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnSearch.addActionListener(new ActionListener() {
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
			}); 
		btnSearch.setBounds(352, 200, 120, 39);
		add(btnSearch);

		ImageIcon backIcon =new ImageIcon("src/images/backIcon.png");
		btnBack = new JButton(backIcon);
		btnBack.setBounds(39, 52, 89, 23);
		add(btnBack);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 191, 255)));
		btnNewButton.setEnabled(false);
		btnNewButton.setBounds(12, 125, 488, 193);
		add(btnNewButton);
		
		

	}
}
