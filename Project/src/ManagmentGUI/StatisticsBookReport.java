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
import Panels.BookPanel;
import Panels.BookStatisticsPanel;
import Panels.HistogramPanel;
import Panels.UserSubscriptionPanel;
import Role.User;
import client.DBgenericObject;

import javax.swing.JTextArea;
import javax.swing.JProgressBar;

public class StatisticsBookReport extends JPanel {

	private static final long serialVersionUID = 1L;
	public JButton btnBack;
	private LoginGUI screen;
	private JPanel pann;
	private ArrayList<Book> tempBooks;
	private JLabel lblOrdersdb;
	private JLabel lblSearchersdb;
	private JProgressBar progressBar;
	private JPanel insidePanel;
	private JLabel lblError;

	public StatisticsBookReport(LoginGUI screen) {
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
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnBack.setBounds(39, 52, 89, 23);
		add(btnBack);

		lblOrdersdb = new JLabel("");
		lblOrdersdb.setBounds(236, 182, 46, 14);
		add(lblOrdersdb);

		lblSearchersdb = new JLabel("");
		lblSearchersdb.setBounds(236, 224, 46, 14);
		add(lblSearchersdb);

		insidePanel = new JPanel();
		insidePanel.setBounds(219, 98, 430, 427);
		add(insidePanel);
		
		lblError = new JLabel("");
		lblError.setFont(new Font("Tahoma", Font.PLAIN, 16));
		insidePanel.add(lblError);

	}

	public void setResult(int purchase, int searches) {
		if (purchase != 0 || searches != 0) {
			HistogramPanel histogramPan = new HistogramPanel();
			histogramPan.addHistogramColumn("Purchase", purchase, Color.RED);
			histogramPan.addHistogramColumn("Searches", searches, Color.YELLOW);
			histogramPan.layoutHistogram();
			insidePanel.add(histogramPan);
			insidePanel.updateUI();
		}
		else
		{
			lblError.setText("There is no purchase or searches to this book :(");
			lblError.setForeground(Color.RED);
		}
	}
		
}
