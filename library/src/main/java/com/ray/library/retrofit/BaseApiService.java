package com.ray.library.retrofit;


import com.ray.library.bean.BaseModel;
import com.ray.library.bean.Demo;
import com.ray.library.bean.DemoUser;

import io.reactivex.Observable;
import retrofit2.http.POST;

/**
 * Created by ray on 17-5-10
 * Description:
 */
public interface BaseApiService {


    @POST("backstage/adsite/registerWifi.do")
    Observable<BaseModel<DemoUser>> register();

}





