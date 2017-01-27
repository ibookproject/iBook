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

import Controller.UserController;
import ManagmentGUI.ConfirmationReviewGUI;
import ManagmentGUI.FormatManagmentGUI;
import Role.User;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 * This class is the menu of qualified editor- show to him all the functionality according to the user privilege
 * @author Coral Carmeli
 *
 */


public class QualifiedEditorMenu extends LibraryWorkerMenu {

	private int Permssion;
	/**
	 * Constructor of the QualifiedEditorMenu class
	 * @param screen This is the main window-login
	 * @param permission This is flag which decide who is the user right now-check if this is qualified editor or 
	 * librarian and send it to the window of confirmation review
	 * @author  Coral Carmeli
	 */	
		
	public QualifiedEditorMenu(LoginGUI screen,int permission) {
		super(screen,permission);
		this.screen=screen;
		pann=this;
		User u = new User();
		ArrayList<User> user= (ArrayList<User>) UserController.SearchUser("privilege",u,"userID=\""+screen.getTempID()+"\"",screen.getClient());
		if(user!=null)
			this.Permssion=user.get(0).getPriviliege();
		else
			this.Permssion=0;
		//this.Permssion=permission;
		initialize();
	}

	/**
	 * This method initialize The window of Qualified Editor Menu-put the components on the screen and set their functionality
	 * and even send the permission of the user to the next window
	 * @author  Coral Carmeli
	 */
	private void initialize() {
		
		this.setSize(850, 625);
		this.setLayout(null);	
		
		
		JButton btnSendingConfirmationReview = new JButton("Confirm Review");
		btnSendingConfirmationReview.setIcon(new ImageIcon("Extras/Images/ok.png"));
		btnSendingConfirmationReview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				////////////////////////button to back panel from panel /////////////////////////////////////////////
				
				ConfirmationReviewGUI Scr=new ConfirmationReviewGUI(screen,Permssion);
				Scr.btnBack.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						screen.setContentPane(pann);
					}
				////////////////////////button to back panel from panel/////////////////////////////////////////////
				});
				screen.setContentPane(Scr);//
			}
		});
	
		btnSendingConfirmationReview.setBounds(12, 444, 193, 53);
		btnSendingConfirmationReview.setFont(new Font("Tahoma", Font.BOLD, 13));
		add(btnSendingConfirmationReview);
	
	}


	
}