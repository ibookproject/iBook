package Book;

import java.util.ArrayList;
import java.util.InputMismatchException;

import Panels.Validation;
import client.DBgenericObject;
import command.DBtranslation;

public class Subject extends DBtranslation {
	private int domainID;
	private String nameSubject;
	public static final int SUBJECTNAMESIZE = 30;

	public Subject(int domainID, String nameSubject) {
		super();
		setDomainID(domainID);
		setNameSubject(nameSubject);
	}

	// empty constactor
	public Subject() {
		super();
	}

	public int getDomainID() {
		return domainID;
	}

	public String getNameSubject() {
		return nameSubject;
	}

	public void setDomainID(int domainID) {
		if (domainID < 0)
			throw new InputMismatchException("you have inserted negativ domain ID");
		this.domainID = domainID;
	}

	public void setNameSubject(String nameSubject) {
		if (nameSubject == null || nameSubject.equals("")
				|| Validation.NameValidation(nameSubject, SUBJECTNAMESIZE) == false)
			throw new InputMismatchException("you have insetred wrong subject name");
		this.nameSubject = nameSubject;
	}

	@Override
	public String getClassName() {
		return "subject";
	}

	@Override
	public String getAttributeToInsert() {

		return "(domainID,nameSubject)";
	}

	@Override
	public String getValToInsert() {
		return String.format("(\"%d\",\"%s\")", domainID, nameSubject);

	}

	// convert array Which was obtained from DB to an actual Subject
	// need to implement in all tables.!!!

	public static ArrayList<Subject> convertBack(ArrayList<DBgenericObject> arr, String fromSentence) {
		ArrayList<Subject> convertedArr = new ArrayList<Subject>();

		for (DBgenericObject ob : arr)
			convertedArr.add(convertDBObject(ob, fromSentence));// for each val
																// in arr this
																// convert back
																// to book

		return convertedArr;

	}

	// this convert specific DBgenericObject to Subject according the
	// fromSentence
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

	@Override
	public String toString() {
		// return "Subject [domainID=" + domainID + ", nameSubject=" +
		// nameSubject + "]";
		return String.format("%s", nameSubject);
	}
}
