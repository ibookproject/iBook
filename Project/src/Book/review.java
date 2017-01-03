package Book;

import java.util.Date;

import command.DBtranslation;
public class review extends DBtranslation {

	private int reviewID;
	private Date reviewDate;
	private String reviewContent;
	private boolean reviewStatus;
	private int bookID;
	
	public review(int reviewID, Date reviewDate, String reviewContent,
			boolean reviewStatus, int bookID) {
		super();
		this.reviewID = reviewID;
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
	public Date getReviewDate() {
		return reviewDate;
	}
	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}
	public String getReviewContent() {
		return reviewContent;
	}
	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}
	public boolean isReviewStatus() {
		return reviewStatus;
	}
	public void setReviewStatus(boolean reviewStatus) {
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
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getAttributeToInsert() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getValToInsert() {
		// TODO Auto-generated method stub
		return null;
	}
}
