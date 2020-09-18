package com.bojun.common.serivce;

import java.io.Serializable;
import java.util.Map;

public class WebSocketCodeBean implements Serializable {

    /**
     * code : 200
     * msg : 登录成功！
     * data :
     */

    private String code;
    private String msg;
    private Map<String, String> data;

    public WebSocketCodeBean(String code, Map<String, String> data) {
        this.code = code;
        this.data = data;
    }

    @Override
    public String toString() {
        return "WebSocketCodeBean{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }

}

