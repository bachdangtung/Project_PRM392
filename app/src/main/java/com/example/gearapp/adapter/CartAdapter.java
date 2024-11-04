package com.example.gearapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.gearapp.R;
import com.example.gearapp.model.NewProduct;

import java.text.DecimalFormat;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    Context context;
    List<NewProduct> cartList;

    public CartAdapter(List<NewProduct> cartList, Context context) {
        this.cartList = cartList;
        this.context = context;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        NewProduct product = cartList.get(position);
        holder.productName.setText(product.getName());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.productPrice.setText("Price: " + decimalFormat.format(Double.parseDouble(product.getPrice())) + "Đ");

        Glide.with(context).load(product.getImage()).into(holder.productImage);

        // Delete button
        holder.btnDelete.setOnClickListener(v -> {
            cartList.remove(position);
            notifyItemRemoved(position);
            Toast.makeText(context, "Product removed from cart", Toast.LENGTH_SHORT).show();
        });

        // Payment button
        holder.btnPayment.setOnClickListener(v -> {
            // Implement payment logic here
            Toast.makeText(context, "Proceeding to payment", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        TextView productName, productPrice;
        ImageView productImage;
        Button btnPayment, btnDelete;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.cart_product_name);
            productPrice = itemView.findViewById(R.id.cart_product_price);
            productImage = itemView.findViewById(R.id.cart_product_image);
            btnPayment = itemView.findViewById(R.id.btn_payment);
            btnDelete = itemView.findViewById(R.id.btn_delete);
        }
    }
}
