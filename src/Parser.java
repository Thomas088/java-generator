import static java.lang.System.*;
import java.io.File;
import java.util.Scanner;
import java.util.Vector;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Matcher;

/**
 * Parser => Class for parsing a JMerise or Looping .sql file
 * @author Java Generator Team
 *
 */
public class Parser {
	
    //	static final (java) = const (javascript, php, python...)
	private static final GeneratorLogger logger = new GeneratorLogger();
	private static final StringBuilder tableResult = new StringBuilder();
	private static final StringBuilder numberResult = new StringBuilder();
	private static final StringBuilder attrResult = new StringBuilder();
	private static final StringBuilder attrType = new StringBuilder();
	private static final StringBuilder currentAttr = new StringBuilder();
	private static final StringBuilder currentLine = new StringBuilder();
	private static final StringBuilder currentCharacter = new StringBuilder();
	private static final RegexRepertory regexRepertory = new RegexRepertory();
	private static final Vector<TableData> listOfTables = new Vector<TableData>();
	private static final String createTableStr = "CREATE TABLE ";
	private static final String openParenthese = "(";
	private static final String closeParenthese = ")";
	private static final String virgule = ",";
	
	// Not final because we want new arrays in parsing loop
	private static ArrayList<String> arrayAttributeTemp;
	private static ArrayList<String> arrayTypeTemp;
	private static ArrayList<String> arrayForeignKeysTemp;
	
	private static HashMap<String, ArrayList<String>> mapLinkAttributeAndDataTemp;
	
	private static boolean isNumberFounded;
	private static boolean isIntermediaryTable;
	private static Integer marker;
	private static Integer i;
	private static Matcher matcher;	
	private static String temp;
	private static String tableName;
	private static String attribute;
	private static String type;
	
	// INIT
	
	/**
	 * initStrings() : init all the StringBuilders
	 */
	private static void initStrings() {
		
		numberResult.setLength(0);
		tableResult.setLength(0);
		attrResult.setLength(0);
		currentAttr.setLength(0);
		currentLine.setLength(0);
		currentCharacter.setLength(0);
		attrType.setLength(0);
		
	}
	
	// ------------------ PARSER HELPERS ----------------------- // 

	/**
	 * isPrimaryKey = detect if the parsed line is for a primary key attribute
	 * Strategy = we have to lower case all the line before the check (toLowerCase())  
	 * @param {String} currentLine : the line to evaluate : the line to evaluate
	 * @return {boolean} the response
	 */
	private static boolean isPrimaryKey(String currentLine) {	
		return currentLine.trim().toLowerCase().contains("auto_increment");
	}
	
	/**
	 * isPrimaryKeyLineStart = detect if the parsed line start with primary key keyword
	 * Strategy = we have to lower case all the line before the check (toLowerCase())  
	 * @param {String} currentLine : the line to evaluate : the line to evaluate
	 * @return {boolean} the response
	 */
	private static boolean isPrimaryKeyLineStart(String currentLine) {	
		return currentLine.trim().toLowerCase().startsWith("primary key");
	}
	
	/**
	 * isforeignKeyLineStart = detect if the parsed line start with foreign key keyword
	 * Strategy = we have to lower case all the line before the check (toLowerCase())  
	 * @param {String} currentLine : the line to evaluate : the line to evaluate
	 * @return {boolean} the response
	 */
	private static boolean isforeignKeyLineStart(String currentLine) {	
		return currentLine.trim().toLowerCase().startsWith("foreign key");
	}
	
