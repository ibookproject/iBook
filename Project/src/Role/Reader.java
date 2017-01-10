package Role;

import java.util.InputMismatchException;

public class Reader extends User{

	private int subscriptionMethod;
	public Reader(String userID, String password, String firstName, String lastName, int privilege,int subscriptionMethod) {
		super(userID, password, firstName, lastName, privilege);
		setSubscriptionMethod(subscriptionMethod);
	}
	public int getSubscriptionMethod() {
		return subscriptionMethod;
	}
	public void setSubscriptionMethod(int subscriptionMethod) {
		switch(subscriptionMethod)
		{
		case NONE:
			this.subscriptionMethod=NONE;
			break;
		case YEARLY:
			this.subscriptionMethod=YEARLY;
			break;
		case MONTHLY:
			this.subscriptionMethod=MONTHLY;
			break;
		case SINGLE:
			this.subscriptionMethod=SINGLE;
		break;
		default:
			throw new InputMismatchException("wrong subscription inserted");
		}
	}

}
