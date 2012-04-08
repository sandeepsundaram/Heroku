package DBUtils;

import java.net.URI;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {

		try {
			URI dbUri = new URI(System.getenv("DATABASE_URL"));

			String username = dbUri.getUserInfo().split(":")[0];
			String password = dbUri.getUserInfo().split(":")[1];
			String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();
			
			System.out.println(dbUrl);

			Configuration cfg = new Configuration()
			.addClass(api.User.class)

			.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver")
			.setProperty("hibernate.connection.url", dbUrl)
			.setProperty("hibernate.connection.username", username)
			.setProperty("hibernate.connection.password", password)
	
			.setProperty("hibernate.format_sql", "true")
			.setProperty("hibernate.hbm2ddl.auto", "update")
			.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");

			return cfg.buildSessionFactory();
		} catch(Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}