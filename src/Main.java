import static java.lang.System.*;

import java.nio.file.Path;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Vector;
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
		DatabaseController database = new DatabaseController();
		GeneratorLogger logger = new GeneratorLogger();
		Vector<TableData> listOfTables = new Vector<TableData>();
		
		boolean isLogged = false;
		
		TableData currentTable = null;
		
        String userInput = "";
        String currentAttribut = "";
        String answerForTables = "y";
        String answerForAttribut = "y";
        
        try {
        	   	
            isLogged = database.createConnection();
            
            if (isLogged) {
            	logger.logInfo("createConnection()", "Connexion success.");
//            	database.callSearchDatasProcedure("address", 50); // <= Decommenter pour tester     	
            } else {
            	logger.logError("createConnection()", "Error on connexion to database.");
            }
 
        	// Parser automatiquement
        	listOfTables = parser.parse("./labo-test/mcfly.sql");
        	parser.printArrayTableData(listOfTables);
           
//  			while(answerForAttribut.toString().equalsIgnoreCase("y")) {
//  				out.println("Enter table name : ");
//  				userInput = helper.readString(); // déjà un while dans le Helpers
//  				
//  				currentTable = new TableData(); // On instancie un nouvel objet pour chaque infos que le user rentre
//  				currentTable.setTableName(userInput);
//  				
//  				// La variable est deja etablie une fois a oui pour pouvoir encoder les attributs
//  				while(answerForAttribut.equalsIgnoreCase("y"))	
//  					
//  				{
//  					answerForAttribut = "";
//  					out.println("Enter attribute name : ");
//  					
//  					currentAttribut = helper.readString();
//  					currentTable.pushInAttributeList(currentAttribut);
//  					
//  					answerForAttribut = helper.chooseContinueState();
//  					
//  					if(answerForAttribut.equalsIgnoreCase("n")) {
//  						break;
//  					} else {
//  						answerForAttribut;
//  					}
//  					
//  				};
//  				
//  				listOfTables.add(currentTable);
//  				
//  				if (listOfTables.size() >= 1) {
//  					out.println("Voulez vous re-entrer une 2eme table");
//  				}
//  				
//  				answerForTables = helper.chooseContinueState(); // méthode de l'état de continue ou non dans les Helpers
//  				
//  				if(answerForTables.equalsIgnoreCase("n")) {
//  					break;
//  				} else {
//  					answerForTables = "y";
//  				}
//  				
//  			 }
        
        } catch (Exception e) {
        	logger.logError("Main()", e.getMessage());			
		}		
        
	 }      
			      
}        
	