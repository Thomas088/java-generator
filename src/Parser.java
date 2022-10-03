import static java.lang.System.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Parser {

    public static void main(String args[]) throws FileNotFoundException {
 
        File jMeriseSQL = new File("./test.sql");
        Scanner scanner = new Scanner(jMeriseSQL);
        
        int lineNumber = 1;
        char parenthese = '(';
        
        String createTableStr = "CREATE TABLE ";
        String tableResult;
        String[] list_of_tables = {};
        
        Pattern createTablePattern = Pattern.compile(createTableStr, Pattern.CASE_INSENSITIVE);
        
        while(scanner.hasNextLine()) {
        	
            String strCurrentLine = scanner.nextLine();
            int currenLineLength = strCurrentLine.length();
            lineNumber++;
        
                      
            if (strCurrentLine.startsWith(createTableStr)) {
             
                 tableResult = "";

	    		 for (int i = 0; i < currenLineLength; i++) {
	
                     // ------------ INDEX VERSION ----------- //
	    			 char currentCaracter = strCurrentLine.charAt(i);
	    		        // Si l'on tombe apres le CREATE TABLE et l'espace
	    		    if (i >= createTableStr.length()) {
	
	    		        // ... ET si l'on ne tombe pas sur la parenthese ouvrante
	    		        if (!(Character.compare(currentCaracter,parenthese) == 0)) {
	    		                 tableResult += Character.toString(currentCaracter);
	    		        }
	    		     }
	    		 }
	    		 
	             // -------- REGEX VERSION ------------ // 
	             Matcher create_table_matcher = createTablePattern.matcher(strCurrentLine);
	             
                if (create_table_matcher.find()) {
                	tableResult = strCurrentLine.replaceAll(createTableStr, "").replace(Character.toString(parenthese), "").trim();
                	out.println(tableResult);
                }
                
               System.out.println(tableResult); 
            } else {
            	strCurrentLine = "";
            }
        }			
    }      
 }  
