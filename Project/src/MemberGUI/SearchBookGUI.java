package MemberGUI;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import client.DBgenericObject;
import command.searchCommand;
import command.showAllCommand;
import Book.Book;
import Book.Domain;
import Book.SubjectToBook;
import Controller.FormatController;
import Controller.BookController;
import ManagmentGUI.RemovePartReviewGUI;
import MenuGUI.LoginGUI;
import Role.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
/**
 * @author  Coral Carmel
 * The class responsible to the search book of the user
 * 
 */
public class SearchBookGUI extends JPanel {

	private static final long serialVersionUID = 1L;
	public JButton btnBack;
	private LoginGUI screen;
	private JPanel pann;
	private ImageIcon backIcon;
	private JLabel lblSearchBook;
	private JCheckBox chckbxTitle;
	private JCheckBox chckbxAuthor;
	private JCheckBox chckbxLanguage;
	private JCheckBox chckbxSummary;
	private JCheckBox chckbxContents ;
	private JTextField textTitle;
	private JTextField textAuthor;
	private JCheckBox chckbxKeywords;
	private JCheckBox chckbxDomain;
	private JComboBox<Domain> comboBoxDomain;
	private JTextField textFieldLanguage;
	private JTextField textFieldKeywords;
	private JTextField textFieldSummary;
	private JTextField textFieldContents;
	private JButton btnSearch;
	private int flag=0;
	private int flagIfNotFill;
	private int flagNotFoundBook;

