package Fixtures.iBook;

import java.io.IOException;

import Book.Book;
import Controller.BookController;
import client.DBSQLhandler;
import fit.ActionFixture;



public class AddBook extends ActionFixture {
	final public static int DEFAULT_PORT = 5555;
	private Book newBook;
	private String title;
	private String language;
	private String contents;
	private String  keywords;
	private String author;
	private float price;
	private String summary;
	private DBSQLhandler client;
	
	public void startBook(){
		String host;
		try {
			host = args[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			host = "localhost";
		}
		try {
			client = new DBSQLhandler(host, DEFAULT_PORT);// connection to
															// server
		} catch (IOException exception) {
			System.out.println("Error: Can't setup connection!" + " Terminating client.");
			System.exit(1);
		}
		
		newBook=new Book();
	}

	public void setTitle(String title) {
	newBook.setTitle(title);
	}

	public void setLanguage(String language) {
		newBook.setLanguage(language);
	}

	public void setContents(String contents) {
		newBook.setContent(contents);
	}

	public void setKeywords(String keywords) {
		newBook.setKeyword(keywords);
	}

	public void setAuthor(String author) {
		newBook.setAuthor(author);
	}

	public void setPrice(float price) {
		setPrice(price);
	}

	public void setSummary(String summary) {
		newBook.setSummary(summary);
	}
	public boolean addBook(){
		return BookController.AddBook(newBook, client); 
	}

}
