package com.example.gearapp.activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.gearapp.MyDatabaseHelper;
import com.example.gearapp.R;
import com.example.gearapp.adapter.CategoryAdapter;
import com.example.gearapp.adapter.NewProductAdapter;
import com.example.gearapp.adapter.ProductAdapter;
import com.example.gearapp.model.Category;
import com.example.gearapp.model.NewProduct;
import com.example.gearapp.model.Product;
import com.google.android.material.navigation.NavigationView;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    ViewFlipper viewFlipper;
    RecyclerView recyclerViewManHinhChinh;
    NavigationView navigationView;
    ListView listViewManHinhChinh;
    DrawerLayout drawerLayout;
    CategoryAdapter categoryAdapter;
    List<Category> mangloaisp;
    List<NewProduct> newarrayProduct;
    NewProductAdapter spAdapter;
    MyDatabaseHelper myDatabaseHelper;
    private ImageView profileImageView; // Tham chiếu đến ImageView
    private TextView profileTextView; // Tham chiếu đến TextView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AppCompatActivity) this).setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        final String email = intent.getStringExtra("email");
        final String phone = intent.getStringExtra("phone");

        profileImageView = findViewById(R.id.profileImageView); // Lấy ID của ImageView
        profileTextView = findViewById(R.id.userNameTextView); // Lấy ID của TextView
        myDatabaseHelper = new MyDatabaseHelper(this);
        Anhxa();
        ActionBar();
        loadDataFromDatabase();
        List<Product> dashboardProducts = getDashboardProducts();
        ProductAdapter productAdapter = new ProductAdapter(dashboardProducts, this);
        listViewManHinhChinh.setAdapter(productAdapter);
        if (isConnected(this)) {
            Toast.makeText(getApplicationContext(), "Connected to internet", Toast.LENGTH_LONG).show();
            ActionViewFlipper();
            getEventClick();
        } else {
            Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_LONG).show();
        }

        // Thiết lập sự kiện nhấn cho hình ảnh hồ sơ
        profileImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển đến UserProfileActivity
                Intent intent = new Intent(MainActivity.this, UserProfileActivity.class);
                String email = getIntent().getStringExtra("email"); // Nhận email từ Intent
                String username = getIntent().getStringExtra("username");
                String phone = getIntent().getStringExtra("phone");
                intent.putExtra("email", email);
                intent.putExtra("username", username);
                intent.putExtra("phone", phone);
                startActivity(intent);
            }
        });

        // Nhận username từ Intent
        String username = intent.getStringExtra("username");

        if (username != null) {
            profileTextView.setText(username); // Hiển thị tên người dùng
        }
    }

    private void getEventClick() {
        listViewManHinhChinh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Intent Home = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(Home);
                        break;
                    case 1:
                        //Intent Profile = new Intent(getApplicationContext(), ProfileActivity.class);
                        //startActivity(Profile);
                        break;
                    case 2:
                        Intent Cart = new Intent(getApplicationContext(), CartActivity.class);
                        startActivity(Cart);
                        break;
                    case 3:
                        Intent Logout = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(Logout);
                        break;
                }
            }
        });
    }

    private void loadDataFromDatabase() {
        newarrayProduct = getAllProducts();
        if (!newarrayProduct.isEmpty()) {
            spAdapter = new NewProductAdapter(newarrayProduct, this);
            recyclerViewManHinhChinh.setAdapter(spAdapter);
        } else {
            Toast.makeText(this, "No products found", Toast.LENGTH_SHORT).show();
        }
        Log.d("ProductCount", "Number of products: " + newarrayProduct.size());
    }

    public List<NewProduct> getAllProducts() {
        List<NewProduct> productList = new ArrayList<>();
        SQLiteDatabase db = myDatabaseHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM product", null);

        Log.d("Database", "Total Rows: " + cursor.getCount());

        if (cursor.moveToFirst()) {
            do {
                NewProduct product = new NewProduct();

                int idIndex = cursor.getColumnIndex("id");
                int nameIndex = cursor.getColumnIndex("name");
                int imageIndex = cursor.getColumnIndex("image");
                int priceIndex = cursor.getColumnIndex("price");
                int descriptionIndex = cursor.getColumnIndex("description");
                int categoryIdIndex = cursor.getColumnIndex("category_id");

                if (idIndex != -1) product.setId(cursor.getInt(idIndex));
                if (nameIndex != -1) product.setName(cursor.getString(nameIndex));
                if (imageIndex != -1) product.setImage(cursor.getString(imageIndex));
                if (priceIndex != -1) product.setPrice(cursor.getString(priceIndex));
                if (descriptionIndex != -1) product.setDescription(cursor.getString(descriptionIndex));
                if (categoryIdIndex != -1) product.setCategory_id(cursor.getInt(categoryIdIndex));

                productList.add(product);
            } while (cursor.moveToNext());
        } else {
            Log.d("Database", "No products found in the database.");
        }

        cursor.close();
        db.close();
        return productList;
    }

    private void ActionViewFlipper() {
        List<String> mangquangcao = new ArrayList<>();
        mangquangcao.add("https://mauweb.monamedia.net/thegioididong/wp-content/uploads/2017/12/banner-Le-hoi-phu-kien-800-300.png");
        mangquangcao.add("https://mauweb.monamedia.net/thegioididong/wp-content/uploads/2017/12/banner-HC-Tra-Gop-800-300.png");
        mangquangcao.add("https://mauweb.monamedia.net/thegioididong/wp-content/uploads/2017/12/banner-big-ky-nguyen-800-300.jpg");

        for (String url : mangquangcao) {
            ImageView imageView = new ImageView(getApplicationContext());
            Glide.with(getApplicationContext()).load(url).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);
        Animation slide_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
        Animation slide_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_right);
        viewFlipper.setInAnimation(slide_in);
        viewFlipper.setOutAnimation(slide_out);
    }

    private void ActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(view -> drawerLayout.openDrawer(GravityCompat.START));
    }

    private void Anhxa() {
        toolbar = findViewById(R.id.toolbarmanhinhchinh);
        viewFlipper = findViewById(R.id.viewflipper);
        recyclerViewManHinhChinh = findViewById(R.id.recycleview);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerViewManHinhChinh.setLayoutManager(layoutManager);
        recyclerViewManHinhChinh.setHasFixedSize(true);
        listViewManHinhChinh = findViewById(R.id.listviewmanhinhchinh);
        navigationView = findViewById(R.id.navigationview);
        drawerLayout = findViewById(R.id.drawerlayout);

        // Initialize lists and adapters
        mangloaisp = new ArrayList<>();
        newarrayProduct = new ArrayList<>();
        categoryAdapter = new CategoryAdapter(getApplicationContext(), mangloaisp);
        listViewManHinhChinh.setAdapter(categoryAdapter);
    }

    private void getNewProduct() {
        newarrayProduct = getAllProducts(); // Use your existing getAllProducts method

        if (!newarrayProduct.isEmpty()) {
            spAdapter = new NewProductAdapter(newarrayProduct, this);
            recyclerViewManHinhChinh.setAdapter(spAdapter);
        } else {
            Toast.makeText(getApplicationContext(), "No new products found", Toast.LENGTH_SHORT).show();
        }

        Log.d("NewProductCount", "Number of new products: " + newarrayProduct.size());
    }


    private void getCategory() {
        mangloaisp = getAllCategories(); // Fetch categories directly in MainActivity

        if (!mangloaisp.isEmpty()) {
            categoryAdapter.notifyDataSetChanged();
        } else {
            Toast.makeText(getApplicationContext(), "No categories found", Toast.LENGTH_LONG).show();
        }

        Log.d("CategoryCount", "Number of categories: " + mangloaisp.size());
    }

    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        SQLiteDatabase db = myDatabaseHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM category", null);

        if (cursor.moveToFirst()) {
            do {
                Category category = new Category();
                int idIndex = cursor.getColumnIndex("id");
                int nameIndex = cursor.getColumnIndex("name");
                int imageIndex = cursor.getColumnIndex("image");

                if (idIndex != -1) category.setId(cursor.getInt(idIndex));
                if (nameIndex != -1) category.setName(cursor.getString(nameIndex));
                if (imageIndex != -1) category.setImage(cursor.getString(imageIndex));

                categories.add(category);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return categories;
    }

    public List<Product> getDashboardProducts() {
        List<Product> productList = new ArrayList<>();
        SQLiteDatabase db = myDatabaseHelper.getReadableDatabase();

        // Assuming the table "dashboard" has columns "id", "name", "image", and "price"
        Cursor cursor = db.rawQuery("SELECT * FROM dashboard", null);

        if (cursor.moveToFirst()) {
            do {
                Product product = new Product();
                int idIndex = cursor.getColumnIndex("id");
                int nameIndex = cursor.getColumnIndex("name");
                int imageIndex = cursor.getColumnIndex("image");


                if (idIndex != -1) product.setId(cursor.getInt(idIndex));
                if (nameIndex != -1) product.setName(cursor.getString(nameIndex));
                if (imageIndex != -1) product.setImage(cursor.getString(imageIndex));

                productList.add(product);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return productList;
    }



    private boolean isConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobile = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        return (wifi != null && wifi.isConnected()) || (mobile != null && mobile.isConnected());
    }
}
