package Book;

import java.sql.Date;
import java.util.InputMismatchException;

import client.DBgenericObject;

public class Cart {

	private int userID;
	private int bookID;
	private int status;
	private int price;
	private Date date;
	
	public Cart(int userID,int bookID,int status,int price,Date date)
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
