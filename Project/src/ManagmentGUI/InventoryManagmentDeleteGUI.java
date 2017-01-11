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
import Controller.bookController;
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




public class InventoryManagmentDeleteGUI extends JPanel {

	private static final long serialVersionUID = 1L;

	public JButton btnBack ;
	private ArrayList<Subject>resultSubjects; // array of subjects of some domain
	private ArrayList<Domain> resultDomains; // array of domains
	private JComboBox comboBoxSubject;
	private JTextField textFieldAutohr;
	private JTextField textFieldBook;
	private int bookId;
	private ArrayList<Book> tempBooks;
	private LoginGUI screen;
	//public static JPanel panel;
	//private JScrollPane scrollPaneMain;
	//private ArrayList<Book> books;
	//private JPanel pann;
	

	public InventoryManagmentDeleteGUI(LoginGUI screen ) {
		super();
		//this.tempBooks=books;
	//	pann=this;
		this.screen=screen;
		initialize();
	}

	private void initialize() {
		
		this.setSize(850, 625);
		this.setLayout(null);

		ImageIcon backIcon =new ImageIcon("src/images/backIcon.png"); 
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
				int index=comboBox.getSelectedIndex();
				if (index!=-1)
					bookId=tempBooks.get(index).getBookID();
			
				System.out.println(bookId);
				
				 
			}
		});
		comboBox.setBounds(225, 141, 412, 20);
		add(comboBox);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				
				 if(textFieldBook.getText().isEmpty())
						JOptionPane.showMessageDialog(screen,"you must fill the name of the book !! ", "Warning",JOptionPane.WARNING_MESSAGE);
				 else
				 {
				 	Book b = new Book(textFieldBook.getText().trim(),textFieldAutohr.getText().trim()); // create new book
				 	
				 	if(textFieldAutohr.getText().isEmpty()==false)
				 	{
				 		tempBooks = bookController.SearchBook("title,author,bookID",b, "title=\""+textFieldBook.getText().trim()+ "\"" + " && "+"author=\""+textFieldAutohr.getText().trim()+"\"", screen.getClient());
				 		 if(tempBooks==null)
				 		 {
								JOptionPane.showMessageDialog(screen,"no book results were found ", "Warning",JOptionPane.WARNING_MESSAGE);
								textFieldBook.setText("");textFieldAutohr.setText("");
				 		 }
				 		 else
				 		 {
				 		//	if(flagFirstTime==1)
				 				comboBox.removeAllItems();
							for(int i=0;i<tempBooks.size();i++)
								comboBox.addItem("Name: "+tempBooks.get(i).getTitle().trim() + " , " +"Author: "+ tempBooks.get(i).getAuthor().trim());
							sb.setList(tempBooks);
							screen.setContentPane(sb);
				 		 }

				 		 
				 	}
			 	else
			 	{
			 		tempBooks = bookController.SearchBook("title,author,bookID",b, "title=\""+textFieldBook.getText().trim() +"\"", screen.getClient());
					 if(tempBooks==null)
			 		 {
							JOptionPane.showMessageDialog(screen,"no book results were found ", "Warning",JOptionPane.WARNING_MESSAGE);
							textFieldBook.setText("");textFieldAutohr.setText("");
			 		 }
			 		 else
			 		 {
			 		if(comboBox.getSize() != null)	comboBox.removeAllItems();
						for(int i=0;i<tempBooks.size();i++)
							comboBox.addItem("Name: "+tempBooks.get(i).getTitle().trim() + " , " +"Author: "+ tempBooks.get(i).getAuthor().trim());
					//	sb.setList(tempBooks);
					//	screen.setContentPane(sb);
			 		 }
			 	}
				 	/*
				comboBox.removeAllItems();
				for(int i=0;i<temp.size();i++)
					comboBox.addItem(temp.get(i).getTitle() + " , " + temp.get(i).getAuthor());
					*/

			}}
			}); 
		btnSearch.setBounds(612, 86, 89, 23);
		add(btnSearch);
		
		JButton btnDelet = new JButton("Delete");
		btnDelet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					int answer=JOptionPane.showConfirmDialog(null, "are you sure you want to delte this book ?","Warning !!", JOptionPane.YES_NO_OPTION);
				System.out.println(answer);
				if(answer==0)
				{
					//means delete ..... 
					Book b=new Book();
					bookController.DeleteBook(b,"bookID=\""+bookId+"\"",screen.getClient());
	
				}
			}
		});
		btnDelet.setBounds(377, 250, 89, 23);
		add(btnDelet);
		
		
	
	}
}