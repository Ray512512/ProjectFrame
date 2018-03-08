package com.ray.library.retrofit;


import com.ray.library.bean.BaseModel;
import com.ray.library.bean.User;

import io.reactivex.Observable;
import retrofit2.http.POST;

/**
 * Created by ray on 17-5-10
 * Description:
 */
public interface ApiService {


    @POST("backstage/adsite/registerWifi.html")
    Observable<BaseModel<User>> registerAndLogin();

}





