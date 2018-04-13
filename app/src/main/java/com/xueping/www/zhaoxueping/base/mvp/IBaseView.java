package com.xueping.www.zhaoxueping.base.mvp;

import android.support.annotation.StringRes;

/**
 * 基础页面视图
 */
public interface IBaseView {
    /**
     * 弹消息
     *
     * @param message
     */
    void showMessage(String message);

    /**
     * 弹消息
     *
     * @param stringId
     */
    void showMessage(@StringRes int stringId);

    /**
     * 加载框
     *
     * @param message
     */
    void showLoadingDialog(String message);

    /**
     * 加载框
     *
     * @param stringId
     */
    void showLoadingDialog(@StringRes int stringId);

    /**
     * 隐藏对话框
     */
    void dismissLoadingDialog();

    /**
     * 单设备登录,强制踢出
     */
//    void logout();
}
