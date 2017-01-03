
package MenuGUI;

import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Rectangle;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JButton;

import ManagmentGUI.InventoryManagmentGUI;
import MemberGUI.CartManagerGUI;
import MemberGUI.RequestPostFillReviewGUI;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;




public class ReaderMenu extends UserMenu {


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
		
	
		JButton btnNewButton = new JButton("Post / fill review");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				////////////////////////button to back panel from panel /////////////////////////////////////////////
				RequestPostFillReviewGUI Pfr=new RequestPostFillReviewGUI(screen,"ID1234");
				Pfr.btnBack.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						screen.setContentPane(pann);
					}
				////////////////////////button to back panel from panel/////////////////////////////////////////////
				});
				screen.setContentPane(Pfr);//
			}
		});
		btnNewButton.setBounds(325, 138, 223, 23);
		add(btnNewButton);
		
		JButton btnDisplayCartManager = new JButton("Buy From Cart");
		btnDisplayCartManager.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				////////////////////////button to back panel from panel /////////////////////////////////////////////
				CartManagerGUI Bfc=new CartManagerGUI(screen);
				Bfc.btnBack.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						screen.setContentPane(pann);
					}
				////////////////////////button to back panel from panel/////////////////////////////////////////////
				});
				screen.setContentPane(Bfc);//
			}
		});
		btnDisplayCartManager.setBounds(325, 172, 223, 23);
		add(btnDisplayCartManager);
		
	
	}


	

	
}