	/**
	 * isComposedPrimaryKey = detect if the parsed line is for a primary key composed attribute (more than 1 primary key)
	 * @param {String} currentLine : the line to evaluate : the line to evaluate
	 * @return {boolean} the response
	 */
	private static boolean isComposedPrimaryKey(String currentLine) {
		
		if (!isPrimaryKeyLineStart(currentLine)) {
			return false;
		}

		currentCharacter.setLength(0);
		
		for (int i = 0; i < currentLine.length(); i++) {
			
			currentCharacter.append(Character.toString(currentLine.charAt(i)));
			
			if (!isBlankSpace(currentCharacter.toString())) {
				
				if (currentCharacter.toString().equals(openParenthese)) {
					marker = i; // start primary key list after the parenthese
				}
					
				if (i > marker && (i != (currentLine.length() - 1))) { // we are in primary key list
					
					if (currentCharacter.toString().equals(virgule)) {
						return true; // We don't search anymore - the next comma is another primary key or after close parentheses
					}  	
				}
			}
			currentCharacter.setLength(0); // for each character in the line
		}
		
		return false;
	}
	
	/**
	 * isConstraintLine = detect if the parsed line is for a constraint (PK, FK)
	 * Strategy = we have to lower case all the line before the check (toLowerCase())  
	 * @param {String} currentLine : the line to evaluate
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
	 * Strategy = we have to lower case all the line before the check (toLowerCase())  
	 * @param {String} currentLine : the line to evaluate
	 * @return {boolean} the response
	 */
	private static boolean isNullAttributeLine(String currentLine) {
		
		currentLine = currentLine.trim().toLowerCase();
		
		return currentLine.contains("null") && 
			  !currentLine.contains("not null");
	}
	
	/**
	 * isNullAttributeLine = detect if the parsed line is not null
	 * Strategy = we have to lower case all the line before the check (toLowerCase())  
	 * @param {String} currentLine : the line to evaluate
	 * @return {boolean} the response
	 */
	private static boolean isNotNullAttributeLine(String currentLine) {	
		return currentLine.trim().toLowerCase().contains("not null");
	}
	
	/**
	 * isForeignKey = detect if the parsed line is for a foreign key
	 * Strategy = we have to lower case all the line before the check (toLowerCase())  
	 * @param {String} currentLine : the line to evaluate
	 * @return {boolean} the response
	 */
	private static boolean isForeignKey(String currentLine) {
		return currentLine.trim().toLowerCase().contains("foreign key");
	}
	
	/**
	 * isCommentLine = detect if the parsed line is for a comment
	 * @param {String} currentLine : the line to evaluate
	 * @return {boolean} the response
	 */
	private static boolean isCommentLine(String currentLine) { 
		return currentLine.trim().startsWith("#");
	}
	
	/**
	 * isLineEmpty = detect if the parsed line is empty
	 * @param {String} currentLine : the line to evaluate
	 * @return {boolean} the response
	 */
	private static boolean isLineEmpty(String currentLine) { 
		return currentLine.length() == 0;
	}
	
	/**
	 * isEndOfTable = detect if the parsed line is a end of table
	 * @param {String} currentLine : the line to evaluate
	 * @return {boolean} the response
	 */
	private static boolean isEndOfTable(String currentLine) { 
		return currentLine.trim().toLowerCase().startsWith(")engine=innoDB;") || 
			   currentLine.trim().contains(");");
	}
	
	/**
	 * isBoolean = detect if the parsed line is a boolean attribute (format: 'boolean' | 'bool')
	 * @param {String} currentLine : the line to evaluate
	 * @return {boolean} the response 
	 */
	private static boolean isBooleanAttr(String currentLine) { 	
		return currentLine.toLowerCase().trim().contains("BOOLEAN") || 
			   currentLine.toLowerCase().trim().contains("BOOL");
	}
	

	// ------------------ GENERAL METHODS ----------------------- // 
	
	// ------------------ TABLES ----------------------- //
	
