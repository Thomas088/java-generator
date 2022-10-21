// logs
import java.util.logging.Level; 
import java.util.logging.Logger; 
import java.util.logging.*; 

public class GeneratorLogger {
	
	private Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
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
