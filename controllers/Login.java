package controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javax.swing.*;

public class Login {
    public TextField txtEmail;
    public PasswordField txtPassword;

    public void signupOnAction(ActionEvent actionEvent) {
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
            check  = 1;

        }
    }

    public void frogotpwOnAction(ActionEvent actionEvent) {
    }
}
