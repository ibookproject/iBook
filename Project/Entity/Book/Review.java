package Book;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import Extras.Validation;
import client.DBgenericObject;
import command.DBtranslation;
/**
 * This class is the entity 'Review',here saved all the fields like they presents in the DB
 * In this class there are all the getters and setters of the entity Review.
 * When we use the DB-When we insert,delete and update from there we convert the data to be like it saved
 *  in DB and in this class it all happens.
 * @author Coral Carmeli
 * @author Sagi Entenberg
 *
 */
public class Review extends DBtranslation {

	private int reviewID;
	private String reviewDate;
	private String reviewContent;
	private int reviewStatus;
	private int bookID;
	private String UserSign;
	public static final int CONFIREMED=1;
	public static final int NOTCONFIREMED=0;
	public static final int NOTIFICATION=-1;
	
	/**
	 * Empty constructor of Review
	 */
	public Review(){
		super();
	}
	
	
	/**
	 *constructor of Review with 5 parameteres - reviewID & reviewDate & reviewContent & reviewStatus & bookID
	 */
	public Review(int reviewID, String reviewDate, String reviewContent,int reviewStatus, int bookID) {
		this(reviewDate,reviewContent,reviewStatus,bookID);
		setReviewID(reviewID);
	}
	
	/**
	 *constructor of Review with 4 parameteres - reviewDate & reviewContent & reviewStatus & bookID
	 */
	public Review( String reviewDate, String reviewContent,int reviewStatus, int bookID) {
		super();
		setReviewDate(reviewDate);
		setReviewContent(reviewContent);
		setReviewStatus(reviewStatus);
		setBookID(bookID);
	}
	
	/**
	 *constructor of Review with 3 parameteres - userSign & reviewContent & bookID
	 *this ctor set the status of the review to NOTIFICATION=-1
	 */
	public Review( String reviewContent, int bookID,String userSign) {
		super();
		Calendar time = Calendar.getInstance();
        String timeRightNow = String.format("%1$tY/%1$tm/%1$td", time);
		setReviewDate(timeRightNow);
		setReviewContent(reviewContent);
		setReviewStatus(NOTIFICATION);
		setBookID(bookID);
		setUserSign(userSign);
        
	}
	
	/**
	 *Getter of Review ID
	 */
	public int getReviewID() {
		return reviewID;
	}
	
	
	/**
	 * Setter of the Review ID
	 * @param reviewID Which is the review ID we want to update/insert
	 */
	public void setReviewID(int reviewID) {
		if(reviewID<0)
			throw new InputMismatchException("you have inserted wrong review ID");
		this.reviewID = reviewID;
	}
	
	/**
	 *Getter of Review date
	 */
	public String getReviewDate() {
		return reviewDate;
	}
	
	/**
	 * Setter of the Review Date
	 * @param reviewDate Which is the review Date we want to update/insert
	 */
	public void setReviewDate(String reviewDate) {
		if (reviewDate == null || reviewDate.equals("") || Validation.DateValidation(reviewDate) == false)
			throw new InputMismatchException("you have inserted wrong review date");
		this.reviewDate=reviewDate;
	}
	
	/**
	 *Getter of Review Content
	 */
	public String getReviewContent() {
		return reviewContent;
	}
	
	/**
	 * Setter of the Review Content 
	 * @param reviewContent Which is the review Content we want to update/insert
	 */
	public void setReviewContent(String reviewContent) {
		if (reviewContent == null || reviewContent.equals("") || Validation.regularValidation(reviewContent) == false)
			throw new InputMismatchException("you have inserted wrong Content");
		this.reviewContent = reviewContent;
	}
	
	/**
	 *Getter of Review Status
	 */
	public int getReviewStatus() {
		return reviewStatus;
	}
	
