package com.ray.library.base.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.SharedElementCallback;
import android.view.View;
import android.view.ViewGroup;

import com.ray.library.R;
import com.ray.library.base.mvp.BasePresenter;
import com.ray.library.view.view.photoview.ImageGalleryActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LookBigPicActivity<P extends BasePresenter> extends BaseActivity {
    protected P mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int inflateContentView() {
        return R.layout.activity_look_big_pic;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
    }

    @Override
    protected void initEvents() {
        setSharedElementCallback(this);
    }

    private Bundle mReenterState;
    protected ViewGroup picListParent;

    /**
     * 接管Activity的setExitSharedElementCallback
     * @param activity
     */
    public void setSharedElementCallback(Activity activity){
        ActivityCompat.setExitSharedElementCallback(activity, new SharedElementCallback() {
            @Override
            public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
                if (mReenterState!=null){
                    int index = mReenterState.getInt(ImageGalleryActivity.TAG_POS,0);
                    sharedElements.clear();
                    sharedElements.put(ImageGalleryActivity.TAG_VIEW, picListParent.getChildAt(index));
                    mReenterState = null;
                }
            }
        });

    }
    @Override
    public void onActivityReenter(int resultCode, Intent data) {
        super.onActivityReenter(resultCode, data);
        mReenterState = new Bundle(data.getExtras());
    }

    public interface LookBitPicCallback{
        void lookBigPic(ArrayList<String> pic, int pos, View imageView, ViewGroup viewGroup);
    }

    public void LookBigPic(ArrayList<String> pic, int pos, View imageView, ViewGroup viewGroup){
        picListParent=viewGroup;
        ImageGalleryActivity.startWithElement(this, pic, pos, imageView);
    }
}
