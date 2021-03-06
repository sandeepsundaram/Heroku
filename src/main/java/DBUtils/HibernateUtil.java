package DBUtils;

import java.net.URI;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.SettingsFactory;

import api.User;

public class HibernateUtil {

	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {

		try {
			URI dbUri = new URI(System.getenv("DATABASE_URL"));

			String username = dbUri.getUserInfo().split(":")[0];
			String password = dbUri.getUserInfo().split(":")[1];
			String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();
			
			System.out.println(dbUrl);

			SessionFactory cfg = new AnnotationConfiguration()
			.addPackage("api")
			.addAnnotatedClass(User.class)

			.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver")
			.setProperty("hibernate.connection.url", dbUrl)
			.setProperty("hibernate.connection.username", username)
			.setProperty("hibernate.connection.password", password)
			
			.setProperty("hibernate.c3p0.max_size","1")
			.setProperty("hibernate.c3p0.min_size","0")
			.setProperty("hibernate.c3p0.timeout","5000")
			.setProperty("hibernate.c3p0.max_statements","100")
			.setProperty("hibernate.c3p0.idle_test_period","300")
			.setProperty("hibernate.c3p0.acquire_increment","2")
			
			.setProperty("hibernate.format_sql", "true")
			.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect")
			.configure().buildSessionFactory();
			
			return cfg;

		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("ERROR >>>>>> "+ e.getMessage());
		}

		return null;
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}