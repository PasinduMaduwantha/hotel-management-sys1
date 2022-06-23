package controllers;

import java.sql.ResultSet;
import java.sql.SQLException;

import connections.InsertUpdateDelete;
import connections.Select;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;

public class ForgotPasswordController {
    public TextField txtEmail;
    public TextField txtAns;
    public PasswordField password;
    public TextField txtQuestion;

    public void loginOnAction(ActionEvent actionEvent) {
        try{
            Stage loginStage = new Stage();
            Parent root = FXMLLoader.load(this.getClass().getResource("/forms/Login.fxml"));
            loginStage.setScene(new Scene(root));
            loginStage.setTitle("Login");
            Stage stage = (Stage) txtEmail.getScene().getWindow();
            stage.close();
            loginStage.show();
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void saveOnAction(ActionEvent actionEvent) {
        String email = txtEmail.getText();
        String ans = txtAns.getText();
        String question = txtQuestion.getText();
        String newPassword = password.getText();
        if (email.equals("")||ans.equals("")){
            JOptionPane.showMessageDialog(null, "Please fill all required fields (email,security question and answer)");
        }
        else{
            ResultSet rs = Select.resultSet("select * from users where email = '" + email + "' and sequrityQuestion='"+question+"' and answer = '"+ans+"' ");
            //take the query compatible with entered data
            try {
                if (rs.next()){//if there have relevant query it means email, security question and answer are matching
                    InsertUpdateDelete.setData("update users set password = '"+newPassword+"' where email = '"+email+"'","Password reset successful");
                }
                else{
                    JOptionPane.showMessageDialog(null,"Incorrect Answer");
                }
                rs.close();
                //clear data
                txtEmail.setText("");
                txtQuestion.setText("");
                txtAns.setText("");
                password.setText("");
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
            loginStage.setScene(new Scene(root));
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
        rs.close();
    }
}
