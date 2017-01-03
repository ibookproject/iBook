package ManagmentGUI;

import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;




public class StatisticsUserReportGUI extends JPanel {

	
	private static final long serialVersionUID = 1L;
	public JButton btnBack ;
	private JTextField textField;
	private JFrame screen;
	private JPanel pann;
	
	public StatisticsUserReportGUI(JFrame screen) {
		super();
		initialize();
		this.screen=screen;
		pann=this;

	}


	private void initialize() {
		
		this.setSize(850, 625);
		this.setLayout(null);	
		
		JLabel userReportLbl = new JLabel("Statistics User Report");
		userReportLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		userReportLbl.setBounds(355, 49, 175, 22);
		add(userReportLbl);
		
		ImageIcon backIcon =new ImageIcon("src/images/backIcon.png");
		btnBack = new JButton(backIcon);// declaration of back button
		btnBack.setBounds(39, 52, 89, 23);
		add(btnBack);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(161, 130, 107, 20);
		add(comboBox);
		
		JLabel lblListOfBook = new JLabel("List of Users by ID");
		lblListOfBook.setBounds(39, 133, 137, 14);
		add(lblListOfBook);
		
		JButton btnGetReports = new JButton("Get report");
		btnGetReports.setBounds(598, 129, 107, 23);
		add(btnGetReports);
		
		JTextArea txtrReport = new JTextArea();
		txtrReport.setBounds(205, 175, 500, 300);
		add(txtrReport);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(309, 129, 48, 22);
		add(lblDate);
		
		textField = new JTextField();
		textField.setBounds(343, 129, 122, 21);
		add(textField);
		textField.setColumns(10);
	
	
	}
}
