package Book;

import java.util.ArrayList;
import java.util.InputMismatchException;

import Panels.Validation;
import client.DBgenericObject;
import command.DBtranslation;

public class Domain extends DBtranslation {

	private int DomainID;
	private String DomainName;
	public static final int DNAMESIZE = 30;

	public Domain() {
		super();
	}

	public Domain(String domainName) {
		super();
		setDomainName(domainName);
	}

	public Domain(int domainID, String domainName) {
		this(domainName);
		setDomainID(domainID);
	}

	public int getDomainID() {
		return DomainID;
	}

	public void setDomainID(int domainID) {
		if (domainID < 0)
			throw new InputMismatchException("you have  inserted wrong domain ID");
		DomainID = domainID;
	}

	public String getDomainName() {
		return DomainName;
	}

	public void setDomainName(String domainName) {
		if (domainName == null || domainName.equals("") || Validation.NameValidation(domainName, DNAMESIZE) == false)
			throw new InputMismatchException("you have  inserted wrong domain name");
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
		return String.format("(\"%s\",\"%s\")", DomainID, DomainName);
	}

	// convert array Which was obtained from DB to an actual Domain
	// need to implement in all tables.!!!
	public static ArrayList<Domain> convertBack(ArrayList<DBgenericObject> arr, String fromSentence) {
		ArrayList<Domain> convertedArr = new ArrayList<Domain>();

		for (DBgenericObject ob : arr)
			convertedArr.add(convertDBObject(ob, fromSentence));// for each val
																// in arr this
																// convert back
																// to book

		return convertedArr;

	}

	// this convert specific DBgenericObject to Domain according the
	// fromSentence
	private static Domain convertDBObject(DBgenericObject ob, String fromSentenceArray) {
		Domain recover = new Domain();
		String[] fromSentence = fromSentenceArray.split(",");
		for (int i = 0; i < fromSentence.length; i++) {
			switch (fromSentence[i]) {
			case "DomainID":
				recover.setDomainID((int) ob.getValtoArray(i));
				break;
			case "DomainName":
				recover.setDomainName((String) ob.getValtoArray(i));
				break;
			default:
				throw new InputMismatchException("you have inserred wrong to search statment");
			}// end switch
		} // end for
		return recover;

	}

	@Override
	public String toString() {
		// return "Domain [DomainID=" + DomainID + ", DomainName=" + DomainName
		// + "]";
		return String.format("%s", DomainName);
	}

}
