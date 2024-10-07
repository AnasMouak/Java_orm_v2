package orm;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import orm.entity.Country;
import orm.entity.City;
import orm.util.HibernateUtil;

public class ModelCityMainFetchByCountry {
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: mvn exec:java -Dexec.mainClass=\"orm.ModelCityMainFetchByCountry\" -Ddb.url=\"jdbc:mysql://localhost:3306/your_database\" -Ddb.username=\"root\" -Ddb.password=\"password\"");
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
            Query<Country> queryCountry = session.createQuery("FROM Country", Country.class);
            Query<City> queryCity = session.createQuery("FROM City", City.class);
            List<Country> countries = queryCountry.list();
            List<City> cities = queryCity.list();

            // Print all City objects
            if (cities.isEmpty()) {
                System.out.println("No cities found.");
            } else {
                for (City city : cities) {
                    for (Country country : countries) {
                        if (city.getCountry().getId() == country.getId()) {
                            System.out.println(country.getName() + " " + city.getId() + " " + city.getName());
                        }
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
