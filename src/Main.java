import static java.lang.System.*;

import java.util.HashMap;
import java.util.LinkedList;

public class Main 
{

	public static void main(String[] args) 
	{
		
		// IMPORT DES AUTRES CLASSES NECESSAIRE AU MENU
		MenuDisplays menus = new MenuDisplays();
		Parser parser = new Parser();
		Helpers helper = new Helpers();
		
		LinkedList<String> arrayTables = new LinkedList<String>();
		LinkedList<String> arrayAttribut = new LinkedList<String>();
		
        String currentTable = "";
        String currentAttribut = "";
        String answerForTables = "y";
        String answerForAttribut = "y";
        
               
			while(answerForTables.equalsIgnoreCase("y")) 
			{
				answerForTables = "";	
				out.println("Enter table name : ");
				currentTable = helper.readString().trim(); // déjà un while dans le Helpers
				
				
				arrayTables.add(currentTable);  // on push dans le tableau de tables
				
				while(answerForAttribut.equalsIgnoreCase("y"))	
					
				{
					answerForAttribut = "";
					out.println("Enter attribute name : ");
					currentAttribut = helper.readString().trim();
					arrayAttribut.add(currentAttribut);
					answerForAttribut = helper.chooseContinueState();
					
					if(answerForAttribut.equalsIgnoreCase("n")) break;
					
				};
				
				if(arrayTables.size() >= 1) { // si on a plus d'une table...
					out.println("Do you want to RE-enter a table ? : ");
				}
				
				answerForTables = helper.chooseContinueState(); // méthode de l'état de continue ou non dans les Helpers
				if(answerForTables.equalsIgnoreCase("n")) break;
				
			 }
			
	    	 out.println(arrayTables);
	    	 out.println(arrayAttribut);
	 }      
			      
}        
		
		
	



