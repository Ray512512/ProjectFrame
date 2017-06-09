package com.ray.projectframe.mvp.presenter;

import android.app.Activity;

import com.ray.projectframe.base.mvp.BasePresenter;
import com.ray.projectframe.mvp.view.LoginIView;


/**
 * Created by ray on 17/5/16.
 */
public class LoginPresenter extends BasePresenter<LoginIView> {
    public LoginPresenter(Activity mContext, LoginIView mView) {
        super(mContext, mView);
    }

    //登录
    public void login(String phone) {


    }
}
