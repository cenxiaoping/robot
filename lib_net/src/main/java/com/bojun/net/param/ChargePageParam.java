package com.bojun.net.param;

import java.io.Serializable;

/**
 * 简  述
 * 作  者  ZHANGYU
 * 包  名  com.bojun.net.param
 * 时  间  2020/8/21 14:46
 */
public class ChargePageParam implements Serializable {


    /**
     * endDate : 2020-08-30
     * everyPage : 10
     * pageNum : 1
     * patientId : 1
     * startDate : 2020-08-301
     */

    private String endDate;
    private int everyPage;
    private int pageNum;
    private String patientId;
    private String startDate;

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getEveryPage() {
        return everyPage;
    }

    public void setEveryPage(int everyPage) {
        this.everyPage = everyPage;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
}