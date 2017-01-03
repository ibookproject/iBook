
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

import ManagmentGUI.ConfirmationReviewGUI;
import ManagmentGUI.FormatManagmentGUI;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;




public class QualifiedEditorMenu extends LibraryWorkerMenu {

	private int Permssion;
	
	public QualifiedEditorMenu(LoginGUI screen,int permission) {
		super(screen);
		this.screen=screen;
		pann=this;
		this.Permssion=permission;
		initialize();
	}

	/**
	 * This method initializes StudentForm
	 */
	private void initialize() {
		
		this.setSize(850, 625);
		this.setLayout(null);	
		
		
		JButton btnSendingConfirmationReview = new JButton("Sending Confirmation Review");
		if(this.Permssion==4){
		btnSendingConfirmationReview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				////////////////////////button to back panel from panel /////////////////////////////////////////////
				
				ConfirmationReviewGUI Scr=new ConfirmationReviewGUI(screen,4);
				Scr.btnBack.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						screen.setContentPane(pann);
					}
				////////////////////////button to back panel from panel/////////////////////////////////////////////
				});
				screen.setContentPane(Scr);//
			}
		});}
		else if((this.Permssion==5)||(this.Permssion==6))
		{
			btnSendingConfirmationReview.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					////////////////////////button to back panel from panel /////////////////////////////////////////////
					
					ConfirmationReviewGUI Scr=new ConfirmationReviewGUI(screen,5);
					Scr.btnBack.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							screen.setContentPane(pann);
						}
					////////////////////////button to back panel from panel/////////////////////////////////////////////
					});
					screen.setContentPane(Scr);//
				}
			});}
		btnSendingConfirmationReview.setBounds(325, 272, 223, 23);
		add(btnSendingConfirmationReview);
	
	}


	
}
