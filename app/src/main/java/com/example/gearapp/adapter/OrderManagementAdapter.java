package com.example.gearapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gearapp.activity.EditOrderStatusActivity;
import com.example.gearapp.model.Order;
import com.example.gearapp.R;

import java.util.List;

public class OrderManagementAdapter extends RecyclerView.Adapter<OrderManagementAdapter.OrderViewHolder> {

    private List<Order> orderList;
    private Context context;
    private OnOrderClickListener onOrderClickListener;

    // Interface for click handling
    public interface OnOrderClickListener {
        void onOrderClick(Order order);
    }

    // Constructor accepting click listener
    public OrderManagementAdapter(Context context, List<Order> orderList, OnOrderClickListener listener) {
        this.context = context;
        this.orderList = orderList;
        this.onOrderClickListener = listener;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_order_management, parent, false);
        return new OrderViewHolder(view, onOrderClickListener);
    }

    @Override

    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order order = orderList.get(position);
        holder.tvOrderId.setText("Order ID: " + order.getId());
        holder.tvTotalMoney.setText("Total Money: " + order.getTotalMoney());
        holder.tvStatus.setText("Status: " + order.getStatus());
        holder.tvDateOrder.setText("Date Order: " + order.getDateOrder());
        holder.tvUserName.setText("User: " + order.getUser().getName());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, EditOrderStatusActivity.class);
            intent.putExtra("orderId", order.getId());
            intent.putExtra("status", order.getStatus());
            context.startActivity(intent);
        });
    }


    @Override
    public int getItemCount() {
        return orderList.size();
    }

    // ViewHolder class with click handling
    public static class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView tvOrderId, tvTotalMoney, tvStatus, tvDateOrder, tvUserName;

        public OrderViewHolder(@NonNull View itemView, OnOrderClickListener listener) {
            super(itemView);
            tvOrderId = itemView.findViewById(R.id.tvOrderId);
            tvTotalMoney = itemView.findViewById(R.id.tvTotalMoney);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            tvDateOrder = itemView.findViewById(R.id.tvDateOrder);
            tvUserName = itemView.findViewById(R.id.tvUserName);

            // Set click listener for the entire item
            itemView.setOnClickListener(view -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION && listener != null) {
                    listener.onOrderClick((Order) view.getTag());
                }
            });
        }
    }
}
