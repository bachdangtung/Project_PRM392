package com.example.gearapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import com.example.gearapp.R;
import com.google.android.material.navigation.NavigationView;

public class AdminDashboardActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        // Khởi tạo Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Khởi tạo DrawerLayout và NavigationView
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);

        // Thiết lập ActionBarDrawerToggle
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Thiết lập sự kiện chọn item trong NavigationView
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.nav_category_management) {
                    startActivity(new Intent(AdminDashboardActivity.this, CategoryManagementActivity.class));
                } else if (id == R.id.nav_product_management) {
                    startActivity(new Intent(AdminDashboardActivity.this, ProductManagementActivity.class));
                } else if (id == R.id.nav_order_management) {
                    startActivity(new Intent(AdminDashboardActivity.this, OrderManagementActivity.class));
                } else if (id == R.id.nav_statistics) {
                    startActivity(new Intent(AdminDashboardActivity.this, StatisticsActivity.class));
                } else if (id == R.id.nav_logout) {
                    startActivity(new Intent(AdminDashboardActivity.this, LoginActivity.class));
                }

                // Đóng drawer sau khi chọn item
                drawerLayout.closeDrawers();
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu); // Inflate menu_main.xml vào Toolbar
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Xử lý sự kiện click cho các item trong Toolbar (nếu cần)
        return super.onOptionsItemSelected(item);
    }
}
