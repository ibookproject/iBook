package command;

import java.sql.SQLException;
import java.util.ArrayList;

/**
*@Category
*data base 
*communication
*
*@Extends
*{@link searchCommand}
*
*this class is do define the search actions between some tables and to get the generic parameters to make 
*a search quary
* @param <E>
* the type of the entity to send,
* the entity extend the class DBtranslation, to make the quary
*  @author
*  kfir Girstein
**/
public class joinCommand<E extends DBtranslation> extends searchCommand<E> {
	private static final long serialVersionUID = 1L;
	private ArrayList<joinObject> tablesToJoin;

	
/**
	 * constractor with the actual object
	 *  
 *  @author kfir Girstein
 * @param 
 * selectSentence
 *  all the attributes of the object E to get from the quary
 *  actual name of the attributes with ',' between the subconditions
	 *  <b> must include the table name + '.' before the attribute</b>
 * @param table1
 	 *  one of the actual class/table to make the quary on
	 *  <b>
	 *  the table mast extends DBtransltion
	 * </b> 
 * @param tablesToJoin
 * {@link ArrayList} of the {@link joinObject} object, that include the tables to conncted
 * @param whereSentence
 	 *  the condition of the quary, insert the coditions of the values
	 *  with ' && ' between the make the spcipic and oprator
	  *  <b>must to add before any subcondition,witch table the condition is </b>

 * @throws SQLException
 */
	public joinCommand(String selectSentence, E table1, ArrayList<joinObject>  tablesToJoin,String whereSentence) throws SQLException {
		super(selectSentence, table1, whereSentence);
		if(tablesToJoin==null)
			throw new SQLException("you inesrt wrong tables to Join");
		this.tablesToJoin=tablesToJoin;
			
	}
	

	
/**
	 * constractor to make quary,
	 * without specipic values
 *  @author kfir Girstein
 * @param 
 * selectSentence
 *  all the attributes of the object E to get from the quary
 *  actual name of the attributes with ',' between the subconditions
	 *  <b> must include the table name + '.' before the attribute</b>
	 * in the select selectSentence have to add the name of the table first the '.' and the the name 
	 * example b.getTable().bookID
 * @param table1
 	 *  one of the actual class/table to make the quary on
	 *  <b>
	 *  the table mast extends DBtransltion
	 * </b> 
 * @param tablesToJoin
 * {@link ArrayList} of the {@link joinObject} object, that include the tables to conncted
 * @param whereSentence
 	 *  the condition of the quary, insert the coditions of the values
	 *  with ' && ' between the make the spcipic and oprator
	  *  <b>must to add before any subcondition,witch table the condition is </b>

 * @throws SQLException
 */
	
	//in the select selectSentence have to add the name of the table first the '.' and the the name 
	// example b.getTable().bookID
	public joinCommand(String selectSentence, ArrayList<joinObject>  tablesToJoin,String whereSentence) throws SQLException {
		super(selectSentence,whereSentence);
		if(tablesToJoin==null)
			throw new SQLException("you inesrt wrong tables to Join");
		this.tablesToJoin=tablesToJoin;
			
	}
/**
 * @author kfir Girstein
	*@return
	*all the tables in the quary, from the ArrayList<joinObject>
	**/
	@Override
	public String getTable()
	{
		String temp="";
		String toAdd="";
		ArrayList<String> st=new ArrayList<String>();
		for(int i=0;i<tablesToJoin.size();i++){
			toAdd=tablesToJoin.get(i).getTable1();
			if(!st.contains(toAdd))
				st.add(toAdd);
			toAdd=tablesToJoin.get(i).getTable2(); 
			if(!st.contains(toAdd))
				st.add(toAdd);///will be like this table1,table2,table3....
		}
		
		for(int i=0;i<st.size()-1;i++)
			temp+=st.get(i)+",";
		temp+=st.get(st.size()-1);
		return temp;
	}
	/**
	 * 
	 *  @author kfir Girstein
	 * @return
	 * make to whole search quary,
	 * include the join condition, to inegrate between all the tables and make all the condition
	 * will be: <b><code>"SELECT table.att1,table2.att2.. FROM table1,table2,.. WHRER table1.key=table2.key AND table1.condition1 AND table2.conditon2 AND  .."</code></b>
	 */
	public String getJoinQuery()
	{
		String temp="";
		if(tablesToJoin.size()==1)
			temp=tablesToJoin.get(0).getJoinQuery();
		else{
		for(int i=0;i<tablesToJoin.size()-1;i++)
			temp+=tablesToJoin.get(i).getJoinQuery()+" AND "; /// will be table1.joinAttribute=table2.joinAttribute AND table3.joinAttribute=table4.joinAttribute
		temp+=tablesToJoin.get(tablesToJoin.size()-1).getJoinQuery();
		}
		if(this.getCondition().length()>0)
			temp+=" AND ";
		return temp;
	}


	

}
