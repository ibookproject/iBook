package ManagmentGUI;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.MatteBorder;

import Book.Book;
import Book.Domain;
import Book.Subject;
import Controller.FormatController;
import Controller.ReviewController;
import Extras.Validation;
import Controller.BookController;
import MemberGUI.SearchBook;
import MenuGUI.LoginGUI;
import Panels.BookPanel;
import client.DBgenericObject;

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

import javax.swing.JComboBox;
import javax.swing.SwingConstants;


/**
 * The class take care of Delete book from the system.
 * 
 * @author  hen saada
 */

public class InventoryManagmentDeleteGUI extends JPanel {

	private static final long serialVersionUID = 1L;

	public JButton btnBack ;
	private JTextField textFieldAutohr;
	private JTextField textFieldBook;
	private int bookId;
	private ArrayList<Book> tempBooks;
	private LoginGUI screen;
	private int index;
	

	/**
	 * the class of build the panel GUI to Delete book
	 * @param screen
	 *  LoginGUI extends JFrame
	 * @author Hen Saada
	 */
	public InventoryManagmentDeleteGUI(LoginGUI screen ) {
		super();
		bookId=-1;
		this.screen=screen;
		initialize();
	}

	
	/**
	 * This method initialize The window of Delete book ,puts the components on the screen and set their functionality , 
	 * the user can search book to delete by the title, or author , or both. and select to delete the book.
	 * the method also check validation about the input fields - author and title .
	 * title can include numbers and English letter only 
	 * author can include English letters only
	 * @author  hen saada
	 * 
	 */
	private void initialize() {
		
		this.setSize(850, 625);
		this.setLayout(null);

		ImageIcon backIcon =new ImageIcon("Extras/Images/backIcon.png"); 
		 btnBack = new JButton(backIcon);
			SearchBook sb = new SearchBook(screen,tempBooks);
		sb.btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBack.setBounds(35, 25, 89, 30);
		add(btnBack);
		
		JLabel lblSearchBookFor = new JLabel("Delete Book");
		lblSearchBookFor.setHorizontalAlignment(SwingConstants.CENTER);
		lblSearchBookFor.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 21));
		lblSearchBookFor.setBounds(361, 40, 195, 40);
		add(lblSearchBookFor);
		
		JLabel lblNameOfAuthor = new JLabel("name of author:");
		lblNameOfAuthor.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNameOfAuthor.setBounds(416, 135, 140, 19);
		add(lblNameOfAuthor);
		
		textFieldAutohr = new JTextField();
		textFieldAutohr.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldAutohr.setBounds(560, 132, 150, 30);
		add(textFieldAutohr);
		textFieldAutohr.setColumns(10);
		
		JLabel lblNameOfBook = new JLabel("Title of book:");
		lblNameOfBook.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNameOfBook.setBounds(105, 135, 131, 20);
		add(lblNameOfBook);
		
		textFieldBook = new JTextField();
		textFieldBook.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldBook.setBounds(232, 132, 150, 30);
		add(textFieldBook);
		textFieldBook.setColumns(10);
		
		JComboBox comboBoxBooks = new JComboBox();
		comboBoxBooks.setFont(new Font("Tahoma", Font.PLAIN, 16));
		comboBoxBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				index=comboBoxBooks.getSelectedIndex();
				if (index!=-1)
					bookId=tempBooks.get(index).getBookID();
				else
					bookId=-1;

				
				 
			}
		});
		comboBoxBooks.setBounds(250, 220, 412, 30);
		add(comboBoxBooks);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 
				Book b=new Book();
				
				 	 if(textFieldAutohr.getText().isEmpty()&&textFieldBook.getText().isEmpty())
				 	 {
							JOptionPane.showMessageDialog(screen,"you must fill the name of the book !! ", "Warning",JOptionPane.WARNING_MESSAGE);
			 				comboBoxBooks.removeAllItems();
				 	 }
					 	else if(textFieldAutohr.getText().isEmpty()==false&&textFieldBook.getText().isEmpty()==false)
					 	{
					 		if(Validation.AuthorValidation(textFieldAutohr.getText(), 100)==true&&Validation.TitleValidation(textFieldBook.getText(), 20)==true&&Validation.regularValidation(textFieldAutohr.getText())==true&&Validation.regularValidation(textFieldBook.getText())==true)
					 		{
					 		tempBooks = BookController.SearchBook("title,author,bookID",b, "title LIKE '%"+textFieldBook.getText().trim() +"%'"+ " && "+"author LIKE '%"+textFieldAutohr.getText().trim()+"%'"+ "&&"+"bookEnable=\""+1+"\"", screen.getClient());
					 		 if(tempBooks==null)
					 		 {
									JOptionPane.showMessageDialog(screen,"no book results were found ", "Warning",JOptionPane.WARNING_MESSAGE);
									textFieldBook.setText("");textFieldAutohr.setText("");
					 				comboBoxBooks.removeAllItems();
					 		 }
					 		 else
					 		 {
					 				comboBoxBooks.removeAllItems();
								for(int i=0;i<tempBooks.size();i++)
									comboBoxBooks.addItem("Name: "+tempBooks.get(i).getTitle().trim() + " , " +"Author: "+ tempBooks.get(i).getAuthor().trim());
					 		 }	 
					 		} else 
					 		{
					 			if(Validation.TitleValidation(textFieldBook.getText(), 20)==false||Validation.regularValidation(textFieldBook.getText())==false){JOptionPane.showMessageDialog(screen,"Iligel title field! ", "Warning",JOptionPane.WARNING_MESSAGE);textFieldBook.setText("");}
					 			 if(Validation.AuthorValidation(textFieldAutohr.getText(), 100)==false||Validation.regularValidation(textFieldAutohr.getText())==false){JOptionPane.showMessageDialog(screen,"Iligel author field! ", "Warning",JOptionPane.WARNING_MESSAGE);textFieldAutohr.setText("");}
					 		}
					 	}
				 	else if(textFieldAutohr.getText().isEmpty()&&textFieldBook.getText().isEmpty()==false)
				 	{			
				 		if(Validation.TitleValidation(textFieldBook.getText(), 20)==true&&Validation.regularValidation(textFieldBook.getText())==true)
				 		{
				 		tempBooks = BookController.SearchBook("title,author,bookID",b, "title LIKE '%"+textFieldBook.getText().trim() +"%'"+ " && "+"bookEnable=\""+1+"\"", screen.getClient());
						 if(tempBooks==null)
				 		 {
								JOptionPane.showMessageDialog(screen,"no book results were found ", "Warning",JOptionPane.WARNING_MESSAGE);
								textFieldBook.setText("");textFieldAutohr.setText("");
				 				comboBoxBooks.removeAllItems();
				 		 }
				 		 else
				 		 {
				 			 if(comboBoxBooks.getSize() != null)	comboBoxBooks.removeAllItems();
				 			 	for(int i=0;i<tempBooks.size();i++)
				 			 		comboBoxBooks.addItem("Name: "+tempBooks.get(i).getTitle().trim() + " , " +"Author: "+ tempBooks.get(i).getAuthor().trim());
				 		 }
				 		}
				 		else {JOptionPane.showMessageDialog(screen," Iligal title field! ", "Warning",JOptionPane.WARNING_MESSAGE);textFieldBook.setText("");}

				 	}
				 	else if(textFieldAutohr.getText().isEmpty()==false&&textFieldBook.getText().isEmpty())
				 	{
				 		if(Validation.AuthorValidation(textFieldAutohr.getText(), 100)==true&&Validation.regularValidation(textFieldAutohr.getText())==true)
				 		{

				 		tempBooks = BookController.SearchBook("title,author,bookID",b, "author LIKE '%"+textFieldAutohr.getText().trim() +"%'"+ " && "+"bookEnable=\""+1+"\"", screen.getClient());
						 if(tempBooks==null)
				 		 {
								JOptionPane.showMessageDialog(screen,"no book results were found ", "Warning",JOptionPane.WARNING_MESSAGE);
								textFieldBook.setText("");textFieldAutohr.setText("");
				 				comboBoxBooks.removeAllItems();
				 		 }
				 		 else
				 		 {
				 			 if(comboBoxBooks.getSize() != null)	comboBoxBooks.removeAllItems();
				 			 	for(int i=0;i<tempBooks.size();i++)
				 			 		comboBoxBooks.addItem("Name: "+tempBooks.get(i).getTitle().trim() + " , " +"Author: "+ tempBooks.get(i).getAuthor().trim());
				 		 }
				 		} else {JOptionPane.showMessageDialog(screen,"Iligal author field!! ", "Warning",JOptionPane.WARNING_MESSAGE);textFieldAutohr.setText("");}

				 	}
				}
			}); 
		btnSearch.setBounds(733, 132, 89, 30);
		add(btnSearch);
		
		JButton btnDelet = new JButton("Delete");
		btnDelet.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnDelet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(bookId!=-1)
				{
					int answer=JOptionPane.showConfirmDialog(null, "are you sure you want to delte this book ?","Warning !!", JOptionPane.YES_NO_OPTION);

					if(answer==0)
					{
						//means delete ..... 
						Book b=new Book();
						BookController.DeleteBook(b,"bookID=\""+bookId+"\""+ " && "+"bookEnable=\""+1+"\"",screen.getClient());
					//	comboBox.removeAllItems();
						tempBooks.remove(index);
						comboBoxBooks.removeItemAt(index);
					}
				}
				else JOptionPane.showMessageDialog(screen,"there is no book to select ", "Warning",JOptionPane.WARNING_MESSAGE);

			}
		});
		btnDelet.setBounds(672, 220, 89, 30);
		add(btnDelet);
		comboBoxBooks.removeAllItems();
		
	
	}
}