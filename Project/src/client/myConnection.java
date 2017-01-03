package client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class myConnection {
	
	private String url;
	private String password;
	private String username;
	private Connection conn;
	
	public myConnection(String u,String p,String user)
	{
		url=u;
		password=p;
		username=user;
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

	public Connection getConnection() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		conn = DriverManager.getConnection(url, username, password);
		return conn;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	

}
