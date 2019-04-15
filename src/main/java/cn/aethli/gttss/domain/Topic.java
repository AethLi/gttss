package cn.aethli.gttss.domain;

public class Topic {
    public final static int COMPARE_OLD_TOPIC = 0;
    public final static int COMPARE_NEW_TOPIC = 0;
    public final static int COMPARE_OLD_PLUS_TOPIC = 0;
    String id;
    String name;
    String teacherId;
    String teacher2Id;
    String needStudent;
    String source;
    String properties;
    int compare;
    String content;
    String result;
    String reference;
    String planLinkKey;
    String teacherVerifyId;
    String adminVerifyId;

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

    public String getNeedStudent() {
        return needStudent;
    }

    public void setNeedStudent(String needStudent) {
        this.needStudent = needStudent;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }

    public int getCompare() {
        return compare;
    }

    public void setCompare(int compare) {
        this.compare = compare;
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
}
