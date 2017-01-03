package client;

import java.io.Serializable;
import java.util.Arrays;

public class DBgenericObject implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int numVals;
	private Object[] arr;
	
	public DBgenericObject(int num)
	{
		numVals=num;
		arr=new Object[num];
	}
	public void insertValtoArray(int i,Object ob)
	{
		if(i<0||i>=numVals)
			throw new RuntimeException("out of lines");
		arr[i]=ob;
	}
	public Object getValtoArray(int i)
	{
		if(i<0||i>=numVals)
			throw new RuntimeException("out of lines");
		return arr[i];
	}
	@Override
	public String toString() {
		return Arrays.toString(arr);
	}
}
