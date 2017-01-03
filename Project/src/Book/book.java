package Book;

import java.util.ArrayList;

import client.DBgenericObject;
import command.DBtranslation;

public class book extends DBtranslation {

	private int bookID;
	private String title;
	private String language;
	private String author;
	private String summary;
	private boolean bookEnable;

	public book(int bookID, String title, String language, String author, String summary, boolean bookEnable) {
		super();
		this.bookID = bookID;
		this.title = title;
		this.language = language;
		this.author = author;
		this.summary = summary;
		this.bookEnable = bookEnable; //bookEnable for display temporary remove
	}
	public book(String title, String language, String author, String summary, boolean bookEnable) {
		super();
		this.title = title;
		this.language = language;
		this.author = author;
		this.summary = summary;
		this.bookEnable = bookEnable;
	}
	public book(book b)
	{
		this.bookID = b.bookID;
		this.title = b.title;
		this.language = b.language;
		this.author = b.author;
		this.summary = b.summary;
		this.bookEnable = b.bookEnable;
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
	@Override
	public String getClassName() {
		return "book";
	}
	

	@Override
	public String getValToInsert() {
		int temp=0;
		if(bookEnable)temp=1;
		return String.format("(\"%s\",\"%s\",\"%s\",\"%s\",%d)",title,language,author,summary,temp);
	}
	@Override
	public String getAttributeToInsert() {
		return "(title,language,author,summary,bookEnable)";
	}


	public static ArrayList<book> convertBack(ArrayList<DBgenericObject> arr) {
		 ArrayList<book> convertedArr=new ArrayList<book>();
		for(DBgenericObject ob:arr)
		{
			convertedArr.add(new book((int)ob.getValtoArray(0),(String)ob.getValtoArray(1),(String)ob.getValtoArray(2),(String)ob.getValtoArray(3),(String)ob.getValtoArray(4),(boolean)((int)ob.getValtoArray(5)==1)));
		}
		return convertedArr;
		
	}
	@Override
	public String toString() {
		return "book [bookID=" + bookID + ", title=" + title + ", language=" + language + ", author=" + author
				+ ", summary=" + summary + ", bookEnable=" + bookEnable + "]";
	}

}
