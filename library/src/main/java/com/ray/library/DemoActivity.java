/*
package com.ray.library;

import android.Manifest;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;
import com.jakewharton.rxbinding2.view.RxView;
import com.ray.library.base.ui.BaseActivity;
import com.ray.library.bean.User;
import com.ray.library.bean.UserDao;
import com.ray.library.imgseletor.MultiImageSelector;
import com.ray.library.rxbus.Event;
import com.ray.library.utils.T;
import com.ray.library.view.view.listview.HorizontalListView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class DemoActivity extends BaseActivity {
    private static final String TAG = "DemoActivity";
    @BindView(R.id.tv_test)
    TextView tvTest;
   */
/* @BindView(R.id.spark_button)
    SparkButton sparkButton;*//*

    @BindView(R.id.h_listview)
   HorizontalListView hListview;
    @BindView(R.id.img_show)
    ImageView imgShow;

    private UserDao mUserDao;

    @Override
    protected int inflateContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initPresenter() {
//        DemoActivityPermissionsDispatcher.getDeviceIdPerssionWithCheck(this);

    }

    @NeedsPermission({Manifest.permission.READ_PHONE_STATE})
    public void getDeviceIdPerssion() {
    }


    @Override
    protected void initView(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        setTitle(getClass().getSimpleName());
//        setTopLeftButton(R.mipmap.ic_launcher);
//        setTopRightButton(R.mipmap.ic_launcher_round, () -> T.makeText(mContext, "123", T.LENGTH_SHORT).show());
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

        RxView.clicks(tvTest).throttleFirst(1, TimeUnit.SECONDS).subscribe(new Consumer<Object>() {
            @Override
            public void accept(@NonNull Object o) throws Exception {
//                RxBus.get().post("", new Event.DemoEvent());
//                new DemoPresenter((Activity) mContext, DemoActivity.this).login(mContext, "17775526994");
            }
        });

//        mUserDao = MyDaoMaster.getInstance(this).getDaoSession().getUserDao();

    }

    @Subscribe(tags = {@Tag(Event.TAG.TEXT), @Tag("")})
    public void eat(Event.DemoEvent event) {
        T.show(mContext, "1");
    }

    @Subscribe
    public void eat2(Event.DemoEvent event) {
        T.show(mContext, "2");
    }

    private void playHeartAnimation() {
        */
/*sparkButton.setChecked(false);
        new Handler().postDelayed(() -> {
            sparkButton.setChecked(true);
            sparkButton.playAnimation();
        }, 300);*//*

    }

    private void registSMSreciver() {
       */
/* CodeConfig config = new CodeConfig.Builder().codeLength(4) // 设置验证码长度
                .smsFromStart(133) // 设置验证码发送号码前几位数字
                //.smsFrom(1690123456789) // 如果验证码发送号码固定，则可以设置验证码发送完整号码
                .smsBodyStartWith("百度科技") // 设置验证码短信开头文字
                .smsBodyContains("验证码") // 设置验证码短信内容包含文字
                .build();
        EditText editText = new EditText(mContext);
        AuthCode.getInstance().with(mContext).config(config).into(editText);*//*

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.add, R.id.delete, R.id.update, R.id.query,R.id.seletor_pic, R.id.take_photo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.add:
                DemoUser mUser = new DemoUser((long) 2, "媳妇");
                mUserDao.insert(mUser);//添加一个
                break;
            case R.id.delete:
                mUserDao.deleteByKey((long) 2);
                break;
            case R.id.update:
                mUserDao.update(new DemoUser((long) 2, "蒲蒲"));
                break;
            case R.id.query:
                DemoUser s = mUserDao.queryBuilder().where(UserDao.Properties.Name.eq("蒲蒲")).unique();
//                DemoUser s=mUserDao.init((long) 2);
                tvTest.setText(s == null ? "暂无此用户" : s.getName());
                break;
            case R.id.seletor_pic:
                chooseImages(1,false);
                break;
            case R.id.take_photo:
                chooseImages(2,true);
                break;
        }
    }

    public void chooseImages(int startAction, boolean isCamera) {
       MultiImageSelector.create()
               .single()
               .showCamera(false)
               .onlyCamera(isCamera)
               .start(this,startAction);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode){
            case RESULT_OK ://选择照片
                String path = "file://" + data.getStringArrayListExtra(MultiImageSelector.EXTRA_RESULT).get(0);
                switch (requestCode) {
                    case 1:
                        Glide.with(this).init(path).into(imgShow);
                        break;
                    case 2:
                        Glide.with(this).init(path).into(imgShow);
                        break;
                }
                break;
        }
    }


}
*/
