package cn.aethli.gttss.domain;

public class SysUser {
    public static final int STATUS_NORMAL = 0;
    public static final int STATUS_BANNED = 1;
    public static final int STATUS_ALLOW_lOGIN_ONLY = 2;
    String userId;
    String account;
    Integer permission;
    String password;
    Integer status;
    String phoneNum;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Integer getPermission() {
        return permission;
    }

    public void setPermission(Integer permission) {
        this.permission = permission;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
}
