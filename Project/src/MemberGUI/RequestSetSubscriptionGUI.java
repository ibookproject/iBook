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
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.MatteBorder;




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
		
		JRadioButton rdbtnSingl = new JRadioButton("Single subscription");
		rdbtnSingl.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 191, 255)));
		rdbtnSingl.setFont(new Font("Tahoma", Font.BOLD, 16));
		rdbtnSingl.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			radioButtonChoose = UserStatus.SINGLE;
			}
		});
			rdbtnSingl.setBounds(56, 217, 186, 54);
		add(rdbtnSingl);
		
		JRadioButton rdbtnMonthly = new JRadioButton("Monthly subscription");
		rdbtnMonthly.setFont(new Font("Tahoma", Font.BOLD, 16));
		rdbtnMonthly.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				radioButtonChoose = UserStatus.MONTHLY;
				}
			});
		rdbtnMonthly.setBounds(301, 217, 209, 54);
		add(rdbtnMonthly);
		
		JRadioButton rdbtnYearly = new JRadioButton("Yearly subscription");
		rdbtnYearly.setFont(new Font("Tahoma", Font.BOLD, 16));
		rdbtnYearly.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				radioButtonChoose = UserStatus.YEARLY;
				}
			});
		rdbtnYearly.setBounds(571, 217, 194, 54);
		add(rdbtnYearly);
		
		JLabel lblRequestToSet = new JLabel("REQUEST TO SET SUBSCRIPTION");
		lblRequestToSet.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 21));
		lblRequestToSet.setBounds(242, 92, 398, 54);
		add(lblRequestToSet);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnSingl);
		group.add(rdbtnMonthly);
		group.add(rdbtnYearly);
		User u = new User(screen.getTempID());
		JButton btnSendRequest = new JButton("Send Request");
		btnSendRequest.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 191, 255)));
		btnSendRequest.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnSendRequest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(radioButtonChoose==0)
					JOptionPane.showMessageDialog(screen,"                          Sorry!\nNo selected one of options", "Warning",JOptionPane.WARNING_MESSAGE);
				else
				{
					ArrayList<User> temp= (ArrayList<User>) UserController.SearchUser("subscriptionMethod,subscriptionRequest",u,"userID=\""+screen.getTempID()+"\"",screen.getClient());
				
					if(radioButtonChoose==UserStatus.SINGLE)
					{
						if(temp.get(0).getSubscriptionRequest()==UserStatus.SINGLE)
							JOptionPane.showMessageDialog(screen,"This request alredy sent! ", "Warning",JOptionPane.WARNING_MESSAGE);
						else
						{
							int x =temp.get(0).getSubscriptionMethod();
							if(temp.get(0).getSubscriptionMethod()>UserStatus.NONE)
								JOptionPane.showMessageDialog(screen,"There is already another subscription", "Warning",JOptionPane.WARNING_MESSAGE);
							else
							{
							UserController.UpdateUserStatus(u, "subscriptionRequest=\""+UserStatus.SINGLE+"\"", "userID=\""+screen.getTempID()+"\"", screen.getClient());
							JOptionPane.showMessageDialog(screen,"Send request Subscription sucsseccfully", "Warning",JOptionPane.WARNING_MESSAGE);
							}
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
								if(temp.get(0).getSubscriptionMethod()>UserStatus.SINGLE)
									JOptionPane.showMessageDialog(screen,"There is already another subscription", "Warning",JOptionPane.WARNING_MESSAGE);
								else
								{
								UserController.UpdateUserStatus(u, "subscriptionRequest=\""+UserStatus.MONTHLY+"\"", "userID=\""+screen.getTempID()+"\"", screen.getClient());
								JOptionPane.showMessageDialog(screen,"Send request Subscription sucsseccfully", "Warning",JOptionPane.WARNING_MESSAGE);
								}
							}
						}
						else
							if(radioButtonChoose==UserStatus.YEARLY)
							{
								if(temp.get(0).getSubscriptionRequest()==UserStatus.YEARLY)
									JOptionPane.showMessageDialog(screen,"This request alredy sent! ", "Warning",JOptionPane.WARNING_MESSAGE);
								else
								{
									if(temp.get(0).getSubscriptionMethod()>UserStatus.MONTHLY)
										JOptionPane.showMessageDialog(screen,"There is already another subscription", "Warning",JOptionPane.WARNING_MESSAGE);
									else{
									UserController.UpdateUserStatus(u, "subscriptionRequest=\""+UserStatus.YEARLY+"\"", "userID=\""+screen.getTempID()+"\"", screen.getClient());
									JOptionPane.showMessageDialog(screen,"Send request Subscription sucsseccfully", "Warning",JOptionPane.WARNING_MESSAGE);
									}
								}
							}
					
					}
				}
			}
		});
		btnSendRequest.setBounds(348, 481, 148, 46);
		add(btnSendRequest);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setEnabled(false);
		btnNewButton.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 191, 255)));
		btnNewButton.setBounds(44, 209, 209, 73);
		add(btnNewButton);
		
		JButton button = new JButton("");
		button.setEnabled(false);
		button.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 191, 255)));
		button.setBounds(293, 209, 227, 73);
		add(button);
		
		JButton button_1 = new JButton("");
		button_1.setEnabled(false);
		button_1.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 191, 255)));
		button_1.setBounds(562, 209, 215, 73);
		add(button_1);
	}
	
}
