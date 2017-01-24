package Controller;

import java.sql.SQLException;
import java.util.ArrayList;

import Book.Book;
import Book.Domain;
import Book.Subject;
import Book.SubjectToBook;
import Book.Subject;
import Role.User;
import client.DBSQLhandler;
import client.DBgenericObject;
import command.DBtranslation;
import command.insertCommand;
import command.searchCommand;
import command.showAllCommand;
/**
 * format controller
 * @author hen saada
 *@author coral carmeli
 */
public class FormatController {
	
/**
 * 
 * @param fromSentence
 * string for the query sql " FROM"
 * @param domain
 * name of the table that get from this object 
 * @param whereSentence
 * string for the query sql "WHERE"
 * @param client
 * the current client that ask the query
 * @return
 *  The Array list of books according the search query the user sends(with the conditions of-select,from and where)
 * @author hen saada 
 */
	public static ArrayList<Domain> SearchDomain(String fromSentence,Domain domain,String whereSentence,DBSQLhandler client)
	{
		// filed is need to look like "userID,password,..."
		client.searchInDB(new searchCommand<Domain>(fromSentence,domain,whereSentence));//call command and client ask to search a book
		while(!client.GetGotMessag()){//search user in db
			try{
			Thread.sleep(50);
			}
			catch(InterruptedException ex)
			{
				System.out.println("InterruptedException "+ex);
			}
		}
		try {
			return Domain.convertBack((ArrayList<DBgenericObject>) client.getResultObject(),fromSentence);
		} catch (SQLException e) {
			return null;
		}
	}
	/**
	 * 
	 * @param fromSentence
	 * string for the query sql " FROM"
	 * @param subject
	 * name of the table that get from this object 
	 * @param whereSentence
	 * string for the query sql "WHERE"
	 * @param client
	 * the current client that ask the query
	 * @return
	 *  The Array list of subject's according the search query the user sends(with the conditions of-select,from and where)
	 * @author hen saada
	 */
	public static ArrayList<Subject> SearchSubject(String fromSentence,Subject subject,String whereSentence,DBSQLhandler client)
	{
		client.searchInDB(new searchCommand<Subject>(fromSentence,subject,whereSentence));//call command and client ask to search a book
		while(!client.GetGotMessag()){
			try{
			Thread.sleep(50);
			}
			catch(InterruptedException ex)
			{
				System.out.println("InterruptedException "+ex);
			}
		}
		try {
			return Subject.convertBack((ArrayList<DBgenericObject>) client.getResultObject(),fromSentence);
		} catch (SQLException e) {
			return null;
		}
	}
	
	/**
	 * 
	 * @param fromSentence
	 * string for the query sql " FROM"
	 * @param domain
	 * name of the table that get from this object 
	 * @param whereSentence
	 * string for the query sql "WHERE"
	 * @param client
	 * the current client that ask the query
	 * @return
	 * true if the add domain query succeed , else false
	 * @author hen saada
	 */
	public static boolean AddDomain(Domain domain,DBSQLhandler client) // boolean function that return true if the add book done else false.
	{
			client.insertToDB(new insertCommand<DBtranslation>(domain)); 	
			while(!client.GetGotMessag()){//add book to DB
				try{
				Thread.sleep(50);
				}
				catch(InterruptedException ex)
				{
					System.out.println("InterruptedException "+ex);
					return false;
				}
			}
			return true;	// means the book add successful	
	}
	
