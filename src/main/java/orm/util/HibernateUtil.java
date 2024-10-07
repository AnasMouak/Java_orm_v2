package orm.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import orm.entity.Country;
import orm.entity.City;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory(String dbUrl, String dbUsername, String dbPassword) {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
                // Set database connection details dynamically
                configuration.setProperty("hibernate.connection.url", dbUrl);
                configuration.setProperty("hibernate.connection.username", dbUsername);
                configuration.setProperty("hibernate.connection.password", dbPassword);
                configuration.addAnnotatedClass(Country.class);
                configuration.addAnnotatedClass(City.class);
                
                sessionFactory = configuration.buildSessionFactory();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
