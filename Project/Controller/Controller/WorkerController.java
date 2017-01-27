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

public class WorkerController {
	
	public static ArrayList<LibraryWorker> SearchWorker(String fromSentence,LibraryWorker user,String whereSentence,DBSQLhandler client)
	{
		client.searchInDB(new searchCommand<LibraryWorker>(fromSentence,user,whereSentence));//call command and client ask to search a book
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
