package ManagmentGUI;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;



public class RemovePartReviewGUI extends JPanel {

	private static final long serialVersionUID = 1L;
	public JButton btnBack;
	private JFrame screen;
	private JPanel pann;

	public RemovePartReviewGUI(JFrame screen) {
		super();
		this.screen=screen;
		pann=this;

		initialize();
	}

	
	private void initialize() {
		
		this.setSize(850, 625);
		this.setLayout(null);	
		
		
		ImageIcon backIcon =new ImageIcon("src/images/backIcon.png");
		 btnBack = new JButton(backIcon);
		 btnBack.setBounds(39, 52, 89, 23);
		 add(btnBack);
		 
		 JLabel lblRemovePartOf = new JLabel("REMOVE PART OF REVIEW");
		 lblRemovePartOf.setFont(new Font("Tahoma", Font.PLAIN, 18));
		 lblRemovePartOf.setBounds(322, 79, 234, 16);
		 add(lblRemovePartOf);
		 
		 JTextArea textArea = new JTextArea();
		 textArea.setFont(new Font("Courier New", Font.PLAIN, 13));
		 textArea.setText("\u05DB\u05D0\u05DF \u05EA\u05D5\u05E4\u05D9\u05E2 \u05D1\u05D9\u05E7\u05D5\u05E8\u05EA \u05DE\u05DE\u05E0\u05D4 \u05D9\u05D4\u05D9\u05D4 \u05D0\u05E4\u05E9\u05E8 \u05DC\u05DE\u05D7\u05D5\u05E7 \u05D7\u05DC\u05E7\u05D9\u05DD");
		 textArea.setBounds(136, 172, 556, 301);
		 add(textArea);
		 
		 JButton btnNewButton = new JButton("Submit");
		 btnNewButton.setBounds(369, 517, 97, 25);
		 add(btnNewButton);
		
	
	
	}
}