package ManagmentGUI;


import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;

import MenuGUI.LoginGUI;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;




public class ConfirmationReviewGUI extends JPanel {

	private static final long serialVersionUID = 1L;
	public JButton btnBack ;
	private LoginGUI screen;
	private JPanel pann;
	private int Permission;
	private ImageIcon backIcon;
	private JLabel confirmLbl ;
	private JTextArea textArea ;
	private JButton btnRemovePartReview;
	private JButton btnNotConfirm;
	private JButton btnConfirm ;
	
	public ConfirmationReviewGUI(LoginGUI screen,int permission) {
		super();
		this.screen=screen;
		pann=this;
		this.Permission=permission;
		initialize();
	}


	private void initialize() {
		
		this.setSize(850, 625);
		this.setLayout(null);	
		
		backIcon =new ImageIcon("src/images/backIcon.png");
		btnBack = new JButton(backIcon);// declaration of back button
		btnBack.setBounds(39, 52, 89, 23);
		add(btnBack);
		
		confirmLbl = new JLabel("Confirmation Review");
		confirmLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		confirmLbl.setBounds(355, 49, 175, 22);
		add(confirmLbl);
		
		textArea = new JTextArea();
		textArea.setBounds(174, 109, 549, 387);
		add(textArea);
		if(this.Permission==5){
		btnRemovePartReview = new JButton("Remove Part Of Review");
		btnRemovePartReview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
////////////////////////button to back to confirmation review from remove part of review /////////////////////////////////////////////
				RemovePartReviewGUI Rpr=new RemovePartReviewGUI(screen);
				Rpr.btnBack.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e) 
					{
						screen.setContentPane(pann);
					}
////////////////////////button to back to confirmation review from remove part of review/////////////////////////////////////////////
				});
		screen.setContentPane(Rpr);//
			}
		});
		
		btnRemovePartReview.setBounds(536, 509, 187, 25);
		add(btnRemovePartReview);}
		
	
		btnNotConfirm = new JButton("Not Confirm");
		btnNotConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNotConfirm.setBounds(388, 509, 109, 25);
		add(btnNotConfirm);
		
		btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnConfirm.setBounds(230, 509, 109, 25);
		add(btnConfirm);
		
		/*JRadioButton rdbtnConfirm = new JRadioButton("Remove Part Of Review");
		rdbtnConfirm.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				
			}
		});
		rdbtnConfirm.setBounds(67, 545, 123, 25);
		add(rdbtnConfirm);*/
		
	
	}
}