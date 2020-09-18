package com.bojun.net.dto;

import java.io.Serializable;

/**
 * Created by cxsz-luyong on 2017/12/6.
 */

public class CodeData<T> implements Serializable {

    /**
     * code : 200
     * msg : 登录成功！
     * data :
     */

    private int code;
    private String msg;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "CodeData{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
