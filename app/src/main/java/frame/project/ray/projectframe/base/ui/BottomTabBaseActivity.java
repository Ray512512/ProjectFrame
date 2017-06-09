package frame.project.ray.projectframe.base.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.List;

import frame.project.ray.projectframe.R;
import frame.project.ray.projectframe.base.view.BottomTabView;


public abstract class BottomTabBaseActivity extends BaseActivity {

    FragmentPagerAdapter adapter;
    LimitViewPager viewPager;
    BottomTabView bottomTabView;
    @Override
    protected void setMainLayout() {
        setContentView(R.layout.activity_base_bottom_tab);
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
