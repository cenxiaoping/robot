package com.bojun.common.util.rot;

import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.bojun.common.serivce.KeyConstants;
import com.reeman.nerves.RobotActionProvider;
import com.speech.processor.SpeechPlugin;


/**
 * @author ZJcan
 * @date 2017-12-04
 * 处理机器人指令
 */

public class RobotReceiverHandler extends Handler {
    private static final String TAG = "RobotReceiverHandler";
    private boolean isFirst = true;
    /**
     * 0 急停打开
     */
    public static int scramState = 0;
    public static int runScene = 0;
    private boolean currentWakeup = false;

    /**
     * 0 未充电
     */
    public static int uncharged;
    /**
     * 电量
     */
    private static int level;

    private boolean hasPerson = false;
    private boolean hasLaser = false;
    private long scramDownTime = 0;
    private long scramOpenTime = 0;
    private String faceTemp = null;

    public RobotReceiverHandler() {

    }

    @Override
    public void handleMessage(Message msg) {
        Log.d(TAG, "handleMessage() called with: msg.what = ------------->[" + msg.what + "]");
        super.handleMessage(msg);
        int what = msg.what;
        switch (what) {
            case RobotIntentAction.ACTION_WAKE:
                int angle = (int) msg.obj;
                handleWake(angle);
                break;
            case RobotIntentAction.ACTION_LAST_MOVE:
                int type = (int) msg.obj;
                handleLastMove(type);
                break;
            case RobotIntentAction.ACTION_SCRAMSTATE://急停
//                int stopState = (int) msg.obj;
//                scramState = stopState;
//                if (stopState == 0) {
//                    SpeechPlugin.getInstance().startSpeak("急停开关被打开");
//                    EventBus.getDefault().post("", "setNextLocation");
//                    ViewControlModel viewControlModel = new ViewControlModel();
//                    viewControlModel.setType(1);
//                    EventBus.getDefault().post(viewControlModel, "ViewControl");
//                    SpeechPlugin.getInstance().setCurrentNavPoint("");
//                }
//                if (runScene == RunSceneType.Navigation) {
//                    RobotActionProvider.getInstance().sendRosCom("cancel_goal");
//                } else if (runScene == RunSceneType.Auto_Charge_Navigation) {
//                    RobotActionProvider.getInstance().sendRosCom("bat:uncharge");
//                }
//                runScene = RunSceneType.Normal;
                break;
            case RobotIntentAction.ACTION_CONNECTIVITY_NET:
                NetworkInfo info = (NetworkInfo) msg.obj;
                if (info.getState().equals(NetworkInfo.State.DISCONNECTED)) {
//                    Toast.makeText(BjApplication.getAppContext(), "网络断开连接，请检查网络！", Toast.LENGTH_LONG).show();
                    SpeechPlugin.getInstance().startSpeak("网络断开连接，请检查网络！");
                } else if (info.getState().equals(NetworkInfo.State.CONNECTED)) {
                    SpeechPlugin.getInstance().startSpeak("网络已连接！");
                    Log.d(TAG, "handleMessage:  ----------------NetworkInfo.State.CONNECTED-------------- ");
                }
                break;
            case RobotIntentAction.ACTION_BATTERY:
                int l = (int) msg.obj;
                level = l;
//                if (l < 20 && (uncharged == 0)) {
//                    if (LocalConfig.AutoIntroduce != -1 || SpeechPlugin.getInstance().isSpeaking()) {
//                        return;
//                    }
//                    if (runScene == RunSceneType.Normal) {
//                        // 自动充电
//                        NavigationManager.goNavigation("充电站", "电量低我先去充电了");
//                    }
//                } else if (l < LocalConfig.AUTO_CHARGE && (uncharged == 0)) {
//                    if (LocalConfig.AutoIntroduce != -1 || SpeechPlugin.getInstance().isSpeaking()) {
//                        return;
//                    }
//                    if (runScene == RunSceneType.Normal) {
//                        // 自动充电
//                        NavigationManager.goNavigation("充电站", "低于电量最低值，我先去去充电了");
//                    }
//                }
                break;
            case RobotIntentAction.ACTION_POWER_CONNECTE:
                int powcon = (int) msg.obj;
                uncharged = powcon;
                if (powcon == 0) {
                    // 拔掉充电器
                    // SpeechPlugin.getInstance().startSpeak("已断开充电器，停止充电");
                } else if (powcon == 1) {
                    SpeechPlugin.getInstance().startSpeak("连接电源适配器成功，开始充电");
                } else if (powcon == 2) {
                    try {
                        SpeechPlugin.getInstance().startSpeak("连接充电桩成功，开始充电");
                        //当定位失败后可以采用对接一下充电桩的方式使导航重置
                        //当充电桩位置与重定位点位置相同时才能这样使导航重置有效
                        SpeechPlugin speechPlugin = SpeechPlugin.getInstance();
//                        if (speechPlugin.getContactLocations().get("充电站").equals(speechPlugin.getContactLocations().get("重定位点"))) {
//                            NavigationManager.goNavigation("重定位点", "");
//                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case RobotIntentAction.ACTION_LASER_STREAM:
                if (isFirst) {
                    isFirst = false;
                }
                String laser = msg.obj.toString();
                handleLaserStream(laser);
                break;
            case RobotIntentAction.ACTION_READ_FACE:
                String facename = msg.obj.toString();
                handleReadFace(facename);
                break;
            case RobotIntentAction.ACTION_TIME_TICK:
//                LocalConfig.isWorkTime = (int) msg.obj;
//                String flag = LocalConfig.isWorkTime == 0 ? "是" : "否";
//                getLogger().d("是否工作时间=====" + flag);
//                if (LocalConfig.isWorkTime == 0) {
//                    if (level > LocalConfig.AUTO_CHARGE + 20) {
//                        SpeechPlugin speechPlugin = SpeechPlugin.getInstance();
//                        if (LocalConfig.AutoIntroduce != -1 || speechPlugin != null && speechPlugin.isSpeaking()) {
//                            return;
//                        }
//                        if (!LocalConfig.isAutoBack) {
//                            return;
//                        }
//                        if (TextUtils.isEmpty(SpeechPlugin.getInstance().getCurrentNavPoint()) || !SpeechPlugin.getInstance().getCurrentNavPoint().equals("前台")) {
//                            if ((uncharged == 2) && (runScene == RunSceneType.Auto_Charge_Navigation)) {
//                                // 电量高于目标值 自动返回前台  在上班时间
//                                RobotActionProvider.getInstance().sendRosCom("bat:uncharge");
//                                Message message = new Message();
//                                message.what = RobotIntentAction.ACTION_CHARGE_AUTO_BACK;
//                                sendMessageDelayed(message, 2500);
//                            } else if (uncharged == 0 && (runScene == RunSceneType.Normal)) {
//                                if ("前台".equals(SpeechPlugin.getInstance().getCurrentNavPoint())) {
//                                    return;
//                                }
//                                NavigationManager.goNavigation("前台", "我先回前台了，现在是工作时间");
//                            }
//                        }
//                    }
//                } else {
//                    if (LocalConfig.AutoIntroduce != -1 || SpeechPlugin.getInstance().isSpeaking()) {
//                        return;
//                    }
//                    if (runScene == RunSceneType.Normal && (uncharged == 0)) {
//                        //非工作时间,自动充电
//                        NavigationManager.goNavigation("充电站", "非工作时间我就先去充电了");
//                    }
//                }
                break;
            case RobotIntentAction.ACTION_CHARGE_AUTO_BACK:
//                runScene = RunSceneType.Normal;
//                RobotActionProvider.getInstance().moveFront(20, 0);
//                NavigationManager.goNavigation("前台", "我先回前台了，现在是工作时间");
                break;
            default:
                break;
        }

    }


    /**
     * 按下去和打开的时间间隔   大于10S   发重启位
     */
    private void locScramState(int state) {
        //按下 0  打开  1
        if (state == 0) {
            scramDownTime = System.currentTimeMillis();
        } else if (state == 1) {
            scramOpenTime = System.currentTimeMillis();
            if ((scramOpenTime - scramDownTime) > 1000 * 10) {
                //执行重定位
                RobotActionProvider.getInstance().sendRosCom("nav:reloc");
            }
        }

    }

    private void handleWake(int angle) {
        hasAutoGreet = false;
        if (scramState == 0) {
            SpeechPlugin.getInstance().startSpeak("你好，" + KeyConstants.RONAME + "在呢");
            return;
        }
        if (uncharged == 2) {
            if (level > 20) {
                RobotActionProvider.getInstance().moveFront(20, 0);
                Message msg = new Message();
                msg.what = RobotIntentAction.ACTION_WAKE;
                msg.obj = angle;
                sendMessageDelayed(msg, 2500);
                return;
            } else {
                SpeechPlugin.getInstance().startSpeak("你好，" + KeyConstants.RONAME + "正在充电中");
                return;
            }
        } else if (uncharged == 1) {
            SpeechPlugin.getInstance().startSpeak("你好，" + KeyConstants.RONAME + "正在充电中");
            return;
        }

//        if (runScene == RunSceneType.Navigation) {
//            RobotActionProvider.getInstance().sendRosCom("cancel_goal");
//            turnByAngle(angle);
//        } else if (runScene == RunSceneType.Auto_Charge_Navigation) {
//            RobotActionProvider.getInstance().sendRosCom("bat:uncharge");
//            Message msg = new Message();
//            msg.what = RobotIntentAction.ACTION_WAKE;
//            msg.obj = angle;
//            sendMessageDelayed(msg, 2500);
//        } else {
//            turnByAngle(angle);
//        }
//        runScene = RunSceneType.Normal;
    }

    private void turnByAngle(int angle) {
        if (10 <= angle && angle < 180) {
            currentWakeup = true;
            RobotActionProvider.getInstance().moveLeft(angle, 0);
        } else if (180 <= angle && angle <= 350) {
            currentWakeup = true;
            RobotActionProvider.getInstance().moveRight(360 - angle, 0);
        } else {
            SpeechPlugin.getInstance().startSpeak("你好，" + KeyConstants.RONAME + "在呢");
        }
    }

    private void handleLastMove(int type) {
        if (currentWakeup) {
            if (type == 16 || type == 17 || type == 18) {
                SpeechPlugin.getInstance().startSpeak("你好，" + KeyConstants.RONAME + "在呢");
                currentWakeup = false;
            }
        }
    }


    private void handleLaserStream(String laser) {
         Log.d(TAG, "handleLaserStream() called with:----------------》 laser = [" + laser + "]");
        if (currentWakeup) {
            return;
        }
//        if (runScene != RunSceneType.Normal) {
//            return;
//        }
        // laser:[1.12] 单位米
        if (laser.length() < 7) {
            return;
        }
        String buf = laser.substring(7, laser.length() - 1);
        try {
            float ll = Float.valueOf(buf);
            if (scramState != 0 && uncharged == 0) {
                if (hasAutoGreet) {
                    if (ll < 0.8 || ll > 2.2) {//TODO 这个逻辑值得探究
                        RobotActionProvider.getInstance().stopMove();
                    }
                }
            }

//            if (ll > 2.2) {
//                if (hasAutoGreet && "前台".equals(SpeechPlugin.getInstance().getCurrentNavPoint())) {
//                    if (System.currentTimeMillis() - autoGreetTime > 2 * 60 * 1000) {
//                        hasAutoGreet = false;
//                        NavigationManager.goNavigation("复位", "");
//                    }
//                }
//                if (hasLaser) {
//                    faceTemp = null;
////                    mViewController.addReceptionView();
//                    hasLaser = false;
//                }
//            } else if (ll > 0 && ll <= 2.2) {
//                if (!hasLaser) {
//                    hasLaser = true;
//                    autoGreet(ll);
//                }
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean hasAutoGreet = false;
    private long autoGreetTime = 0;


    /**
     * 自动上前打招呼
     *
     * @param ll
     */
    private void autoGreet(float ll) {
        Log.d(TAG, "autoGreet() called with:------------------- ll = [" + ll + "]");
        if (scramState == 0) {
            return;
        }
        if (uncharged != 0) {
            return;
        }
//        if (IntroduceManager.isIntroduce || RobotReceiverHandler.runScene == RunSceneType.Navigation) {
//            // 处于介绍模式 或 正在导航状态
//            return;
//        }

        //  RobotActionProvider.getInstance().moveFront(Math.round(dell), 0);
        SpeechPlugin.getInstance().startSpeak("你好，" + "请问有什么需要帮助？");
        hasAutoGreet = true;
        autoGreetTime = System.currentTimeMillis();

    }


    private void handleReadFace(String facename) {
        Log.d(TAG, "handleReadFace() called with: facename = [" + facename + "]");
        if (facename.equals(faceTemp)) {
            return;
        }
        faceTemp = facename;
        RobotActionProvider.getInstance().combinedActionTtyS4(34);
    }

}
