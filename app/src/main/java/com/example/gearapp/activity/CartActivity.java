package com.example.gearapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.gearapp.R;

public class CartActivity extends AppCompatActivity {

    private ImageView productImage;
    private TextView productName, productPrice;
    private Button btnPayment, btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        // Initialize views
        productImage = findViewById(R.id.cart_product_image);
        productName = findViewById(R.id.cart_product_name);
        productPrice = findViewById(R.id.cart_product_price);
        btnPayment = findViewById(R.id.btn_payment);
        btnDelete = findViewById(R.id.btn_delete);

        // Retrieve product details from intent
        String name = getIntent().getStringExtra("productName");
        String price = getIntent().getStringExtra("productPrice");
        String imageUrl = getIntent().getStringExtra("productImage");

        // Set product details
        productName.setText(name);
        productPrice.setText("Price: " + price + "Đ");
        Glide.with(this).load(imageUrl).into(productImage);

        // Set delete button listener
        btnDelete.setOnClickListener(v -> {
            // Clear product details and display message
            Toast.makeText(CartActivity.this, "Product removed from cart", Toast.LENGTH_SHORT).show();
            finish();
        });

        // Set payment button listener
        btnPayment.setOnClickListener(v -> {
            // Implement payment logic here
            Toast.makeText(CartActivity.this, "Proceeding to payment", Toast.LENGTH_SHORT).show();
        });
    }
}
