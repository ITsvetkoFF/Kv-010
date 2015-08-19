package com.saucelabs.Tests.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by rotiutc on 05.11.2014.
 */
public class BaseDAO {

    private String url = "jdbc:mysql://localhost:3306/enviromap";
    private String userName = "root";
    private String userPassword = "root";

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, userName, userPassword);
        return connection;
    }

    public Connection getConnection(String url, String userName, String userPassword)
            throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, userName, userPassword);
        return connection;
    }
}