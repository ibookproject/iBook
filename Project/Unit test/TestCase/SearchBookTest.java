package TestCase;

import java.io.IOException;
import java.util.ArrayList;

import Book.Book;
import Book.Domain;
import Book.Subject;
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
	private Subject bookSubject;
	public SearchBookTest(String name) {
		super(name);
		String host="localhost";
		try {
			client = new DBSQLhandler(host, DEFAULT_PORT);// connection to
															// server
		} catch (IOException exception) {
			System.out.println("Error: Can't setup connection!" + " Terminating client.");
			System.exit(1);
		}
		
	}

	protected void setUp() throws Exception {
		
		title="Test";
		author="Group 3";
		language="HEB";
		contents="1.check 2.Test 3.case";
		keywords="check test group 3";
		summary="search book test";
		bookDomain=new Domain("TestDomain");
		titleNoAns="NoAns";
		price=(float) 52.5;
		//insert one book
		BookController.AddBook(new Book(title, language, author, summary,keywords, contents, price), client);
		
		///insert 5 book with the same information but the title is different
		String temp;
		for(int i=1;i<=5;i++)
		{
			temp=title;
			temp=temp+Integer.toString(i);
			BookController.AddBook(new Book(temp, language, author, summary,keywords, contents, price), client);
		}
		
		//insert the book to DomianTable
		FormatController.AddDomain(bookDomain, client);
		ArrayList<Domain> tempDomain=FormatController.SearchDomain("*", bookDomain,"domainName=\""+bookDomain.getDomainID()+"\"", client);
		bookDomain.setDomainID(tempDomain.get(0).getDomainID());
		tempDomain=null;
		bookSubject=new Subject(bookDomain.getDomainID(),"TestSubject");
		FormatController.AddSubject(bookSubject, client);
		String condition="title=\""+ title+ "\"" + " && " + "author=\"" + author  + " && " + "language=\"" + language + " && " + "contents=\"" + contents+ " && " + "contents=\"" + keywords;
		ArrayList<Book> actual=BookController.SearchBook("bookID", searchBook, condition, client);
		SubjectToBook bookToDomain=new SubjectToBook(actual.get(0).getBookID(), bookDomain.getDomainID(), bookSubject.getNameSubject());
		FormatController.AddBookIdDomainIdSubjectNameTOSubjectToBookTable(bookToDomain, client);
		
	}

	
	/**
	 * @author kfir
	 * check for the book form the data base form the data base with the following details in setup
	 * check if the number of actual is 1(the size of the arrayList)
	 */
	public void testOnlyOneactual() {
		Book expectedBook=new Book(title, language, author, summary,keywords, contents, price);
		int expectedSize= 1;
		String condition="title=\""+ title+ "\"" + " && " + "author=\"" + author  + " && " + "language=\"" + language + " && " + "contents=\"" + contents+ " && " + "contents=\"" + keywords;
		ArrayList<Book> actual=BookController.SearchBook("*", searchBook, condition, client);
		assertEquals(expectedSize, actual.size());
		assertEquals(expectedBook, actual.get(0));
	}
	/**
	 * @author kfir
	 * check for the book form the data base form the data base with the following author that initialize at SetUp
	 * check if the number of actual is more then one 1(the size of the arrayList)
	 */
	public void testMoreThenOneactualByAuthor() {
		String expectedAuthor="Group 3";
		ArrayList<Book> actual=BookController.SearchBook("*", searchBook, "author LIKE '%" +expectedAuthor + "%'", client);
		assertTrue(actual.size()>1);
		for(int i=0;i<actual.size()-1;i++)
		{
			assertEquals(expectedAuthor, actual.get(i).getAuthor());
			assertEquals(expectedAuthor, actual.get(i+1).getAuthor());
			assertNotSame("The "+i+"and "+(i+1)+"bookID is ", actual.get(i),actual.get(i+1));
		}
	}
	/**
	 * @author kfir
	 * check for the book form the data base form the data base with the following Language that initialize at SetUp
	 */
	public void testSearchByLanguage() {
		String expectedLanguage="HEB";
		ArrayList<Book> actual=BookController.SearchBook("*", searchBook, "Language=\""+language, client);
		for(int i=0;i<actual.size();i++)
		{
			assertEquals(language, actual.get(i).getLanguage());
			assertNotSame("The "+i+"and "+(i+1)+"bookID is ", actual.get(i).getBookID(),actual.get(i+1).getBookID());
		}
	}
	/**
	 * @author kfir
	 * check for the book form the data base form the data base with the following Summary that initialize at SetUp
	 */
	public void testSearchBySummary() {
		Book expectedBook= new Book();
		
		///change to like
		ArrayList<Book> actual=BookController.SearchBook("*", searchBook, "Summary=\""+summary, client);
		for(int i=0;i<actual.size();i++)
		{
			assertEquals(summary, actual.get(i).getSummary());
			assertNotSame("The "+i+"and "+(i+1)+"bookID is ", actual.get(i).getBookID(),actual.get(i+1).getBookID());
		}
	}
	/**
	 * @author kfir
	 * check for the book form the data base with the following author that initialize at SetUp
	 * check if the number of actual is more then one 1(the size of the arrayList)
	 */
	public void testSearchByDomain() {
		Book expectedBook= new Book();
		ArrayList<SubjectToBook> actual=FormatController.SearchBookInSubjectToBookAccordingDomain("*",new SubjectToBook(), "dominID=\""+bookDomain.getDomainID(), client);
		for(int i=0;i<actual.size();i++)
		{
			assertEquals(bookDomain.getDomainID(), actual.get(i).getDomainID());
			assertNotSame("The "+i+"and "+(i+1)+"bookID is ", actual.get(i).getBookID(),actual.get(i+1).getBookID());
		}
	}
	/**
	 * @author kfir
	 * check for the book form the data base with the following Keywords that initialize at SetUp
	 */
	public void testSearchByKeywords() {
		Book expectedBook= new Book();
		ArrayList<Book> actual=BookController.SearchBook("*", searchBook, "keyword=\""+keywords, client);
		for(int i=0;i<actual.size();i++)
		{
			assertEquals(keywords, actual.get(i).getKeywordString());
			assertNotSame("The "+i+"and "+(i+1)+"bookID is ", actual.get(i).getBookID(),actual.get(i+1).getBookID());
		}
	}
	/**
	 * @author kfir
	 * check for the book form the data base with the following author and title that initialize at SetUp
	 */
	public void testCombination() {
		Book expectedBook= new Book();
		ArrayList<Book> actual=BookController.SearchBook("*", searchBook, "author=\""+author+" && "+"title=\""+title, client);
		for(int i=0;i<actual.size();i++)
		{
			assertEquals(author, actual.get(i).getAuthor());
			assertEquals(title, actual.get(i).getTitle());
			assertNotSame("The "+i+"and "+(i+1)+"bookID is ", actual.get(i).getBookID(),actual.get(i+1).getBookID());
		}
	}
	/**
	 * @author kfir
	 * check for the book form the data base with the following title titleNoAns that initialize at SetUp
	 * check if the actual size if 0 --> there is no one with the title: titleNoAns
	 */
	public void testNoactual() {
		Book expectedBook= new Book();
		int expectedSize= 0;
		ArrayList<Book> actual=BookController.SearchBook("*", searchBook, "title=\""+titleNoAns, client);
		assertNull(actual);
	}
}
