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
	
	public void logInfo(String methodName, String message) {
	  logger.log(Level.INFO, methodName + " : " + message);
	}
	
	public void logWarning(String methodName, String message) {
		logger.log(Level.WARNING, methodName + " : " + message);
	}
	
	public void logError(String methodName, String message) {
		logger.log(Level.SEVERE, methodName + " : " + message);
	}

}
