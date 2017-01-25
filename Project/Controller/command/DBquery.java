package command;

import java.io.Serializable;

/**
 * 
 *an Object that sent the quary to the server with the type of the quary 
 *@Category
 *Data Base
 *Client Server
 *  @author kfir Girstein
 *   
 */
public class DBquery implements Serializable{


	private static final long serialVersionUID = 1L;
	private String query;
	private String type;
	
	/**
	 * 
	 *  @author kfir Girstein
	 * @param query
	 * @param type
	 */
	public DBquery(String query,String type)
	{
		this.query=query;
		this.type=type;
	}
	/**
	 * 
	 *  @author kfir Girstein
	 * @return the quary to send
	 */
	public String getQuery(){return query;}
	/**
	 * 
	 *  @author kfir Girstein
	 * @return Type of the quary
	 */
	public String getType(){return type;}
	public String toString(){return query;}
}
