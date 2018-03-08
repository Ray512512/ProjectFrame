package com.ray.projectframe.mvp.presenter;

import android.app.Activity;
import android.content.Context;

import com.ray.library.base.mvp.BasePresenter;
import com.ray.library.bean.User;
import com.ray.library.rxjava.RxHelper;
import com.ray.library.rxjava.RxSubscribe;
import com.ray.library.rxjava.util.RxManager;
import com.ray.library.utils.SystemUtil;
import com.ray.projectframe.mvp.view.LoginIView;

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
        /**
         * 普通请求
         */
        mApiService.registerAndLogin().compose(RxHelper.handleResult()).
                subscribe(new RxSubscribe<Object>(mContext,mView) {
                    @Override
                    public void _onNext(Object user) {
                    }

                    @Override
                    public void _onError(String message) {

                    }

                });

        /**
         * 遇到异常重试请求
         */
        mApiService.registerAndLogin().retryWhen(RxHelper.retryWhen()).compose(RxHelper.handleResult()).
                subscribe(new RxSubscribe<Object>(mContext,mView) {
                    @Override
                    public void _onNext(Object user) {
                    }

                    @Override
                    public void _onError(String message) {

                    }
                });


        /**
         * 轮询请求
         */
        RxManager.interval(10, time -> mApiService.registerAndLogin().compose(RxHelper.handleResult()).
                subscribe(new RxSubscribe<Object>(mContext,mView) {
                    @Override
                    public void _onNext(Object user) {
                    }

                    @Override
                    public void _onError(String message) {

                    }
                }));

        /**
         * 依次执行两个请求
         */
        mApiService.registerAndLogin().compose(RxHelper.handleResult()).doOnNext(o -> {
             //第一次请求成功
        }).flatMap(user -> mApiService.registerAndLogin()).compose(RxHelper.handleResult()).
                subscribe(new RxSubscribe<User>(mContext,mView) {
                    @Override
                    public void _onNext(User user) {
                        //第二次请求成功
                    }

                    @Override
                    public void _onError(String message) {

                    }
                });

    }




}
