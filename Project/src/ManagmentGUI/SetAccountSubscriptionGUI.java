package ManagmentGUI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;

import Controller.UserController;
import MenuGUI.LoginGUI;
import Role.User;

import javax.swing.JRadioButton;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;

public class SetAccountSubscriptionGUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel lblUserCheck;
	public JButton btnBack;
	private LoginGUI screen;
	private JPanel pann;
	private JTextField txtUserID;

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
				User u = new User(txtUserID.getText());// create user from text
														// fields
				ArrayList<User> temp = (ArrayList<User>) UserController.SearchUser("userID", u,
						"userID=\"" + u.getUserID() + "\"", screen.getClient());
				if (temp == null || temp.isEmpty()) {
					lblUserCheck.setText(temp.get(0).getFirstName() + temp.get(0).getLastName());
				} else {
					lblUserCheck.setText("User was not found!");
				}
			}
		});
		txtUserID.setBounds(165, 157, 86, 20);
		add(txtUserID);
		txtUserID.setColumns(10);

		JRadioButton rdbtnOne = new JRadioButton("One Time Purchase");
		rdbtnOne.setBounds(165, 181, 145, 23);
		add(rdbtnOne);

		JRadioButton rdbtnMonthly = new JRadioButton("Monthly");
		rdbtnMonthly.setBounds(165, 207, 109, 23);
		add(rdbtnMonthly);

		JRadioButton rdbtnYearly = new JRadioButton("Yearly");
		rdbtnYearly.setBounds(165, 233, 109, 23);
		add(rdbtnYearly);

		// Group the radio buttons.
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnOne);
		group.add(rdbtnMonthly);
		group.add(rdbtnYearly);

		JButton btnSet = new JButton("Set");
		btnSet.setBounds(422, 328, 89, 23);
		add(btnSet);



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