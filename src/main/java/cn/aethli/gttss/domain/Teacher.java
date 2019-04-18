package cn.aethli.gttss.domain;

public class Teacher {
    public static final int STATUS_NORMAL = 0;
    public static final int STATUS_BANNED = 1;
    public static final int STATUS_ALLOW_lOGIN_ONLY = 2;
    String userId;
    String name;
    String unitId;
    int status;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
