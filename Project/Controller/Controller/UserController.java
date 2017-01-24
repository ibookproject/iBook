package Controller;

import java.sql.SQLException;
import java.util.ArrayList;
import Role.User;
import client.DBSQLhandler;
import client.DBgenericObject;
import command.DBtranslation;
import command.insertCommand;
import command.searchCommand;
import command.updateCommand;
/**
 * User Controller
 * @author Sagi Entenberg
 *
 */
public class UserController {
/**
 * Search User
 * @param fromSentence
 *  String of the SQL query "From"
 * @param user
 * the name of the table from this object
 * @param whereSentence
 * String of the SQL query "Where"
 * @param client
 * the client who ask the query
 * @return
 * Array list of result User
 * @author Sagi Entenberg
 */
	public static ArrayList<User> SearchUser(String fromSentence,User user,String whereSentence,DBSQLhandler client)
	{
		client.searchInDB(new searchCommand<User>(fromSentence,user,whereSentence));//call command and client ask to search a book
		while(!client.GetGotMessag()){//search user in db
			try{
			Thread.sleep(50);
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
	/**
	 * Create New User
	 * @param user
	 * the name of the table from this object
	 * @param client
	 * the client who ask the query
	 * @return
	 * true or false if the update Succeeded
	 * @author Almog Yamin
	 */
	public static boolean CreateNewAccount(User user,DBSQLhandler client) // boolean function that return true if user added else false.
	{
			client.insertToDB(new insertCommand<DBtranslation>(user)); 	
			while(!client.GetGotMessag()){//add user to DB
				try{
				Thread.sleep(50);
				}
				catch(InterruptedException ex)
				{
					System.out.println("InterruptedException "+ex);
					return false;
				}
			}
			return true;	// means the user add successful	
	}
	/**
	 * Set Status Subscription
	 * @param user
	 * the name of the table from this object
	 * @param updateCondition
	 * String of the SQL query "From"
	 * @param searchCondition
	 *  String of the SQL query "Where"
	 * @param client
	 * the client who ask the query
	 * @return
	 * @author Almog Yamin
	 */
	public static boolean SetStatusSubscription(User user , String updateCondition , String searchCondition, DBSQLhandler client) // boolean function that return true if user updated else false.
	{
			client.UpdateInDB(new updateCommand<DBtranslation>(user, searchCondition, updateCondition));
			while(!client.GetGotMessag()){//add user to DB
				try{
				Thread.sleep(50);

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
	/**
	 * Update User Status
	 * @param user
	 * the name of the table from this object
	 * @param updateCondition
	 * String of the SQL query "From"
	 * @param searchCondition
	 * String of the SQL query "Where"
	 * @param client
	 * the client who ask the quer
	 * @return
	 * true or false if the update Succeeded
	 * @author Sagi Entenberg
	 */
	public static boolean UpdateUserStatus(User user , String updateCondition , String searchCondition, DBSQLhandler client) // boolean function that return true if user updated else false.
	{
			client.UpdateInDB(new updateCommand<DBtranslation>(user, searchCondition, updateCondition));
			
			while(!client.GetGotMessag()){//add user to DB
				try{
				Thread.sleep(50);
				}
				catch(InterruptedException ex)
				{
					System.out.println("InterruptedException "+ex);
					return false;
				}
			}
			return true;	// means the user add successful	
	}
	/**
	 * Update User Info
	 * @param user
	 * the name of the table from this object
	 * @param updateCondition
	 *  String of the SQL query "From"
	 * @param searchCondition
	 * String of the SQL query "Where"
	 * @param client
	 * the client who ask the query
	 * @return
	 * true or false if the update Succeeded
	 * @author Sagi Entenberg
	 */
	public static boolean UpdateUserInfo(User user , String updateCondition , String searchCondition, DBSQLhandler client) // boolean function that return true if user updated else false.
	{
			client.UpdateInDB(new updateCommand<DBtranslation>(user, searchCondition, updateCondition));
			while(!client.GetGotMessag()){//add user to DB
				try{
				Thread.sleep(50);
				}
				catch(InterruptedException ex)
				{
					System.out.println("InterruptedException "+ex);
					return false;
				}
			}
			return true;	// means the user add successful	
	}
	
}