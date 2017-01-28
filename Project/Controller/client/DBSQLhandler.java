// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

package client;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import Role.User;
import command.DBquery;
import command.FileCommand;
import command.deleteCommand;
import command.insertCommand;
import command.joinCommand;
import command.searchCommand;
import command.showAllCommand;
import command.updateCommand;
import ocsf.client.AbstractClient;

/**
 * This class overrides some of the methods defined in the abstract superclass
 * in order to give more functionality to the client.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;
 * @author Fran&ccedil;ois B&eacute;langer
 * @version July 2000
 */
public class DBSQLhandler extends AbstractClient {
	/*
	 * GetAllTable AddToDB SearchInDB RemoveFromDB UpdateDB
	 * HandleMessageFromServer GetResultObject SetResultObject GetReports
	 */
	// Instance variables **********************************************

	/**
	 * The interface type variable. It allows the implementation of the display
	 * method in the client.
	 */
	private boolean gotMessage; // if the server end to work with the client
	public boolean gotExceptions;/// if there is Exceptions
	private DBquery dbq;
	public Object resultObject; // the result of the search
	private User nowRunUser; // the user is now running at the this client
								// running
	// Constructors ****************************************************

	/**
	 * Constructs an instance of the DBSQLhandler.
	 *
	 * @param host
	 *            The server to connect to.
	 * @param port
	 *            The port number to connect on.
	 */

	public DBSQLhandler(String host, int port) throws IOException {
		super(host, port); // Call the superclass constructor
		this.gotMessage = true;
		openConnection();
	}

	// Instance methods ************************************************
	/**
	 * This method handles all data that comes in from the server. it set the
	 * answer to the result Object and release the spinlock
	 * 
	 * @param msg
	 *            The message from the server.
	 * @throws SQLException
	 */
	public void handleMessageFromServer(Object msg) {
		setResultObject(msg);
		synchronized (this) {
			setGotMessage(true);
		}

	}

	/**
	 * This method handles data coming from the UI and translated the insert
	 * message to a SQL query to send to the server Furthermore it lock the
	 * spinlock
	 * 
	 * @param message
	 *            The message from the UI, this is a insert command with the
	 *            information to send
	 */
	public void insertToDB(insertCommand<?> message) {
		try {
			synchronized (this) {
				setGotMessage(false);
			}
			dbq = new DBquery(
					String.format("INSERT INTO %s %s VALUES %s", message.getTable(),
							message.getToInsert().getAttributeToInsert(), message.getToInsert().getValToInsert()),
					"insert");
			sendToServer(dbq);
		} catch (IOException e) {
			System.out.println("Could not send message to server.  Terminating client.\n" + e);
			quit();
		}
	}

	/**
	 * This method handles data coming from the UI and send the file message to
	 * the server,with file to set into the server files Furthermore it lock the
	 * spinlock
	 * 
	 * @param message
	 *            The message from the UI, this is a file command with the
	 *            information to send
	 */
	public void SendFileToServer(FileCommand message) {
		try {
			synchronized (this) {
				setGotMessage(false);
			}

			sendToServer(message);
		} catch (IOException e) {
			System.out.println("Could not send message to server.  Terminating client.\n" + e);
			quit();
		}
	}

	/**
	 * This method handles data coming from the UI and send the file message to
	 * the server,with file to get form server Furthermore it lock the spinlock
	 * 
	 * @param message
	 *            The message from the UI, this is a file command with the
	 *            information to send
	 */
	public void GetFileFromServer(FileCommand message) {
		try {
			synchronized (this) {
				setGotMessage(false);
			}
			sendToServer(message);
		} catch (IOException e) {
			System.out.println("Could not send message to server.  Terminating client.\n" + e);
			quit();
		}
	}

	/**
	 * This method handles data coming from the UI and translated the get all
	 * table message to a SQL query to send to the server Furthermore it lock
	 * the spinlock
	 * 
	 * @param message
	 *            The message from the UI, this is a get all command with the
	 *            information to send
	 */
	public void getAllTable(showAllCommand<?> message) {
		try {
			synchronized (this) {
				setGotMessage(false);
			}
			dbq = new DBquery(String.format("SELECT * FROM %s", message.getTable()), "GetAllTable");
			sendToServer(dbq);
		} catch (IOException e) {
			System.out.println("Could not send message to server.  Terminating client.\n" + e);
			quit();
		}
	}

	/**
	 * This method handles data coming from the UI and translated the delete
	 * message to a SQL query to send to the server Furthermore it lock the
	 * spinlock
	 * 
	 * @param message
	 *            The message from the UI, this is a delete command with the
	 *            information to send
	 */
	public void deleteFromDB(deleteCommand<?> message) {
		DBquery dbq;
		try {
			synchronized (this) {
				setGotMessage(false);
			}
			dbq = new DBquery(String.format("DELETE FROM %s WHERE %s", message.getTable(), message.getCondition()),
					"delete");
			sendToServer(dbq);
		} catch (IOException e) {
			System.out.println("Could not send message to server.  Terminating client.\n" + e);
			quit();
		}
	}

