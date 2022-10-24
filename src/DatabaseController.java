// db config
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;

// statements
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// datas
import java.util.Vector;

import static java.lang.System.*;

/**
 * DatabaseController => The class who interact with the database
 * @author Java Generator Team
 *
 */
	public class DatabaseController {
		
		// Credentials (in clear for the moment)
		private static final String databaseName ="fake_database";
		private static final String url = "jdbc:mariadb://localhost:3306/";
		private static final String user ="root"; // The main user in MariaDB
		private static final String pwd ="password01"; // Type your password
		
		// Utils
		private static final GeneratorLogger logger = new GeneratorLogger();
		private static final Vector<String> dataVector = new Vector<String>();
		
		// DB
		private static Connection connection;
		private static PreparedStatement statement;
		
		/**
		 * callSearchDatasProcedure() - Call the main procedure in database
		 * @param {String} tableToSearch => the table to seach in database
		 * @param {int} limit
		 */
		public void callSearchDatasProcedure(String tableToSearch, int limit) {
				
			try {	
				
					statement = connection.prepareStatement("{call search_datas(?,?);}");
					
					statement.setString(1, tableToSearch);
					statement.setInt(2, limit);
					
					if(!statement.isClosed()) {
						
						ResultSet datas = statement.executeQuery();
			
						while (datas.next()) {
							dataVector.add(datas.getString(1).trim());
						}
						
						out.println("SUCCESS : \n");
						out.println(dataVector);
						
					} else {
						out.println("STATEMENT NOT OPEN");
					}		
				
			} catch (SQLException e) {
				logger.logError("callSearchDatasProcedure()", e.getMessage()); 			
			}		
		}
		
		/**
		 * callGenerateRandomNumber() - Call the random number procedure
		 * @param {int} min
		 * @param {int} max
		 */
		public void callGenerateRandomNumber(int min, int max) {
							
			try {	
				
					statement = connection.prepareStatement("{call generate_random_number(?,?);}");
					
					statement.setInt(1, min);
					statement.setInt(2, max);
					
					if(!statement.isClosed()) {
						
						ResultSet datas = statement.executeQuery();
						
						out.println("SUCCESS : \n");
						
						while (datas.next()) out.println(datas.getInt(1));
						
					} else {
						out.println("STATEMENT NOT OPEN");
					}		
				
			} catch (SQLException e) {
				logger.logError("callGenerateRandomNumber()", e.getMessage()); 			
			}		
		}
		
		/**
		 * createConnection() : create a session connexion to mariaDB 
		 * @return {boolean} : if the connection success or fail
		 */
		public boolean createConnection() {
			
			boolean isSuccess = false;

			try {

			    	connection = DriverManager.getConnection(url
			    										+ databaseName 
			    										+ "?user="+ user 
			    										+ "&password="+ pwd);
			    
			    	if (connection.isValid(5)) isSuccess = true;
			
				} catch (SQLException e) {
					isSuccess = false;
					logger.logError("createConnection()", e.getMessage());
				}
			
		    return isSuccess;
		}	
    }

