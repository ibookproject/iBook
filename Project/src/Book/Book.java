package Book;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.InputMismatchException;

import client.DBgenericObject;
import command.DBtranslation;

public class Book extends DBtranslation  {

	private int bookID;
	private String title;
	private String language;
	private String author;
	private String summary;
	private boolean bookEnable;
	private String[] keyword;
	private String[] content;
	//private String cont;
	
	//empty constructor
	public Book()
	{
		super();	
	}
	public Book(int bookID)
	{
		super();	
		this.bookID=bookID;
	}
	
	//Contractor to insert form DB without keyword and content
	public Book(int bookID, String title, String language, String author, String summary, boolean bookEnable) {
		super();
		this.bookID = bookID;
		this.title = title;
		this.language = language;
		this.author = author;
		this.summary = summary;
		this.bookEnable = bookEnable; //bookEnable for display temporary remove
	}
	//Contractor to search form DB without keyword and content
	public Book(String title, String language, String author, String summary, boolean bookEnable) {
		super();
		this.title = title;
		this.language = language;
		this.author = author;
		this.summary = summary;
		this.bookEnable = bookEnable;
	}
	
	public Book(String title, String author) {
		super();
		this.title = title;
		this.author = author;
	}
	
	//Contractor to insert form DB with keyword and content
	public Book( String title, String language, String author, String summary, boolean bookEnable,String keyword,String content)
	{
		this(title,language,author,summary,bookEnable);
		this.keyword=keyword.split(" ");
		this.content=content.split(" ");
		
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
	}
	
	public int getBookID() {
		return bookID;
	}

	public void setBookID(int bookID) {
		this.bookID = bookID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}
	//bookEnable for display temporary remove
	public boolean isBookEnable() {
		return bookEnable;
	}
	//bookEnable for display temporary remove
	public void setBookEnable(boolean bookEnable) {
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
		this.content=content.split(" ");
	}
	
	@Override
	public String getClassName() {
		return "book";
	}
	

	@Override
	public String getValToInsert() {
		int temp=0;
		if(bookEnable)temp=1;
		return String.format("(\"%s\",\"%s\",\"%s\",\"%s\",%d,\"%s\",\"%s\")",title,language,author,summary,temp,getKeywordToString(),getContentToString());
	}
	@Override
	public String getAttributeToInsert() {
		return "(title,language,author,summary,bookEnable,keyword,content)";
	}

//convert array Which was obtained from DB to an actual book
//need to implement in all tables.!!!
	
	public static ArrayList<Book> convertBack(ArrayList<DBgenericObject> arr,String fromSentence) {
		 ArrayList<Book> convertedArr=new ArrayList<Book>();
		 
		 if(fromSentence.indexOf('*')>=0)
			 for(DBgenericObject ob:arr)
			 {
				 convertedArr.add(new Book((int)ob.getValtoArray(0),(String)ob.getValtoArray(1),(String)ob.getValtoArray(2),(String)ob.getValtoArray(3),(String)ob.getValtoArray(4),((int)ob.getValtoArray(5))==1));
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
			default:
				throw new InputMismatchException("you have inserred wrong to search statment");
			 }//end switch
		 }//end for
		 return recover;
	}
	
	@Override
	public String toString() {
		/*return "book [bookID=" + bookID + ", title=" + title + ", language=" + language + ", author=" + author
				+ ", summary=" + summary + ", bookEnable=" + bookEnable + "]";
	*/
	String temp=String.format("%d",bookID);
	if(title!=null)
		temp+=", "+title;
	if(language!=null)
		temp+=", "+language;
	if(author!=null)
		temp+=", "+author;
	if(summary!=null)
		temp+=", "+summary;
	if(bookEnable==true)
		temp+=", yes";
	else
		temp+=", no";
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
