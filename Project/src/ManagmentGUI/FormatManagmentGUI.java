package ManagmentGUI;


import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JTable;
import java.awt.SystemColor;
import javax.swing.UIManager;
import java.awt.Color;




public class FormatManagmentGUI extends JPanel {


	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	public JButton btnBack;
	private JTable table;
	private JTable table_1;

	public FormatManagmentGUI( ) {
		super();		
		initialize();
	}

	
	private void initialize() {
		
		this.setSize(850, 600);
		this.setLayout(null);	
		
		JLabel lblFormatManagment = new JLabel("Format managment");
		lblFormatManagment.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblFormatManagment.setBounds(322, 33, 154, 39);
		add(lblFormatManagment);
		
		JLabel lblChooseBook = new JLabel("Choose book for ataching subject :");
		lblChooseBook.setBounds(32, 90, 197, 14);
		add(lblChooseBook);
		
		JList<?> list = new JList<Object>();
		
		list.setBounds(154, 191, -93, -63);
		add(list);
		
		JLabel lblChooseDomain = new JLabel("Choose Domain : ");
		lblChooseDomain.setBounds(308, 90, 114, 14);
		add(lblChooseDomain);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(324, 108, 75, 20);
		add(comboBox_1);
		
		JLabel lblChooseSubjectAt = new JLabel("Choose subject at Domain");
		lblChooseSubjectAt.setBounds(296, 218, 150, 14);
		
		JLabel lblChooseDomain1 = new JLabel("Choose Domain :");
		lblChooseDomain1.setBounds(71, 115, 116, 23);
		add(lblChooseDomain1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(71, 137, 101, 20);
		add(comboBox);
		
		JLabel lblChooseSubject = new JLabel("Choose Subject : ");
		lblChooseSubject.setBounds(71, 168, 95, 14);
		add(lblChooseSubject);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(71, 185, 103, 20);
		add(comboBox_2);
		
		JLabel lblChooseBook1 = new JLabel("Choose Book :");
		lblChooseBook1.setBounds(71, 225, 91, 23);
		add(lblChooseBook1);
		
		JComboBox comboBox_11 = new JComboBox();
		comboBox_11.setBounds(71, 243, 105, 23);
		add(comboBox_11);
		
		add(lblChooseSubjectAt);
		
		JButton btnAddNewDomain = new JButton("Add");
		btnAddNewDomain.setBounds(660, 108, 67, 20);
		add(btnAddNewDomain);
		
		JLabel lblNewDomain = new JLabel("new domain : ");
		lblNewDomain.setBounds(482, 114, 83, 14);
		add(lblNewDomain);
		
		textField = new JTextField();
		textField.setBounds(564, 108, 86, 20);
		add(textField);
		textField.setColumns(10);
		
		JComboBox comboBox_21 = new JComboBox();
		comboBox_21.setBounds(324, 237, 75, 20);
		add(comboBox_21);
		
		JLabel lblNewSubjectAt = new JLabel("new subject at domain : ");
		lblNewSubjectAt.setBounds(434, 240, 176, 14);
		add(lblNewSubjectAt);
		
		textField_1 = new JTextField();
		textField_1.setBounds(564, 237, 86, 20);
		add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(660, 237, 67, 21);
		add(btnAdd);
		
		JButton btnAtachBook = new JButton("atach book to subject");
		btnAtachBook.setBounds(311, 440, 165, 32);
		add(btnAtachBook);
		
		
		ImageIcon backIcon =new ImageIcon("src/images/backIcon.png");
		btnBack = new JButton(backIcon);
		btnBack.setBounds(84, 44, 67, 20);
		add(btnBack);
		
		JLabel lblDbAnswer = new JLabel("DB answer");
		lblDbAnswer.setBounds(670, 129, 75, 14);
		add(lblDbAnswer);
		
		JLabel lblDbAnswer_1 = new JLabel("DB answer");
		lblDbAnswer_1.setBounds(670, 257, 67, 14);
		add(lblDbAnswer_1);
		
		table = new JTable();
		table.setBackground(SystemColor.inactiveCaptionBorder);
		table.setBounds(239, 83, 527, 262);
		add(table);
		
		table_1 = new JTable();
		table_1.setBackground(SystemColor.inactiveCaptionBorder);
		table_1.setBounds(10, 83, 221, 262);
		add(table_1);
	
	}
}