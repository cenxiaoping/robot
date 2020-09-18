package com.bojun.net.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * 简  述
 * 作  者  chenxiaoping
 * 包  名  com.bojun.net.dto
 * 时  间  2020/8/25 11:27
 */
public class NoteListBean {
    private List<NoteListBean> records;
    private String id;
    private String patientId;
    private String remark;
    private String noteType;
    private String isDelete;
    private List<AttachsBean> attachs;
    private String createUserId;
    private String createUserName;
    private String createTime;
    private String updateTime;
    private boolean isSelect;

    public class AttachsBean{
        private String attachName;
        private String attachUrl;
        private long noteId;
        private long voiceTime;

        public String getAttachName() {
            return attachName == null ? "" : attachName;
        }

        public void setAttachName(String attachName) {
            this.attachName = attachName == null ? "" : attachName;
        }

        public String getAttachUrl() {
            return attachUrl == null ? "" : attachUrl;
        }

        public void setAttachUrl(String attachUrl) {
            this.attachUrl = attachUrl == null ? "" : attachUrl;
        }

        public long getNoteId() {
            return noteId;
        }

        public void setNoteId(long noteId) {
            this.noteId = noteId;
        }

        public long getVoiceTime() {
            return voiceTime;
        }

        public void setVoiceTime(long voiceTime) {
            this.voiceTime = voiceTime;
        }
    }



    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public List<NoteListBean> getRecords() {
        if (records == null) {
            return new ArrayList<>();
        }
        return records;
    }

    public void setRecords(List<NoteListBean> records) {
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

    public String getRemark() {
        return remark == null ? "" : remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? "" : remark;
    }

    public String getNoteType() {
        return noteType == null ? "" : noteType;
    }

    public void setNoteType(String noteType) {
        this.noteType = noteType == null ? "" : noteType;
    }

    public String getIsDelete() {
        return isDelete == null ? "" : isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete == null ? "" : isDelete;
    }

    public List<AttachsBean> getAttachs() {
        if (attachs == null) {
            return new ArrayList<>();
        }
        return attachs;
    }

    public void setAttachs(List<AttachsBean> attachs) {
        this.attachs = attachs;
    }

    public String getCreateUserId() {
        return createUserId == null ? "" : createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId == null ? "" : createUserId;
    }

    public String getCreateUserName() {
        return createUserName == null ? "" : createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName == null ? "" : createUserName;
    }

    public String getCreateTime() {
        return createTime == null ? "" : createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? "" : createTime;
    }

    public String getUpdateTime() {
        return updateTime == null ? "" : updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? "" : updateTime;
    }
}