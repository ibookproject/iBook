package Book;

import command.DBtranslation;

public class Domain extends DBtranslation {

	private int DomainID;
	private String DomainName;
	
	
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
