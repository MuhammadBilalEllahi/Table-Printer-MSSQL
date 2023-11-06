package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingeltonJDBC {

        // Static variable reference of single_instance
        // of type Singleton
        private static SingeltonJDBC single_instance = null;
        public  static Connection connection ;

        // Declaring a variable of type String


        // Constructor
        // Here we will be creating private constructor
        // restricted to this class itself
        private SingeltonJDBC()
        {
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

        // Static method
        // Static method to create instance of Singleton class
        public static synchronized SingeltonJDBC getInstance()
        {
            if (single_instance == null)
                single_instance = new SingeltonJDBC();

            return single_instance;
        }
    }

