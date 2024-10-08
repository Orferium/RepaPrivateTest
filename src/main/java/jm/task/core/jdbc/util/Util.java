package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class Util  {
    // реализуйте настройку соеденения с БД
    private static final String PASSWORD = "pasword!@#321";
    private static final String USERNAME = "postgres";
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";

    private Util() {
    }

    public static Connection open() {

       /* try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }*/

        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
