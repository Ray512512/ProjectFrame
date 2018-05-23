# ProjectFrame

集mvp+rxjava+retrofit+rxbus+greendao一体的安卓项目快速开发架构

## 引入

### Step 1. 根目录build.gradle添加 JitPack 仓库地址

root gradle

```
    allprojects {
        repositories {
            ...
            maven { url "https://jitpack.io" }
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

## 项目主要第三方库如下

1. Rx家族
   + java响应式编程[Rxjava ](https://github.com/ReactiveX/RxJava)
   + android响应式编程[RxAndroid](https://github.com/ReactiveX/RxAndroid)
   + 事件总线[RxBus](https://github.com/AndroidKnife/RxBus)
   + View事件扩展[RxBinding ](https://github.com/JakeWharton/RxBinding)
   + 权限检测[RxPermissions](https://github.com/tbruyelle/RxPermissions)
2. 控件绑定[ButterKnife](https://github.com/JakeWharton/butterknife)
3. 图片加载[Glide](https://github.com/bumptech/glide)
4. 网络框架[Retrofit](https://github.com/square/retrofit)
5. 数据解析[Gson](https://github.com/google/gson)
6. 数据库管理[GreenDao](https://github.com/greenrobot/greenDAO)
7. 图片拖拽放大[PhotoView](https://github.com/chrisbanes/PhotoView)
8. 各种动态加载中动画[SpinKit](https://github.com/ybq/Android-SpinKit)
9. 文件下载[filedownloader](https://github.com/lingochamp/FileDownloader)
10. App检测更新下载[FastUpdate](https://github.com/Ray512512/FastUpdate)
11. 粗暴屏幕适配方案[rudeness](https://github.com/Firedamp/Rudeness)
12. 异常上报[Bugly](https://bugly.qq.com)+[CrashHandler](https://blog.csdn.net/jyp123123/article/details/62037974)
13. 图片选择器[MultiImageSelector](https://github.com/lovetuzitong/MultiImageSelector)

以上包含第三方库，是根据我自身日常开发常用到的一些功能，库中还有很多自定义View采用了down源码方式依赖到了库中，后续补充介绍。

具体每个第三方的使用方式见各自链接，此库中对部分库做了二次封装，后面会对常用功能进行使用介绍。

## 工具类 

##### （以下可在使用到时查看）

1. AlertDialogUtil 快速弹出应用不同场景下系统风格弹窗（弹窗内容不同组合）

~~~java
AlertDialogUtil.AlertDialog(mContext, "弹窗内容", "确定", "取消", (dialog, which) -> {
                  //PositiveButton listener
                });
其他场景调用方式见 AlertDialogUtil 方法定义注释              

~~~

2.AnimaUtil 封装了部分常用动画，如缩放，旋转

~~~java
AnimaUtil.startAnima(v,new ScaleInAnimation()); //调用缩放动画
~~~

3.AppManager 所有继承自BaseActivity的activity管理类，内部封装了一些基本管理方法：如退出App，获取栈顶Activity

4.BitmapUtil Bitmap简单处理工具类，目前包含处理圆形Bitmap，drawable 转换成bitmap

~~~java
BitmapUtil.toRoundBitmap(bitmap);//普通Bitmap转圆形Bitmap  返回Bitmap
BitmapUtil.drawableToBitmap(drawable);//drawable 转换成bitmap  返回Bitmap
~~~



5.DateUtils 将当前时间转换为 “上午 9:20” 时间格式，一般结合刷新控件使用

~~~java
DateUtils.getTimestampString(System.currentTimeMillis())
~~~

6.DensityUtils dp、px、sp 等单位互相转换工具类

7.DeviceUtil 获取设备信息工具类，如：apk版本，手机型号，操作系统信息、设备标识号、屏幕尺寸等

8.FileUtils 文件管理类，常用场景：快速储存文本到本地文件、生成文件夹、压缩图片

9.GlideUtils Glide工具类，封装了Glide的一些常用方法，也方便统一管理，比如加统一的占位图等

10.GsonUtil Gson工具类，json字符串转对象，对象集合，包含方法如下：

~~~java
   public static <T> T parseJSON(String json, Class<T> clazz) {
        Gson gson = new Gson();
        Type listType = new TypeToken<T>(){}.getType();
        return gson.fromJson(json,listType);
    }

    public static <T> ArrayList<T> jsonToArrayList(String json, Class<T> clazz) {
        Type type = new TypeToken<ArrayList<JsonObject>>() {}.getType();
        ArrayList<JsonObject> jsonObjects = new Gson().fromJson(json, type);
        ArrayList<T> arrayList = new ArrayList<>();
        for (JsonObject jsonObject : jsonObjects)
        {
            arrayList.add(new Gson().fromJson(jsonObject, clazz));
        }
        return arrayList;
    }
~~~

11.ImageUtil 常看大图工具类

12.L  Android默认Log快速使用类

13.NetUtils 检测当前网络连接状态，获取信号强度等

14.SimpleTextWatcher TextWatcher简单实现类

15.SPUtils SharedPreferences管理类，可快速储存基本类型以及序列化对象类型数据

16.StringUtil 字符串工具类判断非空、集合数组字符串之间互转

17.T  Toast简单封装使用类

~~~java
 T.show("submit");
~~~

18.TimeFormat 时间格式转换

19.ValidatorUtil 正则校验

20.ViewUtils View处理类

21.WebViewUtil WebView快速参数设置

## 使用到的自定义View

1. 轮播控件[BannerLayout](https://github.com/dongjunkun/BannerLayout)
2. RecycleView 简洁适配器 [BaseRecyclerViewAdapterHelper](https://github.com/CymChad/BaseRecyclerViewAdapterHelper)

   3.多状态按钮[StateButton](https://github.com/niniloveyou/StateButton)

4. 仿IOS开关 [UISwitchButton](https://blog.csdn.net/fangchao3652/article/details/44039275)

5. 具动画切换效果开关 [SmoothCheckBox](https://github.com/andyxialm/SmoothCheckBox)

6. 九宫格图片控件 [MultiImageView](https://blog.csdn.net/sw5131899/article/details/52838261) 

7. 流式布局 [FlowLayout](https://github.com/hongyangAndroid/FlowLayout)

8. 具有阻尼回弹效果的[OverScroView](https://github.com/CymChad/BaseRecyclerViewAdapterHelper)

9. 上拉刷新 [PullToRefreshLayout](https://github.com/jingchenUSTC/PullToRefreshAndLoad)

10. ViewPager指示器[magicindicator](https://github.com/hackware1993/MagicIndicator)

11. 多类型RecyclerView支持器[MultiItem](https://github.com/free46000/MultiItem)

    

以上即为整个项目架构主要涵盖的内容，下面介绍主要功能用法

## 主要功能使用介绍

### 1. 基本架构mvp模型

该框架中mvp模式不同于传统mvp，属于变种mvp模式，封装了view层（接口）、presenter（业务）两层实现

- ##### BaseIView

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

+ ##### BasePresenter

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

+ ##### BaseActivity （BaseFragment类似）

BaseActivity里封装了一个activity业务逻辑使用到的绝大部分场景，具体BaseActivity实现可参考源码，主要包含点如下：

1. 加入AppManager管理类，方便全局控制该Activity
2. 设置无ActionBar与保持竖屏风格
3. 创建RxJava观察者管理类在销毁时取消相关订阅
4. 注册与解除RxBus事件
5. 构造一个VaryViewHelper，快速的替换页面内容（网络请求错误，空白界面等）
6. BaseActivity作为一个抽象类，提取了业务逻辑几个流程作为抽象方法交于子类实现

###### 继承BaseActivity Demo演示

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

+ ##### BottomTabBaseActivity 

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

+ ##### CheckPermissionsActivity

快速权限检测基类Activity，当你需要在程序一开始执行app所有相关权限检测通过才能进入app时可继承该类

一般用于启动页继承该类进行权限快速检测，具体实现参考源码

###### 以下是Demo中启动页示例

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

+ #### RxJava

  #####     1.请求遇到异常重试

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

  #####     2.无限轮询请求

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

  #####     3.有停止条件的轮询

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

  #####     4.延迟任务

  ~~~java
  RxManager.delay(1, () -> {
              //do something
          });
  ~~~

  #####     5.联合判断

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

  #####     6.依次执行两个请求

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

  #####     7.两个请求同时发送

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

  #####     8.合并两个请求结果

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

  #####     9.组合本地数据与网络数据，本地有缓存可不访问网络

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

  #####     10.联合判断

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

+ ####  RxBinding

#####        1.防止按钮重复点击

~~~java
RxManager.clicks(name, 2000, () -> { //2s内只会响应一次click

       });
~~~

#####     2.点击的多次监听

Android是不能多次监听同一个点击事件。但利用`RxJava`的操作符，例如`publish`, `share`或`replay`可以实现 

  