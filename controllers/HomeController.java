package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.awt.event.ActionEvent;

public class HomeController {

    @FXML
    private TableView<?> roomData;
    @FXML
    private TableColumn<?, ?> rnCol;
    @FXML
    private TableColumn<?, ?> rtCol;
    @FXML
    private TableColumn<?, ?> bedCol;
    @FXML
    private TableColumn<?, ?> priceCol;
    @FXML
    private TableColumn<?, ?> statCol;
    @FXML
    private TextField roomNumm;
    @FXML
    private ComboBox<?> roomType;
    @FXML
    private ComboBox<?> bedNo;
    @FXML
    private TextField price;

    void addOnAction(ActionEvent event) {

    }

    public void manageroomLogOutOnAction(javafx.event.ActionEvent actionEvent) {
    }
}

