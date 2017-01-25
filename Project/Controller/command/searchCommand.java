package command;

/**
*@Category
*data base 
*communication
*
*@Extends
*{@link deleteCommand}
*
*this class is do define the search actions and to get the generic parameters to make 
*a search quary
* @param <E>
* the type of the entity to send,
* the entity extend the class DBtranslation, to make the quary
*  @author
*  kfir Girstein
**/
public class searchCommand<E extends DBtranslation> extends deleteCommand<E> {

	private static final long serialVersionUID = 1L;
	private String filedsToSearch; // filedsTosearch is need to look like "a,c,v,d..."

	/**
	 * constractor with the actual object
	 *  
	 * @param
	 *  selectSentence
	 *  all the attributes of the object E to get from the quary
	 *  actual name of the attributes with ',' between the subconditions
	 * @param 
	 * table1
	 * the actual class/table to make the quary on
	 * @param
	 *  whereSentence
	 *  the condition of the quary, insert the coditions of the values
	 *  with ' && ' between the make the spcipic and oprator
	 * @author
	 *  kfir Girstein
	 */
	public searchCommand(String selectSentence,E table1,String whereSentence) {
		super(table1, whereSentence);
		if(selectSentence.indexOf('*')>=0)
			filedsToSearch="*";
		else
		filedsToSearch=selectSentence;
	}
	/**
	 * constractor to make quary,
	 * without specipic values
	 *
	 * @param
	 *  selectSentence
	 * all the attributes of the object E to get from the quary
	 *  actual name of the attributes with ',' between
	 * @param
	 *  whereSentence
	 * the condition of the quary, insert the coditions of the values
	 *  with ' && ' between the make the spcipic and oprator
	 * @author
	 *  kfir Girstein
	 */
	public searchCommand(String selectSentence,String whereSentence) {
		super(whereSentence);
		if(selectSentence.indexOf('*')>=0)
			filedsToSearch="*";
		else
		filedsToSearch=selectSentence;
	}
	/**
	 * 
	 *  @author 
	 *  kfir Girstein
	 * @return
	 * the attribute to show/get form the table 
	 * "SELECT" attributes
	 */
	public String getfiledsToSearch()
	{
		return filedsToSearch;
	}
}
