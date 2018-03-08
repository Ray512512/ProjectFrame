package com.ray.projectframe;

import android.view.View;
import android.widget.TextView;

import com.ray.library.base.ui.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by 陈序员 on 2017/5/3.
 * Email: Matthew_Chen_1994@163.com
 * Blog: https://blog.ifmvo.cn
 */

public class Fragment1 extends BaseFragment {

    @BindView(R.id.txt)
    TextView txt;

    @Override
    protected int inflateContentView() {
        return R.layout.fragment;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initView(View view) {
        ButterKnife.bind(this,view);
//        txt.setText(this.getClass().getSimpleName());
//        txt.setOnClickListener(v -> startActivity(new Intent(mContext, DemoActivity.class)));
    }



}
