package controller;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseHandler {
    public Connection con;
    private String driver = "com.mysql.cj.jdbc.Driver";
    //private String url = "jdbc:mysql://localhost/restoran";
    private String url = "jdbc:mysql://localhost/db_uas_1123002?serverTimezone=UTC&useSSL=false";
    private String username = "root";
    private String password = "";

    private static DatabaseHandler instance;

    public static DatabaseHandler getInstance() {
        if (instance == null) {
            synchronized (DatabaseHandler.class) {
                if (instance == null) {
                    instance = new DatabaseHandler();
                }
            }
        }
        return instance;
    }

    private Connection logOn() {
        try {
            Class.forName(driver).getDeclaredConstructor().newInstance();
            con = DriverManager.getConnection(url, username, password);
        } catch (Exception ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getLocalizedMessage());
            JOptionPane.showMessageDialog(null, "Error Ocurred when login " + ex);
        }
        return con;
    }

    private void logOff() {
        try {
            con.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error Ocurred when login" + ex);
        }
    }

    public void connect() {
        try {
            con = logOn();
        } catch (Exception ex) {
            System.out.println("Error occured when connecting to database");
        }
    }

    public void disconnect() {
        try {
            logOff();
        } catch (Exception ex) {
            System.out.println("Error occured when connecting to database");
        }
    }
}

