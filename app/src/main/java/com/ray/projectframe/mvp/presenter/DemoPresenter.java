package com.ray.projectframe.mvp.presenter;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;

import com.jakewharton.rxbinding2.widget.RxTextView;
import com.ray.library.base.mvp.BasePresenter;
import com.ray.library.bean.User;
import com.ray.library.rxjava.RxHelper;
import com.ray.library.rxjava.RxRetrofitCache;
import com.ray.library.rxjava.RxSubscribe;
import com.ray.library.rxjava.util.RxManager;
import com.ray.library.utils.SPUtils;
import com.ray.library.utils.SystemUtil;
import com.ray.projectframe.mvp.view.LoginIView;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.ray.projectframe.mvp.presenter.DemoEntry.REG_LOGIN;


/**
 * Created by ray on 17/5/16.
 * RxJava操作符使用案例
 */
public class DemoPresenter extends BasePresenter<LoginIView> {
    private static final String TAG = "LoginPresenter";

    public DemoPresenter(Activity mContext, LoginIView mView) {
        super(mContext, mView);
    }

    //登录
    public void login(Context c,String phone) {
        DemoEntry registEntry=new DemoEntry(REG_LOGIN);
        registEntry.setMobile(phone);
        registEntry.setPassword("123456");
        registEntry.setExt0(SystemUtil.getDeviceId(c));
        /**
         * 1.普通请求
         */
        mApiService.registerAndLogin().compose(RxHelper.handleResult()).
                subscribe(new RxSubscribe<User>(mContext,mView) {
                    @Override
                    public void _onNext(User user) {
                    }

                    @Override
                    public void _onError(String message) {

                    }

                });

        /**
         * 2.遇到异常重试请求
         */
        mApiService.registerAndLogin().retryWhen(RxHelper.retryWhen()).compose(RxHelper.handleResult()).
                subscribe(new RxSubscribe<User>(mContext,mView) {
                    @Override
                    public void _onNext(User user) {
                    }

                    @Override
                    public void _onError(String message) {

                    }
                });


        /**
         * 3.轮询请求
         */
        RxManager.interval(10, time -> mApiService.registerAndLogin().compose(RxHelper.handleResult()).
                subscribe(new RxSubscribe<User>(mContext,mView) {
                    @Override
                    public void _onNext(User user) {
                    }

                    @Override
                    public void _onError(String message) {

                    }
                }));

        /**
         *4. 依次执行两个请求
         * 第二个请求需使用第一个请求的结果
         */
        mApiService.registerAndLogin().compose(RxHelper.handleResult()).doOnNext(o -> {
             //第一次请求成功
        }).flatMap(user -> mApiService.registerAndLogin()).compose(RxHelper.handleResult()).
                subscribe(new RxSubscribe<User>(mContext,mView) {
                    @Override
                    public void _onNext(User user) {
                        //第二次请求成功
                    }

                    @Override
                    public void _onError(String message) {

                    }
                });


        /**
         * 5.合并两个请求结果后发送事件1
         * 实现较为简单的从（网络 + 本地）获取数据 & 统一展示
         */
        Observable<User> fromCache = Observable.create((ObservableOnSubscribe<User>) e -> {
            User cache = (User) SPUtils.get("",null);
            if (cache != null) {
                e.onNext(cache);
            } else {
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        Observable.merge(fromCache, mApiService.registerAndLogin()).subscribe(new Observer<Object>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object o) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
        /**
         * 6.合并两个请求结果后发送事件2
         * 实现较为复杂的合并2个网络请求向2个服务器获取数据 & 统一展示
         */
        Observable.zip(mApiService.registerAndLogin(), mApiService.registerAndLogin(), (userBaseModel, userBaseModel2) ->
                new User(userBaseModel.msg + userBaseModel2.msg)).observeOn(AndroidSchedulers.mainThread()) // 在主线程接收 & 处理数据
                .subscribe(combine_infro -> {
                    // 结合显示2个网络请求的数据结果
                    Log.d(TAG, "最终接收到的数据是：" + combine_infro);
                }, throwable -> System.out.println("失败"));



        /**
         * 7.组合两个不同来源的数据，优先使用本地数据（本地无数据才向网络获取）
         * 可控制是否强制获取网络数据
         */
        RxRetrofitCache.load(mContext,"user",mApiService.registerAndLogin(),true).compose(RxHelper.handleResult()).
                subscribe(new RxSubscribe<User>(mContext,mView) {
                    @Override
                    public void _onNext(User user) {
                    }

                    @Override
                    public void _onError(String message) {

                    }
                });

        /**
         * 8.联合判断
         * 提交按钮是否可点击
         */
        EditText name=null,age=null,job=null;
        //采用skip(1)原因：跳过 一开始EditText无任何输入时的空值
        Observable<CharSequence> nameObservable = RxTextView.textChanges(name).skip(1);
        Observable<CharSequence> ageObservable = RxTextView.textChanges(age).skip(1);
        Observable<CharSequence> jobObservable = RxTextView.textChanges(job).skip(1);
        Observable.combineLatest(nameObservable,ageObservable,jobObservable, (charSequence, charSequence2, charSequence3) -> {
            boolean isUserNameValid = !TextUtils.isEmpty(name.getText()) ;
            boolean isUserAgeValid = !TextUtils.isEmpty(age.getText());
            boolean isUserJobValid = !TextUtils.isEmpty(job.getText()) ;
            return isUserNameValid && isUserAgeValid && isUserJobValid;
        }).subscribe(s -> Log.e(TAG, "提交按钮是否可点击： "+s));

        /**
         * 9.功能防抖
         * 2s内只会响应一次click
         */
       RxManager.clicks(name, 2, () -> {

       });


        /**
         * 自动搜索优化
         * 间隔时间达到固定值才能响应事件
         */
        RxManager.autoSearch(name, 1, () -> {

        });

    }




}
