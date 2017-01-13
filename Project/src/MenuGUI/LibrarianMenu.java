
package MenuGUI;

import javax.swing.JLabel;

import ManagmentGUI.FormatManagmentGUI;
import ManagmentGUI.InventoryManagmentGUI;
import ManagmentGUI.RemovePartReviewGUI;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;




public class LibrarianMenu extends QualifiedEditorMenu {

	private static final long serialVersionUID = 1L;
	private int Permission;
	//private JFrame screen;
	private JPanel pann;
	
	public LibrarianMenu(LoginGUI screen,int permission,int UserIdAtDataBase) {
		super(screen,permission,UserIdAtDataBase);
		this.screen=screen;
		pann=this;
		this.Permission=permission;
		initialize();
	}
//	public LibrarianMenu() {} // empty constructor just for "labriran manager worker" that extend will no have errors ... 

	/**
	 * This method initializes StudentForm
	 */
	private void initialize() {
		
		this.setSize(850, 625);
		this.setLayout(null);	
		
		JButton btnInventoryManagment = new JButton("Inventory managment");
		
		btnInventoryManagment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				////////////////////////button to back panel from panel /////////////////////////////////////////////
				InventoryManagmentGUI Im=new InventoryManagmentGUI(screen);
				Im.btnBack.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						screen.setContentPane(pann);
					}
				////////////////////////button to back panel from panel/////////////////////////////////////////////
				});
				screen.setContentPane(Im);//
			}
		});
		btnInventoryManagment.setBounds(325, 305, 223, 23);
		add(btnInventoryManagment);
		
		JButton btnFormatManagment = new JButton("Format managment");
		btnFormatManagment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				////////////////////////button to back panel from panel /////////////////////////////////////////////
				FormatManagmentGUI Fm=new FormatManagmentGUI(screen);
				Fm.btnBack.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						screen.setContentPane(pann);
					}
				////////////////////////button to back panel from panel/////////////////////////////////////////////
				});
				screen.setContentPane(Fm);//
			}
		});
		btnFormatManagment.setBounds(325, 339, 223, 23);
		add(btnFormatManagment);

	}
}
