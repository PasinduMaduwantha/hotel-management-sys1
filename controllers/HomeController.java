package controllers;

import connections.InsertUpdateDelete;
import connections.Select;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    public Button logoutBtn;
    public ComboBox chackIngenderCombo;
    public TextField checkInName;
    public TextField checkInMobileNo;
    public TextField checkInNationality;
    public TextField checkInEmail;
    public TextField checkInAddress;
    public TextField checkInID;
    public TextField CheckInDate;
    public ComboBox checkInBedCombo;
    public ComboBox checkInRoomNoCombo;
    public ComboBox checkInRoomTypeCombo;
    public TextField checkiInPrice;

    @FXML
    private TableView roomData;
    @FXML
    private TableColumn<TableData,String> rnCol;
    @FXML
    private TableColumn<TableData,String> rtCol;
    @FXML
    private TableColumn<TableData,String> bedCol;
    @FXML
    private TableColumn<TableData,String> priceCol;
    @FXML
    private TableColumn<TableData,String> statCol;


    /******************************************************************
     *                          check out variables
     *******************************************************************/
    public TextField customerNameCO;
    public TextField checkInDateCO;
    public TextField checkOutDateCO;
    public TextField customerMobileNumCO;
    public TextField pricePerDayCO;
    public TextField numOfDaysStayCO;
    public TextField totalAmountCO;
    public TextField emailCO;
    public TextField roomNumTxt;
    public TableView checkOutTable;

    public TableColumn<CheckOutTableData,String> checkOUtIdCOl;
    public TableColumn<CheckOutTableData,String> checkOutNameCOl;
    public TableColumn<CheckOutTableData,String> checkOutMobileCol;
    public TableColumn<CheckOutTableData,String> checkOutNationalityCol;
    public TableColumn<CheckOutTableData,String> checkOutGenderCol;
    public TableColumn<CheckOutTableData,String> checkOutEmailCol;
    public TableColumn<CheckOutTableData,String> checkOutIdProofCol;
    public TableColumn<CheckOutTableData,String> checkOutAddressCol;
    public TableColumn<CheckOutTableData,String> checkOutCheckInCol;
    public TableColumn<CheckOutTableData,String> checkOutroNumberCol;
    public TableColumn<CheckOutTableData,String> checkOutBedCol;
    public TableColumn<CheckOutTableData,String> checkOutRoomTypeCol;
    public TableColumn<CheckOutTableData,String> checkOutPricePerDayCol;

    /******************************************************************
     *******************************************************************/
    @FXML
    private TextField roomNumm;
    @FXML
    private ComboBox roomType;
    @FXML
    private ComboBox bedNo;
    @FXML
    private TextField price;

    String bedCheckIn,roomTypeCheckIn,roomNoCheckIn,priceCheckIn;

    public void roomDetails(){
        checkInRoomNoCombo.getItems().clear();
        checkiInPrice.setText("");
        bedCheckIn = (String) checkInBedCombo.getValue();
        roomTypeCheckIn = (String) checkInRoomTypeCombo.getValue();
        try {
            ResultSet rs = Select.resultSet("SELECT * FROM rooms WHERE bed= '"+bedCheckIn+"' AND roomType = '"+roomTypeCheckIn+"' AND status='Available' ");
            while (rs.next()){
                checkInRoomNoCombo.getItems().add(rs.getString(1));
            }
            rs.close();
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }
    }

    ObservableList<String> roomTypeList = FXCollections.observableArrayList("AC", "Non-AC");
    ObservableList<String> bedNoList = FXCollections.observableArrayList("Single", "Double", "Triple");
    ObservableList<String> genderList = FXCollections.observableArrayList("Male","Female","Other");
    ObservableList data = FXCollections.observableArrayList(
//            new TableData("0", "AC", "Single", "500", "Available")
    );
    ObservableList checkOutList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /*******************************************************
         room check in
         ********************************************************/
        rnCol.setCellValueFactory(new PropertyValueFactory<TableData, String>("name"));
        rtCol.setCellValueFactory(new PropertyValueFactory<TableData, String>("email"));
        bedCol.setCellValueFactory(new PropertyValueFactory<TableData, String>("question"));
        priceCol.setCellValueFactory(new PropertyValueFactory<TableData, String>("address"));
        statCol.setCellValueFactory(new PropertyValueFactory<TableData, String>("status"));
        roomData.setItems(data);

        roomType.setItems(roomTypeList);
        bedNo.setItems(bedNoList);

        checkInBedCombo.setItems(bedNoList);
        checkInRoomTypeCombo.setItems(roomTypeList);
        chackIngenderCombo.setItems(genderList);
        //update date automatically
        CheckInDate.setEditable(false);
        checkiInPrice.setEditable(false);
        CheckInDate.setText(java.time.LocalDate.now().toString());

        /*******************************************************
         room check Out
         ********************************************************/
        checkOUtIdCOl.setCellValueFactory(new PropertyValueFactory<CheckOutTableData, String>("checkOUtIdCOl"));
        checkOutNameCOl.setCellValueFactory(new PropertyValueFactory<CheckOutTableData, String>("checkOutNameCOl"));
        checkOutMobileCol.setCellValueFactory(new PropertyValueFactory<CheckOutTableData, String>("checkOutMobileCol"));
        checkOutNationalityCol.setCellValueFactory(new PropertyValueFactory<CheckOutTableData, String>("checkOutNationalityCol"));
        checkOutGenderCol.setCellValueFactory(new PropertyValueFactory<CheckOutTableData, String>("checkOutGenderCol"));
        checkOutEmailCol.setCellValueFactory(new PropertyValueFactory<CheckOutTableData, String>("checkOutEmailCol"));
        checkOutIdProofCol.setCellValueFactory(new PropertyValueFactory<CheckOutTableData, String>("checkOutIdProofCol"));
        checkOutAddressCol.setCellValueFactory(new PropertyValueFactory<CheckOutTableData, String>("checkOutAddressCol"));
        checkOutCheckInCol.setCellValueFactory(new PropertyValueFactory<CheckOutTableData, String>("checkOutCheckInCol"));
        checkOutroNumberCol.setCellValueFactory(new PropertyValueFactory<CheckOutTableData, String>("checkOutroNumberCol"));
        checkOutBedCol.setCellValueFactory(new PropertyValueFactory<CheckOutTableData, String>("checkOutBedCol"));
        checkOutRoomTypeCol.setCellValueFactory(new PropertyValueFactory<CheckOutTableData, String>("checkOutRoomTypeCol"));
        checkOutPricePerDayCol.setCellValueFactory(new PropertyValueFactory<CheckOutTableData, String>("checkOutPricePerDayCol"));
        checkOutTable.setItems(checkOutList);
    }

    public void logOutOnAction(javafx.event.ActionEvent actionEvent) {
        try{
            Stage loginStage = new Stage();
            Parent root = FXMLLoader.load(this.getClass().getResource("/forms/Login.fxml"));
            loginStage.setScene(new Scene(root,1300,500));
            loginStage.setTitle("Login");
            Stage stage = (Stage) logoutBtn.getScene().getWindow();
            stage.close();
            loginStage.show();

        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void addRoomOnAction(javafx.event.ActionEvent actionEvent) {
        String roomNo = roomNumm.getText();
        String type=null,bed=null;
        if (roomType.getValue() != null && bedNo.getValue() != null ){
            type = roomType.getValue().toString();
            bed = bedNo.getValue().toString();
        }
        String price1 = price.getText();
        String status = "Not-Available";

        if(roomNo.equals("") || type.equals("") || bed.equals("") || price1.equals("")){
            JOptionPane.showMessageDialog(null, "Please fill all the fields");
        }
        else{
            try{
                String sql = "INSERT INTO rooms VALUES ('"+roomNo+"', '"+type+"', '"+bed+"', '"+price1+"', '"+status+"')";
                InsertUpdateDelete.setData(sql, "Room Added");
            }
            catch (Exception e){
                JOptionPane.showMessageDialog(null, "Error in room adding");
            }
        }
    }

    public void roomTablenAction(MouseEvent mouseEvent) throws SQLException {
        String query = "SELECT * FROM rooms";
        ResultSet rs = Select.resultSet(query);
        data.clear();
        while(rs.next()){
            data.add(new TableData(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
        }
        roomData.setItems(data);
        rs.close();
    }

    public void roomViewOnAction(ContextMenuEvent contextMenuEvent) throws SQLException {
    }

    public void onAction(ActionEvent actionEvent) throws SQLException {
    }

    /***********************************************************************************
     * Room Check-In
     ************************************************************************************/

    public void allocateRoomheckInOnAction(ActionEvent actionEvent) {
        int id = 1;
        String name = checkInName.getText();
        String mobile = checkInMobileNo.getText();
        String nationality = checkInNationality.getText();
        String gender = chackIngenderCombo.getValue().toString();
        String email = checkInEmail.getText();
        String idProof = checkInID.getText();
        String address = checkInAddress.getText();
        String date = CheckInDate.getText();
        String bed = checkInBedCombo.getValue().toString();
        String roomType = checkInRoomTypeCombo.getValue().toString();
        String roomNo = checkInRoomNoCombo.getValue().toString();
        String price = checkiInPrice.getText();

        //Query
        String query = "SELECT MAX(bookingId) FROM customers";
        try{
            ResultSet rs = Select.resultSet(query);
            while (rs.next()){
                id = rs.getInt(1);
            }
            id += 1;
            if (!price.equals("")){
                query = "UPDATE rooms SET status='Not-Available' WHERE roomNo='"+roomNo+"'";
                InsertUpdateDelete.setData(query, "Room Updated");
                query = "INSERT INTO customers (bookingId, name,mobilNo,nationality ,gender ,email ,idProof , address ,checkIn ,roomNo ,bed ,roomType ,pricePerDay ) VALUES ('"+id+"', '"+name+"', '"+mobile+"', '"+nationality+"', '"+gender+"','"+email+"','"+idProof+"','"+address+"','"+date+"','"+roomNo+"','"+bed+"','"+roomType+"','"+price+"')";
                InsertUpdateDelete.setData(query,"Customer Check In Successfully");

            }
            rs.close();
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error in room allocation");
        }
    }

    public void clearChekInOnAction(ActionEvent actionEvent) {
        checkInName.setText("");
        checkInMobileNo.setText("");
        checkInNationality.setText("");
        checkInEmail.setText("");
        checkInAddress.setText("");
        checkInID.setText("");
//        CheckInDate.setText("");
        checkiInPrice.setText("");
    }

    public void checkInBedComboOnAction(ActionEvent actionEvent) {
        roomDetails();
    }

    public void checkInRoomTypeComboOnAction(ActionEvent actionEvent) {
        roomDetails();
    }

    public void checkInRoomNoComboOnAction(ActionEvent actionEvent) {
        if (checkInRoomNoCombo.getValue() != null) {
            roomNoCheckIn = checkInRoomNoCombo.getValue().toString();
        }
        try{
            String sql = "SELECT * FROM rooms WHERE roomNo = '"+roomNoCheckIn+"'";
            ResultSet rs = Select.resultSet(sql);
            while(rs.next()){
                checkiInPrice.setText(rs.getString(4));
            }
            rs.close();
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error in room details");
        }
    }

    /***********************************************************************************
     * Room Check-In
     ************************************************************************************/

    int checkOutId = 0;
    String checkOutQuery = "";
    String checkOutRoomNo = "";
    String checkOutBed = "";
    String checkOutRoomType = "";

    public void checkOutOnAction(ActionEvent actionEvent) {
    }

    public void clearOnAction(ActionEvent actionEvent) {
    }

    public void searchOnAction(ActionEvent actionEvent) {
    }

    public void ckeckOutTableOnAction(MouseEvent mouseEvent) throws SQLException {
        String query = "SELECT * FROM customers WHERE chekOut is null";
        ResultSet checkOutRs = Select.resultSet(query);
        checkOutList.clear();
        while(checkOutRs.next()){
            checkOutList.add(new CheckOutTableData(checkOutRs.getString(1), checkOutRs.getString(2), checkOutRs.getString(3), checkOutRs.getString(4), checkOutRs.getString(5), checkOutRs.getString(6), checkOutRs.getString(7), checkOutRs.getString(8), checkOutRs.getString(9), checkOutRs.getString(10), checkOutRs.getString(11), checkOutRs.getString(12), checkOutRs.getString(13)));
        }

        roomData.setItems(checkOutList);
        checkOutRs.close();

    }
}

