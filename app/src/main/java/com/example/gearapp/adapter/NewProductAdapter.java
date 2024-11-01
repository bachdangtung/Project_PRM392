package com.example.gearapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.gearapp.R;
import com.example.gearapp.model.NewProduct;

import java.text.DecimalFormat;
import java.util.List;

public class NewProductAdapter extends RecyclerView.Adapter<NewProductAdapter.MyViewHolder> {
    Context context;
    List<NewProduct> array;

    public NewProductAdapter(List<NewProduct> array, Context context) {
        this.array = array;
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
        NewProduct newProduct = array.get(position);
        holder.txtname.setText(newProduct.getName());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtprice.setText("Price: "+decimalFormat.format(Double.parseDouble(newProduct.getPrice()))+"Đ");
        //holder.txtprice.setText(newProduct.getPrice()+ "");
        Glide.with(context).load(newProduct.getImage()).into(holder.imgimage);
    }

    @Override
    public int getItemCount() {
        return array.size();
    }


    public class MyViewHolder extends  RecyclerView.ViewHolder{
        TextView txtprice, txtname;
        ImageView imgimage;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtprice = itemView.findViewById(R.id.itemproduct_price);
            txtname = itemView.findViewById(R.id.itemproduct_name);
            imgimage = itemView.findViewById(R.id.itemproduct_image);
        }
    }
}
