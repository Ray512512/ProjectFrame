package com.ray.projectframe;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import frame.project.ray.projectframe.R;
import com.ray.projectframe.base.ui.TopBarBaseActivity;
import com.ray.projectframe.utils.ToastUtil;


public class DemoActivity extends TopBarBaseActivity {


    @BindView(R.id.tv_test)
    TextView tvTest;

    @Override
    protected int inflateContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle(getClass().getSimpleName());
        setTopLeftButton(R.mipmap.ic_launcher);
        setTopRightButton(R.mipmap.ic_launcher_round, () -> Toast.makeText(mContext, "123", Toast.LENGTH_SHORT).show());
    }

    @Override
    protected void initEvents() {
        tvTest.setOnClickListener(v -> ToastUtil.show(DemoActivity.this, "123"));
    }

}
