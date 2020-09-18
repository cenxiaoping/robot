package com.bojun.common;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.text.TextUtils;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bojun.common.fragmentation.fragmentation.Fragmentation;
import com.bojun.common.fragmentation.fragmentation.helper.ExceptionHandler;
import com.bojun.common.serivce.BedsInformationServiceImpl;
import com.bojun.common.util.rot.Constants;
import com.bojun.common.util.CrashHandler;
import com.bojun.floating.ViewFloat;
import com.bojun.net.BuildConfig;
import com.bojun.net.RetrofitManager;
import com.bojun.net.config.API;
import com.bojun.net.user.LoginDTO;
import com.demons.alivelibrary.DaemonEnv;
import com.example.lib_utils.LogPrintUtil;
import com.example.lib_utils.SpUtil;
import com.facebook.stetho.Stetho;
import com.google.gson.Gson;
import com.reeman.nerves.RobotActionProvider;
import com.speech.processor.SpeechPlugin;

import java.lang.ref.WeakReference;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


/**
 * 初始化应用程序
 */
public class BaseApplication extends Application {
    private static BaseApplication mApplication;
    private static WeakReference<Activity> currentActivity;

    public static BaseApplication getAppContext() {
        return mApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        LogPrintUtil.init(BuildConfig.IS_DEBUG);
        ViewFloat.init(this, BuildConfig.IS_DEBUG);
//        initCrashHandler();//异常捕捉初始化
        Stetho.initializeWithDefaults(this);
        if (BuildConfig.IS_DEBUG) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this); // 尽可能早，推荐在Application中初始化
        API.getInstance().init(getApplicationContext());
        RetrofitManager.init(getApplicationContext());
//        Realm.init(this);
//        initRobot();
        Fragmentation.builder()
                // 设置 栈视图 模式为 （默认）悬浮球模式   SHAKE: 摇一摇唤出  NONE：隐藏， 仅在Debug环境生效
                .stackViewMode(Fragmentation.BUBBLE)
                .debug(true) // 实际场景建议.debug(BuildConfig.DEBUG)
                /**
                 * 可以获取到{@link me.yokeyword.fragmentation.exception.AfterSaveStateTransactionWarning}
                 * 在遇到After onSaveInstanceState时，不会抛出异常，会回调到下面的ExceptionHandler
                 */
                .handleException(new ExceptionHandler() {
                    @Override
                    public void onException(Exception e) {
                        // 以Bugtags为例子: 把捕获到的 Exception 传到 Bugtags 后台。
                        // Bugtags.sendException(e);
                    }
                })
                .install();

        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(@NonNull Activity activity) {

            }

            @Override
            public void onActivityResumed(@NonNull Activity activity) {

                if (currentActivity == null) {
                    currentActivity = new WeakReference<>(activity);
                } else {
                    currentActivity.clear();
                    ;
                    currentActivity = new WeakReference<>(activity);
                }

            }

            @Override
            public void onActivityPaused(@NonNull Activity activity) {

            }

            @Override
            public void onActivityStopped(@NonNull Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(@NonNull Activity activity) {

            }
        });
        DaemonEnv.initialize(this, BedsInformationServiceImpl.class, DaemonEnv.DEFAULT_WAKE_UP_INTERVAL);
    }

    private void initRobot() {
        // 初始化语音
        //说明:"586b9487" 为讯飞平台申请的 id、Constants.ROBOT_ID 为锐曼平台申请的id。id 要和 so 文件相对应
        SpeechPlugin.CreateSpeechUtility(this, "586b9487", Constants.ROBOT_ID);
        // 初始拾音方向
        if (RobotActionProvider.getInstance() != null) {
            RobotActionProvider.getInstance().setBeam(0);
        }
    }

    public static BaseApplication getInstance() {
        return mApplication;
    }

    private void initCrashHandler() {
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());
    }

    /**
     * 保存登录信息
     *
     * @param userInfo
     */
    public void putUserInfo(LoginDTO userInfo) {
        if (userInfo != null) {
            String s = new Gson().toJson(userInfo);
            SpUtil.putString(this, "UserInfo", s);
        } else {
            SpUtil.putString(this, "UserInfo", "");
        }
    }

    /**
     * 获取登录信息
     *
     * @return
     */
    public LoginDTO getUserInfo() {
        String userInfo = SpUtil.getString(this, "UserInfo");
        if (!TextUtils.isEmpty(userInfo)) {
            return new Gson().fromJson(userInfo, LoginDTO.class);
        }
        return new LoginDTO();
    }

    public static Activity getCurrentActivity() {
        if (currentActivity != null) {
            return currentActivity.get();
        } else {
            return null;
        }
    }
}
