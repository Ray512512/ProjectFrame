package com.ray.projectframe;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.ray.library.base.ui.BaseFragment;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import tyrantgit.widget.HeartLayout;


/**
 * Created by 陈序员 on 2017/5/3.
 * Email: Matthew_Chen_1994@163.com
 * Blog: https://blog.ifmvo.cn
 */

public class Fragment1 extends BaseFragment {

    @BindView(R.id.txt)
    TextView txt;
    @BindView(R.id.heart_layout)
    HeartLayout mHeartLayout;
    private Timer timer;

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
        mHeartLayout.setOnClickListener(v -> {
            if(!isRun){
                start();
            }else {
                stop();
            }
//            mHeartLayout.addHeart(randomColor());
        });
    }

    private boolean isRun=false;
    private void start(){
        isRun=true;
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                mHeartLayout.post(() -> mHeartLayout.addHeart(randomColor()));
            }
        }, 100, 100);
    }

    private void stop(){
        isRun=false;
        timer.cancel();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stop();
    }
    private int randomColor() {
        return Color.rgb(new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255));
    }

}
