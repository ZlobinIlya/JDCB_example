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
            Statement statement = null;

            try {

                statement = connection.createStatement();
                ps = connection.prepareStatement("SELECT * FROM courses;");
                ResultSet rs = null;
                try {
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
                    }
                    System.out.println("--------------------------------------");
                    statement.executeUpdate("DELETE FROM courses WHERE hourse = 40;");

                    ps = connection.prepareStatement("SELECT * FROM courses;");

                    rs = ps.executeQuery();
                    while (rs.next()) {
                        System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
                    }
                    System.out.println("+++++++++++++++++++++++++++++++++++++++++");
//
//                    connection.setAutoCommit(false);
//                                        //Используем BATCH и транзакции
//                    statement.addBatch("INSERT INTO courses (c_no, title, hourse) VALUES ('FF322','Математика',40);");
//                    statement.addBatch("INSERT INTO courses (c_no, title, hourse) VALUES ('ВВ213','Математика',40);");
//                    statement.addBatch("INSERT INTO courses (c_no, title, hourse) VALUES ('FQ322','Математика',40);");
//                    statement.addBatch("INSERT INTO courses (c_no, title, hourse) VALUES ('WF322','Математика',40);");
//                    if(statement.executeBatch().length == 4){
//                        connection.commit();
//                        System.out.println("Всё норм.");
//                    }else{
//                        connection.rollback();
//                    }
                    statement.execute("INSERT INTO courses (c_no, title, hourse) VALUES ('FF322','Математика',40);");
                    statement.execute("INSERT INTO courses (c_no, title, hourse) VALUES ('ВВ213','Математика',40);");
                    statement.execute("INSERT INTO courses (c_no, title, hourse) VALUES ('FQ322','Математика',40);");
                    statement.execute("INSERT INTO courses (c_no, title, hourse) VALUES ('WF322','Математика',40);");


                    ps = connection.prepareStatement("SELECT * FROM courses;");

                    rs = ps.executeQuery();
                    while (rs.next()) {
                        System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
                    }
                    System.out.println("==================================================");


                    statement.executeUpdate("DELETE FROM courses WHERE hourse = 40;");

                    ps = connection.prepareStatement("SELECT * FROM courses;");

                    rs = ps.executeQuery();
                    while (rs.next()) {
                        System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
                    }
                    System.out.println("==================================================");


                } finally {

                    if (rs != null & statement != null) {
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
