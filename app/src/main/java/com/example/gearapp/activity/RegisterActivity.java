package com.example.gearapp.activity;

import static com.example.gearapp.R.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gearapp.MyDatabaseHelper;
import com.example.gearapp.R;
import com.example.gearapp.validate.RegisterValidate;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {
    TextInputEditText username, password, repassword, emailEdt, phonenumber;
    Button signup;
    TextView loginText;
    MyDatabaseHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_register);

        username = (TextInputEditText) findViewById(R.id.username);
        password = (TextInputEditText) findViewById(R.id.password);
        repassword = (TextInputEditText) findViewById(R.id.repassword);
        emailEdt = (TextInputEditText) findViewById(R.id.email);
        phonenumber = (TextInputEditText) findViewById(id.phonenumber);
        signup = (Button) findViewById(R.id.btnsignup);
        loginText = (TextView) findViewById(id.loginText);
        DB = new MyDatabaseHelper(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();
                String email = emailEdt.getText().toString();
                String phone = phonenumber.getText().toString();

                if(user.equals("")||pass.equals("")||repass.equals("")||email.equals("")||phone.equals(""))
                    Toast.makeText(RegisterActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else if (!RegisterValidate.validateUsername(user)) {
                    Toast.makeText(RegisterActivity.this, "Tên đăng nhập không hợp lệ", Toast.LENGTH_SHORT).show();
                }else if (!RegisterValidate.validatePassword(pass)) {
                    Toast.makeText(RegisterActivity.this, "Mật khẩu không hợp lệ", Toast.LENGTH_SHORT).show();
                }else if (!RegisterValidate.validateEmail(email)) {
                    Toast.makeText(RegisterActivity.this, "Email không hợp lệ", Toast.LENGTH_SHORT).show();
                }else if (!RegisterValidate.validatePhone(phone)) {
                    Toast.makeText(RegisterActivity.this, "Số điện thoại không hợp lệ", Toast.LENGTH_SHORT).show();
                }else{
                    if(pass.equals(repass)){
                        Boolean checkEmail = DB.checkEmail(email);
                        if(checkEmail==false){
                            Boolean insert = DB.addUser(email, user, pass, phone);
                            if(insert==true){
                                Toast.makeText(RegisterActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                                intent.putExtra("username", user);
                                intent.putExtra("email", email);
                                intent.putExtra("phone", phone);
                                startActivity(intent);
                            }else{
                                Toast.makeText(RegisterActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(RegisterActivity.this, "Email already exists! Please use a different email.", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(RegisterActivity.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}