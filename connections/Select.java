package connections;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Select {
    public static ResultSet resultSet(String sqlQuery){
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionProvider.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQuery);
            return resultSet;
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null,e);
            return null;
        }
    }
}
