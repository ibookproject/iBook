package command;

public abstract class DBtranslation {

	public abstract String getClassName();

	public abstract String getAttributeToInsert(); // get coulms of the table

	public abstract String getValToInsert(); // get value of insert

}
