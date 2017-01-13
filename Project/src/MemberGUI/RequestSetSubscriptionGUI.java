package MemberGUI;

import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JLabel;

import Controller.UserController;
import MenuGUI.LoginGUI;
import Role.User;
import Role.UserStatus;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;




public class RequestSetSubscriptionGUI extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JButton btnBack ;
	private int radioButtonChoose = 0;// integer to sent what the radio button
	private LoginGUI screen;
	private JPanel pann;
	
	public RequestSetSubscriptionGUI(LoginGUI screen) {
		super();
		this.screen = screen;
		pann = this;
		initialize();
	}

	/**
	 * This method initializes StudentForm
	 */
	private void initialize() {
		
		this.setSize(850, 625);
		this.setLayout(null);	
		ImageIcon backIcon =new ImageIcon("src/images/backIcon.png");
		btnBack = new JButton(backIcon);// declaration of back button
		btnBack.setBounds(48, 47, 84, 23);
		add(btnBack);
		
		JRadioButton rdbtnSingl = new JRadioButton("choose single subscription");
		rdbtnSingl.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			radioButtonChoose = UserStatus.SINGLE;
			}
		});
			rdbtnSingl.setBounds(133, 217, 177, 25);
		add(rdbtnSingl);
		
		JRadioButton rdbtnMonthly = new JRadioButton("choose monthly subscription");
		rdbtnMonthly.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				radioButtonChoose = UserStatus.MONTHLY;
				}
			});
		rdbtnMonthly.setBounds(344, 217, 202, 25);
		add(rdbtnMonthly);
		
		JRadioButton rdbtnYearly = new JRadioButton("choose yearly subscription");
		rdbtnYearly.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				radioButtonChoose = UserStatus.YEARLY;
				}
			});
		rdbtnYearly.setBounds(550, 217, 194, 25);
		add(rdbtnYearly);
		
		JLabel lblRequestToSet = new JLabel("REQUEST TO SET SUBSCRIPTION");
		lblRequestToSet.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblRequestToSet.setBounds(296, 78, 290, 16);
		add(lblRequestToSet);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnSingl);
		group.add(rdbtnMonthly);
		group.add(rdbtnYearly);
		User u = new User(screen.getTempID());
		JButton btnSendRequest = new JButton("Send Request");
		btnSendRequest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(radioButtonChoose==0)
					JOptionPane.showMessageDialog(screen,"                          Sorry!\nNo selected one of options", "Warning",JOptionPane.WARNING_MESSAGE);
				else
				{
					ArrayList<User> temp= (ArrayList<User>) UserController.SearchUser("subscriptionRequest",u,"userID=\""+screen.getTempID()+"\"",screen.getClient());
				
					if(radioButtonChoose==UserStatus.SINGLE)
					{
						if(temp.get(0).getSubscriptionRequest()==UserStatus.SINGLE)
							JOptionPane.showMessageDialog(screen,"This request alredy sent! ", "Warning",JOptionPane.WARNING_MESSAGE);
						else
						{
							UserController.UpdateUserStatus(u, "subscriptionRequest=\""+UserStatus.SINGLE+"\"", "userID=\""+screen.getTempID()+"\"", screen.getClient());
							JOptionPane.showMessageDialog(screen,"Send request Subscription sucsseccfully", "Warning",JOptionPane.WARNING_MESSAGE);
						}
					}
					else
					{
						if(radioButtonChoose==UserStatus.MONTHLY)
						{
							if(temp.get(0).getSubscriptionRequest()==UserStatus.MONTHLY)
								JOptionPane.showMessageDialog(screen,"This request alredy sent! ", "Warning",JOptionPane.WARNING_MESSAGE);
							else
							{
								UserController.UpdateUserStatus(u, "subscriptionRequest=\""+UserStatus.MONTHLY+"\"", "userID=\""+screen.getTempID()+"\"", screen.getClient());
								JOptionPane.showMessageDialog(screen,"Send request Subscription sucsseccfully", "Warning",JOptionPane.WARNING_MESSAGE);
							}
						}
						else
							if(radioButtonChoose==UserStatus.YEARLY)
							{
								if(temp.get(0).getSubscriptionRequest()==UserStatus.YEARLY)
									JOptionPane.showMessageDialog(screen,"This request alredy sent! ", "Warning",JOptionPane.WARNING_MESSAGE);
								else
								{
									UserController.UpdateUserStatus(u, "subscriptionRequest=\""+UserStatus.YEARLY+"\"", "userID=\""+screen.getTempID()+"\"", screen.getClient());
									JOptionPane.showMessageDialog(screen,"Send request Subscription sucsseccfully", "Warning",JOptionPane.WARNING_MESSAGE);
								}
							}
					
					}
				}
			}
		});
		btnSendRequest.setBounds(385, 348, 127, 25);
		add(btnSendRequest);
	}
	
}
