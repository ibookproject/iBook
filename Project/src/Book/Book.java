package Book;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.InputMismatchException;

import Panels.Validation;
import client.DBgenericObject;
import command.DBtranslation;

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
	//private String cont;
	
	//empty constructor
	public Book()
	{
		super();	
	}
	public Book(int bookID)
	{
		super();	
		setBookID(bookID);
	}
	public Book(String title, String author) {
		super();
		setTitle(title);
		setAuthor(author);
	}
	public Book(String title, String language, String author, String summary, int bookEnable) {
		this(title,author);
		setLanguage(language);
		setSummary(summary);
		setBookEnable(bookEnable);
	}
	
	//Contractor to insert form DB without keyword and content
	public Book(int bookID, String title, String language, String author, String summary, int bookEnable) {
		this(title,language,author,summary,bookEnable);
		setBookID(bookID);
	}
	//Contractor to search form DB without keyword and content


	
	//Contractor to insert form DB with keyword and content
	public Book( String title, String language, String author, String summary, int bookEnable,String keyword,String content)
	{
		this(title,language,author,summary,bookEnable);
		setKeyword(keyword);
		setContent(content);
		
	}
	
	
	//Contractor to insert form DB with keyword and content and price and numberOfOrders
	public Book( String title, String language, String author, String summary, int bookEnable,String keyword,String content,float price)
	{
		this(title,language,author,summary,bookEnable,keyword,content);
		setPrice(price);
		this.numberOfOrder=0;
		
	}
	
	
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
	
	public int getBookID() {
		return bookID;
	}

	public void setBookID(int bookID) {
		if(bookID<0)
				throw new InputMismatchException("you have  inserted wrong book ID");
		this.bookID = bookID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		if (title == null || title.equals("") || Validation.TitleValidation(title,TITLESIZE) == false)
			throw new InputMismatchException("you have  inserted wrong title");
		this.title = title;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		if (language == null || language.equals("") || Validation.NameValidation(language,10) == false)
			throw new InputMismatchException("you have  inserted wrong language");
		this.language = language;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		if (author == null || author.equals("") || Validation.AuthorValidation(author,AUTHORSIZE) == false)
			throw new InputMismatchException("you have  inserted wrong author");
		this.author=author;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		if (summary == null || summary.equals("") || Validation.regularValidation(summary) == false)
			throw new InputMismatchException("you have inserted wrong summary");
		this.summary = summary;
	}
	//bookEnable for display temporary remove
	public int isBookEnable() {
		return bookEnable;
	}
	//bookEnable for display temporary remove
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

	public String[] getKeyword() {
		return keyword;
	}
	public String getKeywordString() {
		return keyword.toString();
	}
	
	//to insert into DB
	public String getKeywordToString()
	{
		String toString="";
		for(int i=0;i<keyword.length-1;i++)
			toString+=keyword[i]+" ";
		return toString+keyword[keyword.length-1];
	}
	
	public void setKeyword(String[] keyword) {//copy the array
		this.keyword = keyword;
	}
	
	public void setKeyword(String keyword) {/// splite form DB
		if(keyword==null||keyword.equals("")||keyword.equals(" "))
			this.keyword=new String[]{" "};
		else
		this.keyword=keyword.split(" ");
	}
	
	public String[] getContent() {
		return content;
	}
	
	//to insert into DB
	public String getContentToString()
	{
		String toString="";
		for(int i=0;i<content.length-1;i++)
			toString+=content[i]+" ";
		return toString+content[content.length-1];
	}

	public void setContent(String[] content) {//copy array
		this.content = content;
	}
	public void setContent(String content){//splite from DB
		if(content==null||content.equals("")||content.equals(" "))
			this.content=new String[]{" "};
		else
		this.content=content.split(" ");
	}

	public long getNumberOfOrder() {
		return numberOfOrder;
	}
	public void setNumberOfOrder(long numberOfOrder) {
		if(numberOfOrder<0)
			throw new InputMismatchException("you have insert wrong number of order");
		this.numberOfOrder = numberOfOrder;
	}


	public float getPrice() {
		return price;
	}
	
	public void setPrice(float price) {
		if(price<0)
			throw new InputMismatchException("you have inserted negative price");
		this.price = price;
	}


	
	
	
	@Override
	public String getClassName() {
		return "book";
	}
	

	@Override
	public String getValToInsert() {
		int temp=0;
		if(bookEnable==1)temp=1;
		return String.format("(\"%s\",\"%s\",\"%s\",\"%s\",%d,\"%s\",\"%s\",0,%f)",title,language,author,summary,temp,getKeywordToString(),getContentToString(),price);
	}
	@Override
	public String getAttributeToInsert() {
		return "(title,language,author,summary,bookEnable,keyword,content,numberOfOrder,price)";
	}

//convert array Which was obtained from DB to an actual book
//need to implement in all tables.!!!
	
	public static ArrayList<Book> convertBack(ArrayList<DBgenericObject> arr,String fromSentence) {
		 ArrayList<Book> convertedArr=new ArrayList<Book>();
		 
		 if(fromSentence.indexOf('*')>=0)
			 for(DBgenericObject ob:arr)
			 {
				 convertedArr.add(new Book((int)ob.getValtoArray(0),(String)ob.getValtoArray(1),(String)ob.getValtoArray(2),(String)ob.getValtoArray(3),(String)ob.getValtoArray(4),(int)ob.getValtoArray(5)));
			 }
		else
		for(DBgenericObject ob:arr)
				convertedArr.add(convertDBObject(ob, fromSentence));//for each val in arr this convert back to book
		
		return convertedArr;
		
	}
	
	//this convert specific  DBgenericObject to book according the fromSentence
	private static Book convertDBObject(DBgenericObject ob,String fromSentenceArray)
	{
		 Book recover=new Book();
		 String[] fromSentence=fromSentenceArray.split(",");
		 for(int i=0;i<fromSentence.length;i++)
		 {
			 switch (fromSentence[i]) {
			case "bookID":
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
	
	@Override
	public String toString() {
		/*return "book [bookID=" + bookID + ", title=" + title + ", language=" + language + ", author=" + author
				+ ", summary=" + summary + ", bookEnable=" + bookEnable + "]";
	*/
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


	/*Comparator for sorting the list by roll no*/
	public static Comparator<Book> IdBookNum = new Comparator<Book>() 
	{

		public int compare(Book b1, Book b2) 
		{

		   int idNum1 = b1.getBookID();
		   int idNum2 = b2.getBookID();

		   /*For ascending order*/
		   return idNum1-idNum2;

		   /*For descending order*/
		   //rollno2-rollno1;
	   }
	};

}//end Book class