	/**
	 * getTablenameByIndex => get the table name(s) of generated .SQL or text file (works for Jmerise) - Search by string indexes
	 * @param {String} currentLine : the line to evaluate
	 * @return {String} The tablename
	 */
	private static String getTablenameByIndex(String currentLine) {
		
		initStrings();
		
		 for (int i = 0; i < currentLine.length(); i++) {

			 currentCharacter.append(Character.toString(currentLine.charAt(i)));
			 
			    // Si l'on tombe apres le CREATE TABLE et l'espace	 
			    if (i >= createTableStr.length()) {
	
			        // ... ET si l'on ne tombe pas sur la openParenthese ouvrante
			        if (!(currentCharacter.toString().equals(openParenthese))) {
			                 tableResult.append(currentCharacter.toString());
			        } else {
			        	break; // force end loop if we match the parenthesis or other stuffs such as empty spaces etc 
			        }
			     }
			    
			    currentCharacter.setLength(0);
			 }
		 
		return tableResult.toString().trim();
	}
	
	/**
	 * getTablenameByRegex => get the table name(s) of generated .SQL or text file - Search by regular expression (regex)
	 * @param {String} currentLine : the line to evaluate
	 * @return {String} The tablename
	 */
	private static String getTablenameByRegex(String currentLine) {
		
		initStrings();
		
		matcher = regexRepertory.getCreateTablePattern().matcher(currentLine);
         
        if (matcher.find()) {
        	tableResult.append(currentLine.replaceAll(createTableStr, "").replace(openParenthese, "").trim()) ;
        } else {
        	logger.logWarning("getTablenameByRegex()", "No match.");
        }
		
        return tableResult.toString();
	}
	
	/**
	 * isBlankSpace() => Detect that current character is an empty space (or tab).
	 * @param strCharacter
	 * @return {boolean} the response
	 */
	private static boolean isBlankSpace(String strCharacter) {
		return strCharacter.toString().equals(" ") || 
			   strCharacter.toString().equals("\t"); // tabulation 
	}
	
	// ------------------ ATTRIBUTES ----------------------- //
	
