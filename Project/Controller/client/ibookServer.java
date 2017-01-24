package client;
// This file contains material supporting section 3.7 of the textbook:

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.Box.Filler;

import com.mysql.jdbc.*;

import Role.User;
import command.DBquery;
import command.FileCommand;
import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

/**
 * This class overrides some of the methods in the abstract superclass in order
 * to give more functionality to the server.
 *

 */
public class ibookServer extends AbstractServer {
	/*
	 * HandleMessageFromClient ServerStarted ServerStopped
	 */
	// Class variables *************************************************

	public String sendMessage;
	public ArrayList<DBgenericObject> resultList;
	private  myConnection myconnt;
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
			if(msg instanceof FileCommand) 
			{
				if(((FileCommand)msg).getBytesToFile()!=null)
				{
					  writeBytesToFile(((FileCommand)msg).getBytesToFile(), "C:\\Books\\" + ((FileCommand)msg).getBookName() + ".pdf");
					  writeBytesToFile(((FileCommand)msg).getBytesToFile(), "C:\\Books\\" + ((FileCommand)msg).getBookName() + ".doc");
					  writeBytesToFile(((FileCommand)msg).getBytesToFile(), "C:\\Books\\" + ((FileCommand)msg).getBookName() + ".fb2");
					  
				System.out.println("Message File received:   from " + client);
				
				}
				else
				{
					File fileTOSend = new File("C:\\Books\\" + ((FileCommand)msg).getBookName() +((FileCommand)msg).getType());
					byte[] toSend=DBSQLhandler.getBytesFromFile(fileTOSend);
					client.sendToClient(toSend);
				}
			}
				
			else if (msg instanceof DBquery) {

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
			} else
				client.sendToClient("You Send wrong message");
			
		} catch (Exception e) {
			if(e instanceof RuntimeException)
			System.out.println("there is no answer form the query"+msg.toString());
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
	 * @author Sagi Entenberg
	 * @author Kfir Girestein
	 */
	protected void serverStarted() {
		System.out.println("Server listening for connections on port " + getPort());
		try {
			Connection conn = myConnection.getMyConnection();
			Statement stmt = conn.createStatement();
			String query="UPDATE user SET userStatus=0";
			stmt.executeUpdate(query);
			stmt.close();
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
			System.out.println("ERROR - Could not listen for clients!"+ex.getMessage());
		}
	}
	/**
	 * 
	 * @param bFile
	 * the bytes to write
	 * @param fileDest
	 * the path of the file Direction
	 * @author Sagi Entenberg
	 */
	 private static void writeBytesToFile(byte[] bFile, String fileDest) {

	        try (FileOutputStream fileOuputStream = new FileOutputStream(fileDest)) {
	            fileOuputStream.write(bFile);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	    }
}
// End of EchoServer class
