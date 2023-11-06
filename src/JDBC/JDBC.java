package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC {


    private   static Connection connection;
    private JDBC(){

        System.out.println(">Wait a minute please<");

        String url = "jdbc:sqlserver://localhost:1433;database=TempTry;encrypt=true;trustServerCertificate=true;";
        String user = "sa";
        String password = "reallyStrongPwd123";

        try {
             connection = DriverManager.getConnection(url,user,password);
            System.out.println("Connected! AHAHAHHA");
        } catch (SQLException e) {
            System.out.println("NOOOOOOO!!!! NOT-CONNECTED");
            throw new RuntimeException(e);
        }


    }
}
