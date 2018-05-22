package com.ray.projectframe;

import com.ray.library.BaseApplication;
import com.ray.library.common.CrashHandler;
import com.ray.library.utils.SystemUtil;
import com.ray.library.greendao.MyDaoMaster;
import com.ray.projectframe.common.Const;

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
        CrashHandler.getInstance().init(this,true);
        MyDaoMaster.getInstance(this).init(Const.DB_NAME);
    }
}
