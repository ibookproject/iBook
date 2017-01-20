package ManagmentGUI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Book.Book;
import Controller.UserController;
import Controller.BookController;
import MenuGUI.LoginGUI;
import Panels.Validation;
import command.DBtranslation;
import command.insertCommand;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTextArea;

public class AddOrUpdateBookGUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField title;
	private JTextField lang;
	private JTextField contents;
	private JTextField keyword;
	private JTextField author;
	private JTextField price;
	private JTextArea summary;
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
		
		 summary = new JTextArea();
		 summary.setLineWrap(true);
		summary.setBounds(119, 306, 169, 69);
		add(summary);
		
		JLabel lblPrice = new JLabel("Price:");
		lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPrice.setBounds(26, 265, 62, 30);
		add(lblPrice);
		JLabel lblPleaseFillThe = new JLabel("please fill the fields : ");
		lblPleaseFillThe.setBounds(119, 69, 140, 14);
		add(lblPleaseFillThe);
		
		JLabel lblTitle = new JLabel("Title :");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTitle.setBounds(26, 94, 62, 14);
		add(lblTitle);
		
		JLabel lblLanguage = new JLabel("Language : ");
		lblLanguage.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLanguage.setBounds(26, 124, 86, 14);
		add(lblLanguage);
		
		JLabel lblSummary = new JLabel("Summary :");
		lblSummary.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSummary.setBounds(26, 321, 84, 30);
		add(lblSummary);
		
		JLabel lblContents = new JLabel("Contents : ");
		lblContents.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblContents.setBounds(26, 155, 102, 30);
		add(lblContents);
		
		JLabel lblKeywords = new JLabel("Keywords : ");
		lblKeywords.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblKeywords.setBounds(26, 196, 102, 20);
		add(lblKeywords);
		
		JLabel lblAuthor = new JLabel("Author :");
		lblAuthor.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAuthor.setBounds(26, 240, 74, 14);
		add(lblAuthor);
		
		title = new JTextField();
		title.setBounds(138, 93, 121, 20);
		add(title);
		title.setColumns(10);
		
		lang = new JTextField();
		lang.setBounds(138, 123, 121, 20);
		add(lang);
		lang.setColumns(10);
		
		contents = new JTextField();
		contents.setBounds(138, 162, 121, 20);
		add(contents);
		contents.setColumns(10);
		
		keyword = new JTextField();
		keyword.setBounds(138, 198, 121, 20);
		add(keyword);
		keyword.setColumns(10);
		
		author = new JTextField();
		author.setBounds(141, 239, 118, 20);
		add(author);
		author.setColumns(10);
		
		price = new JTextField();
		price.setBounds(141, 275, 118, 20);
		add(price);
		price.setColumns(10);
		
		
		if(ISUpdateOrAdd==0)
		{
			
			Book tempObject = new Book(); // create new book
			 tempBooks = BookController.SearchBook("bookID,title,author,language,summary,keyword,content,price",tempObject, "bookID=\""+Bookid+ "\"" , screen.getClient());
			 
			 title.setText(tempBooks.get(0).getTitle());

			 price.setText(String.valueOf(tempBooks.get(0).getPrice()));
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
					 String warnings = "ERROR :\n";
					  if(Validation.regularValidation(title.getText())==false)
							warnings += "title field - The following characters are not allowed :  |,%,\\," + "\",',&,=\n";
					  if(Validation.TitleValidation(title.getText(),20)==false)
							warnings += "title field - Must contain only English letters or numbers\n";

					  if(Validation.regularValidation(lang.getText())==false)
							warnings += "language field - The following characters are not allowed :  |,%,\\," + "\",',&,=\n";
					  if(Validation.NameValidation(lang.getText(), 20)==false)
							warnings += "language field - Must contain only English letters \n";
					  
					  if(Validation.regularValidation(author.getText())==false)
							warnings += "author field - The following characters are not allowed :  |,%,\\," + "\",',&,=\n";
					  if(Validation.AuthorValidation(author.getText(), 20)==false)
							warnings += "author field - Must contain only English letters \n";

					  if(Validation.regularValidation(price.getText())==false)
							warnings += "price field - The following characters are not allowed :  |,%,\\," + "\",',&,=\n";
					String answer= Validation.PriceValidation(price.getText());
					if(answer!="")
						warnings +=answer;
					  
					/*
					  int flag=0,cnt=0;
					  for(int i=0;i<price.getText().length();i++)
						  if(price.getText().charAt(i)=='.')
						  {
							  if(++cnt==2)
							  {
									warnings += "price field - Must contain more then 1 '.'  \n";
									flag=1;
									break;
							  }
						  }
					  if(flag==0)
					  for(int i=0;i<price.getText().length();i++)
						  if((price.getText().charAt(i)>'A'&&price.getText().charAt(i)<'Z')||((price.getText().charAt(i)>'a'&&price.getText().charAt(i)<'z'))&&(price.getText().charAt(i)!='.'))
						  {
							warnings += "price field - Must contain only numbers letters \n";
							flag=1;
							break;
						  }
					  if(flag==0)
					  for(int i=0;i<price.getText().length();i++)
						  if(price.getText().charAt(i)=='.'&&(i==0||i==price.getText().length()-1))
						  {
									warnings += "price field - Must contain only Ligal Number \n";
									break;
						  }
					  flag=0;
					  */
					  
					  if(Validation.regularValidation(keyword.getText())==false)
							warnings += "keword field - The following characters are not allowed :  |,%,\\," + "\",',&,=\n";
						 
					 if(title.getText().isEmpty()||lang.getText().isEmpty()||author.getText().isEmpty()||summary.getText().isEmpty()||contents.getText().isEmpty()||keyword.getText().isEmpty()||price.getText().isEmpty())
							JOptionPane.showMessageDialog(screen,"please fill all the book fields!! ", "Warning",JOptionPane.WARNING_MESSAGE);
					 else if(!warnings.equalsIgnoreCase("ERROR :\n"))
							JOptionPane.showMessageDialog(screen, warnings, "Warning", JOptionPane.WARNING_MESSAGE);
					 else
					 {
				 	Book b = new Book(title.getText().trim(),lang.getText().trim(),author.getText().trim(),summary.getText().trim(),1,keyword.getText().trim(),contents.getText().trim(),Float.parseFloat(price.getText().trim())); // create new book
				 	
					ArrayList<Book> temp = BookController.SearchBook("title,language",b, "title=\""+title.getText().trim()+ "\"" + " && "+"author=\""+author.getText().trim()+"\"", screen.getClient());//call search book method from book controller
				 	//System.out.println(temp);
					if(temp==null||temp.isEmpty())
					{
						
						boolean result=BookController.AddBook(b,screen.getClient()); // return true or false from the controller DB
					 	if (result==false)
							JOptionPane.showMessageDialog(screen,"Add book process FAILED ! ", "Warning",JOptionPane.WARNING_MESSAGE);
					 	else
					 	{
					 		title.setText("");lang.setText("");author.setText("");summary.setText(""); contents.setText(""); keyword.setText(""); price.setText("");
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
					 String warnings = "ERROR :\n";
					  if(Validation.regularValidation(title.getText())==false)
							warnings += "title field - The following characters are not allowed :  |,%,\\," + "\",',&,=\n";
					  if(Validation.TitleValidation(title.getText(),20)==false)
							warnings += "title field - Must contain only English letters or numbers\n";

					  if(Validation.regularValidation(lang.getText())==false)
							warnings += "language field - The following characters are not allowed :  |,%,\\," + "\",',&,=\n";
					  if(Validation.NameValidation(lang.getText(), 20)==false)
							warnings += "language field - Must contain only English letters \n";
					  
					  if(Validation.regularValidation(author.getText())==false)
							warnings += "author field - The following characters are not allowed :  |,%,\\," + "\",',&,=\n";
					  if(Validation.AuthorValidation(author.getText(), 20)==false)
							warnings += "author field - Must contain only English letters \n";

					  if(Validation.regularValidation(price.getText())==false)
							warnings += "price field - The following characters are not allowed :  |,%,\\," + "\",',&,=\n";
						String answer= Validation.PriceValidation(price.getText());
						if(answer!="")
							warnings +=answer;
					  if(Validation.regularValidation(keyword.getText())==false)
							warnings += "keword field - The following characters are not allowed :  |,%,\\," + "\",',&,=\n";
						 
					 if(title.getText().isEmpty()||lang.getText().isEmpty()||author.getText().isEmpty()||summary.getText().isEmpty()||contents.getText().isEmpty()||keyword.getText().isEmpty()||price.getText().isEmpty())
							JOptionPane.showMessageDialog(screen,"please fill all the book fields!! ", "Warning",JOptionPane.WARNING_MESSAGE);
					 else if(!warnings.equalsIgnoreCase("ERROR :\n"))
							JOptionPane.showMessageDialog(screen, warnings, "Warning", JOptionPane.WARNING_MESSAGE);
					 else
					 {
					 	Book b = new Book(title.getText(),lang.getText(),author.getText(),summary.getText(),1,keyword.getText(),contents.getText()); // create new book
						boolean result=BookController.UpdateBook(b, "title=\""+title.getText().trim()+ "\"" + " && "+"author=\""+author.getText().trim()+"\""+" && "+"language=\""+lang.getText().trim()+"\""+" && "+"summary=\""+summary.getText().trim()+"\""+" && "+"content=\""+contents.getText().trim()+"\""+" && "+"summary=\""+summary.getText().trim()+"\""+" && "+"keyword=\""+keyword.getText().trim()+"\""+" && "+"price=\""+Float.parseFloat(price.getText().trim())+"\"", "bookID=\""+Bookid+ "\"", screen.getClient()); // return true or false from the controller DB

					 	if (result==false)
							JOptionPane.showMessageDialog(screen,"Update book process FAILED ! ", "Warning",JOptionPane.WARNING_MESSAGE);
					 	else
					 	{
					 		title.setText("");lang.setText("");author.setText("");summary.setText(""); contents.setText(""); keyword.setText(""); price.setText("");
					 		JOptionPane.showMessageDialog(screen,"The book was Updated successfully to DB !", "done",JOptionPane.INFORMATION_MESSAGE);
							
							
					 		InventoryManagmentGUI goback=new InventoryManagmentGUI(screen); 
							goback.btnBack.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									screen.setContentPane(Mainpann);
								}
							});
							screen.setContentPane(goback);//send to search book window
					 	}
					 }
				 }
			 }
		});
		if(ISUpdateOrAdd==1)
			btnAdd.setText("Add book");
			else if(ISUpdateOrAdd==0)
				btnAdd.setText("Update book");
		btnAdd.setBounds(374, 436, 120, 23);
		add(btnAdd);	
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


