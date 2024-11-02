package com.example.gearapp.retrofit;

//import android.database.Observable;

import com.example.gearapp.model.CategoryModel;
import com.example.gearapp.model.NewProductModel;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

import io.reactivex.rxjava3.core.Single;

public interface APISelling {
    @GET("api/categories")
    Observable<CategoryModel> getCategory();

    @GET("api/getnewproduct")
    Observable<NewProductModel> getNewProduct();
}
