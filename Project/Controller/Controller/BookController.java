package Controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JOptionPane;

import command.DBtranslation;
import command.FileCommand;
import command.deleteCommand;
import command.insertCommand;
import command.joinCommand;
import command.joinObject;
import command.searchCommand;
import command.showAllCommand;
import command.updateCommand;
import client.DBSQLhandler;
import client.DBgenericObject;
import Book.Cart;
import Book.Domain;
import Book.Review;
import Book.SearchToBook;
import Book.SubjectToBook;
import Book.Book;
import MenuGUI.LoginGUI;
import Panels.BookPerCart;


public class BookController {

	/**
	 * This method do search book according the condition sentence which sent to her.
	 * The method return the list of books she found according the 'Where' sentence.
	 * @author Coral Carmeli
	 * @param fromSentence is the parameters we want to get details on him(Select)
	 * @param book is just a kind of book to get the name of the table
	 * @param condition this is the 'Where' sentence  we search according to him
	 * @param client the current client that ask the query
	 * @return 
	 * The Array list of books according the search query the user sends(with the conditions of-select,from and where)
	 */

	public static ArrayList<Book> SearchBook(String fromSentence,Book book,String condition,DBSQLhandler client)
	{
		// filed is need to look like "bookID,author,..."
		client.searchInDB(new searchCommand<Book>(fromSentence,book,condition));//call command and client ask to search a book
		while(!client.GetGotMessag()){//search book in db
			try{
			Thread.sleep(10);
			}
			catch(InterruptedException ex)
			{
				System.out.println("InterruptedException "+ex);
			}
		}
		try {
			
			return  Book.convertBack((ArrayList<DBgenericObject>) client.getResultObject(), fromSentence);
		} catch (SQLException e) {
			return null;
		}
	}
	/**
	 * This method search book in the table searchToBook according the specifec conditions and return the search results
	 * @author Almog Yamin
	 * @param fromSentence is the parameters we want to get details on him(Select)
	 * @param btb  is just a kind of book to get the name of the table
	 * @param condition this is the 'Where' sentence  we search according to him
	 * @param client the current client that ask the query
	 * @return 
	 * The Array list of SearchToBook according the search query the user sends(with the conditions of-select,from and where)
	 */
	public static ArrayList<SearchToBook> SearchSearchToBook(String fromSentence,SearchToBook btb,String condition,DBSQLhandler client)
	{
		// filed is need to look like "bookID,author,..."
		client.searchInDB(new searchCommand<SearchToBook>(fromSentence,btb,condition));//call command and client ask to search a book
		while(!client.GetGotMessag()){//search book in db
			try{
			Thread.sleep(10);
			}
			catch(InterruptedException ex)
			{
				System.out.println("InterruptedException "+ex);
			}
		}
		try {
			
			return  SearchToBook.convertBack((ArrayList<DBgenericObject>) client.getResultObject(), fromSentence);
		} catch (SQLException e) {
			return null;
		}
	}
	
	public static boolean AddBook(Book book,DBSQLhandler client) // boolean function that return true if the add book done else false.
	{
		if(book.getTitle()==null||book.getLanguage()==null||book.getAuthor()==null||book.getSummary()==null||book.getContent()==null||book.getKeyword()==null||book.getPrice()<0)
			return false;
		
		//check if the title and author of the Book book is already Exists.
		ArrayList<Book> searchSame = BookController.SearchBook("title,language", book, "title=\""
				+ book.getTitle() + "\"" + " && " + "author=\"" + book.getAuthor() + "\"",
				client);// call search book method
									// from book controller
		if(searchSame==null||searchSame.isEmpty()){
			client.insertToDB(new insertCommand<DBtranslation>(book)); 	
			while(!client.GetGotMessag()){//add book to DB
				try{
				Thread.sleep(10);
				}
				catch(InterruptedException ex)
				{
					System.out.println("InterruptedException "+ex);
					return false;
				}
			}
			try {
				if (client.getResultObject() instanceof Throwable)
					return false;
			} catch (SQLException e) {
				return false;
			}
		return true;	// means the book add successful	
	}
	else
		return false;
	}
		
