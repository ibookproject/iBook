package Controller;

import java.sql.SQLException;
import java.util.ArrayList;

import Book.book;
import Role.user;
import client.DBSQLhandler;
import command.searchCommand;

public class UserController {
	
	public static ArrayList<user> SearchUser(user u,String condition,DBSQLhandler client)
	{
		// filed is need to look like "userID,password,..."
		client.searchInDB(new searchCommand<user>(u,condition, "UserID"));//call command and client ask to search a book
		while(!client.GetGotMessag()){//search user in db
			try{
			Thread.sleep(500);
			}
			catch(InterruptedException ex)
			{
				System.out.println("InterruptedException "+ex);
			}
		}
		try {
			return (ArrayList<user>)client.getResultObject();
		} catch (SQLException e) {
			return null;
		}
	}

}
