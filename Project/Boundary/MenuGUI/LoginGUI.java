package MenuGUI;


import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Role.User;
import Role.UserStatus;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.awt.event.ActionEvent;
import java.io.IOException;

import Controller.UserController;
import DB.Test;
import Extras.TimerProject;
import client.DBSQLhandler;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JPasswordField;

import java.awt.Color;

import javax.swing.border.MatteBorder;

public class LoginGUI extends JFrame {

	/**
	 * 
	 * @author Sagi Entenberg
	 *
	 */
	private static final long serialVersionUID = 1L;

	final public static int DEFAULT_PORT = 5555;

	private JPanel FirstPanel = null;
	private JTextField txtUserID=null;
	private JPasswordField pwdPassword=null;
	public static LoginGUI screen;
	private DBSQLhandler client;// client attribute
	private int counteEnrty=0;
	private int flagTry=0;
	private String userInput;
	private String newStatus;
	private String tempID;
	private TimerProject y;
	/**
	 * @param host
	 * Build the first window of the App - Login.
	 */
	public LoginGUI(String host) {
		super();
		setResizable(false);
		initialize();
		tempID=null;
		this.screen = this;
		try {
			client = new DBSQLhandler(host, DEFAULT_PORT);// connection to
															// server
		} catch (IOException exception) {
			System.out.println("Error: Can't setup connection!" + " Terminating client.");
			System.exit(1);
		}
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
		  	@Override
			public void windowClosing(WindowEvent e) {
		  		ImageIcon point=new ImageIcon("bookIcon.png");//Extras/Images/
				Object[] options = { "Yes, please", "No" };
				int result = JOptionPane.showOptionDialog(null, "Would you like exit now?", "Exit",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, point,options,options[0]);
				if(result==JOptionPane.YES_OPTION){
					if(tempID!=null){
				User u = new User(tempID);
				ArrayList<User> temp= (ArrayList<User>) UserController.SearchUser("userStatus",u,"userID=\""+tempID+"\"",client);
				if(temp.get(0).getUserStatus()!=2)
				UserController.UpdateUserStatus(u, "userStatus=\"" + "0" + "\"", "userID=\"" + tempID + "\"",
						getClient());
					}
				if(y!=null)
				y.endTimer();
				client.quit();
				System.exit(0);
				}
				else
					setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		  	}
		  });
	}
/**
 * 
 * @return tempID - ID of the user that connected now
 */
	public String getTempID() {
		return tempID;
	}
