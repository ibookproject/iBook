
package MenuGUI;

import java.awt.Font;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JButton;

import ManagmentGUI.InventoryManagmentGUI;
import MemberGUI.CartManagerGUI;
import MemberGUI.RequestPostFillReviewGUI;
import MemberGUI.SearchBook;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;




public class ReaderMenu extends UserMenu {

		public int UserIdAtDataBase1; // save the user id at the data base , need it for the buy from cart query
	/**
	 * @wbp.parser.constructor
	 */
	public ReaderMenu(LoginGUI screen,int UserIdAtDataBase) {
		super(screen);
		this.screen=screen;
		pann=this;
		initialize();
		this.UserIdAtDataBase1=UserIdAtDataBase;
	}
	
	public ReaderMenu(LoginGUI screen) {
		super(screen);
		this.screen=screen;
		pann=this;
		initialize();
	}

	/**
	 * This method initializes StudentForm
	 */
	private void initialize() {
		
		this.setSize(850, 625);
		this.setLayout(null);
		
	
		
		JButton btnDisplayCartManager = new JButton("Buy From Cart");
		btnDisplayCartManager.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				////////////////////////button to back panel from panel /////////////////////////////////////////////
				CartManagerGUI Bfc=new CartManagerGUI(screen,UserIdAtDataBase1);
				Bfc.btnBack.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						screen.setContentPane(pann);
					}
				////////////////////////button to back panel from panel/////////////////////////////////////////////
				});
				screen.setContentPane(Bfc);//
			}
		});
		btnDisplayCartManager.setBounds(12, 255, 193, 53);
		btnDisplayCartManager.setIcon(new ImageIcon("Extras/Images/cart.png"));
		btnDisplayCartManager.setFont(new Font("Tahoma", Font.BOLD, 13));
		add(btnDisplayCartManager);
		
		
	
	}


	

	
}
