package ManagmentGUI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTextField;

import Book.book;
import Controller.UserController;
import MenuGUI.LoginGUI;
import Role.user;

public class CreateNewAccountGUI extends JPanel {

	private static final long serialVersionUID = 1L;
	public JButton btnBack;
	private LoginGUI screen;
	private JPanel pann;
	private JTextField txtUserID;
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JTextField txtPassword;
	private JTextField txtSecondPassword;

	public CreateNewAccountGUI(LoginGUI screen) {
		super();
		this.screen = screen;
		pann = this;

		initialize();
	}

	private void initialize() {
		
		this.setSize(850, 625);
		this.setLayout(null);	
		
		JLabel lblNewLabel = new JLabel("Create New Account");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(355, 49, 175, 22);
		add(lblNewLabel);
		
		ImageIcon backIcon =new ImageIcon("src/images/backIcon.png");
		btnBack = new JButton(backIcon);
		btnBack.setBounds(39, 52, 89, 23);
		add(btnBack);
		
		JButton btnCreate = new JButton("Create");

		btnCreate.setBounds(342, 326, 89, 23);
		add(btnCreate);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(39, 136, 89, 14);
		add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(39, 161, 89, 14);
		add(lblLastName);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(39, 186, 89, 14);
		add(lblPassword);
		
		JLabel lblPasswordAgain = new JLabel("Password Again");
		lblPasswordAgain.setBounds(39, 211, 89, 14);
		add(lblPasswordAgain);
		
		txtFirstName = new JTextField();
		txtFirstName.setBounds(204, 133, 118, 20);
		add(txtFirstName);
		txtFirstName.setColumns(10);
		
		txtLastName = new JTextField();
		txtLastName.setBounds(204, 158, 118, 20);
		add(txtLastName);
		txtLastName.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.setBounds(204, 183, 118, 20);
		add(txtPassword);
		txtPassword.setColumns(10);
		
		txtSecondPassword = new JTextField();
		txtSecondPassword.setBounds(204, 208, 118, 20);
		add(txtSecondPassword);
		txtSecondPassword.setColumns(10);
		
		JLabel lblUserId = new JLabel("User ID");
		lblUserId.setBounds(39, 111, 46, 14);
		add(lblUserId);
		
		txtUserID = new JTextField();
		txtUserID.setBounds(204, 108, 118, 20);
		add(txtUserID);
		txtUserID.setColumns(10);
		
		JLabel lblAnswermessage = new JLabel("");
		lblAnswermessage.setBounds(169, 287, 443, 14);
		add(lblAnswermessage);
		
		
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					/*	
	private JTextField txtUserID;
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JTextField txtPassword;
	private JTextField txtSecondPassword;*/
				
				user u = new user(txtUserID.getText(), txtPassword.getText(), txtFirstName.getText(),
						txtLastName.getText(), 1);//create book from text fields
				String condition = "";//initialize the condition
			/*	if (txtUserID.isSelected())
					condition += "title=\"" + u.getTitle() + "\"";//add "title" to condition
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
				}*/
				if (!condition.equals("")) {//if have some condition
					boolean result = UserController.CreateNewAccount(u, screen.getClient());//call search book method from book controller
				 	if (result==false)
						JOptionPane.showMessageDialog(screen,"Add user process FAILD ! ", "Warning",JOptionPane.WARNING_MESSAGE);
				 	else
				 		JOptionPane.showMessageDialog(screen,"The book was add successfully to DB !", "done",JOptionPane.INFORMATION_MESSAGE);
					
					}

		}});
		
	}
}