	public SearchBookGUI(LoginGUI screen) {
		super();
		this.screen = screen;
		pann = this;
		flagNotFoundBook=0;
		initialize();
		
	}
	/**
	 * @author  Coral Carmeli
	 * The method initialize the window of search
	 * 
	 */
	private void initialize() {

		this.setSize(850, 625);
		this.setLayout(null);
		String domains[] = new String[3];

		for (int i = 0; i < 3; i++) {
			domains[i] = "Domain " + i;
		}

		backIcon = new ImageIcon("src/images/backIcon.png");
		btnBack = new JButton(backIcon);// declaration of back button
		btnBack.setBounds(39, 52, 89, 23);
		add(btnBack);
		
		lblSearchBook = new JLabel("Search Book");
		lblSearchBook.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSearchBook.setBounds(355, 49, 175, 22);
		add(lblSearchBook);

		chckbxTitle = new JCheckBox("Title");
		chckbxTitle.setFont(new Font("Tahoma", Font.BOLD, 15));
		chckbxTitle.setBounds(257, 150, 111, 25);
		add(chckbxTitle);

		chckbxAuthor = new JCheckBox("Author");
		chckbxAuthor.setFont(new Font("Tahoma", Font.BOLD, 15));
		chckbxAuthor.setBounds(257, 200, 111, 25);
		add(chckbxAuthor);

		chckbxLanguage = new JCheckBox("Language");
		chckbxLanguage.setFont(new Font("Tahoma", Font.BOLD, 15));
		chckbxLanguage.setBounds(257, 250, 111, 25);
		add(chckbxLanguage);

		chckbxSummary = new JCheckBox("Summary");
		chckbxSummary.setFont(new Font("Tahoma", Font.BOLD, 15));
		chckbxSummary.setBounds(257, 300, 111, 25);
		add(chckbxSummary);

		chckbxContents = new JCheckBox("Contents");
		chckbxContents.setFont(new Font("Tahoma", Font.BOLD, 15));
		chckbxContents.setBounds(257, 350, 111, 25);
		add(chckbxContents);

		chckbxKeywords = new JCheckBox("Keywords");
		chckbxKeywords.setFont(new Font("Tahoma", Font.BOLD, 15));
		chckbxKeywords.setBounds(257, 400, 111, 25);
		add(chckbxKeywords);

		chckbxDomain = new JCheckBox("Domain");
		chckbxDomain.setFont(new Font("Tahoma", Font.BOLD, 15));
		chckbxDomain.setBounds(257, 450, 111, 25);
		add(chckbxDomain);
		comboBoxDomain = new JComboBox<Domain>();
		Domain d=new Domain();
		ArrayList<Domain> domainList=FormatController.GetAllDomain(d, screen.getClient());
		
		for (Domain d1:domainList) {
			comboBoxDomain.addItem(d1);
		}
		comboBoxDomain.setBounds(393, 452, 116, 22);
		add(comboBoxDomain);

		textFieldSummary = new JTextField();
		textFieldSummary.setBounds(393, 302, 116, 22);
		add(textFieldSummary);
		textFieldSummary.setColumns(10);

		textFieldContents = new JTextField();
		textFieldContents.setColumns(10);
		textFieldContents.setBounds(393, 352, 116, 22);
		add(textFieldContents);

		textFieldKeywords = new JTextField();
		textFieldKeywords.setColumns(10);
		textFieldKeywords.setBounds(393, 402, 116, 22);
		add(textFieldKeywords);

		textTitle = new JTextField();
		textTitle.setBounds(393, 152, 116, 22);
		add(textTitle);
		textTitle.setColumns(10);

		textAuthor = new JTextField();
		textAuthor.setBounds(393, 202, 116, 22);
		add(textAuthor);
		textAuthor.setColumns(10);

		textFieldLanguage = new JTextField();
		textFieldLanguage.setBounds(393, 252, 116, 22);
		add(textFieldLanguage);
		textFieldLanguage.setColumns(10);
		
		/**
		 * @author  Coral Carmeli
		 * 
		 * The button "search" responsible on the functionality of the method
		 * The logic of the search is in this listener
		 */
		ArrayList<Book> bookss=new ArrayList<Book>();
		btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
// //////////////////////button back to Search book GUI// /////////////////////////////////////////////
				SearchBook sb = new SearchBook(screen,bookss);
				sb.btnBack.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//SearchBookGUI pan=new SearchBookGUI(screen);
						screen.setContentPane(pann);
					}
// //////////////////////button back to Search book GUI/////////////////////////////////////////////
				});
				Book b=new Book();
				flagIfNotFill=0;
				flagNotFoundBook=0;
				//int flag
				String condition = "";//initialize the condition
				if (chckbxTitle.isSelected())
					if(textTitle.getText().isEmpty())
						showMessageDialogErrorEmptyField();
					else
						condition +="title LIKE '%"+textTitle.getText().trim() +"%'";
				if (chckbxLanguage.isSelected()) {
					if(textFieldLanguage.getText().isEmpty())
						showMessageDialogErrorEmptyField();
					else
					{
						if (!condition.equals(""))
							condition += " && ";
						condition += "language LIKE '%" + textFieldLanguage.getText().trim() + "%'";//add "language" to condition
					}
				}
				if (chckbxAuthor.isSelected()) {
					if(textAuthor.getText().isEmpty())
						showMessageDialogErrorEmptyField();
					else
					{
						if (!condition.equals(""))
							condition += " && ";
						condition += "author LIKE '%" + textAuthor.getText().trim() + "%'";//add "author" to condition
					}
				}
				if (chckbxSummary.isSelected()) {
					if(textFieldSummary.getText().isEmpty())
						showMessageDialogErrorEmptyField();
					else
					{
						if (!condition.equals(""))
							condition += " && ";
						condition += "summary LIKE '%" + textFieldSummary.getText() + "%'";//add "summary" to condition
					}
				}
				if (chckbxContents.isSelected()) {
					if(textFieldContents.getText().isEmpty())
						showMessageDialogErrorEmptyField();
					else
					{
						if (!condition.equals(""))
							condition += " && ";
						condition += "content LIKE '%" +textFieldContents.getText() + "%'";//add "content" to condition
					}
				}
	
				ArrayList<Book> bookKeywordsChoose=new ArrayList<Book>();
				ArrayList<Book> bookKeywordsDomains=new ArrayList<Book>();
				ArrayList<Book> bookDomainList=new 	ArrayList<Book>();
				
				if(chckbxDomain.isSelected())
				{	
					SubjectToBook s=new SubjectToBook();
					Domain d=(Domain)comboBoxDomain.getSelectedItem();
					ArrayList<SubjectToBook> subjectDomainList=FormatController.SearchBookInSubjectToBookAccordingDomain("bookID", s, "DomainID=\""+d.getDomainID()+"\"", screen.getClient());
					if(subjectDomainList!=null)
					{
						flag=1;
						Book b1=new Book();
						
						ArrayList<Book> allBooks=BookController.SearchBook("bookID,title,language,author,summary,content,keyword,price", b1, "bookEnable=\""+1+"\"", screen.getClient());
						
						for(Book b2:allBooks)
						{
							for(SubjectToBook s1:subjectDomainList)
							{
								if(b2.getBookID()==s1.getBookID())
									bookDomainList.add(b2);
							}
						}
					}	
					else
						{
						JOptionPane.showMessageDialog(screen,"Theres no books in the chosen domain", "Warning",JOptionPane.WARNING_MESSAGE);
						flagNotFoundBook=1;
						}
						
				}
				if (chckbxKeywords.isSelected())
				{
					if(textFieldKeywords.getText().isEmpty())
						showMessageDialogErrorEmptyField();
					else
					{
						Book b2=new Book();
						ArrayList<Book> bookKeywords=BookController.SearchBook("bookID,title,language,author,summary,content,keyword,price", b2, "bookEnable=\""+1+"\"", screen.getClient());
						if(bookKeywords!=null)
						{
							flag=2;
							for(Book b1:bookKeywords)
							{
								for(int i=0;i<b1.getKeyword().length;i++)
									if(textFieldKeywords.getText().equals(b1.getKeyword()[i]))
										bookKeywordsChoose.add(b1);	
							}
						}
						else
						{
							JOptionPane.showMessageDialog(screen,"Theres no books in the chosen kewords", "Warning",JOptionPane.WARNING_MESSAGE);
							flagNotFoundBook=1;
							}
					
							
					}
				}
		
			
				
				if(chckbxKeywords.isSelected()&&chckbxDomain.isSelected())		//AND between both ArrayList!
				{
					
					if((bookDomainList!=null)&&(bookKeywordsChoose!=null))
					{
						flag=3;
						for(Book b1:bookDomainList)
						{
							for(int i=0;i<bookKeywordsChoose.size();i++)
								if(bookKeywordsChoose.get(i).getBookID()==b1.getBookID())
									bookKeywordsDomains.add(b1);
						}
					}
					else
					{
						JOptionPane.showMessageDialog(screen,"Theres no books in the chosen domain or kewords", "Warning",JOptionPane.WARNING_MESSAGE);
						flagNotFoundBook=1;
						}
					
				}
				
				if (!condition.equals("")||flag!=0) //if have some condition
				{
					ArrayList<Book> temp1 =new ArrayList<Book>();
					if(condition.equals("")&&flag==1)
						temp1=bookDomainList;
					else
						if(condition.equals("")&&flag==2)
							temp1=bookKeywordsChoose;
						else
							if(condition.equals("")&&flag==3)
								temp1=bookKeywordsDomains;
							
									
								else{
					
					ArrayList<Book> temp = BookController.SearchBook("bookID,title,language,author,summary,content,keyword,price",b,condition, screen.getClient());//call search book method from book controller
					if (temp != null) 
					{
						if(flag==1)//Domain
						{
							if(bookDomainList!=null)
								for(Book b1:bookDomainList)
								{
									for(int i=0;i<temp.size();i++)
										if(temp.get(i).getBookID()==b1.getBookID())
											temp1.add(b1);
								}
							else
							{
								flagNotFoundBook=1;
								JOptionPane.showMessageDialog(screen,"Not found any book result\n", "Warning",JOptionPane.WARNING_MESSAGE);
							}
								
						}
						else
							if(flag==2)//keywords
							{
								if(bookKeywordsChoose!=null)
									for(Book b1:bookKeywordsChoose)
									{
										for(int i=0;i<temp.size();i++)
											if(temp.get(i).getBookID()==b1.getBookID())
												temp1.add(b1);
									}
								else
								{
									JOptionPane.showMessageDialog(screen,"Not found any book result\n", "Warning",JOptionPane.WARNING_MESSAGE);
									flagNotFoundBook=1;
								}
							}
							else
								if(flag==3)//both domain and keywords
								{
									if(bookKeywordsDomains!=null)
										for(Book b1:bookKeywordsDomains)
										{
											for(int i=0;i<temp.size();i++)
												if(temp.get(i).getBookID()==b1.getBookID())
													temp1.add(b1);
										}
									else
									{
										JOptionPane.showMessageDialog(screen,"Not found any book result\n", "Warning",JOptionPane.WARNING_MESSAGE);
										flagNotFoundBook=1;
									}
								}
								else
									temp1=temp;
					} 
					else
							{
							JOptionPane.showMessageDialog(screen,"Not found any book result\n", "Warning",JOptionPane.WARNING_MESSAGE);
							flagNotFoundBook=1;
							}
					}
					
					if(flagNotFoundBook==0)
					{
						sb.setList(temp1);
						screen.setContentPane(sb);
					}
				} else //(condition)=="")empty
					if(flagIfNotFill==0)
						JOptionPane.showMessageDialog(screen,"Nothing has selected", "Warning",JOptionPane.WARNING_MESSAGE);
				
				
				flagIfNotFill=0;
				flagNotFoundBook=0;
				flag=0;
			}
		});
		btnSearch.setBounds(326, 537, 97, 25);
		add(btnSearch);
		
		JLabel lblSearchBookGif = new JLabel("");
		lblSearchBookGif.setIcon(new ImageIcon("C:\\Users\\coral\\Downloads\\ibook\\Project\\Search50.png"));
		lblSearchBookGif.setBounds(480, 27, 69, 62);
		add(lblSearchBookGif);
	}
	public void showMessageDialogErrorEmptyField()
	{
		if(flagIfNotFill==0)
		{
				JOptionPane.showMessageDialog(screen,"You must fill the chosen empty fields", "Warning",JOptionPane.WARNING_MESSAGE);
			flagIfNotFill=1;
		}
	}
}
