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

### 项目主要第三方库如下

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
10. ViewPager指示器[magicindicator](https://github.com/hackware1993/MagicIndicator)
11. 多类型RecyclerView支持器[MultiItem](https://github.com/free46000/MultiItem)
12. App检测更新下载[FastUpdate](https://github.com/Ray512512/FastUpdate)
13. 粗暴屏幕适配方案[rudeness](https://github.com/Firedamp/Rudeness)
14. 异常上报[Bugly](https://bugly.qq.com)+[CrashHandler](https://blog.csdn.net/jyp123123/article/details/62037974)
15. 图片选择器[MultiImageSelector](https://github.com/lovetuzitong/MultiImageSelector)
16. 多状态按钮[StateButton](https://github.com/niniloveyou/StateButton)

以上包含第三方库，是根据我自身日常开发常用到的一些功能，库中还有很多自定义View采用了down源码方式依赖到了库中，后续补充介绍。

具体每个第三方的使用方式见各自链接，此库中对部分库做了二次封装，后面会对常用功能进行使用介绍。

### 工具类

1. AlertDialogUtil 快速弹出系统风格弹窗

~~~
种类一：
AlertDialogUtil.AlertDialog(mContext, "弹窗内容", "确定", "取消", (dialog, which) -> {
                  //PositiveButton 回调
                });
种类二：

~~~



