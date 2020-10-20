package cn.it.jd;

/**
 * @author 15827
 * @date 2020-09-29 8:30
 */

import android.util.Log;

public class MyLog {
    private static final String TAG = "JW";

    public static void log(Object obj) {
        Log.i(TAG, "msg:" + obj);
    }

    public static void print() {
        log("debug...");
    }

}