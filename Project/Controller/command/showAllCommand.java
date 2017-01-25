package command;

/**
*@Category
*data base 
*communication
*
*@Extends
*Command<E>
*
*this class is do define the action to get all the table form the DB
*this is equal to make to quary: "SELECT * FROM table"
* @param <E>
* the type of the entity to send,
* the entity extend the class DBtranslation, to make the quary
*@author
*  kfir Girstein
**/
public class showAllCommand<E extends DBtranslation> extends command<E> {

	private static final long serialVersionUID = 1L;
	/**
	 * constractor with the actual object
	 * 
	 * @param
	 *  table1
	  * the actual class/table to make the quary on
	 *  @author
	 *   kfir Girstein
	 */

	public showAllCommand(E table1) {
		super(table1);
	}
	
	/**
	 * constractor to make quary,
	 * without specipic values
	 *  @author
	 *   kfir Girstein
	 */
	public showAllCommand() {
		super();
	}

}
