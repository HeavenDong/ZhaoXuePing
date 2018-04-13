package com.xueping.www.zhaoxueping.common.utils;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.StringRes;
import android.widget.Toast;


/**
 * 单例Toast
 */
public class ToastUtil {

    private static Toast mToast;

    private static Context mContext;

    public static void init(Context context) {//在Application初始化一下
        if (null == context) {
            return;
        }
        mContext = context.getApplicationContext();
    }

    public static void showToast(String text) {
        if (null == mContext) {
            return;
        }
        if (mToast == null) {
            mToast = Toast.makeText(mContext, text, Toast.LENGTH_SHORT);
        }
        mToast.setText(text);
        mToast.show();
    }

    public static void showToast(@StringRes int resId)
            throws Resources.NotFoundException {
        if (null == mContext) {
            return;
        }
        if (mToast == null) {
            mToast = Toast.makeText(mContext, resId, Toast.LENGTH_SHORT);
        }
        mToast.setText(resId);
        mToast.show();
    }

}
