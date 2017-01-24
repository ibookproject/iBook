package TestCase;

import java.io.IOException;
import java.util.ArrayList;

import Book.Book;
import Book.Domain;
import Book.SubjectToBook;
import Controller.BookController;
import Controller.FormatController;
import client.DBSQLhandler;
import junit.framework.Assert;
import junit.framework.TestCase;


public class SearchBookTest extends TestCase {
	final public static int DEFAULT_PORT = 5555;
	private Book searchBook;
	private String title;
	private String language;
	private String contents;
	private String  keywords;
	private String author;
	private String titleNoAns;
	private float price;
	private String summary;
	private DBSQLhandler client;
	private Domain bookDomain;
	
	public SearchBookTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		
		//here need to insert real val
		title="kof";
		author="j";
		language="HEB";
		contents="kofiko";
		keywords="kofiko";
		summary="kofiko";
		titleNoAns="NoAns";
		price=(float) 52.5;
		String host="localhost";
		try {
			client = new DBSQLhandler(host, DEFAULT_PORT);// connection to
															// server
		} catch (IOException exception) {
			System.out.println("Error: Can't setup connection!" + " Terminating client.");
			System.exit(1);
		}
		
		searchBook=new Book();
		
		///need to find some domain
		bookDomain=new Domain();
	}

	
	/**
	 * @author kfir
	 * check for the book with the following details in setup
	 * check if the number of result is 1(the size of the arrayList)
	 */
	public void testOnlyOneResult() {
		int expectedBook= 5;
		int expectedSize= 1;
		String condition="title=\""+ title+ "\"" + " && " + "author=\"" + author  + " && " + "language=\"" + language + " && " + "contents=\"" + contents+ " && " + "contents=\"" + keywords;
		ArrayList<Book> result=BookController.SearchBook("bookID", searchBook, condition, client);
		assertEquals(expectedSize, result.size());
		assertEquals(expectedBook, result.get(0).getBookID());
	}
	/**
	 * @author kfir
	 * check for the book with the following author that initailize at SetUp
	 * check if the number of result is more then one 1(the size of the arrayList)
	 */
	public void testMoreThenOneResultByAuthor() {
		Book expectedBook= new Book();
		ArrayList<Book> result=BookController.SearchBook("bookID,author", searchBook, "author LIKE '%" +author + "%'", client);
		assertTrue(result.size()>1);
		for(int i=0;i<result.size()-1;i++)
		{
			assertEquals(author, result.get(i).getAuthor());
			assertEquals(author, result.get(i+1).getAuthor());
			assertNotSame("The "+i+"and "+(i+1)+"bookID is ", result.get(i).getBookID(),result.get(i+1).getBookID());
		}
	}
	/**
	 * @author kfir
	 * check for the book with the following Language that initailize at SetUp
	 */
	public void testSearchByLanguage() {
		Book expectedBook= new Book();
		ArrayList<Book> result=BookController.SearchBook("bookID,Language", searchBook, "Language=\""+language, client);
		for(int i=0;i<result.size();i++)
		{
			assertEquals(language, result.get(i).getLanguage());
			assertNotSame("The "+i+"and "+(i+1)+"bookID is ", result.get(i).getBookID(),result.get(i+1).getBookID());
		}
	}
	/**
	 * @author kfir
	 * check for the book with the following Summary that initailize at SetUp
	 */
	public void testSearchBySummary() {
		Book expectedBook= new Book();
		ArrayList<Book> result=BookController.SearchBook("bookID,Summary", searchBook, "Summary=\""+summary, client);
		for(int i=0;i<result.size();i++)
		{
			assertEquals(summary, result.get(i).getSummary());
			assertNotSame("The "+i+"and "+(i+1)+"bookID is ", result.get(i).getBookID(),result.get(i+1).getBookID());
		}
	}
	/**
	 * @author kfir
	 * check for the book with the following author that initailize at SetUp
	 * check if the number of result is more then one 1(the size of the arrayList)
	 */
	public void testSearchByDomain() {
		Book expectedBook= new Book();
		ArrayList<SubjectToBook> result=FormatController.SearchBookInSubjectToBookAccordingDomain("bookID,dominID",new SubjectToBook(), "dominID=\""+bookDomain.getDomainID(), client);
		for(int i=0;i<result.size();i++)
		{
			assertEquals(bookDomain.getDomainID(), result.get(i).getDomainID());
			assertNotSame("The "+i+"and "+(i+1)+"bookID is ", result.get(i).getBookID(),result.get(i+1).getBookID());
		}
	}
	/**
	 * @author kfir
	 * check for the book with the following Keywords that initailize at SetUp
	 */
	public void testSearchByKeywords() {
		Book expectedBook= new Book();
		ArrayList<Book> result=BookController.SearchBook("bookID,keyword", searchBook, "keyword=\""+keywords, client);
		for(int i=0;i<result.size();i++)
		{
			assertEquals(keywords, result.get(i).getKeywordString());
			assertNotSame("The "+i+"and "+(i+1)+"bookID is ", result.get(i).getBookID(),result.get(i+1).getBookID());
		}
	}
	/**
	 * @author kfir
	 * check for the book with the following author and title that initailize at SetUp
	 */
	public void testCombination() {
		Book expectedBook= new Book();
		ArrayList<Book> result=BookController.SearchBook("bookID,author,title", searchBook, "author=\""+author+" && "+"title=\""+title, client);
		for(int i=0;i<result.size();i++)
		{
			assertEquals(author, result.get(i).getAuthor());
			assertEquals(title, result.get(i).getTitle());
			assertNotSame("The "+i+"and "+(i+1)+"bookID is ", result.get(i).getBookID(),result.get(i+1).getBookID());
		}
	}
	/**
	 * @author kfir
	 * check for the book with the following title titleNoAns that initailize at SetUp
	 * check if the result size if 0 --> there is no one with the title: titleNoAns
	 */
	public void testNoResult() {
		Book expectedBook= new Book();
		int expectedSize= 0;
		ArrayList<Book> result=BookController.SearchBook("bookID,title", searchBook, "title=\""+titleNoAns, client);
		assertNull(result);
	}
}
