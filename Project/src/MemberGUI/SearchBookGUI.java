package MemberGUI;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JScrollBar;
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
import Controller.bookController;
import ManagmentGUI.RemovePartReviewGUI;
import MenuGUI.LoginGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

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
	private JCheckBox chckbxFormat;
	private JComboBox<Domain> comboBoxDomain;
	private JComboBox<String> comboBoxFormat ;
	private JTextField textFieldLanguage;
	private JTextField textFieldKeywords;
	private JTextField textFieldSummary;
	private JTextField textFieldContents;
	private JButton btnSearch;
	private ArrayList<Book> bookss;
	private ArrayList<SubjectToBook> subjectDomainList;
	private ArrayList<Book> bookDomainList;
	private int flag=0;

	public SearchBookGUI(LoginGUI screen) {
		super();
		this.screen = screen;
		pann = this;
		initialize();
	}

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
		lblSearchBook.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSearchBook.setBounds(355, 49, 175, 22);
		add(lblSearchBook);

		chckbxTitle = new JCheckBox("Title");
		chckbxTitle.setFont(new Font("Tahoma", Font.PLAIN, 15));
		chckbxTitle.setBounds(257, 150, 111, 25);
		add(chckbxTitle);

		chckbxAuthor = new JCheckBox("Author");
		chckbxAuthor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		chckbxAuthor.setBounds(257, 200, 111, 25);
		add(chckbxAuthor);

		chckbxLanguage = new JCheckBox("Language");
		chckbxLanguage.setFont(new Font("Tahoma", Font.PLAIN, 15));
		chckbxLanguage.setBounds(257, 250, 111, 25);
		add(chckbxLanguage);

		chckbxSummary = new JCheckBox("Summary");
		chckbxSummary.setFont(new Font("Tahoma", Font.PLAIN, 15));
		chckbxSummary.setBounds(257, 300, 111, 25);
		add(chckbxSummary);

		chckbxContents = new JCheckBox("Contents");
		chckbxContents.setFont(new Font("Tahoma", Font.PLAIN, 15));
		chckbxContents.setBounds(257, 350, 111, 25);
		add(chckbxContents);

		chckbxKeywords = new JCheckBox("Keywords");
		chckbxKeywords.setFont(new Font("Tahoma", Font.PLAIN, 15));
		chckbxKeywords.setBounds(257, 400, 111, 25);
		add(chckbxKeywords);

		chckbxDomain = new JCheckBox("Domain");
		chckbxDomain.setFont(new Font("Tahoma", Font.PLAIN, 15));
		chckbxDomain.setBounds(257, 450, 111, 25);
		add(chckbxDomain);

		chckbxFormat = new JCheckBox("Format");
		chckbxFormat.setFont(new Font("Tahoma", Font.PLAIN, 15));
		chckbxFormat.setBounds(257, 500, 111, 25);
		add(chckbxFormat);

		comboBoxDomain = new JComboBox<Domain>();
		//comboBoxDomain.addItem(" ");
		//comboBoxDomain.setSelectedIndex(0);
		Domain d=new Domain();
		ArrayList<Domain> domainList=FormatController.GetAllDomain(d, screen.getClient());
		
		for (Domain d1:domainList) {
			comboBoxDomain.addItem(d1);
		}
		comboBoxDomain.setBounds(393, 452, 116, 22);
		add(comboBoxDomain);

		//comboBoxDomain.getSelectedItem();
		comboBoxFormat = new JComboBox<String>();
		comboBoxFormat.addItem(" ");
		comboBoxFormat.addItem("PDF");
		comboBoxFormat.addItem("DOC");
		comboBoxFormat.addItem("FB2");

		comboBoxFormat.setBounds(393, 502, 116, 22);
		add(comboBoxFormat);

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

		bookss=new ArrayList<Book>();
		btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
// //////////////////////button back to Search book GUI// /////////////////////////////////////////////
				SearchBook sb = new SearchBook(screen,bookss);
				sb.btnBack.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						screen.setContentPane(pann);
					}
