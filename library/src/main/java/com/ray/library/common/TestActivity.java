package com.ray.library.common;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.jakewharton.rxbinding2.widget.RxTextView;
import com.ray.library.R;
import com.ray.library.R2;
import com.ray.library.rxjava.util.RxInterface;
import com.ray.library.rxjava.util.RxManager;
import com.ray.library.utils.L;
import com.ray.library.utils.T;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestActivity extends AppCompatActivity {
    private static final String TAG = "TestActivity";
    public static long TEST_NUM = 0;
    @BindView(R2.id.test_et1)
    EditText testEt1;
    @BindView(R2.id.test_et2)
    EditText testEt2;
    @BindView(R2.id.test_et3)
    EditText testEt3;
    @BindView(R2.id.test_btn_submit)
    Button testBtnSubmit;

    public static void start(Context context) {
        context.startActivity(new Intent(context, TestActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
        
//        testRxinterval();

        RxManager.combineLatest(new RxInterface.combineLatest() {
            @Override
            public boolean getResult() {
                boolean isUserNameValid = !TextUtils.isEmpty(testEt1.getText().toString().trim()) ;
                boolean isUserAgeValid = !TextUtils.isEmpty(testEt2.getText().toString().trim());
                boolean isUserJobValid = !TextUtils.isEmpty(testEt3.getText().toString().trim()) ;
                return isUserNameValid && isUserAgeValid && isUserJobValid;
            }
            @Override
            public void action(boolean b) {
                testBtnSubmit.setEnabled(b);
                Log.e(TAG, "提交按钮是否可点击： "+b);
            }
        }, RxTextView.textChanges(testEt1),RxTextView.textChanges(testEt2),RxTextView.textChanges(testEt3));

        RxManager.clicks(testBtnSubmit, 2, () -> {
            T.show("submit");
            L.v(TAG,"submit");
        });

        RxManager.autoSearch(testEt1, 2, () -> L.v(TAG,"autoSearch"));
    }

    private void testRxinterval() {
        TEST_NUM = 0;
        RxManager.interval(1, new RxInterface.intervalInterface2() {
            @Override
            public boolean isStop() {
                return TEST_NUM > 10;
            }

            @Override
            public void action(long time) {
                T.show("" + time);
                TEST_NUM = time;
                System.out.print("interval 2\t" + time);
            }
        });
    }
}
