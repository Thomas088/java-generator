import static java.lang.System.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Parser => Class for parsing a JMerise of Looping .sql file
 * @author Utilisateur
 *
 */
public class Parser {
	
	private static final Helpers helper = new Helpers();
	private static final GeneratorLogger logger = new GeneratorLogger();
	private static final String createTableStr = "CREATE TABLE ";
	private static final String parenthese = "(";
	private static final StringBuilder tableResult = new StringBuilder();
	private static final StringBuilder attrResult = new StringBuilder();
	private static final StringBuilder currentAttr = new StringBuilder();
	private static final StringBuilder currentLine = new StringBuilder();
	private static final StringBuilder currentCharacter = new StringBuilder();
	
	// INIT
	
	/**
	 * initStrings() : init all the StringBuilders
	 */
	private static void initStrings() {
		
		tableResult.setLength(0);
		attrResult.setLength(0);
		currentAttr.setLength(0);
		currentCharacter.setLength(0);
		currentLine.setLength(0);
	}
	
	// ------------------ PARSER HELPERS ----------------------- // 

	/**
	 * isPrimaryKey = detect if the parsed line is for a primary key attribute
	 * Strategy = we have to lower case all the line for a check (toLowerCase())  
	 * @param {String} currentLine
	 * @return {boolean} the response
	 */
	private static boolean isPrimaryKey(String currentLine) {
		return currentLine.trim().toLowerCase().contains("auto_increment");
	}
	
	/**
	 * isConstraintLine = detect if the parsed line is for a constraint (PK, FK)
	 * Strategy = we have to lower case all the line for a check (toLowerCase())  
	 * @param {String} currentLine
	 * @return {boolean} the response
	 */
	private static boolean isConstraintLine(String currentLine) {
		
		currentLine = currentLine.trim().toLowerCase();
		
		return currentLine.contains("constraint") || 
			   currentLine.contains("primary key") || 
			   currentLine.contains("foreign key") ||
			   currentLine.contains("references");
	}
	
	/**
	 * isNullAttributeLine = detect if the parsed line is null
	 * Strategy = we have to lower case all the line for a check (toLowerCase())  
	 * @param {String} currentLine
	 * @return {boolean} the response
	 */
	private static boolean isNullAttributeLine(String currentLine) {
		return currentLine.trim().toLowerCase().contains("null");
	}
	
	/**
	 * isNullAttributeLine = detect if the parsed line is not null
	 * Strategy = we have to lower case all the line for a check (toLowerCase())  
	 * @param {String} currentLine
	 * @return {boolean} the response
	 */
	private static boolean isNotNullAttributeLine(String currentLine) {
		return currentLine.trim().toLowerCase().contains("not null");
	}
	
	/**
	 * isForeignKey = detect if the parsed line is for a foreign key
	 * Strategy = we have to lower case all the line for a check (toLowerCase())  
	 * @param {String} currentLine
	 * @return {boolean} the response
	 */
	private static boolean isForeignKey(String currentLine) {
		return currentLine.trim().toLowerCase().contains("foreign key");
	}
	
	/**
	 * isCommentLine = detect if the parsed line is for a comment
	 * @param {String} currentLine
	 * @return {boolean} the response
	 */
	private static boolean isCommentLine(String currentLine) { 
		return currentLine.trim().startsWith("#");
	}
	
	/**
	 * isLineEmpty = detect if the parsed line is empty
	 * @param {String} currentLine
	 * @return {boolean} the response
	 */
	private static boolean isLineEmpty(String currentLine) { 
		return currentLine.length() == 0;
	}
	
	/**
	 * isEndOfTable = detect if the parsed line is a end of table
	 * @param {String} currentLine
	 * @return {boolean} the response
	 */
	private static boolean isEndOfTable(String currentLine) { 
		return currentLine.trim().startsWith(")ENGINE=InnoDB;") || currentLine.trim().contains(");");
	}
	
	
	// ------------------ GENERAL METHODS ----------------------- // 
	
	// ------------------ TABLES ----------------------- //
	
	/**
	 * getTablenameByIndex => get the table name(s) of generated .SQL or text file (works for Jmerise) - Search by string indexes
	 * @param {String} currentLine
	 * @return {String} The tablename
	 */
	private static String getTablenameByIndex(String currentLine) {
		
		initStrings();
		
		 for (int i = 0; i < currentLine.length(); i++) {

			 currentCharacter.append(Character.toString(currentLine.charAt(i)));
			 
			 // Si l'on tombe apres le CREATE TABLE et l'espace	 
			    if (i >= createTableStr.length()) {
	
			        // ... ET si l'on ne tombe pas sur la parenthese ouvrante
			        if (!(currentCharacter.toString().equals(parenthese))) {
			                 tableResult.append(currentCharacter.toString().trim());
			        } else {
			        	break; // force end loop if we match the parenthesis or other stuffs such as empty spaces etc 
			        }
			     }
			    
			    currentCharacter.setLength(0);
			 }
		 
		return tableResult.toString();
	}
	
