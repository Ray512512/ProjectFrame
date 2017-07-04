package com.ray.projectframe.base.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.ray.projectframe.R;
import com.ray.projectframe.base.view.BottomTabView;
import com.ray.projectframe.ui.view.viewpager.LimitViewPager;

import java.util.List;

/**
* 应用主tab activity  继承者无需做任何事
* */
public abstract class BottomTabBaseActivity extends BaseActivity {

    FragmentPagerAdapter adapter;
    LimitViewPager viewPager;
    BottomTabView bottomTabView;
    @Override
    protected int inflateContentView() {
        return R.layout.activity_base_bottom_tab;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_bottom_tab);
        viewPager = (LimitViewPager) findViewById(R.id.viewPager);
        bottomTabView = (BottomTabView) findViewById(R.id.bottomTabView);
        if (getCenterView() == null) {
            bottomTabView.setTabItemViews(getTabViews());
        } else {
            bottomTabView.setTabItemViews(getTabViews(), getCenterView());
        }


        viewPager.setOffscreenPageLimit(getFragments().size());
        adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return getFragments().get(position);
            }

            @Override
            public int getCount() {
                return getFragments().size();
            }
        };

        viewPager.setAdapter(adapter);

        bottomTabView.setOnTabItemSelectListener(position -> viewPager.setCurrentItem(position, true));
        bottomTabView.setOnSecondSelectListener(position -> {

        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bottomTabView.updatePosition(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    protected abstract List<BottomTabView.TabItemView> getTabViews();

    protected abstract List<Fragment> getFragments();

    protected View getCenterView() {
        return null;
    }
}
