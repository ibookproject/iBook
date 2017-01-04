package Book;

import command.DBtranslation;

public class Subject extends DBtranslation {
	int bookID;
	int domainID;
	String nameSubject;
	
	public int getBookID() {
		return bookID;
	}
	public void setBookID(int bookID) {
		this.bookID = bookID;
	}
	public String getNameSubject() {
		return nameSubject;
	}
	public void setNameSubject(String nameSubject) {
		this.nameSubject = nameSubject;
	}
	public int getDomainID() {
		return domainID;
	}
	public void setDomainID(int domainID) {
		this.domainID = domainID;
	}
	@Override
	public String getClassName() {
		return "Subject";
	}
	@Override
	public String getAttributeToInsert() {
		
		return "(bookID,domainID,nameSubject)";
	}
	@Override
	public String getValToInsert() {
		return String.format("(\"%s\",\"%s\",\"%s\")",bookID,domainID,nameSubject);
		
	}
	
}
