package command;

import java.util.ArrayList;

import client.DBgenericObject;

public class joinAnswer {
	private String[] attribute;
	
	private joinAnswer(int len)
	{
		super();
		attribute=new String[len];
	}
	public static ArrayList<joinAnswer> convertBack(ArrayList<DBgenericObject> arr,String selectSentence) {
		 ArrayList<joinAnswer> convertedArr=new ArrayList<joinAnswer>();
		 
		for(DBgenericObject ob:arr)
				convertedArr.add(convertDBObject(ob, selectSentence));//for each val in arr this convert back to book
		
		return convertedArr;
		
	}
	private static joinAnswer convertDBObject(DBgenericObject ob, String selectSentenceArray) {
		 String[] selectSentence=selectSentenceArray.split(",");
		 joinAnswer jAns=new joinAnswer(selectSentence.length);
		 for(int i=0;i<selectSentence.length;i++)
			 jAns.setAttribute(i,(String)ob.getValtoArray(i));
		 return jAns;
		 
	}
	private void setAttribute(int i, String valtoArray) {
		attribute[i]=valtoArray;
	}
	
	public String[] setAttribute(){
	 return attribute;
	}
	public String[] getAttributes()
	{
		 return attribute;
	}
	
	
}
