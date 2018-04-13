package com.xueping.www.zhaoxueping;

import android.app.Activity;
import android.app.Application;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ImageView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bilibili.boxing.BoxingCrop;
import com.bilibili.boxing.BoxingMediaLoader;
import com.bilibili.boxing.loader.IBoxingCallback;
import com.bilibili.boxing.loader.IBoxingMediaLoader;
import com.squareup.leakcanary.LeakCanary;
import com.xueping.www.zhaoxueping.common.boxingutils.BoxingGlideLoader;
import com.xueping.www.zhaoxueping.common.boxingutils.BoxingUcrop;
import com.xueping.www.zhaoxueping.common.utils.FakeCrashLibrary;
import com.xueping.www.zhaoxueping.common.utils.ToastUtil;
import com.xueping.www.zhaoxueping.db.GreenDaoManager;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

/**
 * 作者：Created by BarryDong on 2017/7/19.
 * 邮箱：barry.dong@tianyitechs.com
 */

public class App extends Application {
    private static App app;
    public static final String DB_NAME = "Ethan.db";
    public static App getInstance() {
        if (app == null) {
            synchronized (App.class) {
                if (app == null) {
                    app = new App();
                }
            }
        }
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;

        //初始化单例Toast
        ToastUtil.init(this);
        //leakcanary监控内存泄露
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
        //全局配置，只有一个数据库管理类
//        GreenDaoManager.getInstance(this);

        initARouter();//arouter(Android页面路由框架)
        //Timber初始化
        regToTimber();
        //Boxing
        initBoxing();
    }

    //添加activity集合，退出APP时，循环清除
    private List<Activity> activities = new ArrayList<Activity>();
    public void addActivity(Activity activity) {
        activities.add(activity);
    }
    @Override
    public void onTerminate() {
        super.onTerminate();
        for (Activity activity : activities) {
            activity.finish();

        }
    }

    //初始化Boxing
    private void initBoxing() {
        IBoxingMediaLoader loader = new BoxingGlideLoader();
        BoxingMediaLoader.getInstance().init(loader);
        BoxingCrop.getInstance().init(new BoxingUcrop());
    }

    private void initARouter() {
        if (BuildConfig.DEBUG) {   // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this); // 尽可能早，推荐在Application中初始化
    }

    // 初始化 Timber
    private void regToTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree() {
                @Override
                protected String createStackElementTag(StackTraceElement element) {
                    // 打印行号, 线程名
                    return "[ " + Thread.currentThread().getName() + " ] " +
                            super.createStackElementTag(element) +
                            " (" + element.getLineNumber() + ")";
                }
            });
        } else {
            // release
            Timber.plant(new CrashReportingTree());
        }
    }

    /**
     * A tree which logs important information for crash reporting.
     */
    private static class CrashReportingTree extends Timber.Tree {
        @Override
        protected void log(int priority, String tag, String message, Throwable t) {
            if (priority == Log.VERBOSE || priority == Log.DEBUG) {
                return;
            }
            FakeCrashLibrary.log(priority, tag, message);
            if (t != null) {
                if (priority == Log.ERROR) {
                    FakeCrashLibrary.logError(t);
                } else if (priority == Log.WARN) {
                    FakeCrashLibrary.logWarning(t);
                }
            }
        }
    }
}
