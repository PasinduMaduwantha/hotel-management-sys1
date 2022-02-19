package connections;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionProvider {
    public static Connection getConnection() {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel","root","12345678");
            return con;
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "MySql Connection error");
        }
        return null;
    }
}
