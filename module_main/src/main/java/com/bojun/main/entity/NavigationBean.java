package com.bojun.main.entity;

/**
 *  简  述  院内导航左侧实体类
 *  作  者  管流生
 *  类  名  NavigationBean
 *  时  间  2020/8/18 0018 17:59
 */
public class NavigationBean {
    private String name;
    private boolean isSelect;

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name == null ? "" : name;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