	//new hen 10.1//
	public static boolean UpdateBook(Book b , String updateCondition , String searchCondition, DBSQLhandler client) // boolean function that return true if user updated else false.
	{
			client.UpdateInDB(new updateCommand<DBtranslation>(b, searchCondition, updateCondition));
			while(!client.GetGotMessag()){//add user to DB
				try{
				Thread.sleep(10);
				}
				catch(InterruptedException ex)
				{
					System.out.println("InterruptedException "+ex);
					return false;
				}
			}
			return true;	// means the user add successful	
	}
	/** 
	 * @param stb
	 * name of the table that get from this object 
	 * @param client
	 * the current client that ask the query
	 * @return
	 *  The result of adding a SearchToBook to the list-success or not
	 * @author Almog Yamin
	 */
	public static boolean InsertSearchToBook(SearchToBook stb,DBSQLhandler client) // boolean function that return true if the add book done else false.
	{
			client.insertToDB(new insertCommand<DBtranslation>(stb)); 	
			while(!client.GetGotMessag()){//add book to DB
				try{
				Thread.sleep(10);
				}
				catch(InterruptedException ex)
				{
					System.out.println("InterruptedException "+ex);
					return false;
				}
			}
			return true;	// means the book add successful	
	}
	
	/** 
	 * @param stb
	 * name of the table that get from this object 
	 * @param client
	 * the current client that ask the query
	 * @param
	 * the update Condition - means search what row to update by this condition
	 * @return
	 *  true if the update was success else false
	 * @author Almog Yamin
	 */
	public static boolean UpdateSearchToBook(SearchToBook stb , String updateCondition , String searchCondition, DBSQLhandler client) // boolean function that return true if user updated else false.
	{
			client.UpdateInDB(new updateCommand<DBtranslation>(stb, searchCondition, updateCondition));
			while(!client.GetGotMessag()){//add user to DB
				try{
				Thread.sleep(10);
				}
				catch(InterruptedException ex)
				{
					System.out.println("InterruptedException "+ex);
					return false;
				}
			}
			return true;	// means the user add successful	
	}
	/**
	 * This method search the books with the keywords which sent to the search
	 * @param g is the keyword we want to search
	 * @param client the current client that ask the query
	 * @return ArrayList<Book> which is the Books with the keyword that was sent to method
	 * @author Coral Carmeli
	 */
	public static ArrayList<Book> searchKeywords(String g,DBSQLhandler client)
	{
		ArrayList<Book> bookKeywordsChoose=new ArrayList<Book>();
		Book b2=new Book();
		ArrayList<Book> bookKeywords=BookController.SearchBook("bookID,title,language,author,summary,content,keyword,price", b2, "bookEnable=\""+1+"\"", client);
		if(bookKeywords!=null)
		{
			bookKeywordsChoose = new ArrayList<Book> ();
			for(Book b1:bookKeywords)
				for(int i=0;i<b1.getKeyword().length;i++)//
					if(g.equalsIgnoreCase(b1.getKeyword()[i]))
						bookKeywordsChoose.add(b1);	
			if(bookKeywordsChoose.isEmpty())
				return null;
			return bookKeywordsChoose;
		}
		else
			return null;
	}
	public static boolean DeleteBook(Book b , String searchCondition, DBSQLhandler client)
	{
		client.deleteFromDB(new deleteCommand<DBtranslation>(b, searchCondition));
		while(!client.GetGotMessag()){//add user to DB
			try{
			Thread.sleep(10);
			}
			catch(InterruptedException ex)
			{
				System.out.println("InterruptedException "+ex);
				return false;
			}
		}
		return true;
	}
	
