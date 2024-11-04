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
import com.example.gearapp.model.Category;

import java.util.List;

public class CategoryManagementAdapter extends RecyclerView.Adapter<CategoryManagementAdapter.MyViewHolder> {

    private final List<Category> categoryList;

    public CategoryManagementAdapter(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_category_management, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Category category = categoryList.get(position);
        holder.textViewCategoryId.setText(String.valueOf(category.getId())); // Thiết lập ID của danh mục
        holder.textViewCategoryName.setText(category.getName());

        // Sử dụng Glide để tải ảnh từ URL vào ImageView
        Glide.with(holder.imageViewCategory.getContext())
                .load(category.getImage()) // Đường dẫn URL của hình ảnh
                .placeholder(R.drawable.baseline_image_24) // Hình ảnh mặc định khi tải
                .error(R.drawable.broken_image_24)
                .into(holder.imageViewCategory);


        holder.itemView.setOnClickListener(v -> {
            int categoryId = category.getId();

        });
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewCategoryId;
        TextView textViewCategoryName;
        ImageView imageViewCategory;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewCategoryId = itemView.findViewById(R.id.textViewCategoryId);
            textViewCategoryName = itemView.findViewById(R.id.textViewCategoryName);
            imageViewCategory = itemView.findViewById(R.id.imageViewCategory);
        }
    }
}
