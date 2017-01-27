package Book;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;

import Extras.Validation;
import client.DBgenericObject;
import command.DBtranslation;
/**
 * This class is the entity 'SearchToBook',here saved all the fields like they presents in the DB
 * In this class there are all the getters and setters of the entity SearchToBook.
 * When we use the DB-When we insert,delete and update from there we convert the data to be like it saved
 *  in DB and in this class it all happens.
 * @author Almog Yamin
 *
 */
public class SearchToBook extends DBtranslation{
	private int bookID;
	private String searchDate;
	private long numberOfSearches;
	
	/**
	 * Empty constructor of SearchToBook
	 */
	public SearchToBook()
	{
		super();
	}
	
	/**
	 *constructor of SearchToBook with 3 parametres-bookID & searchDate & numberOfSearches
	 */
	public SearchToBook(int bookID, String searchDate, int numberOfSearches) {
		super();
		setBookID(bookID);
		setSearchDate(searchDate);
		setNumberOfSearches(numberOfSearches);
	}

	/**
	 *Getter of book ID
	 */
	public int getBookID() {
		return bookID;
	}
	
	/**
	 *Getter of search book date
	 */
	public String getSearchDate() {
		return searchDate;
	}
	
	/**
	 *Getter of number of searches of the book
	 */
	public long getNumberOfSearches() {
		return numberOfSearches;
	}
	
	/**
	 * Setter of Book ID
	 * @param bookID which is the ID we want to update/insert
	 */
	public void setBookID(int bookID) {
		if(bookID<0)
			throw new InputMismatchException("you have inserted negative book ID");
		this.bookID = bookID;
	}
	
	/**
	 * Setter of Search Date
	 * @param searchDate which is the search Date we want to update/insert
	 */
	public void setSearchDate(String searchDate) {
		if (searchDate == null || searchDate.equals("") || Validation.DateValidation(searchDate)==false)
			throw new InputMismatchException("you have  inserted wrong search date");
		this.searchDate = searchDate;
	}
	
	/**
	 * Setter of Number Of Searches
	 * @param numberOfSearches which is the number Of Searches we want to update/insert
	 */
	public void setNumberOfSearches(long numberOfSearches) {
		if(numberOfSearches<0)
			throw new InputMismatchException("you have inserted negativ number of searches");
		this.numberOfSearches = numberOfSearches;
	}
	
	/**
	 *This method overrides the getClassName method and return the name of the class like we want
	 */
	@Override
	public String getClassName() {
		return "searchtobook";
	}
	
	/**
	 *method who get values to insert to DB with the specific fields-suitable to the SearchToBook Entity!
	 *@return String with specific format of SearchToBook
	 */
	@Override
	public String getValToInsert() {
		return String.format("(%d,\"%s\",%d)",bookID,searchDate,numberOfSearches);
	}
	
	/**
	 * This method overrides the method toString() and changed the string who Represents the entity SearchToBook.
	 */
	@Override
	public String toString() {
		return "SearchToBook [bookID=" + bookID + ", searchDate=" + searchDate + ", numberOfSearches="
				+ numberOfSearches + "]";
	}
	
	
	/**
	 *method who get attributes to insert to DB with the specific fields-suitable to the SearchToBook Entity!
	 *@return string with the specific fields of the entity SearchToBook
	 */
	@Override
	public String getAttributeToInsert() {
		return "(bookID,searchDate,numberOfSearches)";
	}
	
	
	/**
	 * convert array Which was obtained from DB to an actual SearchToBook
	 * @param arr this is array of DBgenericObject which will convert to suitable to SearchToBook entity
	 * @param fromSentence This is 'Select' query we want to get back.
	 * @return  ArrayList<SearchToBook> which is the converted list the method create
	 * @author Kfir Girstein
	 */
	public static ArrayList<SearchToBook> convertBack(ArrayList<DBgenericObject> arr,String fromSentence) {
		 ArrayList<SearchToBook> convertedArr=new ArrayList<SearchToBook>();
		 
		for(DBgenericObject ob:arr)
				convertedArr.add(convertDBObject(ob, fromSentence));//for each val in arr this convert back to SearchToBook
		
		return convertedArr;
		
	}
	/**
	 * This convert specific  DBgenericObject to SearchToBook according the fromSentence
	 * @param ob This is a DBgenericObject which we need to get value from him- it is the object who return from DB
	 * @param fromSentenceArray This is 'Select' query we want to get back.
	 * @return SearchToBook This SearchToBook is ready with the parameters from DB
	 * @author Kfir Girstein
	 */
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
			case "SearchDate":
				Date d = (Date)ob.getValtoArray(i);
				String txtDate = new SimpleDateFormat("yyyy/MM/dd").format(d);
				recover.setSearchDate(txtDate);
				break;

			
			default:
				throw new InputMismatchException("you have inserred wrong to search statment");
			 }//end switch
		 }//end for
		 return recover;
	}
}