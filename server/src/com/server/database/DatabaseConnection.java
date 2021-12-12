package com.server.database;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public class DatabaseConnection {

    private Connection connection;

    public DatabaseConnection() {

    }

    public void init() throws InstantiationException, IllegalAccessException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "@&!@@)!^");
            System.out.println("Connection to database is successful!");
        } catch (SQLException  e) {
            System.out.printf("Exception: ").printf(e.toString());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }

    public Connection getMyConnection() {
        return connection;
    }

    public void destroy() {
        if (connection != null) {
            try {
                connection.close();
            } catch (Exception e) {
            }
        }
    }

}


