package MenuGUI;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Role.User;
import Role.UserStatus;
import Role.UserStatus.*;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.awt.event.ActionEvent;
import java.io.IOException;



import Controller.UserController;
import client.DBSQLhandler;
import client.DBgenericObject;

public class LoginGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	final public static int DEFAULT_PORT = 5555;

	private JPanel FirstPanel = null;
	private JTextField textField;
	private JTextField textField_1;
	private LoginGUI screen;
	private DBSQLhandler client;// client attribute
	private int counteEnrty=0;

	/**
	 * This is the default constructor
	 */
	public LoginGUI(String host) {
		super();
		initialize();
		this.screen = this;
		try {
			client = new DBSQLhandler(host, DEFAULT_PORT);// connection to
															// server
		} catch (IOException exception) {
			System.out.println("Error: Can't setup connection!" + " Terminating client.");
			System.exit(1);
		}
	}

	/**
	 * This method initializes AcademicFrame
	 */
	private void initialize() {
		this.setSize(850, 625);
		this.setContentPane(getFirstPanel());
		this.setTitle("iBOOK");
	}

	/**
	 * This method initializes FirstPanel
	 */
	private JPanel getFirstPanel() {
		if (FirstPanel == null) {
			FirstPanel = new JPanel();
			FirstPanel.setLayout(null);

			JButton btnLogin = new JButton("Login");
			btnLogin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					User u=null;
					try{
					u=new User(textField.getText(), textField_1.getText());
					}
					catch(InputMismatchException ex){
					System.out.println(ex);
					}
					ArrayList<User> temp= (ArrayList<User>) UserController.SearchUser("userID,privilege",u,"userID=\""+u.getUserID()+"\" && password=\""+u.getPassword()+"\"",client);
					if(temp==null||temp.isEmpty()){
						JOptionPane.showMessageDialog(screen,"wrong password/username", "Warning",
								JOptionPane.WARNING_MESSAGE);
						if(counteEnrty++>=3){
							JOptionPane.showMessageDialog(screen,"this user name is locked!", "Warning",
									JOptionPane.WARNING_MESSAGE);
							client.quit();
							System.exit(1);
						}
						//here need to write sql to update
						
					}
					else{
						if(temp.size()>1)
							throw new InputMismatchException("there is more then one User with userID"+temp.get(0).getUserID());
					switch (temp.get(0).getPriviliege()) {
					case UserStatus.USER: {

						// //////////////////////button to back panel from panel
						// /////////////////////////////////////////////
						UserMenu usm = new UserMenu(screen);
						usm.btnDisconnect
								.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										setContentPane(FirstPanel);
									}
									// //////////////////////button to back
									// panel from
									// panel/////////////////////////////////////////////
								});
						setContentPane(usm);// send to search book window

					}
						break;
					case UserStatus.READER: {
						ReaderMenu usm = new ReaderMenu(screen);
						usm.btnDisconnect
								.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										setContentPane(FirstPanel);
									}
									// //////////////////////button to back
									// panel from
									// panel/////////////////////////////////////////////
								});
						setContentPane(usm);
					}
						break;
					case UserStatus.LIBARYWORKER: {
						LibraryWorkerMenu usm = new LibraryWorkerMenu(screen);
						usm.btnDisconnect
								.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										setContentPane(FirstPanel);
									}
									// //////////////////////button to back
									// panel from
									// panel/////////////////////////////////////////////
								});
						setContentPane(usm);
					}
						break;
					case UserStatus.QUALIFIEDEDITOR: {
						QualifiedEditorMenu usm = new QualifiedEditorMenu(
								screen, 4);
						usm.btnDisconnect
								.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										setContentPane(FirstPanel);
									}
									// //////////////////////button to back
									// panel from
									// panel/////////////////////////////////////////////
								});
						setContentPane(usm);
					}
						break;
					case UserStatus.LIBRRIAN: {
						LibrarianMenu usm = new LibrarianMenu(screen, 5);
						usm.btnDisconnect
								.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										setContentPane(FirstPanel);
									}
									// //////////////////////button to back
									// panel from
									// panel/////////////////////////////////////////////
								});
						setContentPane(usm);
					}
						break;
					case UserStatus.MANAGER: {
						LibraryManagerMenu usm = new LibraryManagerMenu(screen,
								6);
						usm.btnDisconnect
								.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										setContentPane(FirstPanel);
									}
									// //////////////////////button to back
									// panel from
									// panel/////////////////////////////////////////////
								});
						setContentPane(usm);
					}
						break;

					// //////////////////////button go to panel from
					// JFram/////////////////////////////////////////////
					// LibrarianMenu usm=new LibrarianMenu(screen);
					// setContentPane(usm);
					}

					// //////////////////////button go to panel from
					// JFram/////////////////////////////////////////////
				}
				}
			});
			btnLogin.setBounds(386, 321, 89, 23);
			FirstPanel.add(btnLogin);

			JLabel lblLogin = new JLabel("LOGIN");
			lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblLogin.setBounds(401, 28, 85, 23);
			FirstPanel.add(lblLogin);

			textField = new JTextField();
			textField.setBounds(374, 232, 112, 20);
			FirstPanel.add(textField);
			textField.setColumns(10);

			textField_1 = new JTextField();
			textField_1.setBounds(374, 263, 112, 20);
			FirstPanel.add(textField_1);
			textField_1.setColumns(10);

			JLabel lblUserId = new JLabel("USER ID:");
			lblUserId.setBounds(279, 235, 87, 14);
			FirstPanel.add(lblUserId);

			JLabel lblPassword = new JLabel("PASSWORD:");
			lblPassword.setBounds(279, 266, 87, 14);
			FirstPanel.add(lblPassword);

			JTextPane txtpnUser = new JTextPane();
			txtpnUser.setEditable(false);
			txtpnUser
					.setText("1-user menu 2-reader menu 3-librarian worker 4-qualified editor 5-librarian menu 6-librarian meneger menu");
			txtpnUser.setBounds(597, 208, 112, 136);
			FirstPanel.add(txtpnUser);

			JLabel label = new JLabel(
					"\u05EA\u05DB\u05E0\u05D9\u05E1\u05D5 \u05D1\u05D9\u05D5\u05E1\u05E8 \u05D0\u05D9\u05D9\u05D3\u05D9 \u05D0\u05EA \u05D0\u05D7\u05D3 \u05DE\u05D4\u05DE\u05E1\u05E4\u05E8\u05D9\u05DD \u05D4\u05D0\u05DC\u05D5 \u05DB\u05D3\u05D9 \u05DC\u05D4\u05D9\u05DB\u05E0\u05E1 \u05DC\u05EA\u05E4\u05E8\u05D9\u05D8 \u05D4\u05DE\u05EA\u05D0\u05D9\u05DD");
			label.setBounds(438, 137, 415, 80);
			FirstPanel.add(label);
		}
		return FirstPanel;
	}

	public void setpann(JPanel pnl) {
		setContentPane(pnl);
	}

	public DBSQLhandler getClient()// return client
	{
		return client;
	}
}
