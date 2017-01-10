package ManagmentGUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

import Book.Domain;
import Book.SubjectToBook;
import Controller.UserController;
import Controller.bookController;
import MenuGUI.LoginGUI;
import Role.User;

public class BookRateGUI extends JPanel {
	

	private static final long serialVersionUID = 1L;
	public JButton btnBack ;
	private LoginGUI screen;
	private JPanel pann;
	private JLabel lblChooseDomain;
	private JComboBox comboBoxChooseDomain;
	private JLabel lblChooseSubject;
	private JComboBox comboBoxChooseSubject;
	private JLabel lblChooseBook;
	private JRadioButton absolute_btn;
	private JRadioButton proportion_btn;
	private JButton btnShowButton;
	private JLabel bookRateLbl;
	private JComboBox<String> comboBoxBook;
	private ArrayList<Domain> Domains;
	private ArrayList<SubjectToBook> Subjects;
	
	public BookRateGUI(LoginGUI screen) 
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
		
		bookRateLbl = new JLabel("Request Book Rate");
		bookRateLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		bookRateLbl.setBounds(320, 52, 175, 22);
		add(bookRateLbl);
		
		comboBoxBook = new JComboBox<String>();
		comboBoxBook.setBounds(335, 247, 107, 20);
		add(comboBoxBook);
		
		lblChooseDomain = new JLabel("Choose Domain :");
		lblChooseDomain.setBounds(335, 120, 116, 23);
		add(lblChooseDomain);
		
		comboBoxChooseDomain = new JComboBox();
		comboBoxChooseDomain.setBounds(335, 142, 101, 20);
		Domain d=new Domain("nature");
		Domains=bookController.getAllDomainTable(d, screen.getClient());
		for(int i=0;i<Domains.size();i++)
			comboBoxChooseDomain.addItem(Domains.get(i));
		add(comboBoxChooseDomain);
		
		comboBoxChooseDomain.getSelectedItem();
		
		
		lblChooseSubject = new JLabel("Choose Subject : ");
		lblChooseSubject.setBounds(335, 173, 107, 14);
		add(lblChooseSubject);
		
		Domain d1=new Domain(1,"nature");
		SubjectToBook s=new SubjectToBook(1,"temp");
		comboBoxChooseSubject = new JComboBox();
		comboBoxChooseSubject.setBounds(335, 190, 103, 20);
		ArrayList<SubjectToBook> Subjects= (ArrayList<SubjectToBook>)bookController.SearchSubject("nameSubject",s,"domainID=\""+d1.getDomainID()+"\"" ,screen.getClient());
		if(Subjects==null||Subjects.isEmpty())
		{
			screen.setContentPane(pann);
			JOptionPane.showMessageDialog(screen,"No Subject Found\n", "Warning",JOptionPane.WARNING_MESSAGE);
		}
	
		else
		{
			JOptionPane.showMessageDialog(screen,"Subject Found per Domain\n", "Warning",JOptionPane.WARNING_MESSAGE);
		}
		for(int i=0;i<Subjects.size();i++)
			comboBoxChooseSubject.addItem(Subjects.get(i));
		comboBoxChooseSubject.addActionListener(comboBoxBook);
		add(comboBoxChooseSubject);
		
		lblChooseBook = new JLabel("Choose Book :");
		lblChooseBook.setBounds(335, 222, 91, 23);
		add(lblChooseBook);
		
		absolute_btn = new JRadioButton("Absolute Rate");
		absolute_btn.setBounds(269, 315, 123, 25);
		add(absolute_btn);
		
		proportion_btn = new JRadioButton("Proportion Rate");
		proportion_btn.setBounds(422, 315, 123, 25);
		proportion_btn.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
			
			}
		});
		add(proportion_btn);
		
		
		btnShowButton = new JButton("Show");
		btnShowButton.setBounds(334, 406, 117, 22);
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

