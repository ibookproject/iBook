package ManagmentGUI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Book.book;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;




public class AddOrUpdateBookGUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	public JButton btnBack ;
	public ArrayList<book> books;

	private int ISUpdateOrAdd;


	public AddOrUpdateBookGUI(JFrame screen ,int ISUpdateOrAdd) {
		super();
		this.ISUpdateOrAdd=ISUpdateOrAdd;
		initialize();
		JLabel lblEmptyLabelFor = new JLabel("Empty label for answer tkinut");
		lblEmptyLabelFor.setBounds(188, 290, 179, 14);
		add(lblEmptyLabelFor);
	}
	public AddOrUpdateBookGUI(JFrame screen ,int ISUpdateOrAdd,ArrayList<book> books) {
		super();
		this.books=books; // get back the book from the search and now upddate 
		
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
		
		JLabel lblPurchases = new JLabel("Purchases : ");
		lblPurchases.setBounds(40, 250, 72, 14);
		add(lblPurchases);
		
		textField = new JTextField();
		textField.setBounds(100, 96, 86, 20);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(100, 124, 86, 20);
		add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(100, 149, 86, 20);
		add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(100, 174, 86, 20);
		add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(100, 199, 86, 20);
		add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(100, 224, 86, 20);
		add(textField_5);
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setBounds(100, 247, 86, 20);
		add(textField_6);
		textField_6.setColumns(10);
		
		JButton btnAdd = new JButton();
		if(ISUpdateOrAdd==1)
			btnAdd.setText("Add book");
			else if(ISUpdateOrAdd==0)
				btnAdd.setText("Update book");
		btnAdd.setBounds(377, 281, 120, 23);
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