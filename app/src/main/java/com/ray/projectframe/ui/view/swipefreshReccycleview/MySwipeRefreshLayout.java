package com.ray.projectframe.ui.view.swipefreshReccycleview;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;

import com.ray.projectframe.R;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


/**
 * Created by wxy on 16/6/20.
 * 1. setOnRefreshListener()触发刷新回调方法
 * 2. setRefreshing(false)设置刷新完成，进度圆圈圈停止转动。
 * 3. setEnabled(false)可以禁止下拉刷新
 * 4. setDistanceToTriggerSync(300);下拉距离
 * 5. setSize(SwipeRefreshLayout.LARGE); 圈圈大小
 * 6. setColorSchemeResources(R.color.colorYellow);颜色
 * 解决左滑右滑出现刷新 屏蔽
 */
public class MySwipeRefreshLayout extends SwipeRefreshLayout {
    // 子View
    ViewGroup viewGroup;
    private int scaleTouchSlop;
    private float preX;
    public MySwipeRefreshLayout(Context context) {
        super(context);
        initView(context);
    }

    private void initView(Context context) {
        setColorSchemeResources(R.color.colorYellow);
        scaleTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public MySwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    /**
     * 自动刷新
     */
    public void autoRefresh() {
        try {
            Field mCircleView = SwipeRefreshLayout.class.getDeclaredField("mCircleView");
            mCircleView.setAccessible(true);
            View progress = (View) mCircleView.get(this);
            progress.setVisibility(VISIBLE);
            Method setRefreshing = SwipeRefreshLayout.class.getDeclaredMethod("setRefreshing", boolean.class, boolean.class);
            setRefreshing.setAccessible(true);
            setRefreshing.invoke(this, true, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        if (viewGroup != null && viewGroup.getScrollY() > 1) {
            this.setEnabled(false);
            return super.onTouchEvent(arg0);
        } else {
            // 让SwipeRefreshLayout处理本次事件
            return super.onTouchEvent(arg0);
        }

    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                preX = ev.getX();
                break;

            case MotionEvent.ACTION_MOVE:
                float moveX = ev.getX();
                float instanceX = Math.abs(moveX - preX);
                // 容差值大概是24，再加上60
                if(instanceX > scaleTouchSlop + 60){
                    return false;
                }
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }
    public ViewGroup getViewGroup() {
        return viewGroup;
    }

    public void setViewGroup(ViewGroup viewGroup) {
        this.viewGroup = viewGroup;
        this.viewGroup.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MySwipeRefreshLayout.this.viewGroup.getScrollY() <= 0) {
                    MySwipeRefreshLayout.this.setEnabled(true);
                }
                return false;
            }
        });

    }
}
