package com.bojun.common.util.rot;

import android.util.Log;

import com.alibaba.android.arouter.utils.TextUtils;
import com.reeman.nerves.RobotActionProvider;
import com.speech.processor.SpeechPlugin;

import java.util.Map;


/**
 * Created by ZJcan on 2017-12-05.
 */

public class NavigationManager {
    private static final String TAG = "NavigationManager";

    /**
     * 导航
     *
     * @param anst  导航的地址名称
     * @param speak 开始导航时的语音     null 不说话   空格 代表使用默认   有值 则是时语音内容
     */
    public static void goNavigation(String anst, String speak) {
        Log.d(TAG, "goNavigation() called with: anst = [" + anst + "], speak = [" + speak + "]");
        //获取配置文件中导航地点名称及坐标集合
        //主动获取机器 sdcard 的 location 中配置的导
        //航地点名称集合。配置文件位置 /sdcard/reeman/data/locations.cfg
        Map<String, String> locations = SpeechPlugin.getInstance().getContactLocations();
        String firstMacthlocation = "";
        int matchCount = 0;//匹配到的地点数量，超过一个就放弃匹配
        if (locations != null) {
            for (String key : locations.keySet()) {
                if (anst.contains(key) || key.contains(anst)) {
                    matchCount++;
                    if (matchCount == 1) {
                        firstMacthlocation = key;
                    }
                }
            }
        }

        if (matchCount == 1) {
            anst = firstMacthlocation;
        }

        String location = locations == null ? "" : locations.get(anst);
        if (TextUtils.isEmpty(location)) {
            SpeechPlugin.getInstance().startSpeak("不好意思，我还不知道" + anst + "在哪呢");
            return;
        }

        if (!anst.contains("重定位点")) {
            if (RobotReceiverHandler.scramState == 0) {
                if (anst.contains("充电站")) {
                    SpeechPlugin.getInstance().startSpeak("我的急停开关被打开了，不能自动充电了");
                } else {
                    SpeechPlugin.getInstance().startSpeak("我的急停开关被打开了，帮我关闭后，再让我带你过去吧");
                }
                return;
            }
            if (RobotReceiverHandler.uncharged != 0) {
                SpeechPlugin.getInstance().startSpeak("我在充电中，现在不能过去了");
                return;
            }

            if (locations == null) {
                SpeechPlugin.getInstance().startSpeak("我还没设置导航地址呢");
                return;
            }
        }

        if ("复位".equals(anst)) {
            SpeechPlugin.getInstance().startSpeak("这里没什么事，我先回前台了");
            anst = "前台";
        }

        RobotReceiverHandler.hasAutoGreet = false;
        String goal = "";
        if (anst.contains("充电站")) {
            goal = coordinate(location, 0);
            SpeechPlugin.getInstance().startSpeak("我这就去充电");
        } else if (anst.contains("重定位点")) {//用于纠正位置错乱问题
            goal = coordinate(location, 2);
            SpeechPlugin.getInstance().startSpeak(speak);
        } else if (speak == null) { // 机器人不需要说话
            goal = coordinate(location, 1);
        } else if (!speak.equals("")) {
            SpeechPlugin.getInstance().startSpeak(speak);
            goal = coordinate(location, 1);
        } else {
            SpeechPlugin.getInstance().startSpeak("好的，我这就带你过去" + goal);
            goal = coordinate(location, 1);
        }
        IntroduceManager.getInstance().cancelTimer();
        RobotReceiverHandler.runScene = RunSceneType.Navigation;//设置为导航状态
        RobotActionProvider.getInstance().sendRosCom(goal);
        SpeechPlugin.getInstance().setCurrentNavPoint(anst);
    }

    /**
     * @param site 地点
     * @param type 0 ：充电,1：导航, 2：重定位
     * @return
     */
    private static String coordinate(String site, int type) {
        Log.d(TAG, "coordinate() called with: site = [" + site + "], type = [" + type + "]");
        StringBuffer buffer = new StringBuffer();
        if (site == null) {
            return "";
        }
        String[] point = site.split(",");
        float x = Float.valueOf(point[0].replaceAll(" ", ""));
        float y = Float.valueOf(point[1].replaceAll(" ", ""));
        float yaw = Float.valueOf(point[2].replaceAll(" ", ""));

        switch (type) {
            case 0:
                buffer = new StringBuffer("goal:charge");
                break;

            case 1:
                buffer = new StringBuffer("goal:nav");
                break;

            case 2:
                buffer = new StringBuffer("nav:reloc");
                break;
        }

        buffer.append("[").append(x).append(",").append(y).append(",").append(yaw).append("]");
        return buffer.toString();
    }
}
