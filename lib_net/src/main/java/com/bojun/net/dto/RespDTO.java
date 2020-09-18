package com.bojun.net.dto;

import java.io.Serializable;

/**
 * RespDTO
 */
public class RespDTO<T> implements Serializable {

    public int code;
    public String error = "";
    public String msg = "";
    public T data;

    @Override
    public String toString() {
        return "RespDTO{" +
                "code=" + code +
                ", error='" + error + '\'' +
                ", data=" + data +
                '}';
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getCode() {
        return code;
    }
}
