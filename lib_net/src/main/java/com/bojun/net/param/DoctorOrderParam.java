package com.bojun.net.param;

/**
 * 简  述  医嘱
 * 作  者  管流生
 * 包  名  com.bojun.net.param
 * 时  间  2020/8/20 0020 14:29
 */
public class DoctorOrderParam {
    private String patientId;
    private int everyPage;
    private int orderType;
    private int pageNum;

    public DoctorOrderParam(String patientId,  int orderType ,int pageNum,int everyPage) {
        this.patientId = patientId;
        this.everyPage = everyPage;
        this.orderType = orderType;
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

    public int getOrderType() {
        return orderType;
    }

    public void setOrderType(int orderType) {
        this.orderType = orderType;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }
}
