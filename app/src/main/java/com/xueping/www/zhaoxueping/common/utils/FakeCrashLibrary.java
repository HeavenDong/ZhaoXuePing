package com.xueping.www.zhaoxueping.common.utils;

import android.util.Log;


/**
 * 出现崩溃异常回调
 */
public class FakeCrashLibrary {

    public static void log(int priority, String tag, String message) {
        // TODO add log entry to circular buffer.
        Log.e(tag, message);
    }

    public static void logWarning(Throwable t) {
        // TODO report non-fatal warning.
        Log.e("", "", t);
    }

    public static void logError(Throwable t) {
        // TODO report non-fatal error.
        Log.e("", "", t);
    }

    private FakeCrashLibrary() {
        throw new AssertionError("No instances.");
    }
}
