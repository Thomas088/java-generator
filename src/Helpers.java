import static java.lang.System.*;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author Utilisateur
 *
 */
public class Helpers {

	// DECLARE CONSTANTS (const => static final)
	// https://www.theserverside.com/video/Why-we-use-static-final-in-Java-for-constants#:~:text=The%20static%20keyword%20means%20the,to%20create%20a%20constant%20value
	private static final Scanner scanner = new Scanner(new InputStreamReader(System.in));
	private static final StringBuilder inputCurrentValue = new StringBuilder();
	private static final RegexRepertory regexRepertory = new RegexRepertory();
	private static final GeneratorLogger logger = new GeneratorLogger();
	private static Matcher matcher; // not final because different regex evaluated in different helpers 
	
	private static boolean isLetterFounded;
	private static boolean isNumberFounded;
	private static boolean isSpecialCharFounded;
	private static boolean isInvalid;
	private static boolean isSingleDotForDecimals;
	
	
//	private static final String newline = System.getProperty("line.separator"); // a voir si besoin par la suite

	// FOR TEMPLATE
	public String startTemplateInsert(String nameTable) {
	    return "INSERT INTO " + nameTable;
	}
	
	// MYSQL - MARIA
	
	public String transformToMySQLDate(int[] date) {
		
		String dateStr = "";
		
		for (Integer i = 0; i < date.length; i++) {
			dateStr += date[i] + ((i != date.length - 1) ? "-" : "");
		}
		
		return dateStr;
	}
	
	
	public String transformToMySQLDatetime(int[] date, int[] time) {
		
		String dateTimeStr = "";
		return ""; // TODO - A CREER
	} 
	
	// FOR USER PROMPT
	
	/**
	 * chooseContinueState() = method for ask to user for continue or not
	 * @return {String} the response (Yy/Nn)
	 */
	public String chooseContinueState() {
	
		while(true) {
			
			// tip for better performance if we use StringBuilder in a loop
			// https://stackoverflow.com/questions/242438/is-it-better-to-reuse-a-stringbuilder-in-a-loop
			inputCurrentValue.setLength(0); // or choice.delete(0, choice.length()) => choice = ""
			
			out.println("Do you want to continue ? (Yy / Nn)");
			inputCurrentValue.append(scanner.next().trim());
			
			if (inputCurrentValue.toString().length() == 1) {
				
				if (!inputCurrentValue.toString().equalsIgnoreCase("y") && !inputCurrentValue.toString().equalsIgnoreCase("n")) {
					logger.logWarning("chooseContinueState()", "Error bad value - invalid value entered - re-do.");
				} else {
					logger.logInfo("chooseContinueState()", "Value OK - You choosed : " + inputCurrentValue.toString());
					break;
				}
				
			} else {
				logger.logWarning("chooseContinueState()", "Bad value - re-do.");
			}
		}
		
		return inputCurrentValue.toString();
	}
	
	/**
	 * readInt() = method for ask to user for enter a int value
	 * @return {Integer} the result
	 */
	public Integer readInt() {
		
		while (true) {
			
			inputCurrentValue.setLength(0);
			
			out.println("Enter int value :");
			inputCurrentValue.append(scanner.next().trim());
			
			matcher = regexRepertory.getLettersPattern().matcher(inputCurrentValue.toString());
			isLetterFounded = matcher.find();
			
			matcher = regexRepertory.getSpecialSharactersPattern().matcher(inputCurrentValue.toString());
			isSpecialCharFounded = matcher.find();
			
			isInvalid = isLetterFounded || isSpecialCharFounded;
			
			if (isInvalid) {
				logger.logWarning("readInt()", "Error bad value - letter or special character founded - re-do.");
			} else if (inputCurrentValue.toString().length() == 0) {
				logger.logWarning("readInt()", "Empty value - re-do.");
			} else {
				break;
			}
		}
		
		Integer value = Integer.parseInt(inputCurrentValue.toString());
		logger.logInfo("readInt()", "Value OK - You defined : " + value);
		return value;
  }
	
	/**
	 * readFloat() = method for ask to user for enter a float value
	 * @return {Float} the result
	 */
	public Float readFloat() {
		
		Float value = 0.0f;
		
			while (true) {
				
				inputCurrentValue.setLength(0);
				
				out.println("Enter float value (only the '.' for the decimals) : ");
				inputCurrentValue.append(scanner.next().trim());
				
				matcher = regexRepertory.getLettersPattern().matcher(inputCurrentValue.toString());
				isLetterFounded = matcher.find();
				
				matcher = regexRepertory.getSpecialCharactersWithoutDotPattern().matcher(inputCurrentValue.toString());
				isSpecialCharFounded = matcher.find();
				
				// Check decimal dot character
				matcher = regexRepertory.getSingleDotForDecimals().matcher(inputCurrentValue.toString());
				isSingleDotForDecimals = matcher.find();
				
				if (isSingleDotForDecimals) {
					
					if (isLetterFounded || isSpecialCharFounded) {
						logger.logWarning("readFloat()", "Error bad value - letter or special character founded - re-do.");
					} else if (inputCurrentValue.toString().length() == 0) {
						logger.logWarning("readFloat()", "Empty value - re-do.");
					}  else {
						break;
					}
					
				} else {
					logger.logWarning("readFloat()", "You have to set ONLY one dot - re-do.");
				}
			}
		
		value = Float.parseFloat(inputCurrentValue.append("f").toString());
		logger.logInfo("readFloat()", "Value OK - You defined : " + value);
		return value;
	}
	
