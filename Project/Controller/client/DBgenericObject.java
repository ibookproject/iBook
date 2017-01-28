package client;

import java.io.Serializable;
import java.util.Arrays;

/**
 * This object is to receive the answer form the data base with the number of
 * argument which were from the query
 * 
 * @author kfir Girstein
 *
 */
public class DBgenericObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int numVals;
	private Object[] arr;

	/**
	 *
	 * @param num
	 *            the number of object which were from the query
	 */
	public DBgenericObject(int num) {
		numVals = num;
		arr = new Object[num];
	}

	/**
	 * insert a value number i to the answer structure, form the query
	 * 
	 * @param i
	 *            the attribute number at the query
	 * @param ob
	 *            the attribute to set into the answer
	 */
	public void insertValtoArray(int i, Object ob) {
		if (i < 0 || i >= numVals)
			throw new RuntimeException("out of lines");
		arr[i] = ob;
	}

	/**
	 * get the value i form the answer Structure
	 * 
	 * @param i
	 *            the attribute number at the query
	 * @return Object at the place i from the answer Structure
	 */
	public Object getValtoArray(int i) {
		if (i < 0 || i >= numVals)
			throw new RuntimeException("out of lines");
		return arr[i];
	}

	@Override
	public String toString() {
		return Arrays.toString(arr);
	}

	/**
	 * 
	 * @return the number of attribute which were the query
	 */
	public int getNumVals() {
		return numVals;
	}
}
