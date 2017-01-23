
import java.io.IOException;
import java.util.ArrayList;

import Book.Book;
import Book.Domain;
import Controller.BookController;
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
	private float price;
	private String summary;
	private DBSQLhandler client;
	private Domain bookDomain;
	
	public SearchBookTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		title="kof";
		author="kofiko";
		language="kofiko";
		contents="kofiko";
		keywords="kofiko";
		summary="kofiko";
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
		/*12CHF + 14 CHF = 26 CHF */
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
		/*12CHF + 14 CHF = 26 CHF */
		Book expectedBook= new Book();
		int expectedSize= 1;
		ArrayList<Book> result=BookController.SearchBook("bookID,author", searchBook, "author=\""+author, client);
		assertTrue(result.size()>1);
		for(int i=0;i<result.size();i++)
		{
			assertEquals(author, result.get(i).getAuthor());
			assertNotSame("The "+i+"and "+(i+1)+"bookID is ", result.get(i).getBookID(),result.get(i+1).getBookID());
		}
	}
	/**
	 * @author kfir
	 * check for the book with the following author that initailize at SetUp
	 * check if the number of result is more then one 1(the size of the arrayList)
	 */
	public void testSearchByLanguage() {
		/*12CHF + 14 CHF = 26 CHF */
		Book expectedBook= new Book();
		int expectedSize= 1;
		ArrayList<Book> result=BookController.SearchBook("bookID,author", searchBook, "author=\""+author, client);
		assertTrue(result.size()>1);
		for(int i=0;i<result.size();i++)
		{
			assertEquals(author, result.get(i).getAuthor());
			assertNotSame("The "+i+"and "+(i+1)+"bookID is ", result.get(i).getBookID(),result.get(i+1).getBookID());
		}
	}
	/**
	 * @author kfir
	 * check for the book with the following author that initailize at SetUp
	 * check if the number of result is more then one 1(the size of the arrayList)
	 */
	public void testSearchBySummary() {
		/*12CHF + 14 CHF = 26 CHF */
		Book expectedBook= new Book();
		int expectedSize= 1;
		ArrayList<Book> result=BookController.SearchBook("bookID,author", searchBook, "author=\""+author, client);
		assertTrue(result.size()>1);
		for(int i=0;i<result.size();i++)
		{
			assertEquals(author, result.get(i).getAuthor());
			assertNotSame("The "+i+"and "+(i+1)+"bookID is ", result.get(i).getBookID(),result.get(i+1).getBookID());
		}
	}
	/**
	 * @author kfir
	 * check for the book with the following author that initailize at SetUp
	 * check if the number of result is more then one 1(the size of the arrayList)
	 */
	public void testSearchByDomain() {
		/*12CHF + 14 CHF = 26 CHF */
		Book expectedBook= new Book();
		int expectedSize= 1;
		ArrayList<Book> result=BookController.SearchBook("bookID,author", searchBook, "author=\""+author, client);
		assertTrue(result.size()>1);
		for(int i=0;i<result.size();i++)
		{
			assertEquals(author, result.get(i).getAuthor());
			assertNotSame("The "+i+"and "+(i+1)+"bookID is ", result.get(i).getBookID(),result.get(i+1).getBookID());
		}
	}
	/**
	 * @author kfir
	 * check for the book with the following author that initailize at SetUp
	 * check if the number of result is more then one 1(the size of the arrayList)
	 */
	public void testSearchByKeywords() {
		/*12CHF + 14 CHF = 26 CHF */
		Book expectedBook= new Book();
		int expectedSize= 1;
		ArrayList<Book> result=BookController.SearchBook("bookID,author", searchBook, "author=\""+author, client);
		assertTrue(result.size()>1);
		for(int i=0;i<result.size();i++)
		{
			assertEquals(author, result.get(i).getAuthor());
			assertNotSame("The "+i+"and "+(i+1)+"bookID is ", result.get(i).getBookID(),result.get(i+1).getBookID());
		}
	}
	/**
	 * @author kfir
	 * check for the book with the following author that initailize at SetUp
	 * check if the number of result is more then one 1(the size of the arrayList)
	 */
	public void testCombination() {
		/*12CHF + 14 CHF = 26 CHF */
		Book expectedBook= new Book();
		int expectedSize= 1;
		ArrayList<Book> result=BookController.SearchBook("bookID,author", searchBook, "author=\""+author, client);
		assertTrue(result.size()>1);
		for(int i=0;i<result.size();i++)
		{
			assertEquals(author, result.get(i).getAuthor());
			assertNotSame("The "+i+"and "+(i+1)+"bookID is ", result.get(i).getBookID(),result.get(i+1).getBookID());
		}
	}
}
