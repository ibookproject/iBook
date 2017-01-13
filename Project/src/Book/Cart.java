package Book;

import java.sql.Date;
import java.util.ArrayList;
import java.util.InputMismatchException;

import client.DBgenericObject;
import command.DBtranslation;

public class Cart extends DBtranslation {

	private String userID;
	private int bookID;
	private float price;
	protected boolean status;
	private Date buyDate;
	public Cart()
	{
		super();
	}
	public Cart(String userID,int bookID,float price,boolean status,Date date)
	{
		this.userID=userID;
		this.bookID=bookID;
		this.price=price;
		this.status=false;
		this.buyDate=date;
	}
	public Cart(String userID,int bookID,float price)
	{
		this.userID=userID;
		this.bookID=bookID;
		this.price=price;
	}

	public String getUserID() {
		return userID;
	}
	public int getBookID() {
		return bookID;
	}
	public float getPrice() {
		return price;
	}
	public void setUserID(String userID) {
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
	public Date getDate() {
		return buyDate;
	}
	public void setDate(Date d) {
		this.buyDate = d;
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
		return String.format("(%s,%d,%d,%f)",userID,bookID,temp,price);
	}
	@Override
	public String getAttributeToInsert() {
		return "(userID,bookID,status,price)";
	}

	public String toString() {
		
		return String.format("%s", bookID);
	}
	public static ArrayList<Cart> convertBack(ArrayList<DBgenericObject> arr,String fromSentence) {
		 ArrayList<Cart> convertedArr=new ArrayList<Cart>();
		 
		 if(fromSentence.indexOf('*')>=0)
			 for(DBgenericObject ob:arr)
			 {
				 convertedArr.add(new Cart((String)ob.getValtoArray(0),(int)ob.getValtoArray(1),(float)ob.getValtoArray(2),(boolean)ob.getValtoArray(3),(Date)ob.getValtoArray(4)));
			 }
		else
		for(DBgenericObject ob:arr)
				convertedArr.add(convertDBObject(ob, fromSentence));//for each val in arr this convert back to book
		
		return convertedArr;
		
	}
	private static Cart convertDBObject(DBgenericObject ob,String fromSentenceArray)
	{
		 Cart recover=new Cart();
		 String[] fromSentence=fromSentenceArray.split(",");
		 for(int i=0;i<fromSentence.length;i++)
		 {
			 switch (fromSentence[i]) {
			case "userID":
				recover.setUserID((String)ob.getValtoArray(i));
				break;
			case "bookID":
				recover.setBookID((int)ob.getValtoArray(i));
				break;
			case "price":
				recover.setPrice((float)ob.getValtoArray(i));
				break;
			case "status":
				recover.setStatus((boolean)ob.getValtoArray(i));
				break;
			case "date":
				recover.setDate((Date)ob.getValtoArray(i));
				break;
			
			default:
				throw new InputMismatchException("you have inserred wrong to search statment");
			 }//end switch
		 }//end for
		 return recover;
	}
	
	
}
