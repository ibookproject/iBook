package Book;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;

import Panels.Validation;
import client.DBgenericObject;
import command.DBtranslation;
public class Review extends DBtranslation {

	private int reviewID;
	private String reviewDate;
	private String reviewContent;
	private int reviewStatus;
	private int bookID;
	public static final int CONFIREMED=1;
	public static final int NOTCONFIREMED=0;
	//empty constactor
	public Review(){
		super();
	}
	
	public Review(int reviewID, String reviewDate, String reviewContent,int reviewStatus, int bookID) {
		this(reviewDate,reviewContent,reviewStatus,bookID);
		setReviewID(reviewID);
	}
	public Review( String reviewDate, String reviewContent,int reviewStatus, int bookID) {
		super();
		setReviewDate(reviewDate);
		setReviewContent(reviewContent);
		setReviewStatus(reviewStatus);
		setBookID(bookID);
	}
	public int getReviewID() {
		return reviewID;
	}
	public void setReviewID(int reviewID) {
		if(reviewID<0)
			throw new InputMismatchException("you have inserted wrong review ID");
		this.reviewID = reviewID;
	}
	public String getReviewDate() {
		return reviewDate;
	}
	public void setReviewDate(String reviewDate) {
		if (reviewDate == null || reviewDate.equals("") || Validation.DateValidation(reviewDate) == false)
			throw new InputMismatchException("you have inserted wrong review date");
		this.reviewDate=reviewDate;
	}
	public String getReviewContent() {
		return reviewContent;
	}
	public void setReviewContent(String reviewContent) {
		if (reviewContent == null || reviewContent.equals("") || Validation.regularValidation(reviewContent) == false)
			throw new InputMismatchException("you have inserted wrong Content");
		this.reviewContent = reviewContent;
	}
	public int getReviewStatus() {
		return reviewStatus;
	}
	public void setReviewStatus(int reviewStatus) {
		switch(reviewStatus){
		case CONFIREMED:
			this.reviewStatus=CONFIREMED;
			break;
		case NOTCONFIREMED:
			this.reviewStatus=NOTCONFIREMED;
			break;
		default:
		throw new InputMismatchException("you have inserted wrong review status");
		}
		this.reviewStatus = reviewStatus;
	}
	public int getBookID() {
		return bookID;
	}
	public void setBookID(int bookID) {
		if(bookID<0)
			throw new InputMismatchException("you have inserted negative book ID");
		this.bookID = bookID;
	}
	@Override
	public String getClassName() {
		
		return "review";
	}
	@Override
	public String getAttributeToInsert() {
		return "(reviewDate,reviewContent,reviewStatus,bookID)";
	}
	@Override
	public String getValToInsert() {
		return String.format("(\"%s\",\"%s\",%d,%d)",reviewDate,reviewContent,reviewStatus,bookID);
	}

	//convert array Which was obtained from DB to an actual Review
	//need to implement in all tables.!!!
		
		public static ArrayList<Review> convertBack(ArrayList<DBgenericObject> arr,String fromSentence) {
			 ArrayList<Review> convertedArr=new ArrayList<Review>();
			 
			for(DBgenericObject ob:arr)
					convertedArr.add(convertDBObject(ob, fromSentence));//for each val in arr this convert back to book
			
			return convertedArr;
			
		}
		
	//this convert specific  DBgenericObject to book according the fromSentence
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
					String txtDate = new SimpleDateFormat("yyyy/MM/DD").format(d);
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

				default:
					throw new InputMismatchException("you have inserred wrong to search statment");
				 }//end switch
			 }//end for
			 return recover;
			
		}
		@Override
		public String toString() {
			return "Review [reviewID=" + reviewID + ", reviewDate=" + reviewDate + ", reviewContent=" + reviewContent
					+ ", reviewStatus=" + reviewStatus + ", bookID=" + bookID + "]";
		}
		
}

