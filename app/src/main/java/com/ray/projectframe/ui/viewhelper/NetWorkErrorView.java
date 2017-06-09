package com.ray.projectframe.ui.viewhelper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.ray.projectframe.R;

import butterknife.ButterKnife;

/**
 * Created by wxy on 16/8/15.
 * 网络错误页
 */
public class NetWorkErrorView extends LinearLayout {
    public NetWorkErrorView(Context context) {
        super(context);
        initView(context);
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_empty, this);
        ButterKnife.bind(this, view);
    }
}
