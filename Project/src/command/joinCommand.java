package command;

import java.sql.SQLException;

public class joinCommand<E extends DBtranslation,T extends DBtranslation> extends searchCommand<E> {
	private static final long serialVersionUID = 1L;
	private joinObject<E,T>[] tablesToJoin;

	/*
	public joinCommand(String selectSentence, E table1, String whereSentence) {
		super(selectSentence, table1, whereSentence);
		// TODO Auto-generated constructor stub
	}
	*/
	
	//in the select selectSentence have to add the name of the table first the '.' and the the name 
	// example b.getTable().bookID
	public joinCommand(String selectSentence,joinObject<E,T>[] tablesToJoin,String whereSentence) throws SQLException {
		super(selectSentence,whereSentence);
		if(tablesToJoin==null)
			throw new SQLException("you inesrt wrong tables to Join");
		this.tablesToJoin=tablesToJoin;
			
	}
	@Override
	public String getTable()
	{
		String temp="";
		for(int i=0;i<tablesToJoin.length;i++)
			temp+=tablesToJoin[i].getTable1()+","+tablesToJoin[i].getTable2();  ///will be like this table1,table2,table3....
		return temp;
	}
	public String getJoinQuery()
	{
		String temp="";
		if(tablesToJoin.length==1)
			temp=tablesToJoin[0].getJoinQuery();
		else{
		for(int i=0;i<tablesToJoin.length-1;i++)
			temp+=tablesToJoin[i].getJoinQuery()+","; /// will be table1.joinAttribute=table2.joinAttribute AND table3.joinAttribute=table4.joinAttribute
		temp+=tablesToJoin[tablesToJoin.length].getJoinQuery();
		}
		return temp;
	}
	/**
	 * 
	 */

	
	

	

}
