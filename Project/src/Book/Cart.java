package Book;

import java.util.InputMismatchException;

import client.DBgenericObject;
import command.DBtranslation;

public class Cart extends DBtranslation {

	private int userID;
	private int bookID;
	private float price;
	protected boolean status;
	public Cart()
	{
		super();
	}
	public Cart(int userID,int bookID,int price)
	{
		this.userID=userID;
		this.bookID=bookID;
		this.price=price;
		this.status=false;
	}

	public int getUserID() {
		return userID;
	}
	public int getBookID() {
		return bookID;
	}
	public float getPrice() {
		return price;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public void setBookID(int bookID) {
		this.bookID = bookID;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	@Override
	public String getClassName() {
		return "Cart";
	}
	

	@Override
	public String getValToInsert() {
		int temp=0;
		if(status)
		temp=1;
		return String.format("(%d,%d,%d,%f)",userID,bookID,temp,price);
	}
	@Override
	public String getAttributeToInsert() {
		return "(userID,bookID,status,price)";
	}

	public String toString() {
		
		return String.format("%s", bookID);
	}
	
}
