package controllers;

import connections.Select;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.sql.ResultSet;

public class Login {
    public TextField txtEmail;
    public PasswordField txtPassword;

    public void signupOnAction(ActionEvent actionEvent) {
        try{
            Stage loginStage = new Stage();
            Parent root = FXMLLoader.load(this.getClass().getResource("/forms/Signup.fxml"));
            loginStage.setScene(new Scene(root,1300,500));
            loginStage.setTitle("Login");
            Stage stage = (Stage) txtEmail.getScene().getWindow();
            stage.close();
            loginStage.show();

        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void loginOAction(ActionEvent actionEvent) {
        int check = 0;
        String email = txtEmail.getText();
        String password = txtPassword.getText();

        if(email.equals("") || password.equals("")){
            check = 1;
            JOptionPane.showMessageDialog(null, "Please fill all the fields");
        }
        else if (email.equals("admin") && password.equals("1234")){
//            if (true){
            check  = 1;
            try{
                Stage loginStage = new Stage();
                Parent root = FXMLLoader.load(this.getClass().getResource("/forms/Admin.fxml"));
                loginStage.setScene(new Scene(root,1300,500));
                loginStage.setTitle("Admin");
                Stage stage = (Stage) txtEmail.getScene().getWindow();
                stage.close();
                loginStage.show();

            }
            catch (Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
        }
        else{
            ResultSet resultSet = Select.resultSet("SELECT * FROM users WHERE email = '"+email+"' AND password = '"+password+"'");
            try{
                if (resultSet.next()){
                    check = 1;
                    if (resultSet.getString(7).equals("true")){
                        try{
                            Stage loginStage = new Stage();
                            Parent root = FXMLLoader.load(this.getClass().getResource("/forms/Home.fxml"));
                            loginStage.setScene(new Scene(root,1300,500));
                            loginStage.setTitle("Home");
                            Stage stage = (Stage) txtEmail.getScene().getWindow();
                            stage.close();
                            loginStage.show();

                        }
                        catch (Exception e){
                            JOptionPane.showMessageDialog(null, e);
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Wait for admin approval");
                    }
                }
            }
            catch (Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
        }
        if (check == 0){
            JOptionPane.showMessageDialog(null, "Invalid email or password");
        }
    }

    public void frogotpwOnAction(ActionEvent actionEvent) {
        try{
            Stage loginStage = new Stage();
            Parent root = FXMLLoader.load(this.getClass().getResource("/forms/ForgotPassword.fxml"));
            loginStage.setScene(new Scene(root,1300,500));
            loginStage.setTitle("Forgot Password");
            Stage stage = (Stage) txtEmail.getScene().getWindow();
            stage.close();
            loginStage.show();

        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
}
