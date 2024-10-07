package orm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SelectCountries {
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: mvn -q exec:java -Dexec.mainClass=\"orm.SelectCountries\" -Dexec.args=\"<username> <password> <database_name>\"");
            return;
        }

        String username = args[0];
        String password = args[1];
        String databaseName = args[2];

        String url = "jdbc:mysql://localhost:3306/" + databaseName;

        try {

            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM countries");

            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id") + " " + resultSet.getString("name"));
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
