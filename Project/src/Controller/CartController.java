package Controller;

import java.sql.SQLException;
import java.util.ArrayList;

import Book.Book;
import Book.Cart;
import Book.SubjectToBook;
import client.DBSQLhandler;
import client.DBgenericObject;
import command.DBtranslation;
import command.joinCommand;
import command.joinObject;
import command.searchCommand;
import command.updateCommand;

public class CartController {
	/*
	 * BuyFromCart-kfir
	 * AddToCart-kfir
	 * RemoveFromCart-kfir
	 * GetAllOrders-kfir
	 * */

	/*
	public static ArrayList<Cart> GetAllBooksToBuyByUserId(String fromSentence,Cart s,String condition,DBSQLhandler client)
	{
		
		client.searchInDB(new searchCommand<Cart>(fromSentence,s,condition));
		while(!client.GetGotMessag()){
			try{
			Thread.sleep(250);
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
	*/
	
	public static ArrayList<Cart> SearchCart(String fromSentence,Cart t,String condition,DBSQLhandler client)
	{
		// filed is need to look like "bookID,author,..."
		client.searchInDB(new searchCommand<Cart>(fromSentence,t,condition));//call command and client ask to search a book
		while(!client.GetGotMessag()){//search book in db
			try{
			Thread.sleep(250);
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
		try {
			client.joinSearchInDB(new joinCommand<Cart>(fromSentence,c,temp,condition ));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while(!client.GetGotMessag()){//search book in db
			try{
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
	


	
	public static boolean UpdateCart(Cart c, String updateCondition,
			String searchCondition, DBSQLhandler client)
	{
		client.UpdateInDB(new updateCommand<DBtranslation>(c, searchCondition,
				updateCondition));
		while (!client.GetGotMessag()) {// add user to DB
			try {
				Thread.sleep(250);
			} catch (InterruptedException ex) {
				System.out.println("InterruptedException " + ex);
				return false;
			}
		}
		return true; // means the review update successful
	}
	
	
	
	
	
}
