package Panels;

import javax.swing.JPanel;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.MatteBorder;

import java.awt.Color;

import javax.swing.border.LineBorder;
import javax.swing.JCheckBox;

import Book.Book;
import Book.Domain;
import Book.Review;
import Controller.BookController;
import ManagmentGUI.ConfirmationReviewGUI;
import ManagmentGUI.RemovePartReviewGUI;
import MenuGUI.LoginGUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JTextArea;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.CompoundBorder;

import client.DBgenericObject;
/**
 * @author Coral Carmeli
 * This panel presents the details of the review in the method 'Confirmation Review'
 */
public class ReviewPanel extends JPanel{
	
	private LoginGUI screen;
	private JLabel lblNewTitle;
	private JLabel lblNewDate;
	private JLabel lblDate;
	private JLabel lblContent;
	private JTextArea textAreaContent;
	private JLabel lblBookTitle;
	private JCheckBox chckbxChooseReview;
	private JButton btnRemovePartOf;
	private int reviewId;
	private int Permission;
	private JPanel pann;
	private ConfirmationReviewGUI crg;
	
	public ReviewPanel(LoginGUI screen,int reviewID,String reviewContent,String bookTitle,int permission,JPanel pan,String reviewDate) {
		setBackground(Color.WHITE);
		setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new MatteBorder(1, 1, 1, 1, (Color) new Color(255, 200, 0))));
		setPreferredSize(new Dimension(731, 149));
		setLayout(null);
		reviewId=reviewID;
		Permission=permission;
		pann=pan;
		
		lblDate = new JLabel("Review Date:");
		lblDate.setFont(new Font("VAGRounded BT", Font.BOLD, 17));
		lblDate.setBounds(34, 102, 126, 23);
		add(lblDate);
		
		lblNewTitle = new JLabel(bookTitle);
		lblNewTitle.setBounds(155, 11, 93, 25);
		add(lblNewTitle);
		
		lblNewDate = new JLabel(reviewDate);
		lblNewDate.setBounds(155, 107, 93, 16);
		add(lblNewDate);
		
		lblContent = new JLabel("Review \r\nContent:");
		lblContent.setFont(new Font("VAGRounded BT", Font.BOLD, 17));
		lblContent.setBounds(310, 10, 150, 23);
		add(lblContent);
		
		
		
		lblBookTitle = new JLabel("Book Name:");
		lblBookTitle.setFont(new Font("VAGRounded BT", Font.BOLD, 17));
		lblBookTitle.setBounds(34, 10, 114, 23);
		add(lblBookTitle);
		
		chckbxChooseReview = new JCheckBox("");
		chckbxChooseReview.setBackground(Color.WHITE);
		chckbxChooseReview.setBounds(8, 61, 29, 25);
		add(chckbxChooseReview);
		
		btnRemovePartOf = new JButton("Remove Part Of Review");
		btnRemovePartOf.setBounds(563, 47, 173, 25);
		btnRemovePartOf.addActionListener(new ActionListener() 
		{
		public void actionPerformed(ActionEvent e) 
		{
////////////////////////button to back to confirmation review from remove part of review /////////////////////////////////////////////
			RemovePartReviewGUI Rpr=new RemovePartReviewGUI(screen,reviewID);
			
			Rpr.btnBack.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) 
				{
					//crg=new ConfirmationReviewGUI(screen, Permission);
					
					screen.setContentPane(pann);
				}
			});

////////////////////////button to back to confirmation review from remove part of review/////////////////////////////////////////////
		
	
				screen.setContentPane(Rpr);			
		}
		});
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setAutoscrolls(true);
		scrollPane.setBounds(240, 36, 322, 100);
		add(scrollPane);
		
		textAreaContent = new JTextArea();
		textAreaContent.setEditable(false);
		textAreaContent.setFont(new Font("Monospaced", Font.PLAIN, 14));
		textAreaContent.setLineWrap(true);
		textAreaContent.setWrapStyleWord(true);
		scrollPane.setViewportView(textAreaContent);
		textAreaContent.setRows(4);
		textAreaContent.setBackground(Color.YELLOW);
		textAreaContent.setForeground(new Color(0, 0, 0));
		textAreaContent.setText(reviewContent);
		
	if(this.Permission==5)
	{
		add(btnRemovePartOf);
	}
	
	}
	public int getchbxChoose()
	{
		if(chckbxChooseReview.isSelected())
			return 1;
		else
			return 0;
	}
	public int getReviewId()
	{
		return reviewId;
	}
}
