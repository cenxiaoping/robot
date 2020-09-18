package com.bojun.net.entity;

/**
 * 简  述  信息公示左侧实体类
 * 作  者  管流生
 * 类  名  NavigationBean
 * 时  间  2020/8/18 0018 17:59
 */
public class InfoDisclosureBean {


    private boolean isSelect;
    private int bacRecId;
    private int imgRecId;
    /**
     * searchValue : null
     * createBy : null
     * createTime : null
     * updateBy : null
     * updateTime : null
     * remark : null
     * params : {}
     * id : 6
     * newsTypeCode : SPXW
     * name : 视频新闻
     * showIndex : 7
     */

    private ParamsBean params;
    private int id;
    private String newsTypeCode;
    private String name;
    private int showIndex;


    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public int getBacRecId() {
        return bacRecId;
    }

    public void setBacRecId(int bacRecId) {
        this.bacRecId = bacRecId;
    }

    public int getImgRecId() {
        return imgRecId;
    }

    public void setImgRecId(int imgRecId) {
        this.imgRecId = imgRecId;
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

    public String getNewsTypeCode() {
        return newsTypeCode;
    }

    public void setNewsTypeCode(String newsTypeCode) {
        this.newsTypeCode = newsTypeCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getShowIndex() {
        return showIndex;
    }

    public void setShowIndex(int showIndex) {
        this.showIndex = showIndex;
    }

    public static class ParamsBean {
    }
}
