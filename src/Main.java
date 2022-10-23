import static java.lang.System.*;

import java.nio.file.Path;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.lang.StringBuilder;

/**
 * 
 * @author Java Generator Team
 *
 */
public class Main {
	
	/**
	 * Main() => The main software program
	 * @param args
	 */
	public static void main(String[] args) {
		
		// IMPORT DES AUTRES CLASSES NECESSAIRE AU MENU
		MenuDisplays menus = new MenuDisplays();
		Parser parser = new Parser();
	    Helpers helper = new Helpers();
		DatabaseController database = new DatabaseController();
		GeneratorLogger logger = new GeneratorLogger();
		
		boolean isLogged = false;
		
		LinkedList<TableData> listOfTables = new LinkedList<TableData>();
		TableData currentTable = null;
		
        String userInput = "";
        String currentAttribut = "";
        
        String answerForTables = "y";
        String answerForAttribut = "y";
        
        try {
        	   	
            isLogged = database.createConnection();
            
            if (isLogged) {
            	
//            	database.callSearchDatasProcedure("phone", 50); <= Decommenter pour tester
//            	database.callGenerateRandomNumber(10, 10000); <= Decommenter pour tester
            } else {
            	logger.logError("createConnection()", "Error on connexion to database.");
            }
        
        	parser.parseTEST();
        	
        } catch (Exception e) {
        	logger.logError("Main()", e.getMessage());			
		}		
        
	 }      
			      
}        
	