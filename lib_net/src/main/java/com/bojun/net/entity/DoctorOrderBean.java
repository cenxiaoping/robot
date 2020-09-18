package com.bojun.net.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 简  述  医嘱
 * 作  者  管流生
 * 包  名  com.bojun.net.entity
 * 时  间  2020/8/20 0020 14:17
 */
public class DoctorOrderBean {

    /**
     * current : 0
     * hitCount : true
     * optimizeCountSql : true
     * orders : [{"asc":true,"column":""}]
     * pages : 0
     * records : [{"administration":"","content":"","contentList":[{"content":"","dosage":0,"dosage_units":""}],"costFlag":"","deptCode":"","dispenseStatus":0,"doctorId":"","doctorName":"","dosage":0,"dosageUnits":"","droppSpeed":0,"drugProducer":"","drugSpec":"","endDateTime":"","executeDays":0,"executeDeptCode":"","frequency":"","id":0,"isSubmit":0,"isUrgent":0,"nurseId":"","nurseName":"","orderClassId":"","orderFlag":"","orderNo":"","orderStatusCode":"","orderStatusName":"","orderSubNo":"","orderTime":"","patientId":"","remark":"","remindTime":"","repeatIndicator":0,"skinTest":"","startDateTime":"","stopDoctorId":"","stopDoctorName":"","stopTime":"","totalAmount":0,"totalDosage":0,"unitPrice":0,"useUnit":""}]
     * searchCount : true
     * size : 0
     * total : 0
     */

    private int current;
    private int pages;
    private int size;
    private int total;
    private List<RecordsBean> records;

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
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
        private String patientId; //患者id
        private String doctorId; //开立医嘱的医生id
        private String doctorName; //医生姓名
        private String deptCode; //开立医嘱的科室code
        private String executeDeptCode; //执行医嘱科室code
        private String orderNo; //医嘱号
        private String orderSubNo; //医嘱子号
        private Integer repeatIndicator; //长期医嘱标志：1:长期医嘱 2:临时医嘱
        private double totalDosage; //总剂量
        private String administration; //给药途径和方法
        private String frequency; //频次
        private String startDateTime; //医嘱开始日期及时间
        private String endDateTime; //医嘱结束日期及时间
        private String remark; //备注
        private String orderStatusName;
        private List<ContentListBean> contentList;

        public String getPatientId() {
            return patientId == null ? "" : patientId;
        }

        public void setPatientId(String patientId) {
            this.patientId = patientId == null ? "" : patientId;
        }

        public String getDoctorId() {
            return doctorId == null ? "" : doctorId;
        }

        public void setDoctorId(String doctorId) {
            this.doctorId = doctorId == null ? "" : doctorId;
        }

        public String getDoctorName() {
            return doctorName == null ? "" : doctorName;
        }

        public void setDoctorName(String doctorName) {
            this.doctorName = doctorName == null ? "" : doctorName;
        }

        public String getDeptCode() {
            return deptCode == null ? "" : deptCode;
        }

        public void setDeptCode(String deptCode) {
            this.deptCode = deptCode == null ? "" : deptCode;
        }

        public String getExecuteDeptCode() {
            return executeDeptCode == null ? "" : executeDeptCode;
        }

        public void setExecuteDeptCode(String executeDeptCode) {
            this.executeDeptCode = executeDeptCode == null ? "" : executeDeptCode;
        }

        public String getOrderNo() {
            return orderNo == null ? "" : orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo == null ? "" : orderNo;
        }

        public String getOrderSubNo() {
            return orderSubNo == null ? "" : orderSubNo;
        }

        public void setOrderSubNo(String orderSubNo) {
            this.orderSubNo = orderSubNo == null ? "" : orderSubNo;
        }

        public Integer getRepeatIndicator() {
            return repeatIndicator;
        }

        public void setRepeatIndicator(Integer repeatIndicator) {
            this.repeatIndicator = repeatIndicator;
        }

        public double getTotalDosage() {
            return totalDosage;
        }

        public void setTotalDosage(double totalDosage) {
            this.totalDosage = totalDosage;
        }

        public String getAdministration() {
            return administration == null ? "" : administration;
        }

        public void setAdministration(String administration) {
            this.administration = administration == null ? "" : administration;
        }

        public String getFrequency() {
            return frequency == null ? "" : frequency;
        }

        public void setFrequency(String frequency) {
            this.frequency = frequency == null ? "" : frequency;
        }

        public String getStartDateTime() {
            return startDateTime == null ? "" : startDateTime;
        }

        public void setStartDateTime(String startDateTime) {
            this.startDateTime = startDateTime == null ? "" : startDateTime;
        }

        public String getEndDateTime() {
            return endDateTime == null ? "" : endDateTime;
        }

        public void setEndDateTime(String endDateTime) {
            this.endDateTime = endDateTime == null ? "" : endDateTime;
        }

        public String getRemark() {
            return remark == null ? "" : remark;
        }

        public void setRemark(String remark) {
            this.remark = remark == null ? "" : remark;
        }

        public String getOrderStatusName() {
            return orderStatusName == null ? "" : orderStatusName;
        }

        public void setOrderStatusName(String orderStatusName) {
            this.orderStatusName = orderStatusName == null ? "" : orderStatusName;
        }

        public List<ContentListBean> getContentList() {
            if (contentList == null) {
                return new ArrayList<>();
            }
            return contentList;
        }

        public void setContentList(List<ContentListBean> contentList) {
            this.contentList = contentList;
        }

        public static class ContentListBean {
            private String content;
            private int dosage;
            private String dosage_units;

            public int getDosage() {
                return dosage;
            }

            public void setDosage(int dosage) {
                this.dosage = dosage;
            }

            public String getDosage_units() {
                return dosage_units == null ? "" : dosage_units;
            }

            public void setDosage_units(String dosage_units) {
                this.dosage_units = dosage_units == null ? "" : dosage_units;
            }

            public String getContent() {
                return content == null ? "" : content;
            }
            public void setContent(String content) {
                this.content = content == null ? "" : content;
            }
        }
    }
}
