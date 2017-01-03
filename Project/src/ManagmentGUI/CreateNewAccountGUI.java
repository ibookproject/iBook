package ManagmentGUI;


import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTextField;




public class CreateNewAccountGUI extends JPanel {

	
	private static final long serialVersionUID = 1L;
	public JButton btnBack;
	private JFrame screen;
	private JPanel pann;
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JTextField txtPassword;
	private JTextField txtSecondPassword;
	private JTextField txtEmail;

	public CreateNewAccountGUI(JFrame screen) {
		super();
		this.screen=screen;
		pann=this;

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
		btnCreate.setBounds(707, 540, 89, 23);
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
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(39, 236, 89, 14);
		add(lblEmail);
		
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
		
		txtEmail = new JTextField();
		txtEmail.setBounds(204, 233, 118, 20);
		add(txtEmail);
		txtEmail.setColumns(10);
	
	}
}