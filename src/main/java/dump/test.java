package dump;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class test {

    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Application", "postgres", "cskl19nigypqjexm");

        System.out.println("uuuu");
    }
}