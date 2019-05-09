package com.epam.zlobin.connection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;

public class ConnectorDB {
    public static final String PATH_TO_PROPERTIES = "src\\main\\java\\com\\epam\\zlobin\\connection\\database.properties";
    public static Connection getConnection() throws SQLException, FileNotFoundException {
        FileInputStream fileInputStream;
        Properties prop = new Properties();

        try {
            fileInputStream = new FileInputStream(PATH_TO_PROPERTIES);
            prop.load(fileInputStream);
        }catch (IOException e){
            System.out.println("Ошибка в программе: файл src/main/com/epam/zlobin/connection/database.properties не обнаружено");
        }


        String url = prop.getProperty("url");
        String user = prop.getProperty("user");
        String pass = prop.getProperty("password");
        System.out.println(url+ user +  pass);
        return DriverManager.getConnection(url, user, pass);
    }
}
