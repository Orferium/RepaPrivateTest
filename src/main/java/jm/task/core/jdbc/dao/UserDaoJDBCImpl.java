package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        try (Connection connection = Util.open()) {
            Statement statement = connection.createStatement();

            String createTableSQL = "CREATE TABLE IF NOT EXISTS users ("
                    + "id SERIAL PRIMARY KEY,"
                    + "name VARCHAR(30),"
                    + "lastName VARCHAR(30),"
                    + "age SMALLINT)";

            statement.execute(createTableSQL);

            System.out.println("Таблица создана успешно !!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void dropUsersTable() {
        boolean tableExists = false;

        try (Connection connection = Util.open()) {
            Statement statement = connection.createStatement();

            String checkTableExistenceQuery = "SELECT COUNT(*) FROM information_schema.tables WHERE table_name = 'users'";
            ResultSet rs = statement.executeQuery(checkTableExistenceQuery);

            while (rs.next()) {
                int count = rs.getInt(1);
                if (count > 0) {
                    tableExists = true;
                    break;
                }
            }

            if (tableExists) {
                String dropTableQuery = "DROP TABLE users";
                statement.executeUpdate(dropTableQuery);
            } else {
                System.out.println("Таблица пользователей не найдена.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void saveUser(String name, String lastName, byte age) {

        try (Connection connection = Util.open()) {


            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users(name, lastName, age) VALUES(?,?,?)");


            preparedStatement.setString(1,name);
            preparedStatement.setString(2,lastName);
            preparedStatement.setByte(3,age);

            preparedStatement.executeUpdate();



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        PreparedStatement preparedStatement = null;

        try (Connection connection = Util.open()) {
            preparedStatement = connection.prepareStatement("DELETE FROM users WHERE id=?");

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<User> getAllUsers() {

        List<User> usrs = new ArrayList<>();

        try (Connection connection = Util.open()) {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM users";
            ResultSet result = statement.executeQuery(sql);

            while(result.next()) {
                User usr = new User();

                usr.setId(result.getLong("id"));
                usr.setName(result.getString("name"));
                usr.setLastName(result.getString("lastName"));
                usr.setAge(result.getByte("age"));

                usrs.add(usr);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return usrs;
    }

    public void cleanUsersTable() {

        try (Connection connection = Util.open()) {
            Statement statement = connection.createStatement();

            String sql = "TRUNCATE TABLE users RESTART IDENTITY";
            statement.executeUpdate(sql);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
