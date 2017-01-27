package Controller;

import java.sql.SQLException;
import java.util.ArrayList;

import Book.Book;
import Role.LibraryWorker;
import Role.User;
import client.DBSQLhandler;
import client.DBgenericObject;
import command.searchCommand;
import command.showAllCommand;
/**
 * This class is the Worker Controller- here is the functionality that connected to workers 
 * @author Almog Yamin
 */
public class WorkerController {
	/**
	 * Search User
	 * @param fromSentence
	 *  String of the SQL query "From"
	 * @param worker
	 * the name of the table from this object
	 * @param whereSentence
	 * String of the SQL query "Where"
	 * @param client
	 * the client who ask the query
	 * @return
	 * Array list of LibraryWorker
	 * @author Almog Yamin
	 */
	public static ArrayList<LibraryWorker> SearchWorker(String fromSentence,LibraryWorker worker,String whereSentence,DBSQLhandler client)
	{
		client.searchInDB(new searchCommand<LibraryWorker>(fromSentence,worker,whereSentence));//call command and client ask to search a book
		while(!client.GetGotMessag()){//search user in db
			try{
			Thread.sleep(10);
			}
			catch(InterruptedException ex)
			{
				System.out.println("InterruptedException "+ex);
			}
		}
		try {
			return LibraryWorker.convertBack((ArrayList<DBgenericObject>) client.getResultObject(),fromSentence);
		} catch (SQLException e) {
			return null;
		}
	}

}
