package com.xueping.www.zhaoxueping.common.utils;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.text.TextUtils;
import android.view.Window;

import com.githang.statusbar.StatusBarCompat;
import com.xueping.www.zhaoxueping.R;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.regex.Pattern;

/**
 * 作者：Created by BarryDong on 2017/9/6.
 * 邮箱：barry.dong@tianyitechs.com
 */

public class CommonUtils {


    /**
     * 判断是否为MIUI6以上
     */
    public static boolean isMIUI6Later() {
        try {
            Class<?> clz = Class.forName("android.os.SystemProperties");
            Method mtd = clz.getMethod("get", String.class);
            String val = (String) mtd.invoke(null, "ro.miui.ui.version.name");
            if (TextUtils.isEmpty(val)) {
                return false;
            }
            val = val.replaceAll("[vV]", "");
            if (TextUtils.isEmpty(val)) {
                return false;
            }
            int version = Integer.valueOf(val);
            return version >= 6;
        } catch (ClassNotFoundException e) {
            return false;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return false;
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            return false;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 判断是否Flyme4以上
     */
    public static boolean isFlyMe4Later() {
        return Build.FINGERPRINT.contains("Flyme_OS_4")
                || Build.VERSION.INCREMENTAL.contains("Flyme_OS_4")
                || Pattern.compile("Flyme OS [4|5]", Pattern.CASE_INSENSITIVE).matcher(Build.DISPLAY).find();
    }
    public static void setStatusBarColor(Activity activity) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M || CommonUtils.isMIUI6Later() || CommonUtils.isFlyMe4Later()) {
//            StatusBarCompat.setStatusBarColor(activity, Color.WHITE);
//        } else {
//            StatusBarCompat.setStatusBarColor(activity, Color.BLACK);
//        }
        StatusBarCompat.setStatusBarColor(activity, activity.getResources().getColor(R.color.color_theme));
    }

    public static void setStatusBarColor(Window window, boolean lightStatusBar) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M || CommonUtils.isMIUI6Later() || CommonUtils.isFlyMe4Later()) {
            StatusBarCompat.setStatusBarColor(window, Color.WHITE, lightStatusBar);
        } else if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT || Build.VERSION.SDK_INT == Build
                .VERSION_CODES.KITKAT_WATCH) {
            //4.4手机弹框带黑边
            StatusBarCompat.setStatusBarColor(window, Color.parseColor("#00000000"), lightStatusBar);
        } else {

            StatusBarCompat.setStatusBarColor(window, Color.BLACK, lightStatusBar);
        }
    }

}
