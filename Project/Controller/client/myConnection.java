package client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



/**
 * 
 * This object is a singleton of the connection to DataBase in the server
 * the connection must be only one instance for the server,
 * @category
 * Data base
 * Design patterns
 * 
 * 
 * @author kfir Girstein
 *
 */
public class myConnection {
	
	private String url;
	private String password;
	private String username;
	private static Connection conn;
	//create an object of myConnection as singleTon
	private static  myConnection instance=new myConnection();
	
	
	/**
	 * make the constructor private that this class cannot instantiantes any other way
	 */
	private myConnection(){};
	
	/**
	 * 
	 * @param url
	 * the url for the driver(JDBC)
	 * @param password
	 * the password to connected into the data base
	 * @param user
	 * the user name of the connection into the data base
	 * @return
	 */
	public static myConnection startConnection(String url,String password,String user)
	{
		instance.url=url;
		instance.password=password;
		instance.username=user;
		return instance;
	}
	/**
	 * Starting the connection to the data base
	 * @return connection instance to the data base
	 * @throws SQLException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	public static Connection getMyConnection() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		conn = DriverManager.getConnection(instance.url, instance.username, instance.password);
		return conn;
	}
	/**
	 * closing the connection when the server is closed
	 */
	public static void closeMyConnection() throws SQLException  {
		conn.close();
	}
	/**
	 * 
	 * @return the url of the connection driver
	 */
	public String getUrl() {
		return url;
	}
/**
 * 
 * @return the password of the mysql connection (user)
 */
	public String getPassword() {
		return password;
	}
/**
 * 
 * @return the user name of the mysql connection (user)
 */
	public String getUsername() {
		return username;
	}

/**
 * set the password of the mysql connection (user) to the singleton instance
 * @param password
 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * set the user name of the mysql connection (user) to the singleton instance
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	

}
