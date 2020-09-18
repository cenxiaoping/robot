package com.bojun.net.entity;

import java.io.Serializable;

/**
 * 简  述
 * 作  者  ZHANGYU
 * 包  名  com.bojun.main.entity
 * 时  间  2020/9/3 16:27
 */
public class HospitalVideoVo implements Serializable {


    /**
     * searchValue : null
     * createBy : null
     * createTime : 2020-09-03 16:16:22
     * updateBy : null
     * updateTime : null
     * remark : null
     * params : {}
     * id : 2
     * hospitalCode : 123456
     * name : 医生简介
     * description : 医生简介
     * downloadUrl : http://vfx.mtime.cn/Video/2019/03/18/mp4/190318231014076505.mp4
     * showIndex : null
     * isEnabeld : 1
     */

    private String createTime;
    private int id;
    private String name;
    private String description;
    private String downloadUrl;
    private int isEnabeld;

    private boolean isSelect;

    public String getCreateTime() {
        return createTime == null ? "" : createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? "" : createTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name == null ? "" : name;
    }

    public String getDescription() {
        return description == null ? "" : description;
    }

    public void setDescription(String description) {
        this.description = description == null ? "" : description;
    }

    public String getDownloadUrl() {
        return downloadUrl == null ? "" : downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl == null ? "" : downloadUrl;
    }

    public int getIsEnabeld() {
        return isEnabeld;
    }

    public void setIsEnabeld(int isEnabeld) {
        this.isEnabeld = isEnabeld;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}