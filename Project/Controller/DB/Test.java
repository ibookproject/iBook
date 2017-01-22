package DB;
import Controller.UserController;
import MenuGUI.LoginGUI;
import Role.User;

import java.util.Vector;

import javax.swing.JFrame;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class Test {
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
		  LoginGUI loginFrame = new LoginGUI(host); // create StudentFrame
		  /*
		  loginFrame.addWindowListener(new WindowAdapter() {
		  	@Override
		  
		  	public void windowClosing(WindowEvent e) {
		  		User u = new User(exitID);
		  		UserController.UpdateUserStatus(u, "userStatus=\""+"0"+"\"", "userID=\""+exitID+"\"",exitCLIENT.getClient());
		  		System.out.println(exitID);
		  	}
		  });
		  */
		  
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
		Test.exitCLIENT = exitCLIENT;
	}

}
