package Controller;

import java.sql.SQLException;
import java.util.ArrayList;

import Book.Book;
import Role.User;
import client.DBSQLhandler;
import client.DBgenericObject;
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
	
	public static ArrayList<User> SearchUser(String fromSentence,User u,String whereSentence,DBSQLhandler client)
	{
		// filed is need to look like "userID,password,..."
		client.searchInDB(new searchCommand<User>(fromSentence,u,whereSentence));//call command and client ask to search a book
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
			return User.convertBack((ArrayList<DBgenericObject>) client.getResultObject(),fromSentence);
		} catch (SQLException e) {
			return null;
		}
	}
	public static boolean CreateNewAccount(User b,DBSQLhandler client) // boolean function that return true if user added else false.
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
