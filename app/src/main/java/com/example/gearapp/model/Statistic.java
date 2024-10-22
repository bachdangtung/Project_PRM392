package com.example.gearapp.model;

public class Statistic {
    private String date;
    private double totalMoney;
    private int totalOrder;
    private int totalProduct;

    public Statistic() {
    }

    public Statistic(int totalOrder, double totalMoney, String date, int totalProduct) {
        this.totalOrder = totalOrder;
        this.totalMoney = totalMoney;
        this.date = date;
        this.totalProduct = totalProduct;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTotalProduct() {
        return totalProduct;
    }

    public void setTotalProduct(int totalProduct) {
        this.totalProduct = totalProduct;
    }

    public int getTotalOrder() {
        return totalOrder;
    }

    public void setTotalOrder(int totalOrder) {
        this.totalOrder = totalOrder;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }
}
