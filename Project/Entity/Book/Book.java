package Book;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.InputMismatchException;

import Extras.Validation;
import client.DBgenericObject;
import command.DBtranslation;
/**
 * This class is the entity 'Book',here saved all the fields like they presents in the DB
 * In this class there are all the getters and setters of the entity Book.
 * When we use the DB-When we insert,delete and update from there we convert the data to be like it saved
 *  in DB and in this class it all happens.
 * @author Coral Carmeli
 *
 */
public class Book extends DBtranslation  {

	private int bookID;
	private String title;
	private String language;
	private String author;
	private String summary;
	private int bookEnable;
	private String[] keyword;
	private String[] content;
	private long numberOfOrder;
	private float price;
	public static final int ENABLE=1;
	public static final int DISEABLE=0;
	public static final int TITLESIZE=50;
	public static final int AUTHORSIZE=30;
	/**
	 * Empty constructor of Book
	 */
	public Book()
	{
		super();	
	}
	
	/**
	 * constructor of Book with one parametr
	 * @param bookID
	 */
	public Book(int bookID)
	{
		super();	
		setBookID(bookID);
	}
	
	/**
	 * constructor of Book with 2 parametres-title , author
	 * @param title
	 * @param author
	 */
	public Book(String title, String author) {
		super();
		setTitle(title);
		setAuthor(author);
	}
	
	
	/**
	 * 
	 *constructor of Book with 5 parametres-title , author , language  , summary , bookEnable
	 * @param title
	 * @param language
	 * @param author
	 * @param summary
	 * @param bookEnable
	 */
	public Book(String title, String language, String author, String summary, int bookEnable) {
		this(title,author);
		setLanguage(language);
		setSummary(summary);
		setBookEnable(bookEnable);
	}
	
	/**
	 * constructor of Book with 6 parametres- book ID ,title ,author , language  , summary , bookEnable
	 *This constructor is for the insert to DB/take from DB without keyword and content
	 * @param bookID
	 * @param title
	 * @param language
	 * @param author
	 * @param summary
	 * @param bookEnable
	 */
	public Book(int bookID, String title, String language, String author, String summary, int bookEnable) {
		this(title,language,author,summary,bookEnable);
		setBookID(bookID);
	}

/**
 * constructor of Book with 7 parametres- keyword ,title ,author , language  , summary , bookEnable,content
 *This constructor is for the insert to DB/take from DB without keyword and content
 * @param title
 * @param language
 * @param author
 * @param summary
 * @param bookEnable
 * @param keyword
 * @param content
 */
	public Book( String title, String language, String author, String summary, int bookEnable,String keyword,String content)
	{
		this(title,language,author,summary,bookEnable);
		setKeyword(keyword);
		setContent(content);
		
	}
	
	/**
	 * constructor of Book with 8 parametres- keyword , content , title , author, language  , summary , bookEnable ,price
	 *This constructor is for take from DB/insert to DB with keyword and content and price and numberOfOrders
	 * @param title
	 * @param language
	 * @param author
	 * @param summary
	 * @param bookEnable
	 * @param keyword
	 * @param content
	 * @param price
	 */
	public Book( String title, String language, String author, String summary, int bookEnable,String keyword,String content,float price)
	{
		this(title,language,author,summary,bookEnable,keyword,content);
		setPrice(price);
		this.numberOfOrder=0;
		
	}
	
	/**
	 * constructor of Book with 8 parametres- keyword , content , title ,author , language  ,summary , bookEnable , price
	 * This constructor is for take from DB/insert to DB with keyword and content and price and numberOfOrders with default enable=1
	 * @param title 
	 * @param language
	 * @param author
	 * @param summary
	 * @param keyword
	 * @param content
	 * @param price
	 */
	public Book(String title, String language, String author, String summary,String keyword,String content,float price)
	{
		this(title,language,author,summary,ENABLE,keyword,content,price);
		this.numberOfOrder=0;
	}
	
