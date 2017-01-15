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

public class FormatController {
	

	
	public static ArrayList<Domain> SearchDomain(String fromSentence,Domain d,String whereSentence,DBSQLhandler client)
	{
		// filed is need to look like "userID,password,..."
		client.searchInDB(new searchCommand<Domain>(fromSentence,d,whereSentence));//call command and client ask to search a book
		while(!client.GetGotMessag()){//search user in db
			try{
			Thread.sleep(500);
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
	
	public static ArrayList<Subject> SearchSubject(String fromSentence,Subject s,String whereSentence,DBSQLhandler client)
	{
		// filed is need to look like "userID,password,..."
		client.searchInDB(new searchCommand<Subject>(fromSentence,s,whereSentence));//call command and client ask to search a book
		while(!client.GetGotMessag()){//search user in db
			try{
			Thread.sleep(500);
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
	
	public static boolean AddDomain(Domain d,DBSQLhandler client) // boolean function that return true if the add book done else false.
	{
			client.insertToDB(new insertCommand<DBtranslation>(d)); 	
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
	public static boolean AddSubject(Subject s,DBSQLhandler client) // boolean function that return true if the add book done else false.
	{
			client.insertToDB(new insertCommand<DBtranslation>(s)); 	
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
	public static ArrayList<Domain> GetAllDomain(Domain d,DBSQLhandler client)// changes
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
		return  Domain.convertBack((ArrayList<DBgenericObject>) client.getResultObject(),"DomainID,DomainName");
	} 	
	catch (SQLException e) {
		return null;
	}
	}
	
	
	/*Coral made for search book*/
	public static ArrayList<Subject> GetAllSubject(Subject s,DBSQLhandler client)// changes
	{
		// filed is need to look like "bookID,author,..."
		client.getAllTable((new showAllCommand<Subject>(s)));
		try{
		Thread.sleep(500);
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
			Thread.sleep(500);
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
	/*Coral made*/
	
	
	////////////////////new/////////////////////
	public static ArrayList<Subject> SearchSubjectAtDomain(String fromSentence,Subject s,String condition,DBSQLhandler client)
	{
		// filed is need to look like "bookID,author,..."
		client.searchInDB(new searchCommand<Subject>(fromSentence,s,condition));//call command and client ask to search a book
		while(!client.GetGotMessag()){//search Subject in db
			try{
			Thread.sleep(500);
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
	////////////////////new/////////////////////
	
	
	public static ArrayList<SubjectToBook> SearchSubjectAtSubjectToBook(String fromSentence,SubjectToBook s,String whereSentence,DBSQLhandler client)
	{
		// filed is need to look like "userID,password,..."
		client.searchInDB(new searchCommand<SubjectToBook>(fromSentence,s,whereSentence));//call command and client ask to search a book
		while(!client.GetGotMessag()){//search user in db
			try{
			Thread.sleep(500);
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
	
	

}
