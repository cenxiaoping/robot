package com.bojun.net.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * 简  述
 * 作  者  chenxiaoping
 * 包  名  com.bojun.net.dto
 * 时  间  2020/8/24 16:02
 */
public class MainLeftMenu {

    private List<MainLeftMenu> children;
    private String menuName;
    private String menuUrl;
    private String authorityUrl;
    private String isDisplay;
    private int showIndex;
    private int level;

    public List<MainLeftMenu> getChildren() {
        if (children == null) {
            return new ArrayList<>();
        }
        return children;
    }

    public void setChildren(List<MainLeftMenu> children) {
        this.children = children;
    }

    public String getMenuName() {
        return menuName == null ? "" : menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? "" : menuName;
    }

    public String getMenuUrl() {
        return menuUrl == null ? "" : menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl == null ? "" : menuUrl;
    }

    public String getAuthorityUrl() {
        return authorityUrl == null ? "" : authorityUrl;
    }

    public void setAuthorityUrl(String authorityUrl) {
        this.authorityUrl = authorityUrl == null ? "" : authorityUrl;
    }

    public String getIsDisplay() {
        return isDisplay == null ? "" : isDisplay;
    }

    public void setIsDisplay(String isDisplay) {
        this.isDisplay = isDisplay == null ? "" : isDisplay;
    }

    public int getShowIndex() {
        return showIndex;
    }

    public void setShowIndex(int showIndex) {
        this.showIndex = showIndex;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}