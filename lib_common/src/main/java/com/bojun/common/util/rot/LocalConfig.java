package com.bojun.common.util.rot;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * Description: This's LocalConfigHandle
 * Time: 2018/7/6  15:04
 *
 * @author lishiting
 */
public class LocalConfig {
    private static String reemanurl = "http://www.rmbot.cn/index.php";
    /**
     * 记录地点下标
     */
    public static int locCount = 0;
    /**
     * 0 自动介绍 1 单个地点介绍 -1 无导航计划
     */
    public static int AutoIntroduce = -1;

    /**
     * 是否正在自动导航流程导航中
     */
    public static boolean isAutoNavRuning = false;

    /**
     * 默认自动充电电量
     */
    public static int AUTO_CHARGE = 20;

    /**
     * 是否在工作时间  -1无状态    0 在工作时间  1非工作时间
     */
    public static int isWorkTime = 0;

    /**
     * 是否充电时间
     */
//    public static boolean isChargeTime = false;

    /**
     * 是否自动返回
     */
    public static boolean isAutoBack = false;

    /**
     * 是否在播放界面界面
     */
    public static boolean isPlayVideo = false;

    /**
     * 是否在设置界面
     */
    public static boolean isSetting = false;

    /**
     * 导航地点
     */
    public static List<String> navLocation = null;

    /**
     * 间隔时间
     */
    public static int spaceTime = 5;



//    public static String RobotScene = SceneValue.SCENE_NORMAL;

    /**
     * 获取reeman url
     *
     * @param text
     * @return
     */
    public static String getReemanAPI(String text) {
        String url = null;
        try {
            url = LocalConfig.reemanurl + "/intermgr/Index/GetReemanAnswer?"
                    + "DeviceNum=" + Constants.ROBOT_ID + "&Question="
                    + URLEncoder.encode(text, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return url;
    }


}
