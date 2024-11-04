package com.example.gearapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.gearapp.R;
import com.example.gearapp.model.Product;

import java.util.List;

public class ProductAdapter extends BaseAdapter {

    List<Product> array;
    Context context;

    public ProductAdapter(List<Product> array, Context context) {
        this.array = array;
        this.context = context;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


    public class ViewHolder{
        TextView text_nameproduct;
        ImageView imgimage;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder =  null;
        if (view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.item_sanpham, null);
            viewHolder.text_nameproduct = view.findViewById(R.id.item_nameproduct);
            viewHolder.imgimage = view.findViewById(R.id.item_image);
            view.setTag(viewHolder);
        }else  {
            viewHolder = (ViewHolder) view.getTag();
            viewHolder.text_nameproduct.setText(array.get(i).getName());
            Glide.with(context).load(array.get(i).getImage()).into(viewHolder.imgimage);
        }
        return view;
    }
}
