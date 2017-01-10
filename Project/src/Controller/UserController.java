package Controller;

import java.sql.SQLException;
import java.util.ArrayList;

import Book.Book;
import DB.WaitGif;
import MenuGUI.LoginGUI;
import Role.User;
import client.DBSQLhandler;
import client.DBgenericObject;
import command.DBtranslation;
import command.insertCommand;
import command.searchCommand;
import command.updateCommand;

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
	public static boolean CreateNewAccount(User u,DBSQLhandler client) // boolean function that return true if user added else false.
	{
			client.insertToDB(new insertCommand<DBtranslation>(u)); 	
			while(!client.GetGotMessag()){//add user to DB
				try{
				Thread.sleep(500);
				}
				catch(InterruptedException ex)
				{
					System.out.println("InterruptedException "+ex);
					return false;
				}
			}
			return true;	// means the user add successful	
	}
	
	public static boolean SetStatusSubscription(User u , String updateCondition , String searchCondition, DBSQLhandler client) // boolean function that return true if user updated else false.
	{
			client.UpdateInDB(new updateCommand<DBtranslation>(u, searchCondition, updateCondition));
			while(!client.GetGotMessag()){//add user to DB
				try{
				Thread.sleep(500);

				}
				catch(InterruptedException ex)
				{
					System.out.println("InterruptedException "+ex);
					return false;
				}
			}
			return true;	// means the user add successful	
	}
	//UpdateUserStatus
	public static boolean UpdateUserStatus(User u , String updateCondition , String searchCondition, DBSQLhandler client) // boolean function that return true if user updated else false.
	{
			client.UpdateInDB(new updateCommand<DBtranslation>(u, searchCondition, updateCondition));
			WaitGif wg=new WaitGif();
			while(!client.GetGotMessag()){//add user to DB
				try{
				Thread.sleep(500);
				wg.setVisible(true);
				}
				catch(InterruptedException ex)
				{
					System.out.println("InterruptedException "+ex);
					return false;
				}
			}
			wg.setVisible(false);
			return true;	// means the user add successful	
	}
}
