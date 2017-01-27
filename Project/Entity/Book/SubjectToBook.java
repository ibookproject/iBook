package Book;

import java.util.ArrayList;
import java.util.InputMismatchException;

import Extras.Validation;
import client.DBgenericObject;
import command.DBtranslation;
/**
 * This class is the entity 'SubjectToBook',here saved all the fields like they presents in the DB
 * In this class there are all the getters and setters of the entity SubjectToBook.
 * When we use the DB-When we insert,delete and update from there we convert the data to be like it saved
 *  in DB and in this class it all happens.
 * @author Hen Saada
 *
 */
public class SubjectToBook extends DBtranslation {
	private int bookID;
	private int domainID;
	private String nameSubject;

	/**
	 * Empty constructor of SubjectToBook
	 */
	public SubjectToBook() {
		super();
	}
	/**
	 *constructor of Subject with 2 parametres-domainID & nameSubject
	 */
	public SubjectToBook(int domainID, String nameSubject) {
		super();
		setDomainID(domainID);
		setNameSubject(nameSubject);
	}
	/**
	 *constructor of Subject with 3 parametres-domainID & nameSubject & bookID
	 */
	public SubjectToBook(int bookID, int domainID, String nameSubject) {
		this(domainID, nameSubject);
		setBookID(bookID);
	}
	/**
	 *Getter of book ID
	 */
	public int getBookID() {
		return bookID;
	}
	/**
	 * Setter of Book ID
	 * @param bookID which is the ID we want to update/insert
	 */
	public void setBookID(int bookID) {
		if (bookID < 0)
			throw new InputMismatchException("you have  inserted wrong book ID");
		this.bookID = bookID;
	}
	/**
	 *Getter of Subject Name 
	 */
	public String getNameSubject() {
		return nameSubject;
	}
	/**
	 * Setter of the NameSubject
	 * @param nameSubject Which is the name Subjectwe want to update/insert
	 */
	public void setNameSubject(String nameSubject) {
		if (nameSubject == null || nameSubject.equals("")
				|| Validation.NameValidation(nameSubject, Subject.SUBJECTNAMESIZE) == false)
			throw new InputMismatchException("you have insetred wrong subject name");
		this.nameSubject = nameSubject;
	}
	/**
	 *Getter of Domain ID
	 */
	public int getDomainID() {
		return domainID;
	}
	/**
	 * Setter of the Domain ID
	 * @param domainID Which is the domain ID we want to update/insert
	 */
	public void setDomainID(int domainID) {
		if (domainID < 0)
			throw new InputMismatchException("you have  inserted wrong domain ID");
		this.domainID = domainID;
	}
	/**
	 *This method overrides the getClassName method and return the name of the class like we want
	 */
	@Override
	public String getClassName() {
		return "subjecttobook";
	}
	/**
	 *method who get attributes to insert to DB with the specific fields-suitable to the SubjectToBook Entity!
	 *@return string with the specific fields of the entity SubjectToBook
	 */
	@Override
	public String getAttributeToInsert() {

		return "(bookID,domainID,nameSubject)";
	}

	
	/**
	 *method who get values to insert to DB with the specific fields-suitable to the SubjectToBook Entity!
	 *@return String with specific format of SubjectToBook
	 */
	@Override
	public String getValToInsert() {
		return String.format("(\"%s\",\"%s\",\"%s\")", bookID, domainID, nameSubject);

	}

	/**
	 * convert array Which was obtained from DB to an actual SubjectToBook
	 * @param arr this is array of DBgenericObject which will convert to suitable to SubjectToBook entity
	 * @param fromSentence This is 'Select' query we want to get back.
	 * @return  ArrayList<SubjectToBook> which is the converted list the method create
	 * @author Kfir Girstein
	 */
	public static ArrayList<SubjectToBook> convertBack(ArrayList<DBgenericObject> arr, String fromSentence) {
		ArrayList<SubjectToBook> convertedArr = new ArrayList<SubjectToBook>();

		for (DBgenericObject ob : arr)
			convertedArr.add(convertDBObject(ob, fromSentence));// for each val
																// in arr this
																// convert back
																// to book

		return convertedArr;

	}

	/**
	 * This convert specific  DBgenericObject to SubjectToBook according the fromSentence
	 * @param ob This is a DBgenericObject which we need to get value from him- it is the object who return from DB
	 * @param fromSentenceArray This is 'Select' query we want to get back.
	 * @return SubjectToBook This SubjectToBook is ready with the parameters from DB
	 * @author Kfir Girstein
	 */
	private static SubjectToBook convertDBObject(DBgenericObject ob, String fromSentenceArray) {
		SubjectToBook recover = new SubjectToBook();
		String[] fromSentence = fromSentenceArray.split(",");
		for (int i = 0; i < fromSentence.length; i++) {
			switch (fromSentence[i]) {
			case "bookID":
				recover.setBookID((int) ob.getValtoArray(i));
				break;
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
	 * This method overrides the method toString() and changed the string who Represents the entity SubjectToBook.
	 */
	@Override
	public String toString() {
		return "Subject [bookID=" + bookID + ", domainID=" + domainID + ", nameSubject=" + nameSubject + "]";
	}

}
