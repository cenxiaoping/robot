package com.bojun.net.dto;

/**
 * 简  述
 * 作  者  chenxiaoping
 * 包  名  com.bojun.net.dto
 * 时  间  2020/8/24 17:28
 */
public class DepartmentDoctor {
//     "id": null,
//             "employeeId": "1250",
//             "doctorId": "1250",
//             "realName": "廖云峰",
//             "deptCode": "18120404",
//             "postName": "科室主任",
//             "jobTitle": "特殊使用级",
//             "deptName": "黄金院区泌外一区",
//             "chargeCount": 0

    private String id;
    private String employeeId;
    private String doctorId;
    private String realName;
    private String deptCode;
    private String postName;
    private String jobTitle;
    private String deptName;
    private String chargeCount;

    public String getId() {
        return id == null ? "" : id;
    }

    public void setId(String id) {
        this.id = id == null ? "" : id;
    }

    public String getEmployeeId() {
        return employeeId == null ? "" : employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId == null ? "" : employeeId;
    }

    public String getDoctorId() {
        return doctorId == null ? "" : doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId == null ? "" : doctorId;
    }

    public String getRealName() {
        return realName == null ? "" : realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? "" : realName;
    }

    public String getDeptCode() {
        return deptCode == null ? "" : deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode == null ? "" : deptCode;
    }

    public String getPostName() {
        return postName == null ? "" : postName;
    }

    public void setPostName(String postName) {
        this.postName = postName == null ? "" : postName;
    }

    public String getJobTitle() {
        return jobTitle == null ? "" : jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle == null ? "" : jobTitle;
    }

    public String getDeptName() {
        return deptName == null ? "" : deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName == null ? "" : deptName;
    }

    public String getChargeCount() {
        return chargeCount == null ? "" : chargeCount;
    }

    public void setChargeCount(String chargeCount) {
        this.chargeCount = chargeCount == null ? "" : chargeCount;
    }
}