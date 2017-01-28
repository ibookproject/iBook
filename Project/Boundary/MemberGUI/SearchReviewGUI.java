package MemberGUI;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.MatteBorder;

import Book.Book;
import Book.Review;
import Controller.ReviewController;
import Extras.Validation;
import Controller.BookController;
import MenuGUI.LoginGUI;
import Panels.SearchReviewPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JRadioButton;
/**
 * The class take care of Search Review functionality search reviews by there book
 * 
 * @author  hen saada
 */
public class SearchReviewGUI extends JPanel {
	private JTextField textFieldAutohr;
	private JTextField textFieldBook;
	private int bookId;
	private ArrayList<Book> tempBooks;
	private LoginGUI screen;
	private Review r;
	private int radioButtonChoose = 0;// integer to sent what the radio button
	private JPanel pann;
	public static JPanel panel;
	private JScrollPane scrollPaneMain;
	private ArrayList<Review> temp;
	private String titleBook;
	private int index;
	private int flag;
	
	private static final long serialVersionUID = 1L;
	public JButton btnBack ;
	private JTextField textFieldKeyWord;
	/**
	 * the class of build the panel GUI to Search review
	 * @param screen
	 *  LoginGUI extends JFrame
	 * @author Hen Saada
	 */
	public SearchReviewGUI(LoginGUI screen) {
		super();
		bookId=-1;
		flag=0;
		pann=this;
		this.screen=screen;
		initialize();
	}

