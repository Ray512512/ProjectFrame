package com.example.library;

import com.ray.library.rxjava.util.RxInterface;
import com.ray.library.rxjava.util.RxManager;

import org.junit.Test;

/**
 * Created by Ray on 2018/3/7.
 */

public class RxJavaTest {
    private static final String TAG = "RxJavaTest";

    @Test
    public  void testInterval(){
        RxManager.interval(1, time -> {
            RxManager.test_num = time;
            System.out.print("interval 1\t"+time);
        });
        RxManager.interval(1, new RxInterface.intervalInterface2() {
            @Override
            public boolean isStop() {
                return RxManager.test_num>10;
            }

            @Override
            public void action(long time) {
                System.out.print("interval 2\t"+time);
            }
        });
    }
}
