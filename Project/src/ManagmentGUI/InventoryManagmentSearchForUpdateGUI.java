package ManagmentGUI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Book.book;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;




public class InventoryManagmentSearchForUpdateGUI extends JPanel {

	private static final long serialVersionUID = 1L;
	public JButton btnBack ;
	private JFrame screen;
	private JPanel pann;
	ArrayList<book> books;	// return the books that choosen


	public InventoryManagmentSearchForUpdateGUI(JFrame screen) {
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
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBack.setBounds(11, 33, 89, 23);
		add(btnBack);
		
		JLabel lblSearchBookFor = new JLabel("Search Book For Update");
		lblSearchBookFor.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSearchBookFor.setBounds(361, 33, 195, 46);
		add(lblSearchBookFor);
		
		
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
		
		
		
		
		
		
		JButton btnSend = new JButton("Select");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				////////////////////////button to back panel from panel /////////////////////////////////////////////
				AddOrUpdateBookGUI goback=new AddOrUpdateBookGUI(screen,0, books); 
				goback.btnBack.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						screen.setContentPane(pann);
					}
				////////////////////////button to back panel from panel/////////////////////////////////////////////
				});
				screen.setContentPane(goback);//send to search book window
			}
		
		});
				
		btnSend.setBounds(335, 318, 89, 23);
		add(btnSend);
	
	}
}
