package com.ray.projectframe.base.ui;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import butterknife.ButterKnife;
import frame.project.ray.projectframe.R;
import com.ray.projectframe.base.mvp.BaseIView;
import com.ray.projectframe.base.mvp.BasePresenter;
import com.ray.projectframe.common.ConstantField;
import com.ray.projectframe.ui.viewhelper.VaryViewHelper;
import com.ray.projectframe.utils.AppManager;
import com.ray.projectframe.utils.SystemUtil;


/**
 * Created by caism on 2017/4/13.
 */

public abstract class BaseActivity<P extends BasePresenter>  extends AppCompatActivity implements VaryViewHelper.NetWorkErrorListener,BaseIView {
    public Context mContext;
    protected VaryViewHelper mVaryViewHelper;
    protected int viewType = ConstantField.TYPE_VIEW_DATA;
    protected P mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        AppManager.getInstance(mContext).addActivity(this);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(inflateContentView());
        ButterKnife.bind(this);
        initPresenter();
        initView(savedInstanceState);
        initEvents();
    }

    protected abstract int inflateContentView();
    /**
     * 初始化p
     **/
    protected abstract void initPresenter() ;

    /**
     * 初始化view
     * */
    protected abstract void initView(Bundle savedInstanceState) ;
    /**
     * 初始化事件
     */
    protected abstract void initEvents();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getInstance(mContext).killActivity(this);
        if (mPresenter != null) {
            mPresenter.onDetachView();
        }
    }

    //展示加载页
    public void showLoadingView() {
        if (mVaryViewHelper != null) {
            mVaryViewHelper.showLoadingView();
        }
    }

    //展示网络错误页
    public void showNetWorkErrorView() {
        if (mVaryViewHelper != null) {
            mVaryViewHelper.showNetWorkErrorView();
        }
    }
    public void showErrorView() {
        if (mVaryViewHelper != null) {
            viewType = ConstantField.TYPE_VIEW_ERROR;
            mVaryViewHelper.showNetWorkErrorView();
        }
    }
    //展示数据页
    public void showDataView() {
        if (mVaryViewHelper != null) {
            viewType = ConstantField.TYPE_VIEW_DATA;
            mVaryViewHelper.showDataView();
        }
    }

    //设置覆盖加载页面 网络错误页面
    protected void initHelperView(View bindView) {
        mVaryViewHelper = new VaryViewHelper.Builder()
                .setDataView(bindView)//放数据的父布局，逻辑处理在该Activity中处理
                .setLoadingView(LayoutInflater.from(this).inflate(R.layout.view_loading, null))//加载页，无实际逻辑处理
                .setEmptyView(LayoutInflater.from(this).inflate(R.layout.view_empty, null))//空页面，无实际逻辑处理
                .setErrorView(LayoutInflater.from(this).inflate(R.layout.view_error, null))//错误页面
                .setNetWorkErrorView(LayoutInflater.from(this).inflate(R.layout.view_neterror, null))//网络错误页
                .setRefreshListener(this)//错误页点击刷新实现
                .build();
    }

    //打开设置网络
    @Override
    public void onSettingNetWork() {
        SystemUtil.openWifi(mContext);
    }

    //刷新覆盖
    @Override
    public void onRefresh() {

    }
}
