package com.bojun.common.serivce;


import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;

public class JsonUtils {
    public static HashMap<String, Object> getJosnMap(String jsonStr) {
        HashMap<String, Object> jsonMap = new HashMap<>();
        try {
            String key = null;
            Object value = null;
            int type = 0;

            HashMap<String, Object> map = new HashMap<>();
            HashMap<String, Object> valueMap = new HashMap<>();

            JSONObject jsonObject = new JSONObject(jsonStr);
            Iterator<String> it = jsonObject.keys();

            while (it.hasNext()) {
                key = it.next();
                value = jsonObject.getString(key);
                map.put(key, value);
            }

            JSONObject jsonKey;
            if (value != null) {
                jsonKey = new JSONObject(value.toString());
            } else {
                jsonKey = new JSONObject("");
            }

//            JSONObject jsonKey = new JSONObject(value.toString());

            if (key.equals("noticeInfo"))  //卫教资讯
            {
                type = KeyConstants.SOCKET_INFORMATION;
                if (!jsonKey.has("autoPlay")) {
                    valueMap.put("autoPlay", "");
                } else {
                    valueMap.put("autoPlay", jsonKey.get("autoPlay").toString());
                }

                if (!jsonKey.has("circulateTimes")) {
                    valueMap.put("circulateTimes", "");
                } else {
                    valueMap.put("circulateTimes", jsonKey.get("circulateTimes").toString());
                }

                if (!jsonKey.has("fileUrl")) {
                    valueMap.put("fileUrl", "");
                } else {
                    valueMap.put("fileUrl", jsonKey.get("fileUrl").toString());
                }

                if (!jsonKey.has("isCirculate")) {
                    valueMap.put("isCirculate", "");
                } else {
                    valueMap.put("isCirculate", jsonKey.get("isCirculate").toString());
                }

                if (!jsonKey.has("unReadCount")) {
                    valueMap.put("unReadCount", "");
                } else {
                    valueMap.put("unReadCount", jsonKey.get("unReadCount").toString());
                }

                if (!jsonKey.has("fileType")) {
                    valueMap.put("fileType", "10000");
                } else {
                    valueMap.put("fileType", jsonKey.get("fileType").toString());
                }

//                valueMap.put("autoPlay",        jsonKey.get("autoPlay").toString());
//                valueMap.put("circulateTimes",  jsonKey.get("circulateTimes").toString());
//                valueMap.put("fileUrl",         jsonKey.get("fileUrl").toString());
//                valueMap.put("isCirculate",     jsonKey.get("isCirculate").toString());
//                valueMap.put("unReadCount",     jsonKey.get("unReadCount").toString());
//                valueMap.put("fileType",        jsonKey.get("fileType").toString());
            }
//            else if(key.equals("voiceCall"))
//            {  //呼叫推送
//                type = KeyConstants.SOCKET_CALL;
//                valueMap.put("callId",        jsonKey.get("callId").toString());
//                valueMap.put("callStatus",    jsonKey.get("callStatus").toString());
//                valueMap.put("callType",      jsonKey.get("callType").toString());
//            }
            else if (key.equals("voiceCall")) {  //呼叫推送
                type = KeyConstants.SOCKET_RTC_VOICE_CALL;
                //String callClassId = jsonKey.get("callClassId").toString();
                //callClassId    1：床旁打pda  2:pda打床旁  3：pda打pda 4：分机打床旁

                valueMap.put("callId", jsonKey.get("callId").toString());
                valueMap.put("patientId", jsonKey.get("patientId").toString());
                valueMap.put("patientName", jsonKey.get("patientName").toString());
                valueMap.put("nurseId", jsonKey.get("nurseId").toString());
                valueMap.put("nurseName", jsonKey.get("nurseName").toString());
                valueMap.put("gender", jsonKey.get("gender").toString());
                valueMap.put("callStatus", jsonKey.get("callStatus").toString());
                valueMap.put("bedNumber", jsonKey.get("bedNumber").toString());
                valueMap.put("callType", jsonKey.get("callType").toString());
                valueMap.put("callClassId", jsonKey.get("callClassId").toString());
                valueMap.put("wsToken", jsonKey.get("wsToken").toString());

            } else if (key.equals("sleepModeInfo")) {  //睡眠模式

                type = KeyConstants.SOCKET_SLEEP;
                if (jsonKey.has("brightness")) {
                    valueMap.put("sleepModeStatus", jsonKey.get("brightness").toString());
                } else {
                    //数据异常设置为100
                    valueMap.put("sleepModeStatus", "100");
                }
//                if (jsonKey.get("brightness") == null || jsonKey.get("brightness").toString().equals(""))
//                {
//                    //数据异常设置为100
//                    valueMap.put("sleepModeStatus",   "100");
//
//                }else {
//                    valueMap.put("sleepModeStatus",    jsonKey.get("brightness").toString());
//                }

            } else if (key.equals("bedsideClientVersion")) {  //接收版本推送信息
                type = KeyConstants.SOCKET_VERSION;
                if (!jsonKey.has("describe")) {
                    valueMap.put("describe", "");
                } else {
                    valueMap.put("describe", jsonKey.get("describe").toString());
                }

                if (!jsonKey.has("equipmentType")) {
                    valueMap.put("equipmentType", "");
                } else {
                    valueMap.put("equipmentType", jsonKey.get("equipmentType").toString());
                }


                if (!jsonKey.has("fileName")) {
                    valueMap.put("fileName", "");
                } else {
                    valueMap.put("fileName", jsonKey.get("fileName").toString());
                }

                if (!jsonKey.has("versionNo")) {
                    valueMap.put("versionNo", "");
                } else {
                    valueMap.put("versionNo", jsonKey.get("versionNo").toString());
                }

//                valueMap.put("describe",        jsonKey.get("describe").toString());
//                valueMap.put("equipmentType",   jsonKey.get("equipmentType").toString());
//                valueMap.put("fileName",        jsonKey.get("fileName").toString());
//                valueMap.put("versionNo",       jsonKey.get("versionNo").toString());
            } else if (key.equals("voicePlay")) {
                //接收音乐推送
                type = KeyConstants.SOCKET_MUSIC;
                if (!jsonKey.has("playTimes")) {
                    valueMap.put("playTimes", "");
                } else {
                    valueMap.put("playTimes", jsonKey.get("playTimes").toString());
                }

                if (!jsonKey.has("filePath")) {
                    valueMap.put("filePath", "");
                } else {
                    valueMap.put("filePath", jsonKey.get("filePath").toString());
                }

//                valueMap.put("playTimes",      jsonKey.get("playTimes").toString());
//                valueMap.put("filePath",      jsonKey.get("filePath").toString());

            } else {
                type = 5;
                valueMap.put("test", "test");

            }

            jsonMap.put(KeyConstants.SOCKET_TYPE, type);
            jsonMap.put(KeyConstants.SOCKET_MAP, valueMap);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonMap;
    }


}
