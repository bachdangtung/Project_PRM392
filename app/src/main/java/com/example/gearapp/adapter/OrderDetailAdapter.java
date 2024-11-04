package com.example.gearapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.gearapp.R;
import com.example.gearapp.model.OrderDetail;

import java.util.ArrayList;

public class OrderDetailAdapter extends RecyclerView.Adapter<OrderDetailAdapter.OrderDetailViewHolder> {
    private ArrayList<OrderDetail> orderDetailList;

    public OrderDetailAdapter(ArrayList<OrderDetail> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }

    @NonNull
    @Override
    public OrderDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_detail, parent, false);
        return new OrderDetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderDetailViewHolder holder, int position) {
        OrderDetail orderDetail = orderDetailList.get(position);
        holder.tvProductName.setText("Product Name: " + orderDetail.getProduct().getName());
        holder.tvQuantity.setText("Quantity: " + orderDetail.getQuantity());
        holder.tvPrice.setText("Price: " + orderDetail.getPrice());

        // Sử dụng Glide để tải ảnh từ URL
        Glide.with(holder.itemView.getContext())
                .load(orderDetail.getProduct().getImage()) // URL của ảnh
                .into(holder.tvProductImage); // ImageView để hiển thị ảnh
        holder.tvProductImage.getLayoutParams().width = 500; // đặt chiều rộng
        holder.tvProductImage.getLayoutParams().height = 500; // đặt chiều cao
        holder.tvProductImage.requestLayout(); // yêu cầu để cập nhật giao diện
    }

    @Override
    public int getItemCount() {
        return orderDetailList.size();
    }

    public static class OrderDetailViewHolder extends RecyclerView.ViewHolder {
        TextView tvProductName ,tvQuantity, tvPrice;
        ImageView tvProductImage;

        public OrderDetailViewHolder(@NonNull View itemView) {
            super(itemView);
            tvProductName = itemView.findViewById(R.id.tvProductName);
            tvProductImage = itemView.findViewById(R.id.tvProductImage);
            tvQuantity = itemView.findViewById(R.id.tvQuantity);
            tvPrice = itemView.findViewById(R.id.tvPrice);

        }
    }
}
