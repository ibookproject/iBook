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

/**
 *  @author kfir Girstein
 *NEED TO INSERT REAL VALUES
 */
public class RemoveBookTest extends TestCase {
	final public static int DEFAULT_PORT = 5555;
	private Book searchBook;
	private String title;
	private String language;
	private String contents;
	private String keywords;
	private String author;
	private String titleNoAns;
	private float price;
	private String summary;
	private DBSQLhandler client;

	public RemoveBookTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {

		// here need to insert real val
		//first add a new book or books and then
		title = "kof";
		author = "j";
		language = "HEB";
		contents = "kofiko";
		keywords = "kofiko";
		summary = "kofiko";
		titleNoAns = "NoAns";
		price = (float) 52.5;
		String host = "localhost";
		try {
			client = new DBSQLhandler(host, DEFAULT_PORT);// connection to
															// server
		} catch (IOException exception) {
			System.out.println("Error: Can't setup connection!" + " Terminating client.");
			System.exit(1);
		}

		searchBook = new Book();
	}
	
	/**
	 * @author kfir delete the book from the data base with the following
	 *         details in setup check if the number of result is 1(the size of
	 *         the arrayList)
	 */
	public void testDeleteOnlyOneSucceed() {
		boolean result;
		int expectedNumber = 1;
		String condition = "title=\"" + title + "\"" + " && " + "author=\"" + author + " && " + "language=\"" + language
				+ " && " + "contents=\"" + contents + " && " + "contents=\"" + keywords;
		ArrayList<Book> searchResult = BookController.SearchBook("bookID", searchBook, condition, client);
		if (searchResult == null || searchResult.size() != 1)
			assertEquals("can not find any result for this book", expectedNumber, 0);
		else {
			assertEquals(expectedNumber, searchResult.size());
			result = BookController.DeleteBook(searchResult.get(0), "bookID=" + searchResult.get(0).getBookID(),
					client);
			assertTrue(result);
		}
	}
	/*

	public void testDeleteLotsWithSameTitle() {
		boolean expected = true;
		boolean result;
		int expectedNumber = 1;
		int count = 0;
		int countDeleted = 0;
		String condition = "title=\"" + title;
		ArrayList<Book> searchResult = BookController.SearchBook("bookID", searchBook, condition, client);
		if (searchResult == null || searchResult.size() != 1)
			assertEquals("can not find any result for this book", expectedNumber, 0);
		else {
			assertTrue(expectedNumber < searchResult.size());
			for (Book b : searchResult) {
				result = BookController.DeleteBook(searchResult.get(0), "bookID=" + searchResult.get(0).getBookID(),
						client);
				assertEquals(expected, result);
				if (result)
					count++;
			}
			assertEquals(count, searchResult.size());
		}
	}
*/
	/**
	 * @author kfir Girstein check if not delete someone if not found the book
	 *         try to delete the book with title:"bla bla" check if the number
	 *         of book before and after the delete is the same
	 */
	public void testNotFoundBookToDelete() {
		boolean expected = false;
		boolean result;
		int expectedNumber;
		int resultNumber;
		String condition = "title=\"" + titleNoAns;
		ArrayList<Book> searchResult = BookController.getAllBookTable(new Book(), client);
		expectedNumber = searchResult.size();
		searchResult.clear();
		result = BookController.DeleteBook(new Book(), condition, client);
		searchResult = BookController.getAllBookTable(new Book(), client);
		resultNumber = searchResult.size();
		assertEquals(expected, result);
		assertEquals(expectedNumber, resultNumber);

	}

}
