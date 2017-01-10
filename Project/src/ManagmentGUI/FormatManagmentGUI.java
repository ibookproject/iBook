package ManagmentGUI;


import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JTable;
import java.awt.SystemColor;
import javax.swing.UIManager;

import Book.Book;
import Book.Domain;
import Book.Subject;
import Controller.FormatController;
import Controller.bookController;
import MenuGUI.LoginGUI;
import command.joinCommand;
import command.joinObject;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;




public class FormatManagmentGUI extends JPanel {


	private static final long serialVersionUID = 1L;
	private JTextField DomainTextField;
	private JTextField SubjectTextField;
	public JButton btnBack;
	private JTable table;
	private JTable table_1;
	public LoginGUI screen;
	private ArrayList<Domain> resultDomains;
	private ArrayList<Subject> resultSubjects;
	private ArrayList<Book> resultBook;
	private JComboBox SubjectBox;
	private JComboBox DomainAttachcomboBox;
    private JComboBox SubjectAttachcomboBox;
    private JComboBox bookComboBox;

	public FormatManagmentGUI(LoginGUI screen) {
		super();		
		this.screen=screen;
		initialize();
	}

	
	private void initialize() {
		
		this.setLayout(null);	
		this.setSize(850, 600);

		
		 DomainAttachcomboBox = new JComboBox(); // for attaching
		 SubjectAttachcomboBox = new JComboBox();// for attaching
		 bookComboBox = new JComboBox();//for attaching
		 
		JLabel lblFormatManagment = new JLabel("Format managment");
		lblFormatManagment.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblFormatManagment.setBounds(322, 33, 154, 39);
		add(lblFormatManagment);
		
		JLabel lblChooseBook = new JLabel("Choose book for ataching subject :");
		lblChooseBook.setBounds(32, 90, 197, 14);
		add(lblChooseBook);
		
		JList<?> list = new JList<Object>();
		
		list.setBounds(154, 191, -93, -63);
		add(list);
		
		JLabel lblChooseDomain = new JLabel("Choose Domain : ");
		lblChooseDomain.setForeground(Color.RED);
		lblChooseDomain.setBounds(251, 90, 114, 14);
		add(lblChooseDomain);
		
		JComboBox DomainBox = new JComboBox();
		SubjectBox = new JComboBox();
		Domain d = new Domain("1");
		
		 resultDomains = FormatController.GetAllDomain(d,screen.getClient());//
			for(Domain dd:resultDomains){ // adding all the Domain names to the checkbox
				DomainBox.addItem(dd);
				DomainAttachcomboBox.addItem(dd);
			}
			
			// this is for the first time that we get in the format managar , if the domain list is not null..->
			//..-> we will do " selected item " for to show all the subject list for the first time ! 
			if(resultDomains!=null)
			{
				DomainBox.setSelectedIndex(0);
				Subject s=new Subject(3,"1");  //create empty project
				resultSubjects=FormatController.SearchSubjectAtDomain("nameSubject", s,"domainID="+((Domain) DomainBox.getSelectedItem()).getDomainID(), screen.getClient());
				if(resultSubjects!=null&&!resultSubjects.isEmpty())
				for(Subject ddd:resultSubjects) // adding all the Domain names to the checkbox
					SubjectBox.addItem(ddd);
			}
			// this is for the first time that we get in the format managar , if the domain list is not null..
			//we will do " selected item " for to show all the subject list for the first time ! 
		
		DomainBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Subject s=new Subject(3,"1");  //create empty project
				if(DomainBox.getItemAt(0)!=null)
				{	
				resultSubjects=FormatController.SearchSubjectAtDomain("nameSubject", s,"domainID="+((Domain) DomainBox.getSelectedItem()).getDomainID(), screen.getClient());
				System.out.println(resultSubjects); // print it at the console ... i cant print it at "subjects" list becuz there is problm
				if(resultSubjects!=null) // if there is no result 
				{
					SubjectBox.removeAllItems(); // first clear all the subject result from the checkbox 
				for(Subject ddd:resultSubjects) // adding all the Domain names to the checkbox
					SubjectBox.addItem(ddd);
				}
				else SubjectBox.removeAllItems();
			}
			}
		});
		DomainBox.setBounds(265, 115, 75, 20);
		add(DomainBox);
		
	
		SubjectBox.setBounds(267, 237, 75, 20);
		add(SubjectBox);
		
		JLabel lblChooseSubjectAt = new JLabel("Choose subject at Domain");
		lblChooseSubjectAt.setBounds(239, 218, 150, 14);
		

	
		 DomainAttachcomboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Subject s=new Subject(3,"1");  //create empty project
				if(DomainAttachcomboBox.getItemAt(0)!=null)
				{	
				resultSubjects=FormatController.SearchSubjectAtDomain("nameSubject", s,"domainID="+((Domain) DomainAttachcomboBox.getSelectedItem()).getDomainID(), screen.getClient());
				System.out.println(resultSubjects); // print it at the console ... i cant print it at "subjects" list becuz there is problm
				if(resultSubjects!=null) // if there is no result 
				{
					SubjectAttachcomboBox.removeAllItems(); // first clear all the subject result from the checkbox 
				for(Subject ddd:resultSubjects) // adding all the Domain names to the checkbox
					SubjectAttachcomboBox.addItem(ddd);
				}
				else SubjectAttachcomboBox.removeAllItems();
			}
			}
		});
		 DomainAttachcomboBox.setBounds(71, 137, 101, 20);
		add(DomainAttachcomboBox);
		
		JLabel lblChooseSubject = new JLabel("Choose Subject : ");
		lblChooseSubject.setBounds(71, 168, 95, 14);
		add(lblChooseSubject);
		/*
		 SubjectAttachcomboBox.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent e) {
			 		Subject s=new Subject(3,"1");  //create empty project
					if(DomainAttachcomboBox.getItemAt(0)!=null)
					{	
					//resultSubjects=FormatController.SearchSubjectAtDomain("nameSubject", s,"domainID="+((Domain) DomainAttachcomboBox.getSelectedItem()).getDomainID(), screen.getClient());
						joinObject<Domain, Subject>[] temp= new joinObject<Domain, DBtranslation>[1];
						resultBook=screen.getClient().joinSearchInDB(new joinCommand<Domain,Subject>(selectSentence, tablesToJoin, whereSentence) );
						System.out.println(resultSubjects); // print it at the console ... i cant print it at "subjects" list becuz there is problm
					if(resultSubjects!=null) // if there is no result 
					{
						SubjectAttachcomboBox.removeAllItems(); // first clear all the subject result from the checkbox 
					for(Subject ddd:resultSubjects) // adding all the Domain names to the checkbox
						SubjectAttachcomboBox.addItem(ddd);
					}
					else SubjectAttachcomboBox.removeAllItems();
				}
			 	}
			 });
			 */
		SubjectAttachcomboBox.setBounds(71, 185, 103, 20);
		add(SubjectAttachcomboBox);
		
		
		JLabel lblChooseBook1 = new JLabel("Choose Book :");
		lblChooseBook1.setBounds(71, 225, 91, 23);
		add(lblChooseBook1);
		
		
		bookComboBox.setBounds(71, 243, 105, 23);
		add(bookComboBox);
		
		add(lblChooseSubjectAt);
		
		JLabel lblNewDomain = new JLabel("new domain : ");
		lblNewDomain.setBounds(482, 114, 83, 14);
		add(lblNewDomain);
		
		DomainTextField = new JTextField();
		DomainTextField.setBounds(564, 108, 86, 20);
		add(DomainTextField);
		DomainTextField.setColumns(10);
		
		
		JButton btnAddNewDomain = new JButton("Add"); // adding new domain
		btnAddNewDomain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String s=DomainTextField.getText();
				
				if(!s.isEmpty())
					{
					Domain d = new Domain(DomainTextField.getText()); //   	
					ArrayList<Domain> temp = FormatController.SearchDomain("DomainName",d, "domainName=\""+DomainTextField.getText()+ "\"" ,screen.getClient());
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
			 		} else {JOptionPane.showMessageDialog(screen,"format is already EXSIT ! ", "Warning",JOptionPane.WARNING_MESSAGE);DomainTextField.setText("");}
				}
				else JOptionPane.showMessageDialog(screen,"format field is empty ! ", "Warning",JOptionPane.WARNING_MESSAGE);
			}
		});
		
		btnAddNewDomain.setBounds(660, 108, 67, 20);
		add(btnAddNewDomain);
		

		
		JLabel lblNewSubjectAt = new JLabel("new subject at CHOSSEN domain : ");
		lblNewSubjectAt.setForeground(Color.RED);
		lblNewSubjectAt.setBounds(357, 240, 197, 14);
		add(lblNewSubjectAt);
		
		SubjectTextField = new JTextField();
		SubjectTextField.setBounds(564, 237, 86, 20);
		add(SubjectTextField);
		SubjectTextField.setColumns(10);
		
		JButton btnAdd = new JButton("Add"); // adding new subject
		btnAdd.addActionListener(new ActionListener() {
			@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent e) {
				
				String s=SubjectTextField.getText();
				if(!s.isEmpty())
				{
					////////////////////////////////////
					int id=((Domain) DomainBox.getSelectedItem()).getDomainID();/// here is the problem
					//////////////////////////////////////
				Subject sub = new Subject(id,s); // create new Subject	
				ArrayList<Subject> temp = FormatController.SearchSubject("bookID,domainID,nameSubject",sub, "nameSubject=\""+s+ "\"" ,screen.getClient());
				System.out.println("///"+temp+"//");
				
				if(temp==null)
				{
		 		boolean result=FormatController.AddSubject(sub,screen.getClient()); // return true or false from the controller DB 
		 		if (result==false)
		 			JOptionPane.showMessageDialog(screen,"Add Subject process FAILD ! ", "Warning",JOptionPane.WARNING_MESSAGE);
		 		else
		 		{
		 			String str=DomainTextField.getText();
		 			SubjectBox.addItem(sub);
		 			SubjectBox.setSelectedItem(sub);
		 			SubjectTextField.setText("");
		 			JOptionPane.showMessageDialog(screen,"The Subject was added successfully to DB !", "done",JOptionPane.INFORMATION_MESSAGE);
		 			resultSubjects=FormatController.SearchSubjectAtDomain("nameSubject", sub,"DomainID="+((Domain) DomainBox.getSelectedItem()).getDomainID(), screen.getClient());
		 		}
				} else {JOptionPane.showMessageDialog(screen,"Subject  is already EXSIT ! ", "Warning",JOptionPane.WARNING_MESSAGE);SubjectTextField.setText("");}
				}
				else JOptionPane.showMessageDialog(screen,"Subject field is empty ! ", "Warning",JOptionPane.WARNING_MESSAGE);
			
		}
		
	});
			
		btnAdd.setBounds(660, 237, 67, 21);
		add(btnAdd);
		
		JButton btnAtachBook = new JButton("atach book to subject");
		btnAtachBook.setBounds(311, 440, 165, 32);
		add(btnAtachBook);
		
		
		ImageIcon backIcon =new ImageIcon("src/images/backIcon.png");
		btnBack = new JButton(backIcon);
		btnBack.setBounds(84, 44, 67, 20);
		add(btnBack);
		
		JLabel lblDbAnswer = new JLabel("DB answer");
		lblDbAnswer.setBounds(670, 129, 75, 14);
		add(lblDbAnswer);
		
		JLabel lblDbAnswer_1 = new JLabel("DB answer");
		lblDbAnswer_1.setBounds(670, 257, 67, 14);
		add(lblDbAnswer_1);
		
		table = new JTable();
		table.setBackground(SystemColor.inactiveCaptionBorder);
		table.setBounds(239, 83, 527, 262);
		add(table);
		
		table_1 = new JTable();
		table_1.setBackground(SystemColor.inactiveCaptionBorder);
		table_1.setBounds(10, 83, 221, 262);
		add(table_1);
		
		JLabel label = new JLabel("Choose Subject : ");
		label.setBounds(71, 115, 95, 14);
		add(label);
		
		JLabel label_1 = new JLabel("New label");
		label_1.setBounds(69, 114, 46, 14);
		add(label_1);
		
		JLabel lblChooseDomain_1 = new JLabel("Choose Domain :");
		lblChooseDomain_1.setBounds(69, 115, 46, 14);
		add(lblChooseDomain_1);
	
	}
}