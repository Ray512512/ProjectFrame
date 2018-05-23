package com.ray.library.retrofit;


import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ray on 2017/5/7.
 * email：1452011874@qq.com
 */
public class BaseApiManager {

    public static  String ROOT_URL = "";
    private static Retrofit retrofit;

    /**
     * 初始化api
     * @param apiServer
     * @param rootUrl
     * @param interceptors 选择添加拦截器
     * @param <T>
     * @return
     */
    public static <T> T create(Class<T> apiServer, String rootUrl, Interceptor...interceptors){
        if(retrofit==null) {
            ROOT_URL=rootUrl;
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient.Builder builder=new OkHttpClient.Builder();
            for (Interceptor interceptor : interceptors) {
                builder.addInterceptor(interceptor);
            }
            OkHttpClient okHttpClient = builder
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
