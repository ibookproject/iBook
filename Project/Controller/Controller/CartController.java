package Controller;

import java.sql.SQLException;
import java.util.ArrayList;

import Book.Book;
import Book.Cart;
import Book.SubjectToBook;
import client.DBSQLhandler;
import client.DBgenericObject;
import command.DBtranslation;
import command.deleteCommand;
import command.insertCommand;
import command.joinCommand;
import command.joinObject;
import command.searchCommand;
import command.updateCommand;

public class CartController {

	/** 
	 * @param fromSentence
	 * string for the query sql " FROM"
	 * @param cart
	 * name of the table that get from this object 
	 * @param whereSentence
	 * string for the query sql "WHERE"
	 * @param client
	 * the current client that ask the query
	 * @return
	 *  The result  Array list of Cart by userId and bookid
	 * @author coral carmeli
	 */
	public static ArrayList<Cart> SearchCart(String fromSentence,Cart cart,String condition,DBSQLhandler client)
	{
		client.searchInDB(new searchCommand<Cart>(fromSentence,cart,condition));//call command and client ask to search a book
		while(!client.GetGotMessag()){
			try{
			Thread.sleep(50);
			}
			catch(InterruptedException ex)
			{
				System.out.println("InterruptedException "+ex);
			}
		}
		try {			
			return  Cart.convertBack((ArrayList<DBgenericObject>) client.getResultObject(), fromSentence);
		} catch (SQLException e) {
			return null;
		}
	}
	/** 
	 * @param fromSentence
	 * string for the query sql " FROM"
	 * @param cart
	 * name of the table that get from this object 
	 * @param temp
	 * variable that include Common Fields of cart and book after join query
	 * @param client
	 * the current client that ask the query
	 * @return
	 *  The result  Array list from type temp of the JOIN QUERY between Cart and book according to the specific user id
	 * @author hen saada 
	 */
	public static ArrayList<Book> GetCartListForUser(String fromSentence,Cart cart,ArrayList<joinObject> temp,String condition,DBSQLhandler client)
	{
		try 
		{
			client.joinSearchInDB(new joinCommand<Cart>(fromSentence,cart,temp,condition ));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while(!client.GetGotMessag()){
			try
			{
				Thread.sleep(500);
			}
			catch(InterruptedException ex)
			{
				System.out.println("InterruptedException "+ex);
			}
		}
		try
		{
			return Book.convertBack((ArrayList<DBgenericObject>) client.getResultObject(),"bookID,title,author,price");
		}
		catch (SQLException e) 
		{
			return null;
		}
	
		}
	
	/** 
	 * @param cart
	 * name of the table that get from this object 
	 * @param client
	 * the current client that ask the query
	 * @return
	 *  The result of adding a new cart to the list-success or not
	 * @author coral carmeli
	 */

	public static boolean AddToCart(Cart cart , DBSQLhandler client)
	{
		client.insertToDB(new insertCommand<DBtranslation>(cart)); 	
		while(!client.GetGotMessag()){//add book to DB
			try{
			Thread.sleep(50);
			}
			catch(InterruptedException ex)
			{
				System.out.println("InterruptedException "+ex);
				return false;
			}
		}
		return true;	// means the cart add successful	
	}

	
	/** 
	 * @param cart
	 * name of the table that get from this object 
	 * @param client
	 * the current client that ask the query
	 * @param
	 * the update Condition - means search what row to update by this condition
	 * @return
	 *  true if the update was success else false
	 * @author hen saada
	 */
	public static boolean UpdateCart(Cart cart, String updateCondition,String searchCondition, DBSQLhandler client)
	{
		client.UpdateInDB(new updateCommand<DBtranslation>(cart, searchCondition,
				updateCondition));
		while (!client.GetGotMessag()) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException ex) {
				System.out.println("InterruptedException " + ex);
				return false;
			}
		}
		return true; 
	}

	
	
	/**
	 * @author Coral Carmeli
	 * @param date(String), status(int),userId(String ),client
	 * @return The result of join between the 2 tables:'Book','Cart' according the same bookID and the query conditions,return the requested fields.
	 */

	
	
	/** 
	 * @param date
	 * string for the query sql " FROM"
	 * @param status
	 * 
	 * @param userId
	 * 
	 * @param client
	 * the current client that ask the query
	 * @return
	 *  The result  Array list from type temp of the JOIN QUERY between Cart and book according to the specific user id
	 * @author coral carmeli
	 */
	public static ArrayList<DBgenericObject> searchJoinCartBook(String date,int status,String userId,DBSQLhandler client) throws SQLException
	{

		Book book=new Book();
		Cart cart=new Cart();
		ArrayList<joinObject> temp =new ArrayList<joinObject>();
				
		temp.add(new joinObject(cart.getClassName(), book.getClassName(), "bookID"));
		
		client.joinSearchInDB(new joinCommand<Cart>("book.bookID,book.title,book.author,cart.buyDate",cart,temp,"cart.userID=\""+userId +"\""+" && "+"cart.status=1"+" && "+"cart.buyDate>'"+date+"'"));
		while(!	client.GetGotMessag()){//search book in db
			try{
			Thread.sleep(500);
			}
			catch(InterruptedException ex)
			{
				System.out.println("InterruptedException "+ex);
			}
		}
		return (ArrayList<DBgenericObject>)client.getResultObject();		
	}
	
	
	/** 
	 * @param cart
	 * name of the table that get from this object 
	 * @param
	 * the Condition that we search by him.
	 * @param client
	 * the current client that ask the query
	 * @param
	 * the update Condition - means search what row to update by this condition
	 * @return
	 *  true if the update was success else false
	 * @author hen saada
	 */
	public static boolean DeleteFromCart(Cart cart , String searchCondition, DBSQLhandler client)
	{
		client.deleteFromDB(new deleteCommand<DBtranslation>(cart, searchCondition));
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
		return true;
	}
	
}
