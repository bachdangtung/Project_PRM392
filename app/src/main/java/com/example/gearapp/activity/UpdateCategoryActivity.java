package com.example.gearapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gearapp.MyDatabaseHelper;
import com.example.gearapp.R;
import com.example.gearapp.model.Category;

public class UpdateCategoryActivity extends AppCompatActivity {

    private EditText editTextCategoryName, editTextImageUrl;
    private Button buttonSaveCategory;
    private MyDatabaseHelper db;
    private int categoryId = -1; // ID của danh mục đang được cập nhật (mặc định -1 nếu thêm mới)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_category);

        // Khởi tạo các thành phần giao diện
        initViews();

        // Khởi tạo database helper
        db = new MyDatabaseHelper(this);

        // Lấy ID danh mục từ Intent (nếu có)
        categoryId = getIntent().getIntExtra("categoryId", -1);

        // Nếu có ID, tức là cập nhật danh mục, thì load dữ liệu của danh mục
        if (categoryId != -1) {
            loadCategoryData(categoryId);
        }

        // Thiết lập sự kiện khi nhấn nút lưu
        buttonSaveCategory.setOnClickListener(view -> saveCategory());
    }

    // Khởi tạo các thành phần giao diện
    private void initViews() {
        editTextCategoryName = findViewById(R.id.editTextCategoryName);
        editTextImageUrl = findViewById(R.id.editTextImageUrl);
        buttonSaveCategory = findViewById(R.id.buttonSaveCategory);
    }

    // Phương thức tải dữ liệu danh mục
    private void loadCategoryData(int id) {
        Category category = db.getCategoryById(id);
        if (category != null) {
            editTextCategoryName.setText(category.getName());
            editTextImageUrl.setText(category.getImage());
        }
    }

    // Phương thức lưu hoặc cập nhật danh mục
    private void saveCategory() {
        String name = editTextCategoryName.getText().toString().trim();
        String imageUrl = editTextImageUrl.getText().toString().trim();

        if (name.isEmpty() || imageUrl.isEmpty()) {
            Toast.makeText(this, "Vui lòng điền đủ thông tin!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (categoryId == -1) {
            // Thêm danh mục mới
            boolean isInserted = db.insertCategory(new Category(0, name, imageUrl));
            if (isInserted) {
                Toast.makeText(this, "Thêm danh mục thành công!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Thêm danh mục thất bại!", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Cập nhật danh mục hiện có
            boolean isUpdated = db.updateCategory(new Category(categoryId, name, imageUrl));
            if (isUpdated) {
                Toast.makeText(this, "Cập nhật danh mục thành công!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Cập nhật danh mục thất bại!", Toast.LENGTH_SHORT).show();
            }
        }

        // Quay lại Activity trước đó sau khi lưu hoặc cập nhật
        Intent resultIntent = new Intent();
        resultIntent.putExtra("update_success", true); // Gửi thông báo rằng đã cập nhật thành công
        setResult(RESULT_OK, resultIntent); // Thiết lập kết quả trả về
        finish(); // Đóng Activity
    }
}
