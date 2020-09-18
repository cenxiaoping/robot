package com.bojun.net.dto;

import java.util.List;

/**
 * 简  述
 * 作  者  chenxiaoping
 * 包  名  com.bojun.net.dto
 * 时  间  2020/8/22 15:56
 */
public class DepartmentBean {
//    {
//        "id": null,
//            "deptCode": "18120404",
//            "deptName": "黄金院区泌外一区",
//            "deptAlias": null,
//            "higherDept": null,
//            "deptAttr": null,
//            "outpOrInp": null,
//            "internalOrSergery": null,
//            "describe": null,
//            "coverImage": null,
//            "director": null,
//            "viceDirector": null,
//            "matron": null,
//            "showIndex": null,
//            "isEnabled": null,
//            "isDelete": null,
//            "deleteTime": null
//    }

    private String id;
    private String deptCode;
    private String deptName;
    private String deptAlias;
    private String higherDept;
    private String deptAttr;
    private String outpOrInp;
    private boolean isSelect;

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

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

    public String getDeptName() {
        return deptName == null ? "" : deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName == null ? "" : deptName;
    }

    public String getDeptAlias() {
        return deptAlias == null ? "" : deptAlias;
    }

    public void setDeptAlias(String deptAlias) {
        this.deptAlias = deptAlias == null ? "" : deptAlias;
    }

    public String getHigherDept() {
        return higherDept == null ? "" : higherDept;
    }

    public void setHigherDept(String higherDept) {
        this.higherDept = higherDept == null ? "" : higherDept;
    }

    public String getDeptAttr() {
        return deptAttr == null ? "" : deptAttr;
    }

    public void setDeptAttr(String deptAttr) {
        this.deptAttr = deptAttr == null ? "" : deptAttr;
    }

    public String getOutpOrInp() {
        return outpOrInp == null ? "" : outpOrInp;
    }

    public void setOutpOrInp(String outpOrInp) {
        this.outpOrInp = outpOrInp == null ? "" : outpOrInp;
    }
}