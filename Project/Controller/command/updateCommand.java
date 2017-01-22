package command;


public class updateCommand<E extends DBtranslation> extends deleteCommand<E> {

	private String[] setCondition;
	public updateCommand(E table1, String searchCondition,String updateCondition) {
		super(table1, searchCondition);
		setCondition=updateCondition.split(" && ");
	}

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
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
