package connections;

import javax.swing.*;
import java.sql.Connection;
import java.sql.Statement;

public class InsertUpdateDelete {
    public static void setData(String sqlQuery,String msg) {
        Connection connection = null;
        Statement statement = null;
        try {
            System.out.println(sqlQuery);
            connection = ConnectionProvider.getConnection();
            statement = connection.createStatement();
            statement.executeUpdate(sqlQuery);
            if (!msg.equals("")){
                JOptionPane.showMessageDialog(null, msg);
            }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Can not update data");
        }
        finally {
            try {
                connection.close();
                statement.close();
            }catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Can not close connection");
            }
        }
    }
}
