package Controller;

import java.sql.SQLException;
import java.util.ArrayList;

import Book.book;
import Role.user;
import client.DBSQLhandler;
import command.DBtranslation;
import command.insertCommand;
import command.searchCommand;

public class UserController {
	/*
	 * SearchUser - Done
	 * Login
	 * UpdateUserInfo
	 * CreateNewAccount
	 * RequestStatisticUserReport
	 * RequestSubscription
	 * SetStatusSubscription
	 * GetUserList
	 * CheckUserDetails
	 * UpdateUserStatus
	 * */
	
	public static ArrayList<?> SearchUser(user u,String condition,DBSQLhandler client)
	{
		// filed is need to look like "userID,password,..."
		client.searchInDB(new searchCommand<user>(u,condition, "userID,privilege"));//call command and client ask to search a book
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
	public static boolean CreateNewAccount(user b,DBSQLhandler client) // boolean function that return true if user added else false.
	{
			client.insertToDB(new insertCommand<DBtranslation>(b)); 	
			while(!client.GetGotMessag()){//add book to DB
				try{
				Thread.sleep(500);
				}
				catch(InterruptedException ex)
				{
					System.out.println("InterruptedException "+ex);
					return false;
				}
			}
			return true;	// means the book add successful	
	}

}
