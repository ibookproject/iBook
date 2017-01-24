package ManagmentGUI;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Book.Book;
import Controller.UserController;
import Extras.Validation;
import Controller.BookController;
import MenuGUI.LoginGUI;
import client.DBSQLhandler;
//import client.AddFileUI;
import command.DBtranslation;
import command.FileCommand;
import command.insertCommand;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class AddOrUpdateBookGUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField title;
	private JTextField lang;
	private JTextField contents;
	private JTextField keyword;
	private JTextField author;
	private JTextField price;
	private JTextArea summary;
	public JButton btnBack;
	private ArrayList<Book> tempBooks;
	private int Bookid;
	private JPanel pann;
	public LoginGUI screen;
	private JPanel Mainpann;
	private File srcfile=null;
//	private AddFileUI af;
	private int ISUpdateOrAdd;

	/**
	 * The class of build the panel gui to delete or Update Book
	 * @param screen 
	 * LoginGUI extends JFrame
	 * @param ISUpdateOrAdd
	 * flag what selected in the Previous panel
	 * @author Sagi Entenberg
	 * @author Hen Saada
	 * 
	 */
	public AddOrUpdateBookGUI(LoginGUI screen, int ISUpdateOrAdd) {
		super();
		this.ISUpdateOrAdd = ISUpdateOrAdd;
		this.screen = screen;

		pann = this;
		initialize();
	}
	/**
	 * the class of build the panel gui to Add or Update Book
	 * @param screen
	 *  LoginGUI extends JFrame
	 * @param ISUpdateOrAdd
	 * flag what selected in the Previous panel
	 * @param Bookid
	 * get the book for update
	 * @param Mainpann
	 * main panel
	 * @author Sagi Entenberg
	 * @author Hen Saada
	 */
	public AddOrUpdateBookGUI(LoginGUI screen, int ISUpdateOrAdd, int Bookid, JPanel Mainpann) {
		super();
		this.Bookid = Bookid; // get back the book from the search and now upddate
		this.screen = screen;
		this.Mainpann = Mainpann;
		this.ISUpdateOrAdd = ISUpdateOrAdd;
		initialize();
	}

	private void initialize() {

		this.setSize(850, 625);
		this.setLayout(null);
		JLabel lblAddUpdate = new JLabel();
		lblAddUpdate.setHorizontalAlignment(SwingConstants.CENTER);
		if (ISUpdateOrAdd == 1)
			lblAddUpdate.setText("Add book");
		else if (ISUpdateOrAdd == 0)
			lblAddUpdate.setText("Update book");
		lblAddUpdate.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 21));
		lblAddUpdate.setBounds(246, 26, 411, 30);
		add(lblAddUpdate);

		summary = new JTextArea();
		summary.setLineWrap(true);
		summary.setBounds(119, 430, 169, 99);
		add(summary);

		JLabel lblPrice = new JLabel("Price:");
		lblPrice.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPrice.setBounds(26, 380, 62, 30);
		add(lblPrice);
		JLabel lblPleaseFillThe = new JLabel("please fill the fields : ");
		lblPleaseFillThe.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPleaseFillThe.setBounds(119, 89, 200, 20);
		add(lblPleaseFillThe);

		JLabel lblTitle = new JLabel("Title :");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTitle.setBounds(26, 130, 62, 20);
		add(lblTitle);

		JLabel lblLanguage = new JLabel("Language : ");
		lblLanguage.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblLanguage.setBounds(26, 180, 102, 20);
		add(lblLanguage);

		JLabel lblSummary = new JLabel("Summary :");
		lblSummary.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSummary.setBounds(26, 430, 89, 30);
		add(lblSummary);

		JLabel lblContents = new JLabel("Contents : ");
		lblContents.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblContents.setBounds(26, 230, 102, 20);
		add(lblContents);

		JLabel lblKeywords = new JLabel("Keywords : ");
		lblKeywords.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblKeywords.setBounds(26, 280, 102, 20);
		add(lblKeywords);

		JLabel lblAuthor = new JLabel("Author :");
		lblAuthor.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAuthor.setBounds(26, 330, 74, 30);
		add(lblAuthor);

		title = new JTextField();
		title.setBounds(138, 130, 121, 30);
		add(title);
		title.setColumns(10);

		lang = new JTextField();
		lang.setBounds(138, 180, 121, 30);
		add(lang);
		lang.setColumns(10);

		contents = new JTextField();
		contents.setBounds(138, 230, 121, 30);
		add(contents);
		contents.setColumns(10);

		keyword = new JTextField();
		keyword.setBounds(138, 280, 121, 30);
		add(keyword);
		keyword.setColumns(10);

		author = new JTextField();
		author.setBounds(138, 330, 121, 30);
		add(author);
		author.setColumns(10);

		price = new JTextField();
		price.setBounds(138, 380, 121, 30);
		add(price);
		price.setColumns(10);

		if (ISUpdateOrAdd == 0) {

			Book tempObject = new Book(); // create new book
			tempBooks = BookController.SearchBook("bookID,title,author,language,summary,keyword,content,price",
					tempObject, "bookID=\"" + Bookid + "\"", screen.getClient());

			title.setText(tempBooks.get(0).getTitle());

			price.setText(String.valueOf(tempBooks.get(0).getPrice()));
			author.setText(tempBooks.get(0).getAuthor());
			summary.setText(tempBooks.get(0).getSummary());
			String arrKeyword[] = tempBooks.get(0).getKeyword();
			keyword.setText(arrKeyword[0]);
			String arrContent[] = tempBooks.get(0).getKeyword();
			contents.setText(arrContent[0]);
			lang.setText(tempBooks.get(0).getLanguage());
		}

		JButton btnAdd = new JButton();
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (ISUpdateOrAdd == 1)// means its add flag page
				{
				
	
					String warnings = InputValidation();
					if (title.getText().isEmpty() || lang.getText().isEmpty() || author.getText().isEmpty()
							|| summary.getText().isEmpty() || contents.getText().isEmpty()
							|| keyword.getText().isEmpty() || price.getText().isEmpty())
						JOptionPane.showMessageDialog(screen, "please fill all the book fields!! ", "Warning",
								JOptionPane.WARNING_MESSAGE);
					else if (!warnings.equalsIgnoreCase("ERROR :\n"))
						JOptionPane.showMessageDialog(screen, warnings, "Warning", JOptionPane.WARNING_MESSAGE);
					else {
						
						
						Book b = new Book(title.getText().trim(), lang.getText().trim(), author.getText().trim(),
								summary.getText().trim(), 1, keyword.getText().trim(), contents.getText().trim(),
								Float.parseFloat(price.getText().trim())); // create new book

						ArrayList<Book> temp = BookController.SearchBook("title,language", b, "title=\""
								+ title.getText().trim() + "\"" + " && " + "author=\"" + author.getText().trim() + "\"",
								screen.getClient());// call search book method
													// from book controller
						if (temp == null || temp.isEmpty()) {

							boolean result1 = BookController.AddBook(b, screen.getClient()); // return true or false from the controller DB
							if (result1 == false)
								JOptionPane.showMessageDialog(screen, "Add book process FAILED ! ", "Warning",
										JOptionPane.WARNING_MESSAGE);
							else {
								/****************************************************************************************/

								 temp = BookController.SearchBook("bookID", b, "title=\""
										+ title.getText().trim() + "\"" + " && " + "author=\"" + author.getText().trim() + "\"",
								
										screen.getClient());
								 boolean cancel=false;
								 do{
								JFileChooser fs=new JFileChooser(new File("c:\\"));
								fs.setDialogTitle("Upload a Book");
								fs.setBackground(new Color(245, 255, 250));
								int result=fs.showOpenDialog(null);
								
								
								if (result==JFileChooser.APPROVE_OPTION)
								{
									int NewBookID=temp.get(0).getBookID();
									File fileTOSend = new File(fs.getSelectedFile().getAbsolutePath());
									try {
								
										screen.getClient().SendFileToServer(new FileCommand(fileTOSend, NewBookID));
										JOptionPane.showMessageDialog(screen,"Upload in server done! ", "Warning",JOptionPane.INFORMATION_MESSAGE);
										cancel=false;
									}
									catch (IOException e) {
										
										JOptionPane.showMessageDialog(screen,"Cant Upload File !\n "+e.getMessage(), "Warning",JOptionPane.ERROR_MESSAGE);
										cancel=true;
									}

								}
								else
								{
									JOptionPane.showMessageDialog(screen,"You must Upload Book File !\n ", "Warning",JOptionPane.ERROR_MESSAGE);
									cancel=true;
								}
								}while(cancel);
								
								/****************************************************************************************/
								
								title.setText("");
								lang.setText("");
								author.setText("");
								summary.setText("");
								contents.setText("");
								keyword.setText("");
								price.setText("");
								
								JOptionPane.showMessageDialog(screen, "The book was added successfully to DB !", "done",
										JOptionPane.INFORMATION_MESSAGE);
								
							}
						} else {
							title.setText("");
							lang.setText("");
							author.setText("");
							summary.setText("");
							contents.setText("");
							keyword.setText("");
							JOptionPane.showMessageDialog(screen,
									"the book is already exist. Try to add another book\n", "Warning",
									JOptionPane.WARNING_MESSAGE);
						}

						
					}
					
				}
				else // its update
				{
					String warnings = InputValidation();
					if (title.getText().isEmpty() || lang.getText().isEmpty() || author.getText().isEmpty()
							|| summary.getText().isEmpty() || contents.getText().isEmpty()
							|| keyword.getText().isEmpty() || price.getText().isEmpty())
						JOptionPane.showMessageDialog(screen, "please fill all the book fields!! ", "Warning",
								JOptionPane.WARNING_MESSAGE);
					else if (!warnings.equalsIgnoreCase("ERROR :\n"))
						JOptionPane.showMessageDialog(screen, warnings, "Warning", JOptionPane.WARNING_MESSAGE);
					else {
						Book b = new Book(title.getText(), lang.getText(), author.getText(), summary.getText(), 1,
								keyword.getText(), contents.getText()); // create new book
						boolean result = BookController.UpdateBook(b, "title=\"" + title.getText().trim() + "\""
								+ " && " + "author=\"" + author.getText().trim() + "\"" + " && " + "language=\""
								+ lang.getText().trim() + "\"" + " && " + "summary=\"" + summary.getText().trim() + "\""
								+ " && " + "content=\"" + contents.getText().trim() + "\"" + " && " + "summary=\""
								+ summary.getText().trim() + "\"" + " && " + "keyword=\"" + keyword.getText().trim()
								+ "\"" + " && " + "price=\"" + Float.parseFloat(price.getText().trim()) + "\"",
								"bookID=\"" + Bookid + "\"", screen.getClient()); // return true or false from the controller DB

						if (result == false)
							JOptionPane.showMessageDialog(screen, "Update book process FAILED ! ", "Warning",
									JOptionPane.WARNING_MESSAGE);
						else {
							title.setText("");
							lang.setText("");
							author.setText("");
							summary.setText("");
							contents.setText("");
							keyword.setText("");
							price.setText("");
							JOptionPane.showMessageDialog(screen, "The book was Updated successfully to DB !", "done",
									JOptionPane.INFORMATION_MESSAGE);

							InventoryManagmentGUI goback = new InventoryManagmentGUI(screen);
							goback.btnBack.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									screen.setContentPane(Mainpann);
								}
							});
							screen.setContentPane(goback);// send to search book
															// window
						}
					}
				}
			}
		});
		if (ISUpdateOrAdd == 1)
			btnAdd.setText("Add book");
		else if (ISUpdateOrAdd == 0)
			btnAdd.setText("Update book");
		btnAdd.setBounds(372, 531, 161, 30);
		add(btnAdd);
		ImageIcon backIcon = new ImageIcon("Extras/Images/backIcon.png");
		btnBack = new JButton(backIcon);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnBack.setBounds(11, 33, 89, 23);
		add(btnBack);
	}

	public String InputValidation() {
		String warnings = "ERROR :\n";
		if (Validation.regularValidation(title.getText()) == false)
			warnings += "title field - The following characters are not allowed :  |,%,\\," + "\",',&,=\n";
		if (Validation.TitleValidation(title.getText(), 20) == false)
			warnings += "title field - Must contain only English letters or numbers\n";

		if (Validation.regularValidation(lang.getText()) == false)
			warnings += "language field - The following characters are not allowed :  |,%,\\," + "\",',&,=\n";
		if (Validation.NameValidation(lang.getText(), 20) == false)
			warnings += "language field - Must contain only English letters \n";

		if (Validation.regularValidation(author.getText()) == false)
			warnings += "author field - The following characters are not allowed :  |,%,\\," + "\",',&,=\n";
		if (Validation.AuthorValidation(author.getText(), 20) == false)
			warnings += "author field - Must contain only English letters \n";
		String answer = Validation.PriceValidation(price.getText());
		if (answer != "")
			warnings += answer;
		if (Validation.regularValidation(keyword.getText()) == false)
			warnings += "keword field - The following characters are not allowed :  |,%,\\," + "\",',&,=\n";
		return warnings;
	}
}
