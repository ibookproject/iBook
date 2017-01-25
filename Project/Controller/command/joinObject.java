package command;

/**
 * Object to hold the tables to integrate and there attribute to integrate.
 * this class is makes the join part in the quary
 *  @author kfir Girstein
 *
 */
public class joinObject  {
	
	private String table1;
	private String table2; //the second 
	private String joinAttribute;

	
	/**
	 * <h3>constractor</h3>
	 * need to insert to insert first elemnet,second element and the the attribute to join
	 *  @author kfir Girstein
	 * @param table1
	 * String name form the first table
	 * @param table2
	 * String name form the second table to integrate
	 * @param joinAttribute
	 * String name of the attribute the tables are will be join
	 */
	public joinObject(String table1, String table2, String joinAttribute) {
		super();
		this.table1 = table1;
		this.table2 = table2;
		this.joinAttribute = joinAttribute;
	}
	/**
	 * 
	 *  @author kfir Girstein
	 * @return the name of the first table
	 */
	public String getTable1() {
		return table1;
	}
	/**
	 * 
	 *  @author kfir Girstein
	 * @param table1
	 * 
	 * ,the first table, change in the class table
	  */
	public void setTable1(String table1) {
		this.table1 = table1;
	}
	/**
	 * 
	 *  @author kfir Girstein
	 *@return the name of the second table
	 */
	public String getTable2() {
		return table2;
	}
	/**
	 * 
	 *  @author kfir Girstein
	 *@param table2
	 *
	 *,the second table, change in the class table
	 */
	public void setTable2(String table2) {
		this.table2 = table2;
	}
	
	/**
	 * 
	 *  @author kfir Girstein
	 * @return the attribute to integrate between the tables
	 */
	public String getJoinattribute() {
		return joinAttribute;
	}
	public void setJoinattribute(String joinAttribute) {
		this.joinAttribute = joinAttribute;
	}
	
	/**
	 * 
	 *  @author kfir Girstein
	 * @return the join condition for the quary
	 */
	public String getJoinQuery()
	{
	 return	String.format("%s.%s=%s.%s",getTable1(),joinAttribute,getTable2(),joinAttribute);
	}
	
	@Override
	public String toString() {
		return table1+"."+joinAttribute+"="+ table2 + "."+ joinAttribute;
	}
	
}
