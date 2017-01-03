package command;

import java.io.Serializable;

public abstract class command<E extends DBtranslation> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String table;
	
	public command(String table1){
		this.table=table1;
	}
	public String getTable()
	{
		return table;
	}
	public void setTable(String td){
		this.table=td;
	}
}
