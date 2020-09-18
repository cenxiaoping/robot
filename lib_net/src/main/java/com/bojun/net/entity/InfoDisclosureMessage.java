package com.bojun.net.entity;

/**
 * 简  述
 * 作  者  ZHANGYU
 * 包  名  com.bojun.net.entity
 * 时  间  2020/9/7 17:59
 */
public class InfoDisclosureMessage {

    /**
     * createTime :
     * hospitalCode :
     * id : 0
     * newsTypeCode :
     * title :
     * updateTime :
     */

    private String createTime;
    private String hospitalCode;
    private int id;
    private String newsTypeCode;
    private String title;
    private String subTitle;
    private String updateTime;

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getHospitalCode() {
        return hospitalCode;
    }

    public void setHospitalCode(String hospitalCode) {
        this.hospitalCode = hospitalCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNewsTypeCode() {
        return newsTypeCode;
    }

    public void setNewsTypeCode(String newsTypeCode) {
        this.newsTypeCode = newsTypeCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getSubTitle() {
        return subTitle == null ? "" : subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle == null ? "" : subTitle;
    }
}