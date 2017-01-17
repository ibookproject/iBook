package ManagmentGUI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Book.Book;
import Book.Subject;
import Controller.BookController;
import MenuGUI.LoginGUI;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;




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



	public InventoryManagmentSearchForUpdateGUI(LoginGUI screen,JPanel Mainpann) {
		super();
		this.Mainpann=Mainpann;
		this.screen=screen;
		bookId=-1;
		pann=this;
		initialize();
	}

	
	private void initialize() {
		
		this.setSize(850, 625);
		this.setLayout(null);

		ImageIcon backIcon =new ImageIcon("src/images/backIcon.png"); 
		 btnBack = new JButton(backIcon);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBack.setBounds(11, 33, 89, 23);
		add(btnBack);
		
		JLabel lblSearchBookFor = new JLabel("Search Book For Update");
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
				else
					bookId=-1;	
				System.out.println(bookId);
	 
			}
		});
		comboBox.setBounds(225, 141, 412, 20);
		add(comboBox);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			 
				Book b = new Book(textFieldBook.getText().trim(),textFieldAutohr.getText().trim()); // create new book
			 	 if(textFieldAutohr.getText().isEmpty()&&textFieldBook.getText().isEmpty())
			 	 {
						JOptionPane.showMessageDialog(screen,"you must fill the name/author of the book !! ", "Warning",JOptionPane.WARNING_MESSAGE);
		 				comboBox.removeAllItems();
			 	 }
				 	else if(textFieldAutohr.getText().isEmpty()==false&&textFieldBook.getText().isEmpty()==false)
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
 
				 	}
			 	else if(textFieldAutohr.getText().isEmpty()&&textFieldBook.getText().isEmpty()==false)
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
			 	else if(textFieldAutohr.getText().isEmpty()==false&&textFieldBook.getText().isEmpty())
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
			 	}
			}
			}); 
		btnSearch.setBounds(612, 86, 89, 23);
		add(btnSearch);
		
		JButton btnSelect = new JButton("Select");
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(bookId!=-1)
				{
				////////////////////////button to back panel from panel /////////////////////////////////////////////
				AddOrUpdateBookGUI goback=new AddOrUpdateBookGUI(screen,0, bookId,Mainpann); 
				goback.btnBack.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						screen.setContentPane(pann);
					}
				////////////////////////button to back panel from panel/////////////////////////////////////////////
				});
				screen.setContentPane(goback);//send to search book window
				}
				else 
					JOptionPane.showMessageDialog(screen,"there is no book to select ", "Warning",JOptionPane.WARNING_MESSAGE);
			}
	
		});
		btnSelect.setBounds(377, 250, 89, 23);
		add(btnSelect);

	}
}
