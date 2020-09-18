package com.bojun.net.entity;

import java.io.Serializable;

/**
 * 简  述
 * 作  者  ZHANGYU
 * 包  名  com.bojun.net.entity
 * 时  间  2020/9/4 9:42
 */
public class SiteVo implements Serializable {


    /**
     * id : 1
     * bodyPartCode : B01
     * bodyPartName : 全身
     */

    private int id;
    private String bodyPartCode;
    private String bodyPartName;
    private boolean isSelect;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBodyPartCode() {
        return bodyPartCode;
    }

    public void setBodyPartCode(String bodyPartCode) {
        this.bodyPartCode = bodyPartCode;
    }

    public String getBodyPartName() {
        return bodyPartName;
    }

    public void setBodyPartName(String bodyPartName) {
        this.bodyPartName = bodyPartName;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}