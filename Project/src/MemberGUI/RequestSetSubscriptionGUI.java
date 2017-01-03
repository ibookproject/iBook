package MemberGUI;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import java.awt.Font;




public class RequestSetSubscriptionGUI extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JButton btnBack ;
	
	public RequestSetSubscriptionGUI(JFrame screen) {
		super();
		initialize();
	}

	/**
	 * This method initializes StudentForm
	 */
	private void initialize() {
		
		this.setSize(850, 625);
		this.setLayout(null);	
		ImageIcon backIcon =new ImageIcon("src/images/backIcon.png");
		btnBack = new JButton(backIcon);// declaration of back button
		btnBack.setBounds(48, 47, 84, 23);
		add(btnBack);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("choose single subscription");
		rdbtnNewRadioButton.setBounds(133, 217, 177, 25);
		add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("choose monthly subscription");
		rdbtnNewRadioButton_1.setBounds(344, 217, 202, 25);
		add(rdbtnNewRadioButton_1);
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("choose yearly subscription");
		rdbtnNewRadioButton_2.setBounds(550, 217, 194, 25);
		add(rdbtnNewRadioButton_2);
		
		JLabel lblRequestToSet = new JLabel("REQUEST TO SET SUBSCRIPTION");
		lblRequestToSet.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblRequestToSet.setBounds(296, 78, 290, 16);
		add(lblRequestToSet);
		
		JButton btnSendRequest = new JButton("Send Request");
		btnSendRequest.setBounds(385, 348, 127, 25);
		add(btnSendRequest);
	
	}
}
