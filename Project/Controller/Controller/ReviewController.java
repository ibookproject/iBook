package Controller;

import java.sql.SQLException;
import java.util.ArrayList;

import Book.Book;
import Book.Review;
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
 * Review Controller
 * @author Sagi Entenberg
 * @author Coral Carmeli
 */
public class ReviewController {
	
/**
 * Search Reviews
 * @param fromSentence
 * String of the SQL query "From"
 * @param review
 * the name of the table from this object
 * @param whereSentence
 * String of the SQL query "Where"
 * @param client
 * the client who ask the query
 * @return 
 * Array list of result Review
 * @author Sagi Entenberg
 */
	public static ArrayList<Review> SearchReviews(String fromSentence,Review review, String whereSentence, DBSQLhandler client) {
		client.searchInDB(new searchCommand<Review>(fromSentence, review,whereSentence));
		while (!client.GetGotMessag()) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException ex) {
				System.out.println("InterruptedException " + ex);
			}
		}
		try {
			return Review.convertBack(
					(ArrayList<DBgenericObject>) client.getResultObject(),fromSentence);
		} catch (SQLException e) {
			return null;
		}
	}
/**
 * Update Review Content
 * @param review
 * the name of the table from this object
 * @param updateCondition
 * String of the SQL query "From"
 * @param searchCondition
 *  String of the SQL query "Where"
 * @param client
 * the client who ask the query
 * @return
 * true or false if the update Succeeded
 * @author Sagi Entenberg
 */
	public static boolean UpdateReviewContent(Review review, String updateCondition,String searchCondition, DBSQLhandler client) 													
	{
		client.UpdateInDB(new updateCommand<DBtranslation>(review, searchCondition,updateCondition));
		while (!client.GetGotMessag()) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException ex) {
				System.out.println("InterruptedException " + ex);
				return false;
			}
		}
		return true; // means the review update successful
	}
/**
 * Delete Review
 * @param review
 * the name of the table from this object
 * @param searchCondition
 * String of the SQL query "From"
 * @param client
 * the client who ask the query
 * @author Sagi Entenberg
 */
	public static void DeleteReview(Review review, String searchCondition,DBSQLhandler client) {
		client.deleteFromDB(new deleteCommand<DBtranslation>(review, searchCondition));
		while (!client.GetGotMessag()) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException ex) {
				System.out.println("InterruptedException " + ex);

			}
			System.out.println("The review was deleted");
		}
	}

/**
 * Add New Review
 * @param review
 * the name of the table from this object
 * @param client
 * the client who ask the query
 * @return
 * true or false if the update Succeeded
 * @author Sagi Entenberg
 */

	public static boolean AddReview(Review review, DBSQLhandler client) // boolean
	{
		if(review==null||review.getReviewContent()==null||review.getReviewContent().equals("")||review.getUserSign()==null)
		{
			return false;
		}

		client.insertToDB(new insertCommand<DBtranslation>(review));
		while (!client.GetGotMessag()) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException ex) {
				System.out.println("InterruptedException " + ex);
				return false;
			}
		}
		return true; // means the book add successful
	}

	/**
	 *
	 * @param client
	 * the client who ask the query
	 * @return 
	 * The result of join between the 2 tables:'Book','Review' according the same bookID,return the requested fields.
	 *  @author Coral Carmeli
	 */
	public static ArrayList<DBgenericObject> searchJoinReviewBook(DBSQLhandler client) /*throws SQLException*/
	{
		Book b=new Book();
		Review r=new Review();
		ArrayList<joinObject> temp =new ArrayList<joinObject>();
		
		//the first object is the assosiation class and the second is to join with
		temp.add(new joinObject(r.getClassName(), b.getClassName(), "bookID"));
		
		try {
			client.joinSearchInDB(new joinCommand<Review>("review.bookID,review.reviewID,review.reviewContent,review.reviewDate,book.title",r,temp,"review.reviewStatus<1"));
		} catch (SQLException e) {
		
		}
		while(!	client.GetGotMessag())
		{//search book in db
			try{
			Thread.sleep(50);
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
