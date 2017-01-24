package ManagmentGUI;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import Controller.UserController;
import Extras.DatePicker;
import Extras.Validation;
import MenuGUI.LibraryManagerMenu;
import MenuGUI.LoginGUI;
import Role.User;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;

/**
 * Update User Info GUI
 * @author Sagi Entenberg
 * @author Almog Yamin
 */
public class UpdateUserInfoGUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private int userIdAtDataBase;
	private int permission;
	public JButton btnBack;
	public JButton btnUpdate;
	private LoginGUI screen;
	private JPanel pann;
	private JTextField FirstNameTextField;
	private JTextField LastNameTextField;
	private JTextField UserIDTextField;
	private JTextField PasswordtextField;
	private JTextField txtFinishDate;
	private JButton btnChooseDate;
	private JButton btnSelect;
	private JComboBox SubscriptionMethodcomboBox;
	private JComboBox UserStatuscomboBox;
	private ArrayList<User> temp;
	private JComboBox PrivilagecomboBox;
/**
 *  Constructor
 * @param screen
 * 	LoginGUI extends JFram to build the panel
 * @param permission
 * the privilege of This Client
 * @param UserIdAtDataBase
 * 
 */
	public UpdateUserInfoGUI(LoginGUI screen, int permission, int UserIdAtDataBase) {
		super();
		this.screen = screen;
		pann = this;
		this.permission = permission;
		this.userIdAtDataBase = UserIdAtDataBase;
		initialize();
	}

	private void initialize() {

		this.setSize(850, 625);
		this.setLayout(null);

		JLabel lblTitel = new JLabel("Update User Information");
		lblTitel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 21));
		lblTitel.setBounds(341, 30, 263, 45);
		add(lblTitel);

		ImageIcon backIcon = new ImageIcon("Extras/Images/backIcon.png");
		btnBack = new JButton(backIcon);
		btnBack.setBounds(34, 33, 89, 23);
		add(btnBack);

		JLabel lblUserId = new JLabel("User ID:");
		lblUserId.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblUserId.setBounds(34, 113, 96, 30);
		add(lblUserId);
		btnUpdate = new JButton("Update");
		btnUpdate.setEnabled(false);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * private JTextField FirstNameTextField; private JTextField
				 * LastNameTextField; private JTextField UserIDTextField;
				 * private JTextField PasswordtextField; private JTextField
				 * txtFinishDate;
				 */

				String warnings = "ERROR :\n";
				if (!Validation.PasswordValidation(PasswordtextField.getText()))
					warnings += "Password - The following characters are not allowed :|,%,\\," + "\",',&,=\n";
				if (!Validation.NameValidation(FirstNameTextField.getText().trim(), 20))
					warnings += "First name - Must contain only English letters\n";
				if (!Validation.NameValidation(LastNameTextField.getText().trim(), 20))
					warnings += "Last name - Must contain only English letters";
				if (warnings.equalsIgnoreCase("ERROR :\n")) {
					if (!(PasswordtextField.getText().isEmpty() || FirstNameTextField.getText().isEmpty()
							|| LastNameTextField.getText().isEmpty())) {
						User u = new User(UserIDTextField.getText()/* ID */);
						if (UserController.UpdateUserStatus(u,
								"firstName=\"" + FirstNameTextField.getText() + "\"" + " && lastName=\""
										+ LastNameTextField.getText() + "\"" + " && password=\""
										+ PasswordtextField.getText() + "\"" + " && privilege=\""
										+ (PrivilagecomboBox.getSelectedIndex() + 1) + "\"" + " && userStatus=\""
										+ UserStatuscomboBox.getSelectedIndex() + "\"" + " && subscriptionMethod=\""
										+ SubscriptionMethodcomboBox.getSelectedIndex() + "\""
										+ " && finishDateOfSubscription=\"" + txtFinishDate.getText() + "\"",
								"userID=\"" + UserIDTextField.getText() + "\"", screen.getClient())) {
							JOptionPane.showMessageDialog(screen, "User updated successfully", "Plain",
									JOptionPane.INFORMATION_MESSAGE);
							LibraryManagerMenu lmm = new LibraryManagerMenu(screen, permission);
							screen.setContentPane(lmm);
						} else
							JOptionPane.showMessageDialog(screen, "User updated procces faild", "Error",
									JOptionPane.ERROR_MESSAGE);
					} else
						JOptionPane.showMessageDialog(screen, "You must fill all fields", "Error",
								JOptionPane.ERROR_MESSAGE);
				} else
					JOptionPane.showMessageDialog(screen, warnings, "Error",
							JOptionPane.ERROR_MESSAGE);
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnUpdate.setBounds(690, 553, 115, 40);
		add(btnUpdate);

		PrivilagecomboBox = new JComboBox();
		PrivilagecomboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (PrivilagecomboBox.getSelectedIndex() == 0) {
					Date d = new Date();
					btnChooseDate.setEnabled(false);
					SubscriptionMethodcomboBox.setSelectedIndex(0);
					SubscriptionMethodcomboBox.setEnabled(false);
					if (temp.get(0).getFinishDateOfSubscription() != null)
						txtFinishDate.setText(new SimpleDateFormat("yyyy/MM/dd").format(d));

				} else {
					SubscriptionMethodcomboBox.setEnabled(true);
					SubscriptionMethodcomboBox.setSelectedIndex(temp.get(0).getSubscriptionMethod());
					btnChooseDate.setEnabled(true);
					if (temp.get(0).getFinishDateOfSubscription() != null)
						txtFinishDate.setText(
								new SimpleDateFormat("yyyy/MM/dd").format(temp.get(0).getFinishDateOfSubscription()));

				}
			}
		});
		PrivilagecomboBox.setModel(new DefaultComboBoxModel(
				new String[] { "USER", "READER", "WORKER", "QUALIFIED EDITOR", "LIBRARIAN", "MANAGER" }));
		PrivilagecomboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		PrivilagecomboBox.setBounds(240, 350, 188, 30);
		PrivilagecomboBox.setEnabled(false);
		add(PrivilagecomboBox);

		SubscriptionMethodcomboBox = new JComboBox();
		SubscriptionMethodcomboBox
				.setModel(new DefaultComboBoxModel(new String[] { "NONE", "SINGLE", "MONTHLY", "YEARLY" }));
		SubscriptionMethodcomboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		SubscriptionMethodcomboBox.setBounds(240, 450, 188, 30);
		SubscriptionMethodcomboBox.setEnabled(false);
		add(SubscriptionMethodcomboBox);

		UserStatuscomboBox = new JComboBox();
		UserStatuscomboBox.setEnabled(false);
		UserStatuscomboBox.setModel(new DefaultComboBoxModel(new String[] { "DISCONNECTED", "CONNECTED", "LOCK" }));
		UserStatuscomboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		UserStatuscomboBox.setBounds(240, 400, 188, 30);
		add(UserStatuscomboBox);

		btnSelect = new JButton("Select");
		btnSelect.setEnabled(false);
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Validation.IDValidation(UserIDTextField.getText())) {
					User u = new User(UserIDTextField.getText()/* ID */);

					temp = (ArrayList<User>) UserController.SearchUser(
							"userID,password,identityNumber,firstName,"
									+ "lastName,userStatus,privilege,subscriptionRequest,subscriptionMethod,finishDateOfSubscription",
							u, "userID=\"" + u.getUserID() + "\"", screen.getClient());
					if (temp == null || temp.isEmpty())
						JOptionPane.showMessageDialog(screen, "User ID not found.", "Warning",
								JOptionPane.WARNING_MESSAGE);

					else {
						FirstNameTextField.setText(temp.get(0).getFirstName());
						LastNameTextField.setText(temp.get(0).getLastName());
						PasswordtextField.setText(temp.get(0).getPassword());
						PrivilagecomboBox.setSelectedIndex(temp.get(0).getPriviliege() - 1);
						SubscriptionMethodcomboBox.setSelectedIndex(temp.get(0).getSubscriptionMethod());
						UserStatuscomboBox.setSelectedIndex(temp.get(0).getUserStatus());
						if (temp.get(0).getFinishDateOfSubscription() != null)
							txtFinishDate.setText(new SimpleDateFormat("yyyy/MM/dd")
									.format(temp.get(0).getFinishDateOfSubscription()));

						UserIDTextField.setEditable(false);
						FirstNameTextField.setEnabled(true);
						LastNameTextField.setEnabled(true);
						PasswordtextField.setEnabled(true);
						PrivilagecomboBox.setEnabled(true);
						SubscriptionMethodcomboBox.setEnabled(true);
						UserStatuscomboBox.setEnabled(true);
						btnSelect.setEnabled(false);
						btnUpdate.setEnabled(true);
						btnChooseDate.setEnabled(true);

					}

				} else
					JOptionPane.showMessageDialog(screen, "User ID must contain only numbers (0-9)", "Warning",
							JOptionPane.WARNING_MESSAGE);
			}
		});
		btnSelect.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnSelect.setBounds(470, 113, 97, 30);
		add(btnSelect);

		UserIDTextField = new JTextField();
		UserIDTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent key) {
				if ((UserIDTextField.getText().length() >= 0) && (key.getKeyCode() != 8))
					btnSelect.setEnabled(true);
				else if ((UserIDTextField.getText().length() == 1) && (key.getKeyCode() == 8))
					btnSelect.setEnabled(false);
			}
		});

		UserIDTextField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		UserIDTextField.setBounds(240, 113, 188, 30);
		add(UserIDTextField);
		UserIDTextField.setColumns(10);

		JLabel lblFirstName = new JLabel("First name:");
		lblFirstName.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblFirstName.setBounds(34, 200, 115, 30);
		add(lblFirstName);

		JLabel lblLastName = new JLabel("Last name:");
		lblLastName.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblLastName.setBounds(34, 300, 115, 30);
		add(lblLastName);

		FirstNameTextField = new JTextField();
		FirstNameTextField.setEnabled(false);
		FirstNameTextField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		FirstNameTextField.setBounds(240, 200, 188, 30);
		add(FirstNameTextField);
		FirstNameTextField.setColumns(10);

		LastNameTextField = new JTextField();
		LastNameTextField.setEnabled(false);
		LastNameTextField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		LastNameTextField.setBounds(240, 300, 188, 30);
		add(LastNameTextField);
		LastNameTextField.setColumns(10);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPassword.setBounds(34, 250, 89, 30);
		add(lblPassword);

		JLabel lblUserStstus = new JLabel("User status:");
		lblUserStstus.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblUserStstus.setBounds(34, 400, 115, 30);
		add(lblUserStstus);

		JLabel lblPrivilage = new JLabel("Privilege:");
		lblPrivilage.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPrivilage.setBounds(34, 350, 115, 30);
		add(lblPrivilage);

		PasswordtextField = new JTextField();
		PasswordtextField.setEnabled(false);
		PasswordtextField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		PasswordtextField.setColumns(10);
		PasswordtextField.setBounds(240, 250, 188, 30);
		add(PasswordtextField);

		JLabel lblSubscriptionMethod = new JLabel("Subscription Method:");
		lblSubscriptionMethod.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSubscriptionMethod.setBounds(34, 450, 177, 30);
		add(lblSubscriptionMethod);

		JLabel lblFinishdatesub = new JLabel("Finish Date Subscription:");
		lblFinishdatesub.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblFinishdatesub.setBounds(34, 500, 213, 30);
		add(lblFinishdatesub);

		// create button and there object
		btnChooseDate = new JButton("CHOOSE DATE");
		btnChooseDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnChooseDate.setEnabled(false);
		// perform action listener
		btnChooseDate.addActionListener(new ActionListener() {
			// performed action
			public void actionPerformed(ActionEvent arg0) {
				// create frame new object f
				JFrame f = new JFrame();
				f.setLocation(400, 400);
				f.setBounds(200, 200, 200, 200);
				// set text which is collected by date picker i.e. set date
				txtFinishDate.setText(new DatePicker(f).setPickedDate());

			}
		});
		// set button bound
		btnChooseDate.setBounds(426, 500, 153, 30);
		// add button in contentPane
		add(btnChooseDate);

		txtFinishDate = new JTextField();
		txtFinishDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtFinishDate.setEnabled(false);
		txtFinishDate.setBounds(240, 500, 188, 30);
		add(txtFinishDate);
		txtFinishDate.setColumns(10);

	}
}
