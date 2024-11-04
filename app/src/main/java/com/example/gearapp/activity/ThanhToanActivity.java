package com.example.gearapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.example.gearapp.R;
import com.example.gearapp.OrderDatabaseHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ThanhToanActivity extends AppCompatActivity {
    TextView txtTotalPrice;
    EditText edtPhoneNumber, edtEmail, edtShippingAddress;
    Button btnPlaceOrder;
    OrderDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan);

        // Set up toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Find views
        txtTotalPrice = findViewById(R.id.txtTotalPrice);
        edtPhoneNumber = findViewById(R.id.edtPhoneNumber);
        edtEmail = findViewById(R.id.edtEmail);
        edtShippingAddress = findViewById(R.id.edtShippingAddress);
        btnPlaceOrder = findViewById(R.id.btnPlaceOrder);

        // Initialize database helper
        dbHelper = new OrderDatabaseHelper(this);

        // Get data from intent
        Intent intent = getIntent();
        double totalPrice = intent.getDoubleExtra("TOTAL_PRICE", 0.0);
        txtTotalPrice.setText(String.format("Total: $%.2f", totalPrice));

        // Place Order button click listener
        btnPlaceOrder.setOnClickListener(v -> {
            String phone = edtPhoneNumber.getText().toString().trim();
            String emailInput = edtEmail.getText().toString().trim();
            String shippingAddress = edtShippingAddress.getText().toString().trim();

            // Check input fields
            if (phone.isEmpty() || emailInput.isEmpty() || shippingAddress.isEmpty()) {
                Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            } else {
                // Log the information for debugging
                Log.d("OrderInfo", "Phone: " + phone);
                Log.d("OrderInfo", "Email: " + emailInput);
                Log.d("OrderInfo", "Shipping Address: " + shippingAddress);

                // Prepare data for insertion
                int quantity = 1; // Example quantity
                String status = "Pending"; // Initial status
                String dateOrder = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

                // Insert order into database
                boolean success = dbHelper.insertOrder(
                        shippingAddress, phone, emailInput, quantity,
                        String.format(Locale.getDefault(), "%.2f", totalPrice),
                        status, dateOrder);

                if (success) {
                    Toast.makeText(this, "Đặt hàng thành công!", Toast.LENGTH_LONG).show();
                    finish(); // Close activity after placing the order
                } else {
                    Toast.makeText(this, "Đặt hàng thất bại", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
