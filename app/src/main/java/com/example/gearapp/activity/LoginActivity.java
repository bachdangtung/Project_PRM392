package com.example.gearapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.gearapp.R;

public class LoginActivity extends AppCompatActivity {
    EditText editEmailAddressLog, editPasswordLog;
    Button btnLoginLog, btnRegisterLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        editEmailAddressLog = findViewById(R.id.editEmailAddressLog);
        editPasswordLog = findViewById(R.id.editPasswordLog);
        btnLoginLog = findViewById(R.id.btnLoginLog);
        btnRegisterLog = findViewById(R.id.btnRegisterLog);

        btnRegisterLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, RegiterActivity.class);
                startActivity(i);
            }
        });
    }
}