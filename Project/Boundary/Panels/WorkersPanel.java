
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
import Role.LibraryWorker;
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

public class WorkersPanel extends JPanel{
	private ArrayList<Book> Books;
	private LoginGUI screen;
//	private java.util.Date date;
	
	public WorkersPanel(LoginGUI screen,LibraryWorker lw) {
		setBackground(Color.WHITE);
		setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(50, 100, 150)));
		setPreferredSize(new Dimension(577, 138));
		setLayout(null);
		
		JLabel lblWorkerID = new JLabel("Worker ID:");
		lblWorkerID.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblWorkerID.setBounds(22, 10, 147, 20);
		add(lblWorkerID);
		
		JLabel lblWorkerIDdb = new JLabel(lw.getWorkerID());
		lblWorkerIDdb.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblWorkerIDdb.setBounds(140, 10, 141, 20);
		add(lblWorkerIDdb);
		
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblFirstName.setBounds(22, 40, 147, 20);
		add(lblFirstName);
		
		JLabel lblFirstnamedb = new JLabel(lw.getFirstName());
		lblFirstnamedb.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblFirstnamedb.setBounds(140, 40, 141, 20);
		add(lblFirstnamedb);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLastName.setBounds(22, 70, 147, 20);
		add(lblLastName);
		
		JLabel lblLastnamedb = new JLabel(lw.getLastName());
		lblLastnamedb.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblLastnamedb.setBounds(140, 70, 141, 20);
		add(lblLastnamedb);
		
		String roleName="";
		switch(lw.getRole())
		{
			case UserStatus.QUALIFIEDEDITOR:
				roleName = "QUALIFIED EDITOR";
				break;
			case UserStatus.LIBARYWORKER:
				roleName = "LIBARY WORKER";
				break;
			case UserStatus.LIBRRIAN:
				roleName = "LIBRRIAN";
				break;
			case UserStatus.MANAGER:
				roleName = "MANAGER";
				break;
		};
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEmail.setBounds(275, 10, 147, 20);
		add(lblEmail);
		
		JLabel lblRole = new JLabel("Role:");
		lblRole.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblRole.setBounds(275, 40, 147, 20);
		add(lblRole);
		
		JLabel lblDepartment = new JLabel("Department:");
		lblDepartment.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDepartment.setBounds(22, 100, 147, 20);
		add(lblDepartment);
		
		JLabel lblEmailDB = new JLabel(lw.getEmail());
		lblEmailDB.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEmailDB.setBounds(374, 10, 141, 20);
		add(lblEmailDB);
		
		JLabel lblRoleDB = new JLabel(roleName);
		lblRoleDB.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblRoleDB.setBounds(374, 40, 183, 20);
		add(lblRoleDB);
		
		JLabel lblDepartmentDB = new JLabel(lw.getdepartment());
		lblDepartmentDB.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDepartmentDB.setBounds(140, 100, 141, 20);
		add(lblDepartmentDB);
		
			
	}
}
//how to print the date right now:
//String dateRightNow = new SimpleDateFormat("yyyy/MM/dd").format(cal.getTime());



