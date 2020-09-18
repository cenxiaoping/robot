package com.bojun.common.util.rot;

import android.util.Log;

import com.speech.processor.SpeechPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static com.bojun.common.util.rot.RobotReceiverHandler.runScene;


public class IntroduceManager {
    public static final String MODE_ONE_WAY = "one_way";
    public static final String MODE_INTERACTIVE = "interactive";
    public static final String MODE_NULL = "";
    public static final String DIRECT = "direct";
    private final long ONE_WAY_PERIOD = 10 * 1000;
    //    private final long INTERACTIVE_PERIOD = 2 * 60 * 1000;
    private final long INTERACTIVE_PERIOD = 30 * 1000;
    private String mode = "";         // one_way 一遍走完   interactive 互动性介绍   direct 直接介绍指定的
    public static boolean isIntroduce = false;
    public static int nextPosition = 0;     // 当前参观的位置
    private List<String> navigationList = new ArrayList<>();     // 参观位置名称列表

    private IntroduceManager() {
    }

    private static class LazyHolder {
        private static final IntroduceManager MANAGER_INSTANCE = new IntroduceManager();
    }


//    private static IntroduceManager manager;
//
//    public static IntroduceManager getInstance() {
//        if (manager == null) {
//            synchronized (IntroduceManager.class) {
//                if (manager == null) {
//                    manager = new IntroduceManager();
//                }
//            }
//        }
//        return manager;
//    }

    public static IntroduceManager getInstance() {
        return LazyHolder.MANAGER_INSTANCE;
    }


    public void setMode(String mode) {
        if (mode == null) {
            mode = "";
        }
        this.mode = mode;
        if (mode.equals("")) {
            isIntroduce = false;
        } else {
            isIntroduce = true;
        }
        Log.d(IntroduceManager.class.getSimpleName(), "cyl--setMode--: 设置介绍模式  " + mode);
    }

    public String getMode() {
        return mode;
    }

    public void setNavigationList(List<String> navigationList) {
        if (navigationList == null) {
            navigationList = new ArrayList<>();
        }
        this.navigationList = navigationList;
    }

    public List<String> getNavigationList() {
        if (navigationList == null) {
            navigationList = new ArrayList<>();
        }
        return navigationList;
    }

    public boolean judgeIfSpeech() {
        Log.d(IntroduceManager.class.getSimpleName(), "cyl--setMode--: 设置是否回答问题  ");
        return !mode.equals(MODE_ONE_WAY);
    }

//    long lastStamp = 0;

    public void speakOver() {
        if (mode.equals("") && runScene != RunSceneType.Navigation) {
//            DLog.d("cyl--111");
            return;
        }
        if (!isIntroduce || runScene == RunSceneType.Navigation) {
            return;
        }

        if (mode.equals(MODE_ONE_WAY)) {
            return;
        }


//        long currStamp = System.currentTimeMillis();
//        if (currStamp - lastStamp < 60 * 1000) {
//            DLog.d("cyl--444: false");
//            return;
//        }
//        lastStamp = currStamp;


//        switch (IntroduceManager.mode) {
//            case MODE_ONE_WAY:
////                startTimer();
//                break;
//            case MODE_INTERACTIVE:
//                break;
//            case DIRECT:
//                break;
//            default:
//        }
        startTimer();
    }

    public void navigationEnd() {
        if (mode.equals("")) {
            Log.d(IntroduceManager.class.getSimpleName(), "cyl--444");
            return;
        }
        Log.d(IntroduceManager.class.getSimpleName(), "cyl--navigationEnd--: 导航结束");
        nextPosition++;     // 导航结束，地点index 加1

        startTimer();
    }


    private void startTimer() {
        cancelTimer();
        Log.d(IntroduceManager.class.getSimpleName(), "cyl--startTimer: ");
        timer = new Timer();
        timerTask = new MyTimerTask();
        switch (mode) {
            case MODE_ONE_WAY:
                timer.schedule(timerTask, ONE_WAY_PERIOD);
                break;
            case MODE_INTERACTIVE:
                timer.schedule(timerTask, INTERACTIVE_PERIOD);
                break;
            case DIRECT:
                timer.schedule(timerTask, INTERACTIVE_PERIOD);
                break;
            default:
//                timer.schedule(timerTask, 5 * 1000);
        }
    }

    public void cancelTimer() {
        Log.d(IntroduceManager.class.getSimpleName(), "cyl--cancelTimer: ");
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        if (timerTask != null) {
            timerTask.cancel();
            timerTask = null;
        }
    }

    private Timer timer;

    private MyTimerTask timerTask;

    private class MyTimerTask extends TimerTask {
        @Override
        public void run() {
            Log.d(IntroduceManager.class.getSimpleName(), "cyl--计时器--计时");
            if (nextPosition >= navigationList.size() && !mode.equals(DIRECT)) {
                Log.d(IntroduceManager.class.getSimpleName(), "cyl--run: 参观结束");
                NavigationManager.goNavigation("前台", "介绍已经结束，你再仔细参观，我先回前台去咯");
                mode = "";
                isIntroduce = false;
                return;
            }

            switch (mode) {
                case MODE_ONE_WAY:
                    if (nextPosition < navigationList.size()) {
                        Log.d(IntroduceManager.class.getSimpleName(), "cyl--run: 准备前往下一地点  " + navigationList.get(nextPosition));
                        SpeechPlugin.getInstance().onReemanTextUnderstand(navigationList.get(nextPosition));  // 请求导航操作
                    }
                    break;

                case MODE_INTERACTIVE:
                    if (nextPosition < navigationList.size()) {
                        Log.d(IntroduceManager.class.getSimpleName(), "cyl--run: 准备前往下一地点  " + navigationList.get(nextPosition));
                        SpeechPlugin.getInstance().onReemanTextUnderstand(navigationList.get(nextPosition));  // 请求导航操作
                    }
                    break;
                case DIRECT:
                    Log.d(IntroduceManager.class.getSimpleName(), "cyl--run: 准备回前台  ");
                    SpeechPlugin.getInstance().startSpeak("没什么事情的话，我就先回前台咯");
                    NavigationManager.goNavigation("前台", "");
                    mode = "";
                    break;
                default:
            }
        }
    }

//    class CustomSpeak implements ISpeakListener {
//
//        @Override
//        public void onSpeakOver(SpeechError speechError) {
//
//        }
//
//        @Override
//        public void onInterrupted() {
//
//        }
//
//        @Override
//        public void onSpeakBegin(String s) {
//
//        }
//    }

}
