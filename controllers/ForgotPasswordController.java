package controllers;

import java.sql.ResultSet;
import java.sql.SQLException;

import connections.InsertUpdateDelete;
import connections.Select;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.*;

import static com.sun.glass.ui.Cursor.setVisible;

public class ForgotPasswordController {
    public TextField txtEmail;
    public TextField txtAns;
    public PasswordField password;
    public TextField txtQuestion;

    public void loginOnAction(ActionEvent actionEvent) {
        try{
            Stage loginStage = new Stage();
            Parent root = FXMLLoader.load(this.getClass().getResource("/forms/Login.fxml"));
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

    public void svaeOnAvtion(ActionEvent actionEvent) {
        int check = 0;
        String email = txtEmail.getText();
        String ans = txtAns.getText();
        String question = txtQuestion.getText();
        String newPassword = password.getText();
        if (email.equals("") || email.equals("")){
            check = 1;
            JOptionPane.showMessageDialog(null, "Please enter your email(required)");
        }
        else{
            ResultSet rs = Select.resultSet("select * from users where email = '" + email + "' and sequrityQuestion='"+question+"' and answer = '"+ans+"' ");
            try {
                if (rs.next()){
                    check = 1;
                    InsertUpdateDelete.setData("update users set password = '"+newPassword+"' where email = '"+email+"'","Password reset successful");

                    try{
                        Stage forgetPW = new Stage();
                        Parent root = FXMLLoader.load(this.getClass().getResource("/forms/ForgotPassword.fxml"));
                        forgetPW.setScene(new Scene(root,1300,500));
                        forgetPW.setTitle("ForgotPassword");
                        Stage stage = (Stage) txtEmail.getScene().getWindow();
                        stage.close();
                        forgetPW.show();

                    }
                    catch (Exception e){
                        JOptionPane.showMessageDialog(null, e);
                    }
                }
            }
            catch (SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    public void signupOnAction(ActionEvent actionEvent) {
        try{
            Stage loginStage = new Stage();
            Parent root = FXMLLoader.load(this.getClass().getResource("/forms/Signup.fxml"));
            loginStage.setScene(new Scene(root,1300,500));
            loginStage.setTitle("signup");
            Stage stage = (Stage) txtEmail.getScene().getWindow();
            stage.close();
            loginStage.show();

        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void searchOnAction(ActionEvent actionEvent) throws SQLException {
        int check = 0;
        String email = txtEmail.getText();
        ResultSet rs = Select.resultSet("select * from users where email = '" + email + "'");

        if (email.equals("")){
            check = 1;
            JOptionPane.showMessageDialog(null, "Please enter your email");
        }
        else {
            try {
                if (rs.next()){
                    check = 1;
                    txtQuestion.setEditable(false);
                    txtQuestion.setText(rs.getString(4));
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        if (check == 0){
            JOptionPane.showMessageDialog(null, "Incorrect email");
        }
    }
}
