package Role;

import java.util.InputMismatchException;

public class User implements Privilege {
	private String userID;
	private String password;
	private String firstName;
	private String lastName;
	private int userStatus;
	
	public User(String userID, String password, String firstName, String lastName, int userStatus) {
		setUserID(userID);
		setPassword(password);
		setFirstName(firstName);
		setLastName(lastName);
		setUserStatus(userStatus);
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
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
		switch(userStatus)
		{
		case USER:
			userStatus=USER;
			break;
		case READER:
			userStatus=READER;
			break;
		case LIBARYWORKER:
			userStatus=LIBARYWORKER;
			break;
		case QUALIFIEDEDITOR:
			userStatus=QUALIFIEDEDITOR;
			break;
		case LIBRRIAN:
			userStatus=LIBRRIAN;
			break;
		case MANAGER:
			userStatus=MANAGER;
			break;
			default:
				throw new InputMismatchException("wrong status inserted");
			}
		}
	}
