package Test;



import DBTablePrinter.DBTablePrinter;
import JDBC.SingeltonJDBC;

import javax.swing.text.Document;
import java.sql.*;
import java.util.Scanner;


import java.sql.ResultSet;
import java.sql.Statement;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;

import static JDBC.SingeltonJDBC.connection;

public class Test {

    public static void main(String[] args) throws SQLException, DocumentException {


        SingeltonJDBC  jdbc = SingeltonJDBC.getInstance();


        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.println("> Press 1 to delete  DATA FROM TABLE\n" +
                "Press 2 to Read Stored Information ");
        int option = scanner.nextInt();

        if(option == 1){
            String sql = "TRUNCATE TABLE Billing";
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            System.out.println("DELETED");
        }
        if(option == 2){
            String sql = "SELECT * FROM Billing";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            //DBTablePrinter.printResultSet(resultSet);

            com.itextpdf.text.Document document;
            document =   new com.itextpdf.text.Document(PageSize.A4,50,50,50,50);
            document.open();
            System.out.println(document.isOpen());

            int count=0;
            while (resultSet.next()) {
                count++;
                System.out.println("Inside while > "+count);
                String data = resultSet.getString("ID") + " " + resultSet.getString("Item")
                        + " " + resultSet.getString("Quantity")+ " " + resultSet.getString("Price");
                Paragraph paragraph = new Paragraph(data);
                document.add(paragraph);
                System.out.println(paragraph);

            }

            // Close the PDF document and the database connection
            document.close();
            System.out.println(document);

            System.out.println("READ");

/*          String sql = "SELECT * FROM Billing";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){

            }
            resultSet.close();*/

        }
        else
        {
            System.out.println("Enter A Billing ID: ");
            int id = scanner.nextInt();
            System.out.println("Enter An Item Name: ");
            String name = scanner.next();
            System.out.println("Enter the Quantity: ");
            int quantity = scanner.nextInt();
            System.out.println("Enter A Price: ");
            float price = scanner.nextFloat();

            String sql_statement = "INSERT INTO Billing(ID, Item, Quantity, Price)" +
                    "VALUES(?,?,?,?)";

            PreparedStatement statement = connection.prepareStatement(sql_statement);
            statement.setInt(1,id);
            statement.setString(2,name);
            statement.setInt(3,quantity);
            statement.setFloat(4,price);
            //Statement statement = jdbc.connection.createStatement();
            statement.executeUpdate();

            System.out.println("\nThe ID is \""+id+"\" with Product name \""+name+"\" and quantity is \""+quantity +
                    "\" with Price \""+ price+"\"\n");

            System.out.println("-> Information: RECORD HAS BEEN ADDED ");
        }
    }
}
