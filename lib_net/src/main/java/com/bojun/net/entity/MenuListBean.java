package com.bojun.net.entity;

/**
 * 简  述
 * 作  者  chenxiaoping
 * 包  名  com.bojun.net.entity
 * 时  间  2020/9/7 17:46
 */
public class MenuListBean {

    private String id;
    private String menuCode;
    private String menuName;

    public String getId() {
        return id == null ? "" : id;
    }

    public void setId(String id) {
        this.id = id == null ? "" : id;
    }

    public String getMenuCode() {
        return menuCode == null ? "" : menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode == null ? "" : menuCode;
    }

    public String getMenuName() {
        return menuName == null ? "" : menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? "" : menuName;
    }
}