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

    

以上即为整个项目架构主要涵盖的内容，后续补充主要功能用法。