	/**
	 * This method initialize The window of Search Review ,puts the components on the screen and set their functionality
	 * the user Can Search Review's By title/author OR keyword of the book's
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
		
		JLabel lblKeyWord = new JLabel("Key Word : ");
		lblKeyWord.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblKeyWord.setBounds(335, 85, 86, 30);
		add(lblKeyWord);
		
		textFieldKeyWord = new JTextField();
		textFieldKeyWord.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldKeyWord.setBounds(460, 85, 150, 30);
		add(textFieldKeyWord);
		textFieldKeyWord.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Search Review's of book");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 21));
		lblNewLabel.setBounds(325, 40, 264, 40);
		add(lblNewLabel);
		
		JLabel lblNameOfAuthor = new JLabel("name of author:");
		lblNameOfAuthor.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNameOfAuthor.setBounds(335, 120, 120, 30);
		add(lblNameOfAuthor);
		
		textFieldAutohr = new JTextField();
		textFieldAutohr.setBounds(460, 127, 150, 30);
		add(textFieldAutohr);
		textFieldAutohr.setColumns(10);
		
		JLabel lblNameOfBook = new JLabel("title of book:");
		lblNameOfBook.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNameOfBook.setBounds(335, 85, 111, 30);
		add(lblNameOfBook);
		
		textFieldBook = new JTextField();
		textFieldBook.setBounds(460, 87, 150, 30);
		add(textFieldBook);
		textFieldBook.setColumns(10);
		
		JComboBox comboBoxOfBooks = new JComboBox();
		comboBoxOfBooks.setBounds(289, 178, 348, 20);
		add(comboBoxOfBooks);
		
		
		JRadioButton rdbtnSearchByTitleauthor = new JRadioButton("Search By Title/Author");
		rdbtnSearchByTitleauthor.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbtnSearchByTitleauthor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				radioButtonChoose=1;
				panel.removeAll();
				panel.updateUI();
				comboBoxOfBooks.setVisible(true);
				lblNameOfBook.setVisible(true);
				textFieldBook.setVisible(true);
				lblNameOfAuthor.setVisible(true);
				textFieldAutohr.setVisible(true);
				lblKeyWord.setVisible(false);
				textFieldKeyWord.setVisible(false);
			}
		});
		rdbtnSearchByTitleauthor.setBounds(125, 85, 185, 30);
		add(rdbtnSearchByTitleauthor);
		
		JRadioButton rdbtnSearchByBook = new JRadioButton("Search By Book KeyWord");
		rdbtnSearchByBook.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbtnSearchByBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				radioButtonChoose=2;
				panel.removeAll();
				panel.updateUI();
				comboBoxOfBooks.setVisible(false);
				lblNameOfBook.setVisible(false);
				textFieldBook.setVisible(false);
				lblNameOfAuthor.setVisible(false);
				textFieldAutohr.setVisible(false);
				lblKeyWord.setVisible(true);
				textFieldKeyWord.setVisible(true);
			}
		});
		rdbtnSearchByBook.setBounds(125, 120, 203, 30);
		add(rdbtnSearchByBook);
		
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnSearchByTitleauthor);
		group.add(rdbtnSearchByBook);
		
		rdbtnSearchByBook.setSelected(true);
		comboBoxOfBooks.setVisible(false);
		lblNameOfBook.setVisible(false);
		textFieldBook.setVisible(false);
		lblNameOfAuthor.setVisible(false);
		textFieldAutohr.setVisible(false);
		lblKeyWord.setVisible(true);
		textFieldKeyWord.setVisible(true);

		comboBoxOfBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.removeAll();
				panel.updateUI();
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
		


		
		scrollPaneMain = new JScrollPane();
		scrollPaneMain.getVerticalScrollBar().setUnitIncrement(16);
		scrollPaneMain.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneMain.setAutoscrolls(true);
		scrollPaneMain.setBounds(125, 220, 682, 332);
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
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(radioButtonChoose==1)
				{			
			 	 if(textFieldAutohr.getText().isEmpty()&&textFieldBook.getText().isEmpty())
				 {
		 				comboBoxOfBooks.removeAllItems();
		 				panel.removeAll();
		 				panel.setVisible(false);
						scrollPaneMain.setVisible(false);
						panel.updateUI();
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
								panel.updateUI();
				 		 }
				 		 else
				 		 {
				 				comboBoxOfBooks.removeAllItems();
							for(int i=0;i<tempBooks.size();i++)
								comboBoxOfBooks.addItem("Name: "+tempBooks.get(i).getTitle().trim() + " , " +"Author: "+ tempBooks.get(i).getAuthor().trim());
							panel.updateUI();
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
							panel.updateUI();

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
							panel.updateUI();

			 		 }
			 		 else
			 		 {
			 			 if(comboBoxOfBooks.getSize() != null)	comboBoxOfBooks.removeAllItems();
			 			 	for(int i=0;i<tempBooks.size();i++)
			 			 		comboBoxOfBooks.addItem("Name: "+tempBooks.get(i).getTitle().trim() + " , " +"Author: "+ tempBooks.get(i).getAuthor().trim());
			 		 }
			 		} else {JOptionPane.showMessageDialog(screen,"Iligal author field!! ", "Warning",JOptionPane.WARNING_MESSAGE);textFieldAutohr.setText("");}

			 	}
			}
			 	 }
				else
				{	
	 				panel.removeAll();

					 r = new Review();// create review
					 ArrayList<Book> bookKeywordsChoose = new ArrayList<Book> ();
					bookKeywordsChoose=BookController.searchKeywords(textFieldKeyWord.getText(),screen.getClient());
					if(bookKeywordsChoose!=null){
					for(int i=0;i<bookKeywordsChoose.size();i++)
					{
					temp = ReviewController.SearchReviews("reviewID,reviewDate,reviewContent,reviewStatus,bookID", r, ""+ "bookID=\""+bookKeywordsChoose.get(i).getBookID()+"\""+ " && "+"reviewStatus=\""+"1"+"\"", screen.getClient());
					if (temp != null) 
					{
						panel.setVisible(true);
						scrollPaneMain.setVisible(true);
							for(int j=0;j<temp.size();j++)
							{
								panel.add(new SearchReviewPanel(screen,temp.get(j),bookKeywordsChoose.get(i).getTitle()));	
							}
					} 			
					}
					}
					else 
					{
						panel.setVisible(false);
						scrollPaneMain.setVisible(false);
						JOptionPane.showMessageDialog(screen,"no review results !  ", "no results",JOptionPane.QUESTION_MESSAGE);
						textFieldKeyWord.setText("");
						
					}
					panel.updateUI();				
				}
			}
			}); 
		btnSearch.setBounds(636, 109, 89, 30);
		add(btnSearch);

		ImageIcon backIcon =new ImageIcon("Extras/Images/backIcon.png");
		btnBack = new JButton(backIcon);
		btnBack.setBounds(35, 25, 89, 30);
		add(btnBack);
		



	}
}