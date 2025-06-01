package dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.sql.Connection;
import java.sql.DriverManager;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;

    static {
        try {
            System.out.println("Loading Hibernate configuration...");
            Configuration config = new Configuration().configure();
            System.out.println("Hibernate dialect: " + config.getProperty("hibernate.dialect"));
            System.out.println("Using driver: " + config.getProperty("hibernate.connection.driver_class"));
            System.out.println("JDBC URL: " + config.getProperty("hibernate.connection.url"));
            System.out.println("Driver version: " + com.mysql.cj.jdbc.Driver.class.getPackage().getImplementationVersion());

            // Test database connection
            System.out.println("Testing database connection...");
            try (Connection conn = DriverManager.getConnection(
                    config.getProperty("hibernate.connection.url"),
                    config.getProperty("hibernate.connection.username"),
                    config.getProperty("hibernate.connection.password"))) {
                System.out.println("Database connection successful!");
            } catch (Exception e) {
                throw new RuntimeException("Failed to connect to database: " + e.getMessage(), e);
            }

            System.out.println("Building SessionFactory...");
            sessionFactory = config.buildSessionFactory();
            System.out.println("SessionFactory created successfully.");
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed: " + ex);
            ex.printStackTrace();
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}