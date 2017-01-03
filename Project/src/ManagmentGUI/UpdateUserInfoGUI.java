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




public class UpdateUserInfoGUI extends JPanel {



		private static final long serialVersionUID = 1L;
		public JButton btnBack;
		private JFrame screen;
		private JPanel pann;
		private JTextField textField;
		private JTextField textField_1;
		private JTextField textField_2;
		private JTextField textField_3;

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
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblNewLabel.setBounds(355, 49, 248, 22);
			add(lblNewLabel);
			
			ImageIcon backIcon =new ImageIcon("src/images/backIcon.png");
			btnBack = new JButton(backIcon);
			btnBack.setBounds(39, 52, 89, 23);
			add(btnBack);
			
			JLabel lblEnterUserId = new JLabel("Choose Worker");
			lblEnterUserId.setBounds(34, 160, 115, 14);
			add(lblEnterUserId);
			
			JLabel lblChooseOneOption = new JLabel("Role");
			lblChooseOneOption.setBounds(34, 235, 115, 14);
			add(lblChooseOneOption);
		   
		    
		    JComboBox comboBox = new JComboBox(); // יהיה מסוג יוזר
		    comboBox.setBounds(159, 157, 160, 20);
		    add(comboBox);
		    
		    JComboBox comboBox_1 = new JComboBox();
		    comboBox_1.setBounds(159, 232, 160, 20);
		    comboBox_1.addItem("User");
		    comboBox_1.addItem("Reader");
		    comboBox_1.addItem("Worker");
		    comboBox_1.addItem("Qualified Editor");
		    comboBox_1.addItem("Manager");
		    add(comboBox_1);
		    
		    JLabel lblFirstName = new JLabel("First name");
		    lblFirstName.setBounds(34, 185, 115, 14);
		    add(lblFirstName);
		    
		    JLabel lblLastName = new JLabel("Last name");
		    lblLastName.setBounds(34, 210, 115, 14);
		    add(lblLastName);
		    
		    textField = new JTextField();
		    textField.setBounds(159, 182, 160, 20);
		    add(textField);
		    textField.setColumns(10);
		    
		    textField_1 = new JTextField();
		    textField_1.setBounds(159, 207, 160, 20);
		    add(textField_1);
		    textField_1.setColumns(10);
		    
		    JLabel lblEmail = new JLabel("Email");
		    lblEmail.setBounds(34, 260, 115, 14);
		    add(lblEmail);
		    
		    textField_2 = new JTextField();
		    textField_2.setBounds(159, 257, 160, 20);
		    add(textField_2);
		    textField_2.setColumns(10);
		    
		    JLabel lblPassword = new JLabel("Password");
		    lblPassword.setBounds(34, 285, 115, 14);
		    add(lblPassword);
		    
		    textField_3 = new JTextField();
		    textField_3.setBounds(159, 282, 160, 20);
		    add(textField_3);
		    textField_3.setColumns(10);
		    
		    JButton btnNewButton = new JButton("Update");
		    btnNewButton.setBounds(405, 528, 89, 23);
		    add(btnNewButton);

	/*
		    JRadioButton catButton = new JRadioButton(catString);
		    catButton.setMnemonic(KeyEvent.VK_C);
		    catButton.setActionCommand(catString);

		    JRadioButton dogButton = new JRadioButton(dogString);
		    dogButton.setMnemonic(KeyEvent.VK_D);
		    dogButton.setActionCommand(dogString);

		    JRadioButton rabbitButton = new JRadioButton(rabbitString);
		    rabbitButton.setMnemonic(KeyEvent.VK_R);
		    rabbitButton.setActionCommand(rabbitString);

		    JRadioButton pigButton = new JRadioButton(pigString);
		    pigButton.setMnemonic(KeyEvent.VK_P);
		    pigButton.setActionCommand(pigString);

		    //Group the radio buttons.
		    ButtonGroup group = new ButtonGroup();
		    group.add(birdButton);
		    group.add(catButton);
		    group.add(dogButton);
		    group.add(rabbitButton);
		    group.add(pigButton);

		    //Register a listener for the radio buttons.
		    birdButton.addActionListener(this);
		    catButton.addActionListener(this);
		    dogButton.addActionListener(this);
		    rabbitButton.addActionListener(this);
		    pigButton.addActionListener(this);*/
		
		}
	}