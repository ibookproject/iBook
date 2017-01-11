
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
import Controller.bookController;
import MenuGUI.LoginGUI;
import Role.User;
import Role.UserStatus;

import java.awt.Font;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserSubscriptionPanel extends JPanel{
	private ArrayList<Book> Books;
	private LoginGUI screen;
	private JLabel lblAnswerfromserver;
	
	public UserSubscriptionPanel(LoginGUI screen,User u) {
		setBackground(Color.WHITE);
		setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(46, 139, 87)));
		setPreferredSize(new Dimension(577, 112));
		setLayout(null);
		
		JButton btnSetSubscription = new JButton("Confirm");
		btnSetSubscription.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
						boolean result = false;
						if(u==null)
							JOptionPane.showMessageDialog(screen,"Update user process FAILED ! ", "Warning",JOptionPane.WARNING_MESSAGE);
						else
						{
							// update user by the new subscription method from the radio button that choosen
							// and set privilege to 2 (READER) if its under 2
							if(u.getPriviliege() == 1)
								result = UserController.SetStatusSubscription(u, "subscriptionMethod=\""+u.getSubscriptionRequest()+"\" && privilege=\""+ UserStatus.READER +"\"" + " && subscriptionRequest=\""+ 0 +"\"", "userID=\""+u.getUserID()+"\"", screen.getClient());
							else
								result = UserController.SetStatusSubscription(u, "subscriptionMethod=\""+u.getSubscriptionRequest()+"\""+ " && subscriptionRequest=\""+ 0 +"\"", "userID=\""+u.getUserID()+"\"", screen.getClient());
							if(result)
							{
								lblAnswerfromserver.setText("Updated successfully!");
								lblAnswerfromserver.setForeground(Color.GREEN);
								btnSetSubscription.setVisible(false);
							}
							else 
								JOptionPane.showMessageDialog(screen,"Update user process FAILED", "Warning",JOptionPane.WARNING_MESSAGE);

							
						}
					
			}
		});
		btnSetSubscription.setBounds(415, 32, 117, 23);
		add(btnSetSubscription);
		
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
		lblRequestsubscriptiondb.setBounds(179, 83, 141, 14);
		add(lblRequestsubscriptiondb);
		
		lblAnswerfromserver = new JLabel("");
		lblAnswerfromserver.setBounds(415, 66, 152, 14);
		add(lblAnswerfromserver);

			
	}
}



