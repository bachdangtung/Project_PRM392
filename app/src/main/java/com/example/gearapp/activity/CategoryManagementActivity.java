package com.example.gearapp.activity;


import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.gearapp.MyDatabaseHelper;
import com.example.gearapp.R;
import com.example.gearapp.adapter.CategoryManagementAdapter;
import com.example.gearapp.model.Category;

import java.util.ArrayList;
import java.util.List;


public class CategoryManagementActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CategoryManagementAdapter adapter;
    private List<Category> categoryList;
    private MyDatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_management);

        recyclerView = findViewById(R.id.recyclerViewCategory);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Khởi tạo MyDatabaseHelper
        db = new MyDatabaseHelper(this);

        // Tải danh mục từ cơ sở dữ liệu
        loadCategories();
    }
    private void loadCategories() {
        categoryList = new ArrayList<>();
        Cursor cursor = db.readAllCategory(); // Gọi hàm để đọc tất cả danh mục từ cơ sở dữ liệu

        if (cursor != null) {
            int idIndex = cursor.getColumnIndex("id");
            int nameIndex = cursor.getColumnIndex("name");
            int imageIndex = cursor.getColumnIndex("image");

            // Kiểm tra xem các cột có tồn tại hay không
            if (idIndex != -1 && nameIndex != -1 && imageIndex != -1) {
                while (cursor.moveToNext()) {
                    // Lấy thông tin từ con trỏ
                    int id = cursor.getInt(idIndex);
                    String name = cursor.getString(nameIndex);
                    String image = cursor.getString(imageIndex);

                    // Tạo đối tượng Category và thêm vào danh sách
                    Category category = new Category(id, name, image);
                    categoryList.add(category);
                }
            } else {
                // Xử lý khi một hoặc nhiều cột không tồn tại
                Toast.makeText(this, "Column names do not exist in the database!", Toast.LENGTH_SHORT).show();
            }
            cursor.close(); // Đóng con trỏ
        }

        // Tạo và gán adapter cho RecyclerView
        adapter = new CategoryManagementAdapter(categoryList);
        recyclerView.setAdapter(adapter);
    }

}
