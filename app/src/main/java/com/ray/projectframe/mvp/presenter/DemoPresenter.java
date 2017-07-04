package com.ray.projectframe.mvp.presenter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.ray.projectframe.base.mvp.BasePresenter;
import com.ray.projectframe.mvp.view.LoginIView;
import com.ray.projectframe.rxjava.RxHelper;
import com.ray.projectframe.rxjava.RxSubscribe;
import com.ray.projectframe.utils.SystemUtil;

import static com.ray.projectframe.mvp.presenter.DemoEntry.REG_LOGIN;


/**
 * Created by ray on 17/5/16.
 */
public class DemoPresenter extends BasePresenter<LoginIView> {
    private static final String TAG = "LoginPresenter";

    public DemoPresenter(Activity mContext, LoginIView mView) {
        super(mContext, mView);
    }

    //登录
    public void login(Context c,String phone) {
        DemoEntry registEntry=new DemoEntry(REG_LOGIN);
        registEntry.setMobile(phone);
        registEntry.setPassword("123456");
        registEntry.setExt0(SystemUtil.getDeviceId(c));

        mApiService.registerAndLogin(registEntry).compose(RxHelper.handleResult()).
                subscribe(new RxSubscribe<DemoBean>(mContext,mView) {
                    @Override
                    public void _onNext(DemoBean user) {
                        Log.v(TAG,user.getMobile());
                    }

                    @Override
                    public void _onError(String message) {

                    }

                });

    }




}
