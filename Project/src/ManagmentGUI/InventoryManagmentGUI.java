package ManagmentGUI;


import javax.swing.JPanel;

import MenuGUI.LoginGUI;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;




public class InventoryManagmentGUI extends JPanel {

	private static final long serialVersionUID = 1L;
	public JButton btnBack;
	private LoginGUI screen;
	private JPanel pann;

	public InventoryManagmentGUI(LoginGUI screen) {
		super();
		this.screen=screen;
		pann=this;

		initialize();
	}

	
	private void initialize() {
		
		this.setSize(850, 625);
		this.setLayout(null);	
		
		JLabel lblNewLabel = new JLabel("Inventory Managment");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(355, 49, 175, 22);
		add(lblNewLabel);

		JLabel lblinventoryGif = new JLabel("");
		lblinventoryGif.setIcon(new ImageIcon("inventory.gif"));
		lblinventoryGif.setBounds(555, 155, 375, 505);
		add(lblinventoryGif);
		
		JButton btnDelete = new JButton("Delete book");
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				////////////////////////button to back panel from panel /////////////////////////////////////////////
				InventoryManagmentDeleteGUI del=new InventoryManagmentDeleteGUI(screen); 
				del.btnBack.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						screen.setContentPane(pann);
					}
				////////////////////////button to back panel from panel/////////////////////////////////////////////
				});
				screen.setContentPane(del);//send to search book window
			}
		
		});
	
		
		btnDelete.setBounds(191, 176, 145, 30);
		add(btnDelete);
		JButton btnAddupdate = new JButton("Add book");
		btnAddupdate.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnAddupdate.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent e) {
				////////////////////////button to back panel from panel /////////////////////////////////////////////
				AddOrUpdateBookGUI aOu=new AddOrUpdateBookGUI(screen,1); // 1 means add 
				aOu.btnBack.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						screen.setContentPane(pann);
					}
				////////////////////////button to back panel from panel/////////////////////////////////////////////
				});
				screen.setContentPane(aOu);//send to search book window
			}
		});
		btnAddupdate.setBounds(366, 176, 122, 30);
		add(btnAddupdate);
		ImageIcon backIcon =new ImageIcon("src/images/backIcon.png");
		 btnBack = new JButton(backIcon);
		btnBack.setBounds(39, 52, 89, 23);
		add(btnBack);
		
		JButton btnUpdateBook = new JButton("Update book");
		btnUpdateBook.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnUpdateBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				////////////////////////button to back panel from panel /////////////////////////////////////////////
				InventoryManagmentSearchForUpdateGUI search=new InventoryManagmentSearchForUpdateGUI(screen,pann); // 0 means update
				search.btnBack.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						screen.setContentPane(pann);
					}
				////////////////////////button to back panel from panel/////////////////////////////////////////////
				});
				screen.setContentPane(search);//send to search book window
			}
		});
		btnUpdateBook.setBounds(512, 176, 145, 30);
		add(btnUpdateBook);
	
	}
}