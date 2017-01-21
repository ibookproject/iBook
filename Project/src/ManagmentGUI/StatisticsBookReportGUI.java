package ManagmentGUI;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.MatteBorder;

import Book.Book;
import Book.Domain;
import Controller.UserController;
import Controller.BookController;
import MenuGUI.LoginGUI;
import Panels.BookStatisticsPanel;
import Panels.UserSubscriptionPanel;
import Panels.Validation;
import Role.User;
import client.DBgenericObject;

import javax.swing.JTextArea;

public class StatisticsBookReportGUI extends JPanel {

	private static final long serialVersionUID = 1L;
	public JButton btnBack;
	private LoginGUI screen;
	private JPanel pann;
	private JTextField textFieldBookTitle;
	private JTextField textFieldAuthor;
	private ArrayList<Book> tempBooks;

	public StatisticsBookReportGUI(LoginGUI screen) {
		super();
		this.screen = screen;
		pann = this;

		initialize();
	}

	private void initialize() {

		this.setSize(850, 625);
		this.setLayout(null);

		JLabel lblNewLabel = new JLabel("Statistics Book Report");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(355, 49, 175, 22);
		add(lblNewLabel);

		ImageIcon backIcon = new ImageIcon("src/images/backIcon.png");
		btnBack = new JButton(backIcon);
		btnBack.setBounds(39, 52, 89, 23);
		add(btnBack);

		JLabel lblBookName = new JLabel("Book title:");
		lblBookName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblBookName.setBounds(153, 111, 91, 19);
		add(lblBookName);

		textFieldBookTitle = new JTextField();
		textFieldBookTitle.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldBookTitle.setColumns(10);
		textFieldBookTitle.setBounds(245, 105, 148, 30);
		add(textFieldBookTitle);

		JLabel lblAuthor = new JLabel("Author name:");
		lblAuthor.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAuthor.setBounds(429, 111, 100, 19);
		add(lblAuthor);

		textFieldAuthor = new JTextField();
		textFieldAuthor.setColumns(10);
		textFieldAuthor.setBounds(535, 105, 148, 30);
		add(textFieldAuthor);

		JScrollPane scrollPaneMain = new JScrollPane();
		scrollPaneMain.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneMain.setAutoscrolls(true);
		scrollPaneMain.setBounds(125, 159, 690, 393);
		add(scrollPaneMain);

		JPanel panel = new JPanel();
		panel.setIgnoreRepaint(true);
		panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		panel.setAutoscrolls(true);
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		scrollPaneMain.setViewportView(panel);
		scrollPaneMain.getVerticalScrollBar().setUnitIncrement(16);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		Book b = new Book();
		JButton btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//////////////////////// button to back panel from panel
				//////////////////////// /////////////////////////////////////////////
				StatisticsBookReport sbr = new StatisticsBookReport(screen);
				sbr.btnBack.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						screen.setContentPane(pann);
					}
					//////////////////////// button to back panel from
					//////////////////////// panel/////////////////////////////////////////////
				});
				panel.removeAll();

				if ((textFieldBookTitle.getText().isEmpty()) && (textFieldAuthor.getText().isEmpty()))// check if field are empty
					JOptionPane.showMessageDialog(screen, "you must fill one field at least ", "Warning",
							JOptionPane.WARNING_MESSAGE);
				else {
					String warnings = "ERROR :\n";
					if (Validation.AuthorValidation(textFieldAuthor.getText().trim(), 20) == false)
						if (!textFieldAuthor.getText().isEmpty())
							warnings += "author field - Must contain only English letters \n";
					if (Validation.TitleValidation(textFieldBookTitle.getText().trim(), 20) == false) // check validation fields
						if (!textFieldBookTitle.getText().isEmpty())
							warnings += "title field - Must contain only English letters or numbers\n";
						if (warnings == "ERROR :\n") {
							Book b = new Book(); // create
							if (textFieldBookTitle.getText().isEmpty())
								tempBooks = BookController.SearchBook("title,author,bookID", b,
										"author LIKE '%" + textFieldAuthor.getText().trim() + "%'", screen.getClient());
							else if (textFieldAuthor.getText().isEmpty())
								tempBooks = BookController.SearchBook("title,author,bookID", b,
										"title LIKE '%" + textFieldBookTitle.getText().trim() + "%'",
										screen.getClient());
							else {
								tempBooks = BookController.SearchBook("title,author,bookID", b,
										"title LIKE '%" + textFieldBookTitle.getText().trim() + "%'" + " && "
												+ "author LIKE '%" + textFieldAuthor.getText().trim() + "%'",
										screen.getClient());
							}
							if (tempBooks != null) {
								for (Book bt : tempBooks)
									panel.add(new BookStatisticsPanel(screen, bt, pann));
								panel.updateUI();
							} else
							{
								JOptionPane.showMessageDialog(screen, "The book is not found!", "Warning",
										JOptionPane.WARNING_MESSAGE);
								panel.updateUI();
							}
							textFieldBookTitle.setText("");
							textFieldAuthor.setText("");
							
						}
					else {
						JOptionPane.showMessageDialog(screen, warnings, "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			}

		});
		btnSearch.setBounds(710, 103, 89, 30);
		add(btnSearch);

	}
}
