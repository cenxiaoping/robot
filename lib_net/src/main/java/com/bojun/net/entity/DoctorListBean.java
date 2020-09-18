package com.bojun.net.entity;

/**
 * 简  述
 * 作  者  chenxiaoping
 * 包  名  com.bojun.net.entity
 * 时  间  2020/9/3 11:48
 */
public class DoctorListBean {

    private String deptCode;
    private String doctorNo;
    private String name;
    private String postName;
    private String title;
    private String doctorImage;
    private String introduce;

    public String getDeptCode() {
        return deptCode == null ? "" : deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode == null ? "" : deptCode;
    }

    public String getDoctorNo() {
        return doctorNo == null ? "" : doctorNo;
    }

    public void setDoctorNo(String doctorNo) {
        this.doctorNo = doctorNo == null ? "" : doctorNo;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name == null ? "" : name;
    }

    public String getPostName() {
        return postName == null ? "" : postName;
    }

    public void setPostName(String postName) {
        this.postName = postName == null ? "" : postName;
    }

    public String getTitle() {
        return title == null ? "" : title;
    }

    public void setTitle(String title) {
        this.title = title == null ? "" : title;
    }

    public String getDoctorImage() {
        return doctorImage == null ? "" : doctorImage;
    }

    public void setDoctorImage(String doctorImage) {
        this.doctorImage = doctorImage == null ? "" : doctorImage;
    }

    public String getIntroduce() {
        return introduce == null ? "" : introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce == null ? "" : introduce;
    }
}