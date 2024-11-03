package com.example.gearapp.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gearapp.activity.UserOrderActivity;
import com.example.gearapp.model.Order;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter {
    public OrderAdapter(UserOrderActivity userOrderActivity, ArrayList<Order> orderList) {
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
