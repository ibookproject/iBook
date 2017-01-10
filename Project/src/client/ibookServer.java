package client;
// This file contains material supporting section 3.7 of the textbook:

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import com.mysql.jdbc.*;

import Role.User;
import command.DBquery;
import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

/**
 * This class overrides some of the methods in the abstract superclass in order
 * to give more functionality to the server.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;re
 * @author Fran&ccedil;ois B&eacute;langer
 * @author Paul Holden
 * @version July 2000
 */
public class ibookServer extends AbstractServer {
	/*
	 * HandleMessageFromClient ServerStarted ServerStopped
	 */
	// Class variables *************************************************

	public String sendMessage;
	public ArrayList<DBgenericObject> resultList;
	private  myConnection myconnt;
	private  ArrayList<User> userlist;
	/**
	 * The default port to listen on.
	 */
	final public static int DEFAULT_PORT = 5555;

	// Constructors ****************************************************

	/**
	 * Constructs an instance of the echo server.
	 *
	 * @param port
	 *            The port number to connect on.
	 */
	public ibookServer(int port, String userName, String password) {
		super(port);
		resultList = null;
		userlist=new ArrayList<User>();
		myconnt = myConnection.startConnection("jdbc:mysql://localhost/ibook", password, userName);
	}

	// Instance methods ************************************************

	/**
	 * This method handles any messages received from the client.
	 *
	 * @param msg
	 *            The message received from the client.
	 * @param client
	 *            The connection from which the message originated.
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */

	public void handleMessageFromClient(Object msg, ConnectionToClient client)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {

		Connection conn = myConnection.getMyConnection();
		Statement stmt = conn.createStatement();
		ResultSet resultquery;
		int NumOfCol;
		System.out.println("Message received: " + msg + " from " + client);
		try {
			if (msg instanceof DBquery) {

				switch (((DBquery) msg).getType()) {
				case "insert":
					if ((stmt.executeUpdate(((DBquery) msg).getQuery())) == 0)
						throw new SQLException(
								"you have a error in your command, the translation is \n" + ((DBquery) msg).getQuery());
					else
						client.sendToClient("object has inserted");
					break;
				case "GetAllTable":
					resultList = new ArrayList<DBgenericObject>();
					resultquery = (ResultSet) stmt.executeQuery(((DBquery) msg).getQuery());
					NumOfCol = resultquery.getMetaData().getColumnCount();
					while (resultquery.next()) {
						DBgenericObject temp = new DBgenericObject(NumOfCol);
						for (int i = 0; i < NumOfCol; i++)
							temp.insertValtoArray(i, resultquery.getObject(i + 1));
						resultList.add(temp);
					}
					if (resultList.isEmpty())
						throw new RuntimeException("The table is empty");
					else
						client.sendToClient(resultList);
					resultquery.close();
					break;
				case "search":
					resultList = new ArrayList<DBgenericObject>();
					resultquery = (ResultSet) stmt.executeQuery(((DBquery) msg).getQuery());
					NumOfCol = resultquery.getMetaData().getColumnCount();
					while (resultquery.next()) {
						DBgenericObject temp = new DBgenericObject(NumOfCol);
						for (int i = 0; i < NumOfCol; i++)
							temp.insertValtoArray(i, resultquery.getObject(i + 1));
						resultList.add(temp);
					}
					if (resultList.isEmpty())
						throw new RuntimeException("not found any result\n");
					else
						client.sendToClient(resultList);
					resultquery.close();
					break;

				case "delete":
					if ((stmt.executeUpdate(((DBquery) msg).getQuery())) == 0)
						throw new SQLException(
								"you have a error in your command, the translation is \n" + ((DBquery) msg).getQuery());
					else
						client.sendToClient("object has removed");
					break;
				case "update":
					if ((stmt.executeUpdate(((DBquery) msg).getQuery())) == 0)
						throw new SQLException(
								"you have a error in your command, the translation is \n" + ((DBquery) msg).getQuery());
					else
						client.sendToClient("object has updated");
					break;
				default:
					break;
				}
				stmt.close();
				conn.close();
			} 
			else {
				if (msg instanceof User) {
					try {
						setUser((User) msg);
					}//end try 
					catch (Exception e) {
						System.out.println("there is an exaption " + e.getClass() + ": " + e);
						client.sendToClient(e);
					}//end catch
				} //end if
				else
					client.sendToClient("You Send wrong message");
			} // end else
		} catch (Exception e) {
			System.out.println("there is an exaption " + e.getClass() + ": " + e);
			try {
				client.sendToClient(e);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	/**
	 * This method overrides the one in the superclass. Called when the server
	 * starts listening for connections.
	 */
	protected void serverStarted() {
		System.out.println("Server listening for connections on port " + getPort());
		try {
			Connection conn = myConnection.getMyConnection();
			System.out.println("SQL connection succeed");
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
				| SQLException ex) {/* handle any errors */
			System.out.println("Exception: " + ex.getMessage());
		}
	}

	/**
	 * This method overrides the one in the superclass. Called when the server
	 * stops listening for connections.
	 */
	protected void serverStopped() {
		try {
			myConnection.closeMyConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Server has stopped listening for connections.");

	}

	// Class methods ***************************************************
	public void setUser(User thisUser) throws RuntimeException {
		if (userlist == null || userlist.isEmpty()) {
			userlist=new  ArrayList<User>();
			userlist.add(thisUser);
		} else {
			if (userlist.contains(thisUser))
				throw new RuntimeException("this user is already loged in!");
			else
				userlist.add(thisUser);
		}
		System.out.println(userlist);

	}

	/**
	 * This method is responsible for the creation of the server instance (there
	 * is no UI in this phase).
	 *
	 * @param args[0]
	 *            The port number to listen on. Defaults to 5555 if no argument
	 *            is entered.
	 */
	public static void main(String[] args) {
		int port; // Port to listen on
		Scanner scan = new Scanner(System.in);
		String userName, password;
		try {
			port = Integer.parseInt(args[0]); // Get port from command line
		} catch (Throwable t) {
			port = DEFAULT_PORT; // Set port to 5555
		}
		System.out.println("please insert user name for mysql\n");
		userName = scan.nextLine();

		System.out.println("please insert password for mysql\n");
		password = scan.nextLine();

		ibookServer sv = new ibookServer(port, userName, password);
		scan.close();
		try {
			sv.listen(); // Start listening for connections
		} catch (Exception ex) {
			System.out.println("ERROR - Could not listen for clients!");
		}
	}
}
// End of EchoServer class
