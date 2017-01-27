package Role;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;

import Extras.Validation;
import client.DBgenericObject;
import command.DBtranslation;

/**
 * This class is the entity 'User',here saved all the fields like they presents in the DB.
 * In this class there are all the getters and setters of the entity User.
 * When we use the DB-When we insert,delete and update from there we convert the data to be like it saved
 *  in DB and in this class it all happens.
 * @author Sagi  Entenberg
 *
 */
public class User extends DBtranslation implements UserStatus {
	private String userID;
	private String password;
	private String identityNumber;
	private String firstName;
	private String lastName;
	private int userStatus;
	private int privilege;
	private int subscriptionRequest;
	private int subscriptionMethod;
	private Date finishDateOfSubscription;

	/**
	 * Empty constructor of User
	 */
	public User() {
		super();
	}
	/**
	 *constructor of User with one parametr
	 */
	public User(String userID) {
		super();
		setUserID(userID);
	}

	public User(String userID, String password) {
		super();
		setUserID(userID);
		setPassword(password);
	}
	/**
	 *constructor of User with 6 parametres-userID & identityNumber &  password & firstName & lastName & privilege
	 *this ctor update the user status too
	 */
	public User(String userID, String identityNumber, String password, String firstName, String lastName,
			int privilege) {
		super();
		Date d = new Date();
		setUserID(userID);
		setPassword(password);
		setFirstName(firstName);
		setLastName(lastName);
		setPrivilege(privilege);
		setIdentityNumber(identityNumber);
		setSubscriptionMethod(NONE);
		setSubscriptionRequest(NONE);
		this.userStatus = DISCONNECTED;
		setFinishDateOfSubscription(d);
	}
	/**
	 *constructor of User with 10 parametres-userID & identityNumber &  password & firstName & lastName & privilege
	 * userStatus & subscriptionRequest & subscriptionMethod & finishDateOfSubscription
	 */
	public User(String userID, String password, String identityNumber, String firstName, String lastName,
			int userStatus, int privilege, int subscriptionRequest, int subscriptionMethod,
			Date finishDateOfSubscription) {
		this(userID, identityNumber, password, firstName, lastName, privilege);
		setUserStatus(userStatus);
		setSubscriptionRequest(subscriptionRequest);
		setSubscriptionMethod(subscriptionMethod);
		this.finishDateOfSubscription = finishDateOfSubscription;
	}
	/**
	 *Getter of User ID
	 */
	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		if (userID == null || userID.equals("") || Validation.regularValidation(userID) == false)
			throw new InputMismatchException("you have  inserted wrong userName");
		this.userID = userID;
	}
	/**
	 *Getter of User Password
	 */
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		if (password == null || password.equals("") || Validation.PasswordValidation(password) == false)
			throw new InputMismatchException("you have  inserted wrong password");
		this.password = password;
	}
	/**
	 *Getter of User Identity Number
	 */
	public String getIdentityNumber() {
		return identityNumber;
	}

	public void setIdentityNumber(String identityNumber) {
		if (identityNumber == null || identityNumber.equals("") || Validation.UserIDValidation(identityNumber) == false)
			throw new InputMismatchException("you have  inserted wrong identity Number");
		this.identityNumber = identityNumber;
	}
	/**
	 *Getter of User First Name
	 */
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		if (firstName == null || firstName.equals("")
				|| Validation.NameValidation(firstName, UserStatus.FNAMESIZE) == false)
			throw new InputMismatchException("you have inserted wrong first name");
		this.firstName = firstName;
	}
	/**
	 *Getter of User Last Name
	 */
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		if (lastName == null || lastName.equals("")
				|| Validation.NameValidation(lastName, UserStatus.LNAMESIZE) == false)
			throw new InputMismatchException("you have inserted wrong last name");
		this.lastName = lastName;
	}
	/**
	 *Getter of User Status
	 */
	public int getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(int userStatus) {
		switch (userStatus) {
		case CONNECTED:
			this.userStatus = CONNECTED;
			break;
		case DISCONNECTED:
			this.userStatus = DISCONNECTED;
			break;
		case LOCK:
			this.userStatus = LOCK;
			break;
		default:
			throw new InputMismatchException("wrong status inserted");

		}
	}
	/**
	 *Getter of User Subscription  Method
	 */
	public int getSubscriptionMethod() {
		return subscriptionMethod;
	}

	public void setSubscriptionMethod(int subscriptionMethod) {
		switch (subscriptionMethod) {
		case NONE:
			this.subscriptionMethod = NONE;
			break;
		case YEARLY:
			this.subscriptionMethod = YEARLY;
			break;
		case MONTHLY:
			this.subscriptionMethod = MONTHLY;
			break;
		case SINGLE:
			this.subscriptionMethod = SINGLE;
			break;
		default:
			throw new InputMismatchException("wrong subscription inserted");
		}

	}

	public void setPrivilege(int privilege) {
		switch (privilege) {
		case USER:
			this.privilege = USER;
			break;
		case READER:
			this.privilege = READER;
			break;
		case LIBARYWORKER:
			this.privilege = LIBARYWORKER;
			break;
		case QUALIFIEDEDITOR:
			this.privilege = QUALIFIEDEDITOR;
			break;
		case LIBRRIAN:
			this.privilege = LIBRRIAN;
			break;
		case MANAGER:
			this.privilege = MANAGER;
			break;
		default:
			throw new InputMismatchException("wrong privilege inserted");
		}
	}
	/**
	 *Getter of User Subscription Request
	 */
	public int getSubscriptionRequest() {
		return subscriptionRequest;
	}

	public void setSubscriptionRequest(int subscriptionRequest) {
		switch (subscriptionRequest) {
		case NONE:
			this.subscriptionRequest = NONE;
			break;
		case YEARLY:
			this.subscriptionRequest = YEARLY;
			break;
		case MONTHLY:
			this.subscriptionRequest = MONTHLY;
			break;
		case SINGLE:
			this.subscriptionRequest = SINGLE;
			break;
		default:
			throw new InputMismatchException("wrong subscription inserted");
		}
	}
	/**
	 *Getter of User Finish Date Of Subscription
	 */
	public Date getFinishDateOfSubscription() {
		return finishDateOfSubscription;
	}

	public void setFinishDateOfSubscription(Date finishDateOfSubscription) {
		if (finishDateOfSubscription /*!*/== null/* && finishDateOfSubscription.before(Calendar.getInstance().getTime())*/)// almog change it becouse i think this check is not right.
			throw new InputMismatchException("you insert wrong date");

		this.finishDateOfSubscription = finishDateOfSubscription;
	}
	/**
	 *Getter of User Priviliege
	 */
	public int getPriviliege() {
		return privilege;
	}
	/**
	 *Overrides the method of get Class Name
	 */
	@Override
	public String getClassName() {
		return "user";
	}
	/**
	 *method who get attributes to insert to DB with the specific fields-suitable to the User Entity!
	 *@return string with the specific fields of the entity User
	 */
	@Override
	public String getAttributeToInsert() {
		return "(userID,password,identityNumber,firstName,lastName,privilege,subscriptionMethod,finishDateOfSubscription)";
	}
	/**
	 *method who get values to insert to DB with the specific fields-suitable to the User Entity!
	 *@return String with specific format of User
	 */
	@Override
	public String getValToInsert() {
		
		return String.format("(\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",%d,%d,\"%s\")", userID, password, identityNumber, firstName,
				lastName, privilege, subscriptionMethod, new SimpleDateFormat("yyyy/MM/dd").format(finishDateOfSubscription));
	}

	/**
	 * convert array Which was obtained from DB to an actual User
	 * @param arr this is array of DBgenericObject which will convert to suitable to User entity
	 * @param fromSentence This is 'Select' query we want to get back.
	 * @return  ArrayList<User> which is the converted list the method create
	 */
	public static ArrayList<User> convertBack(ArrayList<DBgenericObject> arr, String selectSentence) {
		ArrayList<User> convertedArr = new ArrayList<User>();

		for (DBgenericObject ob : arr)
			convertedArr.add(convertDBObject(ob, selectSentence));// for each//
																	// val in//
																	// arr
																	// this//
																	// back to//
																	// book
		return convertedArr;

	}

	/**
	 * This convert specific  DBgenericObject to User according the fromSentence
	 * @param ob This is a DBgenericObject which we need to get value from him- it is the object who return from DB
	 * @param fromSentenceArray This is 'Select' query we want to get back.
	 * @return User This User is ready with the parameters from DB
	 */
	private static User convertDBObject(DBgenericObject ob, String fromSentenceArray) {
		User recover = new User();
		String[] fromSentence = fromSentenceArray.split(",");
		for (int i = 0; i < fromSentence.length; i++) {
			switch (fromSentence[i]) {
			case "userID":
				recover.setUserID((String) ob.getValtoArray(i));
				break;
			case "password":
				recover.setPassword((String) ob.getValtoArray(i));
				break;
			case "firstName":
				recover.setFirstName((String) ob.getValtoArray(i));
				break;
			case "lastName":
				recover.setLastName((String) ob.getValtoArray(i));
				break;
			case "userStatus":
				recover.setUserStatus((int) ob.getValtoArray(i));
				break;
			case "privilege":
				recover.setPrivilege((int) ob.getValtoArray(i));
				break;
			case "subscriptionRequest":
				recover.setSubscriptionRequest((int) ob.getValtoArray(i));
				break;
			case "finishDateOfSubscription":
				recover.setFinishDateOfSubscription((Date) ob.getValtoArray(i));
				break;
			case "subscriptionMethod":
				recover.setSubscriptionMethod((int) ob.getValtoArray(i));
				break;
			case "identityNumber":
				recover.setIdentityNumber((String) ob.getValtoArray(i));
				break;
			default:
				throw new InputMismatchException("you have inserred wrong to search statment");
			}// end switch
		} // end for
		return recover;

	}
	/**
	 * This method overrides the method toString() and changed the string who Represents the entity User.
	 */
	@Override
	public String toString() {
		return "User [userID=" + userID + ", password=" + password + ", firstName=" + firstName + ", lastName="
				+ lastName + ", userStatus=" + userStatus + ", privilege=" + privilege + "]";
	}

}// end class User
