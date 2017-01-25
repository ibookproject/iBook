package Controller;

import java.sql.SQLException;
import java.util.ArrayList;

import Book.Book;
import Role.LibraryWorker;
import client.DBSQLhandler;
import client.DBgenericObject;
import command.showAllCommand;

public class WorkerConroller {
	
	public static ArrayList<LibraryWorker> getAllWorkersTable(LibraryWorker lb,DBSQLhandler client)
	{
		client.getAllTable(new showAllCommand<LibraryWorker>(lb));
		while(!client.GetGotMessag()){//show table -domain
			try{
			Thread.sleep(10);
			}
			catch(InterruptedException ex)
			{
				System.out.println("InterruptedException "+ex);
			}
		}
		try {
			
			return  LibraryWorker.convertBack((ArrayList<DBgenericObject>) client.getResultObject(), "userID,department,role,email");
		} catch (SQLException e) {
			return null;
		}
	}

}
