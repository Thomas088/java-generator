import static java.lang.System.*;
import java.io.File;
import java.util.Vector;

/**
 * Main => The main entry point of the program
 * @author Java Generator Team
 *
 */
public class Main {
	
	/**
	 * Main() => The main software program
	 * @param {String[]} args (argv in C / C++)
	 */
	public static void main(String[] args) {
		
		// IMPORT DES AUTRES CLASSES NECESSAIRE AU MENU
		DatabaseController database = new DatabaseController();
		Parser parser = new Parser();
		FileHandler fileHandler = new FileHandler();
		Vector<TableData> listOfTables = new Vector<TableData>();
		GeneratorLogger logger = new GeneratorLogger();
		File fileToExport;
		
		boolean isLogged = false;
		
        String userInput = "";
        String currentAttribut = "";
        String answerForTables = "y";
        String answerForAttribut = "y";
        
        // START PROGRAM
        try {
        	   	
            isLogged = database.createConnection();
            
            if (isLogged) 
            	 logger.logInfo("createConnection()", "Connexion success.");
	        else logger.logError("createConnection()", "Error on connexion to database.");
 
        	// Parsing automatic mode at start 
        	listOfTables = parser.parse("./labo-test/mcfly.sql");
        	
        	MenuDisplays.printMainMenu();
        	
        	out.println("Here is the list af the tables : ");
        	parser.printArrayTableData(listOfTables);
            
        	
            // Variable is set once for enter in loop..
        	
//			while(answerForAttribut.toString().equalsIgnoreCase("y")) {
//
//				userInput = Helpers.readString(); // déjà un while dans le Helpers
//				
//				// LAST CONTINUE
//				answerForTables = Helpers.chooseContinueState(); // méthode de l'état de continue ou non dans les Helpers
//				
//				if(answerForTables.equalsIgnoreCase("n")) {
//					break;
//				} else {
//					answerForTables = "y";
//				}
//				
//			 }

        	// OK - TESTED 
//        	fileToExport = fileHandler.createFile("eci-insert-test");	
//        	fileHandler.writeToFile(10, listOfTables, fileToExport.getAbsolutePath());
        	
        } catch (Exception e) {
        	logger.logError("Main()", e.getMessage());			
		}		
        
	 }      
			      
}        
	