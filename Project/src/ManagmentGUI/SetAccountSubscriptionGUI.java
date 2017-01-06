package ManagmentGUI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;

import Controller.UserController;
import MenuGUI.LoginGUI;
import Role.User;
import Role.UserStatus;
import javax.swing.JRadioButton;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SetAccountSubscriptionGUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel lblUserCheck;
	public JButton btnBack;
	private LoginGUI screen;
	private JPanel pann;
	private JTextField txtUserID;
	private int radioButtonChoose = 0;// integer to sent what the radio button
										// that
										// choose
	private User u;

	public SetAccountSubscriptionGUI(LoginGUI screen) {
		super();
		this.screen = screen;
		pann = this;

		initialize();
	}

	private void initialize() {

		this.setSize(850, 625);
		this.setLayout(null);

		JLabel lblNewLabel = new JLabel("Set Account Subscription");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(355, 49, 207, 22);
		add(lblNewLabel);

		ImageIcon backIcon = new ImageIcon("src/images/backIcon.png");
		btnBack = new JButton(backIcon);
		btnBack.setBounds(39, 52, 89, 23);
		add(btnBack);

		JLabel lblEnterUserId = new JLabel("Enter User ID");
		lblEnterUserId.setBounds(34, 160, 94, 14);
		add(lblEnterUserId);

		JLabel lblChooseOneOption = new JLabel("Choose one option");
		lblChooseOneOption.setBounds(34, 185, 94, 14);
		add(lblChooseOneOption);

		JLabel lblUserCheck = new JLabel("");
		lblUserCheck.setBounds(310, 160, 481, 14);
		add(lblUserCheck);

		txtUserID = new JTextField();
		txtUserID.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if (!txtUserID.getText().isEmpty()) {// check if text field is
														// not empty user id
					u = new User(txtUserID.getText());// create user
					ArrayList<User> temp = (ArrayList<User>) UserController.SearchUser("userID,firstName,lastName", u,
							"userID=\"" + u.getUserID() + "\"", screen.getClient());
					if (temp == null || temp.isEmpty()) {
						lblUserCheck.setText("User was not found!");
						u = null;// reset the user that need to update
					} else
						lblUserCheck.setText(
								temp.get(0).getFirstName().toString() + " " + temp.get(0).getLastName().toString());
				} else
					u = null;// reset the user that need to update
			}
		});
		txtUserID.setBounds(165, 157, 86, 20);
		add(txtUserID);
		txtUserID.setColumns(10);

		JRadioButton rdbtnOne = new JRadioButton("One Time Purchase");
		rdbtnOne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				radioButtonChoose = UserStatus.SINGLE;
			}
		});
		rdbtnOne.setBounds(165, 181, 145, 23);
		add(rdbtnOne);

		JRadioButton rdbtnMonthly = new JRadioButton("Monthly");
		rdbtnMonthly.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				radioButtonChoose = UserStatus.MONTHLY;
			}
		});
		rdbtnMonthly.setBounds(165, 207, 109, 23);
		add(rdbtnMonthly);

		JRadioButton rdbtnYearly = new JRadioButton("Yearly");
		rdbtnYearly.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				radioButtonChoose = UserStatus.YEARLY;
			}
		});
		rdbtnYearly.setBounds(165, 233, 109, 23);
		add(rdbtnYearly);

		// Group the radio buttons.
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnOne);
		group.add(rdbtnMonthly);
		group.add(rdbtnYearly);

		JButton btnSet = new JButton("Set");
		btnSet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(u==null||radioButtonChoose==0)
					JOptionPane.showMessageDialog(screen,"Update user process FAILED ! ", "Warning",JOptionPane.WARNING_MESSAGE);
				else
				{
					/*UPDATE user
					SET City='Hamburg'
					WHERE CustomerID=1;*/
					boolean result = UserController.SetStatusSubscription(u, "subscriptionMethod=\""+radioButtonChoose+"\" && privilege=\""+ UserStatus.READER +"\"", "userID=\""+u.getUserID()+"\"", screen.getClient());
					// update user by the new subscription method from the radio button that choosen
					// and set privilege to 2 (READER)
					if(result)
						JOptionPane.showMessageDialog(screen,"Subscription method set sucsseccfully", "Warning",JOptionPane.WARNING_MESSAGE);
					else JOptionPane.showMessageDialog(screen,"Update user process FAILED", "Warning",JOptionPane.WARNING_MESSAGE);

				}
			}
		});
		btnSet.setBounds(422, 328, 89, 23);
		add(btnSet);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(422, 185, 46, 14);
		add(lblNewLabel_1);

		/*
		 * דוגמא להשתמש ברדיו באטן JRadioButton catButton = new
		 * JRadioButton(catString); catButton.setMnemonic(KeyEvent.VK_C);
		 * catButton.setActionCommand(catString);
		 * 
		 * JRadioButton dogButton = new JRadioButton(dogString);
		 * dogButton.setMnemonic(KeyEvent.VK_D);
		 * dogButton.setActionCommand(dogString);
		 * 
		 * JRadioButton rabbitButton = new JRadioButton(rabbitString);
		 * rabbitButton.setMnemonic(KeyEvent.VK_R);
		 * rabbitButton.setActionCommand(rabbitString);
		 * 
		 * JRadioButton pigButton = new JRadioButton(pigString);
		 * pigButton.setMnemonic(KeyEvent.VK_P);
		 * pigButton.setActionCommand(pigString);
		 * 
		 * //Group the radio buttons. ButtonGroup group = new ButtonGroup();
		 * group.add(birdButton); group.add(catButton); group.add(dogButton);
		 * group.add(rabbitButton); group.add(pigButton);
		 * 
		 * //Register a listener for the radio buttons.
		 * birdButton.addActionListener(this);
		 * catButton.addActionListener(this); dogButton.addActionListener(this);
		 * rabbitButton.addActionListener(this);
		 * pigButton.addActionListener(this);
		 */

	}
}