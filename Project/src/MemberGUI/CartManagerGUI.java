package MemberGUI;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.MatteBorder;

import Book.Book;
import Book.Cart;
import Book.Domain;
import Book.Review;
import Book.Subject;
import Book.SubjectToBook;
import Controller.CartController;
import Controller.FormatController;
import Controller.UserController;
import Controller.BookController;
import MenuGUI.LoginGUI;
import Panels.CartCheckBoxBooklistPanel;
import Panels.FormatCheckBoxBooklistPanel;
import Panels.SearchReviewPanel;
import Role.User;
import client.DBgenericObject;
import command.joinCommand;
import command.joinObject;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;




public class CartManagerGUI extends JPanel {

	public static JPanel panel;
	public LoginGUI screen;
	public JButton btnBack ;
	private int UserIdAtDataBase;
	private JScrollPane scrollPaneMain;
	private ArrayList <User> searcRes;
	private Date date;
	private int cnt;


	public CartManagerGUI(LoginGUI screen,int UserIdAtDataBase) {
		super();
		this.screen=screen;
		cnt=0;
		initialize();
		this.UserIdAtDataBase=UserIdAtDataBase;
	}

	/**
	 * This method initializes StudentForm
	 */
	private void initialize() {
		
		this.setSize(850, 625);
		this.setLayout(null);	
		ImageIcon backIcon =new ImageIcon("src/images/backIcon.png");
		btnBack = new JButton(backIcon);// declaration of back button
		btnBack.setBounds(39, 52, 89, 23);
		add(btnBack);
		User u= new User();

		searcRes = UserController.SearchUser("userID,firstName,lastName,subscriptionMethod,privilege",u,"subscriptionMethod<>\"" + 0 + "\""+" && " + "userID=\"" + screen.getTempID() + "\"", screen.getClient());//call search book method from book controller
		if(searcRes!=null)
		{
		
		/////////////////////
		scrollPaneMain = new JScrollPane();
		scrollPaneMain.getVerticalScrollBar().setUnitIncrement(16);
		scrollPaneMain.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneMain.setAutoscrolls(true);
		scrollPaneMain.setBounds(272, 132, 337, 324);
		scrollPaneMain.setVisible(false);
		add(scrollPaneMain);

		panel = new JPanel();
		panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		panel.setAutoscrolls(true);
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		scrollPaneMain.setViewportView(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));

		Book b=new Book();	
		Cart c = new Cart();
		ArrayList<joinObject> temp =new ArrayList<joinObject>();

		temp.add(new joinObject(c.getClassName(), b.getClassName(), "bookID"));
		
		ArrayList<Book> bbb;
		bbb=CartController.GetCartListForUser("book.bookID,book.title,book.author,book.price",c,temp,"userID=\""+screen.getTempID() +"\""+" && "+"status=0" , screen.getClient());
		//System.out.print(bbb);
		if (bbb != null) {
			panel.removeAll();
			panel.setVisible(true);
			scrollPaneMain.setVisible(true);
			for(Book tempb:bbb)
				panel.add(new CartCheckBoxBooklistPanel(screen,tempb,tempb.getBookID()));
		} 
		else 
		{
			panel.setVisible(false);
			scrollPaneMain.setVisible(false);
			JOptionPane.showMessageDialog(screen,"Your cart is Empty", "no results",JOptionPane.QUESTION_MESSAGE);
		}

		JLabel lblButFromCart = new JLabel("Buy From Cart");
		lblButFromCart.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblButFromCart.setBounds(379, 52, 163, 23);
		add(lblButFromCart);
		
		JLabel lblChooseBooksFrom = new JLabel("Choose book's from cart :");
		lblChooseBooksFrom.setBounds(379, 107, 151, 14);
		add(lblChooseBooksFrom);
		
		JButton btnBuy = new JButton("Buy");
		btnBuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int flag=0;
		ArrayList<Integer> tempBooksId = new 	ArrayList<Integer> ();
			if(panel.getComponentCount()!=0)
			{
				Cart c= new Cart();
				date = new Date();
				String txtDate = new SimpleDateFormat("yyyy/MM/dd").format(date);
				SimpleDateFormat dt = new SimpleDateFormat("yyyy/MM/dd"); 
				date = new Date(txtDate);
				for(int i=0;i<panel.getComponentCount();i++)
				{
					if((((CartCheckBoxBooklistPanel)panel.getComponent(i)).chckbxNewCheckBox.isSelected())==true)
					{
						tempBooksId.add(((CartCheckBoxBooklistPanel)panel.getComponent(i)).book.getBookID());
						flag=1;
						cnt++;
						((CartCheckBoxBooklistPanel)panel.getComponent(i)).setBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(46, 139, 87)));
						((CartCheckBoxBooklistPanel)panel.getComponent(i)).chckbxNewCheckBox.setEnabled(false);
						CartController.UpdateCart(c, "status=\""+1+"\"" + " && "+"buyDate=\""+txtDate+"\"", "userID=\""+screen.getTempID()+"\""+ " && "+"bookID=\""+(((CartCheckBoxBooklistPanel)panel.getComponent(i))).BookID+"\"", screen.getClient());
						((CartCheckBoxBooklistPanel)panel.getComponent(i)).chckbxNewCheckBox.setSelected(false);
					}
					//panel.updateUI();
				}
				System.out.println(cnt);
				System.out.println(panel.getComponentCount());
				if(cnt==panel.getComponentCount()) 
					JOptionPane.showMessageDialog(screen,"You Bought all your Cart Book's list !", "Warning",JOptionPane.WARNING_MESSAGE);
				else if(flag==1)
				{
			
					JOptionPane.showMessageDialog(screen,"The Purshace  successfully  !", "done",JOptionPane.INFORMATION_MESSAGE);
				}				
				else if (cnt==panel.getComponentCount()) 
							JOptionPane.showMessageDialog(screen,"You Bought all your Cart Book's list !", "Warning",JOptionPane.WARNING_MESSAGE);
			}
			else JOptionPane.showMessageDialog(screen,"no Chossen book's to buy", "Warning",JOptionPane.WARNING_MESSAGE);
	}
});		
		btnBuy.setBounds(390, 467, 59, 23);
		add(btnBuy);		
	}
	else JOptionPane.showMessageDialog(screen,"NO subscriptionMethod !!! ", "Warning",JOptionPane.WARNING_MESSAGE);
	}	
}