package command;

public class insertCommand<E extends DBtranslation> extends command<E> {

	//generic sql command insert
	
	private static final long serialVersionUID = 1L;
	
	public insertCommand(E temp) {
		super(temp);
	}
	public E getToInsert()
	{
		return table;
	}

}
