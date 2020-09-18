package com.bojun.net.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 简  述
 * 作  者  chenxiaoping
 * 包  名  com.bojun.net.entity
 * 时  间  2020/9/3 16:34
 */
public class DrugTypeListBean {
    private List<DrugTypeListBean> rows;
    private String id;
    private String typeCode;
    private String typeName;
    private String hospitalCode;
    private String showIndex;
    private int isEnabeld;
    private boolean isSelect;

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public List<DrugTypeListBean> getRows() {
        if (rows == null) {
            return new ArrayList<>();
        }
        return rows;
    }

    public void setRows(List<DrugTypeListBean> rows) {
        this.rows = rows;
    }

    public String getId() {
        return id == null ? "" : id;
    }

    public void setId(String id) {
        this.id = id == null ? "" : id;
    }

    public String getTypeCode() {
        return typeCode == null ? "" : typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode == null ? "" : typeCode;
    }

    public String getTypeName() {
        return typeName == null ? "" : typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? "" : typeName;
    }

    public String getHospitalCode() {
        return hospitalCode == null ? "" : hospitalCode;
    }

    public void setHospitalCode(String hospitalCode) {
        this.hospitalCode = hospitalCode == null ? "" : hospitalCode;
    }

    public String getShowIndex() {
        return showIndex == null ? "" : showIndex;
    }

    public void setShowIndex(String showIndex) {
        this.showIndex = showIndex == null ? "" : showIndex;
    }

    public int getIsEnabeld() {
        return isEnabeld;
    }

    public void setIsEnabeld(int isEnabeld) {
        this.isEnabeld = isEnabeld;
    }
}