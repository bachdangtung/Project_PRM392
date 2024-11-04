package com.example.gearapp.activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gearapp.MyDatabaseHelper;
import com.example.gearapp.R;
import com.example.gearapp.adapter.OrderManagementAdapter;
import com.example.gearapp.model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderManagementActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private OrderManagementAdapter adapter;
    private List<Order> orderList;
    private MyDatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_management);

        initViews();
        setupDatabase();
        loadOrders();
    }


    private void initViews() {
        recyclerView = findViewById(R.id.recyclerViewOrders);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    private void setupDatabase() {
        db = new MyDatabaseHelper(this);
    }

    // Method to load orders from the database
    private void loadOrders() {
        orderList = db.getSimpleOrderList();

        if (orderList.isEmpty()) {
            Toast.makeText(this, "No orders found!", Toast.LENGTH_SHORT).show();
        }

        // Set up the adapter for RecyclerView and pass in the context
        adapter = new OrderManagementAdapter(this, orderList);
        recyclerView.setAdapter(adapter); // Gán adapter sau khi kiểm tra dữ liệu
    }
}
