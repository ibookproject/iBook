package command;

/**
*@Category
*data base 
*communication
*
*@Extends
*{@link command}
*
*this class is do define the insert actions and to get the generic parameters to make 
*a insert quary
* @param <E>
* the type of the entity to send,
* the entity extend the class DBtranslation, to make the quary
* @author
*  kfir Girstein
**/

public class insertCommand<E extends DBtranslation> extends command<E> {

	//generic sql command insert
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 *  @author kfir Girstein
	 * @param table
	 *  the actual class/table to make the quary on
	 *  <b>
	 *  the table mast extends DBtransltion
	 * </b> 
	 */
	public insertCommand(E table) {
		super(table);
	}
	
	/**
	 * 
	 *  @author kfir Girstein
	 * @return
	 * the object the quary is about
	 * to make the actual quary
	 */
	public E getToInsert()
	{
		return table;
	}

}
