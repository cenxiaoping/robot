package com.bojun.common.util.rot;

import android.app.Application;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;

import com.rsc.impl.OnROSListener;
import com.rsc.impl.RscServiceConnectionImpl;
import com.rsc.reemanclient.ConnectServer;
import com.synjones.idcard.IDCardInfo;
import com.synjones.idcard.OnIDListener;

/**
 * 简  述
 * 作  者  chenxiaoping
 * 包  名  com.bojun.common.util
 * 时  间  2020/9/14 10:26
 */
public class RobotHardSdk {
    private static final String TAG = "RobotHardSdk";
    private static RobotHardSdk sdk;
    private Application mContext;
    private RobotReceiverHandler mHandler;
    private ConnectServer cs;

    private RobotHardSdk(Application application, RobotReceiverHandler handler) {
        mContext = application;
        mHandler = handler;
        RscServiceConnectionImpl impl = new RscServiceConnectionImpl() {

            @Override
            public void onServiceConnected(int name) {
                super.onServiceConnected(name);
                if (name == ConnectServer.Connect_Pr_Id) {
                    cs.registerIDListener(Ilistener);
                }
            }

            @Override
            public void onServiceDisconnected(int name) {
                super.onServiceDisconnected(name);
            }
        };
        cs = ConnectServer.getInstance(mContext, impl);
        registerRos();
    }

    /**
     * 创建SDK实例
     *
     * @param application
     */
    public static void initInstance(Application application, RobotReceiverHandler handler) {
        if (sdk == null) {
            sdk = new RobotHardSdk(application, handler);
        }
    }

    /**
     * 获取SDK实例
     *
     * @return
     */
    public static RobotHardSdk getInstance() {
        if (sdk != null) {
            return sdk;
        } else {
            return null;
        }
    }

    public void release() {
        cs.registerIDListener(null);
        cs.registerROSListener(null);
        if (cs != null)
            cs.release();
        if (sdk != null)
            sdk = null;

    }


    /**
     * 身份证接口
     */
    private OnIDListener Ilistener = new OnIDListener.Stub() {

        @Override
        public void onResult(IDCardInfo info, byte[] photo)
                throws RemoteException {
        }
    };

    public void registerRos() {
        if (cs == null)
            return;
        cs.registerROSListener(mRosListener);
    }

    /**
     * ROS接口
     */
    private OnROSListener mRosListener = new OnROSListener() {
        @Override
        public void onResult(String result) {
//            if (result != null) {
//                if (result.startsWith("laser:[")) {//激光
//                    sendMessage(mHandler, RobotIntentAction.ACTION_LASER_STREAM, 0, 0, result);
//                } else if (result.startsWith("pt:[")) {
////                    sendMessage(mHandler, RobotIntentAction.ACTION_D_3_STREAM, 0, 0, result);
//                } else if (result.equals("wifi:rec")) {
//                } else if (result.startsWith("ip:")) {
////                    SpeechPlugin.getInstance().startSpeak(result);
//                    getLogger().i("RobotHeadSdk", "onResult--ip: " + result);
//                } else if (result.startsWith("move_status:")) {
//                    navigationUpdate(result);
//                } else if (result.equals("bat:reached")) {
//                    RobotReceiverHandler.runScene = RunSceneType.Auto_Charge_Navigation;
//                    SpeechPlugin.getInstance().startSpeak("到达充电区域，开始连接充电桩");
//                    SpeechPlugin.getInstance().setCurrentNavPoint("");
//                } else if (result.equals("sys:uwb:0")) {
//                } else if (result.startsWith("loc[")) {
//                    getLogger().w("roslistener", "收到位置信息回调：  " + result);
//                }
//            }

        }
    };

    private void sendMessage(Handler handler, int what, int arg0, int arg1,
                             Object obj) {
        if (handler != null) {
            Message msg = handler.obtainMessage(what, arg0, arg1, obj);
            msg.sendToTarget();
        }
    }

