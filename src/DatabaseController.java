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

// logs
import java.util.logging.Level; 
import java.util.logging.Logger; 
import java.util.logging.*; 

import static java.lang.System.*;

	public class DatabaseController {
		
		private static String databaseName ="fake_database";
		private static String url = "jdbc:mariadb://localhost:3306/";
		private static String user ="root";
		private static String pwd ="password01";
		
		private static Connection connection;
		private static PreparedStatement statement;
			
		public void callSearchDatasProcedure(String tableToSearch, int limit) {
			
			Vector<String> dataVector = new Vector<String>();
				
			try {	
				
					statement = connection.prepareStatement("{call search_datas(?,?);}");
					
					statement.setString(1, tableToSearch);
					statement.setInt(2, limit);
					
					if(!statement.isClosed()) {
						
						ResultSet datas = statement.executeQuery();
			
						while (datas.next()) {
							dataVector.add(datas.getString(1));
						}
						
						out.println("SUCCESS : \n");
						out.println(dataVector);
						
					} else {
						out.println("STATEMENT NOT OPEN");
					}		
				
			} catch (SQLException e) {
//				e.printStackTrace(); -- TODO : creer la classe Logger				
			}		
		}
		
		
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
					e.printStackTrace();
				}
			
		    return isSuccess;
		}	
    }

