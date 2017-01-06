package ManagmentGUI;


import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;

import javax.swing.JTextField;

import Book.Book;
import Book.Domain;
import Controller.bookController;
import MenuGUI.LoginGUI;
import client.DBgenericObject;

import javax.swing.JTextArea;




public class StatisticsBookReportGUI extends JPanel {

	
	private static final long serialVersionUID = 1L;
	public JButton btnBack;
	private LoginGUI screen;
	private JPanel pann;
	private JTextField txtFromDate;
	private JTextField txtToDate;

	public StatisticsBookReportGUI(LoginGUI screen) {
		super();
		this.screen=screen;
		pann=this;

		initialize();
	}

	
	private void initialize() {
		
		this.setSize(850, 625);
		this.setLayout(null);	
		
		JLabel lblNewLabel = new JLabel("Statistics Book Report");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(355, 49, 175, 22);
		add(lblNewLabel);
		
		ImageIcon backIcon =new ImageIcon("src/images/backIcon.png");
		btnBack = new JButton(backIcon);
		btnBack.setBounds(39, 52, 89, 23);
		add(btnBack);
		
		JComboBox<String> comboBoxDomain = new JComboBox<String>();
		comboBoxDomain.setBounds(39, 199, 107, 20);
		add(comboBoxDomain);
		
		JLabel lblDomain = new JLabel("Domain:");
		lblDomain.setBounds(39, 174, 107, 14);
		add(lblDomain);
		
		JButton btnGetReports = new JButton("Get report");
		btnGetReports.setBounds(423, 508, 107, 23);
		add(btnGetReports);
		
		JTextArea txtrReport = new JTextArea();
		txtrReport.setBounds(205, 175, 500, 300);
		add(txtrReport);
		
		JLabel lblSubject = new JLabel("Subject:");
		lblSubject.setBounds(39, 230, 46, 14);
		add(lblSubject);
		
		JComboBox comboBoxSubject = new JComboBox();
		comboBoxSubject.setBounds(39, 255, 107, 20);
		add(comboBoxSubject);
		
		JLabel lblBook = new JLabel("Book:");
		lblBook.setBounds(39, 286, 46, 14);
		add(lblBook);
		
		JComboBox comboBoxBook = new JComboBox();
		comboBoxBook.setBounds(39, 311, 107, 20);
		add(comboBoxBook);
		
		JLabel lblFromDate = new JLabel("From date:");
		lblFromDate.setBounds(39, 422, 76, 14);
		add(lblFromDate);
		
		JLabel lblToDate = new JLabel("To date:");
		lblToDate.setBounds(39, 447, 76, 14);
		add(lblToDate);
		
		txtFromDate = new JTextField();
		txtFromDate.setBounds(109, 419, 86, 20);
		add(txtFromDate);
		txtFromDate.setColumns(10);
		
		txtToDate = new JTextField();
		txtToDate.setBounds(109, 444, 86, 20);
		add(txtToDate);
		txtToDate.setColumns(10);
		
		Domain d = new Domain();
	/*	ArrayList<DBgenericObject> tempDomain = bookController.GetAllDomainByNames("*",d,, screen.getClient());//call search book method from book controller
		int sizeDomain = tempDomain.size();
		for(int i = 0 ; i<sizeDomain ; i++)
			comboBoxDomain.addItem(tempDomain.get(i).toString());*/
	
	}
}