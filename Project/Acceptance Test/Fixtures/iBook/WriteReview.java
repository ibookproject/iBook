package Fixtures.iBook;

import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import Book.Book;
import Book.Review;
import Controller.BookController;
import Controller.ReviewController;
import client.DBSQLhandler;
import fit.ActionFixture;



public class WriteReview extends ActionFixture {
	final public static int DEFAULT_PORT = 5555;
	private Book toFindBook;
	private Book foundBook;
	private String title;
	private String contents;
	private String author;
	private DBSQLhandler client;
	private Review newReview;
	
	public void startFindBook(){
		String host="localhost";
		try {
			client = new DBSQLhandler(host, DEFAULT_PORT);// connection to
															// server
		} catch (IOException exception) {
			System.out.println("Error: Can't setup connection!" + " Terminating client.");
			System.exit(1);
		}
		toFindBook=new Book();
	}
	public void startReview(){
		newReview=new Review();
		Calendar time = Calendar.getInstance();
        String timeRightNow = String.format("%1$tY/%1$tm/%1$td", time);
		newReview.setReviewDate(timeRightNow);
		newReview.setReviewStatus(Review.NOTIFICATION);
	}

	public void setBookTitle(String title) {
	toFindBook.setTitle(title);
	}
	public void write(String contents) {
		newReview.setReviewContent(contents);
	}
	public void setBookAuthor(String author) {
		toFindBook.setAuthor(author);
	}
	public boolean findBook()
	{
		ArrayList<Book> temp=BookController.SearchBook("bookID,title,author", toFindBook, "title=\""+toFindBook.getTitle()+" && author=\""+author, client);
		if(temp==null||temp.size()==0||temp.size()>1)
			return false;
		foundBook=temp.get(0);
		newReview.setBookID(foundBook.getBookID());
		return true;
	}
	public String getBookTitle(){
		return foundBook.getTitle();
	}
	public String getBookAuthor(){
		return foundBook.getAuthor();
	}
	public int getBookID(){
		return foundBook.getBookID();
	}
	public boolean writeNewReview(){
		return ReviewController.AddReview(newReview, client);
	}
	public boolean checkBookToReview(){
		ArrayList<Review> temp=ReviewController.SearchReviews("bookID,reviewID", new Review(), "reviewContent=\""+contents+" && bookID="+foundBook.getBookID(), client);
	if(temp==null||temp.size()<1)
		return false;
	for(Review r:temp)
	{
		if(r.getBookID()!=foundBook.getBookID())
			return false;
	}
	return true;
	}

}
