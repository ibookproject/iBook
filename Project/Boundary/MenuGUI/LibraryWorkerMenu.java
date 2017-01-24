
package MenuGUI;

import java.awt.Font;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Rectangle;

import javax.swing.JTextField;

import ManagmentGUI.CreateNewAccountGUI;
import ManagmentGUI.SetAccountSubscriptionGUI;
import MemberGUI.SearchBookGUI;

import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LibraryWorkerMenu extends ReaderMenu {

	public LibraryWorkerMenu(LoginGUI screen ,int UserIdAtDataBase) {
		super(screen,UserIdAtDataBase);
		this.screen=screen;
		pann=this;
		initialize();
	}

	/**
	 * This method initializes StudentForm
	 */
	private void initialize() {
		
		this.setSize(850, 600);
		this.setLayout(null);	
		
		JButton btnCreateNewAcount = new JButton("Create acount");
		btnCreateNewAcount.setIcon(new ImageIcon("Extras/Images/NewAcount.png"));
		btnCreateNewAcount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				////////////////////////button to back panel from panel /////////////////////////////////////////////
				CreateNewAccountGUI cna=new CreateNewAccountGUI(screen);
				cna.btnBack.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						screen.setContentPane(pann);
					}
				////////////////////////button to back panel from panel/////////////////////////////////////////////
				});
				screen.setContentPane(cna);//send to search book window
			}
		});
		btnCreateNewAcount.setBounds(12, 318, 193, 53);
		btnCreateNewAcount.setFont(new Font("Tahoma", Font.BOLD, 13));
		add(btnCreateNewAcount);	
		
		JButton btnSetAcountSubscription = new JButton("Set Subscription");
		btnSetAcountSubscription.setIcon(new ImageIcon("Extras/Images/ok.png"));
		btnSetAcountSubscription.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				////////////////////////button to back panel from panel /////////////////////////////////////////////
				SetAccountSubscriptionGUI sas=new SetAccountSubscriptionGUI(screen);
				sas.btnBack.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						screen.setContentPane(pann);
					}
				////////////////////////button to back panel from panel/////////////////////////////////////////////
				});
				screen.setContentPane(sas);//send to search book window
			}
		});
		btnSetAcountSubscription.setBounds(12, 381, 193, 53);
		btnSetAcountSubscription.setFont(new Font("Tahoma", Font.BOLD, 13));
		add(btnSetAcountSubscription);
	
	}


	
}
