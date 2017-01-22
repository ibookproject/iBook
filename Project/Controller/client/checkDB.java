package client;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import Book.*;
import command.*;
import command.insertCommand;
import command.joinObject;
import command.searchCommand;
import command.showAllCommand;
import command.updateCommand;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.BoxLayout;
import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;

public class checkDB extends JPanel {

	public checkDB() {
		
	}
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
	/*
	Book b=new Book("kofiko", "HEB", "j.k. roling", "best to know", true);
	client.insertToDB(new insertCommand<Book>(b));//insert new book to db
	while(!client.GetGotMessag())// client wait for confirm the sql
		Thread.sleep(500);
	
	//
	client.getAllTable(new showAllCommand<Book>());//get all book from db
	while(!client.GetGotMessag())
		Thread.sleep(500);
	
	ArrayList<Book> res=Book.convertBack((ArrayList<DBgenericObject>) client.getResultObject());
	for(Book d:res){
		System.out.println(d+"\n");
	}
	//client.UpdateInDB(new updateCommand<book>("book", "title=\""+b.getTitle()+"\" && language=\""+b.getLanguage()+"\" && author=\""+b.getAuthor()+"\" && summary=\""+b.getSummary()+"\"","title='narnita' && language='ENG'"));
	client.searchInDB(new searchCommand<Book>("title=\""+b.getTitle()+"\" && language=\""+b.getLanguage()+"\" && author=\""+b.getAuthor()+"\" && summary=\""+b.getSummary()+"\"", "bookID"));
	while(!client.GetGotMessag())//search book in db
		Thread.sleep(500);
	System.out.println(client.getResultObject());
	*/
	SubjectToBook stb=new SubjectToBook(1, "1");
	Book b=new Book();
	Domain d=new Domain(1,"1");
	ArrayList<joinObject> temp =new ArrayList<joinObject>();
	
	
	//the first object is the assiation class and the second is to join with
	
	
	temp.add(new joinObject(stb.getClassName(), b.getClassName(), "bookID"));
	temp.add(new joinObject(stb.getClassName(), d.getClassName(), "domainID"));
	
	
	//temp.add(new joinObject(stb.getClassName(), "domain", "bookID"));
	client.joinSearchInDB(new joinCommand<Book>("book.bookID,book.title,SubjectToBook.nameSubject,domain.domainName",b,temp,""));
	while(!client.GetGotMessag()){//search book in db
		try{
		Thread.sleep(500);
		}
		catch(InterruptedException ex)
		{
			System.out.println("InterruptedException "+ex);
		}
	}
	System.out.println(client.getResultObject());
	
	
}
}
