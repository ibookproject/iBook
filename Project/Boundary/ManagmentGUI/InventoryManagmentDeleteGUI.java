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
	 * @param null
	 * @return null
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
		btnBack.setBounds(11, 33, 89, 23);
		add(btnBack);
		
		JLabel lblSearchBookFor = new JLabel("Delete Book");
		lblSearchBookFor.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSearchBookFor.setBounds(361, 33, 195, 46);
		add(lblSearchBookFor);
		
		JLabel lblNameOfAuthor = new JLabel("name of author:");
		lblNameOfAuthor.setBounds(400, 90, 89, 19);
		add(lblNameOfAuthor);
		
		textFieldAutohr = new JTextField();
		textFieldAutohr.setBounds(499, 89, 86, 20);
		add(textFieldAutohr);
		textFieldAutohr.setColumns(10);
		
		JLabel lblNameOfBook = new JLabel("name of book:");
		lblNameOfBook.setBounds(213, 94, 111, 19);
		add(lblNameOfBook);
		
		textFieldBook = new JTextField();
		textFieldBook.setBounds(300, 90, 86, 20);
		add(textFieldBook);
		textFieldBook.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				index=comboBox.getSelectedIndex();
				if (index!=-1)
					bookId=tempBooks.get(index).getBookID();
				else
					bookId=-1;
				System.out.println(index);
				System.out.println(bookId);
				
				 
			}
		});
		comboBox.setBounds(225, 141, 412, 20);
		add(comboBox);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 
				Book b=new Book();
				
				 	 if(textFieldAutohr.getText().isEmpty()&&textFieldBook.getText().isEmpty())
				 	 {
							JOptionPane.showMessageDialog(screen,"you must fill the name of the book !! ", "Warning",JOptionPane.WARNING_MESSAGE);
			 				comboBox.removeAllItems();
				 	 }
					 	else if(textFieldAutohr.getText().isEmpty()==false&&textFieldBook.getText().isEmpty()==false)
					 	{
					 		if(Validation.AuthorValidation(textFieldAutohr.getText(), 20)==true&&Validation.TitleValidation(textFieldBook.getText(), 20)==true&&Validation.regularValidation(textFieldAutohr.getText())==true&&Validation.regularValidation(textFieldBook.getText())==true)
					 		{
					 		tempBooks = BookController.SearchBook("title,author,bookID",b, "title LIKE '%"+textFieldBook.getText().trim() +"%'"+ " && "+"author LIKE '%"+textFieldAutohr.getText().trim()+"%'"+ "&&"+"bookEnable=\""+1+"\"", screen.getClient());
					 		 if(tempBooks==null)
					 		 {
									JOptionPane.showMessageDialog(screen,"no book results were found ", "Warning",JOptionPane.WARNING_MESSAGE);
									textFieldBook.setText("");textFieldAutohr.setText("");
					 				comboBox.removeAllItems();
					 		 }
					 		 else
					 		 {
					 				comboBox.removeAllItems();
								for(int i=0;i<tempBooks.size();i++)
									comboBox.addItem("Name: "+tempBooks.get(i).getTitle().trim() + " , " +"Author: "+ tempBooks.get(i).getAuthor().trim());
					 		 }	 
					 		} else 
					 		{
					 			if(Validation.TitleValidation(textFieldBook.getText(), 20)==false||Validation.regularValidation(textFieldBook.getText())==false){JOptionPane.showMessageDialog(screen,"Iligel title field! ", "Warning",JOptionPane.WARNING_MESSAGE);textFieldBook.setText("");}
					 			 if(Validation.AuthorValidation(textFieldAutohr.getText(), 20)==false||Validation.regularValidation(textFieldAutohr.getText())==false){JOptionPane.showMessageDialog(screen,"Iligel author field! ", "Warning",JOptionPane.WARNING_MESSAGE);textFieldAutohr.setText("");}
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
				 				comboBox.removeAllItems();
				 		 }
				 		 else
				 		 {
				 			 if(comboBox.getSize() != null)	comboBox.removeAllItems();
				 			 	for(int i=0;i<tempBooks.size();i++)
				 			 		comboBox.addItem("Name: "+tempBooks.get(i).getTitle().trim() + " , " +"Author: "+ tempBooks.get(i).getAuthor().trim());
				 		 }
				 		}
				 		else {JOptionPane.showMessageDialog(screen," Iligal title field! ", "Warning",JOptionPane.WARNING_MESSAGE);textFieldBook.setText("");}

				 	}
				 	else if(textFieldAutohr.getText().isEmpty()==false&&textFieldBook.getText().isEmpty())
				 	{
				 		if(Validation.AuthorValidation(textFieldAutohr.getText(), 20)==true&&Validation.regularValidation(textFieldAutohr.getText())==true)
				 		{

				 		tempBooks = BookController.SearchBook("title,author,bookID",b, "author LIKE '%"+textFieldAutohr.getText().trim() +"%'"+ " && "+"bookEnable=\""+1+"\"", screen.getClient());
						 if(tempBooks==null)
				 		 {
								JOptionPane.showMessageDialog(screen,"no book results were found ", "Warning",JOptionPane.WARNING_MESSAGE);
								textFieldBook.setText("");textFieldAutohr.setText("");
				 				comboBox.removeAllItems();
				 		 }
				 		 else
				 		 {
				 			 if(comboBox.getSize() != null)	comboBox.removeAllItems();
				 			 	for(int i=0;i<tempBooks.size();i++)
				 			 		comboBox.addItem("Name: "+tempBooks.get(i).getTitle().trim() + " , " +"Author: "+ tempBooks.get(i).getAuthor().trim());
				 		 }
				 		} else {JOptionPane.showMessageDialog(screen,"Iligal author field!! ", "Warning",JOptionPane.WARNING_MESSAGE);textFieldAutohr.setText("");}

				 	}
				}
			}); 
		btnSearch.setBounds(612, 86, 89, 23);
		add(btnSearch);
		
		JButton btnDelet = new JButton("Delete");
		btnDelet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(bookId!=-1)
				{
					int answer=JOptionPane.showConfirmDialog(null, "are you sure you want to delte this book ?","Warning !!", JOptionPane.YES_NO_OPTION);
				System.out.println(answer);
					if(answer==0)
					{
						//means delete ..... 
						Book b=new Book();
						BookController.DeleteBook(b,"bookID=\""+bookId+"\""+ " && "+"bookEnable=\""+1+"\"",screen.getClient());
					//	comboBox.removeAllItems();
						tempBooks.remove(index);
						comboBox.removeItemAt(index);
					}
				}
				else JOptionPane.showMessageDialog(screen,"there is no book to select ", "Warning",JOptionPane.WARNING_MESSAGE);

			}
		});
		btnDelet.setBounds(377, 250, 89, 23);
		add(btnDelet);
		comboBox.removeAllItems();
		
	
	}
}