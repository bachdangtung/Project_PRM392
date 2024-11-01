package com.example.gearapp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.gearapp.MyDatabaseHelper;
import com.example.gearapp.R;

public class AddCategory extends AppCompatActivity {

    EditText edname,edImage;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_category);
         edname = findViewById(R.id.editTextCategoryImage);
         edImage = findViewById(R.id.editTextCategoryName);
         btnSave = findViewById(R.id.buttonSaveCategory);
         btnSave.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 String name = edname.getText().toString().trim();
                 String image = edImage.getText().toString().trim();
                 if (name.isEmpty() ||   image.isEmpty()) {
                     Toast.makeText(AddCategory.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                 } else {

                     MyDatabaseHelper myDB = new MyDatabaseHelper(AddCategory.this);
                     myDB.addCategory(name, image);
                 }
             }
         });
    }
}