	/**
	 * constructor of Book with one parametr-book
	 * initialize all the field of the entity book in the code
	 * @param b
	 */
	public Book(Book b)
	{
		this.bookID = b.getBookID();
		this.title = b.getTitle();
		this.language = b.getLanguage();
		this.author = b.getAuthor();
		this.summary = b.getSummary();
		this.bookEnable = b.isBookEnable();
		this.keyword=b.getKeyword();
		this.content=b.getContent();
		this.price=b.getPrice();
		this.numberOfOrder=b.getNumberOfOrder();
	}
	/**
	 *Getter of book ID
	 */
	public int getBookID() {
		return bookID;
	}
	/**
	 * Setter of Book ID
	 * @param bookID which is the ID we want to update/insert
	 */
	public void setBookID(int bookID) {
		if(bookID<0)
				throw new InputMismatchException("you have  inserted wrong book ID");
		this.bookID = bookID;
	}
	/**
	 *Getter of book title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * Setter of book title
	 * @param title which is the title we want to update/insert
	 */
	public void setTitle(String title) {
		if (title == null || title.equals("") || Validation.TitleValidation(title,TITLESIZE) == false)
			throw new InputMismatchException("you have  inserted wrong title");
		this.title = title;
	}
	/**
	 *Getter of book language
	 */
	public String getLanguage() {
		return language;
	}
	/**
	 * Setter of book Language
	 * @param language which is the language we want to update/insert
	 */
	public void setLanguage(String language) {
		if (language == null || language.equals("") || Validation.NameValidation(language,10) == false)
			throw new InputMismatchException("you have  inserted wrong language");
		this.language = language;
	}
	/**
	 *Getter of book Author
	 */
	public String getAuthor() {
		return author;
	}
	/**
	 * Setter of book Author
	 * @param author which is the author we want to update/insert
	 */
	public void setAuthor(String author) {
		if (author == null || author.equals("") || Validation.AuthorValidation(author,AUTHORSIZE) == false)
			throw new InputMismatchException("you have  inserted wrong author");
		this.author=author;
	}
	/**
	 *Getter of book Summary
	 */
	public String getSummary() {
		return summary;
	}
	/**
	 * Setter of book Summary
	 * @param summary which is the summary we want to update/insert
	 */
	public void setSummary(String summary) {
		if (summary == null || summary.equals("") || Validation.regularValidation(summary) == false)
			throw new InputMismatchException("you have inserted wrong summary");
		this.summary = summary;
	}
	
	/**
	 * bookEnable for display temporary remove
	 * @return enable which is integer-it is the status of the book
	 */
	public int isBookEnable() {
		return bookEnable;
	}
	
	/**
	 * bookEnable for display temporary remove
	 * @param bookEnable which is the status of the book we want to update/insert
	 */
	public void setBookEnable(int bookEnable) {
		switch(bookEnable){
		case DISEABLE:
			this.bookEnable=DISEABLE;
			break;
		case ENABLE:
			this.bookEnable=ENABLE;
			break;
		default:
			throw new InputMismatchException("you have insert wrong book status");
		}
		this.bookEnable = bookEnable;
	}
	/**
	 *Getter of book Keyword
	 */
	public String[] getKeyword() {
		return keyword;
	}
	/**
	 *Getter of book Keyword
	 */
	public String getKeywordString() {
		return keyword.toString();
	}
	
	/**
	 *Getter of Keyword-to insert to DB
	 */
	public String getKeywordToString()
	{
		String toString="";
		for(int i=0;i<keyword.length-1;i++)
			toString+=keyword[i]+" ";
		return toString+keyword[keyword.length-1];
	}
	/**
	 * Setter of book Keyword
	 * @param keyword which is the keyword we want to update/insert
	 */
	public void setKeyword(String[] keyword) {//copy the array
		this.keyword = keyword;
	}
	/**
	 * Setter of book Keyword
	 * @param keyword which is the keyword we want to update/insert
	 */
	public void setKeyword(String keyword) {/// splite form DB
		if(keyword==null||keyword.equals("")||keyword.equals(" "))
			this.keyword=new String[]{" "};
		else
		this.keyword=keyword.split(" ");
	}
	/**
	 *Getter of book content
	 *@return string[] which is the content in array of strings
	 */
	public String[] getContent() {
		return content;
	}
	
	/**
	 *Getter of book contnet
	 */
	public String getContentToString()
	{
		String toString="";
		for(int i=0;i<content.length-1;i++)
			toString+=content[i]+" ";
		return toString+content[content.length-1];
	}
	/**
	 * Setter of book Content
	 * @param content which is the content we want to update/insert
	 */
	public void setContent(String[] content) {//copy array
		this.content = content;
	}
	/**
	 * Setter of book Content
	 * @param content which is the content we want to update/insert
	 */
	public void setContent(String content){//splite from DB
		if(content==null||content.equals("")||content.equals(" "))
			this.content=new String[]{" "};
		else
		this.content=content.split(" ");
	}
	/**
	 *Getter of book number of orders
	 */
	public long getNumberOfOrder() {
		return numberOfOrder;
	}
	
	/**
	 * Setter of book Number Of Order
	 * @param numberOfOrder which is the number Of Order we want to update/insert
	 */
	public void setNumberOfOrder(long numberOfOrder) {
		if(numberOfOrder<0)
			throw new InputMismatchException("you have insert wrong number of order");
		this.numberOfOrder = numberOfOrder;
	}

	/**
	 *Getter of book price
	 */
	public float getPrice() {
		return price;
	}
	/**
	 * Setter of book Price
	 * @param price which is the book price we want to update/insert
	 */
	public void setPrice(float price) {
		if(price<0)
			throw new InputMismatchException("you have inserted negative price");
		this.price = price;
	}

