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



	
	public static ArrayList<Cart> SearchCart(String fromSentence,Cart t,String condition,DBSQLhandler client)
	{
		// filed is need to look like "bookID,author,..."
		client.searchInDB(new searchCommand<Cart>(fromSentence,t,condition));//call command and client ask to search a book
		while(!client.GetGotMessag()){//search book in db
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
	
	
	public static ArrayList<Book> GetCartListForUser(String fromSentence,Cart c,ArrayList<joinObject> temp,String condition,DBSQLhandler client)
	{
		try 
		{
			client.joinSearchInDB(new joinCommand<Cart>(fromSentence,c,temp,condition ));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while(!client.GetGotMessag()){//search book in db
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
	 * @author Coral Carmeli
	 * @param Cart,client
	 * @return The result of adding a new cart to the list-success or not
	 * The method add a new record to the Cart list
	 */

	public static boolean AddToCart(Cart c , DBSQLhandler client)
	{
		client.insertToDB(new insertCommand<DBtranslation>(c)); 	
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

	
	public static boolean UpdateCart(Cart c, String updateCondition,
			String searchCondition, DBSQLhandler client)
	{
		client.UpdateInDB(new updateCommand<DBtranslation>(c, searchCondition,
				updateCondition));
		while (!client.GetGotMessag()) {// add user to DB
			try {
				Thread.sleep(50);
			} catch (InterruptedException ex) {
				System.out.println("InterruptedException " + ex);
				return false;
			}
		}
		return true; // means the review update successful
	}
	/**
	 * @author Coral Carmeli
	 * @param date(String), status(int),userId(String ),client
	 * @return The result of join between the 2 tables:'Book','Cart' according the same bookID and the query conditions,return the requested fields.
	 */
	public static ArrayList<DBgenericObject> searchJoinCartBook(String date,int status,String userId,DBSQLhandler client) throws SQLException
	{

		Book b=new Book();
		Cart c=new Cart();
		ArrayList<joinObject> temp =new ArrayList<joinObject>();
		
		//the first object is the assiation class and the second is to join with
		
		temp.add(new joinObject(c.getClassName(), b.getClassName(), "bookID"));
		
		client.joinSearchInDB(new joinCommand<Cart>("book.bookID,book.title,book.author,cart.buyDate",c,temp,"cart.userID=\""+userId +"\""+" && "+"cart.status=1"+" && "+"cart.buyDate>'"+date+"'"));
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
	//	return joinAnswer.convertBack((ArrayList<DBgenericObject>)client.getResultObject(),"bookID,title,author,buyDate");
		
	}
	
	public static boolean DeleteFromCart(Cart c , String searchCondition, DBSQLhandler client)
	{
		client.deleteFromDB(new deleteCommand<DBtranslation>(c, searchCondition));
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
