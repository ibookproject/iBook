package Book;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;

import Panels.Validation;
import client.DBgenericObject;
import command.DBtranslation;

public class SearchToBook extends DBtranslation{
	private int bookID;
	private String searchDate;
	private long numberOfSearches;
	
	
	public SearchToBook(int bookID, String searchDate, int numberOfSearches) {
		super();
		setBookID(bookID);
		setSearchDate(searchDate);
		setNumberOfSearches(numberOfSearches);
	}
	
	public SearchToBook()
	{
		super();
	}
	public int getBookID() {
		return bookID;
	}
	public String getSearchDate() {
		return searchDate;
	}
	public long getNumberOfSearches() {
		return numberOfSearches;
	}
	public void setBookID(int bookID) {
		if(bookID<0)
			throw new InputMismatchException("you have inserted negative book ID");
		this.bookID = bookID;
	}
	public void setSearchDate(String searchDate) {
		if (searchDate == null || searchDate.equals("") || Validation.DateValidation(searchDate, "YYYY/MM/DD") == false
				|| Validation.DateValidation(searchDate, "YYYY.MM.DD") == false)
			throw new InputMismatchException("you have  inserted wrong search date");
		this.searchDate = searchDate;
	}
	public void setNumberOfSearches(long numberOfSearches) {
		if(numberOfSearches<0)
			throw new InputMismatchException("you have inserted negativ number of searches");
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
	
	public static ArrayList<SearchToBook> convertBack(ArrayList<DBgenericObject> arr,String fromSentence) {
		 ArrayList<SearchToBook> convertedArr=new ArrayList<SearchToBook>();
		 
		for(DBgenericObject ob:arr)
				convertedArr.add(convertDBObject(ob, fromSentence));//for each val in arr this convert back to SearchToBook
		
		return convertedArr;
		
	}
	
	private static SearchToBook convertDBObject(DBgenericObject ob,String fromSentenceArray)
	{
		 SearchToBook recover=new SearchToBook();
		 String[] fromSentence=fromSentenceArray.split(",");
		 for(int i=0;i<fromSentence.length;i++)
		 {
			 switch (fromSentence[i]) {
			case "bookID":
				recover.setBookID((int)ob.getValtoArray(i));
				break;
			case "numberOfSearches":
				recover.setNumberOfSearches((long)ob.getValtoArray(i));
				break;
			case "status":
				Date d = (Date)ob.getValtoArray(i);
				String txtDate = new SimpleDateFormat("dd/MM/yyyy").format(d);
				recover.setSearchDate(txtDate);
				break;

			
			default:
				throw new InputMismatchException("you have inserred wrong to search statment");
			 }//end switch
		 }//end for
		 return recover;
	}
}
