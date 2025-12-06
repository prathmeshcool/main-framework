package utils;

import java.sql.*;

public class DBUtils {

    private static String dbUrl = "jdbc:sqlite:src/test/resources/testdata/test.db";

    public static void executeUpdate(String query) {
        try (Connection conn = DriverManager.getConnection(dbUrl);
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("DB Update Query Failed: " + query);
        }
    }

    public static ResultSet executeQuery(String query) {
        try {
            Connection conn = DriverManager.getConnection(dbUrl);
            Statement stmt = conn.createStatement();
            return stmt.executeQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("DB Query Failed: " + query);
        }
    }
}
