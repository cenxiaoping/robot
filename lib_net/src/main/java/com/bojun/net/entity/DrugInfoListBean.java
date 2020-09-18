package com.bojun.net.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 简  述
 * 作  者  chenxiaoping
 * 包  名  com.bojun.net.entity
 * 时  间  2020/9/3 9:13
 */
public class DrugInfoListBean {
    private List<DrugInfoListBean> rows;
    private String id;
    private String hospitalCode;
    private String drugCode;
    private String drugName;
    private String drugAlias;
    private String drugTypeCode;

    private boolean isSelect;

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public List<DrugInfoListBean> getRows() {
        if (rows == null) {
            return new ArrayList<>();
        }
        return rows;
    }

    public void setRows(List<DrugInfoListBean> rows) {
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

    public String getDrugCode() {
        return drugCode == null ? "" : drugCode;
    }

    public void setDrugCode(String drugCode) {
        this.drugCode = drugCode == null ? "" : drugCode;
    }

    public String getDrugName() {
        return drugName == null ? "" : drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName == null ? "" : drugName;
    }

    public String getDrugAlias() {
        return drugAlias == null ? "" : drugAlias;
    }

    public void setDrugAlias(String drugAlias) {
        this.drugAlias = drugAlias == null ? "" : drugAlias;
    }

    public String getDrugTypeCode() {
        return drugTypeCode == null ? "" : drugTypeCode;
    }

    public void setDrugTypeCode(String drugTypeCode) {
        this.drugTypeCode = drugTypeCode == null ? "" : drugTypeCode;
    }
}