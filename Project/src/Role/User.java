package Role;

import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;

import client.DBgenericObject;
import command.DBtranslation;
/**
 *@author Sagi Entenberg 
 * 
 */
public class User extends DBtranslation implements UserStatus {
	private String userID;
	private String password;
	private String firstName;
	private String lastName;
	private int userStatus;
	private int privilege;
	private int subscriptionRequest;
	private int subscriptionMethod;
	private Date finishDateOfSubscription;
	/**
	 * 
	 * 
	 */
	//empty constructor
	public User(){
		super();
	}
	
	public User(String userID, String password, String firstName, String lastName, int privilege) 
	{
		setUserID(userID);
		setPassword(password);
		setFirstName(firstName);
		setLastName(lastName);
		setPrivilege(privilege);
		this.userStatus=DISCONNECTED;
	}

	
	public User(String userID, String password) {
		setUserID(userID);
		setPassword(password);
	}
	public User(String userID) {
		setUserID(userID);
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		if(userID==null||userID.equals(""))
			throw new InputMismatchException("forget to insert  userId");
		this.userID = userID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		if(password==null||password.equals(""))
			throw new InputMismatchException("forget to insert  password");
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(int userStatus) {
		switch(userStatus){
		case CONNECTED:
			this.userStatus=CONNECTED;
			break;
		case DISCONNECTED:
			this.userStatus=DISCONNECTED;
			break;
		case LOCK:
			this.userStatus=LOCK;
			break;
			default:
			throw new InputMismatchException("wrong status inserted");
			
		}
	}
	
	
	public int getSubscriptionMethod() {
		return subscriptionMethod;
	}

	public void setSubscriptionMethod(int subscriptionMethod) {
		this.subscriptionMethod = subscriptionMethod;
	}

	public void setPrivilege(int privilege) {
		switch(privilege)
		{
		case USER:
			this.privilege=USER;
			break;
		case READER:
			this.privilege=READER;
			break;
		case LIBARYWORKER:
			this.privilege=LIBARYWORKER;
			break;
		case QUALIFIEDEDITOR:
			this.privilege=QUALIFIEDEDITOR;
			break;
		case LIBRRIAN:
			this.privilege=LIBRRIAN;
			break;
		case MANAGER:
			this.privilege=MANAGER;
			break;
			default:
				throw new InputMismatchException("wrong privilege inserted");
			}
		}
	public int getSubscriptionRequest() {
		return subscriptionRequest;
	}

	public void setSubscriptionRequest(int subscriptionRequest) {
		switch(subscriptionRequest)
		{
		case NONE:
			this.subscriptionRequest=NONE;
			break;
		case YEARLY:
			this.subscriptionRequest=YEARLY;
			break;
		case MONTHLY:
			this.subscriptionRequest=MONTHLY;
			break;
		case SINGLE:
			this.subscriptionRequest=SINGLE;
		break;
		default:
			throw new InputMismatchException("wrong subscription inserted");
		}
	}
	
	public Date getFinishDateOfSubscription() {
		return finishDateOfSubscription;
	}

	public void setFinishDateOfSubscription(Date finishDateOfSubscription) {
		this.finishDateOfSubscription = finishDateOfSubscription;
	}

	public int getPriviliege(){
		return privilege;
	}
	@Override
	public String getClassName() {
		return "user";
	}

	@Override
	public String getAttributeToInsert() {
		return "(userID,password,firstName,lastName,privilege)";
	}

	@Override
	public String getValToInsert() {
		return String.format("(\"%s\",\"%s\",\"%s\",\"%s\",%d)",userID,password,firstName,lastName,privilege);
	}

	//convert array Which was obtained from DB to an actual User
	//need to implement in all tables.!!!
		
	public static ArrayList<User> convertBack(ArrayList<DBgenericObject> arr,String selectSentence){
			 ArrayList<User> convertedArr=new ArrayList<User>();
			 
			for(DBgenericObject ob:arr)
					convertedArr.add(convertDBObject(ob, selectSentence));//for each val in arr this convert back to book
			
			return convertedArr;
			
		}
	
	//this convert specific  DBgenericObject to Subject according the fromSentence
	private static User convertDBObject(DBgenericObject ob,String fromSentenceArray)
	{
		User recover=new User();
		 String[] fromSentence=fromSentenceArray.split(",");
		 for(int i=0;i<fromSentence.length;i++)
		 {
			 switch (fromSentence[i]) {
			case "userID":
				recover.setUserID((String)ob.getValtoArray(i));
				break;
			case "password":
				recover.setPassword((String)ob.getValtoArray(i));
				break;
			case "firstName":
				recover.setFirstName((String)ob.getValtoArray(i));
				break;
			case "lastName":
				recover.setLastName((String)ob.getValtoArray(i));
				break;
			case "userStatus":
				recover.setUserStatus((int)ob.getValtoArray(i));
				break;
			case "privilege":
				recover.setPrivilege((int)ob.getValtoArray(i));
				break;
			case "subscriptionRequest":
				recover.setSubscriptionRequest((int)ob.getValtoArray(i));
				break;
			case "finishDateOfSubscription":
				recover.setFinishDateOfSubscription((Date)ob.getValtoArray(i));
				break;	
			case "subscriptionMethod":
				recover.setSubscriptionMethod((int)ob.getValtoArray(i));
				break;

			default:
				throw new InputMismatchException("you have inserred wrong to search statment");
			 }//end switch
		 }//end for
		 return recover;
		
	}

	@Override
	public String toString() {
		return "User [userID=" + userID + ", password=" + password + ", firstName=" + firstName + ", lastName="
				+ lastName + ", userStatus=" + userStatus + ", privilege=" + privilege + "]";
	}


}//end class User
