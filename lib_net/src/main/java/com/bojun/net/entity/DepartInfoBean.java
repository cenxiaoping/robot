package com.bojun.net.entity;

/**
 * 简  述
 * 作  者  chenxiaoping
 * 包  名  com.bojun.net.entity
 * 时  间  2020/9/3 9:04
 */
public class DepartInfoBean {

    private String id;
    private String deptCode;
    private String hospitalCode;
    private String name;
    private String parentCode;
    private String position;
    private String description;
    private int showIndex;

    public String getId() {
        return id == null ? "" : id;
    }

    public void setId(String id) {
        this.id = id == null ? "" : id;
    }

    public String getDeptCode() {
        return deptCode == null ? "" : deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode == null ? "" : deptCode;
    }

    public String getHospitalCode() {
        return hospitalCode == null ? "" : hospitalCode;
    }

    public void setHospitalCode(String hospitalCode) {
        this.hospitalCode = hospitalCode == null ? "" : hospitalCode;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name == null ? "" : name;
    }

    public String getParentCode() {
        return parentCode == null ? "" : parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode == null ? "" : parentCode;
    }

    public String getPosition() {
        return position == null ? "" : position;
    }

    public void setPosition(String position) {
        this.position = position == null ? "" : position;
    }

    public String getDescription() {
        return description == null ? "" : description;
    }

    public void setDescription(String description) {
        this.description = description == null ? "" : description;
    }

    public int getShowIndex() {
        return showIndex;
    }

    public void setShowIndex(int showIndex) {
        this.showIndex = showIndex;
    }
}