package command;

public class joinObject {
	
	private String table1;
	private String table2;
	private String joinattribute;

	public joinObject(String table1, String table2, String joinattribute) {
		super();
		this.table1 = table1;
		this.table2 = table2;
		this.joinattribute = joinattribute;
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
		return joinattribute;
	}
	public void setJoinattribute(String joinattribute) {
		this.joinattribute = joinattribute;
	}
	
	@Override
	public String toString() {
		return table1+"."+joinattribute+"="+ table2 + "."+ joinattribute;
	}
	
}
