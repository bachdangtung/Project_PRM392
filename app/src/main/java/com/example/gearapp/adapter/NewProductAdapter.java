package com.example.gearapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.gearapp.R;
import com.example.gearapp.activity.CartActivity;
import com.example.gearapp.activity.ProductDetailActivity;
import com.example.gearapp.model.NewProduct;

import java.text.DecimalFormat;
import java.util.List;

public class NewProductAdapter extends RecyclerView.Adapter<NewProductAdapter.MyViewHolder> {
    private AppCompatActivity context;
    List<NewProduct> productList;

    public NewProductAdapter(List<NewProduct> productList, AppCompatActivity context) {
        this.productList = productList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_new, parent, false);
        return new MyViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        NewProduct product = productList.get(position);
        holder.txtName.setText(product.getName());

        // Format price as a currency
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtPrice.setText("Price: " + decimalFormat.format(Double.parseDouble(product.getPrice())) + "Đ");

        // Load image using Glide
        Glide.with(context).load(product.getImage()).into(holder.imgImage);

        // Set click listener for the "View Details" button
        holder.btnViewProductDetail.setOnClickListener(v -> {
            Intent intent = new Intent(context, ProductDetailActivity.class);
            intent.putExtra("productName", product.getName());
            intent.putExtra("productPrice", product.getPrice());
            intent.putExtra("productImage", product.getImage());
            intent.putExtra("productDescription", product.getDescription());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtPrice, txtName;
        ImageView imgImage;
        Button btnViewProductDetail;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtPrice = itemView.findViewById(R.id.itemproduct_price);
            txtName = itemView.findViewById(R.id.itemproduct_name);
            imgImage = itemView.findViewById(R.id.itemproduct_image);
            btnViewProductDetail = itemView.findViewById(R.id.btn_view_product_detail);
        }
    }
}