// //////////////////////button back to Search book GUI/////////////////////////////////////////////
				});
				Book b = new Book(textTitle.getText(), textFieldLanguage.getText(),
						textAuthor.getText(), textFieldSummary.getText(), true,textFieldKeywords.getText(),textFieldContents.getText());//create book from text fields
				String condition = "";//initialize the condition
				if (chckbxTitle.isSelected())
					condition += "title=\"" + b.getTitle() + "\"";//add "title" to condition
				if (chckbxLanguage.isSelected()) {
					if (!condition.equals(""))
						condition += " && ";
					condition += "language=\"" + b.getLanguage() + "\"";//add "language" to condition
				}
				if (chckbxAuthor.isSelected()) {
					if (!condition.equals(""))
						condition += " && ";
					condition += "author=\"" + b.getAuthor() + "\"";//add "author" to condition
				}
				if (chckbxSummary.isSelected()) {
					if (!condition.equals(""))
						condition += " && ";
					condition += "summary=\"" + b.getSummary() + "\"";//add "summary" to condition
				}
			
				ArrayList<Book> bookContentsChoose=new ArrayList<Book>();
				ArrayList<Book> bookKeywordsChoose=new ArrayList<Book>();
				ArrayList<Book> bookKeywordsContents=new ArrayList<Book>();

				ArrayList<Book> bookDomainList=new 	ArrayList<Book>();
				
				if (chckbxContents.isSelected())
				{
					flag=1;
					Book b2=new Book();
					ArrayList<Book> bookContents=bookController.SearchBook("bookID,title,language,author,summary,content,keyword", b2, "bookEnable=\""+1+"\"", screen.getClient());
					
					for(Book b1:bookContents)
					{
						for(int i=0;i<b1.getContent().length;i++)
							if(textFieldContents.getText().equals(b1.getContent()[i]))
								bookContentsChoose.add(b1);	
					}
				}
				
				if (chckbxKeywords.isSelected())
				{
					flag=2;
					Book b2=new Book();
					ArrayList<Book> bookKeywords=bookController.SearchBook("bookID,title,language,author,summary,content,keyword", b2, "bookEnable=\""+1+"\"", screen.getClient());
					
					for(Book b1:bookKeywords)
					{
						for(int i=0;i<b1.getKeyword().length;i++)
							if(textFieldKeywords.getText().equals(b1.getKeyword()[i]))
								bookKeywordsChoose.add(b1);	
					}
				}
		
				if(chckbxKeywords.isSelected()&&chckbxContents.isSelected())		//AND between both ArrayList!
				{
					flag=3;
					if((bookContentsChoose!=null)&&(bookKeywordsChoose!=null))
					{
						for(Book b1:bookContentsChoose)
						{
							for(int i=0;i<bookKeywordsChoose.size();i++)
								if(bookKeywordsChoose.get(i).getBookID()==b1.getBookID())
									bookKeywordsContents.add(b1);
						}
					}
				
				}
				if(chckbxDomain.isSelected())
				{	
					SubjectToBook s=new SubjectToBook();
					Domain d=(Domain)comboBoxDomain.getSelectedItem();
					ArrayList<SubjectToBook> subjectDomainList=FormatController.SearchBookInSubjectToBookAccordingDomain("bookID", s, "DomainID=\""+d.getDomainID()+"\"", screen.getClient());
					if(subjectDomainList!=null)
					{
						Book b1=new Book();
						
						ArrayList<Book> allBooks=bookController.SearchBook("bookID,title,language,author,summary,content,keyword", b1, "bookEnable=\""+1+"\"", screen.getClient());
						
						
						for(Book b2:allBooks)
							
						{
							for(SubjectToBook s1:subjectDomainList)
							{
								if(b2.getBookID()==s1.getBookID())
									{
									bookDomainList.add(b2);
									flag=4;
									}
							
							}
						}
					}	
					else
						JOptionPane.showMessageDialog(screen,"Theres no books in the chosen domain", "Warning",JOptionPane.WARNING_MESSAGE);
						
				}
				
				if (!condition.equals("")||flag!=0) 
				{//if have some condition
					ArrayList<Book> temp1 =new ArrayList<Book>();
					if(condition.equals("")&&flag==1)
						temp1=bookContentsChoose;
					else
						if(condition.equals("")&&flag==2)
							temp1=bookKeywordsChoose;
						else
							if(condition.equals("")&&flag==3)
								temp1=bookKeywordsContents;
							else
								if(condition.equals("")&&flag==4)
									temp1=bookDomainList;
									
								else{
					
					ArrayList<Book> temp = bookController.SearchBook("bookID,title,language,author,summary,content,keyword",b,condition, screen.getClient());//call search book method from book controller
					if (temp != null) 
					{
						
						if(flag==1)//contents
						{
							for(Book b1:bookContentsChoose)
							{
								for(int i=0;i<temp.size();i++)
									if(temp.get(i).getBookID()==b1.getBookID())
										temp1.add(b1);
							}
						}
						else
							if(flag==2)//keywords
							{
								for(Book b1:bookKeywordsChoose)
								{
									for(int i=0;i<temp.size();i++)
										if(temp.get(i).getBookID()==b1.getBookID())
											temp1.add(b1);
								}
							}
							else
								if(flag==3)//both
								{
									for(Book b1:bookKeywordsContents)
									{
										for(int i=0;i<temp.size();i++)
											if(temp.get(i).getBookID()==b1.getBookID())
												temp1.add(b1);
									}
								}
								else
									temp1=temp;
					} 
					else //
						JOptionPane.showMessageDialog(screen,"not found any book result\n", "Warning",
								JOptionPane.WARNING_MESSAGE);
							}
					sb.setList(temp1);
					screen.setContentPane(sb);
				} else //(condition)=="")empty
					JOptionPane.showMessageDialog(screen,"Nothing has selected", "Warning",
							JOptionPane.WARNING_MESSAGE);
			}
		});
		btnSearch.setBounds(326, 537, 97, 25);
		add(btnSearch);
	}
}
