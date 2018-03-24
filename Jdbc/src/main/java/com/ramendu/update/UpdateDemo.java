package com.ramendu.update;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateDemo {

    private static Connection getConnection(String url, String user, String pass) {
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    public static void main(String[] args) {
        Statement statement = null;
        String query = "update actor set first_name = 'Ramendu' where actor_id = 200";
        Connection connection = getConnection("jdbc:mysql://localhost:3306/sakila", "root", "1qazxsw2");

        try {
            statement = connection.createStatement();
            int affected = statement.executeUpdate(query);
            if(affected != 0) {
                System.out.println("Updated rows = "+affected);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }


    }

}
