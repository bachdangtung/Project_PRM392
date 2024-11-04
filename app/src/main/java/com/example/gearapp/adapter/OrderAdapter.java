package com.example.gearapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gearapp.R;
import com.example.gearapp.model.Order;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {
    private ArrayList<Order> orderList;
    private OnOrderClickListener onOrderClickListener;

    public OrderAdapter(ArrayList<Order> orderList, OnOrderClickListener listener) {
        this.orderList = orderList;
        this.onOrderClickListener = listener;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order order = orderList.get(position);
        holder.textView.setText("Order ID: " + order.getId() +
                "\nTotal Money: " + order.getTotalMoney() +
                "\nStatus: " + order.getStatus() +
                "\nDate Order: " + order.getDateOrder());

        holder.btnDetails.setOnClickListener(v -> onOrderClickListener.onOrderClick(order.getId()));
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {
        Button btnDetails;
        TextView textView;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textViewOrderDetails);
            btnDetails = itemView.findViewById(R.id.btnDetails);
        }
    }

    public interface OnOrderClickListener {
        void onOrderClick(int orderId);
    }
}
