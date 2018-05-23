package com.ray.projectframe.demo;

import android.os.Bundle;

import com.ray.library.base.ui.BaseActivity;
import com.ray.projectframe.R;
import com.ray.projectframe.mvp.presenter.DemoPresenter;
import com.ray.projectframe.mvp.view.DemoIView;

import butterknife.ButterKnife;

public class DemoActivity extends BaseActivity<DemoPresenter> implements DemoIView {

    @Override
    protected int inflateContentView() {
        return R.layout.activity_demo;
    }

    @Override
    protected void initPresenter() {
       mPresenter=new DemoPresenter(this,this);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        ButterKnife.bind(this);
    }

    @Override
    protected void initEvents() {

    }

    @Override
    public void onLoginSuccess() {

    }
}
