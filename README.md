# ProjectFrame

集mvp+rxjava+retrofit+rxbus+greendao一体的安卓项目快速开发架构

## 引入

### Step 1. 根目录build.gradle添加 jcenter仓库依赖

root gradle

```
    allprojects {
        repositories {
            ...
           jcenter()
        }
    }
```

### Step 2. 添加项目依赖

app gradle

```
	dependencies {
	       implementation 'com.ray:mvplib:0.0.3'
	}
```

[TOC]

## 一.项目主要第三方库

##### 1.Rx家族

######    java响应式编程[Rxjava ](https://github.com/ReactiveX/RxJava)

######    android响应式编程[RxAndroid](https://github.com/ReactiveX/RxAndroid)

######    事件总线[RxBus](https://github.com/AndroidKnife/RxBus)

######    View事件扩展[RxBinding ](https://github.com/JakeWharton/RxBinding)

######    权限检测[RxPermissions](https://github.com/tbruyelle/RxPermissions)

##### 2.控件绑定[ButterKnife](https://github.com/JakeWharton/butterknife)

##### 3.图片加载[Glide](https://github.com/bumptech/glide)

##### 4.网络框架[Retrofit](https://github.com/square/retrofit)

##### 5.数据解析[Gson](https://github.com/google/gson)

##### 6.数据库管理[GreenDao](https://github.com/greenrobot/greenDAO)

##### 7.图片拖拽放大[PhotoView](https://github.com/chrisbanes/PhotoView)

##### 8.各种动态加载中动画[SpinKit](https://github.com/ybq/Android-SpinKit)

##### 9.文件下载[filedownloader](https://github.com/lingochamp/FileDownloader)

##### 10.App检测更新下载[FastUpdate](https://github.com/Ray512512/FastUpdate)

##### 11.粗暴屏幕适配方案[rudeness](https://github.com/Firedamp/Rudeness)

##### 12.异常上报[Bugly](https://bugly.qq.com)+[CrashHandler](https://blog.csdn.net/jyp123123/article/details/62037974)

##### 13.图片选择器[MultiImageSelector](https://github.com/lovetuzitong/MultiImageSelector)

以上包含第三方库，是根据我自身日常开发常用到的一些功能，库中还有很多自定义View采用了down源码方式依赖到了库中，后续补充介绍。

具体每个第三方的使用方式见各自链接，此库中对部分库做了二次封装，后面会对常用功能进行使用介绍。

## 二.工具类 

（**以下可在使用到时查看**）

##### 1.AlertDialogUtil 

快速弹出应用不同场景下系统风格弹窗（弹窗内容不同组合）

##### 2.AnimaUtil

封装了部分常用动画，如缩放，旋转

##### 3.AppManager 

所有继承自BaseActivity的activity管理类，内部封装了一些基本管理方法：如退出App，获取栈顶Activity

##### 4.BitmapUtil

Bitmap简单处理工具类，目前包含处理圆形Bitmap，drawable 转换成bitmap

##### 5.DateUtils 

将当前时间转换为 “上午 9:20” 时间格式，一般结合刷新控件使用

##### 6.DensityUtils

dp、px、sp 等单位互相转换工具类

##### 7.DeviceUtil

获取设备信息工具类，如：apk版本，手机型号，操作系统信息、设备标识号、屏幕尺寸等

##### 8.FileUtils

文件管理类，常用场景：快速储存文本到本地文件、生成文件夹、压缩图片

##### 9.GlideUtils

Glide工具类，封装了Glide的一些常用方法，也方便统一管理，比如加统一的占位图等

##### 10.GsonUtil

Gson工具类，json字符串转对象，对象集合，包含方法如下：

##### 11.ImageUtil

查看大图工具类

##### 12.*L*(Log)

Android默认Log快速使用类

##### 13.NetUtils

检测当前网络连接状态，获取信号强度等

##### 14.SimpleTextWatcher

TextWatcher简单实现类

##### 15.SPUtils

SharedPreferences管理类，可快速储存基本类型以及序列化对象类型数据

##### 16.StringUtil

字符串工具类判断非空、集合数组字符串之间互转

