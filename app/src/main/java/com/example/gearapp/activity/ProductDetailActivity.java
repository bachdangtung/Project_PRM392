package com.example.gearapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.gearapp.R;

import android.widget.Toast; // For Toast notifications
import com.example.gearapp.model.Product; // For Product class
import com.example.gearapp.model.CartManager; // For CartManager class

public class ProductDetailActivity extends AppCompatActivity {
    Toolbar toolbar;
    ImageView imgProductDetail;
    TextView txtProductName, txtPrice, txtProductDescription;
    Button btnAddToCart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_product_detail);
        ((AppCompatActivity) this).setContentView(R.layout.activity_product_detail);

        // Find the views
        txtProductName = findViewById(R.id.txtproductname);
        txtPrice = findViewById(R.id.txtprice);
        imgProductDetail = findViewById(R.id.imgproductdetail);
        txtProductDescription = findViewById(R.id.txtproductdescription);
        btnAddToCart = findViewById(R.id.btnaddtocart);

        // Retrieve data from the Intent
        Intent intent = getIntent();
        String productName = intent.getStringExtra("productName");
        String productPrice = intent.getStringExtra("productPrice");
        String productImage = intent.getStringExtra("productImage");
        String productDescription = intent.getStringExtra("productDescription");

        // Set the data to the views
        txtProductName.setText(productName);
        txtPrice.setText("Price: " + productPrice + "Đ");
        Glide.with(this).load(productImage).into(imgProductDetail);
        txtProductDescription.setText(productDescription);


        // Set click listener for the "Add to Cart" button
        btnAddToCart.setOnClickListener(v -> {
            // Create a new Product instance
            Product product = new Product();
            product.setName(productName);
            product.setPrice(Double.parseDouble(productPrice.replace("Đ", "").trim()));
            product.setImage(productImage);
            product.setDescription(productDescription);

            // Add the product to the CartManager
            CartManager.getInstance().addProduct(product);

            // Start CartActivity to display the cart
            Intent cartIntent = new Intent(ProductDetailActivity.this, CartActivity.class);
            startActivity(cartIntent); // Navigate to the CartActivity
        });



    }





}