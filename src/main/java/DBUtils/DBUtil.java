package DBUtils;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {


	
	public static String action() throws Exception{

		Connection connection = null;
		try {
			connection= getConnection();
			
			Statement stmt = connection.createStatement();
			stmt.executeUpdate("CREATE TABLE USER (id varchar(10), name varchar(50), dob varchar(50))");
			stmt.executeUpdate("insert into user (dob, name, id) values ('08-04-2012', 'Sandeep', '2223')");
			
		} catch(Exception e) { 
			throw e;
		}finally {
			if(connection != null)
				connection.close();
		}
		
		return null;
	}

	private static Connection getConnection() throws URISyntaxException, SQLException, ClassNotFoundException {
		URI dbUri = new URI(System.getenv("DATABASE_URL"));

		String username = dbUri.getUserInfo().split(":")[0];
		String password = dbUri.getUserInfo().split(":")[1];
		String dbName = dbUri.getPath();
		String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbName;
		
		Class.forName("org.postgresql.Driver"); 
		return DriverManager.getConnection(dbUrl, username, password);
	}

}