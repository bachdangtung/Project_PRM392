package com.example.gearapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.gearapp.model.Order;
import com.example.gearapp.R;
import java.util.List;

public class OrderManagementAdapter extends RecyclerView.Adapter<OrderManagementAdapter.OrderViewHolder> {

    private List<Order> orderList;
    private Context context;
    public OrderManagementAdapter(Context context, List<Order> orderList) {
        this.context = context;
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_order_management, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order order = orderList.get(position);
        holder.tvOrderId.setText("Order ID: " + order.getId());
        holder.tvTotalMoney.setText("Total Money: " + order.getTotalMoney());
        holder.tvStatus.setText("Status: " + order.getStatus());
        holder.tvDateOrder.setText("Date Order: " + order.getDateOrder());
        holder.tvUserName.setText("User: " + order.getUser().getName());
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView tvOrderId, tvTotalMoney, tvStatus, tvDateOrder, tvUserName;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            tvOrderId = itemView.findViewById(R.id.tvOrderId);
            tvTotalMoney = itemView.findViewById(R.id.tvTotalMoney);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            tvDateOrder = itemView.findViewById(R.id.tvDateOrder);
            tvUserName = itemView.findViewById(R.id.tvUserName);
        }
    }
}
