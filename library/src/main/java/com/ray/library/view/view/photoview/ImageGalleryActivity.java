package com.ray.library.view.view.photoview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.SharedElementCallback;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ray.library.R;
import com.ray.library.utils.SystemUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by tiansj on 15/8/6.
 */
public class ImageGalleryActivity extends AppCompatActivity {
public static final String TAG_VIEW="tansition_view";
public static final String TAG_POS="position";
public static final String TAG_URLS="images";
    private int position;
    private List<String> imgUrls; //图片列表
    private TextView headTitle;
    private ImageView headBackBtn;

    private ViewPager mViewPager;


    public static void startWithElement(Activity context, ArrayList<String> urls,
                                        int firstIndex, View view) {
        Intent intent = new Intent(context, ImageGalleryActivity.class);
        intent.putStringArrayListExtra(TAG_URLS, urls);
        intent.putExtra(TAG_POS, firstIndex);
        ActivityOptionsCompat compat = null;
        if (view == null) {
            compat = ActivityOptionsCompat.makeSceneTransitionAnimation(context);
        } else {
            compat = ActivityOptionsCompat.makeSceneTransitionAnimation(context, view,
                    TAG_VIEW);
        }
        ActivityCompat.startActivity(context, intent, compat.toBundle());
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_gallery);

        Intent intent = getIntent();
        position = intent.getIntExtra(TAG_POS, 0);
        imgUrls = intent.getStringArrayListExtra(TAG_URLS);
        if(imgUrls == null) {
            imgUrls = new ArrayList<>();
        }
        initView();
        initViewEvent();
        initGalleryViewPager();

        setEnterSharedElementCallback(new SharedElementCallback() {
            @Override
            public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
                ViewGroup layout = (ViewGroup) mViewPager.findViewWithTag(mViewPager.getCurrentItem());
                if (layout == null) {
                    return;
                }
                sharedElements.clear();
                sharedElements.put(TAG_VIEW, layout);
            }
        });
    }

    private void initView() {
        headTitle = (TextView)findViewById(R.id.tv_title);
        headTitle.setText("1/" + imgUrls.size());
        headBackBtn = (ImageView)findViewById(R.id.img_back);
        headBackBtn.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    private void initViewEvent() {
        headBackBtn.setOnClickListener(v -> SystemUtil.finishAfterTransition(this));
    }

    private void initGalleryViewPager() {
        PhotoViewAdapter pagerAdapter = new PhotoViewAdapter(this, imgUrls);
        pagerAdapter.setOnItemChangeListener(new PhotoViewAdapter.OnItemChangeListener() {
            int len = imgUrls.size();
            @Override
            public void onItemChange(int currentPosition) {
                headTitle.setText((currentPosition+1) + "/" + len);
            }
        });
        mViewPager = (ViewPager)findViewById(R.id.viewer);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(pagerAdapter);
        mViewPager.setCurrentItem(position);
    }

    @Override
    public void finishAfterTransition() {
        Intent intent = new Intent();
        intent.putExtra(TAG_POS, mViewPager.getCurrentItem());
        setResult(RESULT_OK, intent);
        super.finishAfterTransition();
    }
}
