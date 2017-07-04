package com.ray.projectframe.greendao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.ray.projectframe.gen.DaoMaster;
import com.ray.projectframe.gen.DaoSession;

/**
 * Created by ray on 2017/6/22.
 * emial:1452011874@qq.com
 */

public class MyDaoMaster {
    private static MyDaoMaster mMyDaoMaster;
    private Context mContext;

    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    public MyDaoMaster(Context mContext) {
        this.mContext = mContext;
    }

    public static MyDaoMaster getInstance(Context context){
        if(mMyDaoMaster==null){
            synchronized (MyDaoMaster.class){
                if(mMyDaoMaster==null){
                    mMyDaoMaster=new MyDaoMaster(context.getApplicationContext());
                }
            }
        }
        return mMyDaoMaster;
    }

    public void init(String Dbname){
        mHelper = new DaoMaster.DevOpenHelper(mContext, Dbname, null);
        db = mHelper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }


    public DaoSession getDaoSession() {
        return mDaoSession;
    }
    public SQLiteDatabase getDb() {
        return db;
    }
}
