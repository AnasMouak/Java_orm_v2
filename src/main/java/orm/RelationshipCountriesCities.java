package orm;


import org.hibernate.Session;
import org.hibernate.SessionFactory;

import orm.entity.City;
import orm.entity.Country;
import orm.util.HibernateUtil;

public class RelationshipCountriesCities {
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: mvn exec:java -Dexec.mainClass=\"orm.RelationshipCountriesCities\" -Ddb.url=\"jdbc:mysql://localhost:3306/your_database\" -Ddb.username=\"root\" -Ddb.password=\"password\"");
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

            // New Country object
            Country country = new Country();
            country.setName("Ghana");

            // New City object
            City city = new City();
            city.setName("Accra");

            // Associate City with Country
            country.addCity(city);

            // Save Country
            session.save(country);


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
