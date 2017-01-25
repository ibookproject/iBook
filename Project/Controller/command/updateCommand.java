package command;

/**
*@Category
*data base 
*communication
*
*@Extends
*deleteCommand<E>
*
*this class is do define the update actions and to get the generic parameters to make 
*a update quary
* @param <E>
* the type of the entity to send,
* the entity extend the class DBtranslation, to make the quary
*  *  @author
*  kfir Girstein
**/
public class updateCommand<E extends DBtranslation> extends deleteCommand<E> {

	private String[] setCondition;
	
	/**
	 * constractor with the actual object
	 *  
	 * @param
	 *  updateCondition
	 *  all the attributes of the object E to get from the quary
	 *  actual name of the attributes and oprator '=' and then the real values to update
	 *  this is make the real format to the quary
	 * @param 
	 * table1
	 * the actual class/table to make the quary on
	 * @param
	 *  searchCondition
	 *  the condition of the quary, insert the coditions of the values
	 *  with ' && ' between the make the spcipic and oprator
	 * @author
	 *  kfir Girstein
	 */

	public updateCommand(E table1, String searchCondition,String updateCondition) {
		super(table1, searchCondition);
		setCondition=updateCondition.split(" && ");
	}
/**
 * 
 *  @author 
 *  kfir Girstein
 * @return
 * take the update condition and make the specific format to use in update quary in SQL
 * @throws
 * RuntimeException
 * 
 */
	public String getUpdateCondition()
	{
		String st=new String();
		st="";
		for(int i=0;i<setCondition.length-1;i++)
			st+=setCondition[i]+", ";
		st+=setCondition[setCondition.length-1];
		if(st.equals(""))
			throw new RuntimeException("you insert wrong condition!");
		return st;
	}

	private static final long serialVersionUID = 1L;

}
