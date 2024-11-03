package com.example.gearapp.activity;

import static com.example.gearapp.MyDatabaseHelper.COLUMN_ORDER_ADDRESS;
import static com.example.gearapp.MyDatabaseHelper.COLUMN_ORDER_DATEORDER;
import static com.example.gearapp.MyDatabaseHelper.COLUMN_ORDER_EMAIL;
import static com.example.gearapp.MyDatabaseHelper.COLUMN_ORDER_ID;
import static com.example.gearapp.MyDatabaseHelper.COLUMN_ORDER_PHONE;
import static com.example.gearapp.MyDatabaseHelper.COLUMN_ORDER_QUANTITY;
import static com.example.gearapp.MyDatabaseHelper.COLUMN_ORDER_STATUS;
import static com.example.gearapp.MyDatabaseHelper.COLUMN_ORDER_TOTALMONEY;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gearapp.MyDatabaseHelper;
import com.example.gearapp.R;
import com.example.gearapp.adapter.OrderAdapter;
import com.example.gearapp.model.Order;
import com.example.gearapp.model.User;

import java.util.ArrayList;

public class UserOrderActivity extends AppCompatActivity {
    private MyDatabaseHelper dbHelper;
    private RecyclerView recyclerView;
    private ArrayList<Order> orderList;
    private OrderAdapter orderAdapter; // Đảm bảo bạn có một adapter riêng cho RecyclerView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_order);

        dbHelper = new MyDatabaseHelper(this);
        recyclerView = findViewById(R.id.recyclerViewOrders);
        orderList = new ArrayList<>();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        orderAdapter = new OrderAdapter(this, orderList);
        recyclerView.setAdapter(orderAdapter);

        int userId = 2;
        //getIntent().getIntExtra("user_id", -1);
        if (userId == -1) {
            Toast.makeText(this, "ID người dùng không hợp lệ", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        loadUserOrders(userId);
    }

    private void loadUserOrders(int userId) {
        Cursor cursor = dbHelper.getUserOrders(userId);

        try {
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    Order order = new Order(
                            cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ORDER_ID)),
                            cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ORDER_ADDRESS)),
                            cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ORDER_PHONE)),
                            cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ORDER_EMAIL)),
                            cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ORDER_QUANTITY)),
                            cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ORDER_TOTALMONEY)),
                            cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ORDER_STATUS)),
                            cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ORDER_DATEORDER)),
                            new User(userId) // Giả sử lớp User có constructor nhận userId
                    );

                    orderList.add(order);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e("UserOrderActivity", "Lỗi khi tải đơn hàng: " + e.getMessage());
            Toast.makeText(this, "Lỗi khi tải đơn hàng", Toast.LENGTH_SHORT).show();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        orderAdapter.notifyDataSetChanged();
    }


}

