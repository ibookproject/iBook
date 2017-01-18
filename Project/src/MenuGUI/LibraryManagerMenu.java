
package MenuGUI;

import javax.swing.JPanel;

import java.awt.Font;
import java.awt.Rectangle;

import ManagmentGUI.BookRateGUI;
import ManagmentGUI.StatisticsBookReportGUI;
import ManagmentGUI.StatisticsUserReportGUI;
import ManagmentGUI.TemporaryRemoveBookGUI;
import ManagmentGUI.UpdateUserInfoGUI;
import MemberGUI.SearchBookGUI;

import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;




public class LibraryManagerMenu extends LibrarianMenu {


	private static final long serialVersionUID = 1L;
	private LoginGUI screen;
	private JPanel pann;
	private int Permission;
	
	public LibraryManagerMenu(LoginGUI screen,int permission ,int UserIdAtDataBase) {
		super(screen,permission,UserIdAtDataBase);
		this.screen=screen;
		pann=this;
		this.Permission=permission;
		initialize();
	}

	private void initialize() {
		
		this.setSize(850, 625);
		this.setLayout(null);	
		
		JButton btnUpdateUserInfo = new JButton("Update user");
		btnUpdateUserInfo.setIcon(new ImageIcon("UpdateUser.png"));
		btnUpdateUserInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				////////////////////////button to back panel from panel /////////////////////////////////////////////
				UpdateUserInfoGUI Uui=new UpdateUserInfoGUI(screen);
				Uui.btnBack.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						screen.setContentPane(pann);
					}
				////////////////////////button to back panel from panel/////////////////////////////////////////////
				});
				screen.setContentPane(Uui);//send to search book window
			}
		});
		btnUpdateUserInfo.setBounds(651, 129, 189, 53);
		btnUpdateUserInfo.setFont(new Font("Tahoma", Font.BOLD, 13));
		add(btnUpdateUserInfo);
		
		JButton btnTemporaryRemoveBoojk = new JButton("Hide book");
		btnTemporaryRemoveBoojk.setIcon(new ImageIcon("delete.png"));
		btnTemporaryRemoveBoojk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				////////////////////////button to back panel from panel /////////////////////////////////////////////
				TemporaryRemoveBookGUI Trb=new TemporaryRemoveBookGUI(screen);
				Trb.btnBack.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						screen.setContentPane(pann);
					}
				////////////////////////button to back panel from panel/////////////////////////////////////////////
				});
				screen.setContentPane(Trb);//send to search book window
			}
		});
		btnTemporaryRemoveBoojk.setBounds(651, 192, 189, 53);
		btnTemporaryRemoveBoojk.setFont(new Font("Tahoma", Font.BOLD, 13));
		add(btnTemporaryRemoveBoojk);
		
		JButton btnBookStatistics = new JButton("Book statistics");
		btnBookStatistics.setIcon(new ImageIcon("graph.png"));
		btnBookStatistics.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				////////////////////////button to back panel from panel /////////////////////////////////////////////
				StatisticsBookReportGUI sbr=new StatisticsBookReportGUI(screen);
				sbr.btnBack.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						screen.setContentPane(pann);
					}
				////////////////////////button to back panel from panel/////////////////////////////////////////////
				});
				screen.setContentPane(sbr);//send to search book window
			}
		});
		btnBookStatistics.setBounds(651, 318, 189, 53);
		btnBookStatistics.setFont(new Font("Tahoma", Font.BOLD, 13));
		add(btnBookStatistics);
		
		JButton btnUserStatistics = new JButton("User statistics");
		btnUserStatistics.setIcon(new ImageIcon("graph.png"));
		btnUserStatistics.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
////////////////////////button to back panel from panel /////////////////////////////////////////////
				StatisticsUserReportGUI Sur=new StatisticsUserReportGUI(screen);
				Sur.btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				screen.setContentPane(pann);
			}
		////////////////////////button to back panel from panel/////////////////////////////////////////////
		});
		screen.setContentPane(Sur);//send to search book window
			}
		});
		btnUserStatistics.setBounds(651, 255, 189, 53);
		btnUserStatistics.setFont(new Font("Tahoma", Font.BOLD, 13));
		add(btnUserStatistics);
		
		JButton btnBookrate = new JButton("Book rate");
		btnBookrate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
////////////////////////button to back panel from panel /////////////////////////////////////////////
				BookRateGUI Rbr=new BookRateGUI(screen);
				Rbr.btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				screen.setContentPane(pann);
			}
		////////////////////////button to back panel from panel/////////////////////////////////////////////
		});
		screen.setContentPane(Rbr);//send to search book window
			}
		});
		btnBookrate.setBounds(651, 318, 189, 53);
		btnBookrate.setFont(new Font("Tahoma", Font.BOLD, 13));
		add(btnBookrate);
	
	}


	
}
