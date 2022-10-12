import static java.lang.System.*;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Helpers {

	Scanner scanner = new Scanner(new InputStreamReader(System.in));	
	Pattern noLetterOrCharpattern = Pattern.compile("[a-zA-Z-*+$%^&*()=]", Pattern.CASE_INSENSITIVE);
	Pattern noNumbersPatters = Pattern.compile("[0-9]");
	

	// FOR TEMPLATE
	public String startTemplateInsert(String nameTable) {
	    return "INSERT INTO " + nameTable;
	}
	
	// MYSQL - MARIA
	
	public String transformToMySQLDate(int[] date) {
		
		String dateStr = "";
		
		for (int i = 0; i < date.length; i++) {
			dateStr += date[i] + ((i != date.length - 1) ? "-" : "");
		}
		
		return dateStr;
	}
	
	
	public String transformToMySQLDatetime(int[] date, int[] time) {
		
		String dateTimeStr = "";
		return ""; // TODO - A CREER
	} 
	
	// FOR USER PROMPT
	
	public String chooseContinueState() {
		
		// String newline = System.getProperty("line.separator"); a voir si besoin par la suite
		String choice = "";
		
		while(true) {
			
			
			out.println("Do you want to continue ? (Yy / Nn)");
			choice =  scanner.next().trim(); // enlève les espaces
			
			if (choice.length() == 1) {
			
					
				if (choice.length() > 1 || (!choice.equalsIgnoreCase("y") && !choice.equalsIgnoreCase("n"))) {
					err.println("Error bad value - invalid value entered - re-do.");
					choice = "";
				}
				
			} else {
				err.println("Bad value - re-do.");
				choice = "";
			}
			
			out.println("Value OK - You choosed : " + choice);
			break;
		}
		
		return choice;
	}
	
	public Integer readInt() {
		
		String n = "0";
		Integer length = n.trim().length();
		
		while (length > 0 && n == "0") {
			
			out.println("Enter number :");
			n = scanner.next().trim();
			
			Matcher matcher = noLetterOrCharpattern.matcher(n);
			boolean isLetterFounded = matcher.find();
			
			if (isLetterFounded) {
				err.println("Error bad value - letter or special character founded - re-do.");
				n = "0";
			} else if (length == 0) {
				err.println("Empty value - re-do.");
			} else {
				break;
			}
		}
		
		Integer value = Integer.parseInt(n);
		out.println("Value OK - You defined : " + value);
		return value;
  }
	
	public Float readFloat() {
		
		String f = "0.0";
		Integer length = f.trim().length();
		
		while (length > 0 && f == "0.0") {
			
			out.println("Enter number :");
			f = scanner.next().trim();
			
			Matcher matcher = noLetterOrCharpattern.matcher(f);
			boolean isLetterFounded = matcher.find();
			
			if (isLetterFounded) {
				err.println("Error bad value - letter or special character founded - re-do.");
				f = "0.0";
			} else if (length == 0) {
				err.println("Empty value - re-do.");
			} else {
				break;
			}
		}
		
		Float value = Float.parseFloat(f + "f");
		out.println("Value OK - You defined : " + value);
		return value;
	}
	
	public Double readDouble() {
		
		String d = "0.0";
		Integer length = d.trim().length();
		
		while (length > 0 && d == "0.0") {
			
			out.println("Enter number :");
			d = scanner.next().trim();
			
			Matcher matcher = noLetterOrCharpattern.matcher(d);
			boolean isLetterFounded = matcher.find();
			
			if (isLetterFounded) {
				
				err.println("Error bad value - letter or special character founded - re-do.");
				d = "0.0";			
			} else if (length == 0) {
				err.println("Empty value - re-do.");
			} else {
				break;
			}
		}
		
		Double value = Double.parseDouble(d);
		out.println("Value OK - You defined : " + value);
		return value;
	}
	
	public String readString() {
		
		String s = "init";
		Integer length = s.trim().length();
		
		while(length > 0) {
			
			out.println("Type your string :");
			s = scanner.next().trim();
			
			if (length == 0) {
				err.println("Empty value - re-do.");
			} else {
				break;
			}
			
		}
		
		out.println("Value OK - You entered : " + s);
		return s;
	}
	
	
	public String readStringWithoutNumbers() {
		
		String s = "init";
		Integer length = s.trim().length();
		
		while(length > 0) {
			
			out.println("Type your string :");
			s = scanner.next().trim();
			
			Matcher matcher = noNumbersPatters.matcher(s);
			boolean isNumberFounded = matcher.find();
			
			if (isNumberFounded) {			
				err.println("Error Bad value - number founded -  re-do.");
				s = "init";			
			} else if (length == 0) {
				err.println("Empty value - re-do.");
			} else {
				break;
			}
			
		}
		
		out.println("Value OK - You entered : " + s);
		return s;
	}

	// MISC
	
	public static void clearConsole() throws Exception {
	    try {

	     if (System.getProperty("os.name").contains("Windows"))
	         new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	     else
	         Runtime.getRuntime().exec("clear");
	    } catch (IOException | InterruptedException ex) {
	    	throw ex;
	    }
	}
	
	public int generateRandom(int max, int min) {
		int random = (int)Math.floor( Math.random() * (max - min + 1) + min);
		return random;
	}
	
}
