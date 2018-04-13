package com.xueping.www.zhaoxueping.common.utils;

import java.util.regex.Pattern;

/**
 * 作者：Created by BarryDong on 2017/9/14.
 * 邮箱：barry.dong@tianyitechs.com
 */

public class Validator {

    /**
     * 正则表达式：验证密码
     */
    public static final String REGEX_PASSWORD = "^[a-zA-Z0-9]{6,20}$";

    /**
     * 正则表达式：验证手机号
     */
    public static final String REGEX_MOBILE = "^((13[0-9])|(15[^4,\\D])|(18[^4,\\D])|(17[^4,\\D]))\\d{8}$";
//    public static final String REGEX_MOBILE = "[1]\\d{10}(?!\\d)";

    /**
     * 校验密码
     *
     * @param password
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isPassword(String password) {
        return Pattern.matches(REGEX_PASSWORD, password);
    }

    /**
     * 校验手机号
     *
     * @param mobile
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isMobile(String mobile) {
        return Pattern.matches(REGEX_MOBILE, mobile);
    }


}
