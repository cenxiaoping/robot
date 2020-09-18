package com.bojun.net.dto;

/**
 * 简  述
 * 作  者  chenxiaoping
 * 包  名  com.bojun.net.dto
 * 时  间  2020/8/22 17:17
 */
public class DeptWardBean {
    private String id;
    private String deptCode;
    private String wardCode;
    private String wardName;
    private boolean isSelect;

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

    public String getWardCode() {
        return wardCode == null ? "" : wardCode;
    }

    public void setWardCode(String wardCode) {
        this.wardCode = wardCode == null ? "" : wardCode;
    }

    public String getWardName() {
        return wardName == null ? "" : wardName;
    }

    public void setWardName(String wardName) {
        this.wardName = wardName == null ? "" : wardName;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}