	/**
	 * This method handles data coming from the UI and translated the update
	 * message to a SQL query to send to the server Furthermore it lock the
	 * spinlock
	 * 
	 * @param message
	 *            The message from the UI, this is a update command with the
	 *            information to send
	 */
	public void UpdateInDB(updateCommand<?> message) {
		try {
			synchronized (this) {
				setGotMessage(false);
			}
			dbq = new DBquery(String.format("UPDATE %s SET %s WHERE %s", message.getTable(),
					message.getUpdateCondition(), message.getCondition()), "update");
			sendToServer(dbq);
		} catch (IOException e) {
			System.out.println("Could not send message to server.  Terminating client.\n" + e);
			quit();
		}
	}

	/**
	 * This method handles data coming from the UI and translated the search
	 * message to a SQL query to send to the server Furthermore it lock the
	 * spinlock
	 * 
	 * @param message
	 *            The message from the UI, this is a search command with the
	 *            information to send
	 */
	public void searchInDB(searchCommand<?> message) {
		try {
			synchronized (this) {
				setGotMessage(false);
			}
			dbq = new DBquery(String.format("Select %s FROM %s WHERE %s", message.getfiledsToSearch(),
					message.getTable(), message.getCondition()), "search");
			sendToServer(dbq);
		} catch (IOException e) {
			System.out.println("Could not send message to server.  Terminating client.\n" + e);
			quit();
		}
	}

	/**
	 * This method handles data coming from the UI and translated the join
	 * search message to a SQL query to send to the server Furthermore it lock
	 * the spinlock
	 * 
	 * @param message
	 *            The message from the UI, this is a join search command with
	 *            the information to send
	 */
	public void joinSearchInDB(joinCommand<?> message) {
		try {
			synchronized (this) {
				setGotMessage(false);
			}
			String temp = "";
			if (message.getCondition() == null || message.getCondition().equals(""))
				temp = message.getJoinQuery();
			else
				temp = message.getJoinQuery() + message.getCondition();
			dbq = new DBquery(
					String.format("Select %s FROM %s WHERE %s", message.getfiledsToSearch(), message.getTable(), temp),
					"search");
			sendToServer(dbq);
		} catch (IOException e) {
			System.out.println("Could not send message to server.  Terminating client.\n" + e);
			quit();
		}
	}

	/**
	 * only for this class, this is set the lock
	 * 
	 * @param flag
	 */
	private synchronized void setGotMessage(boolean flag) {
		this.gotMessage = flag;
	}

	/**
	 * When necessary synchronization is needed this method is the lock
	 * receiver.
	 * 
	 * @return the spinlock status
	 */
	public synchronized boolean GetGotMessag() {
		return this.gotMessage;
	}

	/**
	 * 
	 * @return result from the server
	 * @throws SQLException
	 */
	public synchronized Object getResultObject() throws SQLException {
		if (resultObject instanceof Throwable)
			throw new SQLException(((Exception) resultObject).getMessage());
		return resultObject;
	}

	/**
	 * only for this class, when the answer form the server has become it set
	 * the result.
	 * 
	 * @param resultObject
	 *            the result from the server
	 */
	private synchronized void setResultObject(Object resultObject) {
		this.resultObject = resultObject;
	}

	public User getNowRunUser() {
		if (nowRunUser == null)
			throw new NullPointerException("no user is connected");
		return nowRunUser;
	}

	public void setNowRunUser(User nowRunUser) throws RuntimeException {
		if (nowRunUser == null)
			throw new NullPointerException("no user is sent.. this null pointer");
		this.nowRunUser = nowRunUser;
	}

	/**
	 * This method terminates the client.
	 */
	public void quit() {
		try {
			closeConnection();
		} catch (IOException e) {
		}
		System.exit(0);
	}

	/**
	 * 
	 * @param bFile
	 *            the bytes to write
	 * @param fileDest
	 *            the path of the file Direction
	 * @author Sagi Entenberg
	 */
	public static byte[] getBytesFromFile(File file) throws IOException {
		// Get the size of the file
		long length = file.length();

		// You cannot create an array using a long type.
		// It needs to be an int type.
		// Before converting to an int type, check
		// to ensure that file is not larger than Integer.MAX_VALUE.
		if (length > Integer.MAX_VALUE) {
			// File is too large
			throw new IOException("File is too large!");
		}

		// Create the byte array to hold the data
		byte[] bytes = new byte[(int) length];

		// Read in the bytes
		int offset = 0;
		int numRead = 0;

		InputStream is = new FileInputStream(file);
		try {
			while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
				offset += numRead;
			}
		} finally {
			is.close();
		}

		// Ensure all the bytes have been read in
		if (offset < bytes.length) {
			throw new IOException("Could not completely read file " + file.getName());
		}
		return bytes;
	}

}
// End of DBSQLhandler class
