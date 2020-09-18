package com.bojun.common.event.common;

/**
 * 简  述
 * 作  者  ZHANGYU
 * 包  名  com.bojun.common.event.common
 * 时  间  2020/9/18 9:31
 */
public class SiteEvent {
    int site;

    public SiteEvent(int site) {
        this.site = site;
    }

    public int getSite() {
        return site;
    }

    public void setSite(int site) {
        this.site = site;
    }

}