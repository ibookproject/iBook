package Controller;

import java.sql.SQLException;
import java.util.ArrayList;

import Book.Cart;
import Book.Subject;
import client.DBSQLhandler;
import client.DBgenericObject;
import command.searchCommand;

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
			Thread.sleep(500);
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
	
	
	
	
	
	
	
}
