package com.bojun.common.util;

import android.os.SystemClock;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bojun.net.dto.KeyConstants;
import com.bojun.net.dto.RouteConstants;

/**
 * 简  述  快速点击跳转设置界面
 * 作  者  管流生
 * 包  名  com.example.lib_utils
 * 时  间  2020/8/28 0028 10:44
 */
 public class FastClickToActUtils {
    private static int COUNTS = 5;// 点击次数
    private static long[] mHits = new long[COUNTS];//记录点击次数
    private static long DURATION = 2000;//有效时间

    public static void  fastClick(View view){
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //将mHints数组内的所有元素左移一个位置
                System.arraycopy(mHits, 1, mHits, 0, mHits.length - 1);
                //获得当前系统已经启动的时间
                mHits[mHits.length - 1] = SystemClock.uptimeMillis();
                if (mHits[0] >= (SystemClock.uptimeMillis() - DURATION)) {
                    //初始化点击次数
                    mHits = new long[COUNTS];
                    ARouter.getInstance().build(RouteConstants.ROUTE_LOGIN_ACTIVITY).navigation();
                }
            }
        });
    }
}
