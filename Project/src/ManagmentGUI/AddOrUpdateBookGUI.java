package ManagmentGUI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Book.book;
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
	public ArrayList<book> books;
	public LoginGUI screen;

	private int ISUpdateOrAdd;

	/**
	 * @wbp.parser.constructor
	 */
	public AddOrUpdateBookGUI(LoginGUI screen ,int ISUpdateOrAdd) {
		super();
		this.ISUpdateOrAdd=ISUpdateOrAdd;
		this.screen=screen;
		initialize();
		JLabel lblEmptyLabelFor = new JLabel("Empty label for answer tkinut");
		lblEmptyLabelFor.setBounds(188, 290, 179, 14);
		add(lblEmptyLabelFor);
	}
	public AddOrUpdateBookGUI(LoginGUI screen ,int ISUpdateOrAdd,ArrayList<book> books) {
		super();
		this.books=books; // get back the book from the search and now upddate 
		this.screen=screen;
		this.ISUpdateOrAdd=ISUpdateOrAdd;
		initialize();
		JLabel lblEmptyLabelFor = new JLabel("Empty label for answer tkinut");
		lblEmptyLabelFor.setBounds(188, 290, 179, 14);
		add(lblEmptyLabelFor);
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
		lblTitle.setBounds(66, 99, 46, 14);
		add(lblTitle);
		
		JLabel lblLanguage = new JLabel("Language : ");
		lblLanguage.setBounds(40, 124, 72, 14);
		add(lblLanguage);
		
		JLabel lblSummary = new JLabel("Summary :");
		lblSummary.setBounds(40, 149, 60, 14);
		add(lblSummary);
		
		JLabel lblContents = new JLabel("Contents : ");
		lblContents.setBounds(40, 174, 60, 14);
		add(lblContents);
		
		JLabel lblKeywords = new JLabel("Keywords : ");
		lblKeywords.setBounds(40, 199, 60, 14);
		add(lblKeywords);
		
		JLabel lblAuthor = new JLabel("Author :");
		lblAuthor.setBounds(50, 227, 46, 14);
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
		
		JButton btnAdd = new JButton();
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				 if(ISUpdateOrAdd==1)//means its add flag page 
				 {
				 	book b = new book(title.getText(),lang.getText(),author.getText(),summary.getText(),true); // create new book	
				 	boolean result=bookController.AddBook(b,screen.getClient()); // return true or false from the controller DB 
				 	if (result==false)
						JOptionPane.showMessageDialog(screen,"Add book process FAILD ! ", "Warning",JOptionPane.WARNING_MESSAGE);
				 	else
					JOptionPane.showMessageDialog(screen,"The book was added successfully to DB !", "done",JOptionPane.INFORMATION_MESSAGE);
					
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

 
