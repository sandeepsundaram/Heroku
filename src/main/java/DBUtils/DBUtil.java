package DBUtils;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {

	public static String action() throws Exception{

		Connection connection = null;
		try {
			connection= getConnection();

			DatabaseMetaData dbmd = connection.getMetaData();

			ResultSet schemas = dbmd.getSchemas();
			schemas.next();

			ResultSetMetaData rsMetaData = schemas.getMetaData();
			int numberOfColumns = rsMetaData.getColumnCount();
			System.out.println(">> resultSet MetaData column Count= " + numberOfColumns);

			for (int i = 1; i <= numberOfColumns; i++) {
				System.out.println(">> Schema Column Name " +rsMetaData.getColumnName(i));	
				System.out.println(">> Schema Column Value " +schemas.getString(i));
			}

			System.out.println(">> Tables");
			ResultSet tables = dbmd.getTables("", "", "", null);
			while (tables.next()) {
				System.out.println(">> Tables " +tables.getString(3));
			}

			System.out.println("Connection to "+dbmd.getDatabaseProductName()+" "+
					dbmd.getDatabaseProductVersion()+" successful.\n");

			Statement stmt = connection.createStatement();
			String createTable = "CREATE TABLE USER (id varchar(10), name varchar(50), dob varchar(50))";
			stmt.executeUpdate(createTable);
			
			stmt.executeUpdate("insert into user (dob, name, id) values ('08-04-2012', 'Sandeep', '2223')");

		} catch(Exception e) { 
			e.printStackTrace();
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