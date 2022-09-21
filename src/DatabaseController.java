import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import static java.lang.System.*;


	public class DatabaseController {
		
		public void createConnection () {
			
			try {
				
				Connection connection = null;
				String databaseName ="insert";
				String url = "jdbc:mariadb://localhost:3306/";
				String user ="root";
				String pwd ="Titi1234";

			    connection = DriverManager.getConnection(url
			    										+ databaseName 
			    										+ "?user="+ user 
			    										+ "&password="+ pwd);

				out.println("Succes");

				} catch (SQLException e) {
					e.printStackTrace();				
				}
		}	
    }

// TODO : Importer la JDBC ! (gestionnaire de requetes)
// Tous les appels procedures doivent etre dans des try - catch !