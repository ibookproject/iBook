package Role;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;

import Extras.Validation;
import client.DBgenericObject;
import command.DBtranslation;

public class LibraryWorker extends DBtranslation implements UserStatus {
	private String workerID;
	private int role;
	private String department;
	private String email;
	private String firstName;
	private String lastName;
	
	
	public LibraryWorker() {
		super();
	}
	

	public LibraryWorker(String workerID, int role, String department, String email, String firstName, String lastName) {
		super();
		setWorkerID(workerID);
		setDepartment(department);
		setEmail(email);
		setRole(role);
		}

	public String getWorkerID() {
		return workerID;
	}

	public void setWorkerID(String workerID) {
		if (workerID == null || workerID.equals("") || Validation.regularValidation(workerID) == false)
			throw new InputMismatchException("you have inserted wrong userName");
		this.workerID = workerID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		if (firstName == null || firstName.equals("") || Validation.NameValidation(firstName , 20) == false)
			throw new InputMismatchException("you have  inserted wrong firstName");
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		if (lastName == null || lastName.equals("") || Validation.NameValidation(lastName , 20) == false)
			throw new InputMismatchException("you have  inserted wrong lastName");
		this.lastName = lastName;
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
		return "libraryworker";
	}

	@Override
	public String getAttributeToInsert() {
		return "(workerID,department,firstName,lastName,role,email)";
	}

	@Override
	public String getValToInsert() {
		
		return String.format("(\"%s\",\"%s\",\"%s\",\"%s\",\"%d\",\"%s\")", workerID, department, firstName, lastName, role, email);
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
			case "workerID":
				recover.setWorkerID((String) ob.getValtoArray(i));
				break;
			case "department":
				recover.setDepartment((String) ob.getValtoArray(i));
				break;
			case "firstName":
				recover.setFirstName((String) ob.getValtoArray(i));
				break;
			case "lastName":
				recover.setLastName((String) ob.getValtoArray(i));
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
		return "User [workerID=" + workerID + ", department=" + department + ", email=" + email + ", role=" + role + "]";
	}


}