##### 17.T（Toast)

Toast简单封装使用类

##### 18.TimeFormat

时间格式转换

##### 19.ValidatorUtil

正则校验

##### 20.ViewUtils

View处理类

##### 21.WebViewUtil

WebView快速参数设置

## 三.使用到的自定义View

##### 1.轮播控件[BannerLayout](https://github.com/dongjunkun/BannerLayout)

##### 2.RecycleView 简洁适配器 [BaseRecyclerViewAdapterHelper](https://github.com/CymChad/BaseRecyclerViewAdapterHelper)

##### 3.多状态按钮[StateButton](https://github.com/niniloveyou/StateButton)

##### 4.仿IOS开关 [UISwitchButton](https://blog.csdn.net/fangchao3652/article/details/44039275)

##### 5.具动画切换效果开关 [SmoothCheckBox](https://github.com/andyxialm/SmoothCheckBox)

##### 6.九宫格图片控件 [MultiImageView](https://blog.csdn.net/sw5131899/article/details/52838261) 

##### 7.流式布局 [FlowLayout](https://github.com/hongyangAndroid/FlowLayout)

##### 8.具有阻尼回弹效果的[OverScroView](https://github.com/CymChad/BaseRecyclerViewAdapterHelper)

##### 9.上拉刷新 [PullToRefreshLayout](https://github.com/jingchenUSTC/PullToRefreshAndLoad)

##### 10.ViewPager指示器[magicindicator](https://github.com/hackware1993/MagicIndicator)

##### 11.多类型RecyclerView支持器[MultiItem](https://github.com/free46000/MultiItem)



以上即为整个项目架构主要涵盖的内容，下面介绍主要功能用法

## 四.主要功能使用介绍

### 1. 基本架构mvp模型

该框架中mvp模式不同于传统mvp，属于变种mvp模式，封装了view层（接口）、presenter（业务）两层实现

##### a.BaseIView

~~~java
public interface BaseIView {
    void showLoading();
    void showNetWorkErrorView();
    void showErrorView();
    void showDataView();
    void showEmptyView();
    void addRxDestroy(Disposable d);//添加rx管理
}
~~~

BaseIView层封装了一些公共场景处理方法，具体实现场景继承该接口即可。

##### b.BasePresenter

~~~JAVA
public abstract class BasePresenter<I extends BaseIView> {
    private static final String TAG = "BasePresenter";
    protected I mView;//view接口
    protected Activity mContext;

    public BasePresenter(Activity mContext, I mView) {
        this.mContext = mContext;
        this.mView = mView;
    }

    public void onDetachView() { //销毁的时候
        if (mView != null) {
            mView = null;
        }
    }
}
~~~

BasePresenter层桥接了BaseIView层，activity只需实例化presenter层实现view层接口即可快速使用

### 2.基类使用介绍

针对一个app基本场景结合mvp模型封装了一些基类

##### a.BaseActivity （BaseFragment类似）

BaseActivity里封装了一个activity业务逻辑使用到的绝大部分场景，具体BaseActivity实现可参考源码，主要包含点如下：

1. 加入AppManager管理类，方便全局控制该Activity
2. 设置无ActionBar与保持竖屏风格
3. 创建RxJava观察者管理类在销毁时取消相关订阅
4. 注册与解除RxBus事件
5. 构造一个VaryViewHelper，快速的替换页面内容（网络请求错误，空白界面等）
6. BaseActivity作为一个抽象类，提取了业务逻辑几个流程作为抽象方法交于子类实现

**继承BaseActivity Demo演示**

~~~java
public class DemoActivity extends BaseActivity<DemoPresenter> implements DemoIView {

    @Override
    protected int inflateContentView() {
        return R.layout.activity_demo;
    }

    @Override
    protected void initPresenter() {
       mPresenter=new DemoPresenter(this,this);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        ButterKnife.bind(this);
    }

    @Override
    protected void initEvents() {

    }

    @Override
    public void onLoginSuccess() {

    }
}
~~~

##### b.BottomTabBaseActivity 

