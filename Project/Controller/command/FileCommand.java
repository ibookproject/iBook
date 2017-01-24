package command;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import client.DBSQLhandler;

public class FileCommand implements Serializable{
	
	private byte[] BytesToFile;
	private String BookName;
	private String Type;
	
	
	public FileCommand(File f,int BookID) throws IOException
	{
		BytesToFile=DBSQLhandler.getBytesFromFile(f);
		this.BookName=Integer.toString(BookID);
		
	}
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
