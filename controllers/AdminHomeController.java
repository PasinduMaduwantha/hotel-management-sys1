package controllers;

import connections.InsertUpdateDelete;
import connections.Select;
import javafx.beans.binding.ObjectExpression;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.net.URL;

public class AdminHomeController implements Initializable {

    public Button logoutBtn;
    public TableView tableVew;
    public TableColumn<TableData, String> name;
    public TableColumn<TableData, String> email;
    public TableColumn<TableData, String> question;
    public TableColumn<TableData, String> address;
    public TableColumn<TableData, String> status;
    public TextField txtField;

    ObservableList data= FXCollections.observableArrayList(
            new TableData("Jacob", "abc", "What is your mother's maiden name?", "123 Main St.", "Active"),
            new TableData("Isabella",  "yahoo", "What was the name of your first pet?", "456 Maple St.", "Active")
    );

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        name.setCellValueFactory(new PropertyValueFactory<TableData, String>("name"));
        email.setCellValueFactory(new PropertyValueFactory<TableData, String>("email"));
        question.setCellValueFactory(new PropertyValueFactory<TableData, String>("question"));
        address.setCellValueFactory(new PropertyValueFactory<TableData, String>("address"));
        status.setCellValueFactory(new PropertyValueFactory<TableData, String>("status"));

//        tableVew.setItems(data);
    }

    public void clearOnAction(ActionEvent event) {
        tableVew.getItems().clear();
    }

    public void logoutOnAction(ActionEvent event) {
        try{
            Stage loginStage = new Stage();
            Parent root = FXMLLoader.load(this.getClass().getResource("/forms/Login.fxml"));
            loginStage.setScene(new Scene(root));
            loginStage.setTitle("Login");
            Stage stage = (Stage) txtField.getScene().getWindow();
            stage.close();
            loginStage.show();

        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void searchOnAction(ActionEvent event) throws SQLException {
        String search = txtField.getText();
        String query = "SELECT * FROM users WHERE name LIKE '%"+search+"%' or email LIKE '%"+search+"%' ";
        ResultSet rs = Select.resultSet(query);
        data.clear();
        while(rs.next()){
            data.add(new TableData(rs.getString(1), rs.getString(2), rs.getString(4), rs.getString(6), rs.getString(7)));
        }
        tableVew.setItems(data);
        rs.close();
    }

    public void viewOnAction(MouseEvent mouseEvent) {;
        int rowIndex = 0;
        rowIndex = tableVew.getSelectionModel().getSelectedIndex();
        String name="",email="",status = "false";
        try{
            //get the selected row and print that data
            TableData selectedRow = (TableData) tableVew.getSelectionModel().getSelectedItem();
            name = selectedRow.getName();
            email = selectedRow.getEmail();
            status = selectedRow.getStatus();

        }
        catch (Exception e){
            System.out.println(e);
        }
        System.out.println(name);
        System.out.println(email);

        if (status.equals("true")){
            status="false";
        }
        else{
            status="true";
        }

        try{
            int a = JOptionPane.showConfirmDialog(null, "Are you sure you want to change the status of "+name+" to "+status+"?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (a==0) {
                String query = "UPDATE users SET status = '" + status + "' WHERE name = '" + name + "' and email = '" + email + "'";
                InsertUpdateDelete.setData(query, "Status Updated");
            }
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error in Status change");
        }
    }
}
