package com.ray.projectframe;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.ray.library.base.ui.BottomTabBaseActivity;
import com.ray.library.base.view.BottomTabView;
import com.ray.library.utils.AppManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BottomTabBaseActivity {
    List<BottomTabView.TabItemView> tabItemViews = new ArrayList<>();

    @Override
    protected List<BottomTabView.TabItemView> getTabViews() {
        tabItemViews.add(new BottomTabView.TabItemView(this, "标题1", R.color.colorPrimary,
                R.color.colorAccent, R.mipmap.ic_launcher, R.mipmap.ic_launcher_round));
        tabItemViews.add(new BottomTabView.TabItemView(this, "标题2", R.color.colorPrimary,
                R.color.colorAccent, R.mipmap.ic_launcher, R.mipmap.ic_launcher_round));
        tabItemViews.add(new BottomTabView.TabItemView(this, "标题3", R.color.colorPrimary,
                R.color.colorAccent, R.mipmap.ic_launcher, R.mipmap.ic_launcher_round));
        tabItemViews.add(new BottomTabView.TabItemView(this, "标题4", R.color.colorPrimary,
                R.color.colorAccent, R.mipmap.ic_launcher, R.mipmap.ic_launcher_round));
        return tabItemViews;
    }

    @Override
    protected List<Fragment> getFragments() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new Fragment1());
        fragments.add(new Fragment2());
        fragments.add(new Fragment3());
        fragments.add(new Fragment4());
        return fragments;
    }

    public static void start(Context context){
        context.startActivity(new Intent(context,MainActivity.class));
        AppManager.getInstance(context).finishActivityExceptMain();
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void initEvents() {

    }

}
