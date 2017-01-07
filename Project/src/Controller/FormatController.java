package Controller;

import Book.Book;
import Book.Domain;
import Book.Subject;
import client.DBSQLhandler;
import command.DBtranslation;
import command.insertCommand;

public class FormatController {
	
	/*
	 * AddNewSubject-coral
	 * AddNewDomain-coral
	 * GetAllSubjectList-coral
	 * GetAllBookList-coral
	 * AttachFormatBook-coral
	 * */
	
	
	
	
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


}
