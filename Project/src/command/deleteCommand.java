package command;


public class deleteCommand<E extends DBtranslation> extends command<E> {
	protected String[] conditions;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public deleteCommand(E table1,String conds) {
		super(table1);
		conditions=conds.split(" && ");
	}
	public deleteCommand(String conds) {
		super();
		conditions=conds.split(" && ");
	}
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
