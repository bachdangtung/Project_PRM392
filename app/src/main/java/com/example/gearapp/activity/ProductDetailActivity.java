package com.example.gearapp.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.gearapp.MyDatabaseHelper;
import com.example.gearapp.R;
import com.example.gearapp.model.Product;

public class ProductDetailActivity extends AppCompatActivity {

    private TextView productName, price, description;
    private Button btnAddtoCart;
    private ImageView imgProductDetail;
    private Spinner spinner;
    private Toolbar toolbar;
    private MyDatabaseHelper myDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        productName = findViewById(R.id.txtproductname);
        price = findViewById(R.id.txtprice);
        description = findViewById(R.id.txtproductdescription);
        btnAddtoCart = findViewById(R.id.btnaddtocart);
        spinner = findViewById(R.id.spinner);
        imgProductDetail = findViewById(R.id.imgproductdetail);
        toolbar = findViewById(R.id.toolbarProductDetail);

        int productId = getIntent().getIntExtra("productId", -1);

        myDatabaseHelper = new MyDatabaseHelper(this);
        Product product = myDatabaseHelper.getProductById(productId);
        if (product != null) {
            productName.setText(product.getName());
            price.setText(String.valueOf(product.getPrice()));

        }
    }
}






