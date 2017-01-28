
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
import java.sql.SQLException;
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
/**
 * @author hen saada 
 * This panel presents the details of the Cart book's List
 */
public class CartCheckBoxBooklistPanel extends JPanel{
	private LoginGUI screen;
	private JLabel lblAnswerfromserver;
	public JCheckBox chckbxNewCheckBox ;
	public static JPanel panel;
	public int BookID;
	public  int index;
	public  int IsBought;
	public JButton RemoveButton;
	public Book book;
	public JButton btnDownloadBookAgain;

	
	
	/**
	 * This is the constructor of the class CartManager-put the components on the screen and set their functionality
	 * @param screen This is the main window-login
	 * @param book is the Book which the panel shown his details
	 * @param BookID save the book id of the represented book at the current panel
	 * @param pan is the previous window of the search
	 * @param save the index that this panel available at the main panel components list
	 * @param IsBought mark if the book is bought
	 * @author  hen saada
	 */
	public CartCheckBoxBooklistPanel(LoginGUI screen,Book book,int BookID,JPanel panel,int indeex,int IsBought) {
		this.book=book;
		this.IsBought=IsBought;
		this.index=indeex;
		this.panel=panel;
		this.BookID=BookID;
		setBackground(Color.WHITE);
		setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));

		setPreferredSize(new Dimension(701, 107));
		setLayout(null);
		ImageIcon backIcon =new ImageIcon("Extras/Images/download.png");

		lblAnswerfromserver = new JLabel("");
		lblAnswerfromserver.setBounds(415, 66, 152, 14);
		add(lblAnswerfromserver);
		
		 chckbxNewCheckBox = new JCheckBox();
		 chckbxNewCheckBox.setFont(new Font("Tahoma", Font.BOLD, 16));
		 chckbxNewCheckBox.setBackground(SystemColor.inactiveCaptionBorder);
		chckbxNewCheckBox.setText("Title:  " + book.getTitle() + "   Author: " + book.getAuthor() +"    price: " +book.getPrice());
		chckbxNewCheckBox.setBounds(6, 11, 468, 88);
		add(chckbxNewCheckBox);
		
		RemoveButton = new JButton("Remove");
		RemoveButton.setFont(new Font("Tahoma", Font.BOLD, 16));
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
						panel.updateUI();
					}												
			}
		});
		RemoveButton.setBounds(538, 21, 124, 30);
		add(RemoveButton);
		
		btnDownloadBookAgain = new JButton("download",backIcon);
		btnDownloadBookAgain.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnDownloadBookAgain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				// ************ SAVE FILE *************//
				int id = BookID;
				final JFileChooser fc = new JFileChooser();
				//fc.setCurrentDirectory(new java.io.File("C:/Users/kfir/Desktop"));;
				fc.setFileFilter(new FileTypeFilter(".pdf","PDF"));
				fc.setFileFilter(new FileTypeFilter(".doc","Word Document"));
				fc.setFileFilter(new FileTypeFilter(".fb2","Fiction Book"));
				fc.setDialogTitle( "SAVE THE BOOK !! :   " +"author : " +(book.getAuthor()+"  ,  " + " title :  "+ book.getTitle()));
			//	fc.setFileFilter(new FileTypeFilter(".PDF","PDF"));
			if(fc.showSaveDialog(null)==JFileChooser.APPROVE_OPTION)
			{
				byte[] FileToSave;
				try {
					FileToSave=BookController.GetBookFile(id, ((FileTypeFilter)fc.getFileFilter()).toString(), screen.getClient());
					 BookController.writeBytesToFile(FileToSave,fc.getSelectedFile().getAbsolutePath()+(FileTypeFilter)fc.getFileFilter());
						
				} catch (IOException | SQLException ex) {
					
					JOptionPane.showMessageDialog(screen,"Cant download File !", "Warning",JOptionPane.WARNING_MESSAGE);
					
				}
			}			
			}
		});
		btnDownloadBookAgain.setBounds(507, 59, 184, 40);
		btnDownloadBookAgain.setVisible(false);
		add(btnDownloadBookAgain);
	}
}



