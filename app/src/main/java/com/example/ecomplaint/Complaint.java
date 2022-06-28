package com.example.ecomplaint;

public class Complaint {

    String complaintID,title,name,regno,incident_info,status,email;

    //String complaintFrom="akhil";

//    Expert expert;

    com.example.ecomplaint.User complaintFrom;


    Complaint(){

    }


    Complaint(String id, String t, String n, String reg, String inc, com.example.ecomplaint.User comp_from, String stat){

        complaintID=id;
        title=t;
        name=n;
        regno=reg;
        incident_info=inc;
        complaintFrom=comp_from;

//        expert=exp;

        status=stat;

    }

    public com.example.ecomplaint.User getComplaintFrom() {
        return complaintFrom;
    }

    public String getComplaintID() {
        return complaintID;
    }

    public String getIncident_info() {
        return incident_info;
    }

    public String getName() {
        return name;
    }

    public String getRegno() {
        return regno;
    }

    public String getTitle() {
        return title;
    }

    public void setComplaintFrom(com.example.ecomplaint.User complaintFrom) {
        this.complaintFrom = complaintFrom;
    }

    public void setComplaintID(String complaintID) {
        this.complaintID = complaintID;
    }

    public void setIncident_info(String incident_info) {
        this.incident_info = incident_info;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRegno(String regno) {
        this.regno = regno;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() { return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

//    public void setExpert(Expert expert) {
//        this.expert = expert;
//    }
//
//    public Expert getExpert() {
//        return expert;
//    }
}



