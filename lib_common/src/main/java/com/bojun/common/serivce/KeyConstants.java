package com.bojun.common.serivce;

import android.os.Environment;

import java.io.File;

/**
 * 全局的静态变量
 */
public class KeyConstants {
    public static final String loginActivityIsExist = "loginActivityIsExist";//登录界面是否存在
    public static final String LODIN_STR = "加载数据中";
    public static final String SP_INFO = "sp_bedside_card";
    public static final String SBSN = "sbsn";
    public static final String HANDLESBSN = "handleSbsn";
    public static final String REQUEST = "request";
    public static final String STATUS = "status";
    public static final String ID = "id";
    public static final String divisionId = "divisionId";
    public static final String FeeNo = "FeeNo";
    public static final String SERVER_ADDRESS = "server_address";
    public static final String IP_NUMBER = "ip_number";
    public static final String PORT_NUMBER = "port_number";
    public static final String BED_NUMBER = "bed_number";
    public static final String BED_DATA = "bed_data";
    public static final String DAY_TO_NIGHT_TIME = "day_to_night_time";
    public static final String NIGHT_TO_DAY_TIME = "night_to_day_time";
    public static final String ModeStatus = "ModeStatus";
    public static final String NAME = "name";
    public static final String TOKEN = "token";
    public static final String CODE = "code";
    public static final String websocketUrl = "websocketUrl";
    public static final String htmlUrl = "htmlUrl";
    public static final String rtcUrl = "rtcUrl";
    public static final String wsToken = "wsToken";
    public static final String Information_Detail = "Information_Detail";
    public static final String STATION_ID = "station_id";
    public static final String HOSPITAL_ID = "hospitalid";
    public static final String EMPOLYEE_ID = "empolyeeId";
    public static final String HOSPITAL_NAME = "hospitalname";
    public static final String KSMC = "ksmc";
    public static final String REFRESH = "refresh";//刷新数据
    public static final String MSG = "msg";
    public static final String REMINDTYPE = "remindType";
    public static final String REMINDCONTENT = "remindContent";
    public static final String XH = "xh";
    public static final String HZ_QR_CODE = "HZ";
    public static final String CW_QR_CODE = "CW";
    public static final String KEY_PAGE_INDEX = "key_page_index";
    public static final String HUSHI_QR_CODE = "HUSHI";
    public static final String HOSPITAL_BED_LIST = "hospital_bed_list";
    public static final String HOSPITAL_BED = "hospital_bed";
    public static final String STATIONID = "stationId";
    public static final String CURRENT_PATIENT_INFO = "current_patient_info";
    public static final String CABIN_INFO = "cabin_info";
    public static final String CABIN_SHIFT_INFO = "cabin_shift_info";
    public static final String CABIN_SHIFT_INFO_MORE = "cabin_shift_info_more";
    public static final String TITLE = "title";
    public static final String USER = "user";
    public static final String FEENO = "feeno";
    public static final String WEBSOCKET_MSG = "websocket_msg";
    public static final String NURSELEVEL = "nurselevel";
    public static final String TRTTNUM = "trttnum";
    public static final String SHIFT_INFO = "shift_Info";
    public static final String COMMON = "common";
    public static final String TYPE = "type";
    public static final String ZLXH = "zlxh";
    public static final String IMAGE = "image";
    public static final String DOC = "doc";
    public static final String DOCX = "docx";
    public static final String XLS = "xls";
    public static final String XLSX = "xlsx";
    public static final String VIDEO = "video";
    public static final String VIDEO_URL = "video_url";
    public static final String PDF_URL = "pdf_url";
    public static final String PPT = "ppt";
    public static final String PPTX = "pptx";
    public static final String PDF = "pdf";
    public static final String MP4 = "mp4";
    public static final String INDEX = "INDEX";
    public static final String TREATMENT_PATIENTS = "treatment_patients";
    public static final int REQUEST_CODE = 201;
    public static final String USER_LIST = "user_list";
    public static final String ACTIVITY_TITLE = "activity_title";
    public static final String CONTEXT_TITLE = "context_title";
    public static final String CONTEXT_DATA = "context_data";
    public static final String CONTEXT = "context";
    public static final String BRIGHTNESS = "brightness";
    public static final String File_Path = Environment.getExternalStorageDirectory() + File.separator + "/CTK/file/";
    public static final String NEED_RTC = "need_rtc";


    public static final String SOCKET_TYPE = "socket_type";
    public static final String SOCKET_MAP  = "socket_map";
    //推送消息类型
    public static final  int  SOCKET_INFORMATION = 1; //卫教资讯
    public static final  int  SOCKET_CALL        = 2; //10.呼叫推送信息
    public static final  int  SOCKET_SLEEP       = 3; //睡眠模式
    public static final  int  SOCKET_VERSION     = 4; //版本更新
    public static final  int  SOCKET_MUSIC       = 5; //语音推送


    public static final  int  SOCKET_RTC_VOICE_CALL = 6;//rtc 语音呼叫

    public static final String WIFI_ENABLED="wifi_enabled";//WIFI 是否启用
    public static final String BLUETOOTH_ENABLED="bluetooth_enabled";//蓝牙是否启用

    public static Boolean isNurseAcitivityLive = false;
    public static Boolean isDoctorAcitivityLive = false;

    /**
     * 机器人名字,当被喊到这个名字的时候,跳转到首页
     */
    public static final String[] ROBOT_NAMES = {"小星星", "小猩猩", "小心心", "小惺惺", "小型新", "小欣欣","小型新"};
    public static final String[] ROBOT_RESPONSE = {"诶,在呢", "诶,请吩咐", "在呢在呢,有什么可以帮到你的"};
    public static final String RONAME = "余甜甜";

    /**
     * 当没有答案返回时,从以下数组中随机取出一个say
     */
    public static final String[] NO_ANSWER_SAY = {"你说的东西我正在学习中", "你可以咨询前台或专家", "你让我大脑一片空白，我都不知道该说什么好呢", "讨厌，你能不能说些我知道的东西"};
}
