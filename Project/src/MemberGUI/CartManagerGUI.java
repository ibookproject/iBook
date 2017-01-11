package MemberGUI;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Component;
import java.awt.Font;
import javax.swing.JScrollBar;
import javax.swing.JList;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;




public class CartManagerGUI extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JButton btnBack ;
	private int UserIdAtDataBase;
	public CartManagerGUI(JFrame screen,int UserIdAtDataBase) {
		super();
		initialize();
		this.UserIdAtDataBase=UserIdAtDataBase;
		System.out.println(this.UserIdAtDataBase);
	}

	/**
	 * This method initializes StudentForm
	 */
	private void initialize() {
		
		this.setSize(850, 625);
		this.setLayout(null);	
		ImageIcon backIcon =new ImageIcon("src/images/backIcon.png");
		btnBack = new JButton(backIcon);// declaration of back button
		btnBack.setBounds(39, 52, 89, 23);
		add(btnBack);
		
		JLabel lblButFromCart = new JLabel("Buy From Cart");
		lblButFromCart.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblButFromCart.setBounds(379, 52, 163, 23);
		add(lblButFromCart);
		
		JLabel lblChooseBooksFrom = new JLabel("Choose book's from cart :");
		lblChooseBooksFrom.setBounds(379, 107, 151, 14);
		add(lblChooseBooksFrom);
		
		JButton btnBuy = new JButton("Buy");
		btnBuy.setBounds(402, 284, 59, 23);
		add(btnBuy);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(368, 132, 142, 20);
		add(comboBox);
		
		
		
	/*	
		JPanel panel = new JPanel();
		panel.setBounds(55, 166, 163, 141);
		for(int i=0;i<7;i++)
		panel.add(new JCheckBox("New check box" + i));
		//add(panel);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(292, 170, 151, 48);
		scrollBar.add(new JCheckBox("New check box"));
		
		scrollBar.add(panel);
		add(scrollBar);
*/
		//for(int i=0;i<7;i++)
		//panel.add(new JCheckBox("New check box" + i))
			
	}
}