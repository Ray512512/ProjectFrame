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
public class DemoApiManager{

    public static  String ROOT_URL = "";
    private static Retrofit retrofit;

    /**
     * 放到application中初始化
     * @param apiServer
     * @param rootUrl
     */
    public static <T> T init(Class<T> apiServer, String rootUrl){
        if(retrofit==null) {
            ROOT_URL=rootUrl;
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .readTimeout(30, TimeUnit.SECONDS)
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .addInterceptor(logging)
                    .build();

            retrofit=new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(rootUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofit.create(apiServer);
    }

    public static  <T> T get(Class<T> apiServer) {
       if(retrofit==null) throw new NullPointerException("请先初始化");
        return retrofit.create(apiServer);
    }
}