/**
 * 
 * @param tempID -ID of the user that connected now
 */
	public void setTempID(String tempID) {
		this.tempID = tempID;
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
			btnLogin.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 191, 255)));
		//	btnLogin.setBackground(new Color(224, 255, 255));
			btnLogin.setFont(new Font("Tahoma", Font.BOLD, 16));
			btnLogin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					User u=null;
					if(flagTry==0)
					{
						userInput=txtUserID.getText();
						flagTry++;
					}
					try{
							u=new User(txtUserID.getText()/*ID*/, pwdPassword.getText()/*Password*/);
				
					ArrayList<User> temp= (ArrayList<User>) UserController.SearchUser("userID,password,identityNumber,firstName,lastName,userStatus,privilege,subscriptionRequest,subscriptionMethod,finishDateOfSubscription",u,"userID=\""+u.getUserID()+"\" && password=\""+u.getPassword()+"\"",client);
					if(temp==null||temp.isEmpty())
					{
						JOptionPane.showMessageDialog(screen,"wrong password/username", "Warning",JOptionPane.WARNING_MESSAGE);
						if(u.getUserStatus()!=3)//not looked yet 
						{	
							if(((txtUserID.getText()!=null)&&(pwdPassword!=null))&&(userInput.equals(txtUserID.getText())))
								counteEnrty++;
							if(counteEnrty>=3)
							{
								JOptionPane.showMessageDialog(screen,"this user name is locked!", "Warning",JOptionPane.WARNING_MESSAGE);
								UserController.UpdateUserStatus(u, "userStatus=\""+"2"+"\"", "userID=\""+txtUserID.getText()+"\"", screen.client);
								counteEnrty=0;
								flagTry--;
							}	
						}
						
					}
					else{
						
						counteEnrty=0;
						flagTry--;
						if(temp.get(0).getUserStatus()==UserStatus.LOCK)
							JOptionPane.showMessageDialog(screen,"this user is already locked!", "Warning",JOptionPane.WARNING_MESSAGE);
						else
							if(temp.get(0).getUserStatus()==UserStatus.CONNECTED)
								JOptionPane.showMessageDialog(screen,"this user is already connected!", "Warning",JOptionPane.WARNING_MESSAGE);
							else
							{
								Date date = new Date();
							//	String txtDate = new SimpleDateFormat("yyyy/MM/dd").format(date); 
							//	date = new Date(txtDate);
								temp.get(0).setUserStatus(UserStatus.CONNECTED);
								if(temp.get(0).getSubscriptionMethod()!=UserStatus.NONE)
									if(temp.get(0).getFinishDateOfSubscription().before(date))
									{
										UserController.UpdateUserStatus(u, "subscriptionMethod=0", "subscriptionMethod<>0 && userID=\""+ txtUserID.getText()+"\"", screen.client);
										JOptionPane.showMessageDialog(screen,"SORRY! your subscription has been finished !", "Warning",JOptionPane.WARNING_MESSAGE);

									}
							setTempID(temp.get(0).getUserID());
							UserController.UpdateUserStatus(u, "userStatus=\""+"1"+"\"", "userID=\""+txtUserID.getText()+"\"", screen.client);
							Test.setExitID(temp.get(0).getUserID());
							Test.setExitCLIENT(screen);
					switch (temp.get(0).getPriviliege()) {
							
					case UserStatus.USER: {
// //////////////////////button to back panel from panel// /////////////////////////////////////////////
						UserMenu usm = new UserMenu(screen);
						usm.btnDisconnect
								.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										setContentPane(FirstPanel);
									}
// //////////////////////button to back panel from panel/////////////////////////////////////////////
								});
						setContentPane(usm);// send to search book window

					}
						break;
					case UserStatus.READER: {
						ReaderMenu usm = new ReaderMenu(screen,Integer.parseInt(txtUserID.getText()));
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
						LibraryWorkerMenu usm = new LibraryWorkerMenu(screen,Integer.parseInt(txtUserID.getText()));
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
						 TimerProject y = new TimerProject();
						y.startTimer(screen);
						LibrarianMenu usm = new LibrarianMenu(screen, 5);
						usm.btnDisconnect
								.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										setContentPane(FirstPanel);
										y.endTimer();
									}
									// //////////////////////button to back
									// panel from
									// panel/////////////////////////////////////////////
								});
						setContentPane(usm);
					}
						break;
					case UserStatus.MANAGER: {
						 TimerProject y = new TimerProject();
						y.startTimer(screen);
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

					// //////////////////////button go to panel from JFram/////////////////////////////////////////////
					
					}

						}
					}
				}//try
					catch(InputMismatchException ex)
					{
						
						JOptionPane.showMessageDialog(screen,"USER ID or Password empty", "Warning",JOptionPane.WARNING_MESSAGE);
					System.out.println("Mismatch password/username");
					flagTry--;;
					}
				}
			});
			
						txtUserID = new JTextField();
						txtUserID.setFont(new Font("Tahoma", Font.PLAIN, 17));
						txtUserID.setBounds(302, 296, 192, 35);
						FirstPanel.add(txtUserID);
						txtUserID.setColumns(10);
			
			pwdPassword = new JPasswordField();
			pwdPassword.setFont(new Font("Tahoma", Font.PLAIN, 17));
			
					pwdPassword.setText("");
					pwdPassword.setBounds(302, 355, 192, 33);
					FirstPanel.add(pwdPassword);
			btnLogin.setBounds(534, 355, 118, 35);
			FirstPanel.add(btnLogin);

			JLabel lblUserId = new JLabel("USER NAME:");
			lblUserId.setFont(new Font("Tahoma", Font.BOLD, 20));
			lblUserId.setBounds(156, 296, 140, 33);
			FirstPanel.add(lblUserId);

			JLabel lblPassword = new JLabel("PASSWORD:");
			lblPassword.setFont(new Font("Tahoma", Font.BOLD, 20));
			lblPassword.setBounds(156, 353, 134, 35);
			FirstPanel.add(lblPassword);
			
			JButton bntBackground = new JButton("");
			bntBackground.setBackground(Color.GRAY);
			bntBackground.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 191, 255)));
			bntBackground.setEnabled(false);
			bntBackground.setBounds(131, 273, 553, 154);
			FirstPanel.add(bntBackground);
			ImageIcon logo=new ImageIcon("Extras/Images/IbookIcon500.png");
			JLabel lblNewLabel = new JLabel("");
			lblNewLabel.setBounds(156, 24, 488, 236);
			lblNewLabel.setIcon(logo);
			FirstPanel.add(lblNewLabel);
			

		}
		return FirstPanel;
	}
/**
 * 
 * @param pnl
 * 
 */
	public void setpann(JPanel pnl) {
		setContentPane(pnl);
	}

/**
 * 
 * @return client
 */
	public DBSQLhandler getClient()// return client
	{
		return client;
	}
}