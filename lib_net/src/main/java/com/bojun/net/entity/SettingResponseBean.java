package com.bojun.net.entity;

/**
 * 简  述
 * 作  者  chenxiaoping
 * 包  名  com.bojun.net.entity
 * 时  间  2020/9/7 15:55
 */
public class SettingResponseBean {
    private String id;
    private String robotCode;
    private String machineCode;
    private String robotName;
    private String hospitalCode;
    private String lastLoginTime;
    private String showIndex;
    private String isEnabeld;
    private String token;

    public String getToken() {
        return token == null ? "" : token;
    }

    public void setToken(String token) {
        this.token = token == null ? "" : token;
    }

    public String getId() {
        return id == null ? "" : id;
    }

    public void setId(String id) {
        this.id = id == null ? "" : id;
    }

    public String getRobotCode() {
        return robotCode == null ? "" : robotCode;
    }

    public void setRobotCode(String robotCode) {
        this.robotCode = robotCode == null ? "" : robotCode;
    }

    public String getMachineCode() {
        return machineCode == null ? "" : machineCode;
    }

    public void setMachineCode(String machineCode) {
        this.machineCode = machineCode == null ? "" : machineCode;
    }

    public String getRobotName() {
        return robotName == null ? "" : robotName;
    }

    public void setRobotName(String robotName) {
        this.robotName = robotName == null ? "" : robotName;
    }

    public String getHospitalCode() {
        return hospitalCode == null ? "" : hospitalCode;
    }

    public void setHospitalCode(String hospitalCode) {
        this.hospitalCode = hospitalCode == null ? "" : hospitalCode;
    }

    public String getLastLoginTime() {
        return lastLoginTime == null ? "" : lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime == null ? "" : lastLoginTime;
    }

    public String getShowIndex() {
        return showIndex == null ? "" : showIndex;
    }

    public void setShowIndex(String showIndex) {
        this.showIndex = showIndex == null ? "" : showIndex;
    }

    public String getIsEnabeld() {
        return isEnabeld == null ? "" : isEnabeld;
    }

    public void setIsEnabeld(String isEnabeld) {
        this.isEnabeld = isEnabeld == null ? "" : isEnabeld;
    }
}