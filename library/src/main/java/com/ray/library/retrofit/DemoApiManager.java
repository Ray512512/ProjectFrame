package com.ray.library.retrofit;


import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ray on 2017/5/7.
 * email：1452011874@qq.com
 */
public class DemoApiManager {
    public static final String ROOT_URL = "http://wififan.zhikenet.com/";
    private static DemoApiService mApiService;

    public static DemoApiService getInstance() {
        if (mApiService == null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .readTimeout(30, TimeUnit.SECONDS)
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .addInterceptor(logging)
                    .build();
            mApiService = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(ROOT_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
                    .create(DemoApiService.class);
        }
        return mApiService;
    }

}
