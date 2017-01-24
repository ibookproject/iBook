package MemberGUI;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.MatteBorder;

import Book.Book;
import Book.Review;
import Controller.ReviewController;
import Controller.UserController;
import Extras.Validation;
import Controller.BookController;
import MenuGUI.LoginGUI;
import Panels.BookPanel;
import Panels.ReviewPanel;
import Panels.SearchReviewPanel;
import Panels.UserSubscriptionPanel;
import Role.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SearchReviewGUI extends JPanel {
	private JTextField textFieldAutohr;
	private JTextField textFieldBook;
	private int bookId;
	private ArrayList<Book> tempBooks;
	private LoginGUI screen;
	private Review r;
	private JPanel pann;
	public static JPanel panel;
	private JScrollPane scrollPaneMain;
	private ArrayList<Review> temp;
	private String titleBook;
	private int index;
	private int flag;
	
	private static final long serialVersionUID = 1L;
	public JButton btnBack ;
	
	public SearchReviewGUI(LoginGUI screen) {
		super();
		bookId=-1;
		flag=0;
		pann=this;
		this.screen=screen;
		initialize();
	}

	
	private void initialize() {
		
		this.setSize(850, 625);
		this.setLayout(null);	
		
		JLabel lblNewLabel = new JLabel("Search Review's of book");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(352, 59, 211, 14);
		add(lblNewLabel);
		
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
		
		
		JComboBox comboBoxOfBooks = new JComboBox();
		
		comboBoxOfBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 index=comboBoxOfBooks.getSelectedIndex();
				if (index!=-1)
					bookId=tempBooks.get(index).getBookID();
				else 
					bookId=-1;				
				if(flag!=0)
				{			
				if(bookId!=-1)
				{
					 r = new Review();// create review
					 titleBook=tempBooks.get(index).getTitle();
					temp = ReviewController.SearchReviews("reviewID,reviewDate,reviewContent,reviewStatus,bookID", r, ""+ "bookID=\""+bookId+"\""+ " && "+"reviewStatus=\""+"1"+"\"", screen.getClient());
					if (temp != null) {
						panel.removeAll();
						panel.setVisible(true);
						scrollPaneMain.setVisible(true);
						for(Review rr:temp)
							panel.add(new SearchReviewPanel(screen,rr,titleBook));						
					} 
					else 
					{
						panel.setVisible(false);
						scrollPaneMain.setVisible(false);
						JOptionPane.showMessageDialog(screen,"no review results !  ", "no results",JOptionPane.QUESTION_MESSAGE);
					}
		
				}
				else 
 				comboBoxOfBooks.removeAllItems();
						 
			}
				flag=1;
			}
		});
		
		comboBoxOfBooks.setBounds(223, 120, 412, 20);
		add(comboBoxOfBooks);
		
		
		scrollPaneMain = new JScrollPane();
		scrollPaneMain.getVerticalScrollBar().setUnitIncrement(16);
		scrollPaneMain.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneMain.setAutoscrolls(true);
		scrollPaneMain.setBounds(125, 160, 682, 392);
		scrollPaneMain.setVisible(false);
		add(scrollPaneMain);
		
		
		panel = new JPanel();
		panel.setIgnoreRepaint(true);
		panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		panel.setAutoscrolls(true);
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		scrollPaneMain.setViewportView(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
	
		
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				
			 	 if(textFieldAutohr.getText().isEmpty()&&textFieldBook.getText().isEmpty())
				 {
		 				comboBoxOfBooks.removeAllItems();
		 				panel.removeAll();
		 				panel.setVisible(false);
						scrollPaneMain.setVisible(false);
						JOptionPane.showMessageDialog(screen,"you must fill the name/author of the book !! ", "Warning",JOptionPane.WARNING_MESSAGE);
				 }
				 else
				 {
				 	Book b = new Book(); // create new book
				 	
				 	 if(textFieldAutohr.getText().isEmpty()==false&&textFieldBook.getText().isEmpty()==false)
				 	{
					 	if(Validation.AuthorValidation(textFieldAutohr.getText(), 20)==true&&Validation.TitleValidation(textFieldBook.getText(), 20)==true&&Validation.regularValidation(textFieldAutohr.getText())==true&&Validation.regularValidation(textFieldBook.getText())==true)
				 		{
					 		tempBooks = BookController.SearchBook("title,author,bookID",b, "title LIKE '%"+textFieldBook.getText().trim() +"%'"+ " && "+"author LIKE '%"+textFieldAutohr.getText().trim()+"%'"+ "&&"+"bookEnable=\""+1+"\"", screen.getClient());
				 		 if(tempBooks==null)
				 		 {
				 			comboBoxOfBooks.removeAllItems();
			 				panel.removeAll();
			 				panel.setVisible(false);
							scrollPaneMain.setVisible(false);
								JOptionPane.showMessageDialog(screen,"no book results were found ", "Warning",JOptionPane.WARNING_MESSAGE);
								textFieldBook.setText("");textFieldAutohr.setText("");
				 		 }
				 		 else
				 		 {
				 				comboBoxOfBooks.removeAllItems();
							for(int i=0;i<tempBooks.size();i++)
								comboBoxOfBooks.addItem("Name: "+tempBooks.get(i).getTitle().trim() + " , " +"Author: "+ tempBooks.get(i).getAuthor().trim());
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
							comboBoxOfBooks.removeAllItems();
			 				panel.removeAll();
			 				panel.setVisible(false);
							scrollPaneMain.setVisible(false);
							JOptionPane.showMessageDialog(screen,"no book results were found ", "Warning",JOptionPane.WARNING_MESSAGE);
							textFieldBook.setText("");textFieldAutohr.setText("");
			 		 }
			 		 else
			 		 {
			 		if(comboBoxOfBooks.getSize() != null)	comboBoxOfBooks.removeAllItems();
						for(int i=0;i<tempBooks.size();i++)
							comboBoxOfBooks.addItem("Name: "+tempBooks.get(i).getTitle().trim() + " , " +"Author: "+ tempBooks.get(i).getAuthor().trim());
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
						 	comboBoxOfBooks.removeAllItems();
			 				panel.removeAll();
			 				panel.setVisible(false);
							scrollPaneMain.setVisible(false);
							JOptionPane.showMessageDialog(screen,"no book results were found ", "Warning",JOptionPane.WARNING_MESSAGE);
							textFieldBook.setText("");textFieldAutohr.setText("");
							comboBoxOfBooks.removeAllItems();
			 		 }
			 		 else
			 		 {
			 			 if(comboBoxOfBooks.getSize() != null)	comboBoxOfBooks.removeAllItems();
			 			 	for(int i=0;i<tempBooks.size();i++)
			 			 		comboBoxOfBooks.addItem("Name: "+tempBooks.get(i).getTitle().trim() + " , " +"Author: "+ tempBooks.get(i).getAuthor().trim());
			 		 }
			 		} else {JOptionPane.showMessageDialog(screen,"Iligal author field!! ", "Warning",JOptionPane.WARNING_MESSAGE);textFieldAutohr.setText("");}

			 	}

			}}
			}); 
		btnSearch.setBounds(612, 86, 89, 23);
		add(btnSearch);

		ImageIcon backIcon =new ImageIcon("Extras/Images/backIcon.png");
		btnBack = new JButton(backIcon);
		btnBack.setBounds(39, 52, 89, 23);
		add(btnBack);

	}
}