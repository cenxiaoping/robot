package com.bojun.net.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 简  述
 * 作  者  chenxiaoping
 * 包  名  com.bojun.net.entity
 * 时  间  2020/9/11 16:11
 */
public class PreDiagnosisListBean {

    private List<PreDiagnosisListBean> rows;
    private String hospitalCode;
    private String title;
    private String answerType;
    private String showIndex;

    public List<PreDiagnosisListBean> getRows() {
        if (rows == null) {
            return new ArrayList<>();
        }
        return rows;
    }

    public void setRows(List<PreDiagnosisListBean> rows) {
        this.rows = rows;
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

    public String getShowIndex() {
        return showIndex == null ? "" : showIndex;
    }

    public void setShowIndex(String showIndex) {
        this.showIndex = showIndex == null ? "" : showIndex;
    }
}