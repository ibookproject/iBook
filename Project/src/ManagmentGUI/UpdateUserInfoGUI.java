package ManagmentGUI;


import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLayeredPane;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;




public class UpdateUserInfoGUI extends JPanel {



		private static final long serialVersionUID = 1L;
		public JButton btnBack;
		private JFrame screen;
		private JPanel pann;
		private JTextField FirstNameTextField;
		private JTextField LastNameTextField;
		private JTextField UserIDTextField;
		private JTextField PasswordtextField;

		public UpdateUserInfoGUI(JFrame screen) {
			super();
			this.screen=screen;
			pann=this;

			initialize();
		}

		
		private void initialize() {
			
			this.setSize(850, 625);
			this.setLayout(null);	
			
			JLabel lblNewLabel = new JLabel("Update User Information");
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 21));
			lblNewLabel.setBounds(341, 54, 289, 45);
			add(lblNewLabel);
			
			ImageIcon backIcon =new ImageIcon("src/images/backIcon.png");
			btnBack = new JButton(backIcon);
			btnBack.setBounds(39, 52, 89, 23);
			add(btnBack);
		    
		    JLabel lblUserId = new JLabel("User ID:");
		    lblUserId.setFont(new Font("Tahoma", Font.BOLD, 18));
		    lblUserId.setBounds(34, 133, 96, 23);
		    add(lblUserId);
		    
		    UserIDTextField = new JTextField();
		    UserIDTextField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		    UserIDTextField.setBounds(141, 124, 188, 39);
		    add(UserIDTextField);
		    UserIDTextField.setColumns(10);
		    
		    JButton btnSelect = new JButton("Select");
		    btnSelect.setFont(new Font("Tahoma", Font.BOLD, 16));
		    btnSelect.setBounds(397, 133, 97, 25);
		    add(btnSelect);
		    
		    JComboBox PrivilagecomboBox = new JComboBox();
		    PrivilagecomboBox.setModel(new DefaultComboBoxModel(new String[] {"User", "Reader", "Worker", "Qualified Editor", "Manager"}));
		    PrivilagecomboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		    PrivilagecomboBox.setBounds(214, 353, 155, 34);
		    PrivilagecomboBox.addItem("User");
		    PrivilagecomboBox.addItem("Reader");
		    PrivilagecomboBox.addItem("Worker");
		    PrivilagecomboBox.addItem("Qualified Editor");
		    PrivilagecomboBox.addItem("Manager");
		    add(PrivilagecomboBox);
		    
		    JLabel lblFirstName = new JLabel("First name:");
		    lblFirstName.setFont(new Font("Tahoma", Font.BOLD, 16));
		    lblFirstName.setBounds(34, 185, 115, 37);
		    add(lblFirstName);
		    
		    JLabel lblLastName = new JLabel("Last name:");
		    lblLastName.setFont(new Font("Tahoma", Font.BOLD, 16));
		    lblLastName.setBounds(401, 186, 115, 34);
		    add(lblLastName);
		    
		    FirstNameTextField = new JTextField();
		    FirstNameTextField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		    FirstNameTextField.setBounds(141, 185, 188, 37);
		    add(FirstNameTextField);
		    FirstNameTextField.setColumns(10);
		    
		    LastNameTextField = new JTextField();
		    LastNameTextField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		    LastNameTextField.setBounds(510, 185, 195, 36);
		    add(LastNameTextField);
		    LastNameTextField.setColumns(10);
		    
		    JLabel lblPassword = new JLabel("Password:");
		    lblPassword.setFont(new Font("Tahoma", Font.BOLD, 16));
		    lblPassword.setBounds(34, 251, 89, 34);
		    add(lblPassword);
		    
		    JButton btnNewButton = new JButton("Update");
		    btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		    btnNewButton.setBounds(667, 536, 115, 45);
		    add(btnNewButton);
		    
		    JLabel lblUserStstus = new JLabel("User ststus:");
		    lblUserStstus.setFont(new Font("Tahoma", Font.BOLD, 16));
		    lblUserStstus.setBounds(34, 487, 115, 37);
		    add(lblUserStstus);
		    
		    JComboBox UserStatuscomboBox = new JComboBox();
		    UserStatuscomboBox.setModel(new DefaultComboBoxModel(new String[] {"DISCONNECTED", "LOCK"}));
		    UserStatuscomboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		    UserStatuscomboBox.setBounds(214, 487, 155, 37);
		    add(UserStatuscomboBox);
		    
		    JLabel lblPrivilage = new JLabel("Privilage:");
		    lblPrivilage.setFont(new Font("Tahoma", Font.BOLD, 16));
		    lblPrivilage.setBounds(34, 352, 115, 37);
		    add(lblPrivilage);
		    
		    PasswordtextField = new JTextField();
		    PasswordtextField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		    PasswordtextField.setColumns(10);
		    PasswordtextField.setBounds(141, 250, 188, 37);
		    add(PasswordtextField);
		    
		    JLabel lblSubscriptionMethod = new JLabel("Subscription Method:");
		    lblSubscriptionMethod.setFont(new Font("Tahoma", Font.BOLD, 16));
		    lblSubscriptionMethod.setBounds(34, 417, 177, 34);
		    add(lblSubscriptionMethod);
		    
		    JComboBox SubscriptionMethodcomboBox = new JComboBox();
		    SubscriptionMethodcomboBox.setModel(new DefaultComboBoxModel(new String[] {"NONE", "SINGLE", "MONTHLY", "YEARLY"}));
		    SubscriptionMethodcomboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		    SubscriptionMethodcomboBox.setBounds(214, 416, 155, 37);
		    add(SubscriptionMethodcomboBox);
		    
		    JLabel lblFinishdatesub = new JLabel("Finish Date Subscription:");
		    lblFinishdatesub.setFont(new Font("Tahoma", Font.BOLD, 16));
		    lblFinishdatesub.setBounds(417, 417, 213, 34);
		    add(lblFinishdatesub);

		
		}
	}