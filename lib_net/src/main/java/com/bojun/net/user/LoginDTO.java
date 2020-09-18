package com.bojun.net.user;

import android.widget.ProgressBar;

import com.bojun.net.dto.RespDTO;
import com.bojun.net.user.entity.User;

/**
 * LoginDTO
 */
public class LoginDTO {
    private User user;
    private String token;
    private String roleId;
    private String dataPermissions;
    private String realName;
    private String specialDepts;
    private String defaultDeptCode;
    private String defaultWardCode;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRoleId() {
        return roleId == null ? "" : roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? "" : roleId;
    }

    public String getDataPermissions() {
        return dataPermissions == null ? "" : dataPermissions;
    }

    public void setDataPermissions(String dataPermissions) {
        this.dataPermissions = dataPermissions == null ? "" : dataPermissions;
    }

    public String getRealName() {
        return realName == null ? "" : realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? "" : realName;
    }

    public String getSpecialDepts() {
        return specialDepts == null ? "" : specialDepts;
    }

    public void setSpecialDepts(String specialDepts) {
        this.specialDepts = specialDepts == null ? "" : specialDepts;
    }

    public String getDefaultDeptCode() {
        return defaultDeptCode == null ? "" : defaultDeptCode;
    }

    public void setDefaultDeptCode(String defaultDeptCode) {
        this.defaultDeptCode = defaultDeptCode == null ? "" : defaultDeptCode;
    }

    public String getDefaultWardCode() {
        return defaultWardCode == null ? "" : defaultWardCode;
    }

    public void setDefaultWardCode(String defaultWardCode) {
        this.defaultWardCode = defaultWardCode == null ? "" : defaultWardCode;
    }
}
