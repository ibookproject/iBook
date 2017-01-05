package Controller;

import java.sql.SQLException;
import java.util.ArrayList;

import command.DBtranslation;
import command.insertCommand;
import command.searchCommand;
import command.showAllCommand;
import client.DBSQLhandler;
import client.DBgenericObject;
import Book.Domain;
import Book.Subject;
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
			
			return  (ArrayList<Book>) client.getResultObject();
		} catch (SQLException e) {
			return null;
		}
	}
	
	// my try to get all Domain from Domain table ... not work for now , if you want to try just make a copy or dont remove this 
	public static ArrayList<DBgenericObject> GetAllDomain(Domain d,DBSQLhandler client)
	{
		// filed is need to look like "bookID,author,..."
		client.getAllTable((new showAllCommand<Domain>(d)));
		try{
		Thread.sleep(500);
		}
		catch(InterruptedException ex)
		{
			System.out.println("InterruptedException "+ex);
		}
		try {
		return  (ArrayList<DBgenericObject>)client.getResultObject();
	} 	
	catch (SQLException e) {
		return null;
	}
	//	return null;
	}
	
	
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
	
	/* not work 
	public static ArrayList<Subject> GetSubjectsOfChoosenDomain(Subject d,DBSQLhandler client)
	{
		// filed is need to look like "bookID,author,..."
		client.searchInDB(new searchCommand<book>(d,condition, "bookID, author"));
		//client.getAllTable((new showAllCommand<Subject>(d)));
		try{
		Thread.sleep(500);
		}
		catch(InterruptedException ex)
		{
			System.out.println("InterruptedException "+ex);
		}
		try {
		return  (ArrayList<Subject>)client.getResultObject();
	} 	
	catch (SQLException e) {
		return null;
	}
	//	return null;
	}
	*/
	
	
}
