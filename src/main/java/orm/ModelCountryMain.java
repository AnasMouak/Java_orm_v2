package orm;

import orm.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


public class ModelCountryMain {
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: mvn exec:java -Dexec.mainClass=\"orm.ModelCountryMain\" -Ddb.url=\"jdbc:mysql://localhost:3306/your_database\" -Ddb.username=\"root\" -Ddb.password=\"password\"");
            System.exit(1);
        }

        // Read command-line arguments
        String dbUrl = args[0];       // Database URL
        String dbUsername = args[1];  // Database username
        String dbPassword = args[2];  // Database password

        // Initialize Hibernate session factory with dynamic database parameters
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory(dbUrl, dbUsername, dbPassword);

        // The Hibernate SessionFactory will automatically create the table(s) based on entity mappings
        Session session = sessionFactory.openSession();

        // Close the session as we don't need to perform any database operations
        session.close();
        sessionFactory.close();

        System.out.println("Database tables created successfully.");
    }
}
