package ManagmentGUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import Book.book;
import Controller.UserController;
import Controller.bookController;
import MemberGUI.SearchBook;
import MenuGUI.LoginGUI;
import Role.user;




public class StatisticsUserReportGUI extends JPanel {

	
	private static final long serialVersionUID = 1L;
	public JButton btnBack ;
	private LoginGUI screen;
	private JPanel pann;
	private JLabel lblDate;
	private JButton btnGetReports;
	private JLabel lblListOfBook;
	private JTextArea txtReport;
	private ArrayList<user> searchRes;
	private JTextField textFieldName;
	private JTextField textFieldDate;
	private JTextField textFieldLastName;
	
	public StatisticsUserReportGUI(LoginGUI screen) {
		super();
		initialize();
		this.screen=screen;
		pann=this;
		
		textFieldName = new JTextField();
		textFieldName.setBounds(349, 129, 116, 22);
		add(textFieldName);
		textFieldName.setColumns(10);
		
		textFieldLastName = new JTextField();
		textFieldLastName.setBounds(349, 168, 116, 22);
		add(textFieldLastName);
		textFieldLastName.setColumns(10);
		
		JLabel lblLastName = new JLabel("User Last Name");
		lblLastName.setBounds(238, 174, 99, 16);
		add(lblLastName);

	}


	private void initialize() {
		
		this.setSize(850, 625);
		this.setLayout(null);	
		
		JLabel userReportLbl = new JLabel("Statistics User Report");
		userReportLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		userReportLbl.setBounds(355, 49, 175, 22);
		add(userReportLbl);
		
		ImageIcon backIcon =new ImageIcon("src/images/backIcon.png");
		btnBack = new JButton(backIcon);// declaration of back button
		btnBack.setBounds(39, 52, 89, 23);
		add(btnBack);
		
		lblListOfBook = new JLabel("User Name");
		lblListOfBook.setBounds(238, 133, 70, 14);
		add(lblListOfBook);
		
		txtReport = new JTextArea();
		txtReport.setBounds(206, 294, 500, 300);
		add(txtReport);
		
		lblDate = new JLabel("Date");
		lblDate.setBounds(238, 203, 48, 22);
		add(lblDate);
		
		textFieldDate = new JTextField();
		textFieldDate.setBounds(349, 203, 116, 21);
		add(textFieldDate);
		textFieldDate.setColumns(10);
		
		btnGetReports = new JButton("Get report");
		btnGetReports.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// //////////////////////button back to Search book GUI
				// /////////////////////////////////////////////
		//		SearchBook sb = new SearchBook(screen);
			/*	sb.btnBack.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						screen.setContentPane(pann);
					}
					// //////////////////////button back to Search book
					// GUI/////////////////////////////////////////////
				});*/
				
				// User(String userID, String password, String firstName, String lastName, int userStatus)
				user u = new user(null, null,textFieldName.getText(), textFieldLastName.getText(),0);//create book from text fields
				String condition = "";//initialize the condition
			//	if (chckbxTitle.isSelected())
					condition += "userID=\"" + "" + "\"";//add "title" to condition
				//if (chckbxLanguage.isSelected()) {
					if (!condition.equals(""))
						condition += " && ";
					condition += "password=\"" + "" + "\"";//add "language" to condition
				//}
			//	if (chckbxAuthor.isSelected()) {
					if (!condition.equals(""))
						condition += " && ";
					condition += "firstName=\"" + u.getFirstName() + "\"";//add "author" to condition
			//	}
				//if (chckbxSummary.isSelected()) {
					if (!condition.equals(""))
						condition += " && ";
					condition += "lastName=\"" + u.getLastName() + "\"";//add "summary" to condition
				//}
				if (!condition.equals("")) 
				{//if have some condition
					ArrayList<user> temp = UserController.SearchUser(u,condition, screen.getClient());//call search book method from book controller
					if (temp != null) 
					{
						setList(temp);
						screen.setContentPane(pann);
					} 
					else //
						JOptionPane.showMessageDialog(screen,"Not found any user result\n", "Warning",JOptionPane.WARNING_MESSAGE);
				} 
				else //(condition)=="")empty
					JOptionPane.showMessageDialog(screen,"Nothing has selected", "Warning",JOptionPane.WARNING_MESSAGE);
			}

		});
		btnGetReports.setBounds(598, 129, 107, 23);
		add(btnGetReports);
	
	}
	
	public void setList(ArrayList<user> list)
	{
		this.searchRes=list;
		//JList<book> bookList = new JList<book>();

		//textArea.setText(list.toString());
	//	add(textArea);
		
		
	}
}
