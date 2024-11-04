package com.example.gearapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.gearapp.R;
import com.example.gearapp.model.Product;
import com.example.gearapp.model.CartManager;

public class CartActivity extends AppCompatActivity {
    Toolbar toolbarCart;
    LinearLayout cartItemsLayout;
    TextView txtTotalPrice;
    Button btnCheckout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        toolbarCart = findViewById(R.id.toolbarCart);
        cartItemsLayout = findViewById(R.id.cartItemsLayout);
        txtTotalPrice = findViewById(R.id.txtTotalPrice);
        btnCheckout = findViewById(R.id.btnCheckout);

        updateCartUI();

        btnCheckout.setOnClickListener(v -> {
            double totalPrice = calculateTotalPrice(); // Calculate the total price
            Intent intent = new Intent(CartActivity.this, ThanhToanActivity.class);
            intent.putExtra("TOTAL_PRICE", totalPrice); // Pass total price to ThanhToanActivity
            startActivity(intent); // Start ThanhToanActivity
        });
    }

    private double calculateTotalPrice() {
        double totalPrice = 0;
        for (Product product : CartManager.getInstance().getProducts()) {
            totalPrice += product.getPrice();
        }
        return totalPrice;
    }

    private void updateCartUI() {
        cartItemsLayout.removeAllViews();

        for (Product product : CartManager.getInstance().getProducts()) {
            int quantity = CartManager.getInstance().getProductQuantity(product); // Get product quantity

            View productView = LayoutInflater.from(this).inflate(R.layout.product_cart_item, null);
            ImageView imgCartProduct = productView.findViewById(R.id.imgCartProduct);
            TextView txtCartProductName = productView.findViewById(R.id.txtCartProductName);
            TextView txtCartProductPrice = productView.findViewById(R.id.txtCartProductPrice);
            Button btnDeleteProduct = productView.findViewById(R.id.btnDeleteProduct);

            txtCartProductName.setText(product.getName());

            double totalPriceForItem = product.getPrice();
            txtCartProductPrice.setText(String.format("Price: $%.2f", totalPriceForItem));

            Glide.with(this).load(product.getImage()).into(imgCartProduct);

            btnDeleteProduct.setOnClickListener(v -> {
                CartManager.getInstance().removeProduct(product);
                updateCartUI();
                Toast.makeText(CartActivity.this, "Sản phẩm đã được xóa", Toast.LENGTH_SHORT).show();
            });

            cartItemsLayout.addView(productView);
        }

        txtTotalPrice.setText(String.format("Total: $%.2f", calculateTotalPrice()));
    }


}
