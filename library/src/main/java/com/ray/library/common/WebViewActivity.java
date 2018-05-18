package com.ray.library.common;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.WebView;
import android.widget.TextView;

import com.ray.library.R;
import com.ray.library.R2;
import com.ray.library.base.ui.BaseActivity;
import com.ray.library.retrofit.DemoApiManager;
import com.ray.library.utils.WebViewUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WebViewActivity extends BaseActivity {
    public static final String KEY_TYPE="type";
    public static final String KEY_URL="url";

    public static final String ABOUT_US_URL="views/intro.html";

    public static final int KEY_ABOUTUS=0;


    @BindView(R2.id.tv_title)
    TextView tvTitle;
    @BindView(R2.id.webview)
    WebView webview;

    private int type;
    private String url;
    public static void start(Context context, int type, String url){
        Intent intent=new Intent(context,WebViewActivity.class);
        intent.putExtra(KEY_TYPE,type);
        intent.putExtra(KEY_URL,url);
        context.startActivity(intent);
    }
    public static void start(Context context, int type){
        Intent intent=new Intent(context,WebViewActivity.class);
        intent.putExtra(KEY_TYPE,type);
        context.startActivity(intent);
    }

    @Override
    protected int inflateContentView() {
        return R.layout.activity_web_view;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        type=getIntent().getIntExtra(KEY_TYPE,-1);
        url=getIntent().getStringExtra(KEY_URL);
        if(type==-1){
            finish();
            return;
        }
        switch (type){
            case KEY_ABOUTUS:
                url= DemoApiManager.ROOT_URL+ABOUT_US_URL;
                tvTitle.setText(R.string.mine_item_about_us);
                break;
        }
        if(TextUtils.isEmpty(url)){
            finish();
            return;
        }
        WebViewUtil.setWebViewSetting(webview);
        webview.loadUrl(url);
    }

    @Override
    protected void initEvents() {

    }


    @OnClick(R2.id.img_back)
    public void onViewClicked() {
        finish();
    }
}
