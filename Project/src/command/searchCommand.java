package command;

public class searchCommand<E extends DBtranslation> extends deleteCommand<E> {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String filedsToSearch; // filedsTosearch is need to look like "a,c,v,d..."

	public searchCommand(String table1, String conds,String fileds) {
		super(table1, conds);
		filedsToSearch=fileds;
	}
	public String getfiledsToSearch()
	{
		return filedsToSearch;
	}
}
