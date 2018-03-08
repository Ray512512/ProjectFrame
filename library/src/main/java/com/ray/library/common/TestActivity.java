package com.ray.library.common;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ray.library.R;
import com.ray.library.rxjava.util.RxInterface;
import com.ray.library.rxjava.util.RxManager;
import com.ray.library.utils.T;

public class TestActivity extends AppCompatActivity {
    public static  long TEST_NUM = 0;

    public static void start(Context context){
        context.startActivity(new Intent(context,TestActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        testRxinterval();
    }

    private void testRxinterval(){
        TEST_NUM =0;
        RxManager.interval(1, new RxInterface.intervalInterface2() {
            @Override
            public boolean isStop() {
                return TEST_NUM>10;
            }

            @Override
            public void action(long time) {
                T.show(""+time);
                TEST_NUM=time;
                System.out.print("interval 2\t"+time);
            }
        });
    }
}
