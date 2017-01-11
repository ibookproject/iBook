package ManagmentGUI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Book.Book;
import Controller.UserController;
import Controller.bookController;
import MenuGUI.LoginGUI;
import command.DBtranslation;
import command.insertCommand;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;




public class AddOrUpdateBookGUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField title;
	private JTextField lang;
	private JTextField summary;
	private JTextField contents;
	private JTextField keyword;
	private JTextField author;
	public JButton btnBack ;
	private ArrayList<Book> tempBooks;
	private int Bookid;
	private JPanel pann;
	public LoginGUI screen;
	private JPanel Mainpann;

	private int ISUpdateOrAdd;

	/**
	 * @wbp.parser.constructor
	 */
	public AddOrUpdateBookGUI(LoginGUI screen ,int ISUpdateOrAdd) {
		super();
		this.ISUpdateOrAdd=ISUpdateOrAdd;
		this.screen=screen;
		

		pann=this;
		initialize();
	}
	public AddOrUpdateBookGUI(LoginGUI screen ,int ISUpdateOrAdd,int Bookid,JPanel Mainpann) {
		super();
		this.Bookid=Bookid; // get back the book from the search and now upddate 
		this.screen=screen;
		this.Mainpann=Mainpann;
		this.ISUpdateOrAdd=ISUpdateOrAdd;
		initialize();
	}
	
	
	
	private void initialize() {
		
		this.setSize(850, 625);
		this.setLayout(null);	
		JLabel lblAddUpdate = new JLabel();
		if(ISUpdateOrAdd==1)
		 lblAddUpdate.setText("Add book");
		else if(ISUpdateOrAdd==0)
			 lblAddUpdate.setText("Update book");
		lblAddUpdate.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAddUpdate.setBounds(346, 26, 217, 30);
		add(lblAddUpdate);
		
		JLabel lblPleaseFillThe = new JLabel("please fill the fields : ");
		lblPleaseFillThe.setBounds(111, 76, 140, 14);
		add(lblPleaseFillThe);
		
		JLabel lblTitle = new JLabel("Title :");
		lblTitle.setBounds(50, 99, 62, 14);
		add(lblTitle);
		
		JLabel lblLanguage = new JLabel("Language : ");
		lblLanguage.setBounds(26, 124, 86, 14);
		add(lblLanguage);
		
		JLabel lblSummary = new JLabel("Summary :");
		lblSummary.setBounds(26, 149, 74, 14);
		add(lblSummary);
		
		JLabel lblContents = new JLabel("Contents : ");
		lblContents.setBounds(26, 174, 74, 14);
		add(lblContents);
		
		JLabel lblKeywords = new JLabel("Keywords : ");
		lblKeywords.setBounds(26, 199, 74, 14);
		add(lblKeywords);
		
		JLabel lblAuthor = new JLabel("Author :");
		lblAuthor.setBounds(36, 227, 60, 14);
		add(lblAuthor);
		
		title = new JTextField();
		title.setBounds(100, 96, 86, 20);
		add(title);
		title.setColumns(10);
		
		lang = new JTextField();
		lang.setBounds(100, 124, 86, 20);
		add(lang);
		lang.setColumns(10);
		
		summary = new JTextField();
		summary.setBounds(100, 149, 86, 20);
		add(summary);
		summary.setColumns(10);
		
		contents = new JTextField();
		contents.setBounds(100, 174, 86, 20);
		add(contents);
		contents.setColumns(10);
		
		keyword = new JTextField();
		keyword.setBounds(100, 199, 86, 20);
		add(keyword);
		keyword.setColumns(10);
		
		author = new JTextField();
		author.setBounds(100, 224, 86, 20);
		add(author);
		author.setColumns(10);
		
		
		if(ISUpdateOrAdd==0)
		{
			
			
			Book tempObject = new Book(); // create new book
			 tempBooks = bookController.SearchBook("bookID,title,author,language,summary,keyword,content",tempObject, "bookID=\""+Bookid+ "\"" , screen.getClient());
			 //System.out.println(Bookid);
			 
			 title.setText(tempBooks.get(0).getTitle());
			 author.setText(tempBooks.get(0).getAuthor());
			 summary.setText(tempBooks.get(0).getSummary());
			 String arrKeyword[]=tempBooks.get(0).getKeyword();
			 keyword.setText(arrKeyword[0]);
			 String arrContent[]=tempBooks.get(0).getKeyword();
			 contents.setText(arrContent[0]);
			 lang.setText(tempBooks.get(0).getLanguage());
		}
		

		
		JButton btnAdd = new JButton();
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				 if(ISUpdateOrAdd==1)//means its add flag page 
				 {
					 if(title.getText().isEmpty()||lang.getText().isEmpty()||author.getText().isEmpty()||summary.getText().isEmpty()||contents.getText().isEmpty()||keyword.getText().isEmpty())
							JOptionPane.showMessageDialog(screen,"please fill all the book fields!! ", "Warning",JOptionPane.WARNING_MESSAGE);
					 else
					 {
						 //	public Book( String title, String language, String author, String summary, boolean bookEnable,String keyword,String content)

				 	Book b = new Book(title.getText().trim(),lang.getText().trim(),author.getText().trim(),summary.getText().trim(),true,keyword.getText().trim(),contents.getText().trim()); // create new book
				 	
					ArrayList<Book> temp = bookController.SearchBook("title,language",b, "title=\""+title.getText().trim()+ "\"" + " && "+"author=\""+author.getText().trim()+"\"", screen.getClient());//call search book method from book controller
				 	//System.out.println(temp);
					if(temp==null||temp.isEmpty())
					{
						
						boolean result=bookController.AddBook(b,screen.getClient()); // return true or false from the controller DB
					 	if (result==false)
							JOptionPane.showMessageDialog(screen,"Add book process FAILED ! ", "Warning",JOptionPane.WARNING_MESSAGE);
					 	else
					 	{
					 		title.setText("");lang.setText("");author.setText("");summary.setText(""); contents.setText(""); keyword.setText("");
					 		JOptionPane.showMessageDialog(screen,"The book was added successfully to DB !", "done",JOptionPane.INFORMATION_MESSAGE);
					 	}
					}
				
					else
						{
				 		title.setText("");lang.setText("");author.setText("");summary.setText(""); contents.setText(""); keyword.setText("");
							JOptionPane.showMessageDialog(screen,"the book is already exist. Try to add another book\n", "Warning",JOptionPane.WARNING_MESSAGE);
						}
					 }
				 }
				 else // its update
				 {
					 	Book b = new Book(title.getText(),lang.getText(),author.getText(),summary.getText(),true,keyword.getText(),contents.getText()); // create new book
						//boolean result=bookController.AddBook(b,screen.getClient()); // return true or false from the controller DB
						boolean result=bookController.UpdateBook(b, "title=\""+title.getText().trim()+ "\"" + " && "+"author=\""+author.getText().trim()+"\""+" && "+"language=\""+lang.getText().trim()+"\""+" && "+"summary=\""+summary.getText().trim()+"\""+" && "+"content=\""+contents.getText().trim()+"\""+" && "+"summary=\""+summary.getText().trim()+"\""+" && "+"keyword=\""+keyword.getText().trim()+"\"", "bookID=\""+Bookid+ "\"", screen.getClient()); // return true or false from the controller DB

					 	if (result==false)
							JOptionPane.showMessageDialog(screen,"Update book process FAILED ! ", "Warning",JOptionPane.WARNING_MESSAGE);
					 	else
					 	{
					 		title.setText("");lang.setText("");author.setText("");summary.setText(""); contents.setText(""); keyword.setText("");
					 		JOptionPane.showMessageDialog(screen,"The book was Updated successfully to DB !", "done",JOptionPane.INFORMATION_MESSAGE);
							
							
					 		InventoryManagmentGUI goback=new InventoryManagmentGUI(screen); 
							goback.btnBack.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									screen.setContentPane(Mainpann);
								}
							});
							screen.setContentPane(goback);//send to search book window
					 	}
			 
					 // String title, String language, String author, String summary, boolean bookEnable,String keyword,String content
					// Book b = new Book(tempBooks.get(0).getTitle(),tempBooks.get(0).getAuthor(),tempBooks.get(0).getSummary(),tempBooks.get(0),true,tempBooks.get(0).getKeyword()); // create new book
					 
					 
				 }
			 }
		});
		if(ISUpdateOrAdd==1)
			btnAdd.setText("Add book");
			else if(ISUpdateOrAdd==0)
				btnAdd.setText("Update book");
		btnAdd.setBounds(377, 281, 120, 23);
		add(btnAdd);
		


		
//String title, String language, String author, String summary, boolean bookEnable
		
		ImageIcon backIcon =new ImageIcon("src/images/backIcon.png"); 
		 btnBack = new JButton(backIcon);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnBack.setBounds(11, 33, 89, 23);
		add(btnBack);
	
	}
}

 
