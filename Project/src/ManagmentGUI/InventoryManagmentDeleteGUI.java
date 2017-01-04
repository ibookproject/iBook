package ManagmentGUI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Book.book;
import Book.Domain;
import Controller.bookController;
import MenuGUI.LoginGUI;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JComboBox;




public class InventoryManagmentDeleteGUI extends JPanel {

	private static final long serialVersionUID = 1L;

	public JButton btnBack ;
	LoginGUI screen;

	public InventoryManagmentDeleteGUI(LoginGUI screen ) {
		super();
		this.screen=screen;
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
		
		JLabel lblDeleteBook = new JLabel("Delete Book");
		lblDeleteBook.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDeleteBook.setBounds(365, 33, 159, 39);
		add(lblDeleteBook);
		
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
		
		
		

		
		
		//ArrayList<domain> result = bookController.GetAllDomain(screen.getClient());//call search book method from book controller
		
		
	
		
		
		
		
		
		
		
		
		
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					int answer=JOptionPane.showConfirmDialog(null, "are you sure you want to delte this book ?","Warning !!", JOptionPane.YES_NO_OPTION);
				System.out.println(answer);
				if(answer==0)
				{
					//means delete ..... 
				}// if it 0 mean no so do nothing . 
			}
		});
		btnDelete.setBounds(335, 303, 101, 30);
		add(btnDelete);
		
	
	
	}
}