	/**
	 * getTablenameByRegex => get the table name(s) of generated .SQL or text file - Search by regular expression (regex)
	 * @param {String} currentLine
	 * @return {String} The tablename
	 */
	private static String getTablenameByRegex(String currentLine) {
		
		initStrings();
		
		Pattern createTablePattern = Pattern.compile(createTableStr, Pattern.CASE_INSENSITIVE);
        Matcher createTableMatcher = createTablePattern.matcher(currentLine);
         
        if (createTableMatcher.find()) {
        	tableResult.append(currentLine.replaceAll(createTableStr, "").replace(parenthese, "").trim()) ;
        }
		
        return tableResult.toString();
	}	
	
	
	// ------------------ ATTRIBUTES ----------------------- //
	
	/**
	 * getAttributeName = parse the line for get the current attribute of a table
	 * @param {String} currentLine
	 * @return {String} the attribute parsed
	 */
	private static String getAttributeName(String currentLine) {
		
		initStrings();
		
		for (int i = 0; i < currentLine.length(); i++) {
			
			currentCharacter.append(currentLine.charAt(i));
			
			boolean isEmptySpace = Character.compare(currentCharacter.toString().charAt(0), ' ') == 0 || 
					    		   Character.compare(currentCharacter.toString().charAt(0), '\t') == 0;
			
			if (!isEmptySpace) {
				
				currentAttr.append(currentLine.charAt(i));	
					
				if (currentLine.charAt(i+1) == ' ') {
					break; // We have finished of parse the attribute name
				}
			}
			
			currentCharacter.setLength(0); // always re-init for the next char
		}
		
		return currentAttr.toString().trim();
	}
	
	/**
	 * getTypeOfAttribute = detect in the parsed line the specified attribute key (comparision with the mariaTypesList enum values)
	 * @param {String} currentLine
	 * @return {String} the attribute type
	 */
	private static String getTypeOfAttribute(String currentLine) {
		
		initStrings();
		
		for (EnumList.mariaAttributeTypesListEnum attribute : EnumList.mariaAttributeTypesListEnum.values()) {
			
			currentAttr.append(attribute.toString().toLowerCase().trim());
			
			if (currentLine.toLowerCase().contains(currentAttr.toString())) {
				attrResult.append(currentAttr.toString());
				break;
			}	
			
			currentAttr.setLength(0);
		}
		
		return attrResult.toString();
	}
	
	private static void printTableDatas(String currentLine) {
		
    	boolean isNull = isNullAttributeLine(currentLine) || !isNotNullAttributeLine(currentLine);
    	boolean isKey = isPrimaryKey(currentLine) || isForeignKey(currentLine);
    	
		out.println("Attribute name : " + getAttributeName(currentLine));
		out.println("Attribute type : " + getTypeOfAttribute(currentLine));
		out.println("Attribute NULL ? : " + ( (isKey || !isNull) ? "No" : "Yes") );
	}
	
	
	// --------------------- PARSE --------------------- //
	
	public static void parseTEST() {
		
		try {
			
	        File jMeriseSQL = new File("./labo-test/DBQ10.sql");
	        Scanner scanner = new Scanner(jMeriseSQL);
	        
	        int i = 1;
	        int startMark = 1;
	        int nbTables = 0;
	        
	        initStrings();
	       
	        while(scanner.hasNextLine()) {
	        	
	        	currentLine.append(scanner.nextLine());
	        	
	        	if (!isCommentLine(currentLine.toString()) && !isLineEmpty(currentLine.toString())) {
	        		
	            	if (currentLine.toString().startsWith(createTableStr)) {
	            		
	            		nbTables++;
	            		out.println("------------------------------------");
	            		out.println("Table number " + nbTables + " : " + getTablenameByIndex(currentLine.toString()));
	            		out.println("------------------------------------");
		        		startMark = i;
		        	}
	            	
		        	if (i > startMark) {
		        		
			        	if (isEndOfTable(currentLine.toString())) {
			        		break;
		        	    }
			        	
			        	String temp = currentLine.toString();
			        	        	
	    				if(!isConstraintLine(temp)) {			
	    					printTableDatas(temp);
	    				}

		        	}	
	        	}
	        	
	        	i++;
	        	currentLine.setLength(0);
	        }
			
		} catch (Exception e) {
			logger.logError("parsingTEST()", e.getMessage());
		}
	}    
 }  
