package com.bojun.net.entity;

import java.io.Serializable;

/**
 * 简  述
 * 作  者  ZHANGYU
 * 包  名  com.bojun.net.entity
 * 时  间  2020/9/4 10:38
 */
public class SymptomInfoVo implements Serializable {


    /**
     * bodyPartCode :
     * symptomCode :
     * symptomName :
     */

    private String bodyPartCode;
    private String symptomCode;
    private String symptomName;

    private boolean isSelect;

    public String getBodyPartCode() {
        return bodyPartCode;
    }

    public void setBodyPartCode(String bodyPartCode) {
        this.bodyPartCode = bodyPartCode;
    }

    public String getSymptomCode() {
        return symptomCode;
    }

    public void setSymptomCode(String symptomCode) {
        this.symptomCode = symptomCode;
    }

    public String getSymptomName() {
        return symptomName;
    }

    public void setSymptomName(String symptomName) {
        this.symptomName = symptomName;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}