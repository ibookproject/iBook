package command;

/**
*@Category
*data base 
*communication
*
*@Extends
*Command<E>
*
*this class is do define the delete actions and to get the generic parameters to make 
*a delete quary
* @param <E>
* the type of the entity to send,
* the entity extend the class DBtranslation, to make the quary
* @author
*  kfir Girstein
**/
public class deleteCommand<E extends DBtranslation> extends command<E> {
	protected String[] conditions;
	private static final long serialVersionUID = 1L;
	/**
	 * constractor with the actual object
	 * 
	 * @param
	 *  table1
	  * the actual class/table to make the quary on
	 * @param 
	 * condition
	   the condition of the quary, insert the coditions of the values
	 *  with ' && ' between the make the spcipic and oprator
 
	 *  @author
	 *   kfir Girstein
	 */
	public deleteCommand(E table1,String condition) {
		super(table1);
		conditions=condition.split(" && ");
	}
	
	/**
	 * constractor to make quary,
	 * without specipic values
	 *  @param condition
	 *  the condition of the quary, insert the coditions of the values
	 *  with ' && ' between the make the spcipic and oprator
 
	 *  @author
	 *   kfir Girstein
	 */
	public deleteCommand(String condition) {
		super();
		conditions=condition.split(" && ");
	}
	/**
	 * 
	 *  @author 
	 *  kfir Girstein
	 * @return
	 * the condition of the quary in the format for SQL
	 */
	public String getCondition()
	{
		String st=new String();
		st="";
		for(int i=0;i<conditions.length-1;i++)
			st+=conditions[i]+" AND ";
		st+=conditions[conditions.length-1];
		//if(st.equals(""))
			//throw new RuntimeException("you insert wrong condition!");
		return st;
		
	}


}
