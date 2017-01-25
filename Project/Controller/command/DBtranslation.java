package command;


/**
 * <h3>Abstract class</h3>
 * Class to define the classes of the tables in the Data Base
 * this class is like an interface to generic objects
 *  @author kfir Girstein
 *
 */
public abstract class DBtranslation {

	/**
	 * 
	 *  @author kfir Girstein
	 * @return the table name of the class in the Data Base
	 */
	public abstract String getClassName();
/**
 * 
 *  @author kfir Girstein
 * @return the attribute format need to insert at the quary
 */
	public abstract String getAttributeToInsert();// get coulms of the table
/**
 * 
 *  @author kfir Girstein
 * @return the actual values of the object to insert and at the same foramt in {@link getAttributeToInsert()}
 */
	public abstract String getValToInsert(); // get value of insert

}
