package com.bojun.net.entity;

import java.io.Serializable;

/**
 * 简  述
 * 作  者  ZHANGYU
 * 包  名  com.bojun.net.entity
 * 时  间  2020/9/8 15:35
 */
public class BuildingInfo implements Serializable {


    /**
     * id : 1
     * hospitalCode : 123456
     * buildingCode : T01
     * buildingName : 门诊大楼
     * position : 门诊大楼
     * floorNumber : 20
     * roomNumber : null
     * description : null
     * showIndex : 1
     * isEnabeld : 1
     */

    private int id;
    private String hospitalCode;
    private String buildingCode;
    private String buildingName;
    private String position;
    private int floorNumber;
    private String roomNumber;
    private String description;
    private int showIndex;
    private int isEnabeld;

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

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getShowIndex() {
        return showIndex;
    }

    public void setShowIndex(int showIndex) {
        this.showIndex = showIndex;
    }

    public int getIsEnabeld() {
        return isEnabeld;
    }

    public void setIsEnabeld(int isEnabeld) {
        this.isEnabeld = isEnabeld;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}