// logs
import java.util.logging.Level; 
import java.util.logging.Logger; 
import java.util.logging.*; 

/**
 * GeneratorLogger => Class for log all types of message
 * @author Java Generator Team
 *
 */
public class GeneratorLogger {
	
	private static Logger logger = Logger.getLogger(GeneratorLogger.class.getName());
	
	/**
	 * logInfo() : Log information message (success exec, variable value and other stuff)
	 * @param methodName
	 * @param message
	 */
	public void logInfo(String methodName, String message) {
	  logger.log(Level.INFO, methodName + " : " + message);
	}
	
	/**
	 * logWarning() : Log warning message (no credentials, forget value etc)
	 * @param methodName
	 * @param message
	 */
	public void logWarning(String methodName, String message) {
		logger.log(Level.WARNING, methodName + " : " + message);
	}
	
	/**
	 * logError() : Log error messages (in case of crash, no connexion etc)
	 * @param methodName
	 * @param message
	 */
	public void logError(String methodName, String message) {
		logger.log(Level.SEVERE, methodName + " : " + message);
	}

}
