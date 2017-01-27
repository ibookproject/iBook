package ManagmentGUI;


import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;

import Book.Book;
import Book.Domain;
import Book.Review;
import Book.Subject;
import Book.SubjectToBook;
import Controller.FormatController;
import Extras.Validation;
import Controller.BookController;
import MenuGUI.LoginGUI;
import Panels.FormatCheckBoxBooklistPanel;
import Panels.SearchReviewPanel;
import command.joinCommand;
import command.joinObject;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
/**
 * The class take care of the format manager  functionality- add new domain and subject , and attaching a numbers of book to some subject that is still not belong.
 * 
 * @author  hen saada
 */

public class FormatManagmentGUI extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField DomainTextField;
	private JTextField SubjectTextField;
	public JButton btnBack;
	private JTable table;
	public LoginGUI screen;
	private ArrayList<Domain> resultDomains;
	private ArrayList<Subject> resultSubjects;
	private JComboBox SubjectBox;
	private JScrollPane scrollPaneMain;
	public static JPanel panel;
    private ArrayList<SubjectToBook> specificBooksWtihSelectedSubject;
    private ArrayList<Book> AllBookList;

	public FormatManagmentGUI(LoginGUI screen) {
		super();		
		this.screen=screen;
		initialize();
	}
	
	/**
	 * This method initialize The window of Format manager,puts the components on the screen and set their functionality
	 * the functionality is to add a new subject to domain , add new domain , choose from exist domain subject and attach books to the subject that is no belong
	 * When the window first loads, loads any domain already exists already and according to its load are subject through ComboBox's so its start work like a automate machine
	 * When the user chooses to add a new subject or field, or choose a subject from an existing domain, 
	 * the window of the list of books loaded automatic his discretion is only the book THAT NOT ATTACHED YET TO THE CHOOSEN SUBJECT,
	 *  so that we get maximum efficiency and user comfort
	 *  also the method check validation about wrong letter at input of new subject and domain 
	 *  and also check if the domain or the subject is already exist at the system.
	 * @author  hen saada
	 * @param null
	 * @return null
	 * 
	 */
	private void initialize() {	
		this.setLayout(null);	
		this.setSize(850, 600);
		
		/////////////////////
		scrollPaneMain = new JScrollPane();
		scrollPaneMain.getVerticalScrollBar().setUnitIncrement(16);
		scrollPaneMain.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneMain.setAutoscrolls(true);
		scrollPaneMain.setBounds(10, 130, 296, 309);
		scrollPaneMain.setVisible(false);
		add(scrollPaneMain);

		panel = new JPanel();
		panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		panel.setAutoscrolls(true);
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		scrollPaneMain.setViewportView(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		////////////////////

		JLabel lblFormatManagment = new JLabel("Format managment");
		lblFormatManagment.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 21));
		lblFormatManagment.setBounds(322, 40, 234, 32);
		add(lblFormatManagment);
		
		JLabel lblChooseBook = new JLabel("Choose book for ataching subject :");
		lblChooseBook.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblChooseBook.setBounds(10, 99, 296, 20);
		add(lblChooseBook);
		
		JList<?> list = new JList<Object>();
		
		list.setBounds(154, 191, -93, -63);
		add(list);
		
		JLabel lblChooseDomain = new JLabel("Choose Domain : ");
		lblChooseDomain.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblChooseDomain.setForeground(Color.BLACK);
		lblChooseDomain.setBounds(328, 137, 138, 20);
		add(lblChooseDomain);
		
		JComboBox DomainBox = new JComboBox();
		DomainBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		SubjectBox = new JComboBox();
		SubjectBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Domain d = new Domain();	
		 resultDomains = FormatController.GetAllDomain(d,screen.getClient());//
		 	if(resultDomains!=null)
			for(Domain dd:resultDomains) // adding all the Domain names to the checkbox
				DomainBox.addItem(dd);				
			
			// this is for the first time that we get in the format managar , if the domain list is not null..->
			//..-> we will do " selected item " for to show all the subject list for the first time ! 
			if(resultDomains!=null)
			{
				DomainBox.setSelectedIndex(0);
				Subject s=new Subject();
				resultSubjects=FormatController.SearchSubjectAtDomain("nameSubject", s,"domainID="+((Domain) DomainBox.getSelectedItem()).getDomainID(), screen.getClient());
				if(resultSubjects!=null&&!resultSubjects.isEmpty())
				{
				for(Subject ddd:resultSubjects) // adding all the Domain names to the checkbox
					SubjectBox.addItem(ddd);
				SubjectBox.setSelectedIndex(0);
				//getting all the book at the DB
				Book tempObject = new Book(); // create new book
				AllBookList=BookController.SearchBook("bookID,title,language,author",tempObject,"bookEnable=\""+1+"\"", screen.getClient());

				
				//getting all the books that the selected subject is attach to them.
				SubjectToBook btemp=new SubjectToBook();														//"domainName=\""+DomainTextField.getText().trim()+ "\""
				specificBooksWtihSelectedSubject=FormatController.SearchSubjectAtSubjectToBook("nameSubject,domainID,bookID", btemp, "nameSubject=\"" +resultSubjects.get(0).getNameSubject()+"\"", screen.getClient());
				//make haluka !
				if(specificBooksWtihSelectedSubject!=null)
				for(int i=0;i<AllBookList.size();i++)
				{
					for(int j=0;j<specificBooksWtihSelectedSubject.size();j++)
					{
						if(specificBooksWtihSelectedSubject.get(j).getBookID()==AllBookList.get(i).getBookID())
						{
							AllBookList.remove(i);
						//	if(i==0)
							i--;
							break;
						}	 
					}
				}

				panel.removeAll();
				panel.setVisible(true);
				scrollPaneMain.setVisible(true);
				for(Book b:AllBookList)
					panel.add(new FormatCheckBoxBooklistPanel(screen,b,((Domain) DomainBox.getSelectedItem()).getDomainID()));
				}
			}
			// this is for the first time that we get in the format managar , if the domain list is not null..
			//we will do " selected item " for to show all the subject list for the first time ! 
		
		DomainBox.addActionListener(new ActionListener() {// domain combobox lisener
			public void actionPerformed(ActionEvent e) {
				Subject s=new Subject();  //create empty project
				if(DomainBox.getItemAt(0)!=null)
				{	
				resultSubjects=FormatController.SearchSubjectAtDomain("nameSubject", s,"domainID="+((Domain) DomainBox.getSelectedItem()).getDomainID(), screen.getClient());
				//System.out.println(resultSubjects); // print it at the console ... i cant print it at "subjects" list becuz there is problm
				if(resultSubjects!=null) // if there is no result 
				{
					SubjectBox.removeAllItems(); // first clear all the subject result from the checkbox 
				for(Subject ddd:resultSubjects) // adding all the Domain names to the checkbox
					SubjectBox.addItem(ddd);
					SubjectBox.setSelectedIndex(0);
				}
				else
				{
					SubjectBox.removeAllItems();
					panel.removeAll();
	 				panel.setVisible(false);
					scrollPaneMain.setVisible(false);
				}
			
				}
				else SubjectBox.removeAllItems();
			}
		});
		DomainBox.setBounds(342, 162, 75, 30);
		add(DomainBox);
		SubjectBox.setBounds(344, 284, 75, 30);
		add(SubjectBox);
		JLabel lblChooseSubjectAt = new JLabel("Choose subject at Domain");
		lblChooseSubjectAt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblChooseSubjectAt.setBounds(316, 265, 150, 14);
		add(lblChooseSubjectAt);
		JLabel lblNewDomain = new JLabel("new domain : ");
		lblNewDomain.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewDomain.setBounds(559, 161, 83, 20);
		add(lblNewDomain);
		
		DomainTextField = new JTextField();
		DomainTextField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		DomainTextField.setBounds(641, 155, 86, 30);
		add(DomainTextField);
		DomainTextField.setColumns(10);
		
		
		JButton btnAddNewDomain = new JButton("Add"); // adding new domain
		btnAddNewDomain.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnAddNewDomain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String s=DomainTextField.getText().trim();
				
				if(!s.isEmpty())
					{
					if(Validation.NameValidation(DomainTextField.getText().trim(),20)==true&&Validation.regularValidation(DomainTextField.getText().trim())==true)
					{
					Domain d = new Domain(DomainTextField.getText().trim()); //   	
					ArrayList<Domain> temp = FormatController.SearchDomain("DomainName",d, "domainName=\""+DomainTextField.getText().trim()+ "\"" ,screen.getClient());
					if(temp==null)
					{
			 		boolean result=FormatController.AddDomain(d,screen.getClient()); // return true or false from the controller DB 
			 		if (result==false){
			 			JOptionPane.showMessageDialog(screen,"Add domain process FAILD ! ", "Warning",JOptionPane.WARNING_MESSAGE);
			 			resultDomains = FormatController.GetAllDomain(d,screen.getClient());//
			 		}
			 		else
			 		{
			 			DomainBox.removeAllItems(); // first remove all the item becuz we add , and then update it again 
			 			resultDomains = FormatController.GetAllDomain(d,screen.getClient());
			 			for(int i=0;i<resultDomains.size();i++)
			 				DomainBox.addItem(resultDomains.get(i)); 
			 			DomainTextField.setText("");
			 			DomainBox.setSelectedIndex(DomainBox.getItemCount()-1);  // some trick to put the selected becuz the selcted with object dont work
			 			JOptionPane.showMessageDialog(screen,"The domain was added successfully to DB !", "done",JOptionPane.INFORMATION_MESSAGE);
			 			
			 		}
			 		} else {JOptionPane.showMessageDialog(screen,"domain is already EXSIT ! ", "Warning",JOptionPane.WARNING_MESSAGE);DomainTextField.setText("");}
					}
					else {JOptionPane.showMessageDialog(screen,"Iligel domain field  ! ", "Warning",JOptionPane.WARNING_MESSAGE);DomainTextField.setText("");}
				}
				else JOptionPane.showMessageDialog(screen,"domain field is empty ! ", "Warning",JOptionPane.WARNING_MESSAGE);
			}
		});
		
		btnAddNewDomain.setBounds(737, 155, 67, 30);
		add(btnAddNewDomain);
		

		JLabel lblNewSubjectAt = new JLabel("new subject at CHOSSEN domain : ");
		lblNewSubjectAt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewSubjectAt.setForeground(Color.BLACK);
		lblNewSubjectAt.setBounds(434, 287, 197, 20);
		add(lblNewSubjectAt);
		
		SubjectTextField = new JTextField();
		SubjectTextField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		SubjectTextField.setBounds(641, 284, 86, 30);
		add(SubjectTextField);
		SubjectTextField.setColumns(10);
		
		JButton btnAdd = new JButton("Add"); // adding new subject
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnAdd.addActionListener(new ActionListener() {
			@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent e) {
				
				String s=SubjectTextField.getText().trim();
				if(!s.isEmpty())
				{
					if(Validation.NameValidation(SubjectTextField.getText().trim(),20)==true&&Validation.regularValidation(SubjectTextField.getText().trim())==true)
					{
					int id=((Domain) DomainBox.getSelectedItem()).getDomainID();
				Subject sub = new Subject(id,s); // create new Subject	
				ArrayList<Subject> temp = FormatController.SearchSubject("domainID,nameSubject",sub, "nameSubject=\""+s+ "\"" ,screen.getClient());				
				if(temp==null)
				{
		 		boolean result=FormatController.AddSubject(sub,screen.getClient()); // return true or false from the controller DB 
		 		if (result==false)
		 			JOptionPane.showMessageDialog(screen,"Add Subject process FAILD ! ", "Warning",JOptionPane.WARNING_MESSAGE);
		 		else
		 		{
		 			String str=DomainTextField.getText().trim();
		 			SubjectBox.addItem(sub);
		 			SubjectBox.setSelectedItem(sub);
		 			SubjectTextField.setText("");
		 			JOptionPane.showMessageDialog(screen,"The Subject was added successfully to DB !", "done",JOptionPane.INFORMATION_MESSAGE);
		 			resultSubjects=FormatController.SearchSubjectAtDomain("nameSubject", sub,"DomainID="+((Domain) DomainBox.getSelectedItem()).getDomainID(), screen.getClient());
		 		}
				} else {JOptionPane.showMessageDialog(screen,"Subject  is already EXSIT ! ", "Warning",JOptionPane.WARNING_MESSAGE);SubjectTextField.setText("");}
				}
					else {JOptionPane.showMessageDialog(screen,"Iligel subject field  ! ", "Warning",JOptionPane.WARNING_MESSAGE);SubjectTextField.setText("");}
				}
					
				else JOptionPane.showMessageDialog(screen,"Subject field is empty ! ", "Warning",JOptionPane.WARNING_MESSAGE);
			
		}
		
	});
		
		
		SubjectBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			//getting all the book at the DB
			Book tempObject = new Book(); // create new book
			AllBookList=BookController.SearchBook("bookID,title,language,author",tempObject,"bookEnable=\""+1+"\"", screen.getClient());
			
			//getting all the books that the selected subject is attach to them.
				if(SubjectBox.getItemCount()!=0)
				{
					SubjectToBook btemp=new SubjectToBook();													
					specificBooksWtihSelectedSubject=FormatController.SearchSubjectAtSubjectToBook("nameSubject,domainID,bookID", btemp, "nameSubject=\"" +((Subject)SubjectBox.getSelectedItem()).getNameSubject()+"\"", screen.getClient());

					//////Query division///////
					if(specificBooksWtihSelectedSubject!=null)
					for(int i=0;i<AllBookList.size();i++)
					{
						for(int j=0;j<specificBooksWtihSelectedSubject.size();j++)
						{
							if(specificBooksWtihSelectedSubject.get(j).getBookID()==AllBookList.get(i).getBookID())
							{
								AllBookList.remove(i);
								i--;
								break;
							}	 
						}
					}
						if(AllBookList!=null)
						{
							panel.removeAll();
							panel.updateUI();
							panel.setVisible(true);
							scrollPaneMain.setVisible(true);
							for(Book b:AllBookList)
								panel.add(new FormatCheckBoxBooklistPanel(screen,b,((Domain) DomainBox.getSelectedItem()).getDomainID()));
						}
						else JOptionPane.showMessageDialog(screen,"All books at the System is already Attached to the chossen subject", "Warning",JOptionPane.WARNING_MESSAGE);
				}
				else
				{
	 				panel.removeAll();
	 				panel.setVisible(false);
					scrollPaneMain.setVisible(false);
				}
			
			}
		});
			
		btnAdd.setBounds(737, 284, 67, 30);
		add(btnAdd);
		
		JButton btnAtachBook = new JButton("attach book to subject");
		btnAtachBook.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnAtachBook.addActionListener(new ActionListener() { //attach Button Lisner  !!!!!!!!!!!!!!
			public void actionPerformed(ActionEvent arg0) {
						int flag=0;
				ArrayList<Integer> tempBooksId = new 	ArrayList<Integer> ();
					if(panel.getComponentCount()!=0)
					{
						for(int i=0;i<panel.getComponentCount();i++)
						{
							if((((FormatCheckBoxBooklistPanel)panel.getComponent(i)).chckbxNewCheckBox.isSelected())==true)
							{
								tempBooksId.add(((FormatCheckBoxBooklistPanel)panel.getComponent(i)).book.getBookID());
								flag=1;
								panel.remove(i);
									i--;
							}
							panel.updateUI();
						}
						if(flag==1)
						{
					SubjectToBook s=new SubjectToBook();
					for(int i=0;i<tempBooksId.size();i++)
						FormatController.AddBookIdDomainIdSubjectNameTOSubjectToBookTable(new SubjectToBook(tempBooksId.get(i),((Domain) DomainBox.getSelectedItem()).getDomainID(),((Subject)SubjectBox.getSelectedItem()).getNameSubject()), screen.getClient());
		 			JOptionPane.showMessageDialog(screen,"The book's attached successfully to the Choosen Subject !", "done",JOptionPane.INFORMATION_MESSAGE);
						}				
						else JOptionPane.showMessageDialog(screen,"you must to choose at list 1 book to attach !", "Warning",JOptionPane.WARNING_MESSAGE);

					}
					else JOptionPane.showMessageDialog(screen,"no Chossen Subject & any book's", "Warning",JOptionPane.WARNING_MESSAGE);
			}
		});
		btnAtachBook.setBounds(313, 522, 215, 32);
		add(btnAtachBook);
	
		ImageIcon backIcon =new ImageIcon("Extras/Images/backIcon.png");
		btnBack = new JButton(backIcon);
		btnBack.setBounds(35, 25, 67, 20);
		add(btnBack);
		
		table = new JTable();
		table.setBackground(SystemColor.inactiveCaptionBorder);
		table.setBounds(316, 130, 527, 314);
		add(table);
	
	}
}