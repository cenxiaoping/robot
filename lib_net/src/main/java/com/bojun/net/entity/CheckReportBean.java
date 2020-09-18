package com.bojun.net.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 简  述  医嘱
 * 作  者  管流生
 * 包  名  com.bojun.net.entity
 * 时  间  2020/8/20 0020 14:17
 */
public class CheckReportBean implements Serializable {
    private int current;
    private int pages;
    private int size;
    private int total;
    private List<CheckReport> records;

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

    public List<CheckReport> getRecords() {
        if (records == null) {
            return new ArrayList<>();
        }
        return records;
    }

    public void setRecords(List<CheckReport> records) {
        this.records = records;
    }

    public static class CheckReport implements Serializable{
        private String testNo; //检验申请号
        private String testName; //检验项目名（检验套餐名称）
        private Integer priorityIndicator; //紧急程度: 0-普通 1-紧急
        private String patientId; //患者id
        private String identityCard; //身份证号码
        private String realName; //姓名
        private Integer gender; //性别：  0：女  1：男
        private String birthDate; //生日
        private Integer isInpatient; //是否是住院病人 0：否 （门诊或外医疗单位来的病人）1：是
        private String testCause; //检验目的
        private String relevantClinicDiag; //诊断正文
        private String specimen; //标本，如血、尿等
        private String notesForSpcm; //标本采集的条件说明，如饭前、饭后一小时等。自由格式，由医生开检验单时说明
        private String spcmReceivedTime; //标本收到日期及时间
        private String requestedTime; //申请日期及时间
        private String deptCode; //申请部门科室code
        private String deptName; //申请部门科室code 名称
        private String doctorName; //申请医生姓名
        private String performedDeptCode; //执行部门科室code
        private String examTypeId; //检查类型id
        private String statusId; //检查状态id
        private String reporter; //报告人姓名
        private String verifieder; //结果校对者姓名
        private String resultsTime; //出结果报告的日期及时间
        private Integer resultsIndicator; //结果标志：  0-未出结果  1-已出结果
        private Integer printIndicator; //打印标记： 0-未打印 1-已打印
        private String remark; //备注
        private Integer abnormalIndicator; //结果正常与否标志，N-正常 L-低 H-高
        private List<LabTestReport> labTestReportList;

        public String getDeptName() {
            return deptName == null ? "" : deptName;
        }

        public void setDeptName(String deptName) {
            this.deptName = deptName == null ? "" : deptName;
        }

        public String getTestNo() {
            return testNo == null ? "" : testNo;
        }

        public void setTestNo(String testNo) {
            this.testNo = testNo == null ? "" : testNo;
        }

        public String getTestName() {
            return testName == null ? "" : testName;
        }

        public void setTestName(String testName) {
            this.testName = testName == null ? "" : testName;
        }

        public Integer getPriorityIndicator() {
            return priorityIndicator;
        }

        public void setPriorityIndicator(Integer priorityIndicator) {
            this.priorityIndicator = priorityIndicator;
        }

        public String getPatientId() {
            return patientId == null ? "" : patientId;
        }

        public void setPatientId(String patientId) {
            this.patientId = patientId == null ? "" : patientId;
        }

        public String getIdentityCard() {
            return identityCard == null ? "" : identityCard;
        }

        public void setIdentityCard(String identityCard) {
            this.identityCard = identityCard == null ? "" : identityCard;
        }

        public String getRealName() {
            return realName == null ? "" : realName;
        }

        public void setRealName(String realName) {
            this.realName = realName == null ? "" : realName;
        }

        public Integer getGender() {
            return gender;
        }

        public void setGender(Integer gender) {
            this.gender = gender;
        }

        public String getBirthDate() {
            return birthDate == null ? "" : birthDate;
        }

        public void setBirthDate(String birthDate) {
            this.birthDate = birthDate == null ? "" : birthDate;
        }

        public Integer getIsInpatient() {
            return isInpatient;
        }

        public void setIsInpatient(Integer isInpatient) {
            this.isInpatient = isInpatient;
        }

        public String getTestCause() {
            return testCause == null ? "" : testCause;
        }

        public void setTestCause(String testCause) {
            this.testCause = testCause == null ? "" : testCause;
        }

        public String getRelevantClinicDiag() {
            return relevantClinicDiag == null ? "" : relevantClinicDiag;
        }

        public void setRelevantClinicDiag(String relevantClinicDiag) {
            this.relevantClinicDiag = relevantClinicDiag == null ? "" : relevantClinicDiag;
        }

        public String getSpecimen() {
            return specimen == null ? "" : specimen;
        }

        public void setSpecimen(String specimen) {
            this.specimen = specimen == null ? "" : specimen;
        }

        public String getNotesForSpcm() {
            return notesForSpcm == null ? "" : notesForSpcm;
        }

        public void setNotesForSpcm(String notesForSpcm) {
            this.notesForSpcm = notesForSpcm == null ? "" : notesForSpcm;
        }

        public String getSpcmReceivedTime() {
            return spcmReceivedTime == null ? "" : spcmReceivedTime;
        }

