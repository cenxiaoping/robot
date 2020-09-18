package com.example.lib_utils;

/**
 * 简  述  防止快速点击
 * 作  者  管流生
 * 包  名  com.bojun.utils
 * 时  间  2019/9/10 0010 8:31
 */
public class FastClickUtils {

    private static final int MIN_DELAY_TIME = 1000;  // 两次点击间隔不能少于1000ms
    private static long lastClickTime;

    public static boolean isFastClick() {
        boolean flag = true;
        long currentClickTime = System.currentTimeMillis();
        if ((currentClickTime - lastClickTime) >= MIN_DELAY_TIME) {
            flag = false;
        }
        lastClickTime = currentClickTime;
        return flag;
    }
}
