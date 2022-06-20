package com.example.ecomplaint;

import com.google.firebase.firestore.auth.User;

public class ComplaintDetails {


    private String title;
    private String name;
    private String phoneNumber;
    private String complaint;
    private String status;

    com.google.firebase.firestore.auth.User complaintFrom;
    // an empty constructor is
    // required when using
    // Firebase Realtime Database.
    String password,email;

    ComplaintDetails(String name, String password, String email){
        this.name=name;
        this.password=password;
        this.email=email;
    }
    public ComplaintDetails() {

    }

    // created getter and setter methods
    // for all our variables.
    public String getTitle() {
        return title;
    }

    public com.google.firebase.firestore.auth.User getComplaintFrom() {
        return complaintFrom;
    }
    public void setComplaintFrom() {
        this.complaintFrom=complaintFrom;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getComplaint() {
        return complaint;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }


}
