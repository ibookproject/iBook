package Panels;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.*;

import ManagmentGUI.StatisticsUserReportGUI;

public class Validation {

	/**
	 * @author  Sagi
	 * @param name, the input to validate
	 * @return false if contain any of the forbbien char--> |,%,\,",',&,=
	 * @return true in any other situation  
	 */
	public static boolean ContentValidation(String name) {
		boolean status = false;
		String namePattern = "[|%\"'&=]+";
		Pattern pattern = Pattern.compile(namePattern);
		Matcher matcher = pattern.matcher(name);

		if (matcher.matches())
			status = false;
		else
			status = true;
		return status;
	}
	

	/**
	 * @author  Sagi
	 * @param name, the input to validate
	 * @return false if contain any of the forbbien char--> |,%,\,",',&,=
	 * @return true in any other situation  
	 */
	public static boolean regularValidation(String name) {
		if (name==null||name.indexOf('|')>0||name.indexOf('%')>0||name.indexOf('\\')>0||name.indexOf('\"')>0||name.indexOf('&')>0||name.indexOf('=')>0)
			return false;
		return true;
	}
	
	/**
	 * @author Almog Yamin
	 * @param name, the input to validate
	 * @param len ,the lenght you want to validate
	 * @return true if contain only engish letters and the lenght is not above len.(len under 128)
	 * @return false in any other situation  
	 */	
	public static boolean NameValidation(String name,int len) {
		String namePattern = "[A-Za-z ]+";
		Pattern pattern = Pattern.compile(namePattern);
		Matcher matcher = pattern.matcher(name);

		if (matcher.matches()&&name.length()<len&&len<128)
			return true;
		
		return false;
	}

	/**
	 * @author Kfir Girstein
	 * @param name, the input to validate
	 * @param len ,the lenght you want to validate
	 * @return true if contain only engish letters Dot,comma,numbers and the lenght is not above len.(len under 128)
	 * @return false in any other situation  
	 */	
	public static boolean TitleValidation(String name,int len) {
		String namePattern = "([A-Za-z ,.0-9]+)";
		Pattern pattern = Pattern.compile(namePattern);
		Matcher matcher = pattern.matcher(name);

		if (matcher.matches()&&name.length()<len&&len<128)
			return true;
		
		return false;
	}
	/**
	 * @author hen saada
	 * @param name, the input to validate
	 * @return true if contain only engish letters Dot,comma,numbers and the lenght is not above len.(len under 128)
	 * @return false in any other situation  
	 */	
	public static String PriceValidation(String price) {

			int cnt=0;
		  String warnings="";
		  for(int i=0;i<price.length();i++)
			  if(price.charAt(i)=='.')
			  {
				  if(++cnt==2)
				  {
						warnings = "price field - Must contain more then 1 '.'  \n";
						return warnings;
				  }
			  }
	
		  for(int i=0;i<price.length();i++)
			  if((price.charAt(i)>'A'&&price.charAt(i)<'Z')||((price.charAt(i)>'a'&&price.charAt(i)<'z'))&&(price.charAt(i)!='.'))
			  {
				warnings = "price field - Must contain only numbers letters \n";
				return warnings;
			  }

		  for(int i=0;i<price.length();i++)
			  if(price.charAt(i)=='.'&&(i==0||i==price.length()-1))
			  {
						warnings = "price field - Must contain only Ligal Number \n";
						return warnings;
			  }
		  return "";
		  
		
	}
	/**
	 * @author Kfir Girstein
	 * @param name, the input to validate
	 * @param len ,the lenght you want to validate
	 * @return true if contain only engish letters Dot,comma,numbers and the lenght is not above len.(len under 128)
	 * @return false in any other situation  
	 */	
	public static boolean AuthorValidation(String name,int len) {
		String namePattern = "([A-Za-z .]+)";
		Pattern pattern = Pattern.compile(namePattern);
		Matcher matcher = pattern.matcher(name);

		if (matcher.matches()&&name.length()<len&&len<128)
			return true;
		
		return false;
	}


	/**
	 * @author Almog Yamin
	 * @param name, the input to validate
	 * @return true if matches only the regex: enlgish letter/numbers/!@#$^*()_+-/.  
	 * and the lenght is not above 20
	 * @return false in any other situation  
	 */
	public static boolean PasswordValidation(String name) {
		String namePattern = "[A-Za-z0-9!@#$^*()_+-/. ]+";
		Pattern pattern = Pattern.compile(namePattern);
		Matcher matcher = pattern.matcher(name);

		if (matcher.matches()&&name.length()<20)
			return true;
		
		return false;
	}
	
	/**
	 * @author Kfir Girstein
	 * @param ID, the input to validate
	 * @return true if contain olny numbers and the length is 9 number only!
	 * @return false in any other situation  
	 */
	public static boolean UserIDValidation(String ID) {
		String namePattern = "[0-9]+";
		Pattern pattern = Pattern.compile(namePattern);
		Matcher matcher = pattern.matcher(ID);

		if (matcher.matches()&&ID.length()==9)
			return true;
		
		return false;
	}
	/**
	 * @author Kfir Girstein
	 * @param ID, the input to validate
	 * @return true if contain only numbers
	 * @return false in any other situation  
	 */
	public static boolean IDValidation(String ID) {
		String namePattern = "[0-9]+";
		Pattern pattern = Pattern.compile(namePattern);
		Matcher matcher = pattern.matcher(ID);

		if (matcher.matches())
			return true;
		
		return false;
	}
	/**
	 * @author Kfir Girstein
	 * @param dateToValidate, the input to validate
	 * @param dateFromat, the format of the date
	 * @return true if there is a mathes to the foramt
	 * @return false in any other situation  
	 */
	public static boolean DateValidation(String dateToValidate){

		if(dateToValidate == null)
			return false;
		if (dateToValidate.matches("([0-9]{2})/([0-9]{2})/([0-9]{4})")||
				dateToValidate.matches("([0-9]{2}).([0-9]{2}).([0-9]{4})")||
				dateToValidate.matches("([0-9]{2})-([0-9]{2})-([0-9]{4})")||
				dateToValidate.matches("([0-9]{4})/([0-9]{2})/([0-9]{2})")||
				dateToValidate.matches("([0-9]{4}).([0-9]{2}).([0-9]{2})")||
				dateToValidate.matches("([0-9]{4})-([0-9]{2})-([0-9]{2})"))
			return true;
		
		return false;
				
		
		}
	
}
