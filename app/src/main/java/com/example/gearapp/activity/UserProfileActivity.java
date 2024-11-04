package com.example.gearapp.activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.gearapp.MyDatabaseHelper;
import com.example.gearapp.R;

public class UserProfileActivity extends AppCompatActivity {

    private TextView userEmail1, username1, userEmail2, username2, mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_profile);

        userEmail1 = findViewById(R.id.userEmail);
        username1 = findViewById(R.id.username);
        userEmail2 = findViewById(R.id.userEmailText);
        username2 = findViewById(R.id.usernameText);
        mobile = findViewById(R.id.userNumber);
        MyDatabaseHelper DB = new MyDatabaseHelper(this);

        // Nhận thông tin từ Intent
        String email = getIntent().getStringExtra("email");
        String username = getIntent().getStringExtra("username");
        String phone = getIntent().getStringExtra("phone");
        String userEmailTextView = getIntent().getStringExtra("email");
        String usernameTextView = getIntent().getStringExtra("username");

        TextView backToHomeText = findViewById(R.id.backToHomeText);
        backToHomeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

            }
        });

        // Cập nhật TextView
        if (email != null) {
            userEmail1.setText(email);
        }
        if (username != null) {
            username1.setText(username);
        }
        if (phone != null) {
            mobile.setText(phone);
        }
        if (userEmailTextView != null) {
            userEmail2.setText(userEmailTextView);
        }
        if (usernameTextView != null) {
            username2.setText(usernameTextView);
        }

    }
}