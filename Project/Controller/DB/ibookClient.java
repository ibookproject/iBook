package DB;
import Controller.UserController;
import MenuGUI.LoginGUI;
import Role.User;

import java.util.Vector;

import javax.swing.JFrame;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class ibookClient {
	private static String exitID;
	private static LoginGUI exitCLIENT;
	public static void main( String args[] )
	   {
		String host;
		try {
			host = args[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			host = "localhost";
		}// take the IP of server the host
		
		//
		//host="192.168.10.250";
		//
		  LoginGUI loginFrame = new LoginGUI(host); // create StudentFrame

		  
		  loginFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		  loginFrame.setSize( 850,625 ); // set frame size
		  loginFrame.setLocationRelativeTo(null); // for set the window at  the middle for default ! 
		  loginFrame.setVisible( true ); // display frame
		  
	  } // end main
	public String getExitID() {
		return exitID;
	}
	public static void setExitID(String ID) {
		exitID = ID;
	}
	public static LoginGUI getExitCLIENT() {
		return exitCLIENT;
	}
	public static void setExitCLIENT(LoginGUI exitCLIENT) {
		ibookClient.exitCLIENT = exitCLIENT;
	}

}
