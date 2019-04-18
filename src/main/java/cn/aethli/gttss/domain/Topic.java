package cn.aethli.gttss.domain;

import java.sql.Timestamp;

public class Topic {
    public final static int COMPARE_OLD_TOPIC = 1;
    public final static int COMPARE_NEW_TOPIC = 0;
    public final static int COMPARE_OLD_PLUS_TOPIC = 2;
    public final static int SELECTSTATUS_NORMAL = 1;
    public final static int SELECTSTATUS_HAS_SELECTED = 1;
    public final static int SELECTSTATUS_ALLOW_MORE_SELECT = 2;
    String id;
    String name;
    String teacherId;
    String teacher2Id;
    int needStudent;
    String sourceId;
    String typeId;
    String propertyId;
    int compare;
    int selectStatus;
    String content;
    String result;
    String reference;
    String planLinkKey;
    String teacherVerifyId;
    String adminVerifyId;
    Timestamp createDt;
    String createBy;
    String verifyBatch;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacher2Id() {
        return teacher2Id;
    }

    public void setTeacher2Id(String teacher2Id) {
        this.teacher2Id = teacher2Id;
    }

    public int getNeedStudent() {
        return needStudent;
    }

    public void setNeedStudent(int needStudent) {
        this.needStudent = needStudent;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    public int getCompare() {
        return compare;
    }

    public void setCompare(int compare) {
        this.compare = compare;
    }

    public int getSelectStatus() {
        return selectStatus;
    }

    public void setSelectStatus(int selectStatus) {
        this.selectStatus = selectStatus;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getPlanLinkKey() {
        return planLinkKey;
    }

    public void setPlanLinkKey(String planLinkKey) {
        this.planLinkKey = planLinkKey;
    }

    public String getTeacherVerifyId() {
        return teacherVerifyId;
    }

    public void setTeacherVerifyId(String teacherVerifyId) {
        this.teacherVerifyId = teacherVerifyId;
    }

    public String getAdminVerifyId() {
        return adminVerifyId;
    }

    public void setAdminVerifyId(String adminVerifyId) {
        this.adminVerifyId = adminVerifyId;
    }

    public Timestamp getCreateDt() {
        return createDt;
    }

    public void setCreateDt(Timestamp createDt) {
        this.createDt = createDt;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getVerifyBatch() {
        return verifyBatch;
    }

    public void setVerifyBatch(String verifyBatch) {
        this.verifyBatch = verifyBatch;
    }
}
