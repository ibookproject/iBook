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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import javax.swing.SwingConstants;




public class RequestSetSubscriptionGUI extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JButton btnBack ;
	private int radioButtonChoose = 0;// integer to sent what the radio button
	private LoginGUI screen;
	private JPanel pann;
	private JLabel lblFinishSubscriptionDate;
	private ArrayList<User> temp;
	
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
			rdbtnSingl.setBounds(47, 212, 203, 67);
		add(rdbtnSingl);
		
		JRadioButton rdbtnMonthly = new JRadioButton("Monthly subscription");
		rdbtnMonthly.setFont(new Font("Tahoma", Font.BOLD, 16));
		rdbtnMonthly.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				radioButtonChoose = UserStatus.MONTHLY;
				}
			});
		rdbtnMonthly.setBounds(309, 212, 203, 67);
		add(rdbtnMonthly);
		
		JRadioButton rdbtnYearly = new JRadioButton("Yearly subscription");
		rdbtnYearly.setFont(new Font("Tahoma", Font.BOLD, 16));
		rdbtnYearly.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				radioButtonChoose = UserStatus.YEARLY;
				}
			});
		rdbtnYearly.setBounds(571, 212, 203, 67);
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
					temp = (ArrayList<User>) UserController.SearchUser("subscriptionMethod,subscriptionRequest",u,"userID=\""+screen.getTempID()+"\"",screen.getClient());
				
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
		
		JButton btnBorderButtonSingle = new JButton("");
		btnBorderButtonSingle.setEnabled(false);
		btnBorderButtonSingle.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 191, 255)));
		btnBorderButtonSingle.setBounds(44, 209, 209, 73);
		add(btnBorderButtonSingle);
		
		JButton btnBorderButtonMothly = new JButton("");
		btnBorderButtonMothly.setEnabled(false);
		btnBorderButtonMothly.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 191, 255)));
		btnBorderButtonMothly.setBounds(306, 209, 209, 73);
		add(btnBorderButtonMothly);
		
		JButton btnBorderButtonYearly = new JButton("");
		btnBorderButtonYearly.setEnabled(false);
		btnBorderButtonYearly.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 191, 255)));
		btnBorderButtonYearly.setBounds(568, 209, 209, 73);
		add(btnBorderButtonYearly);
		
		lblFinishSubscriptionDate = new JLabel("");
		temp = UserController.SearchUser("subscriptionMethod,finishDateOfSubscription", u, "userID=\"" + screen.getTempID() + "\"", screen.getClient());
		if(temp.get(0).getSubscriptionMethod() != 0)
		{
			String method = "";
			switch (temp.get(0).getSubscriptionMethod()) {
			case UserStatus.SINGLE:
				method = "SINGLE";
				break;
			case UserStatus.MONTHLY:
				method = "MONTHLY";
				break;
			case UserStatus.YEARLY:
				method = "YEARLY";
				break;
			}
			lblFinishSubscriptionDate.setText("Your " + method + " subscription will end at: " + new SimpleDateFormat("yyyy/MM/dd").format(temp.get(0).getFinishDateOfSubscription()) );
		}
		else
			lblFinishSubscriptionDate.setText("You don't have any Subscription. Choose one!");
		lblFinishSubscriptionDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblFinishSubscriptionDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblFinishSubscriptionDate.setBounds(189, 162, 451, 23);
		add(lblFinishSubscriptionDate);
	}
}
