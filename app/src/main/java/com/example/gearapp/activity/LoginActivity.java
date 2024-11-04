package com.example.gearapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gearapp.R;
<<<<<<< Updated upstream
=======
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

import io.reactivex.rxjava3.disposables.CompositeDisposable;
>>>>>>> Stashed changes

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
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });
    }
}