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

import java.util.List;

public class OrderManagementActivity extends AppCompatActivity implements OrderManagementAdapter.OnOrderClickListener {

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

        // Initialize adapter with OnOrderClickListener implemented in this activity
        adapter = new OrderManagementAdapter(this, orderList, this);
        recyclerView.setAdapter(adapter);
    }

    // Implementing the OnOrderClickListener interface for handling clicks
    @Override
    public void onOrderClick(Order order) {
        // Toggle status between "Pending" and "Completed"
        String newStatus = order.getStatus().equals("Pending") ? "Completed" : "Pending";

        // Update the status in the database
        if (db.updateOrderStatus(order.getId(), newStatus)) {
            order.setStatus(newStatus); // Update the status in the current list
            adapter.notifyDataSetChanged(); // Refresh RecyclerView
            Toast.makeText(this, "Order status updated!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed to update status.", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        loadOrders(); // Tải lại danh sách đơn hàng để cập nhật thay đổi
    }
}
