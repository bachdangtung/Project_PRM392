package com.example.gearapp.activity;

import android.content.Intent;
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
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class CategoryManagementActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CategoryManagementAdapter adapter;
    private List<Category> categoryList;
    private MyDatabaseHelper db;
    private FloatingActionButton fabAddCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_management);

        initViews();
        setupDatabase();
        loadCategories();
        setupFabButton();
    }

    // Method to initialize views
    private void initViews() {
        recyclerView = findViewById(R.id.recyclerViewCategory);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        fabAddCategory = findViewById(R.id.fabAddCategory);
    }

    // Method to initialize the database helper
    private void setupDatabase() {
        db = new MyDatabaseHelper(this);
    }

    // Method to load categories from the database
    private void loadCategories() {
        categoryList = new ArrayList<>();
        Cursor cursor = db.readAllCategory(); // Retrieve all categories from the database

        if (cursor != null) {
            int idIndex = cursor.getColumnIndex("id");
            int nameIndex = cursor.getColumnIndex("name");
            int imageIndex = cursor.getColumnIndex("image");

            // Check if columns exist in the cursor
            if (idIndex != -1 && nameIndex != -1 && imageIndex != -1) {
                while (cursor.moveToNext()) {
                    // Extract data from cursor
                    int id = cursor.getInt(idIndex);
                    String name = cursor.getString(nameIndex);
                    String image = cursor.getString(imageIndex);

                    // Create Category object and add to list
                    categoryList.add(new Category(id, name, image));
                }
            } else {
                Toast.makeText(this, "Column names do not exist in the database!", Toast.LENGTH_SHORT).show();
            }
            cursor.close(); // Close the cursor after usage
        }

        // Set up the adapter for RecyclerView and pass in the context
        adapter = new CategoryManagementAdapter(this, categoryList);
        recyclerView.setAdapter(adapter);
    }

    // Method to set up the Floating Action Button (FAB) for adding new categories
    private void setupFabButton() {
        fabAddCategory.setOnClickListener(view -> {
            Intent intent = new Intent(CategoryManagementActivity.this, UpdateCategoryActivity.class);
            startActivity(intent);
        });
    }
}
