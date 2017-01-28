package ManagmentGUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Book.Book;
import Controller.BookController;
import Extras.Validation;
import MenuGUI.LoginGUI;

import command.FileCommand;
//import client.AddFileUI;
/**
 * The class of build the panel GUI to add or Update Book
 * @param screen 
 * LoginGUI extends JFrame
 * @param ISUpdateOrAdd
 * flag what selected in the Previous panel
 * @author Sagi Entenberg
 * @author Hen Saada
 * 
 */
public class AddOrUpdateBookGUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtFieldTitle;
	private JTextField txtFieldLang;
	private JTextField txtFieldContents;
	private JTextField txtFieldKeyword;
	private JTextField txtFieldAuthor;
	private JTextField txtFieldPrice;
	private JTextArea txtFieldSummary;
	public JButton btnBack;
	private ArrayList<Book> tempBooks;
	private int Bookid;
	private JPanel pann;
	public LoginGUI screen;
	private JPanel Mainpann;
	private File srcfile=null;
	private int ISUpdateOrAdd;

	/**
	 * the class of build the panel GUI to Add or Update Book
	 * @param screen
	 *  LoginGUI extends JFrame
	 * @param ISUpdateOrAdd
	 * flag that set the MODE of the page ,means if we want to use this window to add a new book we will constructor this object with ISUpdateOrAdd= 1
	 * else if want  to constructor object for update MODE we will send ISUpdateOrAdd=0
	 *this specific constructor  method is for Addbook
	 *@param Bookid
	 * get the book for update
	 * @param Mainpann
	 * main panel
	 * @author Sagi Entenberg
	 * @author Hen Saada
	 * @wbp.parser.constructor
	 */
	public AddOrUpdateBookGUI(LoginGUI screen, int ISUpdateOrAdd) {
		super();
		this.ISUpdateOrAdd = ISUpdateOrAdd;
		this.screen = screen;

		pann = this;
		initialize();
	}
	/**
	 * the class of build the panel GUI to Add or Update Book
	 * @param screen
	 *  LoginGUI extends JFrame
	 * @param ISUpdateOrAdd
	 * flag that set the MODE of the page ,means if we want to use this window to add a new book we will constructor this object with ISUpdateOrAdd= 1
	 * else if want  to constructor object for update MODE we will send ISUpdateOrAdd=0
	 * this specific constructor made for the Update MODE , and get bookId to update and then load his details from the DB to the currect fields
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

	/**
	 * This method initialize The window of Add OR UPDATE book at the SAME CLASS,puts the components on the screen and set their functionality
	 * flag that set the MODE of the page ,means if we want to use this window to add a new book we will constructor this object with ISUpdateOrAdd= 1
	 * the user must fill all the Book Fields , by the correct Validation , and choose the file location  of the book to load to the server 
	 * else if want  to constructor object for update MODE we will send ISUpdateOrAdd=0
	 * the method check all input Validation and will not succeed until the user will fill all the Fields Correct.
	 * @author  hen saada
	 * @param null
	 * @return null
	 * 
	 */
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

		txtFieldSummary = new JTextArea();
		txtFieldSummary.setLineWrap(true);
		txtFieldSummary.setBounds(119, 430, 169, 99);
		add(txtFieldSummary);

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

		txtFieldTitle = new JTextField();
		txtFieldTitle.setBounds(138, 130, 121, 30);
		add(txtFieldTitle);
		txtFieldTitle.setColumns(10);

		txtFieldLang = new JTextField();
		txtFieldLang.setBounds(138, 180, 121, 30);
		add(txtFieldLang);
		txtFieldLang.setColumns(10);

		txtFieldContents = new JTextField();
		txtFieldContents.setBounds(138, 230, 121, 30);
		add(txtFieldContents);
		txtFieldContents.setColumns(10);

		txtFieldKeyword = new JTextField();
		txtFieldKeyword.setBounds(138, 280, 121, 30);
		add(txtFieldKeyword);
		txtFieldKeyword.setColumns(10);

		txtFieldAuthor = new JTextField();
		txtFieldAuthor.setBounds(138, 330, 121, 30);
		add(txtFieldAuthor);
		txtFieldAuthor.setColumns(10);

		txtFieldPrice = new JTextField();
		txtFieldPrice.setBounds(138, 380, 121, 30);
		add(txtFieldPrice);
		txtFieldPrice.setColumns(10);

		if (ISUpdateOrAdd == 0) {

			Book tempObject = new Book(); // create new book
			tempBooks = BookController.SearchBook("bookID,title,author,language,summary,keyword,content,price",
					tempObject, "bookID=\"" + Bookid + "\"", screen.getClient());

			txtFieldTitle.setText(tempBooks.get(0).getTitle());

			txtFieldPrice.setText(String.valueOf(tempBooks.get(0).getPrice()));
			txtFieldAuthor.setText(tempBooks.get(0).getAuthor());
			txtFieldSummary.setText(tempBooks.get(0).getSummary());
			String arrKeyword[] = tempBooks.get(0).getKeyword();
			txtFieldKeyword.setText(arrKeyword[0]);
			String arrContent[] = tempBooks.get(0).getKeyword();
			txtFieldContents.setText(arrContent[0]);
			txtFieldLang.setText(tempBooks.get(0).getLanguage());
		}

		JButton btnAdd = new JButton();
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (ISUpdateOrAdd == 1)// means its add flag page
				{
				
	
					String warnings = InputValidation();
					if (txtFieldTitle.getText().isEmpty() || txtFieldLang.getText().isEmpty() || txtFieldAuthor.getText().isEmpty()
							|| txtFieldSummary.getText().isEmpty() || txtFieldContents.getText().isEmpty()
							|| txtFieldKeyword.getText().isEmpty() || txtFieldPrice.getText().isEmpty())
						JOptionPane.showMessageDialog(screen, "please fill all the book fields!! ", "Warning",
								JOptionPane.WARNING_MESSAGE);
					else if (!warnings.equalsIgnoreCase("ERROR :\n"))
						JOptionPane.showMessageDialog(screen, warnings, "Warning", JOptionPane.WARNING_MESSAGE);
					else {
						
						
						Book b = new Book(txtFieldTitle.getText().trim(), txtFieldLang.getText().trim(), txtFieldAuthor.getText().trim(),
								txtFieldSummary.getText().trim(), 1, txtFieldKeyword.getText().trim(), txtFieldContents.getText().trim(),
								Float.parseFloat(txtFieldPrice.getText().trim())); // create new book

						ArrayList<Book> temp = BookController.SearchBook("title,language", b, "title=\""
								+ txtFieldTitle.getText().trim() + "\"" + " && " + "author=\"" + txtFieldAuthor.getText().trim() + "\"",
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
										+ txtFieldTitle.getText().trim() + "\"" + " && " + "author=\"" + txtFieldAuthor.getText().trim() + "\"",
								
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
								
								txtFieldTitle.setText("");
								txtFieldLang.setText("");
								txtFieldAuthor.setText("");
								txtFieldSummary.setText("");
								txtFieldContents.setText("");
								txtFieldKeyword.setText("");
								txtFieldPrice.setText("");
								
								JOptionPane.showMessageDialog(screen, "The book was added successfully to DB !", "done",
										JOptionPane.INFORMATION_MESSAGE);
								
							}
						} else {
							txtFieldTitle.setText("");
							txtFieldLang.setText("");
							txtFieldAuthor.setText("");
							txtFieldSummary.setText("");
							txtFieldContents.setText("");
							txtFieldKeyword.setText("");
							JOptionPane.showMessageDialog(screen,
									"the book is already exist. Try to add another book\n", "Warning",
									JOptionPane.WARNING_MESSAGE);
						}

						
					}
					
				}
				else // its update
				{
					String warnings = InputValidation();
					if (txtFieldTitle.getText().isEmpty() || txtFieldLang.getText().isEmpty() || txtFieldAuthor.getText().isEmpty()
							|| txtFieldSummary.getText().isEmpty() || txtFieldContents.getText().isEmpty()
							|| txtFieldKeyword.getText().isEmpty() || txtFieldPrice.getText().isEmpty())
						JOptionPane.showMessageDialog(screen, "please fill all the book fields!! ", "Warning",
								JOptionPane.WARNING_MESSAGE);
					else if (!warnings.equalsIgnoreCase("ERROR :\n"))
						JOptionPane.showMessageDialog(screen, warnings, "Warning", JOptionPane.WARNING_MESSAGE);
					else {
						Book b = new Book(txtFieldTitle.getText(), txtFieldLang.getText(), txtFieldAuthor.getText(), txtFieldSummary.getText(), 1,
								txtFieldKeyword.getText(), txtFieldContents.getText()); // create new book
						boolean result = BookController.UpdateBook(b, "title=\"" + txtFieldTitle.getText().trim() + "\""
								+ " && " + "author=\"" + txtFieldAuthor.getText().trim() + "\"" + " && " + "language=\""
								+ txtFieldLang.getText().trim() + "\"" + " && " + "summary=\"" + txtFieldSummary.getText().trim() + "\""
								+ " && " + "content=\"" + txtFieldContents.getText().trim() + "\"" + " && " + "summary=\""
								+ txtFieldSummary.getText().trim() + "\"" + " && " + "keyword=\"" + txtFieldKeyword.getText().trim()
								+ "\"" + " && " + "price=\"" + Float.parseFloat(txtFieldPrice.getText().trim()) + "\"",
								"bookID=\"" + Bookid + "\"", screen.getClient()); // return true or false from the controller DB

						if (result == false)
							JOptionPane.showMessageDialog(screen, "Update book process FAILED ! ", "Warning",
									JOptionPane.WARNING_MESSAGE);
						else {
							txtFieldTitle.setText("");
							txtFieldLang.setText("");
							txtFieldAuthor.setText("");
							txtFieldSummary.setText("");
							txtFieldContents.setText("");
							txtFieldKeyword.setText("");
							txtFieldPrice.setText("");
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
		btnBack.setBounds(11, 33, 89, 30);
		add(btnBack);
	}

	/**
	 * This method check validation about all the book fields
	 * @author  hen saada
	 * @param null
	 * @return String include the errors that we collect from all the validation fields
	 * @author Hen Saada
	 */
	public String InputValidation() {
		String warnings = "ERROR :\n";
		if (Validation.regularValidation(txtFieldTitle.getText()) == false)
			warnings += "title field - The following characters are not allowed :  |,%,\\," + "\",',&,=\n";
		if (Validation.TitleValidation(txtFieldTitle.getText(), 20) == false)
			warnings += "title field - Must contain only English letters or numbers\n";

		if (Validation.regularValidation(txtFieldLang.getText()) == false)
			warnings += "language field - The following characters are not allowed :  |,%,\\," + "\",',&,=\n";
		if (Validation.NameValidation(txtFieldLang.getText(), 20) == false)
			warnings += "language field - Must contain only English letters \n";

		if (Validation.regularValidation(txtFieldAuthor.getText()) == false)
			warnings += "author field - The following characters are not allowed :  |,%,\\," + "\",',&,=\n";
		if (Validation.AuthorValidation(txtFieldAuthor.getText(), 20) == false)
			warnings += "author field - Must contain only English letters \n";
		String answer = Validation.PriceValidation(txtFieldPrice.getText());
		if (answer != "")
			warnings += answer;
		if (Validation.regularValidation(txtFieldKeyword.getText()) == false)
			warnings += "keword field - The following characters are not allowed :  |,%,\\," + "\",',&,=\n";
		return warnings;
	}
}
