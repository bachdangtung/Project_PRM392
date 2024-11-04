package com.example.gearapp.model;

public class Product {
    private int id;
    private String name;
    private String image;
    private double price;
    Category category;
    int category_id;
    private  String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Product(int category_id, Category category, double price) {
        this.category_id = category_id;
        this.category = category;
        this.price = price;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Product() {
    }

    public Product(int id, Category category, String description, double price, String image, String name) {
        this.id = id;
        this.image = image;
        this.name = name;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
