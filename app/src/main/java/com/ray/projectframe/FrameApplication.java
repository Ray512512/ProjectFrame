package com.ray.projectframe;

import com.ray.library.BaseApplication;
import com.ray.library.common.Constant;
import com.ray.projectframe.greendao.MyDaoMaster;

/**
 * Created by Ray on 2017/5/16.
 * email：1452011874@qq.com
 */

public class FrameApplication extends BaseApplication {



    @Override
    public void onCreate() {
        super.onCreate();
        MyDaoMaster.getInstance(this).init(Constant.DB_NAME);
    }
}
