package com.bojun.net.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 简  述
 * 作  者  ZHANGYU
 * 包  名  com.bojun.net.entity
 * 时  间  2020/9/7 18:30
 */
public class InfoDisclosureMessageResp implements Serializable {
    private int total;
    private List<InfoDisclosureMessage> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<InfoDisclosureMessage> getRows() {
        if (rows == null) {
            return new ArrayList<>();
        }
        return rows;
    }

    public void setRows(List<InfoDisclosureMessage> rows) {
        this.rows = rows;
    }
}