	/**
	 * readDouble() = method for ask to user for enter a double value
	 * @return {Double} the result
	 */
	public Double readDouble() {
		
		Double value = 0.0;
	
		while (true) {
			
			inputCurrentValue.setLength(0);
			
			out.println("Enter double value (only the '.' for the decimals) : ");
			inputCurrentValue.append(scanner.next().trim());
			
			matcher = regexRepertory.getLettersPattern().matcher(inputCurrentValue.toString());
			isLetterFounded = matcher.find();
			
			matcher = regexRepertory.getSpecialCharactersWithoutDotPattern().matcher(inputCurrentValue.toString());
			isSpecialCharFounded = matcher.find();
			
			// Check decimal dot character
			matcher = regexRepertory.getSingleDotForDecimals().matcher(inputCurrentValue.toString());
			isSingleDotForDecimals = matcher.find();
			
			if (isSingleDotForDecimals) {
				
				if (isLetterFounded || isSpecialCharFounded) {
					logger.logWarning("readDouble()", "Error bad value - letter or special character founded - re-do.");
				} else if (inputCurrentValue.toString().length() == 0) {
					logger.logWarning("readDouble()", "Empty value - re-do.");
				}  else {
					break;
				}
				
			} else {
				logger.logWarning("readDouble()", "You have to set ONLY one dot - re-do.");
			}
		}
		
		value = Double.parseDouble(inputCurrentValue.toString());
		logger.logInfo("readDouble()", "Value OK - You defined : " + value);
		return value;
	}
	
	/**
	 * readString() = Method for ask to user for enter a string value
	 * @return {String} the result
	 */
	public String readString() {
		
		while(true) {
			
			inputCurrentValue.setLength(0);
			
			out.println("Enter string value :");
			inputCurrentValue.append(scanner.next().trim());
			
			if (inputCurrentValue.toString().length() == 0) {
				err.println("Empty value - re-do.");
			} else {
				break;
			}	
		}
		
		logger.logInfo("readString()", "Value OK - You defined : " + inputCurrentValue.toString());
		return inputCurrentValue.toString();
	}
	
	/**
	 * readStringWithoutNumbers() = Method for ask to user for enter a string value (without numbers)
	 * @return {String} the result
	 */
	public String readStringWithoutNumbers() {
		
		while(true) {
			
			out.println("Type your string :");
			inputCurrentValue.append(scanner.next().trim());
			
			matcher = regexRepertory.getNumbersPattern().matcher(inputCurrentValue.toString());
			isNumberFounded = matcher.find();
			
			if (isNumberFounded) {			
				err.println("Error Bad value - number founded -  re-do.");		
			} else if (inputCurrentValue.toString().length() == 0) {
				err.println("Empty value - re-do.");
			} else {
				break;
			}
			
		}
		
		logger.logInfo("readStringWithoutNumbers()", "Value OK - You defined : " + inputCurrentValue.toString());
		return inputCurrentValue.toString();
	}
	
	/**
	 * readStringWithoutSpecialCharacters() = Method for ask to user for enter a string value (without special characters)
	 * @return {String} the result
	 */
	public String readStringWithoutSpecialCharacters() {
		
		while(true) {
			
			inputCurrentValue.setLength(0);
			
			out.println("Enter string value :");
			inputCurrentValue.append(scanner.next().trim());
				
			matcher = regexRepertory.getSpecialSharactersPattern().matcher(inputCurrentValue.toString());
			isSpecialCharFounded = matcher.find();
			
			if (isSpecialCharFounded) {
				logger.logWarning("readStringWithoutSpecialCharacters()", "Error bad value - special character founded - re-do.");
			} else if (inputCurrentValue.toString().length() == 0) {
				logger.logWarning("readStringWithoutSpecialCharacters()", "Empty value - re-do.");
			} else {
				break;
			}
		}
		
		logger.logInfo("readStringWithoutSpecialCharacters()", "Value OK - You defined : " + inputCurrentValue.toString());
		return inputCurrentValue.toString();
	}
	

	// MISC
	/**
	 * clearConsole() => clear the terminal console
	 * @throws {Exception}
	 */
	public static void clearConsole() throws Exception {
		
		out.println("SCREEN CLEARED");
		
	    try {

	     if (System.getProperty("os.name").contains("Windows")) {
	         new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	     	 out.println('\n');
	     } else {
	    	 Runtime.getRuntime().exec("clear");
	     }    
	    } catch (IOException | InterruptedException ex) {
	    	throw ex;
	    }
	}

}
