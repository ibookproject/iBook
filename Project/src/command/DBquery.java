package command;

import java.io.Serializable;

public class DBquery implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String query;
	private String type;
	
	public DBquery(String q,String t)
	{
		this.query=q;
		this.type=t;
	}
	public String getQuery(){return query;}
	public String getType(){return type;}
	public String toString(){return query;}
}