        public void setSpcmReceivedTime(String spcmReceivedTime) {
            this.spcmReceivedTime = spcmReceivedTime == null ? "" : spcmReceivedTime;
        }

        public String getRequestedTime() {
            return requestedTime == null ? "" : requestedTime;
        }

        public void setRequestedTime(String requestedTime) {
            this.requestedTime = requestedTime == null ? "" : requestedTime;
        }

        public String getDeptCode() {
            return deptCode == null ? "" : deptCode;
        }

        public void setDeptCode(String deptCode) {
            this.deptCode = deptCode == null ? "" : deptCode;
        }

        public String getDoctorName() {
            return doctorName == null ? "" : doctorName;
        }

        public void setDoctorName(String doctorName) {
            this.doctorName = doctorName == null ? "" : doctorName;
        }

        public String getPerformedDeptCode() {
            return performedDeptCode == null ? "" : performedDeptCode;
        }

        public void setPerformedDeptCode(String performedDeptCode) {
            this.performedDeptCode = performedDeptCode == null ? "" : performedDeptCode;
        }

        public String getExamTypeId() {
            return examTypeId == null ? "" : examTypeId;
        }

        public void setExamTypeId(String examTypeId) {
            this.examTypeId = examTypeId == null ? "" : examTypeId;
        }

        public String getStatusId() {
            return statusId == null ? "" : statusId;
        }

        public void setStatusId(String statusId) {
            this.statusId = statusId == null ? "" : statusId;
        }

        public String getReporter() {
            return reporter == null ? "" : reporter;
        }

        public void setReporter(String reporter) {
            this.reporter = reporter == null ? "" : reporter;
        }

        public String getVerifieder() {
            return verifieder == null ? "" : verifieder;
        }

        public void setVerifieder(String verifieder) {
            this.verifieder = verifieder == null ? "" : verifieder;
        }

        public String getResultsTime() {
            return resultsTime == null ? "" : resultsTime;
        }

        public void setResultsTime(String resultsTime) {
            this.resultsTime = resultsTime == null ? "" : resultsTime;
        }

        public Integer getResultsIndicator() {
            return resultsIndicator;
        }

        public void setResultsIndicator(Integer resultsIndicator) {
            this.resultsIndicator = resultsIndicator;
        }

        public Integer getPrintIndicator() {
            return printIndicator;
        }

        public void setPrintIndicator(Integer printIndicator) {
            this.printIndicator = printIndicator;
        }

        public String getRemark() {
            return remark == null ? "" : remark;
        }

        public void setRemark(String remark) {
            this.remark = remark == null ? "" : remark;
        }

        public Integer getAbnormalIndicator() {
            return abnormalIndicator;
        }

        public void setAbnormalIndicator(Integer abnormalIndicator) {
            this.abnormalIndicator = abnormalIndicator;
        }

        public List<LabTestReport> getLabTestReportList() {
            if (labTestReportList == null) {
                return new ArrayList<>();
            }
            return labTestReportList;
        }

        public void setLabTestReportList(List<LabTestReport> labTestReportList) {
            this.labTestReportList = labTestReportList;
        }

        public static class LabTestReport implements Serializable{
            private String testNo; //检验号
            private String itemCode; //项目code
            private String itemName; //项目名
            private String result; //检验结果，可以是定性描述，也可以是定量数值
            private String abnormalIndicator; //结果正常与否标志，N-正常 L-低 H-高
            private String resultTime; //检验结果时间
            private String referenceValue; //参考范围
            private String units; //单位
            private String remark; //备注

            public String getTestNo() {
                return testNo == null ? "" : testNo;
            }

            public void setTestNo(String testNo) {
                this.testNo = testNo == null ? "" : testNo;
            }

            public String getItemCode() {
                return itemCode == null ? "" : itemCode;
            }

            public void setItemCode(String itemCode) {
                this.itemCode = itemCode == null ? "" : itemCode;
            }

            public String getItemName() {
                return itemName == null ? "" : itemName;
            }

            public void setItemName(String itemName) {
                this.itemName = itemName == null ? "" : itemName;
            }

            public String getResult() {
                return result == null ? "" : result;
            }

            public void setResult(String result) {
                this.result = result == null ? "" : result;
            }

            public String getAbnormalIndicator() {
                return abnormalIndicator == null ? "" : abnormalIndicator;
            }

            public void setAbnormalIndicator(String abnormalIndicator) {
                this.abnormalIndicator = abnormalIndicator == null ? "" : abnormalIndicator;
            }

            public String getResultTime() {
                return resultTime == null ? "" : resultTime;
            }

            public void setResultTime(String resultTime) {
                this.resultTime = resultTime == null ? "" : resultTime;
            }

            public String getReferenceValue() {
                return referenceValue == null ? "" : referenceValue;
            }

            public void setReferenceValue(String referenceValue) {
                this.referenceValue = referenceValue == null ? "" : referenceValue;
            }

            public String getUnits() {
                return units == null ? "" : units;
            }

            public void setUnits(String units) {
                this.units = units == null ? "" : units;
            }

            public String getRemark() {
                return remark == null ? "" : remark;
            }

            public void setRemark(String remark) {
                this.remark = remark == null ? "" : remark;
            }
        }
    }
}
