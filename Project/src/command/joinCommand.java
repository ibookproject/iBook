package command;

import java.sql.SQLException;
import java.util.ArrayList;

public class joinCommand<E extends DBtranslation> extends searchCommand<E> {
	private static final long serialVersionUID = 1L;
	private ArrayList<joinObject> tablesToJoin;

	

	public joinCommand(String selectSentence, E table1, ArrayList<joinObject>  tablesToJoin,String whereSentence) throws SQLException {
		super(selectSentence, table1, whereSentence);
		if(tablesToJoin==null)
			throw new SQLException("you inesrt wrong tables to Join");
		this.tablesToJoin=tablesToJoin;
			
	}
	
	
	//in the select selectSentence have to add the name of the table first the '.' and the the name 
	// example b.getTable().bookID
	public joinCommand(String selectSentence, ArrayList<joinObject>  tablesToJoin,String whereSentence) throws SQLException {
		super(selectSentence,whereSentence);
		if(tablesToJoin==null)
			throw new SQLException("you inesrt wrong tables to Join");
		this.tablesToJoin=tablesToJoin;
			
	}

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
	/**
	 * 
	 */

	
	

	

}
