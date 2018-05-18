package com.ray.projectframe;

import com.ray.library.BaseApplication;
import com.ray.library.common.Constant;
import com.ray.library.utils.SystemUtil;
import com.ray.library.greendao.MyDaoMaster;

/**
 * Created by Ray on 2017/5/16.
 * email：1452011874@qq.com
 */

public class FrameApplication extends BaseApplication {



    @Override
    public void onCreate() {
        super.onCreate();
        String processName = SystemUtil.getProcessName(android.os.Process.myPid());
        if(!getPackageName().equals(processName))return;
        MyDaoMaster.getInstance(this).init(Constant.DB_NAME);

    }
}
