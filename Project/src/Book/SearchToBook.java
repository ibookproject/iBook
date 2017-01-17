package Book;

import java.util.Date;

import command.DBtranslation;

public class SearchToBook extends DBtranslation{
	private int bookID;
	private Date searchDate;
	private int numberOfSearches;
	
	public SearchToBook(int bookID, Date searchDate, int numberOfSearches) {
		super();
		this.bookID = bookID;
		this.searchDate = searchDate;
		this.numberOfSearches = numberOfSearches;
	}
	public int getBookID() {
		return bookID;
	}
	public Date getSearchDate() {
		return searchDate;
	}
	public int getNumberOfSearches() {
		return numberOfSearches;
	}
	public void setBookID(int bookID) {
		this.bookID = bookID;
	}
	public void setSearchDate(Date searchDate) {
		this.searchDate = searchDate;
	}
	public void setNumberOfSearches(int numberOfSearches) {
		this.numberOfSearches = numberOfSearches;
	}
	@Override
	public String getClassName() {
		return "searchtobook";
	}
	@Override
	public String getAttributeToInsert() {
		return String.format("(%d,\"%s\",%d)",bookID,searchDate,numberOfSearches);
	}
	@Override
	public String toString() {
		return "SearchToBook [bookID=" + bookID + ", searchDate=" + searchDate + ", numberOfSearches="
				+ numberOfSearches + "]";
	}
	@Override
	public String getValToInsert() {
		return "(bookID,searchDate,numberOfSearches)";
	}
}
