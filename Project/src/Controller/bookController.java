package Controller;

import java.sql.SQLException;
import java.util.ArrayList;

import command.DBtranslation;
import command.insertCommand;
import command.searchCommand;
import command.showAllCommand;
import command.updateCommand;
import client.DBSQLhandler;
import client.DBgenericObject;
import Book.Domain;
import Book.SubjectToBook;
import Book.Book;


public class bookController {
	/*
	 * SearchBook - Done
	 * TemporaryRemove
	 * DeleteBook
	 * GetBookList
	 * RequestStatisticBookReport
	 * RequestBookRate
	 * CheckDetailsInventoryManagment
	 * AddBook - Done
	 * UpdateBook
	 * GetAllDomain- Done
	 * getAllBookTable - Done
	 * */

	public static ArrayList<Book> SearchBook(String fromSentence,Book b,String condition,DBSQLhandler client)
	{
		// filed is need to look like "bookID,author,..."
		client.searchInDB(new searchCommand<Book>(fromSentence,b,condition));//call command and client ask to search a book
		while(!client.GetGotMessag()){//search book in db
			try{
			Thread.sleep(500);
			}
			catch(InterruptedException ex)
			{
				System.out.println("InterruptedException "+ex);
			}
		}
		try {
			
			return  Book.convertBack((ArrayList<DBgenericObject>) client.getResultObject(), fromSentence);
		} catch (SQLException e) {
			return null;
		}
	}
/*#####################################################################*/
/* Return all table of domain for the search in the method BookRate! */
/*#####################################################################*/
	public static ArrayList<Domain> getAllDomainTable(Domain d,DBSQLhandler client)
	{
		client.getAllTable(new showAllCommand<Domain>(d));
		while(!client.GetGotMessag()){//show table -domain
			try{
			Thread.sleep(500);
			}
			catch(InterruptedException ex)
			{
				System.out.println("InterruptedException "+ex);
			}
		}
		try {
			
			return  Domain.convertBack((ArrayList<DBgenericObject>) client.getResultObject(), "DomainID,DomainName");
		} catch (SQLException e) {
			return null;
		}
	}
/*#####################################################################*/


	
/*#####################################################################*/
	/* Return all table of BOOK for the search in the method SearchBook! */
/*#####################################################################*/
		public static ArrayList<Book> getAllBookTable(Book b,DBSQLhandler client)
		{
			client.getAllTable(new showAllCommand<Book>(b));
			while(!client.GetGotMessag()){//show table -domain
				try{
				Thread.sleep(500);
				}
				catch(InterruptedException ex)
				{
					System.out.println("InterruptedException "+ex);
				}
			}
			try {
				
				return  Book.convertBack((ArrayList<DBgenericObject>) client.getResultObject(), "bookID,title");
			} catch (SQLException e) {
				return null;
			}
		}
/*#####################################################################*/
	
/*#####################################################################*/
		/* Search specific subject for the method BookRate! */
/*#####################################################################*/
	public static ArrayList<SubjectToBook> SearchSubject(String fromSentence,SubjectToBook s,String condition,DBSQLhandler client)
	{
		client.searchInDB(new searchCommand<SubjectToBook>(fromSentence,s,condition));//call command and client ask to search a book
		while(!client.GetGotMessag()){//search subject in db
			try{
			Thread.sleep(500);
			}
			catch(InterruptedException ex)
			{
				System.out.println("InterruptedException "+ex);
			}
		}
		try {
			
			return  SubjectToBook.convertBack((ArrayList<DBgenericObject>) client.getResultObject(), fromSentence);
		} catch (SQLException e) {
			return null;
		}
	}
/*#####################################################################*/
	

/*#####################################################################*/
							/* AddBook method */
/*#####################################################################*/
	
	public static boolean AddBook(Book b,DBSQLhandler client) // boolean function that return true if the add book done else false.
	{
			client.insertToDB(new insertCommand<DBtranslation>(b)); 	
			while(!client.GetGotMessag()){//add book to DB
				try{
				Thread.sleep(500);
				}
				catch(InterruptedException ex)
				{
					System.out.println("InterruptedException "+ex);
					return false;
				}
			}
			return true;	// means the book add successful	
	}
	
/*#####################################################################*/
	
	
	//new hen 10.1//
	public static boolean UpdateBook(Book b , String updateCondition , String searchCondition, DBSQLhandler client) // boolean function that return true if user updated else false.
	{
			client.UpdateInDB(new updateCommand<DBtranslation>(b, searchCondition, updateCondition));
			while(!client.GetGotMessag()){//add user to DB
				try{
				Thread.sleep(500);
				}
				catch(InterruptedException ex)
				{
					System.out.println("InterruptedException "+ex);
					return false;
				}
			}
			return true;	// means the user add successful	
	}

	
	
}
