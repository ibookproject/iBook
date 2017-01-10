package DB;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import com.sun.prism.Image;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;


public class WaitGif extends JFrame {
	public WaitGif() {
		
		JLabel lblNewLabel = new JLabel("");
	lblNewLabel.setIcon(new ImageIcon("bookGif.gif"));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 482, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 261, Short.MAX_VALUE)
		);
		getContentPane().setLayout(groupLayout);
		setUndecorated(true);
	}

}
