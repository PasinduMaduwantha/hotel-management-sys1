import connections.ConnectionProvider;
import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;
import java.sql.Connection;
import java.sql.Statement;


public class main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        try{
            Parent root = FXMLLoader.load(this.getClass().getResource("forms/Signup.fxml"));
            primaryStage.setScene(new Scene(root,1300,500));
            primaryStage.setTitle("Signup");
            primaryStage.show();

        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Can not Load the signup fxml");
        }
    }

    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = ConnectionProvider.getConnection();
            statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users (name varchar (200),email varchar (200),password varchar (50),sequrityQuestion varchar (500),answer varchar (200),address varchar (200),status varchar (20))" );
            JOptionPane.showMessageDialog(null, "Table Created Successfully");
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Can not Create table named users");
        }
        finally {
            try {
                connection.close();
                statement.close();
            }
            catch (Exception e){
                JOptionPane.showMessageDialog(null, "Can not close the connection");
            }
        }
        launch(args);
    }
}
