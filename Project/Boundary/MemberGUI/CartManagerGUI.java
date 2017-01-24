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
import Controller.CartController;
import Controller.UserController;
import MenuGUI.LoginGUI;
import Panels.CartCheckBoxBooklistPanel;
import Role.User;
import command.joinObject;

import javax.swing.JOptionPane;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;

public class CartManagerGUI extends JPanel {

	public static JPanel panel;
	public LoginGUI screen;
	public JButton btnBack ;
	private JScrollPane scrollPaneMain;
	private ArrayList <User> searcRes;
	private Date date;
	private int cnt;


	public CartManagerGUI(LoginGUI screen,int UserIdAtDataBase) {
		super();
		this.screen=screen;
		cnt=0;
		initialize();
	}

	private void initialize() {
		
		this.setSize(850, 625);
		this.setLayout(null);	
		ImageIcon backIcon =new ImageIcon("src/images/backIcon.png");
		btnBack = new JButton(backIcon);// declaration of back button
		btnBack.setBounds(39, 52, 89, 23);
		add(btnBack);
		User u= new User();
		
		//checking if there is any subscription method
		searcRes = UserController.SearchUser("userID,firstName,lastName,subscriptionMethod,privilege",u,"subscriptionMethod<>\"" + 0 + "\""+" && " + "userID=\"" + screen.getTempID() + "\"", screen.getClient());//call search book method from book controller
		if(searcRes!=null)
		{
		/////////////////////
		scrollPaneMain = new JScrollPane();
		scrollPaneMain.getVerticalScrollBar().setUnitIncrement(16);
		scrollPaneMain.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneMain.setAutoscrolls(true);
		scrollPaneMain.setBounds(205, 132, 517, 355);
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
		//get all the book at cart that is status 0 or 1 means not buy or already buy but not buy and then delete
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
				((CartCheckBoxBooklistPanel)panel.getComponent(i)).RemoveAfterBuy.setVisible(true);
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
						CartController.UpdateCart(c, "status=\""+Cart.BOUGHT+"\"" + " && "+"buyDate=\""+txtDate+"\"", "userID=\""+screen.getTempID()+"\""+ " && "+"bookID=\""+(((CartCheckBoxBooklistPanel)panel.getComponent(i))).BookID+"\"", screen.getClient());
						((CartCheckBoxBooklistPanel)panel.getComponent(i)).chckbxNewCheckBox.setSelected(false);
						((CartCheckBoxBooklistPanel)panel.getComponent(i)).btnDownloadBookAgain.setVisible(true);
						((CartCheckBoxBooklistPanel)panel.getComponent(i)).RemoveAfterBuy.setVisible(true);
						((CartCheckBoxBooklistPanel)panel.getComponent(i)).RemoveButton.setVisible(false);
						panel.updateUI();

						// ************ SAVE FILE *****************//
						final JFileChooser fc = new JFileChooser();
						//fc.setCurrentDirectory(new java.io.File("C:/Users/kfir/Desktop"));;
						fc.setFileFilter(new FileTypeFilter(".pdf","PDF"));
						fc.setFileFilter(new FileTypeFilter(".doc","Word Document"));
						fc.setFileFilter(new FileTypeFilter(".fb2","Fiction Book"));
						fc.setFileFilter(new FileTypeFilter(".txt","Text File"));
						fc.setDialogTitle( "SAVE THE BOOK !! :   " +"author : " +((CartCheckBoxBooklistPanel)panel.getComponent(i)).book.getAuthor()+"  ,  " + " title :  "+ ((CartCheckBoxBooklistPanel)panel.getComponent(i)).book.getTitle());
					//	fc.setFileFilter(new FileTypeFilter(".PDF","PDF"));
					if(fc.showSaveDialog(null)==JFileChooser.APPROVE_OPTION)
					{
						System.out.println(fc.getSelectedFile());
						System.out.println((FileTypeFilter)fc.getFileFilter());
						try {
							FileWriter fw = new FileWriter(fc.getSelectedFile().getAbsolutePath()+(FileTypeFilter)fc.getFileFilter());
							PrintWriter pw = new PrintWriter(fw);						
							pw.println("author : " +((CartCheckBoxBooklistPanel)panel.getComponent(i)).book.getAuthor()+"  ,  " + " title :  "+ ((CartCheckBoxBooklistPanel)panel.getComponent(i)).book.getTitle());
							pw.println();
							pw.println("******************The Book Pagess********************");
							pw.println("******************The Book Pagess********************");
							pw.println("******************The Book Pagess********************");
							pw.println("******************The Book Pagess********************");
							pw.println("******************The Book Pagess********************");
							pw.println("******************The Book Pagess********************");
							pw.close();
						} 
						catch (IOException ex)
						{
							System.out.println(ex);
						}
					}			
					}
				}
				System.out.println(cnt);
				System.out.println(panel.getComponentCount());
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
		btnBuy.setBounds(417, 519, 80, 39);
		add(btnBuy);		
	}
	else JOptionPane.showMessageDialog(screen,"NO subscriptionMethod !!! ", "Warning",JOptionPane.WARNING_MESSAGE);
	}	
}