package com.ray.library.common.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.ray.library.R;


import magicindicator.buildins.UIUtil;
import magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;

import static com.ray.library.common.adapter.IndicatorAdapter.IndicatorType.Order;

/**
 * Created by Ray on 2017/12/13.
 */

public class IndicatorAdapter extends CommonNavigatorAdapter {
    private String[] mTitleDataList;
    private ViewPager mViewPager;
    public enum IndicatorType{
        Order,ShopCar
    }
    private IndicatorType mIndicatorType = Order;
    private int pading=10;

    public IndicatorAdapter(String[] mTitleDataList, ViewPager mViewPager) {
        this.mTitleDataList = mTitleDataList;
        this.mViewPager = mViewPager;
    }

    public IndicatorAdapter(String[] mTitleDataList, ViewPager mViewPager, IndicatorType indicatorType) {
       this(mTitleDataList,mViewPager);
        mIndicatorType=indicatorType;
    }

    public IndicatorAdapter(String[] mTitleDataList, ViewPager mViewPager, IndicatorType indicatorType, int padding) {
       this(mTitleDataList,mViewPager,indicatorType);
        pading=padding;
    }

    public void setmTitleDataList(String[] mTitleDataList) {
        this.mTitleDataList = mTitleDataList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mTitleDataList == null ? 0 : mTitleDataList.length;
    }

    @Override
    public IPagerTitleView getTitleView(Context context, int i) {
        ColorTransitionPagerTitleView colorTransitionPagerTitleView = new ColorTransitionPagerTitleView(context,pading);
        colorTransitionPagerTitleView.setNormalColor(ContextCompat.getColor(context, R.color.text_1));
        colorTransitionPagerTitleView.setSelectedColor(ContextCompat.getColor(context,R.color.mainColor));
        colorTransitionPagerTitleView.setText(mTitleDataList[i]);
//        colorTransitionPagerTitleView.setTextSize(16);
        colorTransitionPagerTitleView.setOnClickListener(view -> mViewPager.setCurrentItem(i));
        return colorTransitionPagerTitleView;
    }

    @Override
    public IPagerIndicator getIndicator(Context context) {
        switch (mIndicatorType){
            case ShopCar:
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setLineHeight(UIUtil.dip2px(context, 2));
                indicator.setColors(context.getResources().getColor(R.color.mainhalfColor));
                return indicator;
            case Order:
                indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                indicator.setLineHeight(UIUtil.dip2px(context, 5));
                indicator.setLineWidth(UIUtil.dip2px(context, 20));
                indicator.setRoundRadius(UIUtil.dip2px(context, 3));
                indicator.setStartInterpolator(new AccelerateInterpolator());
                indicator.setEndInterpolator(new DecelerateInterpolator(2.0f));
                indicator.setColors(context.getResources().getColor(R.color.mainhalfColor));
                return indicator;
        }
        return  new LinePagerIndicator(context);
    }
}
