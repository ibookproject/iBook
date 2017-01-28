package MemberGUI;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.MatteBorder;

import Book.Book;
import Book.Cart;
import Controller.BookController;
import Controller.CartController;
import Controller.UserController;
import MenuGUI.LoginGUI;
import Panels.CartCheckBoxBooklistPanel;
import Role.User;
import Role.UserStatus;
import command.joinObject;

import javax.swing.JOptionPane;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
/**
 * The class take care of the Cart   functionality- Buy and downloads books, and remove them
 * @author Sagi Entenberg
 * @author Hen Saada
 * @category download a book from server
 *  
 */
public class CartManagerGUI extends JPanel {

	public static JPanel panel;
	public LoginGUI screen;
	public JButton btnBack ;
	private JScrollPane scrollPaneMain;
	private ArrayList <User> searcRes;
	private Date date;
	private int cnt;
	private JButton btnBuy;
	private int SignleSubscriptionGUI;

/**
 * Consructor- building the panel
 * @param screen
 *  LoginGUI extends JFrame
 * @param UserIdAtDataBase
 * 
 */
	public CartManagerGUI(LoginGUI screen,int UserIdAtDataBase) {
		super();
		SignleSubscriptionGUI=0;
		this.screen=screen;
		cnt=0;
		initialize();
	}
	/**
	 * This method initialize The window of Format manager,puts the components on the screen and set their functionality
	 * When the user get in to this window, all of his book's that he added to the cart will show up at the list, all the book that there border is GREEN 
	 * are those who have been purchased already . the user can delete  book's that he still didn't bought (only was added to the cart)
	 * the user can download again the book's that he already have bought
	 * @author  hen saada
	 * 
	 */
	private void initialize() {
		
		this.setSize(850, 625);
		this.setLayout(null);	
		ImageIcon backIcon =new ImageIcon("Extras/Images/backIcon.png");
		btnBack = new JButton(backIcon);// declaration of back button
		btnBack.setBounds(35, 25, 89, 30);
		add(btnBack);
		User u= new User();
		btnBuy = new JButton("Buy");
		btnBuy.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JLabel LabelMassege = new JLabel("NO subscription Method");
		LabelMassege.setForeground(Color.RED);
		LabelMassege.setFont(new Font("Tahoma", Font.PLAIN, 20));
		LabelMassege.setBounds(311, 543, 226, 54);
		LabelMassege.setVisible(false);
		add(LabelMassege);
		
		//checking if there is any subscription method
		searcRes = UserController.SearchUser("userID,firstName,lastName,subscriptionMethod,privilege",u,"subscriptionMethod=\"" + 1 + "\""+" && " + "userID=\"" + screen.getTempID() + "\"", screen.getClient());//call search book method from book controller
		if(searcRes!=null)
			SignleSubscriptionGUI=1;
			
	
		if(SignleSubscriptionGUI==0)
		{
			searcRes = UserController.SearchUser("userID,firstName,lastName,subscriptionMethod,privilege",u,"subscriptionMethod<>\"" + 0 + "\""+" && " + "userID=\"" + screen.getTempID() + "\"", screen.getClient());//call search book method from book controller
			if(searcRes==null)
			{
				btnBuy.setEnabled(false);
				LabelMassege.setVisible(true);
			}	
		}

		scrollPaneMain = new JScrollPane();
		scrollPaneMain.getVerticalScrollBar().setUnitIncrement(16);
		scrollPaneMain.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneMain.setAutoscrolls(true);
		scrollPaneMain.setBounds(35, 133, 749, 355);
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
		int flag=0;
		ArrayList<Book> bbb;
		/**JOIN BETWEEN BOOK AND CART**/

		bbb=CartController.GetCartListForUser("book.bookID,book.title,book.author,book.price",c,temp,"userID=\""+screen.getTempID() +"\""+" && "+"status=\""+Cart.BOUGHT+"\"", screen.getClient());
		if (bbb != null) {
			flag=1;
			panel.removeAll();
			panel.setVisible(true);
			scrollPaneMain.setVisible(true);
			for(int i=0;i<bbb.size();i++)
			{
				panel.add(new CartCheckBoxBooklistPanel(screen,bbb.get(i),bbb.get(i).getBookID(),panel,i,1));
				((CartCheckBoxBooklistPanel)panel.getComponent(i)).setBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(46, 139, 87)));
				((CartCheckBoxBooklistPanel)panel.getComponent(i)).chckbxNewCheckBox.setSelected(false);
				((CartCheckBoxBooklistPanel)panel.getComponent(i)).chckbxNewCheckBox.setEnabled(false);
				((CartCheckBoxBooklistPanel)panel.getComponent(i)).btnDownloadBookAgain.setVisible(true);
				((CartCheckBoxBooklistPanel)panel.getComponent(i)).RemoveButton.setVisible(false);
			}
		} 
		bbb=CartController.GetCartListForUser("book.bookID,book.title,book.author,book.price",c,temp,"userID=\""+screen.getTempID() +"\""+" && "+"status=\""+Cart.ORDERD+"\"" , screen.getClient());
		if (bbb != null) {
			if(flag==0)
			{
			panel.removeAll();
			panel.setVisible(true);
			scrollPaneMain.setVisible(true);
			}
			for(int i=0;i<bbb.size();i++)
				panel.add(new CartCheckBoxBooklistPanel(screen,bbb.get(i),bbb.get(i).getBookID(),panel,i,0));
		} 
		 if(panel.getComponentCount()==0)
		{
			panel.setVisible(false);
			scrollPaneMain.setVisible(false);
			JOptionPane.showMessageDialog(screen,"Your cart is Empty", "no results",JOptionPane.QUESTION_MESSAGE);
			
		}

		JLabel lblButFromCart = new JLabel("Buy From Cart");
		lblButFromCart.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 21));
		lblButFromCart.setBounds(350, 40, 165, 40);
		add(lblButFromCart);
		
		JLabel lblChooseBooksFrom = new JLabel("Choose book's from cart :");
		lblChooseBooksFrom.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblChooseBooksFrom.setBounds(340, 101, 198, 20);
		add(lblChooseBooksFrom);
		
		 
		btnBuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int flag=0;
				int cntSelected=0;
		ArrayList<Integer> tempBooksId = new 	ArrayList<Integer> ();
		
		for(int i=0;i<panel.getComponentCount();i++)
		{
			if((((CartCheckBoxBooklistPanel)panel.getComponent(i)).chckbxNewCheckBox.isSelected())==true)
				cntSelected++;
		}
		
		if(SignleSubscriptionGUI==1&&panel.getComponentCount()!=0&&cntSelected>1)
		{
			JOptionPane.showMessageDialog(screen,"Sorry you can buy only 1 book from the cart.(single subscriptionMethod)", "Warning",JOptionPane.WARNING_MESSAGE);
			cntSelected=0;	
		}
	else if(panel.getComponentCount()!=0)
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
						CartController.UpdateCart(c, "status=\""+Cart.BOUGHT+"\"" + " && "+"buyDate=\""+txtDate+"\"", "userID=\""+screen.getTempID()+"\""+ " && "+"bookID=\""+(((CartCheckBoxBooklistPanel)panel.getComponent(i))).BookID+"\"", screen.getClient());
						((CartCheckBoxBooklistPanel)panel.getComponent(i)).chckbxNewCheckBox.setSelected(false);
						((CartCheckBoxBooklistPanel)panel.getComponent(i)).btnDownloadBookAgain.setVisible(true);
						((CartCheckBoxBooklistPanel)panel.getComponent(i)).RemoveButton.setVisible(false);
						panel.updateUI();

						// ************ SAVE FILE *****************//
						int id = ((CartCheckBoxBooklistPanel)panel.getComponent(i)).BookID;
						final JFileChooser fc = new JFileChooser();
						//fc.setCurrentDirectory(new java.io.File("C:/Users/kfir/Desktop"));;
						fc.setFileFilter(new FileTypeFilter(".pdf","PDF"));
						fc.setFileFilter(new FileTypeFilter(".doc","Word Document"));
						fc.setFileFilter(new FileTypeFilter(".fb2","Fiction Book"));
						fc.setDialogTitle( "Save As: ");
					//	fc.setFileFilter(new FileTypeFilter(".PDF","PDF"));
					if(fc.showSaveDialog(null)==JFileChooser.APPROVE_OPTION)
					{
						byte[] FileToSave;
						try {
							FileToSave=BookController.GetBookFile(id, ((FileTypeFilter)fc.getFileFilter()).toString(), screen.getClient());
							 BookController.writeBytesToFile(FileToSave,fc.getSelectedFile().getAbsolutePath()+(FileTypeFilter)fc.getFileFilter());
								
						} catch (IOException | SQLException e) {
							
							JOptionPane.showMessageDialog(screen,"Cant download File !", "Warning",JOptionPane.WARNING_MESSAGE);
						}
					}			
					}
					
					if(SignleSubscriptionGUI==1&&cntSelected!=0)
					{
						UserController.UpdateUserStatus(u, "subscriptionMethod=\""+0+"\"", "userID=\""+screen.getTempID()+"\"", screen.getClient());
						btnBuy.setEnabled(false);
						LabelMassege.setVisible(true);	
					}

				}
				if(cnt==panel.getComponentCount()) 
					JOptionPane.showMessageDialog(screen,"You Bought all your Cart Book's list !", "Warning",JOptionPane.WARNING_MESSAGE);
				else if(flag==1)
				{
					cnt=0;
						JOptionPane.showMessageDialog(screen,"The Purshace  successfully  !", "done",JOptionPane.INFORMATION_MESSAGE);
				}
				else if (cnt==0)  
						JOptionPane.showMessageDialog(screen,"no Chossen book's to buy", "Warning",JOptionPane.WARNING_MESSAGE);
			}
			else JOptionPane.showMessageDialog(screen,"no Chossen book's to buy", "Warning",JOptionPane.WARNING_MESSAGE);
	}
});		
		btnBuy.setBounds(372, 516, 80, 39);
		add(btnBuy);		

	}	
}