package com.example.gearapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gearapp.R;
import com.example.gearapp.model.Category;

import java.util.List;

public class CategoryManagementAdapter extends RecyclerView.Adapter<CategoryManagementAdapter.MyViewHolder> {

    private List<Category> categoryList;

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

        // Load the image using an image loading library like Glide or Picasso
        // Glide.with(holder.imageViewCategory.getContext()).load(category.getImage()).into(holder.imageViewCategory);

        // Set an OnClickListener for the category item
        holder.itemView.setOnClickListener(v -> {
            // Xử lý sự kiện nhấn vào category
            // Ví dụ: hiển thị ID hoặc thực hiện hành động nào đó dựa trên category ID
            int categoryId = category.getId();
            // Thực hiện hành động với categoryId
        });
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewCategoryId;
        TextView textViewCategoryName;
        ImageView imageViewCategory;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewCategoryId = itemView.findViewById(R.id.textViewCategoryId); // Ánh xạ ID Category
            textViewCategoryName = itemView.findViewById(R.id.textViewCategoryName);
            imageViewCategory = itemView.findViewById(R.id.imageViewCategory);
        }
    }

}
