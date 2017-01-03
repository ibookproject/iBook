package command;

import java.io.Serializable;

public abstract class command<E extends DBtranslation> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected E table;
	
	public command(E table1){
		this.table=table1;
	}
	public command(){
	}
	
	public String getTable()
	{
		return table.getClassName();
	}
	public void setTable(E td){
		this.table=td;
	}
}
