package com.example.gearapp.activity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.gearapp.MyDatabaseHelper;
import com.example.gearapp.R;
import com.example.gearapp.adapter.CategoryAdapter;
import com.example.gearapp.adapter.NewProductAdapter;
import com.example.gearapp.model.Category;
import com.example.gearapp.model.NewProduct;
import com.example.gearapp.model.NewProductModel;
import com.example.gearapp.retrofit.APISelling;
import com.example.gearapp.retrofit.RetrofitClient;
import com.example.gearapp.utils.Utils;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    ViewFlipper viewFlipper;
    RecyclerView recyclerViewManHinhChinh;
    NavigationView navigationView;
    ListView listViewManHinhChinh;
    DrawerLayout drawerLayout;
    CategoryAdapter categoryAdapter;
    List<Category> mangloaisp;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    APISelling apiSelling;
    List<NewProduct> newarrayProduct;
    NewProductAdapter spAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apiSelling = RetrofitClient.getInstance(Utils.BASE_URL).create(APISelling.class);
        Anhxa();
        ActionBar();


        if (isConnected(this)) {
            Toast.makeText(getApplicationContext(), "ok", Toast.LENGTH_LONG).show();
            ActionViewFlipper();
            getCategory();
            getNewProduct();
        } else {
            Toast.makeText(getApplicationContext(), "no internet", Toast.LENGTH_LONG).show();
        }
    }

    private  void getNewProduct() {
        compositeDisposable.add(apiSelling.getNewProduct()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        newProductModel -> {
                            if(newProductModel.isSuccess()) {
                                newarrayProduct = newProductModel.getResult();
                                spAdapter = new NewProductAdapter(newarrayProduct, getApplicationContext());
                                recyclerViewManHinhChinh.setAdapter(spAdapter);

                            }
                        },
                        throwable -> {
                            Toast.makeText(getApplicationContext(), "can not connect server"+throwable.getMessage(), Toast.LENGTH_LONG).show();
                        }
                ));
    }

    /*private void getCategory() {
        compositeDisposable.add(apiSelling.getCategory()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        categoryModel -> {
                            if (categoryModel.isSuccess()) {
                                Toast.makeText(getApplicationContext(),
                                        categoryModel.getResult().get(0).getName(),
                                        Toast.LENGTH_LONG).show();
                            }
                        }
                ));
    }*/

    private void getCategory() {
        compositeDisposable.add(apiSelling.getCategory()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        categoryModel -> {
                            if (categoryModel.isSuccess()) {
                                mangloaisp.clear();
                                mangloaisp.addAll(categoryModel.getResult());
                                categoryAdapter.notifyDataSetChanged();
                            } else {
                                Toast.makeText(getApplicationContext(),
                                        "Failed to retrieve categories",
                                        Toast.LENGTH_LONG).show();
                            }
                        },
                        throwable -> {
                            Toast.makeText(getApplicationContext(),
                                    "Error: " + throwable.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                ));
    }

    private void ActionViewFlipper(){
        List<String> mangquangcao = new ArrayList<>();
        mangquangcao.add("https://mauweb.monamedia.net/thegioididong/wp-content/uploads/2017/12/banner-Le-hoi-phu-kien-800-300.png");
        mangquangcao.add("https://mauweb.monamedia.net/thegioididong/wp-content/uploads/2017/12/banner-HC-Tra-Gop-800-300.png");
        mangquangcao.add("https://mauweb.monamedia.net/thegioididong/wp-content/uploads/2017/12/banner-big-ky-nguyen-800-300.jpg");
        for (int i = 0; i<mangquangcao.size(); i++) {
            ImageView imageView = new ImageView(getApplicationContext());
            Glide.with(getApplicationContext()).load(mangquangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);
        Animation slide_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
        Animation slide_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_right);
        viewFlipper.setInAnimation(slide_in);
        viewFlipper.setOutAnimation(slide_out);
    }

    private void ActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener()
                                             {
                                                 @Override
                                                 public void onClick(View view) {
                                                     drawerLayout.openDrawer(GravityCompat.START);
                                                 }
                                             }
        );
    }

    private void Anhxa() {
        toolbar = findViewById(R.id.toolbarmanhinhchinh);
        viewFlipper = findViewById(R.id.viewflipper);
        recyclerViewManHinhChinh = findViewById(R.id.recycleview);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerViewManHinhChinh.setLayoutManager(layoutManager);
        recyclerViewManHinhChinh.setHasFixedSize(true);
        listViewManHinhChinh = findViewById(R.id.listviewmanhinhchinh);
        navigationView = findViewById(R.id.navigationview);
        drawerLayout = findViewById(R.id.drawerlayout);
        //khoi tao list
        mangloaisp = new ArrayList<>();
        newarrayProduct = new ArrayList<>();
        //khoi tao adapter
        categoryAdapter = new CategoryAdapter(getApplicationContext(),mangloaisp);
        listViewManHinhChinh.setAdapter(categoryAdapter);
    }
    private boolean isConnected (Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI); //nho them quyen vao khong bi loi
        NetworkInfo mobile = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if ((wifi != null && wifi.isConnected()) || (mobile != null && mobile.isConnected()) ) {
            return true;
        } else {
            return false;
        }
    }




}