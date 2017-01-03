package ManagmentGUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;




public class BookRateGUI extends JPanel {

	
	private static final long serialVersionUID = 1L;
	public JButton btnBack ;
	private JFrame screen;
	private JPanel pann;
	
	public BookRateGUI(JFrame screen) 
	{
		super();
		this.screen=screen;
		pann=this;

		initialize();
	}

	private void initialize() {
	
		this.setSize(850, 625);
		this.setLayout(null);	
		ImageIcon backIcon =new ImageIcon("src/images/backIcon.png");
		btnBack = new JButton(backIcon);// declaration of back button
		btnBack.setBounds(39, 52, 89, 23);
		add(btnBack);
		
		JLabel bookRateLbl = new JLabel("Request Book Rate");
		bookRateLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		bookRateLbl.setBounds(355, 49, 175, 22);
		add(bookRateLbl);
		
		JComboBox<String> comboBoxBook = new JComboBox<String>();
		comboBoxBook.setBounds(461, 133, 107, 20);
		add(comboBoxBook);
		
		JLabel lblListOfBook = new JLabel("List of Books By name");
		lblListOfBook.setBounds(300, 136, 137, 14);
		add(lblListOfBook);
		
		JRadioButton absolute_btn = new JRadioButton("Absolute Rate");
		absolute_btn.setBounds(334, 214, 123, 25);
		add(absolute_btn);
		
		JRadioButton proportion_btn = new JRadioButton("Proportion Rate");
		proportion_btn.setBounds(461, 214, 123, 25);
		proportion_btn.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
			
			}
		});
		add(proportion_btn);
		
		
		JButton btnShowButton = new JButton("Show");
		btnShowButton.setBounds(373, 303, 117, 22);
		btnShowButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
////////////////////////button to next window-Show /////////////////////////////////////////////
				ShowBookRate Sbr=new ShowBookRate(screen);
				Sbr.btnBack.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent e) 
					{
							screen.setContentPane(pann);
					}
/////////////////////////button to next window-Show/////////////////////////////////////////////
				});
		screen.setContentPane(Sbr);//send to search book window
			}
		});
		add(btnShowButton);
		}
}

