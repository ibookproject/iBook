package Book;

import command.DBtranslation;

public class Content extends DBtranslation {

	private String content;
	private int bookID;
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getBookID() {
		return bookID;
	}
	public void setBookID(int bookID) {
		this.bookID = bookID;
	}
	@Override
	public String getClassName() {
		return "Content";
	}
	@Override
	public String getAttributeToInsert() {
		return "(content,bookID)";
	}
	@Override
	public String getValToInsert() {
		return String.format("(\"%s\",\"%s\")",content,bookID);
	}
	
	
}
