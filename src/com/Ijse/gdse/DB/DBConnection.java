package com.Ijse.gdse.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static DBConnection dbConection;
    private Connection connection;

    private DBConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection =  DriverManager.getConnection("jdbc:mysql://localhost:3306/laibrymanagementsystem","root","1234");

    }
    public static DBConnection getInstance() throws SQLException, ClassNotFoundException {
        if (dbConection==null){
            dbConection=new DBConnection();

        }
        return dbConection;

    }
    public Connection getConnection(){
        return connection;
    }
}
