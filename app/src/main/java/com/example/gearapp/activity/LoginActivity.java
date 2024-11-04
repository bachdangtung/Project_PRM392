package com.example.gearapp.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;
import com.google.android.material.textfield.TextInputEditText;
import com.example.gearapp.MyDatabaseHelper;
import com.example.gearapp.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    TextInputEditText username, password;
    TextView registerText;
    Button btnlogin;
    MyDatabaseHelper DB;
    private static final String ADMIN_USER = "admin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (TextInputEditText) findViewById(R.id.username1);
        password = (TextInputEditText) findViewById(R.id.password1);
        btnlogin = (Button) findViewById(R.id.btnsignin1);
        registerText = findViewById(R.id.registerText);
        DB = new MyDatabaseHelper(this);

        // Nhận dữ liệu từ RegisterActivity
        Intent registerIntent = getIntent();

        final String emailFromRegister = registerIntent.getStringExtra("email");
        final String phoneFromRegister = registerIntent.getStringExtra("phone");

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = username.getText().toString();
                String pass = password.getText().toString();

                if (user.equals("") || pass.equals(""))
                    Toast.makeText(LoginActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkuserpass = DB.checkusernamepassword(user, pass);
                    if (checkuserpass == true) {
                        // So sánh email đăng nhập với email admin cố định
                        if (user.equals(ADMIN_USER)) {
                            // Nếu là admin, chuyển đến AdminDashboard
                            Toast.makeText(LoginActivity.this, "Sign in successfull", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, AdminDashboardActivity.class);
                            intent.putExtra("username", user);
                            intent.putExtra("email", emailFromRegister);
                            intent.putExtra("phone", phoneFromRegister);
                            startActivity(intent);
                        } else {
                            // Nếu là user, chuyển đến HomeActivity
                            Toast.makeText(LoginActivity.this, "Sign in successfull", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.putExtra("username", user);
                            intent.putExtra("email", emailFromRegister);
                            intent.putExtra("phone", phoneFromRegister);
                            startActivity(intent);
                        }
                    } else {
                        Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}