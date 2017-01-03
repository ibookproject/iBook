package client;

import java.awt.print.Book;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import Book.book;
import command.deleteCommand;
import command.insertCommand;
import command.searchCommand;
import command.showAllCommand;
import command.updateCommand;

public class checkDB {
	final public static int DEFAULT_PORT = 5555;
public static void main(String[] args) throws InterruptedException, SQLException
{
	
	DBSQLhandler client = null;//client - translation to db
	String host;

	try {
		host = args[0];
	} catch (ArrayIndexOutOfBoundsException e) {
		host = "localhost";
	}// take the IP of server the host
	
	try {
		client = new DBSQLhandler(host, DEFAULT_PORT);//connection to server
	} catch (IOException exception) {
		System.out.println("Error: Can't setup connection!" + " Terminating client.");
		System.exit(1);
	}
	
	//insert
	book b=new book("kofiko", "HEB", "j.k. roling", "best to know", true);
	client.insertToDB(new insertCommand<book>(b));//insert new book to db
	while(!client.GetGotMessag())// client wait for confirm the sql
		Thread.sleep(500);
	
	//
	client.getAllTable(new showAllCommand<book>());//get all book from db
	while(!client.GetGotMessag())
		Thread.sleep(500);
	
	ArrayList<book> res=book.convertBack((ArrayList<DBgenericObject>) client.getResultObject());
	for(book d:res){
		System.out.println(d+"\n");
	}
	//client.UpdateInDB(new updateCommand<book>("book", "title=\""+b.getTitle()+"\" && language=\""+b.getLanguage()+"\" && author=\""+b.getAuthor()+"\" && summary=\""+b.getSummary()+"\"","title='narnita' && language='ENG'"));
	client.searchInDB(new searchCommand<book>("title=\""+b.getTitle()+"\" && language=\""+b.getLanguage()+"\" && author=\""+b.getAuthor()+"\" && summary=\""+b.getSummary()+"\"", "bookID"));
	while(!client.GetGotMessag())//search book in db
		Thread.sleep(500);
	System.out.println(client.getResultObject());
}
}
