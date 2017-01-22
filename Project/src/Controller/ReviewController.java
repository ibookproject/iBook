package Controller;

import java.sql.SQLException;
import java.util.ArrayList;

import Book.Book;
import Book.Review;
import Book.SubjectToBook;
import Role.User;
import client.DBSQLhandler;
import client.DBgenericObject;
import command.DBtranslation;
import command.deleteCommand;
import command.insertCommand;
import command.joinCommand;
import command.joinObject;
import command.searchCommand;
import command.updateCommand;
/**
 * 
 * @author Sagi Entenberg
 */
public class ReviewController {
/**
 * 
 * @param fromSentence
 * @param r
 * @param whereSentence
 * @param client
 * @return null if SQLException or result Review
 */
	public static ArrayList<Review> SearchReviews(String fromSentence,Review r, String whereSentence, DBSQLhandler client) {
		// filed is need to look like "bookID"
		client.searchInDB(new searchCommand<Review>(fromSentence, r,
				whereSentence));// call command and client ask to search a
								// review
		while (!client.GetGotMessag()) {// search user in db
			try {
				Thread.sleep(250);
			} catch (InterruptedException ex) {
				System.out.println("InterruptedException " + ex);
			}
		}
		try {
			return Review.convertBack(
					(ArrayList<DBgenericObject>) client.getResultObject(),
					fromSentence);
		} catch (SQLException e) {
			return null;
		}
	}
/**
 * 
 * @param r
 * @param updateCondition
 * @param searchCondition
 * @param client
 * @return boolean (true if successfully/false if not successfully)
 */
	public static boolean UpdateReviewContent(Review r, String updateCondition,String searchCondition, DBSQLhandler client) // boolean function
															// that return true
															// if user updated
															// else false.
	{
		client.UpdateInDB(new updateCommand<DBtranslation>(r, searchCondition,updateCondition));
		while (!client.GetGotMessag()) {// add user to DB
			try {
				Thread.sleep(250);
			} catch (InterruptedException ex) {
				System.out.println("InterruptedException " + ex);
				return false;
			}
		}
		return true; // means the review update successful
	}
/**
 * 
 * @param r
 * @param searchCondition
 * @param client
 */
	public static void DeleteReview(Review r, String searchCondition,DBSQLhandler client) {
		client.deleteFromDB(new deleteCommand<DBtranslation>(r, searchCondition));
		while (!client.GetGotMessag()) {// add user to DB
			try {
				Thread.sleep(250);
			} catch (InterruptedException ex) {
				System.out.println("InterruptedException " + ex);

			}
			System.out.println("The review was deleted");
		}
	}

/**
 * @author Sagi Entenberg
 * @param r
 * @param client
 * @return boolean (true if successfully/false if not successfully)
 */

	public static boolean AddReview(Review r, DBSQLhandler client) // boolean
	{
		client.insertToDB(new insertCommand<DBtranslation>(r));
		while (!client.GetGotMessag()) {// add book to DB
			try {
				Thread.sleep(250);
			} catch (InterruptedException ex) {
				System.out.println("InterruptedException " + ex);
				return false;
			}
		}
		return true; // means the book add successful
	}

	/**
	 * @author Coral Carmeli
	 * @param client
	 * @return The result of join between the 2 tables:'Book','Review' according the same bookID,return the requested fields.
	 */
	public static ArrayList<DBgenericObject> searchJoinReviewBook(DBSQLhandler client) /*throws SQLException*/
	{
		Book b=new Book();
		Review r=new Review();
		ArrayList<joinObject> temp =new ArrayList<joinObject>();
		
		//the first object is the assosiation class and the second is to join with
		temp.add(new joinObject(r.getClassName(), b.getClassName(), "bookID"));
		
		try {
			client.joinSearchInDB(new joinCommand<Review>("review.bookID,review.reviewID,review.reviewContent,review.reviewDate,book.title",r,temp,"review.reviewStatu<1"));
		} catch (SQLException e) {
		
		}
		while(!	client.GetGotMessag())
		{//search book in db
			try{
			Thread.sleep(500);
			}
			catch(InterruptedException ex)
			{
				System.out.println("InterruptedException "+ex);
			}
		}

		try {
			return (ArrayList<DBgenericObject>)client.getResultObject();
		} catch (SQLException e) {
		
		}
		return null;
	}
}
