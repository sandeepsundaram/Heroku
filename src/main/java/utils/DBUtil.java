package utils;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {

	public static String action() throws Exception{

		Connection connection = null;
		try {
			connection= getConnection();

			Statement stmt = connection.createStatement();
			stmt.executeUpdate("DROP TABLE IF EXISTS ticks");
			stmt.executeUpdate("CREATE TABLE ticks (tick timestamp)");
			stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
			ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");
			while (rs.next()) {
				return String.valueOf(rs.getTimestamp("tick"));
			}
			
		} catch(Exception e) { 
		
		}finally {
			if(connection != null)
				connection.close();
		}
		
		return null;
	}

	private static Connection getConnection() throws URISyntaxException, SQLException {
		URI dbUri = new URI(System.getenv("DATABASE_URL"));

		String username = dbUri.getUserInfo().split(":")[0];
		String password = dbUri.getUserInfo().split(":")[1];
		String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();

		return DriverManager.getConnection(dbUrl, username, password);
	}

}
