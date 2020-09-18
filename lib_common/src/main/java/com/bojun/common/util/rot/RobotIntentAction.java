package com.bojun.common.util.rot;

/**
 * @author ZJcan
 * @date 2017-12-04
 */

public class RobotIntentAction {
    public static final int ACTION_WAKE = 0;//唤醒
    public static final int ACTION_LAST_MOVE = ACTION_WAKE + 1;//运动完成
    public static final int ACTION_SCRAMSTATE = ACTION_LAST_MOVE + 1;//急停开关
    public static final int ACTION_CONNECTIVITY_NET = ACTION_SCRAMSTATE + 1;//
    public static final int ACTION_POWER_CONNECTE = ACTION_CONNECTIVITY_NET + 1;//
    public static final int ACTION_BATTERY = ACTION_POWER_CONNECTE + 1;//
    public static final int ACTION_ERROR_DOCKNOTFOUND = ACTION_BATTERY + 1;//
    public static final int ACTION_ERROR_DOCKINGFAILURE = ACTION_ERROR_DOCKNOTFOUND + 1;//
    public static final int ACTION_D_3_STREAM = ACTION_ERROR_DOCKINGFAILURE + 1;//
    public static final int ACTION_LASER_STREAM = ACTION_D_3_STREAM + 1;//激光
    public static final int ACTION_READ_FACE = ACTION_LASER_STREAM + 1;//
    public static final int ACTION_TIME_TICK = ACTION_READ_FACE + 1;//
    public static final int ACTION_CHARGE_AUTO_BACK = ACTION_TIME_TICK + 1;//
}
