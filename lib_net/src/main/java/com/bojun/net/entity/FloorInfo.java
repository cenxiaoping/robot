package com.bojun.net.entity;

import java.io.Serializable;

/**
 * 简  述
 * 作  者  ZHANGYU
 * 包  名  com.bojun.net.entity
 * 时  间  2020/9/8 15:39
 */
public class FloorInfo implements Serializable {


    /**
     * id : 1
     * hospitalCode : 123456
     * buildingCode : T01
     * floorName : 一楼
     * roomNumber : 0
     * description : null
     * navigationMap : t01f1.jpg
     * navigationMapURL : http://bj-ihealthcare.com:56880/navMap/t01f1.jpg
     * navigationContent : 收费室，挂号室
     */

    private int id;
    private String hospitalCode;
    private String buildingCode;
    private String floorName;
    private int roomNumber;
    private String description;
    private String navigationMap;
    private String navigationMapURL;
    private String navigationContent;

    private boolean isSelected;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHospitalCode() {
        return hospitalCode;
    }

    public void setHospitalCode(String hospitalCode) {
        this.hospitalCode = hospitalCode;
    }

    public String getBuildingCode() {
        return buildingCode;
    }

    public void setBuildingCode(String buildingCode) {
        this.buildingCode = buildingCode;
    }

    public String getFloorName() {
        return floorName;
    }

    public void setFloorName(String floorName) {
        this.floorName = floorName;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNavigationMap() {
        return navigationMap;
    }

    public void setNavigationMap(String navigationMap) {
        this.navigationMap = navigationMap;
    }

    public String getNavigationMapURL() {
        return navigationMapURL;
    }

    public void setNavigationMapURL(String navigationMapURL) {
        this.navigationMapURL = navigationMapURL;
    }

    public String getNavigationContent() {
        return navigationContent;
    }

    public void setNavigationContent(String navigationContent) {
        this.navigationContent = navigationContent;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}