package org.example.buildingcompany.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class ConnectionPool {

    private static ConnectionPool instance;
    private static BlockingQueue<Connection> pool;
    private final static Integer POOL_SIZE = 5;
    private final static String dbUrl = "jdbc:mysql://localhost:3306/building_company";
    private final static String username = "root";
    private final static String password = "root";

    private ConnectionPool() {
        pool = new LinkedBlockingDeque<>(POOL_SIZE);
    }

    public static synchronized ConnectionPool getInstance() throws SQLException, InterruptedException {
        if(instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    public synchronized Connection getConnection() throws SQLException, InterruptedException {
        pool.put(DriverManager.getConnection(dbUrl, username, password));
        return pool.take();
    }
    public synchronized void releaseConnection(Connection connection) throws SQLException, InterruptedException {
        connection.close();
        pool.put(DriverManager.getConnection(dbUrl, username, password));
    }

    public synchronized void closeAllConnections() throws SQLException {
        for(Connection c : pool) {
            c.close();
        }
        pool.clear();
    }

}
