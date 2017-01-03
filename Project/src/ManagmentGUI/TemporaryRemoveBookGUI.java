
package ManagmentGUI;

import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;




public class TemporaryRemoveBookGUI extends JPanel {

	
	private static final long serialVersionUID = 1L;
	public JButton btnBack ;
	public TemporaryRemoveBookGUI(JFrame screen) {
		super();
		initialize();
	}

	
	private void initialize() {
		
		this.setSize(850, 625);
		this.setLayout(null);	
		ImageIcon backIcon =new ImageIcon("src/images/backIcon.png");
		btnBack = new JButton(backIcon);// declaration of back button
		btnBack.setBounds(39, 52, 89, 23);
		add(btnBack);
		
		JLabel lblRemoveBook = new JLabel("Temporary remove book");
		lblRemoveBook.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblRemoveBook.setBounds(355, 49, 218, 22);
		add(lblRemoveBook);
		
		JLabel lblListOfBooks = new JLabel("Books List");
		lblListOfBooks.setBounds(393, 94, 103, 14);
		add(lblListOfBooks);
		
		JComboBox comboBoxBookList = new JComboBox();
		comboBoxBookList.setBounds(378, 112, 96, 20);
		add(comboBoxBookList);
		
		JButton btnTemporaryRemoveBook = new JButton("Temporary remove book");
		btnTemporaryRemoveBook.setBounds(327, 458, 196, 25);
		add(btnTemporaryRemoveBook);

	}
}
