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
public class ExamineReportBean implements Serializable {
    private int current;
    private int pages;
    private int size;
    private int total;
    private List<ExamineReport> records;

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

    public List<ExamineReport> getRecords() {
        if (records == null) {
            return new ArrayList<>();
        }
        return records;
    }

    public void setRecords(List<ExamineReport> records) {
        this.records = records;
    }

    public static class ExamineReport implements Serializable {
        private String examNo; //申请检查号
        private String examName; //检查项目名
        private String patientId; //患者id
        private String identityCard; //身份证号码
        private String realName; //病人姓名
        private Integer gender; //性别  0：女  1：男
        private String birthDate; //生日
        private Integer isInpatient; //是否是住院病人 0：否 （门诊或外医疗单位来的病人）1：是
        private String examTypeId; //检查类型id
        private String examTypeName; //检查类型名称
        private String statusId; //检查状态id
        private String deptCode; //申请部门科室code
        private String doctorId; //申请医生id
        private String doctorName; //申请医生姓名
        private String confirmDoctor; //确认医师姓名
        private String performedDeptCode; //执行部门科室code
        private String requestedTime; //申请时间
        private String prepareTime; //预约时间
        private String roomNo; //检查房间号
        private String notice; //注意事项
        private String technician; //操作的技术人员姓名
        private String reporter; //确认报告的医生姓名
        private Integer resultsIndicator; //检查结果状态标志  0：未出结果 1：已出结果
        private Integer printIndicator; //打印标志： 0-未打印  1-已打印
        private String examTime; //检查时间
        private String resultTime; //检查报告时间
        private String remark; //备注
        private String remindTime; //提醒时间
        private String examPosition; //部位
        private String prompt;//提示
        private String recommendation;//建议
        private String description; //检查所见
        private Integer isAbnormal; //是否阳性  0：否  1：是
        private List<ExamImageList> examImageList;

        public String getExamNo() {
            return examNo == null ? "" : examNo;
        }

        public void setExamNo(String examNo) {
            this.examNo = examNo == null ? "" : examNo;
        }

        public String getExamName() {
            return examName == null ? "" : examName;
        }

        public void setExamName(String examName) {
            this.examName = examName == null ? "" : examName;
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

        public String getExamTypeId() {
            return examTypeId == null ? "" : examTypeId;
        }

        public void setExamTypeId(String examTypeId) {
            this.examTypeId = examTypeId == null ? "" : examTypeId;
        }

        public String getExamTypeName() {
            return examTypeName == null ? "" : examTypeName;
        }

        public void setExamTypeName(String examTypeName) {
            this.examTypeName = examTypeName == null ? "" : examTypeName;
        }

        public String getStatusId() {
            return statusId == null ? "" : statusId;
        }

        public void setStatusId(String statusId) {
            this.statusId = statusId == null ? "" : statusId;
        }

        public String getDeptCode() {
            return deptCode == null ? "" : deptCode;
        }

        public void setDeptCode(String deptCode) {
            this.deptCode = deptCode == null ? "" : deptCode;
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

        public String getConfirmDoctor() {
            return confirmDoctor == null ? "" : confirmDoctor;
        }

        public void setConfirmDoctor(String confirmDoctor) {
            this.confirmDoctor = confirmDoctor == null ? "" : confirmDoctor;
        }

        public String getPerformedDeptCode() {
            return performedDeptCode == null ? "" : performedDeptCode;
        }

        public void setPerformedDeptCode(String performedDeptCode) {
            this.performedDeptCode = performedDeptCode == null ? "" : performedDeptCode;
        }

        public String getRequestedTime() {
            return requestedTime == null ? "" : requestedTime;
        }

        public void setRequestedTime(String requestedTime) {
            this.requestedTime = requestedTime == null ? "" : requestedTime;
        }

        public String getPrepareTime() {
            return prepareTime == null ? "" : prepareTime;
        }

        public void setPrepareTime(String prepareTime) {
            this.prepareTime = prepareTime == null ? "" : prepareTime;
        }

        public String getRoomNo() {
            return roomNo == null ? "" : roomNo;
        }

        public void setRoomNo(String roomNo) {
            this.roomNo = roomNo == null ? "" : roomNo;
        }

        public String getNotice() {
            return notice == null ? "" : notice;
        }

        public void setNotice(String notice) {
            this.notice = notice == null ? "" : notice;
        }

        public String getTechnician() {
            return technician == null ? "" : technician;
        }

        public void setTechnician(String technician) {
            this.technician = technician == null ? "" : technician;
        }

        public String getReporter() {
            return reporter == null ? "" : reporter;
        }

        public void setReporter(String reporter) {
            this.reporter = reporter == null ? "" : reporter;
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

        public String getExamTime() {
            return examTime == null ? "" : examTime;
        }

        public void setExamTime(String examTime) {
            this.examTime = examTime == null ? "" : examTime;
        }

        public String getResultTime() {
            return resultTime == null ? "" : resultTime;
        }

        public void setResultTime(String resultTime) {
            this.resultTime = resultTime == null ? "" : resultTime;
        }

        public String getRemark() {
            return remark == null ? "" : remark;
        }

        public void setRemark(String remark) {
            this.remark = remark == null ? "" : remark;
        }

        public String getRemindTime() {
            return remindTime == null ? "" : remindTime;
        }

        public void setRemindTime(String remindTime) {
            this.remindTime = remindTime == null ? "" : remindTime;
        }

        public String getExamPosition() {
            return examPosition == null ? "" : examPosition;
        }

        public void setExamPosition(String examPosition) {
            this.examPosition = examPosition == null ? "" : examPosition;
        }

        public String getPrompt() {
            return prompt == null ? "" : prompt;
        }

        public void setPrompt(String prompt) {
            this.prompt = prompt == null ? "" : prompt;
        }

        public String getRecommendation() {
            return recommendation == null ? "" : recommendation;
        }

        public void setRecommendation(String recommendation) {
            this.recommendation = recommendation == null ? "" : recommendation;
        }

        public String getDescription() {
            return description == null ? "" : description;
        }

        public void setDescription(String description) {
            this.description = description == null ? "" : description;
        }

        public Integer getIsAbnormal() {
            return isAbnormal;
        }

        public void setIsAbnormal(Integer isAbnormal) {
            this.isAbnormal = isAbnormal;
        }

        public List<ExamImageList> getExamImageList() {
            if (examImageList == null) {
                return new ArrayList<>();
            }
            return examImageList;
        }

        public void setExamImageList(List<ExamImageList> examImageList) {
            this.examImageList = examImageList;
        }

        public static class ExamImageList implements Serializable{
            private String examNo; //申请检查号
            private String patientId; //患者id
            private String reportImage; //影像地址
            private String pascAddress; //影像地址
            private int id ;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getExamNo() {
                return examNo == null ? "" : examNo;
            }

            public void setExamNo(String examNo) {
                this.examNo = examNo == null ? "" : examNo;
            }

            public String getPatientId() {
                return patientId == null ? "" : patientId;
            }

            public void setPatientId(String patientId) {
                this.patientId = patientId == null ? "" : patientId;
            }

            public String getReportImage() {
                return reportImage == null ? "" : reportImage;
            }

            public void setReportImage(String reportImage) {
                this.reportImage = reportImage == null ? "" : reportImage;
            }

            public String getPascAddress() {
                return pascAddress == null ? "" : pascAddress;
            }

            public void setPascAddress(String pascAddress) {
                this.pascAddress = pascAddress == null ? "" : pascAddress;
            }
        }
    }
}
