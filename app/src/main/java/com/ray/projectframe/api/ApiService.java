package com.ray.projectframe.api;


import com.ray.library.bean.BaseModel;
import com.ray.library.bean.Demo;
import com.ray.library.bean.DemoUser;
import com.ray.library.retrofit.BaseApiService;

import io.reactivex.Observable;
import retrofit2.http.POST;

/**
 * Created by ray on 17-5-10
 * Description:
 */
public interface ApiService extends BaseApiService {


    @POST("backstage/adsite/registerWifi.html")
    Observable<BaseModel<DemoUser>> register();

    @POST("backstage/adsite/registerWifi.html")
    Observable<BaseModel<Demo>> registerAndLogin2();
}





