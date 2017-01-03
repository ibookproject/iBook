package Role;

import java.util.InputMismatchException;

import command.DBtranslation;

public class user extends DBtranslation implements UserStatus {
	private String userID;
	private String password;
	private String firstName;
	private String lastName;
	private int userStatus;
	private int privilege;
	
	public user(String userID, String password, String firstName, String lastName, int privilege) {
		setUserID(userID);
		setPassword(password);
		setFirstName(firstName);
		setLastName(lastName);
		setPrivilege(privilege);
		this.userStatus=DISCONNECTED;
	}

	public user(String userID, String password) {
		setUserID(userID);
		setPassword(password);
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
	}
