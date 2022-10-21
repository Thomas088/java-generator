import static java.lang.System.*;

import java.nio.file.Path;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Vector;
import java.lang.StringBuilder;

public class Main 
{

	public static void main(String[] args) {
		
		// IMPORT DES AUTRES CLASSES NECESSAIRE AU MENU
		MenuDisplays menus = new MenuDisplays();
		Parser parser = new Parser();
		Helpers helper = new Helpers();
		DatabaseController database = new DatabaseController();
		
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
            	database.callSearchDatasProcedure("pone", 50);
            }
        	
        } catch (Exception e) {
			e.printStackTrace();				
		}		
        
	 }      
			      
}        
	