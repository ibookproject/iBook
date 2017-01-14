package Book;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;

import client.DBgenericObject;
import command.DBtranslation;
public class Review extends DBtranslation {

	private int reviewID;
	private String reviewDate;
	private String reviewContent;
//	private String StrreviewDate;
	private int reviewStatus;
	private int bookID;
	
	
	//empty constactor
	public Review(){
		super();
	}
	
	public Review(int reviewID, String reviewDate, String reviewContent,int reviewStatus, int bookID) {
		super();
		this.reviewID = reviewID;
		this.reviewDate = reviewDate;
		this.reviewContent = reviewContent;
		this.reviewStatus = reviewStatus;
		this.bookID = bookID;
	}
	public Review( String reviewDate, String reviewContent,int reviewStatus, int bookID) {
		super();
		this.reviewDate = reviewDate;
		this.reviewContent = reviewContent;
		this.reviewStatus = reviewStatus;
		this.bookID = bookID;
	}
	public int getReviewID() {
		return reviewID;
	}
	public void setReviewID(int reviewID) {
		this.reviewID = reviewID;
	}
	public String getReviewDate() {
		return reviewDate;
	}
	public void setReviewDate(String reviewDate) {
		this.reviewDate = reviewDate;
	}
	public String getReviewContent() {
		return reviewContent;
	}
	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}
	public int isReviewStatus() {
		return reviewStatus;
	}
	public void setReviewStatus(int reviewStatus) {
		this.reviewStatus = reviewStatus;
	}
	public int getBookID() {
		return bookID;
	}
	public void setBookID(int bookID) {
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
					recover.setReviewDate((String)ob.getValtoArray(i));
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

