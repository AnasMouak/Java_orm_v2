package orm;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import orm.entity.Country;
import orm.util.HibernateUtil;

public class ModelCountryMainMyGet {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: mvn exec:java -Dexec.mainClass=\"orm.ModelCountryMainMyGet\" -Ddb.url=\"jdbc:mysql://localhost:3306/your_database\" -Ddb.username=\"root\" -Ddb.password=\"password\" ");
            System.exit(1);
        }

        // Read command-line arguments
        String dbUrl = System.getProperty("db.url");
        String dbUsername = System.getProperty("db.username");
        String dbPassword = System.getProperty("db.password");
        String countryName = args[0];

        // Initialize Hibernate session factory with dynamic database parameters
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory(dbUrl, dbUsername, dbPassword);
        Session session = sessionFactory.openSession();

        try {
            // Begin transaction
            session.beginTransaction();

            // Query to retrieve all Country objects from the database
            Query<Country> query = session.createQuery("FROM Country", Country.class);
            List<Country> countries = query.list();

            // Print id of country with name equal to countryName
            if (countries.isEmpty()) {
                System.out.println("No countries found.");
            } else {
                for (Country country : countries) {
                    if (countryName.equals(country.getName())) {
                    System.out.println(country.getId());
                    }
                }
            }

            // Commit transaction
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}
