package com.epam.zlobin;

import com.epam.zlobin.connection.ConnectorDB;

import javax.xml.transform.Result;
import java.io.FileNotFoundException;
import java.sql.*;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, FileNotFoundException {
        Connection connection = null;
        try {
            connection = new ConnectorDB().getConnection();
            PreparedStatement ps = null;
            Statement statement= null;
            try {
                statement = connection.createStatement();
                ps = connection.prepareStatement("SELECT * FROM courses;");
                ResultSet rs = null;
                try {
                    statement.executeUpdate("INSERT INTO courses (c_no, title, hourse) VALUES ('FF322','Математика',40),('GG211','Логика', 20);");
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
                    }

                } finally {
                    if (rs != null & statement!= null) {
                        rs.close();
                        statement.close();
                    } else {
                        System.err.println("ошибка во время чтения из БД");

                    }
                }
            } finally {
                if (ps != null) {
                    ps.close();
                } else {
                    System.err.println("Statement не создан");
                }
            }
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.err.println("Сonnection close error: " + e);

                }
            }

        }


    }
}
