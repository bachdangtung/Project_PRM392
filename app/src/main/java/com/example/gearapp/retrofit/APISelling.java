package com.example.gearapp.retrofit;

//import android.database.Observable;

import com.example.gearapp.model.CategoryModel;

import retrofit2.http.GET;

import io.reactivex.rxjava3.core.Single;

public interface APISelling {
    @GET("api/categories")
    Single<CategoryModel> getCategory();
}
