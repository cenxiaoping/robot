package com.bojun.net.dto;

/**
 * 简  述
 * 作  者  chenxiaoping
 * 包  名  com.bojun.net.dto
 * 时  间  2020/8/24 17:04
 */
public class BadNumList {

    private String id;
    private String deptCode;
    private String wardCode;
    private String roomCode;
    private String roomName;
    private String isEnabled;
    private String isDelete;
    private String deleteTime;
    private String remark;
    private String deptName;
    private String wardName;

    public String getId() {
        return id == null ? "" : id;
    }

    public void setId(String id) {
        this.id = id == null ? "" : id;
    }

    public String getDeptCode() {
        return deptCode == null ? "" : deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode == null ? "" : deptCode;
    }

    public String getWardCode() {
        return wardCode == null ? "" : wardCode;
    }

    public void setWardCode(String wardCode) {
        this.wardCode = wardCode == null ? "" : wardCode;
    }

    public String getRoomCode() {
        return roomCode == null ? "" : roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode == null ? "" : roomCode;
    }

    public String getRoomName() {
        return roomName == null ? "" : roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName == null ? "" : roomName;
    }

    public String getIsEnabled() {
        return isEnabled == null ? "" : isEnabled;
    }

    public void setIsEnabled(String isEnabled) {
        this.isEnabled = isEnabled == null ? "" : isEnabled;
    }

    public String getIsDelete() {
        return isDelete == null ? "" : isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete == null ? "" : isDelete;
    }

    public String getDeleteTime() {
        return deleteTime == null ? "" : deleteTime;
    }

    public void setDeleteTime(String deleteTime) {
        this.deleteTime = deleteTime == null ? "" : deleteTime;
    }

    public String getRemark() {
        return remark == null ? "" : remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? "" : remark;
    }

    public String getDeptName() {
        return deptName == null ? "" : deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName == null ? "" : deptName;
    }

    public String getWardName() {
        return wardName == null ? "" : wardName;
    }

    public void setWardName(String wardName) {
        this.wardName = wardName == null ? "" : wardName;
    }
}