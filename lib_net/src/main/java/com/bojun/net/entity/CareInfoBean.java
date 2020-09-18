package com.bojun.net.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 简  述
 * 作  者  lanxianzheng
 * 包  名  com.bojun.net.entity
 * 时  间  2020-08-24 15:23
 */
public class CareInfoBean {

    /**
     * records : [{"recordId":"657dcaf7b2ae4dbfbd1d4e5dacc8f052","patientId":"N0003813872","executTime":"2019-05-22 15:39","executNrseName":"朱桂香1","content":"意识:清醒 心率:88次/分 吸氧方式:面罩 饮食护理:普食 "},{"recordId":"f3a83085f5ae4ef2bdb7136c700e52ef","patientId":"N0003813872","executTime":"2019-05-22 15:39","executNrseName":"朱桂香1","content":"意识:清醒 心率:88次/分 吸氧方式:面罩 饮食护理:普食 "},{"recordId":"edf6de768e8e4a30b8342148c3fabe40","patientId":"N0003813872","executTime":"2019-05-22 15:39","executNrseName":"朱桂香1","content":"意识:清醒 心率:88次/分 吸氧方式:面罩 饮食护理:普食 "}]
     * total : 3
     * size : 10
     * current : 1
     * orders : []
     * optimizeCountSql : true
     * hitCount : false
     * searchCount : true
     * pages : 1
     */

    private int total;
    private int size;
    private int current;
    private boolean optimizeCountSql;
    private boolean hitCount;
    private boolean searchCount;
    private int pages;
    private List<RecordsBean> records;


    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public boolean isOptimizeCountSql() {
        return optimizeCountSql;
    }

    public void setOptimizeCountSql(boolean optimizeCountSql) {
        this.optimizeCountSql = optimizeCountSql;
    }

    public boolean isHitCount() {
        return hitCount;
    }

    public void setHitCount(boolean hitCount) {
        this.hitCount = hitCount;
    }

    public boolean isSearchCount() {
        return searchCount;
    }

    public void setSearchCount(boolean searchCount) {
        this.searchCount = searchCount;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<RecordsBean> getRecords() {
        if (records == null) {
            return new ArrayList<>();
        }
        return records;
    }

    public void setRecords(List<RecordsBean> records) {
        this.records = records;
    }

    public static class RecordsBean {
        /**
         * recordId : 657dcaf7b2ae4dbfbd1d4e5dacc8f052
         * patientId : N0003813872
         * executTime : 2019-05-22 15:39
         * executNrseName : 朱桂香1
         * content : 意识:清醒 心率:88次/分 吸氧方式:面罩 饮食护理:普食
         */

        private String recordId;
        private String patientId;
        private String executTime;
        private String executNrseName;
        private String content;


        public String getRecordId() {
            return recordId == null ? "" : recordId;
        }

        public void setRecordId(String recordId) {
            this.recordId = recordId == null ? "" : recordId;
        }

        public String getPatientId() {
            return patientId == null ? "" : patientId;
        }

        public void setPatientId(String patientId) {
            this.patientId = patientId == null ? "" : patientId;
        }

        public String getExecutTime() {
            return executTime == null ? "" : executTime;
        }

        public void setExecutTime(String executTime) {
            this.executTime = executTime == null ? "" : executTime;
        }

        public String getExecutNrseName() {
            return executNrseName == null ? "" : executNrseName;
        }

        public void setExecutNrseName(String executNrseName) {
            this.executNrseName = executNrseName == null ? "" : executNrseName;
        }

        public String getContent() {
            return content == null ? "" : content;
        }

        public void setContent(String content) {
            this.content = content == null ? "" : content;
        }

        public RecordsBean(String recordId, String patientId, String executTime, String executNrseName, String content) {
            this.recordId = recordId;
            this.patientId = patientId;
            this.executTime = executTime;
            this.executNrseName = executNrseName;
            this.content = content;
        }
    }
}
