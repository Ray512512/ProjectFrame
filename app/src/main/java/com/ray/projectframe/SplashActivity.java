package com.ray.projectframe;

import android.os.Handler;

import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;
import com.ray.library.base.ui.CheckPermissionsActivity;
import com.ray.library.common.TestActivity;
import com.ray.library.rxbus.Event;

public class SplashActivity extends CheckPermissionsActivity {
    /**
     * 获取需要检测的权限列表，返回null 直接回调startApp()
     * @return
     */
    @Override
    protected String[] getPermissions() {
        return null;
    }

    @Override
    protected void startApp() {
        new Handler().postDelayed(() -> {
            MainActivity.start(this);
            finish();
        }, 2000);
    }

    @Override
    protected int inflateContentView() {
        return R.layout.activity_splash;
    }

}
