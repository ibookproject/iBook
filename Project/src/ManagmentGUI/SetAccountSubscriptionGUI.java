


package ManagmentGUI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.MatteBorder;

import Controller.UserController;
import MenuGUI.LoginGUI;
import Panels.*;
import Role.User;
import Role.UserStatus;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

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
	public static JPanel panel;
	private JScrollPane scrollPaneMain;
	private int privilegeTemp=0;// save the privilege of the user that was found
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
		lblChooseOneOption.setBounds(34, 211, 94, 14);
		add(lblChooseOneOption);

		JLabel lblUserCheck = new JLabel("");
		lblUserCheck.setBounds(34, 185, 217, 14);
		add(lblUserCheck);

		txtUserID = new JTextField();
		txtUserID.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if (!txtUserID.getText().isEmpty()) {// check if text field is
														// not empty user id
					u = new User(txtUserID.getText());// create user
					ArrayList<User> temp = (ArrayList<User>) UserController.SearchUser("userID,firstName,lastName,privilege", u,
							"userID=\"" + u.getUserID() + "\"", screen.getClient());
					if (temp == null || temp.isEmpty()) {
						lblUserCheck.setText("User was not found!");
						u = null;// reset the user that need to update
					} else
					{
						lblUserCheck.setText(
								temp.get(0).getFirstName().toString() + " " + temp.get(0).getLastName().toString());
						privilegeTemp = temp.get(0).getPriviliege();
					}
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
		rdbtnOne.setBounds(165, 207, 145, 23);
		add(rdbtnOne);

		JRadioButton rdbtnMonthly = new JRadioButton("Monthly");
		rdbtnMonthly.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				radioButtonChoose = UserStatus.MONTHLY;
			}
		});
		rdbtnMonthly.setBounds(165, 233, 109, 23);
		add(rdbtnMonthly);

		JRadioButton rdbtnYearly = new JRadioButton("Yearly");
		rdbtnYearly.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				radioButtonChoose = UserStatus.YEARLY;
			}
		});
		rdbtnYearly.setBounds(165, 259, 109, 23);
		add(rdbtnYearly);

		// Group the radio buttons.
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnOne);
		group.add(rdbtnMonthly);
		group.add(rdbtnYearly);

		JButton btnSet = new JButton("Set");
		btnSet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean result = false;
				if(u==null||radioButtonChoose==0)
					JOptionPane.showMessageDialog(screen,"Update user process FAILED ! ", "Warning",JOptionPane.WARNING_MESSAGE);
				else
				{
					if(privilegeTemp == 1)
						result = UserController.SetStatusSubscription(u, "subscriptionMethod=\""+radioButtonChoose+"\" && privilege=\""+ UserStatus.READER +"\"", "userID=\""+u.getUserID()+"\"", screen.getClient());
					// update user by the new subscription method from the radio button that choosen
					// and set privilege to 2 (READER) if its under 2
					else
						result = UserController.SetStatusSubscription(u, "subscriptionMethod=\""+radioButtonChoose+"\"", "userID=\""+u.getUserID()+"\"", screen.getClient());
					if(result)
						JOptionPane.showMessageDialog(screen,"Subscription method set sucsseccfully", "Warning",JOptionPane.WARNING_MESSAGE);
					else JOptionPane.showMessageDialog(screen,"Update user process FAILED", "Warning",JOptionPane.WARNING_MESSAGE);

				}
			}
		});
		btnSet.setBounds(103, 306, 89, 23);
		add(btnSet);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(422, 185, 46, 14);
		add(lblNewLabel_1);
		
		JLabel lblListOfUsers = new JLabel("LIST OF USERS WITH REQUESTED SUBSCRIPTION");
		lblListOfUsers.setBounds(427, 135, 278, 14);
		add(lblListOfUsers);

		scrollPaneMain = new JScrollPane();
		scrollPaneMain.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneMain.setAutoscrolls(true);
		scrollPaneMain.setBounds(320, 160, 487, 438);
		add(scrollPaneMain);
		
		panel = new JPanel();
		panel.setIgnoreRepaint(true);
		panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		panel.setAutoscrolls(true);
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		scrollPaneMain.setViewportView(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		//panel.add(new UserSubscriptionPanel(screen));
	//	panel.add(new UserSubscriptionPanel(screen));
		//panel.add(new UserSubscriptionPanel(screen));
	//	panel.add(new UserSubscriptionPanel(screen));
	//	panel.add(new UserSubscriptionPanel(screen));
	//	panel.add(new UserSubscriptionPanel(screen));
	//	panel.add(new UserSubscriptionPanel(screen));
		
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
