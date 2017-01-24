package command;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import client.DBSQLhandler;
/**
 *  this class create file object for send to server
 * @author Sagi Entenberg
 * @author Kfir Girstein
 */
public class FileCommand implements Serializable{
	
	private byte[] BytesToFile;
	private String BookName;
	private String Type;
	
	/**
	 * Constructor
	 * @param file
	 * file to convert to bytes
	 * @param BookID
	 * the new book id to save in server
	 * @throws IOException
	 * exception if the process failed 
	 */
	public FileCommand(File file,int BookID) throws IOException
	{
		BytesToFile=DBSQLhandler.getBytesFromFile(file);
		this.BookName=Integer.toString(BookID);
		
	}
	/**
	 * Constructor
	 * @param BookID
	 * the book id for Download
	 * @param type
	 * type of the format to Download 
	 * @throws IOException
	 * exception if the process failed
	 */
	public FileCommand(int BookID,String type) throws IOException
	{
		this.BookName=Integer.toString(BookID);
		this.Type=type;
		BytesToFile=null;
	}
	
	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public byte[] getBytesToFile() {
		return BytesToFile;
	}
	public void setBytesToFile(byte[] bytesToFile) {
		BytesToFile = bytesToFile;
	}
	public String getBookName() {
		return BookName;
	}
	public void setBookName(String bookName) {
		BookName = bookName;
	}

}
