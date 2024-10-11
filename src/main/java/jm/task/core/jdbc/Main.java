package jm.task.core.jdbc;


import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;


public class Main {
    private final static UserService userService = new UserServiceImpl();
    public static void main(String[] args) throws SQLException {

        // реализуйте алгоритм здесь
        userService.createUsersTable();

        userService.saveUser("Фёдор", "Сумкин", (byte) 53);
        userService.saveUser("Агроном", "-", (byte) 112);
        userService.saveUser("Лаговаз", "Безотцовщина", (byte) 126);
        userService.saveUser("Пендальф", "Серый", (byte) 89);

        userService.removeUserById(2);

        userService.getAllUsers();

        userService.cleanUsersTable();

        userService.dropUsersTable();

    }
}
