package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;
import jm.task.core.jdbc.util.Util.*;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;


public class Main {
    public static void main(String[] args) throws SQLException {
        // реализуйте алгоритм здесь
        UserDaoJDBCImpl usr1 = new UserDaoJDBCImpl();
       // usr1.createUsersTable();
 /*       usr1.saveUser("Аграном", "-", (byte) 22);
        usr1.saveUser("Пендальф", "Серый", (byte) 20);
        usr1.saveUser("Лаговаз", "Безотцовщина", (byte) 15);
        usr1.saveUser("Фёдор", "Сумкин", (byte) 33);*/

        //usr1.getAllUsers();
        System.out.println(usr1.getAllUsers());
        //usr1.removeUserById(1);
       // usr1.dropUsersTable();

    }
}
