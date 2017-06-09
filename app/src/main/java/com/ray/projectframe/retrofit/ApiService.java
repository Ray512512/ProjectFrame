package com.ray.projectframe.retrofit;


import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by ray on 17-5-10
 * Description:
 */
public interface ApiService {


    @POST("")
    Observable<BaseModel<Object>> register(@Body Object registEntry);
}





