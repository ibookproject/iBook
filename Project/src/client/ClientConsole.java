package client;
// This file contains material supporting section 3.7 of the textbook:

// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class constructs the UI for a chat client. It implements the chat
 * interface in order to activate the display() method. Warning: Some of the
 * code here is cloned in ServerConsole
 *
 * @author Fran&ccedil;ois B&eacute;langer
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;re
 * @version July 2000
 */

public class ClientConsole {
	// Class variables *************************************************

	/**
	 * The default port to connect on.
	 */
	final public static int DEFAULT_PORT = 5555;

	// Instance variables **********************************************

	/**
	 * The instance of the client that created this ConsoleChat.
	 */
	DBSQLhandler client;
	//public ArrayList<Worker> toPrintList;
	private Scanner scanner;
	// Constructors ****************************************************

	/**
	 * Constructs an instance of the ClientConsole UI.
	 *
	 * @param host
	 *            The host to connect to.
	 * @param port
	 *            The port to connect on.
	 */
	public ClientConsole(String host, int port) {
		try {
			client = new DBSQLhandler(host, port);
			//toPrintList=new ArrayList<Worker>();
		} catch (IOException exception) {
			System.out.println("Error: Can't setup connection!" + " Terminating client.");
			System.exit(1);
		}
	}

	// Instance methods ************************************************

	/**
	 * This method waits for input from the console. Once it is received, it
	 * sends it to the client's message handler.
	
	 */
	/*
	public void accept() {
		
		try {
			scanner = new Scanner(System.in);
			String nameMsg, depMsg,temp;
			int message = 0;
			boolean breakFlag = false;
			while (!breakFlag) {
					do {
						System.out.println(
								"Please insert your command - \n1. showAll \t\t\t||| (for show our table) \n2. Update \t\t\t||| (for update) \n3. Exit \t\t\t||| (to end the program)");
						message = scanner.nextInt();
					} while (message < 1 && message > 3);

					switch (message) {
					case 1:
						client.setGotMessage(false);
						//toPrintList=new ArrayList<Worker>();
						client.handleMessageFromClientUI("ShowAll worker");
						while(!client.GetGotMessag())
						Thread.sleep(500);
						//toPrintList=(ArrayList<Worker>) client.getResultObject();
						System.out.println("----------WORKER TABLE-----------");
						System.out.println("|Name\t\t|Department\t|");
						System.out.println("---------------------------------");
						//for(Worker w: toPrintList)
							//System.out.println(w);
						System.out.println("---------------------------------\n");
						client.setResultObject(null);
						//toPrintList=null;
						break;
					case 2:
						while(true){
						System.out.println("Enter the name of the Worker that you want to change");
						nameMsg = scanner.next();
						if(nameMsg.matches("[a-zA-Z]+"))
							break;
						System.out.println("please insert only english letters");
						}
						while(true){
						System.out.println("And the updated department");
						depMsg = scanner.next();
						if(depMsg.matches("[a-zA-Z]+"))
							break;
						System.out.println("please insert only english letters");
						}
						client.setGotMessage(false);
						client.handleMessageFromClientUI("Update worker Department " + depMsg + " Name " + nameMsg);
						while(!client.GetGotMessag())
						Thread.sleep(500);
						temp=(String)client.getResultObject();
						System.out.println(temp);
						client.setResultObject(null);
						break; 
					case 3:
						breakFlag = true;
						client.quit();
					default:
						System.out.println("you insert wrong choice,please insert again\n");
						break;
					}
			}
		} catch (Exception ex) {
			System.out.println("Unexpected error while reading from console!\n"+ex.getMessage());
		}
	}

	/**
	 * This method is responsible for the creation of the Client UI.
	 *
	 * @param args[0]
	 *            The host to connect to.
	 */
	public static void main(String[] args) {
		String host;

		try {
			host = args[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			host = "localhost";
		}
		ClientConsole chat = new ClientConsole(host, DEFAULT_PORT);
		//chat.accept(); // Wait for console data
	}


}
// End of ConsoleChat class
