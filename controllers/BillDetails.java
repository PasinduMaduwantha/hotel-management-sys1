package controllers;

import javafx.scene.control.cell.PropertyValueFactory;

public class BillDetails {
    private String biiID;
    private String  billName;
    private String  billMobileNo;
    private String  billNationality;
    private String  billGender;
    private String  billEmail;
    private String  billIDProof;
    private String  billAddress;
    private String  billCheckInDate;
    private String  billRoomNumber;
    private String billBed;
    private String billRoomType;
    private String billPricePerDay;
    private String billNumOFDays;
    private String billTotalAmount;
    private String billCheckOutDate;

    public BillDetails(String biiID, String billName, String billMobileNo, String billNationality, String billGender, String billEmail, String billIDProof, String billAddress, String billCheckInDate, String billRoomNumber, String billBed, String billRoomType, String billPricePerDay, String billNumOFDays, String billTotalAmount, String billCheckOutDate) {
        this.biiID = biiID;
        this.billName = billName;
        this.billMobileNo = billMobileNo;
        this.billNationality = billNationality;
        this.billGender = billGender;
        this.billEmail = billEmail;
        this.billIDProof = billIDProof;
        this.billAddress = billAddress;
        this.billCheckInDate = billCheckInDate;
        this.billRoomNumber = billRoomNumber;
        this.billBed = billBed;
        this.billRoomType = billRoomType;
        this.billPricePerDay = billPricePerDay;
        this.billNumOFDays = billNumOFDays;
        this.billTotalAmount = billTotalAmount;
        this.billCheckOutDate = billCheckOutDate;
    }

    public String getBiiID() {
        return biiID;
    }

    public String getBillName() {
        return billName;
    }

    public String getBillMobileNo() {
        return billMobileNo;
    }

    public String getBillNationality() {
        return billNationality;
    }

    public String getBillGender() {
        return billGender;
    }

    public String getBillEmail() {
        return billEmail;
    }

    public String getBillIDProof() {
        return billIDProof;
    }

    public String getBillAddress() {
        return billAddress;
    }

    public String getBillCheckInDate() {
        return billCheckInDate;
    }

    public String getBillRoomNumber() {
        return billRoomNumber;
    }

    public String getBillBed() {
        return billBed;
    }

    public String getBillRoomType() {
        return billRoomType;
    }

    public String getBillPricePerDay() {
        return billPricePerDay;
    }

    public String getBillNumOFDays() {
        return billNumOFDays;
    }

    public String getBillTotalAmount() {
        return billTotalAmount;
    }

    public String getBillCheckOutDate() {
        return billCheckOutDate;
    }
}
