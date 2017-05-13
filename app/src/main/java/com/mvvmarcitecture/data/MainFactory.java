package com.mvvmarcitecture.data;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.mvvmarcitecture.utils.AppConstant;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rajesh on 12/5/17.
 */

public class MainFactory {

    public static MainServices create() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(AppConstant.HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(MainServices.class);
    }
}
