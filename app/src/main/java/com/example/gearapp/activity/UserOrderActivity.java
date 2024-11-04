package com.example.gearapp.activity;

import static com.example.gearapp.MyDatabaseHelper.COLUMN_ORDER_ADDRESS;
import static com.example.gearapp.MyDatabaseHelper.COLUMN_ORDER_DATEORDER;
import static com.example.gearapp.MyDatabaseHelper.COLUMN_ORDER_EMAIL;
import static com.example.gearapp.MyDatabaseHelper.COLUMN_ORDER_ID;
import static com.example.gearapp.MyDatabaseHelper.COLUMN_ORDER_PHONE;
import static com.example.gearapp.MyDatabaseHelper.COLUMN_ORDER_QUANTITY;
import static com.example.gearapp.MyDatabaseHelper.COLUMN_ORDER_STATUS;
import static com.example.gearapp.MyDatabaseHelper.COLUMN_ORDER_TOTALMONEY;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gearapp.MyDatabaseHelper;
import com.example.gearapp.activity.OrderDetailActivity;
import com.example.gearapp.R;
import com.example.gearapp.adapter.OrderAdapter;
import com.example.gearapp.model.Order;
import com.example.gearapp.model.User;

import java.util.ArrayList;

public class UserOrderActivity extends AppCompatActivity implements OrderAdapter.OnOrderClickListener {
    private MyDatabaseHelper dbHelper;
    private RecyclerView recyclerView;
    private ArrayList<Order> orderList;
    private OrderAdapter orderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_order);

        dbHelper = new MyDatabaseHelper(this);
        recyclerView = findViewById(R.id.recyclerViewOrders);
        orderList = new ArrayList<>();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        orderAdapter = new OrderAdapter(orderList, this);
        recyclerView.setAdapter(orderAdapter);

        // Thêm khoảng cách giữa các item
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.bottom = 50; // 50px khoảng cách giữa các item
            }
        });

        // Thêm đường kẻ đen giữa các item với nhau
        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        ColorDrawable colorDrawable = new ColorDrawable(Color.BLACK);
        divider.setDrawable(colorDrawable);
        recyclerView.addItemDecoration(divider);

        Intent intent = getIntent();
        int userId = intent.getIntExtra("user_id", -1);  // Thay đổi ID người dùng phù hợp
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
                    int orderId = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ORDER_ID));
                    String address = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ORDER_ADDRESS));
                    String phone = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ORDER_PHONE));
                    String email = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ORDER_EMAIL));
                    int quantity = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ORDER_QUANTITY));
                    String totalMoney = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ORDER_TOTALMONEY));
                    String status = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ORDER_STATUS));
                    String dateOrder = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ORDER_DATEORDER));

                    Order order = new Order(orderId, address, phone, email, quantity, totalMoney, status, dateOrder, new User(userId));
                    orderList.add(order);
                } while (cursor.moveToNext());
            } else {
                Toast.makeText(this, "Không tìm thấy đơn hàng", Toast.LENGTH_SHORT).show();
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

    @Override
    public void onOrderClick(int orderId) {
        // Chuyển sang màn hình chi tiết đơn hàng
        Intent intent = new Intent(this, OrderDetailActivity.class);
        intent.putExtra("order_id", orderId);
        startActivity(intent);
    }
}


