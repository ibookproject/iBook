package command;

public class joinObject  {
	
	private String table1;
	private String table2; //the second 
	private String joinAttribute;

	
	///need to insert to insert first elemnet,second element and the the attribute to join
	public joinObject(String table1, String table2, String joinAttribute) {
		super();
		this.table1 = table1;
		this.table2 = table2;
		this.joinAttribute = joinAttribute;
	}
	public String getTable1() {
		return table1;
	}
	public void setTable1(String table1) {
		this.table1 = table1;
	}
	public String getTable2() {
		return table2;
	}
	public void setTable2(String table2) {
		this.table2 = table2;
	}
	public String getJoinattribute() {
		return joinAttribute;
	}
	public void setJoinattribute(String joinAttribute) {
		this.joinAttribute = joinAttribute;
	}
	public String getJoinQuery()
	{
	 return	String.format("%s.%s=%s.%s",getTable1(),joinAttribute,getTable2(),joinAttribute);
	}
	
	@Override
	public String toString() {
		return table1+"."+joinAttribute+"="+ table2 + "."+ joinAttribute;
	}
	
}
