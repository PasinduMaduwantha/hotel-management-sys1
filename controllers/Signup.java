package controllers;

import connections.InsertUpdateDelete;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

public class Signup  {
    public ComboBox seQuestion;
    public TextField txtName;
    public TextField txtEmail;
    public PasswordField password;
    public TextField txtAns;
    public TextField txtAddress;

    public void selectQuestionONAction(MouseEvent mouseEvent) {
        seQuestion.getItems().add("What is your favorite color?");
        seQuestion.getItems().add("What is your favorite animal?");
        seQuestion.getItems().add("What is your favorite food?");
        seQuestion.getItems().add("What is your favorite sport?");

    }

    public void selectQuestion(ActionEvent actionEvent) {
        System.out.println(seQuestion.getValue());
    }

    public void loginOnAction(ActionEvent actionEvent) throws IOException {
        try{
            Stage loginStage = new Stage();
            Parent root = FXMLLoader.load(this.getClass().getResource("/forms/Login.fxml"));
            loginStage.setScene(new Scene(root,1300,500));
            loginStage.setTitle("Login");
            loginStage.show();

        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void signupOnAction(ActionEvent actionEvent) {
        String name =  txtName.getText();
        String email = txtEmail.getText();
        String password = this.password.getText();
        String sequrityQuestion="";
        if (seQuestion.getValue() == null ){
            System.out.println("Please select a question");
        }else {
            sequrityQuestion= seQuestion.getValue().toString();
        }
        String answer = txtAns.getText();
        String address = txtAddress.getText();

        if (name.equals("")||email.equals("")||password.equals("")||sequrityQuestion.equals("")||answer.equals("")||address.equals("")){
            JOptionPane.showMessageDialog(null, "Please fill all the fields");
        }
        else {
            String sqlQuery = "INSERT INTO `users` values ('" + name + "','" + email + "','" + password + "','" + sequrityQuestion + "','" + answer + "','" + address + "','false')";
            InsertUpdateDelete.setData(sqlQuery,"Registered Successfully");
        }
    }

    public void frogotpwOnAction(ActionEvent actionEvent) {
    }

}
