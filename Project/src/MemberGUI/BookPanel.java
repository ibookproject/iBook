package MemberGUI;

import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;

public class BookPanel extends JPanel{
	public BookPanel() {
		setBackground(Color.WHITE);
		setBorder(new LineBorder(new Color(0, 0, 0), 5));
		setPreferredSize(new Dimension(700, 171));
		setLayout(null);
		
		JButton btnNewButton = new JButton("ex");
		btnNewButton.setBounds(150, 73, 89, 23);
		add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(392, 109, 46, 14);
		add(lblNewLabel);
	}
}
