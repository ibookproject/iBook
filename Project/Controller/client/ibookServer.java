package client;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import com.mysql.jdbc.ResultSet;
import command.DBquery;
import command.FileCommand;
import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

/**
 * This class overrides some of the methods in the abstract superclass in order
 * to give more functionality to the server.
 *
 * 
 */
public class ibookServer extends AbstractServer {
	/*
	 * HandleMessageFromClient ServerStarted ServerStopped
	 */
	// Class variables *************************************************

	public String sendMessage;
	public ArrayList<DBgenericObject> resultList;
	private myConnection myconnt;
	/**
	 * The default port to listen on.
	 */
	final public static int DEFAULT_PORT = 5555;

	// Constructors ****************************************************

	/**
	 * Constructs an instance of the ibook server. furthermore, set the
	 * connection attributes and start the connection
	 * 
	 * @param port
	 *            The port number to connect on.
	 * @param userName
	 *            The user name to connect into data base
	 * @param password
	 *            The password to connect into data base
	 * @author kfir Girstein
	 */
	public ibookServer(int port, String userName, String password) {
		super(port);
		resultList = null;
		myconnt = myConnection.startConnection("jdbc:mysql://localhost/ibook", password, userName);
	}

	// Instance methods ************************************************

	/**
	 * This method handles any messages received from the client. with the
	 * massage had received form the client this method directed with action to
	 * do
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
		System.out.println("Message received: " + msg + " from " + client);
		try {
			if (msg instanceof FileCommand)
				FileHandler((FileCommand) msg, client);
			else if (msg instanceof DBquery) {

				switch (((DBquery) msg).getType()) {
				case "insert":
					InsertHandler(((DBquery) msg).getQuery(), client, stmt);
					break;
				case "GetAllTable":
					ShowAllHandler(((DBquery) msg).getQuery(), client, stmt);
					break;
				case "search":
					SearchHandler(((DBquery) msg).getQuery(), client, stmt);
					break;

				case "delete":
					DeleteHandler(((DBquery) msg).getQuery(), client, stmt);
					break;
				case "update":
					UpdateHandler(((DBquery) msg).getQuery(), client, stmt);
					break;
				default:
					client.sendToClient("You Send wrong message");
					break;
				}
				stmt.close();
				conn.close();
			} else
				client.sendToClient("You Send wrong message");

		} catch (Exception e) {
			if (e instanceof RuntimeException)
				System.out.println("there is no answer form the query" + msg.toString());
			try {
				client.sendToClient(e);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	/**
	 * Upload or Send(depend on the File Command) a file.
	 * 
	 * @param msg
	 *            the {@link FileCommand} with all the information on what to
	 *            do, if to upload a file to the server or to send a file to
	 *            client
	 * @param client
	 *            witch client was sent the massage
	 * @throws IOException
	 */
	private void FileHandler(FileCommand msg, ConnectionToClient client) throws IOException {
		if (((FileCommand) msg).getBytesToFile() != null) {
			writeBytesToFile(((FileCommand) msg).getBytesToFile(),
					"C:\\Books\\" + ((FileCommand) msg).getBookName() + ".pdf");
			writeBytesToFile(((FileCommand) msg).getBytesToFile(),
					"C:\\Books\\" + ((FileCommand) msg).getBookName() + ".doc");
			writeBytesToFile(((FileCommand) msg).getBytesToFile(),
					"C:\\Books\\" + ((FileCommand) msg).getBookName() + ".fb2");

			System.out.println("Message File received:   from " + client);

		} else {
			File fileTOSend = new File(
					"C:\\Books\\" + ((FileCommand) msg).getBookName() + ((FileCommand) msg).getType());
			byte[] toSend = DBSQLhandler.getBytesFromFile(fileTOSend);
			client.sendToClient(toSend);
		}
	}

