package com.example.gearapp.activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gearapp.MyDatabaseHelper;
import com.example.gearapp.R;
import com.example.gearapp.adapter.ProductManagementAdapter; // Thay đổi đây
import com.example.gearapp.model.Category;
import com.example.gearapp.model.Product;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ProductManagementActivity extends AppCompatActivity {

    private RecyclerView recyclerViewProducts;
    private ProductManagementAdapter productAdapter; // Thay đổi đây
    private List<Product> productList;
    private MyDatabaseHelper myDB;
    private FloatingActionButton fabAddProduct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_management);

        recyclerViewProducts = findViewById(R.id.recyclerView_products);
        recyclerViewProducts.setLayoutManager(new LinearLayoutManager(this));

        myDB = new MyDatabaseHelper(this);

        // Lấy dữ liệu sản phẩm từ cơ sở dữ liệu
        loadProducts();



        // Set adapter cho RecyclerView
        productAdapter = new ProductManagementAdapter(this, productList);
        recyclerViewProducts.setAdapter(productAdapter);
        fabAddProduct = findViewById(R.id.button_add_product);
        fabAddProduct.setOnClickListener(v -> {
            Intent intent = new Intent(ProductManagementActivity.this, AddProductActivity.class);
            startActivity(intent);
        });

    }

    private void loadProducts() {
        productList = new ArrayList<>();

        Cursor cursor = myDB.readAllProduct();

        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                double price = cursor.getDouble(2);
                String description = cursor.getString(3);
                String image = cursor.getString(4);
                int categoryId = cursor.getInt(5);
                Category category = myDB.getCategoryById(categoryId); // Lấy danh mục dựa vào ID

                productList.add(new Product(id, category, description, price, image, name));
            }
            cursor.close();
        }
    }
}
