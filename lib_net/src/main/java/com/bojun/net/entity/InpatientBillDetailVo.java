package com.bojun.net.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 简  述  患者费用明细清单-vo
 * 作  者  ZHANGYU
 * 包  名  com.bojun.net.entity
 * 时  间  2020/8/21 14:53
 */
public class InpatientBillDetailVo implements Serializable {


    /**
     * current : 0
     * hitCount : true
     * optimizeCountSql : true
     * orders : [{"asc":true,"column":""}]
     * pages : 0
     * records : [{"amount":0,"billNo":"","billTime":"","charges":0,"classCode":"","costsd":0,"itemCode":"","itemName":"","itemSpec":"","orderDoctorId":"","orderDoctorName":"","patientId":"","remark":"","taritemCode":"","units":""}]
     * searchCount : true
     * size : 0
     * total : 0
     */

    private int current;
    private boolean hitCount;
    private boolean optimizeCountSql;
    private int pages;
    private boolean searchCount;
    private int size;
    private int total;
    private List<RecordsBean> records;

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public boolean isHitCount() {
        return hitCount;
    }

    public void setHitCount(boolean hitCount) {
        this.hitCount = hitCount;
    }

    public boolean isOptimizeCountSql() {
        return optimizeCountSql;
    }

    public void setOptimizeCountSql(boolean optimizeCountSql) {
        this.optimizeCountSql = optimizeCountSql;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public boolean isSearchCount() {
        return searchCount;
    }

    public void setSearchCount(boolean searchCount) {
        this.searchCount = searchCount;
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
        return records;
    }

    public void setRecords(List<RecordsBean> records) {
        this.records = records;
    }

    public static class OrdersBean {
    }

    public static class RecordsBean {
        /**
         * amount : 0
         * billNo :
         * billTime :
         * charges : 0
         * classCode :
         * costsd : 0
         * itemCode :
         * itemName :
         * itemSpec :
         * orderDoctorId :
         * orderDoctorName :
         * patientId :
         * remark :
         * taritemCode :
         * units :
         */

        private int amount;
        private String billNo;
        private String billTime;
        private int charges;
        private String classCode;
        private int costsd;
        private String itemCode;
        private String itemName;
        private String itemSpec;
        private String orderDoctorId;
        private String orderDoctorName;
        private String patientId;
        private String remark;
        private String taritemCode;
        private String units;

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public String getBillNo() {
            return billNo;
        }

        public void setBillNo(String billNo) {
            this.billNo = billNo;
        }

        public String getBillTime() {
            return billTime;
        }

        public void setBillTime(String billTime) {
            this.billTime = billTime;
        }

        public int getCharges() {
            return charges;
        }

        public void setCharges(int charges) {
            this.charges = charges;
        }

        public String getClassCode() {
            return classCode;
        }

        public void setClassCode(String classCode) {
            this.classCode = classCode;
        }

        public int getCostsd() {
            return costsd;
        }

        public void setCostsd(int costsd) {
            this.costsd = costsd;
        }

        public String getItemCode() {
            return itemCode;
        }

        public void setItemCode(String itemCode) {
            this.itemCode = itemCode;
        }

        public String getItemName() {
            return itemName;
        }

        public void setItemName(String itemName) {
            this.itemName = itemName;
        }

        public String getItemSpec() {
            return itemSpec;
        }

        public void setItemSpec(String itemSpec) {
            this.itemSpec = itemSpec;
        }

        public String getOrderDoctorId() {
            return orderDoctorId;
        }

        public void setOrderDoctorId(String orderDoctorId) {
            this.orderDoctorId = orderDoctorId;
        }

        public String getOrderDoctorName() {
            return orderDoctorName;
        }

        public void setOrderDoctorName(String orderDoctorName) {
            this.orderDoctorName = orderDoctorName;
        }

        public String getPatientId() {
            return patientId;
        }

        public void setPatientId(String patientId) {
            this.patientId = patientId;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getTaritemCode() {
            return taritemCode;
        }

        public void setTaritemCode(String taritemCode) {
            this.taritemCode = taritemCode;
        }

        public String getUnits() {
            return units;
        }

        public void setUnits(String units) {
            this.units = units;
        }
    }
}