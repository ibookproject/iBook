package command;

import java.io.Serializable;


/**

 *  
 *@category
 *data base 
 *communication
 *
 *This class with all her heirs is to define in the server
 *witch action to do and to translte the command to real quary
 *this is a generic and abstrct class to make the whole proccess 
 *not specipic and can pluged in for all type of class
 *
 * @param <E>
 * the type of the entity to send,
 * the entity extend the class DBtranslation, to make the quary
 *  *  @author
 *  kfir Girstein
 */
public abstract class command<E extends DBtranslation> implements Serializable {

	private static final long serialVersionUID = 1L;
	protected E table;
	
	/**
	 * 
	 *  constractor to with a real paramter of the class,
	 *  only if needed to make comartion or need to take the real values 
	 *  of the type and to use specipic protocol of use
	 * @param
	 *  table1
	 *  the table/class that use
	 *  @author 
	 *  
	 *  kfir Girstein
	 *  
	 */
	public command(E table1){
		super();
		this.table=table1;
	}
	/**
	 * empty constractor, for commands without specipic protocol of use
	 *  @author kfir Girstein
	 */
	public command(){
		super();
	}
	/**
	 * 
	 *  @author 
	 *  kfir Girstein
	 * @return
	 * name of the table/class 
	 */
	public String getTable()
	{
		return table.getClassName();
	}
	/**
	 * 
	 *  @author 
	 *  kfir Girstein
	 * @param 
	 * table
	 * the table/class that use
	 */
	public void setTable(E table){
		this.table=table;
	}
}
