
package Panels;

import javax.swing.JPanel;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.MatteBorder;

import java.awt.Color;

import javax.swing.border.LineBorder;
import javax.swing.JCheckBox;

import Book.Book;
import Book.Domain;
import Controller.UserController;
import Controller.BookController;
import MenuGUI.LoginGUI;
import Role.User;
import Role.UserStatus;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.spi.CalendarNameProvider;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class UserSubscriptionPanel extends JPanel{
	private ArrayList<Book> Books;
	private LoginGUI screen;
	private JLabel lblAnswerfromserver;
//	private java.util.Date date;
	
	public UserSubscriptionPanel(LoginGUI screen,User u) {
		setBackground(Color.WHITE);
		setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(46, 139, 87)));
		setPreferredSize(new Dimension(577, 121));
		setLayout(null);
		JButton btnConfirmSubscription = new JButton("Confirm");
		JButton btnRejectSubscription = new JButton("Reject");


		btnConfirmSubscription.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
						boolean result = false;
						if(u==null)
							JOptionPane.showMessageDialog(screen,"Set subscription process FAILED!", "Warning",JOptionPane.WARNING_MESSAGE);
						else
						{
							Calendar time = Calendar.getInstance();
							time.setTime(time.getTime());
							int method = u.getSubscriptionRequest();
							switch(method){
								case UserStatus.SINGLE:
									time.add(Calendar.DATE, 1);
									break;
								case UserStatus.MONTHLY:
									time.add(Calendar.MONTH, 1);
									break;
								case UserStatus.YEARLY:
									time.add(Calendar.YEAR, 1);
									break;
							}
							// update user by the new subscription method
							// and set privilege to 2 (READER) if its under 2
							if(u.getPriviliege() == 1)
								result = UserController.SetStatusSubscription(u, "subscriptionMethod=\""+u.getSubscriptionRequest()+"\" && privilege=\""+ UserStatus.READER +"\"" + " && subscriptionRequest=\""+ 0 +"\"" + " && finishDateOfSubscription=\"" + new SimpleDateFormat("yyyy/MM/dd").format(time.getTime()) + "\"" , "userID=\""+u.getUserID()+"\"", screen.getClient());
							else
								result = UserController.SetStatusSubscription(u, "subscriptionMethod=\""+u.getSubscriptionRequest()+"\""+ " && subscriptionRequest=\""+ 0 +"\"" + " && finishDateOfSubscription=\"" + new SimpleDateFormat("yyyy/MM/dd").format(time.getTime()) + "\"", "userID=\""+u.getUserID()+"\"", screen.getClient());
							if(result)
							{
								//lblAnswerfromserver.setText("Confirmed successfully!");
								lblAnswerfromserver.setText("Confirmed successfully!");
								lblAnswerfromserver.setForeground(Color.GREEN);
								btnConfirmSubscription.setVisible(false);
								btnRejectSubscription.setVisible(false);
							}
							else 
								JOptionPane.showMessageDialog(screen,"Set subscription process FAILED!", "Warning",JOptionPane.WARNING_MESSAGE);

							
						}
					
			}
		});
		

		btnRejectSubscription.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
						boolean result = false;
						if(u==null)
							JOptionPane.showMessageDialog(screen,"Set subscription process FAILED!", "Warning",JOptionPane.WARNING_MESSAGE);
						else
						{
							// update user to reject subscription
							result = UserController.SetStatusSubscription(u, "subscriptionMethod=\""+0+"\"" + " && subscriptionRequest=\""+ 0 +"\"", "userID=\""+u.getUserID()+"\"", screen.getClient());
						if(result)
							{
								lblAnswerfromserver.setText("Rejected successfully!");
								lblAnswerfromserver.setForeground(Color.RED);
								btnConfirmSubscription.setVisible(false);
								btnRejectSubscription.setVisible(false);
							}
							else 
								JOptionPane.showMessageDialog(screen,"Set subscription process FAILED!", "Warning",JOptionPane.WARNING_MESSAGE);

							
						}
					
			}
		});
		btnRejectSubscription.setBounds(469, 32, 87, 23);
		add(btnRejectSubscription);
		btnConfirmSubscription.setBounds(369, 32, 87, 23);
		add(btnConfirmSubscription);
		
		JLabel lblUserid = new JLabel("UserID:");
		lblUserid.setBounds(22, 11, 147, 14);
		add(lblUserid);
		
		JLabel lblUderiddb = new JLabel(u.getUserID());
		lblUderiddb.setBounds(179, 11, 141, 14);
		add(lblUderiddb);
		
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setBounds(22, 36, 147, 14);
		add(lblFirstName);
		
		JLabel lblFirstnamedb = new JLabel(u.getFirstName());
		lblFirstnamedb.setBounds(179, 36, 141, 14);
		add(lblFirstnamedb);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setBounds(22, 61, 147, 14);
		add(lblLastName);
		
		JLabel lblLastnamedb = new JLabel(u.getLastName());
		lblLastnamedb.setBounds(179, 61, 141, 14);
		add(lblLastnamedb);
		
		JLabel lblRequestSubscription = new JLabel("Request Subscription:");
		lblRequestSubscription.setBounds(22, 86, 147, 14);
		add(lblRequestSubscription);
		
		String choose="";
		switch(u.getSubscriptionRequest())
		{
			case UserStatus.SINGLE:
				choose = "Single";
				break;
			case UserStatus.MONTHLY:
				choose = "Monthly";
				break;
			case UserStatus.YEARLY:
				choose = "Yearly";
				break;
		};

		JLabel lblRequestsubscriptiondb = new JLabel(choose);
		lblRequestsubscriptiondb.setForeground(Color.BLUE);
		lblRequestsubscriptiondb.setBounds(179, 86, 141, 14);
		add(lblRequestsubscriptiondb);
		
		lblAnswerfromserver = new JLabel();
		lblAnswerfromserver.setBounds(379, 66, 152, 14);
		add(lblAnswerfromserver);
		
			
	}
}
//how to print the date right now:
//String dateRightNow = new SimpleDateFormat("yyyy/MM/dd").format(cal.getTime());



