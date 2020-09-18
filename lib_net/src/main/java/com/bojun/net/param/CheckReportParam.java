package com.bojun.net.param;

/**
 * 简  述  检验
 * 作  者  管流生
 * 包  名  com.bojun.net.param
 * 时  间  2020/8/20 0020 14:29
 */
public class CheckReportParam {
    private String patientId;
    private int everyPage;
    private int pageNum;

    public CheckReportParam(String patientId, int pageNum, int everyPage) {
        this.patientId = patientId;
        this.everyPage = everyPage;
        this.pageNum = pageNum;
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
}
