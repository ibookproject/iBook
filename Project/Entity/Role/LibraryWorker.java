package Role;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;

import Extras.Validation;
import client.DBgenericObject;
import command.DBtranslation;

public class LibraryWorker extends DBtranslation implements UserStatus {
	private String userID;
	private int role;
	private String department;
	private String email;
	
	
	public LibraryWorker() {
		super();
	}
	

	public LibraryWorker(String userID, int role, String department, String email) {
		super();
		setUserID(userID);
		setDepartment(department);
		setEmail(email);
		setRole(role);
		}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		if (userID == null || userID.equals("") || Validation.regularValidation(userID) == false)
			throw new InputMismatchException("you have inserted wrong userName");
		this.userID = userID;
	}

	public String getdepartment() {
		return department;
	}

	public void setDepartment(String department) {
		if (department == null || department.equals("") || Validation.NameValidation(department , 20) == false)
			throw new InputMismatchException("you have  inserted wrong department");
		this.department = department;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		if (role > 6 || role < 3)
			throw new InputMismatchException("you have  inserted wrong identity Number");
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if (email == null || email.equals("")
				|| Validation.EmailValidation(email) == false)
			throw new InputMismatchException("you have inserted wrong email");
		this.email = email;
	}

	@Override
	public String getClassName() {
		return "user";
	}

	@Override
	public String getAttributeToInsert() {
		return "(userID,department,role,email)";
	}

	@Override
	public String getValToInsert() {
		
		return String.format("(\"%s\",\"%s\",\"%d\",\"%s\")", userID, department, role, email);
	}

	// convert array Which was obtained from DB to an actual User
	// need to implement in all tables.!!!

	public static ArrayList<LibraryWorker> convertBack(ArrayList<DBgenericObject> arr, String selectSentence) {
		ArrayList<LibraryWorker> convertedArr = new ArrayList<LibraryWorker>();

		for (DBgenericObject ob : arr)
			convertedArr.add(convertDBObject(ob, selectSentence));// for each//
																	// val in//
																	// arr
																	// this//
																	// back to//
																	// book
		return convertedArr;

	}

	// this convert specific DBgenericObject to Subject according the
	// fromSentence
	private static LibraryWorker convertDBObject(DBgenericObject ob, String fromSentenceArray) {
		LibraryWorker recover = new LibraryWorker();
		String[] fromSentence = fromSentenceArray.split(",");
		for (int i = 0; i < fromSentence.length; i++) {
			switch (fromSentence[i]) {
			case "userID":
				recover.setUserID((String) ob.getValtoArray(i));
				break;
			case "department":
				recover.setDepartment((String) ob.getValtoArray(i));
				break;
			case "email":
				recover.setEmail((String) ob.getValtoArray(i));
				break;
			case "role":
				recover.setRole((int) ob.getValtoArray(i));
				break;
			default:
				throw new InputMismatchException("you have inserred wrong to search statment");
			}// end switch
		} // end for
		return recover;

	}

	@Override
	public String toString() {
		return "User [userID=" + userID + ", department=" + department + ", email=" + email + ", role=" + role + "]";
	}


}
