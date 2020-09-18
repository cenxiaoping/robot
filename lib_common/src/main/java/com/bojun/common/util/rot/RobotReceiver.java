package com.bojun.common.util.rot;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;

/**
 * Created by ZJcan on 2017-12-04.
 */

public class RobotReceiver extends BroadcastReceiver {

    private static RobotReceiver instance;
    private RobotReceiverHandler mHandler;

    public static RobotReceiver getInstance(RobotReceiverHandler handler) {
        if (instance == null) {
            instance = new RobotReceiver(handler);
        }
        return instance;
    }

    private RobotReceiver(RobotReceiverHandler handler) {
        mHandler = handler;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
//        String action = intent.getAction();
//        if ("REEMAN_BROADCAST_WAKEUP".equals(action)) {
//            if (RobotReceiverHandler.runScene == RunSceneType.Video_Call) return;
//            RobotActionProvider.getInstance().setBeam(0);
//            SpeechPlugin.getInstance().startRecognize();
//            int angle = intent.getIntExtra("REEMAN_8MIC_WAY", 0);
//            sendMessage(mHandler, RobotIntentAction.ACTION_WAKE, 0, 0, angle);
//        } else if ("REEMAN_LAST_MOVTION".equals(action)) {
//            int type = intent.getIntExtra("REEMAN_MOVTION_TYPE", 0);
//            sendMessage(mHandler, RobotIntentAction.ACTION_LAST_MOVE, 0, 0, type);
//        } else if ("REEMAN_BROADCAST_SCRAMSTATE".equals(action)) {
//            int stopState = intent.getIntExtra("SCRAM_STATE", -1);
//            sendMessage(mHandler, RobotIntentAction.ACTION_SCRAMSTATE, 0, 0, stopState);
//        } else if ("android.net.conn.CONNECTIVITY_CHANGE".equals(action)) {
//            NetworkInfo info = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
//            sendMessage(mHandler, RobotIntentAction.ACTION_CONNECTIVITY_NET, 0, 0, info);
//        } else if ("ACTION_POWER_CONNECTE_REEMAN".equals(action)) {
//            // 插入充电器
//            int powcon = intent.getIntExtra("POWERCHARGE", 0);
//            sendMessage(mHandler, RobotIntentAction.ACTION_POWER_CONNECTE, 0, 0, powcon);
//        } else if (Intent.ACTION_BATTERY_CHANGED.equals(action)) {
//            int level = intent.getIntExtra("level", 0);
//            sendMessage(mHandler, RobotIntentAction.ACTION_BATTERY, 0, 0, level);
//        } else if ("AUTOCHARGE_ERROR_DOCKNOTFOUND".equals(action)) {
//            // 未找到充电桩
//            SpeechPlugin.getInstance().startSpeak("未找到充电桩");
//        } else if ("AUTOCHARGE_ERROR_DOCKINGFAILURE".equals(action)) {
//            // 连接充电桩失败
//            SpeechPlugin.getInstance().startSpeak("连接充电桩失败");
//        } else if (Intent.ACTION_TIME_TICK.equals(action)) {
//            int workTime;
//            if (TimeUtils.isInTime((String) SharedPreferencesUtil.getData("startWorkTime", "08:30"),
//                    (String) SharedPreferencesUtil.getData("endWorkTime", "19:00"))) {
//                workTime = 0;
//            } else {
//                workTime = 1;
//            }
//            sendMessage(mHandler, RobotIntentAction.ACTION_TIME_TICK, 0, 0, workTime);
//        }
    }

    private void sendMessage(Handler handler, int what, int arg0, int arg1,
                             Object obj) {
        if (handler != null) {
            Message msg = handler.obtainMessage(what, arg0, arg1, obj);
            msg.sendToTarget();
        }
    }
}