	/**
	 * send an insert query to the data base, and check if the query succeed
	 * 
	 * @param query
	 *            the query to send to the data base (received form
	 *            DBSQLhandler)
	 * @param client
	 *            witch client was sent the massage
	 * @param stmt
	 *            {@link Statement} Object to send the query to the data base
	 * @throws SQLException
	 * @throws IOException
	 */
	private void InsertHandler(String query, ConnectionToClient client, Statement stmt)
			throws SQLException, IOException {
		if ((stmt.executeUpdate(query) == 0))
			throw new SQLException("you have a error in your command, the translation is \n" + query);
		else
			client.sendToClient("object has inserted");
	}

	/**
	 * send an "SELET * FROM TABLE" query to the data base, and send the result
	 * to the client
	 * 
	 * @param query
	 *            the query to send to the data base (received form
	 *            DBSQLhandler)
	 * @param client
	 *            witch client was sent the massage
	 * @param stmt
	 *            {@link Statement} Object to send the query to the data base
	 * @throws SQLException
	 * @throws IOException
	 */
	private void ShowAllHandler(String query, ConnectionToClient client, Statement stmt)
			throws SQLException, IOException {
		resultList = new ArrayList<DBgenericObject>();
		ResultSet resultquery = (ResultSet) stmt.executeQuery(query);
		int NumOfCol = resultquery.getMetaData().getColumnCount();
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
	}

	/**
	 * send an search query to the data base, and send the result to the client
	 * 
	 * @param query
	 *            the query to send to the data base (received form
	 *            DBSQLhandler)
	 * @param client
	 *            witch client was sent the massage
	 * @param stmt
	 *            {@link Statement} Object to send the query to the data base
	 * @throws SQLException
	 * @throws IOException
	 */
	private void SearchHandler(String query, ConnectionToClient client, Statement stmt)
			throws SQLException, IOException {
		resultList = new ArrayList<DBgenericObject>();
		ResultSet resultquery = (ResultSet) stmt.executeQuery(query);
		int NumOfCol = resultquery.getMetaData().getColumnCount();
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
	}

	/**
	 * send an delete query to the data base, and check if the query succeed
	 * 
	 * @param query
	 *            the query to send to the data base (received form
	 *            DBSQLhandler)
	 * @param client
	 *            witch client was sent the massage
	 * @param stmt
	 *            {@link Statement} Object to send the query to the data base
	 * @throws SQLException
	 * @throws IOException
	 */
	private void DeleteHandler(String query, ConnectionToClient client, Statement stmt)
			throws SQLException, IOException {
		if ((stmt.executeUpdate(query)) == 0)
			throw new SQLException("you have a error in your command, the translation is \n" + query);
		else
			client.sendToClient("object has removed");
	}

	/**
	 * send an update query to the data base, and check if the query succeed
	 * 
	 * @param query
	 *            the query to send to the data base (received form
	 *            DBSQLhandler)
	 * @param client
	 *            witch client was sent the massage
	 * @param stmt
	 *            {@link Statement} Object to send the query to the data base
	 * @throws SQLException
	 * @throws IOException
	 */
	private void UpdateHandler(String query, ConnectionToClient client, Statement stmt)
			throws SQLException, IOException {
		if ((stmt.executeUpdate(query)) == 0)
			throw new SQLException("you have a error in your command, the translation is \n" + query);
		else
			client.sendToClient("object has updated");
	}

	/**
	 * This method overrides the one in the superclass. Called when the server
	 * starts listening for connections.
	 * 
	 * @author Sagi Entenberg
	 * @author Kfir Girestein
	 */
	protected void serverStarted() {
		System.out.println("Server listening for connections on port " + getPort());
		try {
			Connection conn = myConnection.getMyConnection();
			Statement stmt = conn.createStatement();
			String query = "UPDATE user SET userStatus=0 WHERE userStatus=1";
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
			System.out.println("ERROR - Could not listen for clients!" + ex.getMessage());
		}
	}

	/**
	 * 
	 * @param bFile
	 *            the bytes to write
	 * @param fileDest
	 *            the path of the file Direction
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