	/**
	 * 
	 * @param fromSentence
	 * string for the query sql " FROM"
	 * @param subject
	 * name of the table that get from this object 
	 * @param whereSentence
	 * string for the query sql "WHERE"
	 * @param client
	 * the current client that ask the query
	 * @return
	 * true if the add domain query succeed , else false
	 * @author hen saada
	 */
	public static boolean AddSubject(Subject subject,DBSQLhandler client) // boolean function that return true if the add book done else false.
	{
			client.insertToDB(new insertCommand<DBtranslation>(subject)); 	
			while(!client.GetGotMessag()){//add book to DB
				try{
				Thread.sleep(50);
				}
				catch(InterruptedException ex)
				{
					System.out.println("InterruptedException "+ex);
					return false;
				}
			}
			return true;	// means the book add successful	
	}
	public static ArrayList<Domain> GetAllDomain(Domain d,DBSQLhandler client)// changes
	{
		// filed is need to look like "bookID,author,..."
		client.getAllTable((new showAllCommand<Domain>(d)));
		try{
		Thread.sleep(50);
		}
		catch(InterruptedException ex)
		{
			System.out.println("InterruptedException "+ex);
		}
		try {
		return  Domain.convertBack((ArrayList<DBgenericObject>) client.getResultObject(),"DomainID,DomainName");
	} 	
	catch (SQLException e) {
		return null;
	}
	}
	
	
	/**
	 * @author Coral Carmeli
	 * @param Subject,client
	 * @return The ArrayList of Subject - the list of all subject in DB
	 */
	public static ArrayList<Subject> GetAllSubject(Subject s,DBSQLhandler client)// changes
	{
		// filed is need to look like "bookID,author,..."
		client.getAllTable((new showAllCommand<Subject>(s)));
		try{
		Thread.sleep(50);
		}
		catch(InterruptedException ex)
		{
			System.out.println("InterruptedException "+ex);
		}
		try {
		return  Subject.convertBack((ArrayList<DBgenericObject>) client.getResultObject(),"DomainID,nameSubject");
	} 	
		catch (SQLException e) 
		{
			return null;
		}
	}
	
	public static ArrayList<SubjectToBook> SearchBookInSubjectToBookAccordingDomain(String fromSentence,SubjectToBook s,String whereSentence,DBSQLhandler client)
	{
		// filed is need to look like "userID,password,..."
		client.searchInDB(new searchCommand<SubjectToBook>(fromSentence,s,whereSentence));//call command and client ask to search a book
		while(!client.GetGotMessag()){//search user in db
			try{
			Thread.sleep(50);
			}
			catch(InterruptedException ex)
			{
				System.out.println("InterruptedException "+ex);
			}
		}
		try {
			return SubjectToBook.convertBack((ArrayList<DBgenericObject>) client.getResultObject(),fromSentence);
		} catch (SQLException e) {
			return null;
		}
	}
	/**
	 * @author Coral Carmeli
	 * @param fromSentence(String ),Subject, condition(String),client
	 * @return The ArrayList of Subject - the list of all subject in the requested Domain
	 */
	public static ArrayList<Subject> SearchSubjectAtDomain(String fromSentence,Subject s,String condition,DBSQLhandler client)
	{
		// filed is need to look like "bookID,author,..."
		client.searchInDB(new searchCommand<Subject>(fromSentence,s,condition));//call command and client ask to search a book
		while(!client.GetGotMessag()){//search Subject in db
			try{
			Thread.sleep(50);
			}
			catch(InterruptedException ex)
			{
				System.out.println("InterruptedException "+ex);
			}
		}
		try {
			
			return  Subject.convertBack((ArrayList<DBgenericObject>) client.getResultObject(), fromSentence);
		} catch (SQLException e) {
			return null;
		}
	}

	public static ArrayList<SubjectToBook> SearchSubjectAtSubjectToBook(String fromSentence,SubjectToBook s,String whereSentence,DBSQLhandler client)
	{
		// filed is need to look like "userID,password,..."
		client.searchInDB(new searchCommand<SubjectToBook>(fromSentence,s,whereSentence));//call command and client ask to search a book
		while(!client.GetGotMessag()){//search user in db
			try{
			Thread.sleep(50);
			}
			catch(InterruptedException ex)
			{
				System.out.println("InterruptedException "+ex);
			}
		}
		try {
			return SubjectToBook.convertBack((ArrayList<DBgenericObject>) client.getResultObject(),fromSentence);
		} catch (SQLException e) {
			return null;
		}
	}

	public static boolean AddBookIdDomainIdSubjectNameTOSubjectToBookTable(SubjectToBook s,DBSQLhandler client) // boolean function that return true if the add book done else false.
	{
			client.insertToDB(new insertCommand<DBtranslation>(s)); 	
			while(!client.GetGotMessag()){//add book to DB
				try{
				Thread.sleep(50);
				}
				catch(InterruptedException ex)
				{
					System.out.println("InterruptedException "+ex);
					return false;
				}
			}
			return true;	// means the book add successful	
	}
	
	

}
