package com.saucelabs.kv008.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *change to connection pool with jndi
 */
public class ConnectorDB {
    static {
        try {
            Driver myDriver = new org.postgresql.Driver();
            DriverManager.registerDriver(myDriver);

        } catch (SQLException e) {
            System.out.println("Driver not register");
            e.printStackTrace();
        }
    }
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:6543/ecomap_db","ecouser", "ecouser");
    }
}
