// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

package client;

import java.io.IOException;
import java.sql.SQLException;

import command.*;
import ocsf.client.AbstractClient;
/**
 * This class overrides some of the methods defined in the abstract
 * superclass in order to give more functionality to the client.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;
 * @author Fran&ccedil;ois B&eacute;langer
 * @version July 2000
 */
public class DBSQLhandler extends AbstractClient
{
  //Instance variables **********************************************
  
  /**
   * The interface type variable.  It allows the implementation of 
   * the display method in the client.
   */
private  boolean gotMessage;
public  boolean gotExceptions;
private DBquery dbq;
public Object resultObject;
  //Constructors ****************************************************  
  /**
   * Constructs an instance of the chat client.
   *
   * @param host The server to connect to.
   * @param port The port number to connect on.
   * @param clientUI The interface type variable.
   */

  public DBSQLhandler(String host, int port) 
    throws IOException 
  {
    super(host, port); //Call the superclass constructor
    this.gotMessage=true;
    openConnection();
  }
  //Instance methods ************************************************    
  /**
   * This method handles all data that comes in from the server.
   *
   * @param msg The message from the server.
 * @throws SQLException 
   */
  public void handleMessageFromServer(Object msg)
  {
	  setResultObject(msg);
	  setGotMessage(true);
	
  }
  /**
   * This method handles  data coming from the UI            
   *
   * @param message The message from the UI.    
   */
  public void insertToDB(insertCommand<?> message)
  {
    try
    {
    	setGotMessage(false);
    	dbq=new DBquery( String.format("INSERT INTO %s %s VALUES %s",message.getTable(),message.getToInsert().getAttributeToInsert(),message.getToInsert().getValToInsert()),"insert");
    	sendToServer(dbq);
    }
    catch(IOException e)
    {
      System.out.println
        ("Could not send message to server.  Terminating client.\n"+e);
      quit();
    }
  }
  public void getAllTable(showAllCommand<?> message)
  {
    try
    {
    	setGotMessage(false);
    	dbq=new DBquery( String.format("SELECT * FROM %s",message.getTable()),"GetAllTable");
    	sendToServer(dbq);
    }
    catch(IOException e)
    {
      System.out.println
        ("Could not send message to server.  Terminating client.\n"+e);
      quit();
    }
  }
  public void deleteFromDB(deleteCommand<?> message){
	  DBquery dbq;
	    try
	    {
	    	setGotMessage(false);
	    	dbq=new DBquery( String.format("DELETE FROM %s WHERE %s",message.getTable(),message.getCondition()),"delete");
	    	sendToServer(dbq);
	    }
	    catch(IOException e)
	    {
	      System.out.println
	        ("Could not send message to server.  Terminating client.\n"+e);
	      quit();
	    }
  }
  public void UpdateInDB(updateCommand<?> message){
	    try
	    {
	    	setGotMessage(false);
	    	dbq=new DBquery( String.format("UPDATE %s SET %s WHERE %s",message.getTable(),message.getUpdateCondition(),message.getCondition()),"update");
	    	sendToServer(dbq);
	    }
	    catch(IOException e)
	    {
	      System.out.println
	        ("Could not send message to server.  Terminating client.\n"+e);
	      quit();
	    }
  }
  public void searchInDB(searchCommand<?> message){
	    try
	    {
	    	setGotMessage(false);
	    	dbq=new DBquery( String.format("Select %s FROM %s WHERE %s",message.getfiledsToSearch(),message.getTable(),message.getCondition()),"search");
	    	sendToServer(dbq);
	    }
	    catch(IOException e)
	    {
	      System.out.println
	        ("Could not send message to server.  Terminating client.\n"+e);
	      quit();
	    }
  }
  public synchronized void setGotMessage(boolean t){this.gotMessage=t;}
public synchronized  boolean GetGotMessag(){return this.gotMessage;}
public Object getResultObject() throws SQLException {
	 if(resultObject instanceof Throwable )
		  throw new SQLException(((Exception)resultObject).getMessage());
	return resultObject;
}
public void setResultObject(Object resultObject) {
	this.resultObject = resultObject;
}
  /**
   * This method terminates the client.
   */
  public void quit()
  {
    try
    {
      closeConnection();
    }
    catch(IOException e) {}
    System.exit(0);
  }
}
//End of DBSQLhandler class
