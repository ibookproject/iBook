package MemberGUI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;




public class SearchReviewGUI extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JButton btnBack ;
	
	public SearchReviewGUI(JFrame screen) {
		super();
		initialize();
	}

	
	private void initialize() {
		
		this.setSize(850, 625);
		this.setLayout(null);	
		
		JLabel lblNewLabel = new JLabel("Search Review's of book");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(352, 59, 211, 14);
		add(lblNewLabel);
		
		JLabel lblChooseDomain = new JLabel("Choose Domain :");
		lblChooseDomain.setBounds(335, 120, 116, 23);
		add(lblChooseDomain);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(335, 142, 101, 20);
		add(comboBox);
		
		JLabel lblChooseSubject = new JLabel("Choose Subject : ");
		lblChooseSubject.setBounds(335, 173, 95, 14);
		add(lblChooseSubject);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(335, 190, 103, 20);
		add(comboBox_2);
		
		JLabel lblChooseBook = new JLabel("Choose Book :");
		lblChooseBook.setBounds(335, 230, 91, 23);
		add(lblChooseBook);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(335, 248, 105, 23);
		add(comboBox_1);
		
		
		
		
		
		
		ImageIcon backIcon =new ImageIcon("src/images/backIcon.png");
		btnBack = new JButton(backIcon);
	//	btnBack.setIcon(backIcon);
		btnBack.setBounds(39, 52, 89, 23);
		add(btnBack);
		
	
	}
}