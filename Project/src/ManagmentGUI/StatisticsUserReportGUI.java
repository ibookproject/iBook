package ManagmentGUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Book.Book;
import java.util.InputMismatchException;
import Controller.UserController;
import Controller.bookController;
import MemberGUI.SearchBook;
import MenuGUI.LoginGUI;
import Role.User;
import client.DBSQLhandler;
import client.DBgenericObject;

public class StatisticsUserReportGUI extends JPanel
{
	private static final long serialVersionUID = 1L;
	public JButton btnBack ;
	private LoginGUI screen;
	private JPanel pann;
	private ImageIcon backIcon;
	private JLabel lblDate;
	private JLabel userReportLbl;
	private JButton btnGetReports;
	private JLabel lblUserID;
	private JTextArea txtReport;
	private ArrayList<User> searchRes;
	private JTextField textFieldID;
	private JTextField textFieldDate;
	
	public StatisticsUserReportGUI(LoginGUI screen) 
	{
		super();
		
		this.screen=screen;
		pann=this;
		
		initialize();
		
	}

	private void initialize() 
	{
		this.setSize(850, 625);
		this.setLayout(null);	
		
		userReportLbl = new JLabel("Statistics User Report");
		userReportLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		userReportLbl.setBounds(355, 49, 175, 22);
		add(userReportLbl);
		
		backIcon =new ImageIcon("src/images/backIcon.png");
		btnBack = new JButton(backIcon);// declaration of back button
		btnBack.setBounds(39, 52, 89, 23);
		add(btnBack);
		

		textFieldID = new JTextField();
		textFieldID.setBounds(320, 129, 116, 22);
		add(textFieldID);
		textFieldID.setColumns(10);

		
		lblUserID = new JLabel("User ID");
		lblUserID.setBounds(238, 133, 70, 14);
		add(lblUserID);
		
		txtReport = new JTextArea();
		txtReport.setBounds(206, 294, 500, 300);
		add(txtReport);
		
		lblDate = new JLabel("Date");
		lblDate.setBounds(238, 180, 48, 22);
		add(lblDate);
		
		textFieldDate = new JTextField();
		textFieldDate.setBounds(320, 180, 116, 21);
		add(textFieldDate);
		textFieldDate.setColumns(10);
		
		btnGetReports = new JButton("Get report");
		btnGetReports.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				User u=null;
				try
				{
				u=new User(textFieldID.getText(),"2","1","1",1);
				}
				catch(InputMismatchException ex)
				{
				System.out.println(ex);
				}
				ArrayList<User> temp= (ArrayList<User>)UserController.SearchUser("userID,password,firstName,lastName",u,"userID=\""+textFieldID.getText()+"\"" ,screen.getClient());
				if(temp==null||temp.isEmpty())
				{
					setList(temp);
					screen.setContentPane(pann);
					JOptionPane.showMessageDialog(screen,"User was not Found\n", "Warning",JOptionPane.WARNING_MESSAGE);
				}
			
				else
				{
					setTextArea(temp);
					JOptionPane.showMessageDialog(screen,"User Found\n", "Warning",JOptionPane.WARNING_MESSAGE);
				}
			}		
		});
		btnGetReports.setBounds(598, 129, 107, 23);
		add(btnGetReports);
	}
	
	public void setTextArea(ArrayList<User> user)
	{
		txtReport.setText(user.get(0).toString());
	}
	public void setList(ArrayList<User> list)
	{
		this.searchRes=list;
		
	}
}
