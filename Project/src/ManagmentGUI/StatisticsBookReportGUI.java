package ManagmentGUI;


import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JTextArea;




public class StatisticsBookReportGUI extends JPanel {

	
	private static final long serialVersionUID = 1L;
	public JButton btnBack;
	private JFrame screen;
	private JPanel pann;

	public StatisticsBookReportGUI(JFrame screen) {
		super();
		this.screen=screen;
		pann=this;

		initialize();
	}

	
	private void initialize() {
		
		this.setSize(850, 625);
		this.setLayout(null);	
		
		JLabel lblNewLabel = new JLabel("Statistics Book Report");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(355, 49, 175, 22);
		add(lblNewLabel);
		
		ImageIcon backIcon =new ImageIcon("src/images/backIcon.png");
		btnBack = new JButton(backIcon);
		btnBack.setBounds(39, 52, 89, 23);
		add(btnBack);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(205, 130, 107, 20);
		add(comboBox);
		
		JLabel lblListOfBook = new JLabel("List of books by name");
		lblListOfBook.setBounds(39, 133, 137, 14);
		add(lblListOfBook);
		
		JButton btnGetReports = new JButton("Get report");
		btnGetReports.setBounds(404, 129, 107, 23);
		add(btnGetReports);
		
		JTextArea txtrReport = new JTextArea();
		txtrReport.setBounds(205, 175, 500, 300);
		add(txtrReport);
	
	}
}