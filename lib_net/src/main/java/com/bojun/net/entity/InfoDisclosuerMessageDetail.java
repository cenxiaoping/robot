package com.bojun.net.entity;

import java.io.Serializable;

/**
 * 简  述
 * 作  者  ZHANGYU
 * 包  名  com.bojun.net.entity
 * 时  间  2020/9/8 10:05
 */
public class InfoDisclosuerMessageDetail implements Serializable {


    /**
     * searchValue : null
     * createBy : null
     * createTime : 2020-08-31 16:25:08
     * updateBy : null
     * updateTime : null
     * remark : null
     * params : {}
     * id : 2
     * hospitalCode : 123456
     * newsTypeCode : YYKX
     * title : 测试标题2
     * subTitle : 副标题
     * content : 测试内容2
     */

    private String createTime;
    private ParamsBean params;
    private int id;
    private String hospitalCode;
    private String newsTypeCode;
    private String title;
    private String subTitle;
    private String content;


    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }


    public ParamsBean getParams() {
        return params;
    }

    public void setParams(ParamsBean params) {
        this.params = params;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHospitalCode() {
        return hospitalCode;
    }

    public void setHospitalCode(String hospitalCode) {
        this.hospitalCode = hospitalCode;
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

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static class ParamsBean {
    }
}