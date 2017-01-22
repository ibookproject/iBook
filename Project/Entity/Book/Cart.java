package Book;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.InputMismatchException;

import Extras.Validation;
import client.DBgenericObject;
import command.DBtranslation;

public class Cart extends DBtranslation {

	private String userID;
	private int bookID;
	private float price;
	private int status;
	private String buyDate;
	public static final int BOUGHT = 1;// to show the orders
	public static final int ORDERD = 0;// client didn't bought already
	public static final int DELETED = 2;// the client bought and don't want to
										// see on cart

	public Cart() {
		super();
	}

	public Cart(String userID, int bookID, float price, int status, String date) {
		this(userID, bookID, price);
		this.status = ORDERD;
		setDate(date);
	}

	public Cart(String userID, int bookID, float price) {
		super();
		setUserID(userID);
		setBookID(bookID);
		setPrice(price);
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
		if (userID == null || userID.equals("") || Validation.regularValidation(userID) == false)
			throw new InputMismatchException("you have  inserted wrong userName");
		this.userID = userID;
	}

	public void setBookID(int bookID) {
		if (bookID < 0)
			throw new InputMismatchException("you have  inserted wrong book ID");
		this.bookID = bookID;
	}

	public void setPrice(float price) {
		if (price < 0)
			throw new InputMismatchException("you have insert Negative price");
		this.price = price;
	}

	public int getStatus() {
		return status;
	}

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
			throw new InputMismatchException("incurrent status insrted");
		}
	}

	public String getDate() {
		return buyDate;
	}

	public void setDate(String d) {
		if (d == null || d.equals("") || Validation.DateValidation(d)==false)
			throw new InputMismatchException("you have  inserted wrong buy date");
		this.buyDate = d;
	}

	@Override
	public String getClassName() {
		return "Cart";
	}

	@Override
	public String getValToInsert() {
		//return String.format("(\"%s\",\"%s\",%d,%d)",reviewDate,reviewContent,reviewStatus,bookID);
		return String.format("(%s,%d,%d,%f,\"%s\")", userID, bookID, status, price,buyDate);
	}

	@Override
	public String getAttributeToInsert() {
		return "(userID,bookID,status,price,buyDate)";
	}

	public String toString() {

		return String.format("%s", bookID);
	}

	public static ArrayList<Cart> convertBack(ArrayList<DBgenericObject> arr, String fromSentence) {
		ArrayList<Cart> convertedArr = new ArrayList<Cart>();

		for (DBgenericObject ob : arr)
			convertedArr.add(convertDBObject(ob, fromSentence));// for each val in array convert back to book
		return convertedArr;

	}

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
			case "price":
				recover.setPrice((float) ob.getValtoArray(i));
				break;
			case "status":
				recover.setStatus((int) ob.getValtoArray(i));
				break;
			case "buyDate":
				Date d = (Date) ob.getValtoArray(i);
				String txtDate = new SimpleDateFormat("yyyy/MM/DD").format(d);
				recover.setDate(txtDate);
				/* recover.setDate((Date)ob.getValtoArray(i)); */
				break;

			default:
				throw new InputMismatchException("you have inserred wrong to search statment");
			}// end switch
		} // end for
		return recover;
	}

	public static Comparator<Cart> IdBookNum = new Comparator<Cart>() {

		public int compare(Cart b1, Cart b2) {

			float idNum1 = b1.getPrice();
			float idNum2 = b2.getPrice();

			/* For ascending order */
			return (int) idNum1 - (int) idNum2;

			/* For descending order */
			// rollno2-rollno1;
		}
	};

}
