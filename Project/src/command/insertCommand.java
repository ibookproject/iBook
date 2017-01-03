package command;

public class insertCommand<E extends DBtranslation> extends command<E> {

	//generic sql command insert
	
	private static final long serialVersionUID = 1L;
	private E toInsert;
	
	public insertCommand(String table1,E temp) {
		super(table1);
		this.toInsert=temp;
	}

	public E getToInsert() {
		return toInsert;
	}

	public void setToInsert(E toInsert) {
		this.toInsert = toInsert;
	}
	

}
