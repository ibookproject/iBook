package MemberGUI;

import javax.swing.*;
import javax.swing.border.MatteBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import Book.Book;
import MenuGUI.LoginGUI;
import client.DBgenericObject;
import command.showAllCommand;




public class SearchBook extends JPanel {


	private static final long serialVersionUID = 1L;
	public JButton btnBack ;
	private LoginGUI screen;
	private JPanel pann;
	private ImageIcon backIcon;
	private JButton btnPostReview;
	private JLabel lblSearchBook;
	private ArrayList<Book> searchRes;
	public static JPanel Test;

	public SearchBook(LoginGUI screen) {
		super();
		setForeground(Color.LIGHT_GRAY);
		this.screen=screen;
		pann=this;
		initialize();
	}

	private void initialize()
	{
		this.setSize(850, 625);
		this.setLayout(null);	
		
		backIcon =new ImageIcon("src/images/backIcon.png");
		btnPostReview = new JButton("Post Review");
		btnPostReview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
////////////////////////button to back panel from panel /////////////////////////////////////////////
				RequestPostFillReviewGUI Pfr=new RequestPostFillReviewGUI(screen,"ID1234");
				Pfr.btnBack.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						screen.setContentPane(pann);
					}
////////////////////////button to back panel from panel/////////////////////////////////////////////
				});
				screen.setContentPane(Pfr);//
			}
		});
	
		btnPostReview.setBounds(684, 11, 123, 25);
		add(btnPostReview);
		btnBack = new JButton(backIcon);// declaration of back button
		btnBack.setBounds(39, 52, 89, 23);
		add(btnBack);
		
		lblSearchBook = new JLabel("Search Book");
		lblSearchBook.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSearchBook.setBounds(355, 49, 175, 22);
		add(lblSearchBook);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setAutoscrolls(true);
		scrollPane.setBounds(49, 106, 758, 332);
		add(scrollPane);
		
		Test = new JPanel();
		Test.setIgnoreRepaint(true);
		Test.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		Test.setAutoscrolls(true);
		Test.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		scrollPane.setViewportView(Test);
		Test.setLayout(new GridLayout(0, 1, 0, 0));
		//panel.add(new BookPanel());
		
	Test.add(new BookPanel());
	Test.add(new BookPanel());
	Test.add(new BookPanel());
	Test.add(new BookPanel());
	Test.add(new BookPanel());
	Test.add(new BookPanel());
	Test.add(new BookPanel());
	Test.add(new BookPanel());
	Test.add(new BookPanel());
	Test.add(new BookPanel());
		
		ArrayList<JButton> test=new ArrayList<JButton>();

		
	}
	/**
	 * 
	 *@author hensaada
	 *@return nothing
	 *@param list 
	 *give you shit.
	 **/
	public void setList(ArrayList<Book> list)
	{	
		this.searchRes=list;
		String temp = "";
		for(Book b:list)		
			temp+=String.format("%s \n",b.toString());
	
		
	}
}
