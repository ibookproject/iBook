package Book;

import java.util.ArrayList;
import java.util.InputMismatchException;

import Extras.Validation;
import client.DBgenericObject;
import command.DBtranslation;
/**
 * This class is the entity 'Subject',here saved all the fields like they presents in the DB
 * In this class there are all the getters and setters of the entity Subject.
 * When we use the DB-When we insert,delete and update from there we convert the data to be like it saved
 *  in DB and in this class it all happens.
 * @author Hen Saada
 *
 */
public class Subject extends DBtranslation {
	private int domainID;
	private String nameSubject;
	public static final int SUBJECTNAMESIZE = 30;

	
	/**
	 * Empty constructor of Subject
	 */
	public Subject() {
		super();
	}
	
	/**
	 *constructor of Subject with 2 parametres-domainID & nameSubject
	 */
	public Subject(int domainID, String nameSubject) {
		super();
		setDomainID(domainID);
		setNameSubject(nameSubject);
	}
	
	/**
	 *Getter of Domain ID
	 */
	public int getDomainID() {
		return domainID;
	}
	/**
	 *Getter of Subject Name 
	 */
	public String getNameSubject() {
		return nameSubject;
	}
	
	/**
	 * Setter of the Domain ID
	 * @param domainID Which is the domain ID we want to update/insert
	 */
	public void setDomainID(int domainID) {
		if (domainID < 0)
			throw new InputMismatchException("you have inserted negativ domain ID");
		this.domainID = domainID;
	}

	
	/**
	 * Setter of the NameSubject
	 * @param nameSubject Which is the name Subjectwe want to update/insert
	 */
	public void setNameSubject(String nameSubject) {
		if (nameSubject == null || nameSubject.equals("")
				|| Validation.NameValidation(nameSubject, SUBJECTNAMESIZE) == false)
			throw new InputMismatchException("you have insetred wrong subject name");
		this.nameSubject = nameSubject;
	}

	
	/**
	 *This method overrides the getClassName method and return the name of the class like we want
	 */
	@Override
	public String getClassName() {
		return "subject";
	}

	
	/**
	 *method who get attributes to insert to DB with the specific fields-suitable to the Subject Entity!
	 *@return string with the specific fields of the entity Subject
	 */
	@Override
	public String getAttributeToInsert() {

		return "(domainID,nameSubject)";
	}

	
	/**
	 *method who get values to insert to DB with the specific fields-suitable to the Subject Entity!
	 *@return String with specific format of Subject
	 */
	@Override
	public String getValToInsert() {
		return String.format("(\"%d\",\"%s\")", domainID, nameSubject);

	}

	/**
	 * convert array Which was obtained from DB to an actual Subject
	 * @param arr this is array of DBgenericObject which will convert to suitable to Subject entity
	 * @param fromSentence This is 'Select' query we want to get back.
	 * @return  ArrayList<Subject> which is the converted list the method create
	 * @author Kfir Girstein
	 */
	public static ArrayList<Subject> convertBack(ArrayList<DBgenericObject> arr, String fromSentence) {
		ArrayList<Subject> convertedArr = new ArrayList<Subject>();

		for (DBgenericObject ob : arr)
			convertedArr.add(convertDBObject(ob, fromSentence));// for each val
																// in arr this
																// convert back
																// to book
		return convertedArr;

	}

	/**
	 * This convert specific  DBgenericObject to Subject according the fromSentence
	 * @param ob This is a DBgenericObject which we need to get value from him- it is the object who return from DB
	 * @param fromSentenceArray This is 'Select' query we want to get back.
	 * @return Subject This Subject is ready with the parameters from DB
	 * @author Kfir Girstein
	 */
	private static Subject convertDBObject(DBgenericObject ob, String fromSentenceArray) {
		Subject recover = new Subject();
		String[] fromSentence = fromSentenceArray.split(",");
		for (int i = 0; i < fromSentence.length; i++) {
			switch (fromSentence[i]) {
			case "domainID":
				recover.setDomainID((int) ob.getValtoArray(i));
				break;
			case "nameSubject":
				recover.setNameSubject((String) ob.getValtoArray(i));
				break;

			default:
				throw new InputMismatchException("you have inserred wrong to search statment");
			}// end switch
		} // end for
		return recover;

	}
	/**
	 * This method overrides the method toString() and changed the string who Represents the entity Subject.
	 */
	@Override
	public String toString() {
		return String.format("%s", nameSubject);
	}
}
