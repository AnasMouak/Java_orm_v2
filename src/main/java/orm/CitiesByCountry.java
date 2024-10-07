package orm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class CitiesByCountry {
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: mvn -q exec:java -Dexec.mainClass=\"orm.CitiesByCountry\" -Dexec.args=\"<username> <password> <database_name>\"");
            return;
        }

        String username = args[0];
        String password = args[1];
        String databaseName = args[2];

        String url = "jdbc:mysql://localhost:3306/" + databaseName;

        try {

            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM cities JOIN countries ON cities.country_id = countries.id");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String country_name = resultSet.getString("countries.name");
                System.out.println(id + " " + name + " " + country_name);
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
