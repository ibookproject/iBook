package ManagmentGUI;


import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextArea;


public class ShowBookRate extends JPanel {

	private static final long serialVersionUID = 1L;
	public JButton btnBack ;
	private JFrame screen;
	private JPanel pann;
	
	public ShowBookRate(JFrame screen) {
		super();
		this.screen=screen;
		pann=this;

		initialize();
	}

	private void initialize() {
		
		this.setSize(850, 625);
		this.setLayout(null);	
		
		JLabel lblNewLabel = new JLabel("Book Rate");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(355, 49, 175, 22);
		add(lblNewLabel);
		
		
		ImageIcon backIcon =new ImageIcon("src/images/backIcon.png");
		btnBack = new JButton(backIcon);
		btnBack.setBounds(39, 52, 89, 23);
		add(btnBack);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(124, 130, 628, 457);
		add(textArea);
		
	}


}