	/**
	 *Getter of book class name
	 */
	@Override
	public String getClassName() {
		return "book";
	}
	
	/**
	 *method who get values to insert to DB with the specific fields-suitable to the book Entity!
	 *@return String with specific format of book
	 */
	@Override
	public String getValToInsert() {
		int temp=0;
		if(bookEnable==1)temp=1;
		return String.format("(\"%s\",\"%s\",\"%s\",\"%s\",%d,\"%s\",\"%s\",0,%f)",title,language,author,summary,temp,getKeywordToString(),getContentToString(),price);
	}
	/**
	 *method who get attributes to insert to DB with the specific fields-suitable to the book Entity!
	 *@return string with the specific fields of the entity Book
	 */
	@Override
	public String getAttributeToInsert() {
		return "(title,language,author,summary,bookEnable,keyword,content,numberOfOrder,price)";
	}

	/**
	 * convert array Which was obtained from DB to an actual book
	 * @param arr this is array of DBgenericObject which will convert to suitable to Book entity
	 * @param fromSentence This is 'Select' query we want to get back.
	 * @return  ArrayList<Book> which is the converted list the method create
	 * @author Kfir Girstein
	 */
	public static ArrayList<Book> convertBack(ArrayList<DBgenericObject> arr,String fromSentence) {
		 ArrayList<Book> convertedArr=new ArrayList<Book>();
		 
		 if(fromSentence.indexOf('*')>=0)
			 for(DBgenericObject ob:arr)
			 {
				 Book temp=new Book((int)ob.getValtoArray(0),(String)ob.getValtoArray(1),(String)ob.getValtoArray(2),(String)ob.getValtoArray(3),(String)ob.getValtoArray(4),(int)ob.getValtoArray(5));
				temp.setKeyword((String)ob.getValtoArray(6));
				temp.setContent((String)ob.getValtoArray(7));
				temp.setPrice((float)ob.getValtoArray(8));
				temp.setNumberOfOrder((long)ob.getValtoArray(9));
				 convertedArr.add(temp);
				 
			 }
		else
		for(DBgenericObject ob:arr)
				convertedArr.add(convertDBObject(ob, fromSentence));//for each val in arr this convert back to book
		
		return convertedArr;
		
	}
	/**
	 * This convert specific  DBgenericObject to book according the fromSentence
	 * @param ob This is a DBgenericObject which we need to get value from him- it is the object who return from DB
	 * @param fromSentenceArray This is 'Select' query we want to get back.
	 * @return Book This book is ready with the parameters from DB
	 * @author Kfir Girstein
	 */
	
	private static Book convertDBObject(DBgenericObject ob,String fromSentenceArray)
	{
		 Book recover=new Book();
		 String[] fromSentence=fromSentenceArray.split(",");
		 for(int i=0;i<fromSentence.length;i++)
		 {
			 switch (fromSentence[i]) {
			case "bookID": case "MAX(bookID)":
				recover.setBookID((int)ob.getValtoArray(i));
				break;
			case "title":
				recover.setTitle((String)ob.getValtoArray(i));
				break;
			case "language":
				recover.setLanguage((String)ob.getValtoArray(i));
				break;
			case "author":
				recover.setAuthor((String)ob.getValtoArray(i));
				break;
			case "summary":
				recover.setSummary((String)ob.getValtoArray(i));
				break;
			case "keyword":
				recover.setKeyword((String)ob.getValtoArray(i));
				break;
			case "content":
				recover.setContent((String)ob.getValtoArray(i));
				break;
			case "price":
				recover.setPrice((float)ob.getValtoArray(i));
				break;
			case "bookEnable":
				recover.setBookEnable((int)ob.getValtoArray(i));
				break;
			case "numberOfOrder":
				recover.setNumberOfOrder((long)ob.getValtoArray(i));
			
				
			default:
				//throw new InputMismatchException("you have inserred wrong to search statment");
			 }//end switch
		 }//end for
		 return recover;
	}
	/**
	 * This method overrides the method toString() and changed the string who Represents the entity Book.
	 */
	@Override
	public String toString() {
	
	String temp="Book ID  : ";
	temp += String.format("%d",bookID);
	if(title!=null)
		temp+=",  Title: "+title;
	if(author!=null)
		temp+=",  Author: "+author;
	if(language!=null)
		temp+=",  Language: "+language;
	/*if(summary!=null)
		temp+=", "+summary;*/
	if(bookEnable==1)
		temp+=", book Enable";
	else
		temp+=", book disable";
	return temp;
	}//rnd toString


	/*Comparator for sorting the list by bookID*/
	public static Comparator<Book> IdBookNum = new Comparator<Book>() 
	{

		public int compare(Book b1, Book b2) 
		{

		   int idNum1 = b1.getBookID();
		   int idNum2 = b2.getBookID();

		   /*For ascending order*/
		   return idNum1-idNum2;
	   }
	};

}//end Book class
