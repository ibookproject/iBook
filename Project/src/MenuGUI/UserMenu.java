package MenuGUI;

import javax.swing.JPanel;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import MemberGUI.*;
import java.awt.Font;


public class UserMenu extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/// hen- i change all to protected because we extends this ... ( it was private)
	protected LoginGUI screen;
	//protected int UserIdAtDataBase; // this is the user id from the loging , for the cart , i need what is the current Userid at database
	protected JPanel pann;
	protected JButton btnSearchBook;
	protected JButton btnSearchReview;
	protected JButton btnSetSubscription;
	protected JLabel lblUserMenu;
	public JButton btnDisconnect;
	
	/**
	 * This is the default constructor
	 * @param screen 
	 */

	
	public UserMenu(LoginGUI screen ) {
		super();
		initialize();
		this.screen=screen;
		pann=this;
		lblUserMenu = new JLabel("iBOOK Menu");
		lblUserMenu.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUserMenu.setBounds(387, 11, 116, 14);
		add(lblUserMenu);
	}

	private void initialize() {
		
		this.setSize(850, 625);
		this.setLayout(null);
		
		btnSearchBook = new JButton("Search Book");
		btnSearchBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				////////////////////////button to back panel from panel /////////////////////////////////////////////
				SearchBookGUI sbg=new SearchBookGUI(screen);
				sbg.btnBack.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						screen.setContentPane(pann);
					}
				////////////////////////button to back panel from panel/////////////////////////////////////////////
				});
				screen.setContentPane(sbg);//send to search book window
			}
		});
		btnSearchBook.setBounds(325, 36, 223, 23);
		add(btnSearchBook);
		
				///---///
		
		 btnSearchReview = new JButton("Search Review");
		btnSearchReview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				////////////////////////button to back panel from panel /////////////////////////////////////////////
				SearchReviewGUI srg=new SearchReviewGUI(screen);
				srg.btnBack.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						screen.setContentPane(pann);
					}
				////////////////////////button to back panel from panel/////////////////////////////////////////////
				});
				
				screen.setContentPane(srg);//send to search review window
			}
		});
		btnSearchReview.setBounds(325, 104, 223, 23);
		add(btnSearchReview);
		
		
		btnSetSubscription = new JButton("Request Set Subscription");
		btnSetSubscription.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				////////////////////////button to back panel from panel /////////////////////////////////////////////
				RequestSetSubscriptionGUI rss=new RequestSetSubscriptionGUI(screen);
				rss.btnBack.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						screen.setContentPane(pann);
					}
				////////////////////////button to back panel from panel/////////////////////////////////////////////
				});
				
				screen.setContentPane(rss);//send to Request Set Subscription window
			}
		});
		
		btnSetSubscription.setBounds(325, 70, 223, 23);
		add(btnSetSubscription);
		
		btnDisconnect = new JButton("Disconnect");
		btnDisconnect.setBounds(712, 22, 116, 23);
		add(btnDisconnect);
	}


	

	
}