	/**
	 * Setter of the Review Status 
	 * @param reviewStatus Which is the review   Status we want to update/insert
	 */
	public void setReviewStatus(int reviewStatus) {
		switch(reviewStatus){
		case CONFIREMED:
			this.reviewStatus=CONFIREMED;
			break;
		case NOTCONFIREMED:
			this.reviewStatus=NOTCONFIREMED;
			break;
		case NOTIFICATION:
			this.reviewStatus=NOTIFICATION;
			break;
		default:
		throw new InputMismatchException("you have inserted wrong review status");
		}
		this.reviewStatus = reviewStatus;
	}
	
	
	/**
	 *Getter of book ID
	 */
	public int getBookID() {
		return bookID;
	}
	
	
	/**
	 * Setter of Book ID
	 * @param bookID which is the book ID we want to update/insert
	 */
	public void setBookID(int bookID) {
		if(bookID<0)
			throw new InputMismatchException("you have inserted negative book ID");
		this.bookID = bookID;
	}
	
	
	/**
	 *This method overrides the getClassName method and return the name of the class like we want
	 */
	@Override
	public String getClassName() {
		
		return "review";
	}
	
	/**
	 *method who get attributes to insert to DB with the specific fields-suitable to the Review Entity!
	 *@return string with the specific fields of the entity Review
	 */
	@Override
	public String getAttributeToInsert() {
		return "(reviewDate,reviewContent,reviewStatus,bookID,userSign)";
	}
	
	
	/**
	 *method who get values to insert to DB with the specific fields-suitable to the Review Entity!
	 *@return String with specific format of Review
	 */
	@Override
	public String getValToInsert() {
		return String.format("(\"%s\",\"%s\",%d,%d,\"%s\")",reviewDate,reviewContent,reviewStatus,bookID,UserSign);
	}

	/**
	 * convert array Which was obtained from DB to an actual Review
	 * @param arr this is array of DBgenericObject which will convert to suitable to Review entity
	 * @param fromSentence This is 'Select' query we want to get back.
	 * @return  ArrayList<Review> which is the converted list the method create
	 * @author Kfir Girstein
	 */
		public static ArrayList<Review> convertBack(ArrayList<DBgenericObject> arr,String fromSentence) {
			 ArrayList<Review> convertedArr=new ArrayList<Review>();
			 
			for(DBgenericObject ob:arr)
					convertedArr.add(convertDBObject(ob, fromSentence));//for each val in arr this convert back to book
			
			return convertedArr;
			
		}
		
		/**
		 * This convert specific  DBgenericObject to Review according the fromSentence
		 * @param ob This is a DBgenericObject which we need to get value from him- it is the object who return from DB
		 * @param fromSentenceArray This is 'Select' query we want to get back.
		 * @return Review This Review is ready with the parameters from DB
		 * @author Kfir Girstein
		 */
		private static Review convertDBObject(DBgenericObject ob,String fromSentenceArray)
		{
			Review recover=new Review();
			 String[] fromSentence=fromSentenceArray.split(",");
			 for(int i=0;i<fromSentence.length;i++)
			 {
				 switch (fromSentence[i]) {
				case "reviewID":
					recover.setReviewID((int)ob.getValtoArray(i));
					break;
				case "reviewDate":
					Date d = (Date)ob.getValtoArray(i);
					String txtDate = new SimpleDateFormat("yyyy/MM/dd").format(d);
					recover.setReviewDate(txtDate);
					break;
				case "reviewContent":
					recover.setReviewContent((String)ob.getValtoArray(i));
					break;
				case "reviewStatus":
					recover.setReviewStatus((int)ob.getValtoArray(i));
					break;
				case "bookID":
					recover.setBookID((int)ob.getValtoArray(i));
					break;
				case "userSign":
					recover.setUserSign((String)ob.getValtoArray(i));
					break;

				default:
					throw new InputMismatchException("you have inserred wrong to search statment");
				 }//end switch
			 }//end for
			 return recover;
			
		}
		
		/**
		 * This method overrides the method toString() and changed the string who Represents the entity Review.
		 */
		@Override
		public String toString() {
			return "Review [reviewID=" + reviewID + ", reviewDate=" + reviewDate + ", reviewContent=" + reviewContent
					+ ", reviewStatus=" + reviewStatus + ", bookID=" + bookID +",userSign="+ UserSign+"]";
		}

		/**
		 * Getter of User Sign
		 * @return userSign which is the user sign
		 */
		public String getUserSign() {
			
			return UserSign;
		}
		/**
		 * Setter of User Sign
		 * @param userSign which is the user Sign we want to update/insert
		 */
		public void setUserSign(String userSign) {
			if (userSign == null || userSign.equals("") || Validation.regularValidation(userSign) == false)
				throw new InputMismatchException("you have inserted wrong Content");
			this.UserSign = userSign;
		}
		
		
		
}

