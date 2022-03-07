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
            Parent root = FXMLLoader.load(this.getClass().getResource("forms/Home.fxml"));
            primaryStage.setScene(new Scene(root,600,400));
            primaryStage.setTitle("Signup");
            primaryStage.show();

        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Can not Load the signup fxml");
        }
    }

    public static void main(String[] args) {

        Connection connection = null;
        Statement statement1 = null;
        Statement statement2 = null;
        Statement statement3 = null;
        try {
            connection = ConnectionProvider.getConnection();
            statement1 = connection.createStatement();
            statement2 = connection.createStatement();
            statement3 = connection.createStatement();
            statement1.executeUpdate("CREATE TABLE IF NOT EXISTS users (name varchar (200),email varchar (200),password varchar (50),sequrityQuestion varchar (500),answer varchar (200),address varchar (200),status varchar (20))" );
            statement2.executeUpdate("CREATE TABLE IF NOT EXISTS rooms(roomNo varchar (10),roomType varchar(200),bed varchar (200),price int,status varchar (20))");
            statement3.executeUpdate("CREATE TABLE IF NOT EXISTS customers(bookingId int, name varchar (200),mobilNo varchar (20),nationality varchar (200),gender varchar (50),email varchar (200),idProof varchar (200), address varchar (500),checkIn varchar (50),roomNo varchar (10),bed varchar (200),roomType varchar (200),pricePerDay int(10),numberOdDayStay int (10),totalAmount varchar (200),chekOut varchar (50))");
//            JOptionPane.showMessageDialog(null, "Table Created Successfully");
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Can not Create table(s)");
        }
        finally {
            try {
                connection.close();
                statement1.close();
                statement2.close();
                statement3.close();
            }
            catch (Exception e){
                JOptionPane.showMessageDialog(null, "Can not close the connection");
            }
        }
        launch(args);
    }
}
