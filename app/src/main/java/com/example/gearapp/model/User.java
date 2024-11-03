package com.example.gearapp.model;

public class User {

    private int id;
    private String email;
    private String name;
    private String password;
    private String phonenumber;
    private String status;

    public User() {
    }

    public User(int id, String status, String phonenumber, String password, String name, String email) {
        this.id = id;
        this.status = status;
        this.phonenumber = phonenumber;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public User(int userId) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return phonenumber;
    }

    public void setMobile(String mobile) {
        this.phonenumber = mobile;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
