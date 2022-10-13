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
		
		LinkedList<TableData> listOfTables = new LinkedList<TableData>();
		TableData currentTable = null;
		
        String userInput = "";
        String currentAttribut = "";
        String answerForTables = "y";
        String answerForAttribut = "y";
        
               
			while(answerForTables.equalsIgnoreCase("y")) 
			{
				answerForTables = "";	
				out.println("Enter table name : ");
				userInput = helper.readString().trim(); // déjà un while dans le Helpers
				
				currentTable = new TableData(); 
				currentTable.setTableName(userInput);
				
				while(answerForAttribut.equalsIgnoreCase("y"))	
					
				{
					answerForAttribut = "";
					out.println("Enter attribute name : ");
					
					currentAttribut = helper.readString().trim();
					currentTable.pushInAttributeList(currentAttribut);
					
					answerForAttribut = helper.chooseContinueState();
					
					if(answerForAttribut.equalsIgnoreCase("n")) break;
					
				};
				
				listOfTables.add(currentTable);
				
				if(listOfTables.size() >= 1) { // si on a plus d'une table...
					out.println("Do you want to RE-enter a table ? : ");
				}
				
				answerForTables = helper.chooseContinueState(); // méthode de l'état de continue ou non dans les Helpers
				if(answerForTables.equalsIgnoreCase("n")) break;
				
			 }
			
	    	 out.println(currentTable.getTableName());
	    	 out.println(currentTable.getAttributeList());
	 }      
			      
}        
		
		
	



