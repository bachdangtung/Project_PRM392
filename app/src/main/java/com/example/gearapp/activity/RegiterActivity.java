package com.example.gearapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.gearapp.MyDatabaseHelper;
import com.example.gearapp.R;

public class RegiterActivity extends AppCompatActivity {
    EditText editEmailAddressReg, editPasswordReg, editFullnameReg, editPhoneNumberReg, editStatusReg;

    Button btnRegisterReg, btnLoginReg;
    TextView txtDisplayInfoReg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_regiter);

        editEmailAddressReg = findViewById(R.id.editEmailAddressReg);
        editPasswordReg = findViewById(R.id.editPasswordReg);
        editFullnameReg = findViewById(R.id.editFullnameReg);
        editPhoneNumberReg = findViewById(R.id.editPhoneNumberReg);
        editStatusReg = findViewById(R.id.editStatusReg);
        //editDOBReg = findViewById(R.id.editDOBReg);
        //editBioReg = findViewById(R.id.editBioReg);

        txtDisplayInfoReg = findViewById(R.id.txtDisplayInfoReg);

        btnLoginReg = findViewById(R.id.btnLoginReg);
        btnRegisterReg = findViewById(R.id.btnRegisterReg);


        btnLoginReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RegiterActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });

        btnRegisterReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(RegiterActivity.this);
                myDB.addUser(
                        editFullnameReg.getText().toString().trim(),
                        editEmailAddressReg.getText().toString().trim(),
                        editPasswordReg.getText().toString().trim(),
                        editPhoneNumberReg.getText().toString().trim(),
                        editStatusReg.getText().toString().trim()
                );
            }
        });

    }
}