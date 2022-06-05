package com.example.ecomplaint;

public class User {

    String name,password,address,contact,age,username;

    User(String name, String password,String address,String contact,String age,String username){
        this.name=name;
        this.password=password;
        this.username=username;
        this.age=age;
        this.contact=contact;
        this.address=address;
    }

    User(){

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {this.username = username;}

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAge(String age) {this.age = age;}

    public void setAddress(String address) {this.address = address;}

    public void setContact(String contact) {this.contact = contact;}

    public String getAddress() {return address;}

    public String getAge() {return age;}

    public String getName() {
        return name;
    }

    public String getUsername() {return username;}

    public String getContact() {return contact;}

    public String getPassword() {
        return password;
    }


}