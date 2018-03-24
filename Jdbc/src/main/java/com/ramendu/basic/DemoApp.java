package com.ramendu.basic;

import java.sql.*;

/**
 * Demonstrates the sample use case of creating Connection to database and then fetching some information from table
 */
public class DemoApp {

    private static Connection getConnection(String url, String userName, String password) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, userName, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void main(String[] args) {
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = getConnection("jdbc:mysql://localhost:3306/sakila", "root", "1qazxsw2").
                    createStatement();
            String sql = "select * from actor";
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {

                System.out.println(resultSet.getString("actor_id"));
                System.out.println(resultSet.getString("first_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if(resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

