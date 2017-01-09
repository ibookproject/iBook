package command;

public class joinObject<E extends DBtranslation, T extends DBtranslation> {
	
	private E table1;
	private T table2; //the second 
	private String joinAttribute;

	
	///need to insert to insert first elemnet,second element and the the attribute to join
	public joinObject(E table1, T table2, String joinAttribute) {
		super();
		this.table1 = table1;
		this.table2 = table2;
		this.joinAttribute = joinAttribute;
	}
	public String getTable1() {
		return table1.getClassName();
	}
	public void setTable1(E table1) {
		this.table1 = table1;
	}
	public String getTable2() {
		return table2.getClassName();
	}
	public void setTable2(T table2) {
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
