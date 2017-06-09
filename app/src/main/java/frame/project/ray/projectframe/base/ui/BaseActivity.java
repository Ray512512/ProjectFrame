package frame.project.ray.projectframe.base.ui;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import butterknife.ButterKnife;
import frame.project.ray.projectframe.retrofit.ApiManager;
import frame.project.ray.projectframe.retrofit.ApiService;
import frame.project.ray.projectframe.utils.AppManager;


/**
 * Created by caism on 2017/4/13.
 */

public abstract class BaseActivity extends AppCompatActivity {
    public Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        AppManager.getInstance(mContext).addActivity(this);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setMainLayout();
        ButterKnife.bind(this);
        initBeforeData();
        initEvents();
        initAfterData();
    }


    /*
        * 初始化布局
        */
    protected abstract void setMainLayout();

    /**
     * 初始化先前数据
     */
    protected abstract void initBeforeData();

    /**
     * 初始化事件
     */
    protected abstract void initEvents();

    /**
     * 初始化之后数据
     */
    protected abstract void initAfterData();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getInstance(mContext).killActivity(this);
    }

}
