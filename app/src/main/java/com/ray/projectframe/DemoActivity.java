package com.ray.projectframe;

import android.content.ClipData;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.DragEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;
import com.jakewharton.rxbinding2.view.RxView;
import com.ray.projectframe.base.ui.TopBarBaseActivity;
import com.ray.projectframe.common.adapter.ListViewAdapter;
import com.ray.projectframe.common.adapter.base.ViewHolder;
import com.ray.projectframe.rxbus.Event;
import com.ray.projectframe.rxbus.RxBus;
import com.ray.projectframe.ui.view.listview.HorizontalListView;
import com.ray.projectframe.utils.ToastUtil;
import com.varunest.sparkbutton.SparkButton;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;


public class DemoActivity extends TopBarBaseActivity {
    private static final java.lang.String TAG ="DemoActivity";
    @BindView(R.id.tv_test)
    TextView tvTest;
    @BindView(R.id.spark_button)
    SparkButton sparkButton;
    @BindView(R.id.h_listview)
    HorizontalListView hListview;

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
        tvTest.setOnLongClickListener(v -> {
            ClipData data = ClipData.newPlainText("", "");
            v.startDrag(data, new View.DragShadowBuilder(v), null, 0);
            return false;
        });
        tvTest.setOnDragListener((v, event) -> {
            switch (event.getAction()) {

                case DragEvent.ACTION_DRAG_STARTED:
                    return true;

                case DragEvent.ACTION_DRAG_ENTERED:
                    v.setBackgroundColor(Color.CYAN);
                    return true;

                case DragEvent.ACTION_DRAG_LOCATION:
                    return true;

                case DragEvent.ACTION_DRAG_EXITED:
                    v.setBackgroundColor(Color.RED);
                    return true;

                case DragEvent.ACTION_DROP:
                    v.setBackgroundColor(Color.GREEN);
                    return true;

                case DragEvent.ACTION_DRAG_ENDED:
                    return true;
            }
            return false;
        });

        playHeartAnimation();

        ArrayList<Object> a=new ArrayList<>();
        a.add(""); a.add(""); a.add(""); a.add(""); a.add(""); a.add(""); a.add("");
        a.add(""); a.add(""); a.add(""); a.add(""); a.add(""); a.add(""); a.add("");
        a.add(""); a.add(""); a.add(""); a.add(""); a.add(""); a.add(""); a.add("");
        a.add(""); a.add(""); a.add(""); a.add(""); a.add(""); a.add(""); a.add("");
        hListview.setAdapter(new ListViewAdapter<Object>(this,a,R.layout.item_listview) {
            @Override
            public void convert(ViewHolder helper, Object item, int position) {

            }
        });


        RxView.clicks(tvTest).throttleFirst(1, TimeUnit.SECONDS).subscribe(new Consumer<Object>() {
            @Override
            public void accept(@NonNull Object o) throws Exception {
                RxBus.get().post("",new Event.DemoEvent());
            }
        });
    }


    @Subscribe(tags = {@Tag(Event.TAG.TEXT),@Tag("")})
    public void eat(Event.DemoEvent event) {
        ToastUtil.show(mContext,"1");
    }

    @Subscribe
    public void eat2(Event.DemoEvent event) {
        ToastUtil.show(mContext,"2");
    }

    private void playHeartAnimation() {
        sparkButton.setChecked(false);
        new Handler().postDelayed(() -> {
            sparkButton.setChecked(true);
            sparkButton.playAnimation();
        }, 300);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