    /**
     * result :  move_status:x
     *
     * @param result x = ?  0 : 静止待命   1 : 上次目标失败，等待新的导航命令   2 : 上次目标完成，等待新的导航命令  
     *               3 : 移动中，正在前往目的地   4 : 前方障碍物   5 : 目的地被遮挡 6：用户取消导航 7：收到新的导航
     */
    private void navigationUpdate(String result) {
//        Log.d(TAG, "navigationUpdate() called with: result = [" + result + "]");
//        getLogger().v("RobotHardSdk", "navigationUpdate: " + result);
//        if (result == null)
//            return;
//        switch (result) {
//            case "move_status:2"://上次目标完成，等待新的导航命令 
//                RobotReceiverHandler.runScene = RunSceneType.Normal;
//                String mNavSite = SpeechPlugin.getInstance().getCurrentNavPoint();
//                StringBuilder tip = new StringBuilder();
//                if (mNavSite == null || "".equals(mNavSite)) {
//                    mNavSite = "目的地";
//                    tip.append(mNavSite).append("到了，请问还有什么需要帮忙的吗？");
//                    SpeechPlugin.getInstance().startSpeak(tip.toString());
//                    return;
//                }
//                if ("充电站".equals(mNavSite)) {
//                    LocalConfig.AutoIntroduce = -1;
//                    tip.append(mNavSite).append("到了，开始对接充电桩");
//                    SpeechPlugin.getInstance().startSpeak(tip.toString());
//                    return;
//                }
//                if (mNavSite.contains("前台")) {
//                    RobotActionProvider.getInstance().combinedActionTtyS4(3);
//                    SpeechPlugin.getInstance().startSpeak("这里就是前台了");
//                    return;
//                }
//
//                if (IntroduceManager.isIntroduce) {      // 到达导航地点，介绍产品
////                Logr.i(RobotHardSdk.class.getSimpleName(), "cyl--isIntroduce: " + IntroduceManager.isIntroduce);
////                SpeechPlugin.getInstance().onReemanTextUnderstand(mNavSite + "的介绍词");
////                IntroduceManager.getInstance().navigationEnd();
//                    return;
//                }
//                int autoIntroduce = LocalConfig.AutoIntroduce;
//
//                if (autoIntroduce == 0) { //执行自动介绍流程
//                    getLogger().d("执行自动介绍流程");
//                    LocalConfig.isAutoNavRuning = false;
//                    LocalConfig.locCount = LocalConfig.navLocation.indexOf(mNavSite);
//                    EventBus.getDefault().post("", "setNextLocation");
//                    final String finalMNavSite1 = mNavSite;
//                    ReemanAPITools.doHttp(ReemanAPITools.Http_Get, LocalConfig.getReemanAPI(mNavSite), "", s -> {
//                        if (TextUtils.isEmpty(s)) {
//                            return;
//                        }
//                        JSONObject jsonObject = null;
//                        try {
//                            jsonObject = new JSONObject(s);
//                            getLogger().d(finalMNavSite1, jsonObject.toString());
//                            String answer = jsonObject.optString("Data");
//
//                            if (answer.contains("json_")) {
//                                String jsonString = answer.replace("json_", "").trim();
//                                LocalConfig.reemanCorpusModel = JsonUtils.deserialize(jsonString, ReemanCorpusModel.class);
//                                if (LocalConfig.reemanCorpusModel == null) {
//                                    ToastUtil.showShort(mContext, "数据为空");
//                                    return;
//                                }
//                                String introduce = LocalConfig.reemanCorpusModel.getIntroduce();
//                                if (!TextUtils.isEmpty(introduce)) {
//                                    SpeechPlugin.getInstance().startSpeak(introduce, new ISpeakListener() {
//                                        @Override
//                                        public void onSpeakOver(SpeechError speechError) {
//                                            LocalConfig.isAutoNavRuning = true;
//                                            //TODO 关闭图片显示
//                                            ViewControlModel viewControlModel = new ViewControlModel();
//                                            viewControlModel.setType(1);
//                                            EventBus.getDefault().post(viewControlModel, "ViewControl");
//                                            SpeechPlugin.getInstance().startRecognize();
//                                            String videoUrl = LocalConfig.reemanCorpusModel.getVideoUrl();
//                                            if (!TextUtils.isEmpty(videoUrl)) {
//                                                //TODO 显示视频
//                                                VideoPlay.playVideo(videoUrl.replace("\\", "/"));
//                                            } else {
//                                                navEnd();
//                                            }
//                                        }
//
//                                        @Override
//                                        public void onInterrupted() {
//
//                                        }
//
//                                        @Override
//                                        public void onSpeakBegin(String s) {
//                                            SpeechPlugin.getInstance().stopRecognize();
//                                        }
//                                    });
//                                }
//
//                                int action = LocalConfig.reemanCorpusModel.getAction();
//                                RobotActionProvider.getInstance().combinedActionTtyS4(action);
//                                String imgUrl = LocalConfig.reemanCorpusModel.getImageUrl();
//                                if (!TextUtils.isEmpty(imgUrl)) {
//                                    //TODO 显示图片
//                                    ViewControlModel viewControlModel = new ViewControlModel();
//                                    viewControlModel.setType(0);
//                                    viewControlModel.setUrl(imgUrl.replace("\\", "/"));
//                                    EventBus.getDefault().post(viewControlModel, "ViewControl");
//                                }
//                                if (LocalConfig.reemanCorpusModel.getTips() != null && LocalConfig.reemanCorpusModel.getTips().size() > 0) {
//                                    EventBus.getDefault().post(LocalConfig.reemanCorpusModel.getTips(), "getTips");
//                                } else {
//                                    EventBus.getDefault().post("", "getTips");
//                                }
//                                return;
//                            }
//                            //TODO 姿态控制？？？
//                            RobotActionProvider.getInstance().combinedActionTtyS4(3);
//                            SpeechPlugin.getInstance().startSpeak(answer, new ISpeakListener() {
//                                @Override
//                                public void onSpeakOver(SpeechError speechError) {
//                                    LocalConfig.isAutoNavRuning = true;
//                                    navEnd();
//                                    SpeechPlugin.getInstance().startRecognize();
//                                }
//
//                                @Override
//                                public void onInterrupted() {
//
//                                }
//
//                                @Override
//                                public void onSpeakBegin(String s) {
//                                    SpeechPlugin.getInstance().stopRecognize();
//                                }
//                            });
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                    });
//                    return;
//                }
//
//                if (autoIntroduce == 1) { // 单个地点介绍
//                    getLogger().d("执行固定介绍流程");
//                    LocalConfig.AutoIntroduce = -1;
//                    final String finalMNavSite = mNavSite;
//                    ReemanAPITools.doHttp(ReemanAPITools.Http_Get, LocalConfig.getReemanAPI(mNavSite), "", new IReemanApiCallback() {
//                        @Override
//                        public void onResult(String s) {
//                            if (!TextUtils.isEmpty(s)) {
//                                JSONObject jsonObject = null;
//                                try {
//                                    jsonObject = new JSONObject(s);
//                                    getLogger().d(finalMNavSite, jsonObject.toString());
//                                    String answer = jsonObject.optString("Data");
//                                    if (answer.contains("json_")) {
//                                        String jsonString = answer.replace("json_", "").trim();
//                                        LocalConfig.reemanCorpusModel = JsonUtils.deserialize(jsonString, ReemanCorpusModel.class);
//                                        if (LocalConfig.reemanCorpusModel == null) {
//                                            ToastUtil.showShort(mContext, "数据为空");
//                                        } else {
//                                            String introduce = LocalConfig.reemanCorpusModel.getIntroduce();
//                                            if (!TextUtils.isEmpty(introduce)) {
//                                                SpeechPlugin.getInstance().startSpeak(introduce, new ISpeakListener() {
//                                                    @Override
//                                                    public void onSpeakOver(SpeechError speechError) {
//                                                        //TODO 关闭图片显示
//                                                        ViewControlModel viewControlModel = new ViewControlModel();
//                                                        viewControlModel.setType(1);
//                                                        EventBus.getDefault().post(viewControlModel, "ViewControl");
//                                                        SpeechPlugin.getInstance().startRecognize();
//                                                        String videoUrl = LocalConfig.reemanCorpusModel.getVideoUrl();
//                                                        if (!TextUtils.isEmpty(videoUrl)) {
//                                                            //TODO 显示视频
//                                                            VideoPlay.playVideo(videoUrl.replace("\\", "/"));
//                                                        }
//                                                    }
//
//                                                    @Override
//                                                    public void onInterrupted() {
//
//                                                    }
//
//                                                    @Override
//                                                    public void onSpeakBegin(String s) {
//                                                        SpeechPlugin.getInstance().stopRecognize();
//                                                    }
//                                                });
//                                            }
//                                            int action = LocalConfig.reemanCorpusModel.getAction();
//                                            RobotActionProvider.getInstance().combinedActionTtyS4(action);
//                                            String imgUrl = LocalConfig.reemanCorpusModel.getImageUrl();
//                                            if (!TextUtils.isEmpty(imgUrl)) {
//                                                //TODO 显示图片
//                                                ViewControlModel viewControlModel = new ViewControlModel();
//                                                viewControlModel.setType(0);
//                                                viewControlModel.setUrl(imgUrl.replace("\\", "/"));
//                                                EventBus.getDefault().post(viewControlModel, "ViewControl");
//                                            }
//                                            if (LocalConfig.reemanCorpusModel.getTips() != null && LocalConfig.reemanCorpusModel.getTips().size() > 0) {
//                                                EventBus.getDefault().post(LocalConfig.reemanCorpusModel.getTips(), "getTips");
//                                            } else {
//                                                EventBus.getDefault().post("", "getTips");
//                                            }
//                                        }
//                                    } else {
//                                        RobotActionProvider.getInstance().combinedActionTtyS4(32);
//                                        SpeechPlugin.getInstance().startSpeak(answer, new ISpeakListener() {
//                                            @Override
//                                            public void onSpeakOver(SpeechError speechError) {
//                                                LocalConfig.isAutoNavRuning = false;
//                                                SpeechPlugin.getInstance().startRecognize();
//                                            }
//
//                                            @Override
//                                            public void onInterrupted() {
//
//                                            }
//
//                                            @Override
//                                            public void onSpeakBegin(String s) {
//                                                SpeechPlugin.getInstance().stopRecognize();
//                                            }
//                                        });
//                                    }
//                                } catch (JSONException e) {
//                                    e.printStackTrace();
//                                }
//
//                            }
//                        }
//                    });
//                    return;
//                }
//                getLogger().d("固定地点流程");
//                LocalConfig.AutoIntroduce = -1;
//                RobotActionProvider.getInstance().combinedActionTtyS4(3);
//                SpeechPlugin.getInstance().startSpeak("这里是" + mNavSite);
//
//                break;
//            case "move_status:5"://目的地被遮挡
//                Log.e(TAG, "navigationUpdate: 目的地被遮挡");
//                break;
//            case "move_status:4": // 前面有障碍物
//                Log.e(TAG, "navigationUpdate: 前面有障碍物");
//                break;
//            case "move_status:1":// 上次目标失败，等待新的导航命令
//                Log.e(TAG, "navigationUpdate: 上次目标失败，等待新的导航命令");
//                RobotReceiverHandler.runScene = RunSceneType.Normal;
//                LocalConfig.isAutoNavRuning = false;
//                // LocalConfig.isNavRuning = false;
//                SpeechPlugin.getInstance().startSpeak("导航失败");
//                break;
//            case "move_status:6": // 用户主动取消导航
//                Log.e(TAG, "navigationUpdate: 用户主动取消导航");
//                LocalConfig.isAutoNavRuning = false;
//                RobotReceiverHandler.runScene = RunSceneType.Normal;
//                break;
//            case "move_status:7": // 收到新的目标点
//                Log.e(TAG, "navigationUpdate: 收到新的目标点");
//                LocalConfig.isAutoNavRuning = false;
//                break;
//            case "move_status:3"://3 正在导航中
//                Log.d(TAG, "navigationUpdate: 正在导航中");
//                LocalConfig.isAutoNavRuning = false;
//                break;
//        }
    }


    /**
     * 导航结束请求语料
     */
    private void navEnd() {
//        if (LocalConfig.locCount + 1 < LocalConfig.navLocation.size()) {
//            EventBus.getDefault().post(LocalConfig.navLocation
//                    .get(LocalConfig.locCount + 1), "setNextLocation");
//            startCountDownTimer();
//        } else {
//            LocalConfig.AutoIntroduce = -1;
//            EventBus.getDefault().post("", "reStartCountDownTimer");
//            EventBus.getDefault().post("", "setNextLocation");
////            SpeechPlugin.getInstance().startSpeak("展厅介绍完毕");
//            SpeechPlugin.getInstance().onReemanTextUnderstand("introduce_end");
//        }
    }

    private void requestNavEnd() {
//        ReemanAPITools.doHttp(ReemanAPITools.Http_Get,
//                LocalConfig.getReemanAPI(LocalConfig.navLocation.get(LocalConfig.locCount) + "_end"),
//                "", new IReemanApiCallback() {
//                    @Override
//                    public void onResult(String s) {
//                        if (!TextUtils.isEmpty(s)) {
//                            JSONObject jsonEnd = null;
//                            try {
//                                jsonEnd = new JSONObject(s);
//                                getLogger().d(LocalConfig.navLocation.get(LocalConfig.locCount) + "_end", jsonEnd.toString());
//                                String answerEnd = jsonEnd.optString("Data");
//                                SpeechPlugin.getInstance().startSpeak(answerEnd, new ISpeakListener() {
//                                    @Override
//                                    public void onSpeakOver(SpeechError speechError) {
//                                        LocalConfig.isAutoNavRuning = true;
//                                        NavigationManager.goNavigation(LocalConfig.navLocation
//                                                .get(LocalConfig.locCount + 1), "");
//                                        SpeechPlugin.getInstance().startRecognize();
//                                    }
//
//                                    @Override
//                                    public void onInterrupted() {
//
//                                    }
//
//                                    @Override
//                                    public void onSpeakBegin(String s) {
//                                        SpeechPlugin.getInstance().stopRecognize();
//                                    }
//                                });
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }
//                });
    }

    private static CountDownTimer countDownTimer = null;

    public void startCountDownTimer() {
        if (countDownTimer != null) {
            cancelCountDownTimer();
        }
//        countDownTimer = new CountDownTimer(1000 * LocalConfig.spaceTime, 1000) {
//
//            @Override
//            public void onTick(long millisUntilFinished) {
//                getLogger().d("CountDownTimer", millisUntilFinished / 1000 + "S");
//                NavCountDown navCountDown = new NavCountDown();
//                navCountDown.setShow(1);//显示view
//                navCountDown.setCountDownTimer((int) (millisUntilFinished / 1000));
//                EventBus.getDefault().post(navCountDown, "setNavCountDown");
//            }
//
//            @Override
//            public void onFinish() {
//                getLogger().d("CountDownTimer", "结束");
//                if (SpeechPlugin.getInstance().isSpeaking()) {
//                    reStartCountDownTimer();
//                } else {
//                    requestNavEnd();
//                }
//                NavCountDown navCountDown = new NavCountDown();
//                navCountDown.setShow(0);//view 消失
//                EventBus.getDefault().post(navCountDown, "setNavCountDown");
//
//            }
//        }.start();// 调用CountDownTimer对象的start()方法开始倒计时，也不涉及到线程处理
    }

    public void reStartCountDownTimer() {
        cancelCountDownTimer();
        startCountDownTimer();
    }

    public void cancelCountDownTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer = null;
//            NavCountDown navCountDown = new NavCountDown();
//            navCountDown.setShow(0);//view 消失
//            EventBus.getDefault().post(navCountDown, "setNavCountDown");
        }
    }

}