package com.example.gearapp.activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gearapp.MyDatabaseHelper;
import com.example.gearapp.R;
import com.example.gearapp.adapter.OrderDetailAdapter;

import com.example.gearapp.model.OrderDetail;
import com.example.gearapp.model.Product;

import java.util.ArrayList;

public class OrderDetailActivity extends AppCompatActivity {
    private MyDatabaseHelper dbHelper;
    private RecyclerView recyclerView;
    private ArrayList<OrderDetail> orderDetailList;
    private OrderDetailAdapter orderDetailAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        dbHelper = new MyDatabaseHelper(this);
        recyclerView = findViewById(R.id.recyclerViewOrderDetails);
        orderDetailList = new ArrayList<>();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        orderDetailAdapter = new OrderDetailAdapter(orderDetailList);
        recyclerView.setAdapter(orderDetailAdapter);

        Intent intent = getIntent();
        int orderId = intent.getIntExtra("order_id", -1);

        if (orderId != -1) {
            loadOrderDetails(orderId);
        } else {
            Toast.makeText(this, "ID đơn hàng không hợp lệ", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void loadOrderDetails(int orderId) {
        Cursor cursor = dbHelper.getOrderDetails(orderId);

        try {
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                    int quantity = cursor.getInt(cursor.getColumnIndexOrThrow("quantity"));
                    double price = cursor.getDouble(cursor.getColumnIndexOrThrow("price"));
                    String productName = cursor.getString(cursor.getColumnIndexOrThrow("product_name"));
                    String productImage = cursor.getString(cursor.getColumnIndexOrThrow("product_image"));

                    Product product = new Product();
                    product.setName(productName);
                    product.setImage(productImage);

                    OrderDetail orderDetail = new OrderDetail(id, quantity, price, null, product);
                    orderDetailList.add(orderDetail);
                } while (cursor.moveToNext());
            } else {
                Toast.makeText(this, "Không tìm thấy chi tiết đơn hàng", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.e("OrderDetailActivity", "Lỗi khi tải chi tiết đơn hàng: " + e.getMessage());
            Toast.makeText(this, "Lỗi khi tải chi tiết đơn hàng", Toast.LENGTH_SHORT).show();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        orderDetailAdapter.notifyDataSetChanged();
    }

}




