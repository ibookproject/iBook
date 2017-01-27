package Extras;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Book.Review;
import Controller.ReviewController;
import MenuGUI.LoginGUI;
import client.DBSQLhandler;
/**
 * @author Web
 * This class create the Timer feature that we used for send a "notification" about new review request post.
 */
public class TimerProject {
	
	private LoginGUI client;
	private ArrayList<Review> arrR;
	private boolean TimerFlag=true;
	int seccondpass = 0;
	Timer timer = new Timer();
	TimerTask task = new TimerTask() {
		public void run() {
			if(TimerFlag){
			Review r = new Review();
			try{
			arrR = ReviewController.SearchReviews("bookID", r, "reviewStatus =" + Review.NOTIFICATION, client.getClient() );
			JOptionPane.showMessageDialog(null,"you have (" + arrR.size() + ") new review", "Message",JOptionPane.INFORMATION_MESSAGE);
			ReviewController.UpdateReviewContent(r, "reviewStatus ="+0, "reviewStatus=" + -1,  client.getClient());
			}
			catch(RuntimeException ex){	}
			}
			else
			{
				timer.cancel();
				this.cancel();
				try {
					this.finalize();
				} catch (Throwable e) {}
			}
				
			
			//seccondpass++;
			//System.out.println("passed: " + seccondpass);
		}
	};
	public void startTimer(LoginGUI screen) {
		// TODO Auto-generated method stub
		this.client = screen;
		timer.scheduleAtFixedRate(task, 60000, 60000);
		TimerFlag=true;
	}
	public void endTimer(){
		TimerFlag=false;
	}
}