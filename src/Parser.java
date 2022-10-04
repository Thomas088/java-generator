import static java.lang.System.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Parser {
	
	private static String createTableStr = "CREATE TABLE ";
	private static String tableResult = "";
	private static char parenthese = '(';
	
	/**
	 * getTablenameByIndex => get the table name(s) of generated .SQL or text file (works for Jmerise) - Search by string indexes
	 * @param {String} currentLine
	 * @return {String} The tablename
	 */
	private static String getTablenameByIndex(String currentLine) {
		
		tableResult = "";
		
		 for (int i = 0; i < currentLine.length(); i++) {

			 char currentCaracter = currentLine.charAt(i);
		        // Si l'on tombe apres le CREATE TABLE et l'espace
		    if (i >= createTableStr.length()) {

		        // ... ET si l'on ne tombe pas sur la parenthese ouvrante
		        if (!(Character.compare(currentCaracter, parenthese) == 0)) {
		                 tableResult += Character.toString(currentCaracter);
		        } else {
		        	break; // force end loop if we catch the parenthesis or other stuffs such as empty spaces etc 
		        }
		     }
		 }
		 
		return tableResult.trim();
	}
	
	/**
	 * getTablenameByRegex => get the table name(s) of generated .SQL or text file - Search by regular expression (regex)
	 * @param {String} currentLine
	 * @return {String} The tablename
	 */
	private static String getTablenameByRegex(String currentLine) {
		
		tableResult = "";
		
		Pattern createTablePattern = Pattern.compile(createTableStr, Pattern.CASE_INSENSITIVE);
        Matcher createTableMatcher = createTablePattern.matcher(currentLine);
         
        if (createTableMatcher.find()) {
        	tableResult = currentLine.replaceAll(createTableStr, "").replace(Character.toString(parenthese), "").trim();
        }
		
        return tableResult;
	}	

    public static void main(String args[]) throws FileNotFoundException {
 
        File jMeriseSQL = new File("./test.sql");
        Scanner scanner = new Scanner(jMeriseSQL);
             
        String currentTable = "";
        String[] listOfTables = {};
       
        while(scanner.hasNextLine()) {
        	
            String strCurrentLine = scanner.nextLine();
            
            if (strCurrentLine.startsWith(createTableStr)) {
            	
            	// DEMO 
                 System.out.println(getTablenameByRegex(strCurrentLine)); 
                 System.out.println(getTablenameByIndex(strCurrentLine)); 
            }
        }
    }      
 }  
