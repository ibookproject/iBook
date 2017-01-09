package client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class myConnection {
	
	private String url;
	private String password;
	private String username;
	private static Connection conn;
	//create an object of myConnection as singleTon
	private static  myConnection instance=new myConnection();
	
	//make the constructor private that this class cannot instantiantes any other way
	private myConnection(){};
	public static myConnection startConnection(String u,String p,String user)
	{
		instance.url=u;
		instance.password=p;
		instance.username=user;
		return instance;
	}
	public static Connection getMyConnection() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		conn = DriverManager.getConnection(instance.url, instance.username, instance.password);
		return conn;
	}
	public static void closeMyConnection() throws SQLException  {
		conn.close();
	}
	public String getUrl() {
		return url;
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}


	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	

}
