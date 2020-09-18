package com.bojun.common.util.rot;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Message;
import android.util.Log;

import com.bojun.common.BaseApplication;
import com.reeman.nerves.RobotActionProvider;
import com.speech.abstracts.IRecognizeListener;
import com.speech.abstracts.IResultProcessor;
import com.speech.abstracts.ISpeakListener;
import com.speech.processor.SpeechPlugin;

/**
 * @author xiaoqqq
 * @package com.ihealthcare.robot.util.init
 * @date on 2018/10/31 12:35
 * @describe 机器人相关的配置初始化
 */
public class ReemanConfig {
    private static final String TAG = "ReemanConfig";
    private static ReemanConfig instance = new ReemanConfig();
    private Context mContext;
    private static final String SPEECH_TIMEOUT = 1000 * 60 + "";//语音处理超时
    private static final String NET_TIMEOUT = 1000 * 60 + "";//网络超时

    private ReemanConfig() {
    }

    public static ReemanConfig getInstance() {
        return instance;
    }

    public void initReemanConfig(Context context) {
        mContext = context.getApplicationContext();
        // init ai
        SpeechPlugin.CreateInstance(context);
        // 设置机器 ID
        SpeechPlugin.getInstance().setDevID(RobotActionProvider.getInstance().getRobotID());

        // 1.识别状态回调
        IRecognizeListener recListener = BjRecognizeListener.getInstance();
        // 2.语义处理回调
        IResultProcessor resListener = new ReemanResultProcessor(context);
        SpeechPlugin.getInstance().setResultProcessor(resListener);
        // 3.合成回调
        ISpeakListener viewSpeakListener = new ApViewSpeakListener();
        // 4.设置 sdk 内部识别回调
        SpeechPlugin.getInstance().setRecognizeListener(recListener);
        //TODO 识别超时时间、网络超时时间需要进一步调试
        SpeechPlugin.getInstance().setRecogParams("zh_cn", null, null, SPEECH_TIMEOUT, NET_TIMEOUT,null);
        // 设置语料库优先级
        SpeechPlugin.getInstance().setRecognizeZonn("预检分诊|科室导航|预检分诊|医生排班|时间查询|科室介绍|医院介绍|精准分诊|" +
                "机器人介绍|博钧配置|商务机器人闲聊|医生介绍");
        initLocationConfig(context);
        initReceiver(context);
        Message message = new Message();
        message.what = RobotIntentAction.ACTION_SCRAMSTATE;
        message.obj = RobotActionProvider.getInstance().getScramState();
        mHandler.sendMessage(message);
    }

    private void initLocationConfig(Context context) {
//        int navTime = (int) SharedPreferencesUtil.getData("navTime", 5);
//        if (navTime == 5) {
//            SharedPreferencesUtil.putData("navTime", 5);
//        } else {
//            LocalConfig.spaceTime = navTime;
//        }
//        List<String> list = SharedPreferencesUtil.getListData("Locations", String.class);
//        if (list != null && list.size() > 0) {
//            if (!list.equals(SpeechPlugin.getInstance().getNavList())) {
//                SharedPreferencesUtil.delData("Location_Labels_Id");
//                SharedPreferencesUtil.putListData("Locations", SpeechPlugin.getInstance().getNavList());
//                Toast.makeText(context, "Locations.cfg文件已修改,请注意修改相关设置", Toast.LENGTH_SHORT).show();
//            } else {
//                LocalConfig.navLocation = SharedPreferencesUtil.getListData("Location_Labels", String.class);
//                if (LocalConfig.navLocation == null || LocalConfig.navLocation.size() == 0) {
//                    LocalConfig.navLocation = null;
//                }
//            }
//        } else {
//            SharedPreferencesUtil.putListData("Locations", SpeechPlugin.getInstance().getNavList());
//            LocalConfig.navLocation = null;
//            Toast.makeText(context, "Locations.cfg文件已修改,请注意修改相关设置", Toast.LENGTH_SHORT).show();
//        }
    }

    private RobotReceiverHandler mHandler;
    private RobotReceiver receiver;

    private void initReceiver(Context context) {
        mHandler = new RobotReceiverHandler();
        receiver = RobotReceiver.getInstance(mHandler);
        RobotHardSdk.initInstance(BaseApplication.getAppContext(), mHandler);
        IntentFilter filter = new IntentFilter();
        filter.addAction("REEMAN_BROADCAST_WAKEUP");
        filter.addAction("REEMAN_LAST_MOVTION");
        filter.addAction("REEMAN_BROADCAST_SCRAMSTATE");
        filter.addAction(Intent.ACTION_BATTERY_CHANGED);
        filter.addAction("ACTION_POWER_CONNECTE_REEMAN");
        filter.addAction("AUTOCHARGE_ERROR_DOCKNOTFOUND");
        filter.addAction("AUTOCHARGE_ERROR_DOCKINGFAILURE");
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        //每分钟电量变化
        filter.addAction(Intent.ACTION_TIME_TICK);
        context.registerReceiver(receiver, filter);
        Log.d(TAG, "-----------------initReceiver() called with: context = [" + context + "]-----------------");
    }

    /**
     * 释放
     */
    public void releaseAll() {
        Log.d(TAG, "---------------releaseAll() called----------------");
        SpeechPlugin speechPlugin = SpeechPlugin.getInstance();
        if (speechPlugin != null) {
            speechPlugin.stopRecognize();
            speechPlugin.stopSpeak();
            speechPlugin.setRecognizeListener(null);
            speechPlugin.setResultProcessor(null);
            speechPlugin.setViewSpeakListener(null);
            speechPlugin.onDestroy();
        }

        if (receiver != null) {
            try {
                mContext.unregisterReceiver(receiver);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
