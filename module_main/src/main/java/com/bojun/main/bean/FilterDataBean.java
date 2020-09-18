package com.bojun.main.bean;

import java.util.List;

public class FilterDataBean {
    private String YZZH;
    private List<DataBean.ResultBean.ListBean> listBeans;

    public FilterDataBean(String YZZH, List<DataBean.ResultBean.ListBean> listBeans) {
        this.YZZH = YZZH;
        this.listBeans = listBeans;
    }

    public String getYZZH() {
        return YZZH;
    }

    public void setYZZH(String YZZH) {
        this.YZZH = YZZH;
    }

    public List<DataBean.ResultBean.ListBean> getListBeans() {
        return listBeans;
    }

    public void setListBeans(List<DataBean.ResultBean.ListBean> listBeans) {
        this.listBeans = listBeans;
    }

    @Override
    public String toString() {
        return "FilterDataBean{" +
                "YZZH='" + YZZH + '\'' +
                ", listBeans=" + listBeans +
                '}';
    }
}
