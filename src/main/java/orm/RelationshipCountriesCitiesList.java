package orm;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import orm.entity.City;
import orm.entity.Country;
import orm.util.HibernateUtil;

import java.util.List;

public class RelationshipCountriesCitiesList {
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: mvn exec:java -Dexec.mainClass=\"orm.RelationshipCountriesCitiesList\" -Ddb.url=\"jdbc:mysql://localhost:3306/your_database\" -Ddb.username=\"root\" -Ddb.password=\"password\"");
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
            List<Country> countries = session.createQuery("FROM Country", Country.class).list();

            // Loop through the list of countries and print their cities
            for (Country country : countries) {
                System.out.println("Country: " + country.getName());
                for (City city : country.getCities()) {
                    System.out.println("City: " + city.getName());
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
