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

public class TimerProject {
	
	private LoginGUI client;
	private ArrayList<Review> arrR;
	int seccondpass = 0;
	Timer timer = new Timer();
	TimerTask task = new TimerTask() {
		public void run() {
			Review r = new Review();
			try{
			arrR = ReviewController.SearchReviews("bookID", r, "reviewStatus =" + -1, client.getClient() );
			JOptionPane.showMessageDialog(null,"you have (" + arrR.size() + ") new review", "Message",JOptionPane.INFORMATION_MESSAGE);
			ReviewController.UpdateReviewContent(r, "reviewStatus ="+0, "reviewStatus=" + -1,  client.getClient());
			}
			catch(RuntimeException ex){}
				
			
			//seccondpass++;
			//System.out.println("passed: " + seccondpass);
		}
	};

	public void start(LoginGUI screen) {
		// TODO Auto-generated method stub
		this.client = screen;
		timer.scheduleAtFixedRate(task, 10000, 10000);
	}
}