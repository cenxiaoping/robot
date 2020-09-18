package com.bojun.net.entity;

/**
 * 简  述
 * 作  者  chenxiaoping
 * 包  名  com.bojun.net.entity
 * 时  间  2020/9/4 15:03
 */
public class BusinessListBean {
    private String searchValue;
    private String createBy;
    private String createTime;
    private String updateBy;
    private String updateTime;
    private String remark;
    private String id;
    private String name;
    private String content;
    private String hospitalCode;
    private String showIndex;
    private String isEnabeld;
    private boolean isSelect;

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public String getSearchValue() {
        return searchValue == null ? "" : searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue == null ? "" : searchValue;
    }

    public String getCreateBy() {
        return createBy == null ? "" : createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? "" : createBy;
    }

    public String getCreateTime() {
        return createTime == null ? "" : createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? "" : createTime;
    }

    public String getUpdateBy() {
        return updateBy == null ? "" : updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? "" : updateBy;
    }

    public String getUpdateTime() {
        return updateTime == null ? "" : updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? "" : updateTime;
    }

    public String getRemark() {
        return remark == null ? "" : remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? "" : remark;
    }

    public String getId() {
        return id == null ? "" : id;
    }

    public void setId(String id) {
        this.id = id == null ? "" : id;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name == null ? "" : name;
    }

    public String getContent() {
        return content == null ? "" : content;
    }

    public void setContent(String content) {
        this.content = content == null ? "" : content;
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

    public String getIsEnabeld() {
        return isEnabeld == null ? "" : isEnabeld;
    }

    public void setIsEnabeld(String isEnabeld) {
        this.isEnabeld = isEnabeld == null ? "" : isEnabeld;
    }
}