package com.xueping.www.zhaoxueping.db;


import android.content.Context;

import com.xueping.www.zhaoxueping.App;
import com.xueping.www.zhaoxueping.db.gen.DaoMaster;
import com.xueping.www.zhaoxueping.db.gen.DaoSession;

/**
 * 作者：Created by BarryDong on 2017/9/11.
 * 邮箱：barry.dong@tianyitechs.com
 */

public class GreenDaoManager {
    private Context context;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    private static GreenDaoManager mInstance;
    private DaoMaster.DevOpenHelper openHelper;

    public static GreenDaoManager getInstance(Context context) {
        if (mInstance == null) {
            //保证异步处理安全操作
            synchronized (GreenDaoManager.class) {
                if (mInstance == null) {
                    mInstance = new GreenDaoManager(context);
                }
            }
        }
        return mInstance;
    }

    public GreenDaoManager(Context context){
        this.context = context;
        if (openHelper==null) {
            openHelper = new DaoMaster.DevOpenHelper(context, App.getInstance().DB_NAME, null);
        }
        mDaoMaster = new DaoMaster(openHelper.getWritableDatabase());
        //mDaoMaster = new DaoMaster(openHelper.getEncryptedWritableDb(MY_PWD));//加密
        mDaoSession = mDaoMaster.newSession();
    }

    public DaoMaster getMaster() {
        return mDaoMaster;
    }

    public DaoSession getSession() {
        return mDaoSession;
    }

    public DaoSession getNewSession() {
        mDaoSession = mDaoMaster.newSession();
        return mDaoSession;
    }
}
