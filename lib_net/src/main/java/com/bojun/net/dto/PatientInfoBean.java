package com.bojun.net.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 简  述
 * 作  者  chenxiaoping
 * 包  名  com.bojun.net.dto
 * 时  间  2020/8/24 8:56
 */
public class PatientInfoBean implements Serializable {

    private List<PatientInfoBean> records;

    private String id;
    private String patientId;
    private String cardNumber;
    private String admissionNumber;
    private String deptCode;
    private String wardCode;
    private String roomCode;
    private String bedCode;
    private String bedNumber;
    private String realName;
    private String gender;
    private String birthday;
    private String doctorId;
    private String doctorName;
    private String nurseId;
    private String nurseName;
    private String levelCode;
    private String settlementStatus;
    private String admissionTime;
    private String preDischargeDate;
    private String balance;
    private String totalCosts;
    private String remark;
    private String levelName;
    private String describe;
    private String admissionDay;
    private String age;
    private String critically;

    public String getCritically() {
        return critically == null ? "" : critically;
    }

    public void setCritically(String critically) {
        this.critically = critically == null ? "" : critically;
    }
    private String idNo;
    private String contacts;//联系人
    private String contactsPhone;//联系人电话
    private String phone;//患者电话
    private String address;//住址
    private String deptName;//科室名称
    private String allergySource;//过敏史

    public String getAllergySource() {
        return allergySource == null ? "" : allergySource;
    }

    public void setAllergySource(String allergySource) {
        this.allergySource = allergySource == null ? "" : allergySource;
    }

    public String getAdmissionDay() {
        return admissionDay == null ? "" : admissionDay;
    }

    public void setAdmissionDay(String admissionDay) {
        this.admissionDay = admissionDay == null ? "" : admissionDay;
    }

    public String getAge() {
        return age == null ? "" : age;
    }

    public void setAge(String age) {
        this.age = age == null ? "" : age;
    }

    public List<PatientInfoBean> getRecords() {
        if (records == null) {
            return new ArrayList<>();
        }
        return records;
    }

    public void setRecords(List<PatientInfoBean> records) {
        this.records = records;
    }

    public String getId() {
        return id == null ? "" : id;
    }

    public void setId(String id) {
        this.id = id == null ? "" : id;
    }

    public String getPatientId() {
        return patientId == null ? "" : patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId == null ? "" : patientId;
    }

    public String getCardNumber() {
        return cardNumber == null ? "" : cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber == null ? "" : cardNumber;
    }

    public String getAdmissionNumber() {
        return admissionNumber == null ? "" : admissionNumber;
    }

    public void setAdmissionNumber(String admissionNumber) {
        this.admissionNumber = admissionNumber == null ? "" : admissionNumber;
    }

    public String getIdNo() {
        return idNo == null ? "" : idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo == null ? "" : idNo;
    }

    public String getContacts() {
        return contacts == null ? "" : contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts == null ? "" : contacts;
    }

    public String getContactsPhone() {
        return contactsPhone == null ? "" : contactsPhone;
    }

    public void setContactsPhone(String contactsPhone) {
        this.contactsPhone = contactsPhone == null ? "" : contactsPhone;
    }

    public String getPhone() {
        return phone == null ? "" : phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? "" : phone;
    }

    public String getAddress() {
        return address == null ? "" : address;
    }

    public void setAddress(String address) {
        this.address = address == null ? "" : address;
    }

    public String getDeptName() {
        return deptName == null ? "" : deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName == null ? "" : deptName;
    }

    public String getDeptCode() {
        return deptCode == null ? "" : deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode == null ? "" : deptCode;
    }

    public String getWardCode() {
        return wardCode == null ? "" : wardCode;
    }

    public void setWardCode(String wardCode) {
        this.wardCode = wardCode == null ? "" : wardCode;
    }

    public String getRoomCode() {
        return roomCode == null ? "" : roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode == null ? "" : roomCode;
    }

    public String getBedCode() {
        return bedCode == null ? "" : bedCode;
    }

    public void setBedCode(String bedCode) {
        this.bedCode = bedCode == null ? "" : bedCode;
    }

    public String getBedNumber() {
        return bedNumber == null ? "" : bedNumber;
    }

    public void setBedNumber(String bedNumber) {
        this.bedNumber = bedNumber == null ? "" : bedNumber;
    }

    public String getRealName() {
        return realName == null ? "" : realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? "" : realName;
    }

    public String getGender() {
        return gender == null ? "" : gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? "" : gender;
    }

    public String getBirthday() {
        return birthday == null ? "" : birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday == null ? "" : birthday;
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

    public String getNurseId() {
        return nurseId == null ? "" : nurseId;
    }

    public void setNurseId(String nurseId) {
        this.nurseId = nurseId == null ? "" : nurseId;
    }

    public String getNurseName() {
        return nurseName == null ? "" : nurseName;
    }

    public void setNurseName(String nurseName) {
        this.nurseName = nurseName == null ? "" : nurseName;
    }

    public String getLevelCode() {
        return levelCode == null ? "" : levelCode;
    }

    public void setLevelCode(String levelCode) {
        this.levelCode = levelCode == null ? "" : levelCode;
    }

    public String getSettlementStatus() {
        return settlementStatus == null ? "" : settlementStatus;
    }

    public void setSettlementStatus(String settlementStatus) {
        this.settlementStatus = settlementStatus == null ? "" : settlementStatus;
    }

    public String getAdmissionTime() {
        return admissionTime == null ? "" : admissionTime;
    }

    public void setAdmissionTime(String admissionTime) {
        this.admissionTime = admissionTime == null ? "" : admissionTime;
    }

    public String getPreDischargeDate() {
        return preDischargeDate == null ? "" : preDischargeDate;
    }

    public void setPreDischargeDate(String preDischargeDate) {
        this.preDischargeDate = preDischargeDate == null ? "" : preDischargeDate;
    }

    public String getBalance() {
        return balance == null ? "" : balance;
    }

    public void setBalance(String balance) {
        this.balance = balance == null ? "" : balance;
    }

    public String getTotalCosts() {
        return totalCosts == null ? "" : totalCosts;
    }

    public void setTotalCosts(String totalCosts) {
        this.totalCosts = totalCosts == null ? "" : totalCosts;
    }

    public String getRemark() {
        return remark == null ? "" : remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? "" : remark;
    }

    public String getLevelName() {
        return levelName == null ? "" : levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName == null ? "" : levelName;
    }

    public String getDescribe() {
        return describe == null ? "" : describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe == null ? "" : describe;
    }
}