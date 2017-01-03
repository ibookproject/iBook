package ManagmentGUI;


import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;




public class ConfirmationReviewGUI extends JPanel {

	private static final long serialVersionUID = 1L;
	public JButton btnBack ;
	private JFrame screen;
	private JPanel pann;
	private int Permission;
	
	public ConfirmationReviewGUI(JFrame screen,int permission) {
		super();
		this.screen=screen;
		pann=this;
		this.Permission=permission;
		initialize();
	}


	private void initialize() {
		
		this.setSize(850, 625);
		this.setLayout(null);	
		ImageIcon backIcon =new ImageIcon("src/images/backIcon.png");
		btnBack = new JButton(backIcon);// declaration of back button
		btnBack.setBounds(39, 52, 89, 23);
		add(btnBack);
		
		JLabel confirmLbl = new JLabel("Confirmation Review");
		confirmLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		confirmLbl.setBounds(355, 49, 175, 22);
		add(confirmLbl);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(174, 109, 549, 387);
		add(textArea);
		if(this.Permission==5){
		JButton btnRemovePartReview = new JButton("Remove Part Of Review");
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
		
	
		JButton btnNotConfirm = new JButton("Not Confirm");
		btnNotConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNotConfirm.setBounds(388, 509, 109, 25);
		add(btnNotConfirm);
		
		JButton btnConfirm = new JButton("Confirm");
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