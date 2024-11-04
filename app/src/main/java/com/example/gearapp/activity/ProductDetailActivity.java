package com.example.gearapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.gearapp.R;

public class ProductDetailActivity extends AppCompatActivity {
    Toolbar toolbar;
    ImageView imgProductDetail;
    TextView txtProductName, txtPrice, txtProductDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        // Find the views
        txtProductName = findViewById(R.id.txtproductname);
        txtPrice = findViewById(R.id.txtprice);
        imgProductDetail = findViewById(R.id.imgproductdetail);
        txtProductDescription = findViewById(R.id.txtproductdescription);

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
    }

}