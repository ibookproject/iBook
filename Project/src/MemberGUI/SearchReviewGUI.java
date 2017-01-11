package MemberGUI;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.MatteBorder;

import Book.Book;
import Book.Review;
import Controller.ReviewController;
import Controller.bookController;
import MenuGUI.LoginGUI;
import Panels.BookPanel;
import Panels.ReviewPanel;

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
	private ArrayList<ReviewPanel> reviewPanels;
	private JPanel pann;
	public static JPanel panel;

	private ArrayList<Review> temp;
	
	private static final long serialVersionUID = 1L;
	public JButton btnBack ;
	
	public SearchReviewGUI(LoginGUI screen) {
		super();
		bookId=-1;
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
				 				comboBox.removeAllItems();
							for(int i=0;i<tempBooks.size();i++)
								comboBox.addItem("Name: "+tempBooks.get(i).getTitle().trim() + " , " +"Author: "+ tempBooks.get(i).getAuthor().trim());
							//sb.setList(tempBooks);
							//screen.setContentPane(sb);
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
			

			}}
			}); 
		btnSearch.setBounds(612, 86, 89, 23);
		add(btnSearch);
		
		JButton btnDelet = new JButton("Select");
		btnDelet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(bookId!=-1)
				{
					 r = new Review(1, null, null,0, 1);// create review

					temp = (ArrayList<Review>) ReviewController.SearchReviews("reviewID,reviewDate,reviewContent,reviewStatus,bookID", r, ""+ "bookID=\""+bookId+"\"", screen.getClient());
					System.out.println(temp);
					reviewPanels=new ArrayList<ReviewPanel>();
				
					/*
					for(int i=0;i<temp.size();i++)
					{
						reviewPanels.add(new ReviewPanel(screen,temp.get(i)));
						panel.add(reviewPanels.get(0));

					}
					*/
					
				}
				else JOptionPane.showMessageDialog(screen,"there is no book to select ", "Warning",JOptionPane.WARNING_MESSAGE);

				
	
			}
		});
		btnDelet.setBounds(377, 250, 89, 23);
		add(btnDelet);
	
		ImageIcon backIcon =new ImageIcon("src/images/backIcon.png");
		btnBack = new JButton(backIcon);
	//	btnBack.setIcon(backIcon);
		btnBack.setBounds(39, 52, 89, 23);
		add(btnBack);
		
	
	}
}