package com.example.gearapp.model;


public class Order {
    private int id;
    private String address;
    private String phone;
    private String email;
    private int quantity;
    private String totalMoney;
    private String status;
    private String dateOrder;
    User user;

    public Order() {
    }

    public Order(int id, String totalMoney, String status, String dateOrder, User user) {
        this.id = id;
        this.totalMoney = totalMoney;
        this.status = status;
        this.dateOrder = dateOrder;
        this.user = user;
    }

    public Order(int id, String address, String phone, String email, int quantity, String totalMoney, String status, String dateOrder, User user) {
        this.id = id;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.quantity = quantity;
        this.totalMoney = totalMoney;
        this.status = status;
        this.dateOrder = dateOrder;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(String dateOrder) {
        this.dateOrder = dateOrder;
    }

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
