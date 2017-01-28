package ManagmentGUI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Book.Book;
import Book.Subject;
import Controller.BookController;
import Extras.Validation;
import MenuGUI.LoginGUI;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;



/**
 * The class take care of Update book , and search a book first for update him.
 * 
 * @author  hen saada
 */

public class InventoryManagmentSearchForUpdateGUI extends JPanel {

	private static final long serialVersionUID = 1L;
	public JButton btnBack ;
	private LoginGUI screen;
	private JPanel pann;
	private JTextField textFieldAutohr;
	private JTextField textFieldBook;
	private int bookId;
	private ArrayList<Book> tempBooks;
	private JPanel Mainpann;

	/**
	 * the class of build the panel GUI to Search for Update  book
	 * @param screen
	 *  LoginGUI extends JFrame
	 *  @param 
	 *  the last panel of the inventorymenu for go back after the update was done 
	 * @author Hen Saada
	 */

	public InventoryManagmentSearchForUpdateGUI(LoginGUI screen,JPanel Mainpann) {
		super();
		this.Mainpann=Mainpann;
		this.screen=screen;
		bookId=-1;
		pann=this;
		initialize();
	}

	/**
	 * This method initialize The window of update book ,puts the components on the screen and set their functionality , 
	 * the user can search book to update by the title, or author , or both. and select to update the book.
	 * the method send the BookID that selected  with flag 0 to the class AddOrUpdateBookGUI for update
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
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBack.setBounds(35, 25, 89, 30);
		add(btnBack);
		
		JLabel lblSearchBookFor = new JLabel("Search Book For Update");
		lblSearchBookFor.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 21));
		lblSearchBookFor.setBounds(325, 40, 265, 40);
		add(lblSearchBookFor);
		
		JLabel lblNameOfAuthor = new JLabel("name of author:");
		lblNameOfAuthor.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNameOfAuthor.setBounds(392, 135, 140, 19);
		add(lblNameOfAuthor);
		
		textFieldAutohr = new JTextField();
		textFieldAutohr.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldAutohr.setBounds(536, 132, 150, 30);
		add(textFieldAutohr);
		textFieldAutohr.setColumns(10);
		
		JLabel lblNameOfBook = new JLabel("Title of book:");
		lblNameOfBook.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNameOfBook.setBounds(81, 135, 131, 20);
		add(lblNameOfBook);
		
		textFieldBook = new JTextField();
		textFieldBook.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldBook.setBounds(208, 132, 150, 30);
		add(textFieldBook);
		textFieldBook.setColumns(10);
		
		JComboBox comboBoxBooks = new JComboBox();
		comboBoxBooks.setFont(new Font("Tahoma", Font.PLAIN, 16));
		comboBoxBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index=comboBoxBooks.getSelectedIndex();
				if (index!=-1)
					bookId=tempBooks.get(index).getBookID();
				else
					bookId=-1;		 
			}
		});
		comboBoxBooks.setBounds(226, 220, 412, 30);
		add(comboBoxBooks);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			 
				Book b = new Book();
			 	 if(textFieldAutohr.getText().isEmpty()&&textFieldBook.getText().isEmpty())
			 	 {
						JOptionPane.showMessageDialog(screen,"you must fill the name/author of the book !! ", "Warning",JOptionPane.WARNING_MESSAGE);
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
		btnSearch.setBounds(704, 132, 89, 30);
		add(btnSearch);
		
		JButton btnSelect = new JButton("Select");
		btnSelect.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(bookId!=-1)
				{
				AddOrUpdateBookGUI goback=new AddOrUpdateBookGUI(screen,0, bookId,Mainpann); 
				goback.btnBack.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						screen.setContentPane(pann);
					}
				});
				screen.setContentPane(goback);//send to search book window
				}
				else 
					JOptionPane.showMessageDialog(screen,"there is no book to select ", "Warning",JOptionPane.WARNING_MESSAGE);
			}
	
		});
		btnSelect.setBounds(653, 220, 89, 30);
		add(btnSelect);

	}
}