	/**
	 * getAttributeName = parse the line for get the current attribute of a table
	 * @param {String} currentLine : the line to evaluate
	 * @return {String} the attribute parsed
	 */
	private static String getAttributeName(String currentLine) {
	
		initStrings();

		for (int i = 0; i < currentLine.length(); i++) {
			
			currentCharacter.append(currentLine.charAt(i));		
			
			if (!isBlankSpace(currentCharacter.toString())) {
				
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
	 * @param {String} currentLine : the line to evaluate
	 * @return {String} the attribute type
	 */
	private static String getTypeOfAttribute(String currentLine) {
		
		if (isLineEmpty(currentLine)) {
			logger.logError("getLengthOfVarchar()", "Empty line.");
			return "";
		}
		
		initStrings();
		
		for (EnumList.MariaAttributeTypesListEnum attribute : EnumList.MariaAttributeTypesListEnum.values()) {
			
			currentAttr.append(attribute.toString().toLowerCase().trim());
			
			if (currentLine.toLowerCase().contains(currentAttr.toString())) {
				attrResult.append(currentAttr.toString());
				break;
			}	
			
			currentAttr.setLength(0);
		}
		
		return attrResult.toString().trim();
	}
	
	/**
	 * getLengthOfVarcharByIndex() => Get the length of the current attribute (INDEX VERSION)
	 * @param currentLine
	 * @return
	 */
	private static Integer getLengthOfVarcharByIndex(String currentLine) {
		
		if (isLineEmpty(currentLine)) {
			logger.logError("getLengthOfVarchar()", "Empty line.");
			return 0;
		}
		
		initStrings();
		marker = 9999; // Set the market on the first time like this (for later)
		
		String strTemp = getTypeOfAttribute(currentLine.toLowerCase().trim());
		currentAttr.setLength(0);
		currentAttr.append(strTemp);
		
		String varchar = EnumList.MariaAttributeTypesListEnum.VARCHAR.toString();
		
			if (currentAttr.toString().equals(varchar)) {
		
				for (int i = 0; i < currentLine.length(); i++) {
					
					if (Character.toString(currentLine.charAt(i)).equals(openParenthese)) {
						marker = i; // Set the new marker here
					}  
					
					if (i > marker && !Character.toString(currentLine.charAt(i)).equals(closeParenthese)) {
						numberResult.append(Character.toString(currentLine.charAt(i)));
					}
					
					if (Character.toString(currentLine.charAt(i)).equals(closeParenthese)) {
						break; // We already get the length	
					}
				}
			
			}
			
		return Integer.parseInt(numberResult.toString().trim());
	}
	
	/**
	 * getLengthOfVarcharByRegex() => Get the length of the current attribute (REGEX VERSION)
	 * @param currentLine
	 * @return
	 */
	private static Integer getLengthOfVarcharByRegex(String currentLine) {
		
		if (isLineEmpty(currentLine)) {
			logger.logError("getLengthOfVarchar()", "Empty line.");
			return 0;
		}
	
		String strTemp = getTypeOfAttribute(currentLine.toLowerCase().trim());
		currentAttr.setLength(0);
		currentAttr.append(strTemp);
		
		String varchar = EnumList.MariaAttributeTypesListEnum.VARCHAR.getType();
		
			if (currentAttr.toString().equals(varchar)) {
				
				matcher = regexRepertory.getNumbersPattern().matcher(currentLine);
				
				if (matcher.find()) { // number founded
					numberResult.append(matcher.group(0));
				}		
			}
			
		return Integer.parseInt(numberResult.toString().trim());
	}
	
	private static void printAttributeDatas(String currentLine) {
		
    	boolean isNull = isNullAttributeLine(currentLine);
    	boolean isKey = isPrimaryKey(currentLine) || isForeignKey(currentLine);
    	
    	currentAttr.append(getAttributeName(currentLine));
    	
		out.println("Attribute name : " + getAttributeName(currentLine));
		out.println("Attribute type : " + getTypeOfAttribute(currentLine));
		out.println("Attribute NULL ? : " + (isKey || !isNull));
		
		if (currentAttr.toString().toLowerCase().equals(EnumList.MariaAttributeTypesListEnum.VARCHAR.getType().toLowerCase())) {
			out.println("Attribute length (INDEX) : " + getLengthOfVarcharByIndex(currentLine));
			out.println("Attribute length (REGEX) : " + getLengthOfVarcharByRegex(currentLine));
		}
	}
	
	public static String getTableName(String currentLine) {
		
    	if (currentLine.toString().startsWith(createTableStr)) {
  
    		out.println("------------------------------------");
    		out.println("Table name : " + getTablenameByIndex(currentLine.toString()));
    		out.println("------------------------------------");
    		
    	}
    	
    	return getTablenameByIndex(currentLine.toString());
	}
	
	
	/**
	 * getComposedPrimaryKeys = get if the primary keys from intermediary table (pivot)
	 * @param {String} currentLine : the line to evaluate : the line to evaluate
	 * @return {boolean} the response
	 */
	private static ArrayList<String> getComposedPrimaryKeys(String currentLine) {
		
		if (!isPrimaryKeyLineStart(currentLine)) {
			return arrayAttributeTemp;
		}

		currentCharacter.setLength(0);
		
		for (int i = 0; i < currentLine.length(); i++) {
			
			if (!isBlankSpace(currentCharacter.toString())) {
				
				if (currentCharacter.toString().equals(openParenthese)) {
					marker = i; // start primary key list after the parenthese
				}
					
				if (i > marker && (i != (currentLine.length() - 1))) { // we are in primary key list (between parenthesis)
					
					if (!currentCharacter.toString().equals(virgule)) {			
						currentAttr.append(Character.toString(currentLine.charAt(i)));
					} else {
						arrayAttributeTemp.add(currentAttr.toString());
					}
					
					if (currentCharacter.toString().equals(closeParenthese)) {
						 break; // We don't search anymore - we have all keys
					}  		
				}
			}
			currentCharacter.setLength(0); // for each character in the line
		}
		
		return arrayAttributeTemp;
	}
	
	public static void printArrayTableData(Vector<TableData> array) {
		
		if (array.size() == 0) {
			return;
		}
		
		Iterator<TableData> i = array.iterator();
	
	    while (i.hasNext()) {
	    	  
	    	TableData currentTable = ((TableData) i.next());
	    	  
        	out.println("------------------------------------");
        	out.println("tableName : " + currentTable.getTableName());
        	out.println("isPivot : " + currentTable.isIntermediaryTable());
        	out.println("attributeList : " + currentTable.getAttributeList());
        	out.println("typesList : " + currentTable.getTypesList());
        	out.println("foreignKeyList : " + currentTable.getForeignKeyList());
	    }
	}
	
	public static boolean isDocumentValid(File currentFile) {
		
        // [file] check if all is well configured
        if(!currentFile.exists()) {
        	
        	logger.logError("parse()", "The file not exists.");
        	return false;
        	
        } else if (!currentFile.canRead()) {
        	
        	logger.logError("parse()", "The file is unreadable.");
        	return false;
        }
        
        return true;
	}
	
	// --------------------- PARSE --------------------- //
	
	public static Vector<TableData> parse(String path) {
		
	  try {
			
	        File sqlFile = new File(path);   
	        Scanner scanner = new Scanner(sqlFile);
	        
	        if (!isDocumentValid(sqlFile)) {
	        	scanner.close();
	        	return listOfTables;
	        } 
	         
	        i = 1; // we start like an classic document opned in editor
	        marker = 9999;
	    	TableData newTable = null;
	    	
	    	// init
	        initStrings();
	        listOfTables.clear();
	      
	        while(scanner.hasNextLine()) {
	        	
	        	// reset for the next line
	        	currentLine.setLength(0);
	        	currentLine.append(scanner.nextLine());
	        	
	        	temp = currentLine.toString();
	        	
	        	if (!isCommentLine(temp) && !isLineEmpty(temp)) {
	        		
	            	if (temp.startsWith(createTableStr)) {          		
	            		tableName = getTablenameByRegex(temp);
	            		marker = i;
	            		
			  	    	// init arrays for the next table
			  	    	arrayAttributeTemp = new ArrayList<String>();
			  	    	arrayTypeTemp = new ArrayList<String>();
			  	    	
//			  	    	mapAttributeDataTemp = new HashMap<String, ArrayList<String>>();
		        	} 
	            	
	            	if (i > marker) { // we are in attribute list (because attribute is marker + 1)
	            		
		            	if(isConstraintLine(temp)) {
		            		
	            			if (isComposedPrimaryKey(temp)) {
	            				arrayForeignKeysTemp = getComposedPrimaryKeys(temp);
	            				isIntermediaryTable = true;
	            			}
		            		
			            } else {
			            	
		            		if(!isEndOfTable(temp)) { 
		                		
			            		attribute = getAttributeName(temp);
			            		type = getTypeOfAttribute(temp);
			            		arrayAttributeTemp.add(attribute);
			            		arrayTypeTemp.add(type);
			            		
			            	} else {
			            		
		            			// Prepare new tableData and push to list
			            		newTable = new TableData();
			            		
			            		newTable.setIntermediaryTable(isIntermediaryTable);
			            		newTable.setTableName(tableName);    	
			            		newTable.setAttributeList(arrayAttributeTemp);
			            		newTable.setTypesList(arrayTypeTemp);
			            		
			            		if(isIntermediaryTable) {
			            			newTable.setForeignKeyList(arrayForeignKeysTemp);
			            		}
			            		          		
			            		listOfTables.add(newTable);
			            		marker = 0; // Reset for the next new table	
			            	} 
			            }
	            	}	
	        	}
	        	
	          i++;
	        }
	        
			scanner.close();
			return listOfTables;
			
		} catch (Exception e) {
			logger.logError("parse()", e.getMessage());
		}
	  
	  return listOfTables;
	}
 }  
