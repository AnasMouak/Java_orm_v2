package orm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class FilterCities {
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: mvn -q exec:java -Dexec.mainClass=\"orm.FilterCities\" -Dexec.args=\"<username> <password> <database_name>\"");
            return;
        }

        String username = args[0];
        String password = args[1];
        String databaseName = args[2];
        String filter = args[3];

        String url = "jdbc:mysql://localhost:3306/" + databaseName;

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM cities JOIN countries ON countries.id=cities.country_id WHERE countries.name LIKE BINARY ?")) {

            preparedStatement.setString(1, filter);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("cities.name");
                System.out.print(name);
                if (!resultSet.isLast()) {
                    System.out.print(", ");
                }
            }
            System.out.print("\n");

            resultSet.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
