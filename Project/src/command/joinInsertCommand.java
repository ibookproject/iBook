package command;

import java.util.ArrayList;

public class joinInsertCommand<E extends DBtranslation> extends insertCommand<E> {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<joinObject> tablesToJoin;
	
	
	public joinInsertCommand(String table1, E temp) {
		super(table1, temp);
	
	}
	

}