	public static ArrayList<Book> getAllBookTable(Book b,DBSQLhandler client)
	{
		client.getAllTable(new showAllCommand<Book>(b));
		while(!client.GetGotMessag()){//show table -domain
			try{
			Thread.sleep(10);
			}
			catch(InterruptedException ex)
			{
				System.out.println("InterruptedException "+ex);
			}
		}
		try {
			
			return  Book.convertBack((ArrayList<DBgenericObject>) client.getResultObject(), "bookID,title,language,author");
		} catch (SQLException e) {
			return null;
		}
	}
	/**
	 * This comparator is used in the user statistics method in the absolute rate-with this comparator we
	 *  sort the array of books according the number of orders
	 * @author Coral Carmeli
	 * @param not get
	 * @return the array list sorted according the Comparator logic(number of orders of the book  with Book sent)
	 */
	public static Comparator<Book> numOfOrdersCompareBook = new Comparator<Book>() {

		public int compare(Book b1, Book b2) {

			float idNum1 = b1.getNumberOfOrder();
			float idNum2 = b2.getNumberOfOrder();

			/* For ascending order */
			return (int) idNum2 - (int) idNum1;
		}
	};
	/**
	 * This comparator is used in the user statistics method in the propotion rate-with this comparator we sort 
	 * the array of books according the number of orders
	 * @author Coral Carmeli
	 * @param no parameters
	 * @return the array list sorted according the Comparator logic(number of orders of the book but with generic object sent)
	 */
	public static Comparator<DBgenericObject> numOfOrdersCompare = new Comparator<DBgenericObject>() {

		@Override
		public int compare(DBgenericObject d1, DBgenericObject d2) {
			// TODO Auto-generated method stub
			long idNum1 = (long) d1.getValtoArray(1);
			long idNum2 = (long) d2.getValtoArray(1);

			/* For ascending order */
			return (int) idNum2 - (int) idNum1;
		}
	};

	/**
	 * This method is used in the book rate method- the function get book id and return the rate of this specific book
	 * against all the book in the inventory.
	 * @param bookID which is the book we want to get the rate in him
	 * @param client is the current client that ask the query
	 * @param screen The login menu
	 * @return the rate of the book agains all the books in the library
	 * @author Coral Carmeli
	 */
	public static int absoluteBookRate(int bookID,DBSQLhandler client,LoginGUI screen)
	{
		long numOfOrder=-1;
		String title="";
		int rate=-1;
		ArrayList<Book> allBooks=new ArrayList<Book>();
		Book b=new Book();
		allBooks=BookController.SearchBook("bookID,title,language,author,summary,content,keyword,numberOfOrder", b, "bookEnable=1", client);
		
		
		Collections.sort(allBooks, BookController.numOfOrdersCompareBook);
		
		System.out.println("After sort:");
		
		for(Book b4:allBooks)
		{
			if(b4.getBookID()==bookID)
				{
				numOfOrder=b4.getNumberOfOrder();
				title=b4.getTitle();
				}
			System.out.print (b4.getNumberOfOrder()+" ");
		}
		for(int i=0;i<allBooks.size();i++)
			if(allBooks.get(i).getNumberOfOrder()==numOfOrder)
			{
				rate=i+1;
				break;
			}
		if (rate!=-1)
		{
			JOptionPane.showMessageDialog(screen,"The rate of " +title+" is "+ rate +":"+allBooks.size(), "Success",JOptionPane.YES_NO_CANCEL_OPTION);
			System.out.println("The rate of " +title+" is "+ rate +":"+allBooks.size());
		}
		return rate;
	}
	
