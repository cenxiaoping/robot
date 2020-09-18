package com.bojun.net.entity;

/**
 * 简  述
 * 作  者  chenxiaoping
 * 包  名  com.bojun.net.entity
 * 时  间  2020/9/3 9:12
 */
public class HospitalBarCodeBean {

    private String name;
    private String logoImage;
    private String image;
    private String description;
    private String appQrcodeUrl;
    private String mpQrcodeUrl;
    private String webQrcodeUrl;
    private String webUrl;

    public String getWebUrl() {
        return webUrl == null ? "" : webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl == null ? "" : webUrl;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name == null ? "" : name;
    }

    public String getLogoImage() {
        return logoImage == null ? "" : logoImage;
    }

    public void setLogoImage(String logoImage) {
        this.logoImage = logoImage == null ? "" : logoImage;
    }

    public String getImage() {
        return image == null ? "" : image;
    }

    public void setImage(String image) {
        this.image = image == null ? "" : image;
    }

    public String getDescription() {
        return description == null ? "" : description;
    }

    public void setDescription(String description) {
        this.description = description == null ? "" : description;
    }

    public String getAppQrcodeUrl() {
        return appQrcodeUrl == null ? "" : appQrcodeUrl;
    }

    public void setAppQrcodeUrl(String appQrcodeUrl) {
        this.appQrcodeUrl = appQrcodeUrl == null ? "" : appQrcodeUrl;
    }

    public String getMpQrcodeUrl() {
        return mpQrcodeUrl == null ? "" : mpQrcodeUrl;
    }

    public void setMpQrcodeUrl(String mpQrcodeUrl) {
        this.mpQrcodeUrl = mpQrcodeUrl == null ? "" : mpQrcodeUrl;
    }

    public String getWebQrcodeUrl() {
        return webQrcodeUrl == null ? "" : webQrcodeUrl;
    }

    public void setWebQrcodeUrl(String webQrcodeUrl) {
        this.webQrcodeUrl = webQrcodeUrl == null ? "" : webQrcodeUrl;
    }
}