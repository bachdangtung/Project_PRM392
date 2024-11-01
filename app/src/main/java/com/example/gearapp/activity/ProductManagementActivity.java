package com.example.gearapp.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.gearapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ProductManagementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_product_management);
        FloatingActionButton addButton = findViewById(R.id.button_add_product);


        addButton.setOnClickListener(view -> {
            Intent intent = new Intent(ProductManagementActivity.this, AddProductActivity.class);
            startActivity(intent);
        });
    }
}