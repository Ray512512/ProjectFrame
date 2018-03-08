package com.ray.library.common.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class PagerFragmentAdapter extends FragmentPagerAdapter {
         private ArrayList<Fragment> tabFragments=new ArrayList<>();
    private String[] tabIndicators;

    public void setTabIndicators(String[] tabIndicators) {
        this.tabIndicators = tabIndicators;
    }

    public PagerFragmentAdapter(FragmentManager fm, ArrayList<Fragment> tabFragments, String[]  tabIndicators) {
        super(fm);
        this.tabFragments = tabFragments;
        this.tabIndicators = tabIndicators;
    }

    public PagerFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return tabFragments.get(position);
        }

        @Override
        public int getCount() {
            return tabIndicators.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabIndicators[position];
        }
    }
