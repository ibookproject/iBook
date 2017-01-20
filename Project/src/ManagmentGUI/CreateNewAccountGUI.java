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

import Book.Book;
import Controller.UserController;
import MenuGUI.LoginGUI;
import Role.User;
import Panels.Validation;

public class CreateNewAccountGUI extends JPanel {

	private static final long serialVersionUID = 1L;
	public JButton btnBack;
	private LoginGUI screen;
	private JPanel pann;
	private JTextField txtUserID;
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JTextField txtPassword;
	private JTextField txtIdentity;

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

		ImageIcon backIcon = new ImageIcon("src/images/backIcon.png");
		btnBack = new JButton(backIcon);
		btnBack.setBounds(39, 52, 89, 23);
		add(btnBack);

		JButton btnCreate = new JButton("Create");
		btnCreate.setFont(new Font("Tahoma", Font.PLAIN, 16));

		btnCreate.setBounds(708, 531, 89, 37);
		add(btnCreate);

		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblFirstName.setBounds(39, 273, 89, 20);
		add(lblFirstName);

		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLastName.setBounds(39, 333, 89, 20);
		add(lblLastName);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPassword.setBounds(39, 393, 89, 20);
		add(lblPassword);

		txtFirstName = new JTextField();
		txtFirstName.setBounds(239, 268, 146, 30);
		add(txtFirstName);
		txtFirstName.setColumns(10);

		txtLastName = new JTextField();
		txtLastName.setBounds(239, 328, 146, 30);
		add(txtLastName);
		txtLastName.setColumns(10);

		txtPassword = new JTextField();
		txtPassword.setBounds(239, 388, 146, 30);
		add(txtPassword);
		txtPassword.setColumns(10);

		JLabel lblUserId = new JLabel("User ID:");
		lblUserId.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUserId.setBounds(39, 153, 89, 20);
		add(lblUserId);

		txtUserID = new JTextField();
		txtUserID.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtUserID.setBounds(239, 148, 146, 30);
		add(txtUserID);
		txtUserID.setColumns(10);

		JLabel lblAnswermessage = new JLabel("");
		lblAnswermessage.setBounds(169, 287, 443, 14);
		add(lblAnswermessage);

		JLabel lblIdentity = new JLabel("Identity Number:");
		lblIdentity.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblIdentity.setBounds(39, 213, 122, 20);
		add(lblIdentity);

		txtIdentity = new JTextField();
		txtIdentity.setColumns(10);
		txtIdentity.setBounds(239, 208, 146, 30);
		add(txtIdentity);

		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String warnings = "ERROR :\n";
				if (!Validation.regularValidation(txtUserID.getText()))
					warnings += "User Id - The following characters are not allowed :  |,%,\\," + "\",',&,=\n";
				if (!Validation.UserIDValidation(txtIdentity.getText()))
					warnings += "Identity Number - Must contain 9 NUMBERS\n";
				if (!Validation.PasswordValidation(txtPassword.getText()))
					warnings += "Password - The following characters are not allowed :|,%,\\," + "\",',&,=\n";
				if (!Validation.NameValidation(txtFirstName.getText().trim(), 20))
					warnings += "First name - Must contain only English letters\n";
				if (!Validation.NameValidation(txtLastName.getText().trim(), 20))
					warnings += "Last name - Must contain only English letters";
				if (warnings.equalsIgnoreCase("ERROR :\n")) {
					if (!(txtUserID.getText() == "" || txtIdentity.getText() == "" || txtPassword.getText() == ""
							|| txtFirstName.getText() == "" || txtLastName.getText() == "")) {
						User u = new User(txtUserID.getText(), txtIdentity.getText(), txtPassword.getText(),
								txtFirstName.getText(), txtLastName.getText(), 1);// create
																					// user
																					// from
																					// text
																					// fields
						ArrayList<User> temp = (ArrayList<User>) UserController.SearchUser("userID", u, "userID=\""
								+ txtUserID.getText() + "\" OR identityNumber=\"" + txtIdentity.getText() + "\"",
								screen.getClient());
						if (temp == null || temp.isEmpty()) {

							boolean result = UserController.CreateNewAccount(u, screen.getClient());//
							if (result == false)
								JOptionPane.showMessageDialog(screen, "Add user process FAILED ! ", "Warning",
										JOptionPane.WARNING_MESSAGE);
							else {
								JOptionPane.showMessageDialog(screen, "The user was added successfully to DB !", "done",
										JOptionPane.INFORMATION_MESSAGE);
								txtLastName.setText("");
								txtFirstName.setText("");
								txtUserID.setText("");
								txtPassword.setText("");
								txtIdentity.setText("");
							}
						}

						else
							JOptionPane.showMessageDialog(screen,
									"User name or Identity number is already exist. Please try another user name\n",
									"Warning", JOptionPane.WARNING_MESSAGE);

					} else
						JOptionPane.showMessageDialog(screen, "You must fill all fields", "Warning",
								JOptionPane.WARNING_MESSAGE);
				} else
					JOptionPane.showMessageDialog(screen, warnings, "Warning", JOptionPane.WARNING_MESSAGE);
			}
		});

	}
}
