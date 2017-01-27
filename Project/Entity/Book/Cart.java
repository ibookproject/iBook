package Book;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.InputMismatchException;

import Extras.Validation;
import client.DBgenericObject;
import command.DBtranslation;
/**
 * This class is the entity 'Cart',here saved all the fields like they presents in the DB
 * In this class there are all the getters and setters of the entity Cart.
 * When we use the DB-When we insert,delete and update from there we convert the data to be like it saved
 *  in DB and in this class it all happens.
 * @author Hen Saada
 *
 */
public class Cart extends DBtranslation {

	private String userID;
	private int bookID;
	private int status;
	private String buyDate;
	public static final int BOUGHT = 1;// to show the orders
	public static final int ORDERD = 0;// client didn't bought already
	public static final int DELETED = 2;// the client bought and don't want to
										// see on cart
	/**
	 * Empty constructor of Cart
	 */
	public Cart() {
		super();
	}
	
	
	/**
	 *constructor of Cart with 4 parameteres - userID & bookID & status & date
	 *this ctor ignore the status whos sent to him and set the status uf cart to be ORDERD=0
	 */
	public Cart(String userID, int bookID,  int status, String date) {
		this(userID, bookID);
		this.status = ORDERD;
		setDate(date);
	}
	
	/**
	 *constructor of Cart with 2 parameteres - userID & bookID 
	 */

	public Cart(String userID, int bookID) {
		super();
		setUserID(userID);
		setBookID(bookID);
	}

	/**
	 *Getter of user ID
	 */
	public String getUserID() {
		return userID;
	}
	/**
	 *Getter of book ID
	 */
	public int getBookID() {
		return bookID;
	}
/**
 * Setter of User ID
 * @param userID Which is the user ID we want to update/insert
 */
	public void setUserID(String userID) {
		if (userID == null || userID.equals("") || Validation.regularValidation(userID) == false)
			throw new InputMismatchException("you have  inserted wrong userName");
		this.userID = userID;
	}
	/**
	 * Setter of Book ID
	 * @param bookID Which is the book ID we want to update/insert
	 */
	public void setBookID(int bookID) {
		if (bookID < 0)
			throw new InputMismatchException("you have  inserted wrong book ID");
		this.bookID = bookID;
	}
	/**
	 *Getter of cart status
	 */
	public int getStatus() {
		return status;
	}
	/**
	 * Setter of Cart status
	 * @param status Which is the cart status we want to update/insert
	 */
	public void setStatus(int status) {
		switch (status) {
		case BOUGHT:
			this.status = BOUGHT;
			break;
		case ORDERD:
			this.status = ORDERD;
			break;
		case DELETED:
			this.status = DELETED;
			break;
		default:
			throw new InputMismatchException("incurrent status inserted");
		}
	}
	
	/**
	 *Getter of cart record date
	 */
	public String getDate() {
		return buyDate;
	}
	/**
	 * Setter of Cart date
	 * @param d Which is the cart record date we want to update/insert
	 */
	public void setDate(String d) {
		if (d == null || d.equals("") || Validation.DateValidation(d)==false)
			throw new InputMismatchException("you have inserted wrong buy date");
		this.buyDate = d;
	}
	
	
	/**
	 *This method overrides the getClassName method and return the name of the method like we insert
	 */
	@Override
	public String getClassName() {
		return "Cart";
	}
	
	
	/**
	 *method who get values to insert to DB with the specific fields-suitable to the Cart Entity!
	 *@return String with specific format of cart
	 */
	@Override
	public String getValToInsert() {
		return String.format("(%s,%d,%d,\"%s\")", userID, bookID, status, buyDate);
	}

	/**
	 *method who get attributes to insert to DB with the specific fields-suitable to the Cart Entity!
	 *@return string with the specific fields of the entity Cart
	 */
	@Override
	public String getAttributeToInsert() {
		return "(userID,bookID,status,buyDate)";
	}
	/**
	 * This method overrides the method toString() and changed the string with the book ID.
	 */
	public String toString() {

		return String.format("%s", bookID);
	}
	
	/**
	 * convert array Which was obtained from DB to an actual Cart
	 * @param arr this is array of DBgenericObject which will convert to suitable to Cart entity
	 * @param fromSentence This is 'Select' query we want to get back.
	 * @return  ArrayList<Cart> which is the converted list the method create
	 * @author Kfir Girstein
	 */
	public static ArrayList<Cart> convertBack(ArrayList<DBgenericObject> arr, String fromSentence) {
		ArrayList<Cart> convertedArr = new ArrayList<Cart>();

		for (DBgenericObject ob : arr)
			convertedArr.add(convertDBObject(ob, fromSentence));// for each val in array convert back to book
		return convertedArr;

	}
	/**
	 * This convert specific  DBgenericObject to Cart according the fromSentence
	 * @param ob This is a DBgenericObject which we need to get value from him- it is the object who return from DB
	 * @param fromSentenceArray This is 'Select' query we want to get back.
	 * @return Cart This Cart is ready with the parameters from DB
	 * @author Kfir Girstein
	 */
	
	private static Cart convertDBObject(DBgenericObject ob, String fromSentenceArray) {
		Cart recover = new Cart();
		String[] fromSentence = fromSentenceArray.split(",");
		for (int i = 0; i < fromSentence.length; i++) {
			switch (fromSentence[i]) {
			case "userID":
				recover.setUserID((String) ob.getValtoArray(i));
				break;
			case "bookID":
				recover.setBookID((int) ob.getValtoArray(i));
				break;
			case "status":
				recover.setStatus((int) ob.getValtoArray(i));
				break;
			case "buyDate":
				Date d = (Date) ob.getValtoArray(i);
				String txtDate = new SimpleDateFormat("yyyy/MM/DD").format(d);
				recover.setDate(txtDate);
				break;

			default:
				throw new InputMismatchException("you have inserred wrong to search statment");
			}// end switch
		} // end for
		return recover;
	}
}
