package command;

import java.sql.SQLException;
import java.util.ArrayList;

public class joinCommand<E extends DBtranslation,T extends DBtranslation> extends searchCommand<E> {
	private static final long serialVersionUID = 1L;
	private ArrayList<joinObject<E,T>> tablesToJoin;

	

	public joinCommand(String selectSentence, E table1, ArrayList<joinObject<E,T>>  tablesToJoin,String whereSentence) throws SQLException {
		super(selectSentence, table1, whereSentence);
		if(tablesToJoin==null)
			throw new SQLException("you inesrt wrong tables to Join");
		this.tablesToJoin=tablesToJoin;
			
	}
	
	
	//in the select selectSentence have to add the name of the table first the '.' and the the name 
	// example b.getTable().bookID
	public joinCommand(String selectSentence, ArrayList<joinObject<E,T>>  tablesToJoin,String whereSentence) throws SQLException {
		super(selectSentence,whereSentence);
		if(tablesToJoin==null)
			throw new SQLException("you inesrt wrong tables to Join");
		this.tablesToJoin=tablesToJoin;
			
	}

	@Override
	public String getTable()
	{
		String temp="";
		for(int i=0;i<tablesToJoin.size();i++)
			temp+=tablesToJoin.get(i).getTable1()+","+tablesToJoin.get(i).getTable2();  ///will be like this table1,table2,table3....
		return temp;
	}
	public String getJoinQuery()
	{
		String temp="";
		if(tablesToJoin.size()==1)
			temp=tablesToJoin.get(0).getJoinQuery();
		else{
		for(int i=0;i<tablesToJoin.size()-1;i++)
			temp+=tablesToJoin.get(i).getJoinQuery()+","; /// will be table1.joinAttribute=table2.joinAttribute AND table3.joinAttribute=table4.joinAttribute
		temp+=tablesToJoin.get(tablesToJoin.size()).getJoinQuery();
		}
		if(this.getCondition().length()>0)
			temp+=" AND ";
		return temp;
	}
	/**
	 * 
	 */

	
	

	

}
