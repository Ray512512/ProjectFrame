package com.ray.projectframe;

import android.os.Handler;

import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;
import com.ray.library.base.ui.CheckPermissionsActivity;
import com.ray.library.common.TestActivity;
import com.ray.library.rxbus.Event;

public class SplashActivity extends CheckPermissionsActivity {


    @Override
    protected int inflateContentView() {
        return R.layout.activity_splash;
    }


    @Subscribe(tags = {@Tag(Event.TAG.START_APP)})
    public void start(String  s) {
//        if(s.equals("0")) SPUtils.put(Constant.SP_KEY.IS_FIRST_LAUNCH,false,SPUtils.getCommonSp());
        new Handler().postDelayed(() -> {
            MainActivity.start(this);
            finish();
        }, Integer.parseInt(s));
    }
}
