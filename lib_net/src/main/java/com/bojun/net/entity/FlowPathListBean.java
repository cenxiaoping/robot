package com.bojun.net.entity;

/**
 * 简  述
 * 作  者  chenxiaoping
 * 包  名  com.bojun.net.entity
 * 时  间  2020/9/4 15:51
 */
public class FlowPathListBean {
    private String id;
    private String flowCode;
    private String flowName;
    private String hospitalCode;
    private String fileName;
    private String filePath;
    private String showIndex;
    private String isEnabeld;
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

    public String getFlowCode() {
        return flowCode == null ? "" : flowCode;
    }

    public void setFlowCode(String flowCode) {
        this.flowCode = flowCode == null ? "" : flowCode;
    }

    public String getFlowName() {
        return flowName == null ? "" : flowName;
    }

    public void setFlowName(String flowName) {
        this.flowName = flowName == null ? "" : flowName;
    }

    public String getHospitalCode() {
        return hospitalCode == null ? "" : hospitalCode;
    }

    public void setHospitalCode(String hospitalCode) {
        this.hospitalCode = hospitalCode == null ? "" : hospitalCode;
    }

    public String getFileName() {
        return fileName == null ? "" : fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? "" : fileName;
    }

    public String getFilePath() {
        return filePath == null ? "" : filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath == null ? "" : filePath;
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