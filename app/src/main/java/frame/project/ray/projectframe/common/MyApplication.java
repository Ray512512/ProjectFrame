package frame.project.ray.projectframe.common;

import android.app.Application;

/**
 * Created by Ray on 2017/5/16.
 * email：1452011874@qq.com
 */

public class MyApplication extends Application {
    private static MyApplication instance;


    public static MyApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance=this;

    }
}
