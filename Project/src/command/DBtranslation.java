package command;

public abstract class DBtranslation {

	public static String getClassName() {
		return "DBtranslation";
	}

	public static String getAttributeToInsert() {
		return null;
	} // get coulms of the table

	public abstract String getValToInsert(); // get value of insert

}
