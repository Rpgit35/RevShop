package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBTest {

    public static void main(String[] args) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            Connection con = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:XE",
                "revshop",
                "revshop"
            );

            if (con != null) {
                System.out.println("Connected to Oracle 10g successfully!");
                con.close();
            }
        } catch (Exception e) {
            System.out.println("Connection failed");
            e.printStackTrace();
        }
    }
}
