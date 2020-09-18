package com.bojun.net.entity;

import java.io.Serializable;

/**
 * 简  述
 * 作  者  ZHANGYU
 * 包  名  com.bojun.net.entity
 * 时  间  2020/9/10 17:21
 */
public class UpdateVo implements Serializable {


    /**
     * searchValue : null
     * createBy : null
     * createTime : null
     * updateBy : null
     * updateTime : null
     * remark : null
     * params : {}
     * id : 1
     * hospitalCode : 123456
     * versionNo : 1.0
     * isLastest : 1
     * isForceUpdate : 1
     * updateDescription : null
     * fileName : /profile/upload/2020/09/10/78d1a8aa-9ce2-4a5a-8046-168afe42a576.apk
     * uploadTime : 2020-09-10
     */

    private int id;
    private String versionNo;
    private int isLastest;
    private int isForceUpdate;
    private String updateDescription;
    private String fileName;
    private String uploadTime;
    private int versionCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(String versionNo) {
        this.versionNo = versionNo;
    }

    public int getIsLastest() {
        return isLastest;
    }

    public void setIsLastest(int isLastest) {
        this.isLastest = isLastest;
    }

    public int getIsForceUpdate() {
        return isForceUpdate;
    }

    public void setIsForceUpdate(int isForceUpdate) {
        this.isForceUpdate = isForceUpdate;
    }

    public String getUpdateDescription() {
        return updateDescription;
    }

    public void setUpdateDescription(String updateDescription) {
        this.updateDescription = updateDescription;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }

    public static class ParamsBean {
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }
}