package com.ray.projectframe.retrofit;


import com.ray.projectframe.mvp.presenter.DemoBean;
import com.ray.projectframe.mvp.presenter.DemoEntry;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by ray on 17-5-10
 * Description:
 */
public interface ApiService {


    @POST("backstage/adsite/registerWifi.html")
    Observable<BaseModel<DemoBean>> registerAndLogin(@Body DemoEntry registEntry);

}





