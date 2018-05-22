package com.ray.library.base.ui;/*
package com.ray.projectframe.base.ui;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.ray.projectframe.R;
import com.ray.projectframe.base.mvp.BaseIView;
import com.ray.projectframe.base.mvp.BasePresenter;
import com.ray.projectframe.common.ConstantField;
import com.ray.library.retrofit.BaseApiManager;
import com.ray.library.retrofit.BaseApiService;
import com.ray.projectframe.rxbus.RxBus;
import com.ray.projectframe.ui.viewhelper.VaryViewHelper;
import com.ray.projectframe.utils.AppManager;
import com.ray.projectframe.utils.SystemUtil;

import butterknife.ButterKnife;

public abstract class TopBarBaseActivity<P extends BasePresenter>   extends AppCompatActivity implements VaryViewHelper.NetWorkErrorListener,BaseIView {
    TextView tvTitle;
    Toolbar toolbar;
    FrameLayout viewContent;

    public Context mContext;
    public BaseApiService mApiService;
    protected P mPresenter;
    protected VaryViewHelper mVaryViewHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        mApiService= BaseApiManager.getInstance();
        AppManager.getInstance(mContext).addActivity(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.activity_base_top_bar);
        viewContent= (FrameLayout) findViewById(R.id.viewContent);
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        tvTitle= (TextView) findViewById(R.id.tvTitle);
        setSupportActionBar(toolbar);
        //设置不显示自带的 Title
        if(getSupportActionBar()!=null)
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //将继承 TopBarBaseActivity 的布局解析到 FrameLayout 里面
        LayoutInflater.from(this).inflate(inflateContentView(), viewContent);
        ButterKnife.bind(this);

        RxBus.get().register(this);
        initPresenter();
        initView(savedInstanceState);
        initEvents();
    }

    protected abstract int inflateContentView();
    protected abstract void initPresenter() ;
    */
/**
     * 初始化view
     * *//*

    protected abstract void initView(Bundle savedInstanceState) ;
    */
/**
     * 初始化事件
     *//*

    protected abstract void initEvents();
    */
/**
     * 初始化p
     **//*


    protected void setTitle(String title) {
        if (!TextUtils.isEmpty(title)) {
            tvTitle.setText(title);
        }
    }

    protected void setTopLeftButton(int iconResId, OnClickListener onClickListener){
        toolbar.setNavigationIcon(iconResId);
        this.onClickListenerTopLeft = onClickListener;
    }
    protected void setTopLeftButton(int iconResId){
        toolbar.setNavigationIcon(iconResId);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
            if(onClickListenerTopLeft!=null)
            onClickListenerTopLeft.onClick();
        }else if(item.getItemId()==R.id.menu_1){
            if(onClickListenerTopRight!=null){
                onClickListenerTopRight.onClick();
            }
        }
        return true; // true 告诉系统我们自己处理了点击事件
    }

    OnClickListener onClickListenerTopLeft;
    public interface OnClickListener{
        void onClick();
    }
    OnClickListener onClickListenerTopRight;
    // icon 图标id
    int menuResId;
    String menuStr;
    protected void setTopRightButton(String menuStr, OnClickListener onClickListener){
        this.onClickListenerTopRight = onClickListener;
        this.menuStr = menuStr;
    }

    protected void setTopRightButton(int menuResId, OnClickListener onClickListener){
        this.onClickListenerTopRight = onClickListener;
        this.menuResId = menuResId;
        this.menuStr = "Button";
    }
    protected void setTopRightButton(String menuStr, int menuResId, OnClickListener onClickListener){
        this.menuResId = menuResId;
        this.menuStr = menuStr;
        this.onClickListenerTopRight = onClickListener;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (menuResId != 0 || !TextUtils.isEmpty(menuStr)){
            getMenuInflater().inflate(R.menu.menu_activity_base_top_right, menu);
        }
        return true;
    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (menuResId != 0) {
            menu.findItem(R.id.menu_1).setIcon(menuResId);
        }
        if (!TextUtils.isEmpty(menuStr)){
            menu.findItem(R.id.menu_1).setTitle(menuStr);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getInstance(mContext).killActivity(this);
        if (mPresenter != null) {
            mPresenter.onDetachView();
        }
        RxBus.get().unregister(this);
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
            viewType = Constant.TYPE_VIEW_ERROR;
            mVaryViewHelper.showNetWorkErrorView();
        }
    }
    //展示数据页
    public void showDataView() {
        if (mVaryViewHelper != null) {
            viewType = Constant.TYPE_VIEW_DATA;
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
    public void reTry() {

    }
}

*/
