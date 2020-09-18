package com.bojun.net.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 简  述
 * 作  者  chenxiaoping
 * 包  名  com.bojun.net.entity
 * 时  间  2020/9/11 16:11
 */
public class NavigationListBean {
    private List<NavigationListBean> rows;
    private String id;
    private String hospitalCode;
    private String title;
    private String answerType;

    public List<NavigationListBean> getRows() {
        if (rows == null) {
            return new ArrayList<>();
        }
        return rows;
    }

    public void setRows(List<NavigationListBean> rows) {
        this.rows = rows;
    }

    public String getId() {
        return id == null ? "" : id;
    }

    public void setId(String id) {
        this.id = id == null ? "" : id;
    }

    public String getHospitalCode() {
        return hospitalCode == null ? "" : hospitalCode;
    }

    public void setHospitalCode(String hospitalCode) {
        this.hospitalCode = hospitalCode == null ? "" : hospitalCode;
    }

    public String getTitle() {
        return title == null ? "" : title;
    }

    public void setTitle(String title) {
        this.title = title == null ? "" : title;
    }

    public String getAnswerType() {
        return answerType == null ? "" : answerType;
    }

    public void setAnswerType(String answerType) {
        this.answerType = answerType == null ? "" : answerType;
    }
}