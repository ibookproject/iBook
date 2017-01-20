
package Panels;

import javax.swing.JPanel;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.MatteBorder;

import java.awt.Color;

import javax.swing.border.LineBorder;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;

import Book.Book;
import Book.Cart;
import Book.Domain;
import Book.Review;
import Controller.UserController;
import MemberGUI.FileTypeFilter;
import Controller.BookController;
import Controller.CartController;
import MenuGUI.LoginGUI;
import Role.User;
import Role.UserStatus;

import java.awt.Font;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.DropMode;
import javax.swing.ImageIcon;

import java.awt.SystemColor;

public class CartCheckBoxBooklistPanel extends JPanel{
	private LoginGUI screen;
	private JLabel lblAnswerfromserver;
	public JCheckBox chckbxNewCheckBox ;
	public static JPanel panel;
	public int BookID;
	public  int index;
	public  int IsBought;
//	public int price;
	//public int UserId;
	public JButton RemoveButton;
	public Book book;
	public JButton btnDownloadBookAgain;
	public  JButton RemoveAfterBuy;
	
	
	public CartCheckBoxBooklistPanel(LoginGUI screen,Book book,int BookID,JPanel panel,int indeex,int IsBought) {
		this.book=book;
		this.IsBought=IsBought;
		this.index=indeex;
		this.panel=panel;
		this.BookID=BookID;
		//this.price=price;
		setBackground(Color.WHITE);
		setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));

		setPreferredSize(new Dimension(494, 73));
		setLayout(null);
	
		ImageIcon backIcon =new ImageIcon("src/images/download.png");
		
		
		lblAnswerfromserver = new JLabel("");
		lblAnswerfromserver.setBounds(415, 66, 152, 14);
		add(lblAnswerfromserver);
		
		 chckbxNewCheckBox = new JCheckBox();
		 chckbxNewCheckBox.setBackground(SystemColor.inactiveCaptionBorder);
		chckbxNewCheckBox.setText("Name:  " + book.getTitle() + "   Author: " + book.getAuthor() +"    price: " +book.getPrice());
		chckbxNewCheckBox.setBounds(6, 11, 288, 55);
		add(chckbxNewCheckBox);
		
		RemoveButton = new JButton("Remove");
		RemoveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int answer=JOptionPane.showConfirmDialog(null, "are you sure??","Warning !!", JOptionPane.YES_NO_OPTION);
					if(answer==0)//means yes
					{						
						Cart c=new Cart();
						CartController.DeleteFromCart(c, "userID=\""+screen.getTempID()+"\""+ " && "+"bookID=\""+BookID+"\"", screen.getClient());
						panel.remove(index);
						for(int i=index;i<panel.getComponentCount();i++)
						{	
							((CartCheckBoxBooklistPanel)panel.getComponent(i)).index--;
						}
					//	for(int i=0;i<panel.getComponentCount();i++)
					//		System.out.println("*"+((CartCheckBoxBooklistPanel)panel.getComponent(i)).index);
						panel.updateUI();
					}												
			}
		});
		RemoveButton.setBounds(315, 5, 83, 23);
		add(RemoveButton);
		
		btnDownloadBookAgain = new JButton("download",backIcon);
		btnDownloadBookAgain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				// ************ SAVE FILE *****************//
				final JFileChooser fc = new JFileChooser();
				fc.setFileFilter(new FileTypeFilter(".txt","Text File"));
				fc.setDialogTitle( "SAVE THE BOOK !! :   " +"author : " +book.getAuthor()+"  ,  " + " title :  "+ book.getTitle());
			if(fc.showSaveDialog(null)==JFileChooser.APPROVE_OPTION)
			{
				System.out.println(fc.getSelectedFile());
				System.out.println((FileTypeFilter)fc.getFileFilter());
				try {
					FileWriter fw = new FileWriter(fc.getSelectedFile().getAbsolutePath()+(FileTypeFilter)fc.getFileFilter());
					PrintWriter pw = new PrintWriter(fw);						
					pw.println("author : " +book.getAuthor()+"  ,  " + " title :  "+ book.getTitle());
					pw.println();
					pw.println("******************The Book Pagess********************");
					pw.println("******************The Book Pagess********************");
					pw.println("******************The Book Pagess********************");
					pw.println("******************The Book Pagess********************");
					pw.println("******************The Book Pagess********************");
					pw.println("******************The Book Pagess********************");
					pw.close();
				} catch (IOException ex) {
					System.out.println(ex);
				}			
			}}
		});
		btnDownloadBookAgain.setBounds(300, 32, 146, 34);
		btnDownloadBookAgain.setVisible(false);
		add(btnDownloadBookAgain);
		 RemoveAfterBuy = new JButton("Remove");
		RemoveAfterBuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int answer=JOptionPane.showConfirmDialog(null, "are you sure??","Warning !!", JOptionPane.YES_NO_OPTION);
				if(answer==0)//means yes
				{
					Cart c=new Cart();
					CartController.UpdateCart(c, "status=\""+Cart.DELETED+"\"" , "userID=\""+screen.getTempID()+"\""+ " && "+"bookID=\""+BookID+"\"", screen.getClient());
					System.out.println("index="+index);
					panel.remove(index);
					for(int i=index;i<panel.getComponentCount();i++)
					{	
						((CartCheckBoxBooklistPanel)panel.getComponent(i)).index--;
					}
				//	for(int i=0;i<panel.getComponentCount();i++)
				//		System.out.println("*"+((CartCheckBoxBooklistPanel)panel.getComponent(i)).index);				
					panel.updateUI();
				}
				
			}
		});
		RemoveAfterBuy.setBounds(315, 5, 83, 23);
		add(RemoveAfterBuy);
				
	}
	public void SetIsBought(int IsBought)
	{
		this.IsBought=IsBought;
	}
}