此类用于App主页满足底部四个tab点击切换使用时继承该类，然后在子类中配置图标和文本，具体实现可参考[BottomTabBaseActivity](https://github.com/ifmvo/MatthewBases) 

###### 以下是Demo中实现例子：

~~~java
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
~~~

##### c.CheckPermissionsActivity

快速权限检测基类Activity，当你需要在程序一开始执行app所有相关权限检测通过才能进入app时可继承该类

一般用于启动页继承该类进行权限快速检测，具体实现参考源码

**以下是Demo中启动页示例**

~~~java
public class SplashActivity extends CheckPermissionsActivity {
    /**
     * 获取需要检测的权限列表，返回null不执行任何权限检测直接回调startApp()
     * @return
     */
    @Override
    protected String[] getPermissions() {
        return null;
    }

    @Override
    protected void startApp() {
        new Handler().postDelayed(() -> {
            MainActivity.start(this);
            finish();
        }, 2000);
    }

    @Override
    protected int inflateContentView() {
        return R.layout.activity_splash;
    }

}
~~~

### 3.网络模块

网络模块自己针对RxJava+Retrofit进行了一次封装，此处只介绍如何使用，具体封装见源码

Demo中快速进行网络请求

~~~java
        Api.get().register().compose(RxHelper.handleResult()).
                subscribe(new RxSubscribe<DemoUser>(mContext,mView) {
                    @Override
                    public void _onNext(DemoUser demoUser) {
                        
                    }

                    @Override
                    public void _onError(String message) {

                    }
                });
~~~

### 4.Rx家族使用介绍

#### a.RxJava

#####     请求遇到异常重试

~~~java
//可配置重试最大次数与重试条件
api.register().retryWhen(RxHelper.retryWhen(MAX_RETRY_TIME, new RxInterface.reTryWhen(){
            @Override
            public boolean isRetry(Throwable throwable) {
                return throwable instanceof SocketTimeoutException;
            }
        })).compose(RxHelper.handleResult()).
                subscribe(new RxSubscribe<DemoUser>(mContext,mView) {
                    @Override
                    public void _onNext(DemoUser demoUser) {
                    }

                    @Override
                    public void _onError(String message) {

                    }
                });
~~~

#####     无限轮询请求

~~~java
//单位为秒  
RxManager.interval(10, time -> api.register().compose(RxHelper.handleResult()). 
                subscribe(new RxSubscribe<DemoUser>(mContext,mView) {
                    @Override
                    public void _onNext(DemoUser demoUser) {
                    }

                    @Override
                    public void _onError(String message) {

                    }
                }));
~~~

#####     有停止条件的轮询

~~~java
        TEST_NUM = 0;
        RxManager.interval(1, new RxInterface.intervalInterface2() {
            @Override
            public boolean isStop() {
                return TEST_NUM > 10;
            }

            @Override
            public void action(long time) {
                TEST_NUM = time; 
            }
        });
~~~

#####     延迟任务

~~~java
RxManager.delay(1, () -> {
            //do something
        });
~~~

#####     联合判断

~~~java
       /**
         * 提交按钮是否可点击
         */
        EditText name=new EditText(mContext),age=new EditText(mContext),job=new EditText(mContext);
        //采用skip(1)原因：跳过 一开始EditText无任何输入时的空值
        Observable<CharSequence> nameObservable = RxTextView.textChanges(name).skip(1);
        Observable<CharSequence> ageObservable = RxTextView.textChanges(age).skip(1);
        Observable<CharSequence> jobObservable = RxTextView.textChanges(job).skip(1);

        RxManager.combineLatest(new RxInterface.combineLatest() {
            @Override
            public boolean getResult() {
                boolean isUserNameValid = !TextUtils.isEmpty(name.getText()) ;
                boolean isUserAgeValid = !TextUtils.isEmpty(age.getText());
                boolean isUserJobValid = !TextUtils.isEmpty(job.getText()) ;
                return isUserNameValid && isUserAgeValid && isUserJobValid;
            }
            @Override
            public void action(boolean b) {
                Log.e(TAG, "提交按钮是否可点击： "+b);
            }
        },nameObservable,ageObservable,jobObservable);
~~~

#####     依次执行两个请求

~~~java
         /**
         * 第二个请求需使用第一个请求的结果
         */
        api.register().compose(RxHelper.handleResult()).doOnNext(o -> {
             //第一次请求成功
        }).flatMap(user -> api.register()).compose(RxHelper.handleResult()).
                subscribe(new RxSubscribe<DemoUser>(mContext,mView) {
                    @Override
                    public void _onNext(DemoUser demoUser) {
                        //第二次请求成功
                    }

                    @Override
                    public void _onError(String message) {

                    }
                });
~~~

#####     两个请求同时发送

~~~java
       /**
         * 实现较为简单的从（网络 + 本地）获取数据 & 统一展示
         * takeUntil 网络请求结束后就停止接收  保证使用最新网络数据
         */
 Observable<DemoUser> fromCache = Observable.create((ObservableOnSubscribe<DemoUser>) e -> {
      DemoUser cache = (DemoUser) SPUtils.get("",null);
         if (cache != null) {
             e.onNext(cache);
           } else {
            e.onComplete();
          }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
 Observable.merge(fromCache, api.register()).takeUntil( api.register()).subscribe(o -> {

   });  
~~~

#####     合并两个请求结果

~~~java
       /**
         * 实现较为复杂的合并2个网络请求数据 & 统一展示
         */
        Observable.zip(api.register(), api.register(), (userBaseModel, userBaseModel2) ->
                new DemoUser(userBaseModel.msg + userBaseModel2.msg)).observeOn(AndroidSchedulers.mainThread()) // 在主线程接收 & 处理数据
                .subscribe(combine_infro -> {
                    // 结合显示2个网络请求的数据结果
                    Log.d(TAG, "最终接收到的数据是：" + combine_infro);
                }, throwable -> System.out.println("失败"));
~~~

#####     组合本地数据与网络数据，本地有缓存可不访问网络

~~~java
//可控制是否强制获取网络数据
RxRetrofitCache.load(mContext,"user",api.register(),true).compose(RxHelper.handleResult()).
                subscribe(new RxSubscribe<DemoUser>(mContext,mView) {
                    @Override
                    public void _onNext(DemoUser demoUser) {
                    }

                    @Override
                    public void _onError(String message) {

                    }
                });
~~~

#####     联合判断

~~~java
       /**
         * 提交按钮是否可点击
         */
        EditText name=new EditText(mContext),age=new EditText(mContext),job=new EditText(mContext);
        //采用skip(1)原因：跳过 一开始EditText无任何输入时的空值
        Observable<CharSequence> nameObservable = RxTextView.textChanges(name).skip(1);
        Observable<CharSequence> ageObservable = RxTextView.textChanges(age).skip(1);
        Observable<CharSequence> jobObservable = RxTextView.textChanges(job).skip(1);

        RxManager.combineLatest(new RxInterface.combineLatest() {
            @Override
            public boolean getResult() {
                boolean isUserNameValid = !TextUtils.isEmpty(name.getText()) ;
                boolean isUserAgeValid = !TextUtils.isEmpty(age.getText());
                boolean isUserJobValid = !TextUtils.isEmpty(job.getText()) ;
                return isUserNameValid && isUserAgeValid && isUserJobValid;
            }
            @Override
            public void action(boolean b) {
                Log.e(TAG, "提交按钮是否可点击： "+b);
            }
        },nameObservable,ageObservable,jobObservable);
~~~
####  b.RxBinding

#####        防止按钮重复点击

~~~java
RxManager.clicks(name, 2000, () -> { //2s内只会响应一次click

       });
~~~

#####     点击的多次监听

Android是不能多次监听同一个点击事件。但利用`RxJava`的操作符，例如`publish`, `share`或`replay`可以实现 

~~~java
       /**
         * 点击了一个按钮在多个地方收到通知
         */
        Button button=new Button(mContext);
        Observable<Object> observable = RxView.clicks(button).share();
        CompositeDisposable compositeDisposable = new CompositeDisposable();

        Disposable disposable1 = observable.subscribe(o -> Log.d(TAG, "disposable1, receive: " + o.toString()));
        Disposable disposable2 = observable.subscribe(o -> Log.d(TAG, "disposable2, receive: " + o.toString()));

        compositeDisposable.add(disposable1);
        compositeDisposable.add(disposable2);
~~~

##### 自动搜索优化

~~~java
       /**
         * 间隔时间达到固定值才能响应事件
         */
        RxManager.autoSearch(name, 1000, () -> {

        });
~~~

##### 联合判断

~~~java
 EditText name=new EditText(mContext),age=new EditText(mContext),job=new EditText(mContext);
        //采用skip(1)原因：跳过 一开始EditText无任何输入时的空值
        Observable<CharSequence> nameObservable = RxTextView.textChanges(name).skip(1);
        Observable<CharSequence> ageObservable = RxTextView.textChanges(age).skip(1);
        Observable<CharSequence> jobObservable = RxTextView.textChanges(job).skip(1);

        RxManager.combineLatest(new RxInterface.combineLatest() {
            @Override
            public boolean getResult() {
                boolean isUserNameValid = !TextUtils.isEmpty(name.getText()) ;
                boolean isUserAgeValid = !TextUtils.isEmpty(age.getText());
                boolean isUserJobValid = !TextUtils.isEmpty(job.getText()) ;
                return isUserNameValid && isUserAgeValid && isUserJobValid;
            }
            @Override
            public void action(boolean b) {
                Log.e(TAG, "提交按钮是否可点击： "+b);
            }
        },nameObservable,ageObservable,jobObservable);
~~~

#### c.RxPermission

##### 快速判断单个或多个权限是否授权

~~~java
 RxPermissions rxPermissions = new RxPermissions(mContext);
        rxPermissions.request(Manifest.permission.CAMERA,Manifest.permission.READ_PHONE_STATE)
                .subscribe(granted -> {
                    if (granted) {
                        // I can control the camera now
                    } else {
                        // Oups permission denied
                    }
                });
~~~

#####  结合RxBinding实现点击功能按钮并检测权限

~~~java
RxView.clicks(mContext.findViewById(R.id.enableCamera)).compose(rxPermissions.ensure(Manifest.permission.CAMERA))
                .subscribe(granted -> {
                    // R.id.enableCamera has been clicked
                });
~~~

#####  权限检测流程控制，所有权限都返回授权结果

~~~java
 rxPermissions.requestEach(Manifest.permission.CAMERA, Manifest.permission.READ_PHONE_STATE)
                .subscribe(permission -> { // will emit 2 Permission objects
                    if (permission.granted) {
                        // `permission.name` is granted !
                    } else if (permission.shouldShowRequestPermissionRationale) {
                        // Denied permission without ask never again
                    } else {
                        // Denied permission with ask never again
                        // Need to go to the settings
                    }
                });
~~~

#####  权限检测流程控制，某一个权限又返回结果时

~~~java
rxPermissions.requestEachCombined(Manifest.permission.CAMERA,Manifest.permission.READ_PHONE_STATE)
                .subscribe(permission -> { // will emit 1 Permission object
                    if (permission.granted) {
                        // All permissions are granted !
                    } else if (permission.shouldShowRequestPermissionRationale){
                    // At least one denied permission without ask never again
                } else {
            // At least one denied permission with ask never again
            // Need to go to the settings
        }
    });
~~~

#### d.RxBus

注册与解除绑定都已在BaseActivity（BaseFragment）中完成，若在其他地方响应事件可自己实现注册与解绑

#####  发送主线程事件

~~~java
//发送
RxBus.get().post(Event.TAG.NEXT_POS, "test");

//接收处理
@Subscribe(tags = {@Tag(Event.TAG.NEXT_POS)})
    public void nextPos(String s) {
     //do nextPos
    }
~~~

##### 发送子线程事件

~~~java
//发送
RxBus.getIO().post(Event.TAG.START_CHECKSHELF, "test");

//接收处理
@Subscribe(tags = {@Tag(Event.TAG.START_CHECKSHELF)})
    public void startCheckShelf(String s) {
      //do 
    }
~~~

其他第三方或自定义控件使用请点击对应链接查看，此处不做介绍。



# License

```
Copyright 2018 ray512512

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

