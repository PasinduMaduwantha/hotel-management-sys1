package controllers;

public class TableData {
    private String name;
    private String email;
    private String question;
    private String address;
    private String status;



    public TableData(String name, String email, String question, String address, String status) {
        this.name = name;
        this.email = email;
        this.question = question;
        this.address = address;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getQuestion() {
        return question;
    }

    public String getAddress() {
        return address;
    }

    public String getStatus() {
        return status;
    }
}

