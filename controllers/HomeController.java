package controllers;

import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
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
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
        String name = customerNameCO.getText();
        String mobile = customerMobileNumCO.getText();
        String email = emailCO.getText();

        String checkOut = checkOutDateCO.getText();
        String noOfDays = numOfDaysStayCO.getText();
        String totalPrice = totalAmountCO.getText();
        checkOutRoomNo = roomNumTxt.getText();
        checkOutQuery = "UPDATE customers SET numberOdDayStay='"+noOfDays+"', totalAmount= '"+totalPrice+"',chekOut='"+checkOut+"' WHERE bookingId='"+checkOutId+"'";
        InsertUpdateDelete.setData(checkOutQuery, "");
        checkOutQuery = "UPDATE rooms SET status='Available' WHERE roomNo='"+checkOutRoomNo+"'";
        InsertUpdateDelete.setData(checkOutQuery, "");

        String path="H:\\hotel-management-sys1\\";
        com.itextpdf.text.Document document = new com.itextpdf.text.Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(path+""+checkOutId+".pdf"));
            document.open();
            Paragraph paragraph1 = new Paragraph("\tHotel Management System-Customer Check Out Details");
            document.add(paragraph1);
            Paragraph paragraph2 = new Paragraph("****************************************************************************************************************");
            document.add(paragraph2);
            Paragraph paragraph3 = new Paragraph("\tBill No: "+checkOutId+"\nCustomer Details: \nName: "+name+"\nMobile No: "+mobile+"\nEmail: "+email+"\n");
            document.add(paragraph3);
            document.add(paragraph2);
            Paragraph paragraph4 = new Paragraph("\tRoom Details:\nRoom Number "+roomNumTxt.getText()+"\nRoom Type: "+checkOutRoomType+"\nBed: "+checkOutBed+"\nPrice per day: "+pricePerDayCO.getText()+"");
            document.add(paragraph4);
            document.add(paragraph2);
            PdfPTable table1 = new PdfPTable(4);
            table1.addCell("Check In Date"+checkInDateCO.getText());
            table1.addCell("Check Out Date"+checkOut);
            table1.addCell("No Of Days Stay"+noOfDays);
            table1.addCell("Total Amount To Paid"+totalPrice);
            document.add(table1);
            document.add(paragraph2);
            Paragraph paragraph5=new Paragraph("Thank You for staying with us. See you again");
            document.add(paragraph5);
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        document.close();
        int option=JOptionPane.showConfirmDialog(null,"Do you want to print the bill?","Confirm",JOptionPane.YES_NO_OPTION);
        if(option==0){
            try {
                if((new File("H:\\hotel-management-sys1\\"+checkOutId+".pdf")).exists()){
                    Process process = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler H:\\hotel-management-sys1\\"+checkOutId+".pdf");
                }
                else
                    JOptionPane.showMessageDialog(null,"File is not exist");
            }
            catch (Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
        }

    }

    public void clearOnAction(ActionEvent actionEvent) {
        try{
            Stage loginStage = new Stage();
            Parent root = FXMLLoader.load(this.getClass().getResource("/forms/Home.fxml"));
            loginStage.setScene(new Scene(root,1300,500));
            loginStage.setTitle("Home");
            Stage stage = (Stage) roomNumTxt.getScene().getWindow();
            stage.close();
            loginStage.show();

        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void searchOnAction(ActionEvent actionEvent) {
        String roomNo=roomNumTxt.getText();
        try{
            String sql = "SELECT * FROM customers WHERE roomNo = '"+roomNo+"' AND chekOut IS NULL";
            ResultSet rs = Select.resultSet(sql);
            if (rs.next()){
                roomNumTxt.setEditable(false);
                checkOutId = rs.getInt(1);
                customerNameCO.setText(rs.getString(2));
                customerMobileNumCO.setText(rs.getString(3));
                checkInDateCO.setText(rs.getString(9));
                pricePerDayCO.setText(rs.getString(13));
                emailCO.setText(rs.getString(6));

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Calendar c = Calendar.getInstance();
                checkOutDateCO.setText(sdf.format(c.getTime()));
                String dateBefore = rs.getString(9);
                Date date1 = sdf.parse(dateBefore);
                String dateAfter = sdf.format(c.getTime());
                Date date2 = sdf.parse(dateAfter);
                long diff = date2.getTime() - date1.getTime();
                int days = (int) (diff / (1000*60*60*24));
                if (days == 0)
                    days = 1;

                numOfDaysStayCO.setText(String.valueOf(days));
                float totalPrice = Float.parseFloat(pricePerDayCO.getText()) * days;
                totalAmountCO.setText(String.valueOf(totalPrice));
                checkOutRoomType = rs.getString(12);
                checkOutBed = rs.getString(11);
            }
            else {
                JOptionPane.showMessageDialog(null,"Customer is already checked out");
            }
        } catch (SQLException | ParseException throwables) {
            throwables.printStackTrace();
        }
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

