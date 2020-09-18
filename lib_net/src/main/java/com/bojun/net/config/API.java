package com.bojun.net.config;

import android.content.Context;

import com.bojun.net.dto.KeyConstants;
import com.example.lib_utils.SpUtil;

/**
 * API
 */
public class API {

    public volatile static API retrofitManager;
    public Context context;

    public void init(Context context) {
        this.context = context;
    }

    public static API getInstance() {
        if (retrofitManager == null) {
            synchronized (API.class) {
                if (retrofitManager == null) {
                    retrofitManager = new API();
                }
            }
        }
        return retrofitManager;
    }

    public String getHostUrl() {
        String address = SpUtil.getString(context, KeyConstants.SERVER_ADDRESS, "192.168.1.92");
        String port = SpUtil.getString(context, KeyConstants.PORT_NUMBER, "8684");
        return "http://" + address + ":" + port + "/";
//        return "http://192.168.1.92:8681/roundsWards/";
    }
}
