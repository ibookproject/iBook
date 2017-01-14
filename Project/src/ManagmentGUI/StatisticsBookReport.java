package ManagmentGUI;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.MatteBorder;

import Book.Book;
import Book.Domain;
import Controller.UserController;
import Controller.bookController;
import MenuGUI.LoginGUI;
import Panels.BookPanel;
import Panels.BookStatisticsPanel;
import Panels.UserSubscriptionPanel;
import Role.User;
import client.DBgenericObject;

import javax.swing.JTextArea;
import javax.swing.JProgressBar;

public class StatisticsBookReport extends JPanel {

	private static final long serialVersionUID = 1L;
	public JButton btnBack;
	private LoginGUI screen;
	private JPanel pann;
	private ArrayList<Book> tempBooks;
	private JLabel lblOrdersdb;
	private JLabel lblSearchersdb;
	private JProgressBar progressBar;
	private JProgressBar progressBarSearches;
	private JProgressBar progressBarOrders;
	
	public StatisticsBookReport(LoginGUI screen) {
		super();
		this.screen = screen;
		pann = this;

		initialize();
	}

	private void initialize() {

		this.setSize(850, 625);
		this.setLayout(null);

		JLabel lblNewLabel = new JLabel("Statistics Book Report");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(355, 49, 175, 22);
		add(lblNewLabel);

		ImageIcon backIcon = new ImageIcon("src/images/backIcon.png");
		btnBack = new JButton(backIcon);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

		}});
		btnBack.setBounds(39, 52, 89, 23);
		add(btnBack);
		
		JLabel lblOrders = new JLabel("Orders:");
		lblOrders.setBounds(130, 182, 75, 14);
		add(lblOrders);
		
		lblOrdersdb = new JLabel("");
		lblOrdersdb.setBounds(236, 182, 46, 14);
		add(lblOrdersdb);
		
		JLabel lblSearches = new JLabel("Searches:");
		lblSearches.setBounds(130, 224, 75, 14);
		add(lblSearches);
		
		lblSearchersdb = new JLabel("");
		lblSearchersdb.setBounds(236, 224, 46, 14);
		add(lblSearchersdb);
		
		progressBarOrders = new JProgressBar();
		progressBarOrders.setBounds(292, 182, 146, 14);
		progressBarOrders.setMaximum(2000);
		add(progressBarOrders);
		
		progressBarSearches = new JProgressBar();
		progressBarSearches.setBounds(292, 224, 146, 14);
		add(progressBarSearches);
		
		Book b=new Book();

	}
	public void setResult(int purchase,int searches)
	{	
		lblOrdersdb.setText(Integer.toString(purchase));
		lblSearchersdb.setText(Integer.toString(searches));
		//textFieldOrders.setBounds(292, 179, 5*purchase, 20);
		progressBarOrders.setValue(20*purchase);
		progressBarSearches.setValue(20*searches);
		
	}
}
