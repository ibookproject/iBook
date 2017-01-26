package ManagmentGUI;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.MatteBorder;



import Book.Cart;
import Controller.CartController;
import Extras.*;
import MenuGUI.LoginGUI;
import Panels.BookPerCart;
import client.DBgenericObject;

import javax.swing.SwingConstants;


/**
 * The class take care of show the user statistics- The library manager first need to insert user ID and than the requested Dates.
 * When the fields checked-the functionality of the class is to show the manager what he asked for according the specific details.
 * @author  Coral Carmeli
 */

public class StatisticsUserReportGUI extends JPanel
{
	private static final long serialVersionUID = 1L;
	public JButton btnBack ;
	private LoginGUI screen;
	private ImageIcon backIcon;
	private JLabel lblDate;
	private JLabel userReportLbl;
	private JButton btnGetReports;
	private JLabel lblUserID;
	private JTextField textFieldID;
	private JScrollPane scrollPaneMain;
	private JPanel panel;
	private JTextField txtFromDate;
	/**
	 * Constructor of the StatisticsUserReportGUI class
	 * @param screen This is the main window-login
	 * @author  Coral Carmeli
	 */		
	public StatisticsUserReportGUI(LoginGUI screen) 
	{
		super();
		this.screen=screen;
		initialize();
	}
	/**
	 * @author Coral Carmeli
	 * @param no parameters
	 * @return void
	 * This method initialize The window of StatisticsUserReportGUI-put the components on the screen and set their functionality	
	 */

	private void initialize() 
	{
		this.setSize(850, 625);
		this.setLayout(null);	
		
		userReportLbl = new JLabel("Statistics User Report");
		userReportLbl.setHorizontalAlignment(SwingConstants.CENTER);
		userReportLbl.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 21));
		userReportLbl.setBounds(355, 49, 236, 22);
		add(userReportLbl);
		
		backIcon =new ImageIcon("Extras/Images/backIcon.png");
		btnBack = new JButton(backIcon);// declaration of back button
		btnBack.setBounds(39, 52, 89, 23);
		add(btnBack);
		

		textFieldID = new JTextField("");
		textFieldID.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldID.setBounds(320, 130, 116, 30);
		add(textFieldID);
		textFieldID.setColumns(10);

		
		lblUserID = new JLabel("User ID:");
		lblUserID.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblUserID.setBounds(218, 130, 70, 20);
		add(lblUserID);
				
		lblDate = new JLabel("From Date:");
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDate.setBounds(192, 181, 96, 20);
		add(lblDate);
		
		scrollPaneMain = new JScrollPane();
		scrollPaneMain.setBounds(55, 250, 731, 230);
		scrollPaneMain.getVerticalScrollBar().setUnitIncrement(16);
		scrollPaneMain.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneMain.setAutoscrolls(true);
		add(scrollPaneMain);
				
		panel = new JPanel();
		panel.setIgnoreRepaint(true);
		panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		panel.setAutoscrolls(true);
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		txtFromDate = new JTextField();
		txtFromDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtFromDate.setBackground(Color.WHITE);
		txtFromDate.setEditable(false);
		txtFromDate.setBounds(320, 181, 116, 30);
		add(txtFromDate);
		txtFromDate.setColumns(10);
		/**
		 * This button is the Choose date button- when the user press, open to him calender which he need to choose the requested date
		 * @author  Coral Carmeli
		 * 
		 */	
		JButton btnChooseFromDate = new JButton("Choose Date");
		btnChooseFromDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnChooseFromDate.addActionListener(new ActionListener() 
		{	
			public void actionPerformed(ActionEvent arg0) 
			{
				JFrame f = new JFrame();
				f.setLocation(400, 400);
				f.setBounds(200, 200, 200, 200);
				txtFromDate.setText(new DatePicker(f).setPickedDate());

			}
		});
		btnChooseFromDate.setBounds(435, 181, 143, 30);
		add(btnChooseFromDate);
		/**
		 * This button is the get report button- when the user press, after the input validation the action is to show to the user
		 * The requested details on the user,according the specific date he chose.
		 * @author  Coral Carmeli
		 * 
		 */	
	
		btnGetReports = new JButton("Get report");
		btnGetReports.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnGetReports.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				
				Cart t=new Cart();
				if( txtFromDate.getText().isEmpty()||textFieldID.getText().isEmpty())
					{
					if(txtFromDate.getText().isEmpty())
						JOptionPane.showMessageDialog(screen,"Please fill the date field!", "Warning",JOptionPane.WARNING_MESSAGE);
					else if(textFieldID.getText().isEmpty())
						JOptionPane.showMessageDialog(screen,"Please fill the user ID field!", "Warning",JOptionPane.WARNING_MESSAGE);
					}
				else 
					if(Validation.IDValidation(textFieldID.getText().trim()) == false)
						JOptionPane.showMessageDialog(screen,"Please fill the user ID correctly,just numbers!", "Warning",JOptionPane.WARNING_MESSAGE);
				else
				{
					try
					{
						scrollPaneMain.setViewportView(panel);
						panel.removeAll();
						ArrayList<DBgenericObject> joinAnswerCartBook=CartController.searchJoinCartBook(txtFromDate.getText(),1,textFieldID.getText(),screen.getClient());
						if(joinAnswerCartBook.isEmpty())
							JOptionPane.showMessageDialog(screen,"There's nothing to show!", "Warning",JOptionPane.WARNING_MESSAGE);
						
						else
							for(DBgenericObject j:joinAnswerCartBook)
								panel.add(new BookPerCart(screen, (int)j.getValtoArray(0),(String)j.getValtoArray(1),(String)j.getValtoArray(2),new SimpleDateFormat("yyyy/MM/dd").format((Date)j.getValtoArray(3))));
							
					} 
					catch (SQLException e1) 
					{
						JOptionPane.showMessageDialog(screen,"There's nothing to show!", "Warning",JOptionPane.WARNING_MESSAGE);
						System.out.println("There's no books to show!");
					}
				
				}
			}	
		});
		btnGetReports.setBounds(598, 129, 125, 30);
		add(btnGetReports);
	}
}
