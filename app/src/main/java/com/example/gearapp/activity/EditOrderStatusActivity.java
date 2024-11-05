package com.example.gearapp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.gearapp.MyDatabaseHelper;
import com.example.gearapp.R;
import com.example.gearapp.model.Order;

public class EditOrderStatusActivity extends AppCompatActivity {

    private EditText editTextStatus;
    private Button btnSave;
    private MyDatabaseHelper db;
    private int orderId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_order_status);

        // Lấy dữ liệu từ Intent
        orderId = getIntent().getIntExtra("orderId", -1);
        String currentStatus = getIntent().getStringExtra("status");

        editTextStatus = findViewById(R.id.editTextStatus);
        btnSave = findViewById(R.id.btnSave);
        db = new MyDatabaseHelper(this);

        // Hiển thị trạng thái hiện tại
        editTextStatus.setText(currentStatus);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newStatus = editTextStatus.getText().toString();
                if (!newStatus.isEmpty()) {
                    db.updateOrderStatus(orderId, newStatus);
                    Toast.makeText(EditOrderStatusActivity.this, "Đã cập nhật trạng thái", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(EditOrderStatusActivity.this, "Trạng thái không được để trống", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
