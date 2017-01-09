package Controller;

import java.sql.SQLException;
import java.util.ArrayList;

import Book.Book;
import Book.Review;
import Role.User;
import client.DBSQLhandler;
import client.DBgenericObject;
import command.DBtranslation;
import command.insertCommand;
import command.searchCommand;
import command.updateCommand;

public class ReviewController {
	/*
	 * AddReview-kfir
	 * SearchReviews-sagi
	 * CheckReview-
	 * EditReviewDetails-sagi
	 * UpdateReviewContent-sagi
	 * SetConfirmationReviewStatus-coral
	 * RequsetPostReview
	 * GetCategorysList
	 * GetBookLisAtCategoryt
	 * */

	public static ArrayList<Review> SearchReviews(String fromSentence,Review r,String whereSentence,DBSQLhandler client)
	{
		// filed is need to look like "bookID"
		client.searchInDB(new searchCommand<Review>(fromSentence,r,whereSentence));//call command and client ask to search a review
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
			return Review.convertBack((ArrayList<DBgenericObject>) client.getResultObject(),fromSentence);
		} catch (SQLException e) {
			return null;
		}
	}
	public static boolean UpdateReviewContent(Review r , String updateCondition , String searchCondition, DBSQLhandler client) // boolean function that return true if user updated else false.
	{
			client.UpdateInDB(new updateCommand<DBtranslation>(r, searchCondition, updateCondition));
			while(!client.GetGotMessag()){//add user to DB
				try{
				Thread.sleep(500);
				}
				catch(InterruptedException ex)
				{
					System.out.println("InterruptedException "+ex);
					return false;
				}
			}
			return true;	// means the user add successful	
	}
}
