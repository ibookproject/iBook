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




public class SetAccountSubscriptionGUI extends JPanel {

	private static final long serialVersionUID = 1L;
	public JButton btnBack;
	private JFrame screen;
	private JPanel pann;
	private JTextField textField;

	public SetAccountSubscriptionGUI(JFrame screen) {
		super();
		this.screen=screen;
		pann=this;

		initialize();
	}

	
	private void initialize() {
		
		this.setSize(850, 625);
		this.setLayout(null);	
		
		JLabel lblNewLabel = new JLabel("Set Account Subscription");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(355, 49, 207, 22);
		add(lblNewLabel);
		
		ImageIcon backIcon =new ImageIcon("src/images/backIcon.png");
		btnBack = new JButton(backIcon);
		btnBack.setBounds(39, 52, 89, 23);
		add(btnBack);
		
		JLabel lblEnterUserId = new JLabel("Enter User ID");
		lblEnterUserId.setBounds(34, 160, 94, 14);
		add(lblEnterUserId);
		
		JLabel lblChooseOneOption = new JLabel("Choose one option");
		lblChooseOneOption.setBounds(34, 185, 94, 14);
		add(lblChooseOneOption);
		
		textField = new JTextField();
		textField.setBounds(165, 157, 86, 20);
		add(textField);
		textField.setColumns(10);
		
		JRadioButton rdbtnOne = new JRadioButton("One Time Purchase");
		rdbtnOne.setBounds(165, 181, 145, 23);
		add(rdbtnOne);
		
		JRadioButton rdbtnMonthly = new JRadioButton("Monthly");
		rdbtnMonthly.setBounds(165, 207, 109, 23);
		add(rdbtnMonthly);
		
		JRadioButton rdbtnYearly = new JRadioButton("Yearly");
		rdbtnYearly.setBounds(165, 233, 109, 23);
		add(rdbtnYearly);
	   
	    //Group the radio buttons.
	    ButtonGroup group = new ButtonGroup();
	    group.add(rdbtnOne);
	    group.add(rdbtnMonthly);
	    group.add(rdbtnYearly);
	    
	    JButton btnSet = new JButton("Set");
	    btnSet.setBounds(422, 328, 89, 23);
	    add(btnSet);
	    
	    JLabel label = new JLabel("\u05DB\u05D0\u05DF \u05D9\u05D5\u05E6\u05D2 \u05E9\u05DD \u05D4\u05DE\u05E9\u05EA\u05DE\u05E9 \u05DC\u05E4\u05D9 \u05D4\u05D0\u05D9\u05D9\u05D3\u05D9 \u05D1\u05E8\u05D2\u05E2 \u05E9\u05D9\u05D5\u05E7\u05DC\u05D3 \u05D0\u05D9\u05D9\u05D3\u05D9 \u05DB\u05DC\u05E9\u05D4\u05D5 - \u05D5\u05D0\u05DD \u05D0\u05D9\u05DF \u05DB\u05D6\u05D4 - \u05EA\u05D5\u05E6\u05D2 \u05E9\u05D2\u05D9\u05D0\u05D4");
	    label.setBounds(310, 160, 481, 14);
	    add(label);

/*דוגמא להשתמש ברדיו באטן
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