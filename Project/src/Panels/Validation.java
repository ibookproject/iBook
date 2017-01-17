package Panels;

import java.util.regex.*;

import ManagmentGUI.StatisticsUserReportGUI;

public class Validation {

	public static boolean NameValidation(String name) {
		boolean status = false;
		String namePattern = "[A-Za-z ]+";
		Pattern pattern = Pattern.compile(namePattern);
		Matcher matcher = pattern.matcher(name);

		if (matcher.matches())
			status = true;
		else
			status = false;
		return status;
	}


	public static boolean PasswordValidation(String name) {
		boolean status = false;
		String namePattern = "[A-Za-z0-9!@#$%^&*()_+-=/. ]+";
		Pattern pattern = Pattern.compile(namePattern);
		Matcher matcher = pattern.matcher(name);

		if (matcher.matches())
			status = true;
		else
			status = false;
		return status;
	}
}
