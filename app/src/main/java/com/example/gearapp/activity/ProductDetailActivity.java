package com.example.gearapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
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
    Spinner spinnerQuantity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AppCompatActivity) this).setContentView(R.layout.activity_product_detail);

        txtProductName = findViewById(R.id.txtproductname);
        txtPrice = findViewById(R.id.txtprice);
        imgProductDetail = findViewById(R.id.imgproductdetail);
        txtProductDescription = findViewById(R.id.txtproductdescription);
        spinnerQuantity = findViewById(R.id.spinner);
        btnAddToCart = findViewById(R.id.btnaddtocart);

        Integer[] quantities = new Integer[99];
        for (int i = 0; i < 99; i++) {
            quantities[i] = i + 1;
        }
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, quantities);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerQuantity.setAdapter(adapter);

        Intent intent = getIntent();
        String productName = intent.getStringExtra("productName");
        String productPrice = intent.getStringExtra("productPrice");
        String productImage = intent.getStringExtra("productImage");
        String productDescription = intent.getStringExtra("productDescription");

        txtProductName.setText(productName);
        txtPrice.setText("Price: " + productPrice + "Đ");
        Glide.with(this).load(productImage).into(imgProductDetail);
        txtProductDescription.setText(productDescription);


        btnAddToCart.setOnClickListener(v -> {
            // Get the selected quantity from Spinner
            int quantity = (Integer) spinnerQuantity.getSelectedItem();

            Product product = new Product();
            product.setName(productName);
            product.setPrice(Double.parseDouble(productPrice.replace("Đ", "").trim()) * quantity); // Multiply price by quantity
            product.setImage(productImage);
            product.setDescription(productDescription);

            CartManager.getInstance().addProduct(product, quantity);

            Toast.makeText(this, "Added to cart: " + productName + " (x" + quantity + ")", Toast.LENGTH_SHORT).show();

            Intent cartIntent = new Intent(ProductDetailActivity.this, CartActivity.class);
            startActivity(cartIntent);
        });



    }





}