	/**
	 * This method is used in the book rate method- the function get book id and return the rate of this specific book
	 * against all the book with the same domain.
	 * @param bookID which is the book we want to get the rate in him
	 * @param client is the current client that ask the query
	 * @param screen The login menu
	 * @return the rate of the book agains all the books in the same domain
	 * @author Coral Carmeli
	 */
	public static int propotionBookRate(int bookID,DBSQLhandler client,LoginGUI screen)
	{
		String title="";
		int rate=-1;
		ArrayList<SubjectToBook> bookPerDomain=new ArrayList<SubjectToBook>();
		ArrayList<Book> specificBook=new ArrayList<Book>();
		Book b=new Book();
		SubjectToBook s=new SubjectToBook();
		bookPerDomain=FormatController.SearchBookInSubjectToBookAccordingDomain("bookID,domainID", s, "bookID=" + bookID , client);
		specificBook=BookController.SearchBook("bookID,title,language,author,summary,content,keyword,numberOfOrder", b, "bookID=\""+bookID+"\"", client);
		if(bookPerDomain==null||bookPerDomain.isEmpty())
			JOptionPane.showMessageDialog(screen,"Theres no bookID in any domain", "Warning",JOptionPane.WARNING_MESSAGE);
			
		else
		{
			try
			{
				title=specificBook.get(0).getTitle();
				ArrayList<DBgenericObject> joinAnswerDomainBook=searchJoinSubjectBook(bookPerDomain.get(0).getDomainID(),bookID,screen.getClient());
				if(joinAnswerDomainBook.isEmpty())
					JOptionPane.showMessageDialog(screen,"There's nothing to show!", "Warning",JOptionPane.WARNING_MESSAGE);
				
				else
				{
					Collections.sort(joinAnswerDomainBook, numOfOrdersCompare);
					System.out.print("After sorting");
					for(DBgenericObject j:joinAnswerDomainBook)
					{
						System.out.print((long)j.getValtoArray(1)+" ");
					}
					for(int i=0;i<joinAnswerDomainBook.size();i++)
						if((int)joinAnswerDomainBook.get(i).getValtoArray(0)==bookID)
							{
							rate=i+1;
							break;
							}
					if (rate!=-1)
					{
						JOptionPane.showMessageDialog(screen,"The rate of " +title+" is "+ rate +":"+joinAnswerDomainBook.size(), "Success",JOptionPane.YES_NO_CANCEL_OPTION);
						System.out.println("The rate of " +title+" is "+ rate +":"+joinAnswerDomainBook.size());
					}
					return rate;
				}	
			} 
			catch (SQLException e1) 
			{
				JOptionPane.showMessageDialog(screen,"There's nothing to show!", "Warning",JOptionPane.WARNING_MESSAGE);
				System.out.println("There's no books to this domain!");
			}
		}
		return -1;
	}	

/**
 * This method is used in the book rate method
 * This method get book and domain ID's and return the result of the join between the specific tables.
 * @param domainID this is the domain we want to get the book for him
 * @param bookID is the book we want to do join according to him
 * @param client is the current client that ask the query
 * @return the result of the join between the 'Book' table and 'SubjectToBook' table
 * @throws SQLException
 * @author Coral Carmeli
 */
	public static ArrayList<DBgenericObject> searchJoinSubjectBook(int domainID,int bookID,DBSQLhandler client) throws SQLException
	{
		Book b=new Book();
		SubjectToBook s=new SubjectToBook();
		ArrayList<joinObject> temp =new ArrayList<joinObject>();
		
		//the first object is the assosiation class and the second is to join with
		temp.add(new joinObject(s.getClassName(), b.getClassName(), "bookID"));
		
		client.joinSearchInDB(new joinCommand<SubjectToBook>("book.bookID,book.numberOfOrder,SubjectToBook.domainID",s,temp,"SubjectToBook.domainID=\""+domainID +"\""));
		while(!	client.GetGotMessag()){//search book in db
			try{
			Thread.sleep(10);
			}
			catch(InterruptedException ex)
			{
				System.out.println("InterruptedException "+ex);
			}
		}
		return (ArrayList<DBgenericObject>)client.getResultObject();
	}
	/**
	 * 
	 *@author Sagi Entenberg
	 * @param BookID
	 * @param type
	 * @param client
	 * @return temp (bytes for send)
	 * @throws IOException
	 * @throws SQLException
	 */
	public static byte[] GetBookFile(int BookID,String type,DBSQLhandler client) throws IOException, SQLException
	{
		Object temp ;
		client.GetFileFromServer(new FileCommand(BookID,type));
		while (!client.GetGotMessag())
		{
			try{
				Thread.sleep(10);
				}
				catch(InterruptedException ex)
				{
					System.out.println("InterruptedException "+ex);
				}
		}
		if((temp=client.getResultObject())instanceof byte[])
			return (byte[])temp;
		else
		return null;
		
	}
	/**
	 * @author Sagi Entenberg
	 * @param bFile (all bytes for fill)
	 * @param fileDest (path )
	 */
	 public static void writeBytesToFile(byte[] bFile, String fileDest) {

	        try (FileOutputStream fileOuputStream = new FileOutputStream(fileDest)) {
	            fileOuputStream.write(bFile);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	    }
	
}
