package com.ray.library.rxjava.util;

/**
 * Created by Ray on 2018/3/7.
 * RX回调接口
 */

public class RxInterface {

    public  interface simple {
        void action();
    }

    //无条件轮训
    public  interface intervalInterface1 {
         void action(long time);
    }

  //延时执行
    public interface delayed{
        void action();
    }

    //有条件轮训
    public  interface intervalInterface2{
         boolean isStop();
         void action(long time);
    }

    //联合判断
    public  interface combineLatest {
        boolean getResult();
        void action(boolean b);
    }
}
