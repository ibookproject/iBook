package Book;

import java.util.InputMismatchException;

import client.DBgenericObject;

public class Cart {

	private int userID;
	private int bookID;
	private int status;
	private int price;
	private String date;
	
	public Cart(int userID,int bookID,int status,int price,String date)
	{
		this.userID=userID;
		this.bookID=bookID;
		this.status=status;
		this.price=price;
		this.date=date;
	}
	public Cart()
	{
		super();
	}
	

	public String toString() {
		
		return String.format("%s", bookID);
	}
	
}
