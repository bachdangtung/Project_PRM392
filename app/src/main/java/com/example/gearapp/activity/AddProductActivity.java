package com.example.gearapp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gearapp.MyDatabaseHelper;
import com.example.gearapp.R;

public class AddProductActivity extends AppCompatActivity {


    EditText edName, edPrice,edDescription, edImage;
    Button btnSave;
    Spinner spinnerCategory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_product);

        edName = findViewById(R.id.input_name);
        edPrice = findViewById(R.id.input_price);
        edDescription = findViewById(R.id.input_description);
        edImage = findViewById(R.id.input_image);
        spinnerCategory = findViewById(R.id.spinner_category);
        btnSave = findViewById(R.id.button_save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = edName.getText().toString().trim();
                String image = edImage.getText().toString().trim();
                String price = edPrice.getText().toString().trim();
                String description = edDescription.getText().toString().trim();


                int category_id = (int) spinnerCategory.getSelectedItemId();

                if (name.isEmpty() || price.isEmpty() || description.isEmpty() || image.isEmpty()) {
                    Toast.makeText(AddProductActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {

                    MyDatabaseHelper myDB = new MyDatabaseHelper(AddProductActivity.this);
                    myDB.addProduct(name, image, price, description, category_id);
                }
            }
        });
    }
}