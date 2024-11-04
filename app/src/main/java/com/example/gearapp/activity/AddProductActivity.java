package com.example.gearapp.activity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gearapp.MyDatabaseHelper;
import com.example.gearapp.R;
import com.example.gearapp.model.Category;

import java.util.ArrayList;
import java.util.List;

public class AddProductActivity extends AppCompatActivity {

    private EditText edName, edPrice, edDescription, edImage;
    private Button btnSave;
    private Spinner spinnerCategory;
    private MyDatabaseHelper myDB;
    private List<Category> categoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        edName = findViewById(R.id.input_name);
        edPrice = findViewById(R.id.input_price);
        edDescription = findViewById(R.id.input_description);
        edImage = findViewById(R.id.input_image);
        spinnerCategory = findViewById(R.id.spinner_category);
        btnSave = findViewById(R.id.button_save);

        myDB = new MyDatabaseHelper(this);

        // Load dữ liệu danh mục vào Spinner
        loadCategories();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edName.getText().toString().trim();
                String image = edImage.getText().toString().trim();
                String price = edPrice.getText().toString().trim();
                String description = edDescription.getText().toString().trim();

                // Lấy ID của danh mục được chọn
                int selectedPosition = spinnerCategory.getSelectedItemPosition();
                int categoryId = categoryList.get(selectedPosition).getId();

                if (name.isEmpty() || price.isEmpty() || description.isEmpty() || image.isEmpty()) {
                    Toast.makeText(AddProductActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    // Thêm sản phẩm vào cơ sở dữ liệu
                    myDB.addProduct(name, price, description , image, categoryId);
                    Toast.makeText(AddProductActivity.this, "Thêm sản phẩm thành công", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    private void loadCategories() {
        categoryList = new ArrayList<>();
        Cursor cursor = myDB.readAllCategory();

        if (cursor != null) {
            // Lấy chỉ số của cột "id" và "name"
            int idIndex = cursor.getColumnIndex("id");
            int nameIndex = cursor.getColumnIndex("name");

            // Kiểm tra xem cả hai cột có tồn tại không
            if (idIndex != -1 && nameIndex != -1) {
                while (cursor.moveToNext()) {
                    // Lấy dữ liệu từ các cột "id" và "name"
                    int id = cursor.getInt(idIndex);
                    String name = cursor.getString(nameIndex);

                    // Tạo đối tượng Category chỉ với id và name, thêm vào danh sách
                    Category category = new Category(id, name);
                    categoryList.add(category);
                }
            } else {
                // Hiển thị thông báo nếu không tìm thấy cột "id" hoặc "name"
                Toast.makeText(this, "Không tìm thấy cột 'id' hoặc 'name' trong cơ sở dữ liệu!", Toast.LENGTH_SHORT).show();
            }
            cursor.close(); // Đóng con trỏ
        }

        // Chuyển danh sách tên Category vào Adapter
        List<String> categoryNames = new ArrayList<>();
        for (Category category : categoryList) {
            categoryNames.add(category.getName());
        }

        // Thiết lập Adapter cho Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categoryNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapter);
    }
}
