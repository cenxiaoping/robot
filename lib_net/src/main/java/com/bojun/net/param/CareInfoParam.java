package com.bojun.net.param;

/**
 * 简  述
 * 作  者  lanxianzheng
 * 包  名  com.bojun.net.param
 * 时  间  2020-08-24 15:16
 */
public class CareInfoParam {

    private String patientId;
    private int everyPage;
    private int pageNum;
    private String startDate;
    private String endDate;

    public CareInfoParam(String patientId, int everyPage, int pageNum, String startDate, String endDate) {
        this.patientId = patientId;
        this.everyPage = everyPage;
        this.pageNum = pageNum;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getPatientId() {
        return patientId == null ? "" : patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId == null ? "" : patientId;
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

    public String getStartDate() {
        return startDate == null ? "" : startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate == null ? "" : startDate;
    }

    public String getEndDate() {
        return endDate == null ? "" : endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate == null ? "" : endDate;
    }
}
