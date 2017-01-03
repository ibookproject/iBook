package DB;
import MenuGUI.LoginGUI;

import java.util.Vector;

import javax.swing.JFrame;


public class Test {
	
	public static void main( String args[] )
	   {
		String host;
		try {
			host = args[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			host = "localhost";
		}// take the IP of server the host
		  LoginGUI loginFrame = new LoginGUI(host); // create StudentFrame
		  loginFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		  loginFrame.setSize( 850,625 ); // set frame size
		  loginFrame.setLocationRelativeTo(null); // for set the window at  the middle for default ! 
		  loginFrame.setVisible( true ); // display frame
		  
	  } // end main
	
}
