package orm;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import orm.entity.Country;
import orm.util.HibernateUtil;

public class ModelCountryMainFetchFirst {
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: mvn exec:java -Dexec.mainClass=\"orm.ModelCountryMainFetchFirst\" -Ddb.url=\"jdbc:mysql://localhost:3306/your_database\" -Ddb.username=\"root\" -Ddb.password=\"password\"");
            System.exit(1);
        }

        // Read command-line arguments
        String dbUrl = args[0];
        String dbUsername = args[1];
        String dbPassword = args[2];

        // Initialize Hibernate session factory with dynamic database parameters
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory(dbUrl, dbUsername, dbPassword);
        Session session = sessionFactory.openSession();

        try {
            // Begin transaction
            session.beginTransaction();

            // Query to retrieve all Country objects from the database
            Query<Country> query = session.createQuery("FROM Country", Country.class);
            List<Country> countries = query.list();

            // Print First Country object
            if (countries.isEmpty()) {
                System.out.println("No countries found.");
            } else {
                Country country = countries.get(0);
                System.out.println(country.getId() + " " + country.getName());
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
