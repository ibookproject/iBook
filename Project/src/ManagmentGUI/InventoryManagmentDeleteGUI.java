package ManagmentGUI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Book.Book;
import Book.Domain;
import Book.Subject;
import Controller.bookController;
import MenuGUI.LoginGUI;
import client.DBgenericObject;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JComboBox;




public class InventoryManagmentDeleteGUI extends JPanel {

	private static final long serialVersionUID = 1L;

	public JButton btnBack ;
	private ArrayList<Subject>resultSubjects; // array of subjects of some domain
	private ArrayList<Domain> resultDomains; // array of domains
	private JComboBox comboBoxSubject;
	private JComboBox comboBoxBook;
	private LoginGUI screen;
	

	public InventoryManagmentDeleteGUI(LoginGUI screen ) {
		super();
		this.screen=screen;
		initialize();
	}

	private void initialize() {
		this.setSize(850, 625);
		this.setLayout(null);	
		ImageIcon backIcon =new ImageIcon("src/images/backIcon.png"); 
		 btnBack = new JButton(backIcon);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBack.setBounds(11, 33, 89, 23);
		add(btnBack);
		
		JLabel lblDeleteBook = new JLabel("Delete Book");
		lblDeleteBook.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDeleteBook.setBounds(365, 33, 159, 39);
		add(lblDeleteBook);
		
		
		Domain d = new Domain("1");
		 resultDomains = bookController.GetAllDomain(d,screen.getClient());//
		//System.out.println(result);
		
		JLabel lblChooseDomain = new JLabel("Choose Domain :");
		lblChooseDomain.setBounds(335, 120, 116, 23);
		add(lblChooseDomain);
		
		JComboBox comboBoxDomain = new JComboBox();
		JComboBox comboBoxSubject=new JComboBox();
		comboBoxDomain.setBounds(335, 142, 101, 20);
		//comboBoxDomain.addItem("1111");
		for(Domain dd:resultDomains) // adding all the Domain names to the checkbox
			comboBoxDomain.addItem(dd);

		comboBoxDomain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Subject s=new Subject(3,3,"1");  //create empty project
				// select Subject of the specific domain ! 
				
				resultSubjects=bookController.SearchSubjectAtDomain("nameSubject", s,"DomainID="+((Domain) comboBoxDomain.getSelectedItem()).getDomainID(), screen.getClient());
				System.out.println(resultSubjects); // print it at the console ... i cant print it at "subjects" list becuz there is problm
				//resultDomains.clear();// maybe not need .... 	
				if(resultSubjects!=null)
				{
					comboBoxSubject.removeAllItems();
					for(Subject ss:resultSubjects) // adding all the Domain names to the checkbox
						comboBoxSubject.addItem(ss);
				}
				else comboBoxSubject.removeAllItems();
			
				
				
			}
		});
		
		add(comboBoxDomain);
		
		JLabel lblChooseSubject = new JLabel("Choose Subject : ");
		lblChooseSubject.setBounds(335, 173, 95, 14);
		add(lblChooseSubject);
		
	
		
		
		comboBoxSubject.setBounds(335, 190, 103, 20);
		add(comboBoxSubject);
		
		JLabel lblChooseBook = new JLabel("Choose Book :");
		lblChooseBook.setBounds(335, 230, 91, 23);
		add(lblChooseBook);
		
		comboBoxBook = new JComboBox();
		comboBoxBook.setBounds(335, 248, 105, 23);
		add(comboBoxBook);
		

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					int answer=JOptionPane.showConfirmDialog(null, "are you sure you want to delte this book ?","Warning !!", JOptionPane.YES_NO_OPTION);
				System.out.println(answer);
				if(answer==0)
				{
					//means delete ..... 
				}// if it 0 mean no so do nothing . 
			}
		});
		btnDelete.setBounds(335, 303, 101, 30);
		add(btnDelete);
		
	
	
	}
}