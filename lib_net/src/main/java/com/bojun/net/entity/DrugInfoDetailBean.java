package com.bojun.net.entity;

/**
 * 简  述
 * 作  者  chenxiaoping
 * 包  名  com.bojun.net.entity
 * 时  间  2020/9/3 9:13
 */
public class DrugInfoDetailBean {
    private String id;
    private String hospitalCode;
    private String drugCode;
    private String barCode;
    private String drugName;
    private String drugAlias;
    private String drugTypeCode;
    private String drugSpec;
    private String description;
    private String showIndex;
    private String isEnabeld;

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

    public String getBarCode() {
        return barCode == null ? "" : barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode == null ? "" : barCode;
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

    public String getDrugSpec() {
        return drugSpec == null ? "" : drugSpec;
    }

    public void setDrugSpec(String drugSpec) {
        this.drugSpec = drugSpec == null ? "" : drugSpec;
    }

    public String getDescription() {
        return description == null ? "" : description;
    }

    public void setDescription(String description) {
        this.description = description == null ? "" : description;
    }

    public String getShowIndex() {
        return showIndex == null ? "" : showIndex;
    }

    public void setShowIndex(String showIndex) {
        this.showIndex = showIndex == null ? "" : showIndex;
    }

    public String getIsEnabeld() {
        return isEnabeld == null ? "" : isEnabeld;
    }

    public void setIsEnabeld(String isEnabeld) {
        this.isEnabeld = isEnabeld == null ? "" : isEnabeld;
    }
}