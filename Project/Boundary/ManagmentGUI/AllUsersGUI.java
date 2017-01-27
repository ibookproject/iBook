package ManagmentGUI;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.MatteBorder;

import Book.Book;
import Book.Domain;
import Controller.UserController;
import Controller.WorkerController;
import Extras.Validation;
import Controller.BookController;
import MenuGUI.LoginGUI;
import Panels.BookStatisticsPanel;
import Panels.UserSubscriptionPanel;
import Panels.UsersPanel;
import Panels.WorkersPanel;
import Role.LibraryWorker;
import Role.User;
import client.DBgenericObject;

import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class AllUsersGUI extends JPanel {

	private static final long serialVersionUID = 1L;
	public JButton btnBack;
	private LoginGUI screen;
	private JPanel pann;
	private ArrayList<User> tempUsers;
	private ArrayList<LibraryWorker> tempWorkers;

	public AllUsersGUI(LoginGUI screen) {
		super();
		this.screen = screen;
		pann = this;

		initialize();
	}

	private void initialize() {

		this.setSize(850, 625);
		this.setLayout(null);

		JLabel lblHeader = new JLabel("All Users");
		lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeader.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 21));
		lblHeader.setBounds(355, 49, 234, 22);
		add(lblHeader);

		ImageIcon backIcon = new ImageIcon("Extras/Images/backIcon.png");
		btnBack = new JButton(backIcon);
		btnBack.setBounds(39, 52, 89, 23);
		add(btnBack);

		JScrollPane scrollPaneMain = new JScrollPane();
		scrollPaneMain.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneMain.setAutoscrolls(true);
		scrollPaneMain.setBounds(125, 159, 690, 393);
		add(scrollPaneMain);

		JPanel panel = new JPanel();
		panel.setIgnoreRepaint(true);
		panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		panel.setAutoscrolls(true);
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		scrollPaneMain.setViewportView(panel);
		scrollPaneMain.getVerticalScrollBar().setUnitIncrement(16);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnUsers = new JButton("Show Users");
		btnUsers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//////////////////////// button to back panel from panel
				//////////////////////// /////////////////////////////////////////////
				StatisticsBookReport sbr = new StatisticsBookReport(screen);
				sbr.btnBack.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						screen.setContentPane(pann);
					}
					//////////////////////// button to back panel from
					//////////////////////// panel/////////////////////////////////////////////
				});
				panel.removeAll();

							User u = new User(); // create
							tempUsers = UserController.SearchUser("userID,identityNumber,firstName,lastName", u,"userID <> 0" ,screen.getClient());
							
							if (tempUsers != null) {
								for (User ut : tempUsers)
									panel.add(new UsersPanel(screen, ut));
								panel.updateUI();
							} else
							{
								JOptionPane.showMessageDialog(screen, "The request FAILD", "Warning",
										JOptionPane.WARNING_MESSAGE);
								panel.updateUI();
							}
		}});
		btnUsers.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnUsers.setBounds(206, 90, 149, 30);
		add(btnUsers);
		
		JButton btnWorkers = new JButton("Show Workers");
		btnWorkers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//////////////////////// button to back panel from panel
				//////////////////////// /////////////////////////////////////////////
				StatisticsBookReport sbr = new StatisticsBookReport(screen);
				sbr.btnBack.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						screen.setContentPane(pann);
					}
					//////////////////////// button to back panel from
					//////////////////////// panel/////////////////////////////////////////////
				});
				panel.removeAll();

							LibraryWorker lu = new LibraryWorker(); // create
							tempWorkers = WorkerController.SearchWorker("workerID,department,firstName,lastName,role,email",lu,"workerID <> 0",screen.getClient());
							
							if (tempWorkers != null) {
								for (LibraryWorker lut : tempWorkers)
									panel.add(new WorkersPanel(screen, lut));
								panel.updateUI();
							} else
							{
								JOptionPane.showMessageDialog(screen, "The request FAILD", "Warning",
										JOptionPane.WARNING_MESSAGE);
								panel.updateUI();
							}
		}});
		btnWorkers.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnWorkers.setBounds(562, 90, 184, 30);
		add(btnWorkers);
		Book b = new Book();

	}
}
