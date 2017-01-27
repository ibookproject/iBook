package Book;

import java.util.ArrayList;
import java.util.InputMismatchException;

import Extras.Validation;
import client.DBgenericObject;
import command.DBtranslation;
/**
 * This class is the entity 'Domain',here saved all the fields like they presents in the DB
 * In this class there are all the getters and setters of the entity Domain.
 * When we use the DB-When we insert,delete and update from there we convert the data to be like it saved
 *  in DB and in this class it all happens.
 * @author Coral Carmeli
 *
 */
public class Domain extends DBtranslation {

	private int DomainID;
	private String DomainName;
	public static final int DNAMESIZE = 30;

	/**
	 * Empty constructor of Domain
	 */
	public Domain() {
		super();
	}
	/**
	 *constructor of Domain with one parametr
	 */
	public Domain(String domainName) {
		super();
		setDomainName(domainName);
	}
	/**
	 *constructor of Domain with 2 parametres-domainID & domainName
	 */
	public Domain(int domainID, String domainName) {
		this(domainName);
		setDomainID(domainID);
	}
	/**
	 *Getter of Domain ID
	 */
	public int getDomainID() {
		return DomainID;
	}

	public void setDomainID(int domainID) {
		if (domainID < 0)
			throw new InputMismatchException("you have  inserted wrong domain ID");
		DomainID = domainID;
	}
	/**
	 *Getter of Domain ID
	 */
	public String getDomainName() {
		return DomainName;
	}

	public void setDomainName(String domainName) {
		if (domainName == null || domainName.equals("") || Validation.NameValidation(domainName, DNAMESIZE) == false)
			throw new InputMismatchException("you have  inserted wrong domain name");
		DomainName = domainName;
	}
	/**
	 *This method overrides the getClassName method and return the name of the class like we want
	 */
	@Override
	public String getClassName() {
		return "Domain";
	}
	/**
	 *method who get attributes to insert to DB with the specific fields-suitable to the Domain Entity!
	 *@return string with the specific fields of the entity Domain
	 */
	@Override
	public String getAttributeToInsert() {

		return "(DomainID,DomainName)";
	}
	/**
	 *method who get values to insert to DB with the specific fields-suitable to the Domain Entity!
	 *@return String with specific format of Domain
	 */
	@Override
	public String getValToInsert() {
		return String.format("(\"%s\",\"%s\")", DomainID, DomainName);
	}

	/**
	 * convert array Which was obtained from DB to an actual Domain
	 * @param arr this is array of DBgenericObject which will convert to suitable to Domain entity
	 * @param fromSentence This is 'Select' query we want to get back.
	 * @return  ArrayList<Domain> which is the converted list the method create
	 * @author Kfir Girstein
	 */
	public static ArrayList<Domain> convertBack(ArrayList<DBgenericObject> arr, String fromSentence) {
		ArrayList<Domain> convertedArr = new ArrayList<Domain>();

		for (DBgenericObject ob : arr)
			convertedArr.add(convertDBObject(ob, fromSentence));// for each val
																// in arr this
																// convert back
																// to book
		return convertedArr;

	}

	/**
	 * This convert specific  DBgenericObject to Domain according the fromSentence
	 * @param ob This is a DBgenericObject which we need to get value from him- it is the object who return from DB
	 * @param fromSentenceArray This is 'Select' query we want to get back.
	 * @return Domain This Domain is ready with the parameters from DB
	 * @author Kfir Girstein
	 */
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
	/**
	 * This method overrides the method toString() and changed the string who Represents the entity Domain.
	 */
	@Override
	public String toString() {
		// return "Domain [DomainID=" + DomainID + ", DomainName=" + DomainName
		// + "]";
		return String.format("%s", DomainName);
	}

}
