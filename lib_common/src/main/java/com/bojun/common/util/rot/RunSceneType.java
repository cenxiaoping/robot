package com.bojun.common.util.rot;

/**
 * Created by ZJcan on 2017-12-04.
 */

public class RunSceneType {
    public static final int Normal = 0;
    public static final int Navigation = Normal + 1; // 导航
    public static final int Auto_Charge_Navigation = Navigation + 1; // 自动充电最后对接过程
    public static final int Video_Call = Auto_Charge_Navigation + 1; // 视频通话
}
