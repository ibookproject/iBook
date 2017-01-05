package Book;

import command.DBtranslation;

public class Domain extends DBtranslation {
	
	private int DomainID;
	private String DomainName;
	
	public Domain(int domainID, String domainName) {
		super();
		DomainID = domainID;
		DomainName = domainName;
	}
	
	public Domain( String domainName) {
		super();
		DomainName = domainName;
	}
	
	public int getDomainID() {
		return DomainID;
	}
	public void setDomainID(int domainID) {
		DomainID = domainID;
	}
	public String getDomainName() {
		return DomainName;
	}
	public void setDomainName(String domainName) {
		DomainName = domainName;
	}
	@Override
	public String getClassName() {
		return "Domain";
	}
	@Override
	public String getAttributeToInsert() {
		
		return "(DomainID,DomainName)";
	}
	@Override
	public String getValToInsert() {
		return String.format("(\"%s\",\"%s\")",DomainID,DomainName);
	}
	
}
