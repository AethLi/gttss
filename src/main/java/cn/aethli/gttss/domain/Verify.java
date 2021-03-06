package cn.aethli.gttss.domain;

import java.io.Serializable;
import java.util.Date;

public class Verify implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column verify.id
     *
     * @mbg.generated Sat Apr 20 09:20:33 CST 2019
     */
    private String id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column verify.type
     *
     * @mbg.generated Sat Apr 20 09:20:33 CST 2019
     */
    private Integer type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column verify.createDt
     *
     * @mbg.generated Sat Apr 20 09:20:33 CST 2019
     */
    private Date createDt;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column verify.createBy
     *
     * @mbg.generated Sat Apr 20 09:20:33 CST 2019
     */
    private String createBy;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column verify.status
     *
     * @mbg.generated Sat Apr 20 09:20:33 CST 2019
     */
    private Integer status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column verify.explanation
     *
     * @mbg.generated Sat Apr 20 09:20:33 CST 2019
     */
    private String explanation;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table verify
     *
     * @mbg.generated Sat Apr 20 09:20:33 CST 2019
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column verify.id
     *
     * @return the value of verify.id
     *
     * @mbg.generated Sat Apr 20 09:20:33 CST 2019
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column verify.id
     *
     * @param id the value for verify.id
     *
     * @mbg.generated Sat Apr 20 09:20:33 CST 2019
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column verify.type
     *
     * @return the value of verify.type
     *
     * @mbg.generated Sat Apr 20 09:20:33 CST 2019
     */
    public Integer getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column verify.type
     *
     * @param type the value for verify.type
     *
     * @mbg.generated Sat Apr 20 09:20:33 CST 2019
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column verify.createDt
     *
     * @return the value of verify.createDt
     *
     * @mbg.generated Sat Apr 20 09:20:33 CST 2019
     */
    public Date getCreateDt() {
        return createDt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column verify.createDt
     *
     * @param createDt the value for verify.createDt
     *
     * @mbg.generated Sat Apr 20 09:20:33 CST 2019
     */
    public void setCreateDt(Date createDt) {
        this.createDt = createDt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column verify.createBy
     *
     * @return the value of verify.createBy
     *
     * @mbg.generated Sat Apr 20 09:20:33 CST 2019
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column verify.createBy
     *
     * @param createBy the value for verify.createBy
     *
     * @mbg.generated Sat Apr 20 09:20:33 CST 2019
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column verify.status
     *
     * @return the value of verify.status
     *
     * @mbg.generated Sat Apr 20 09:20:33 CST 2019
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column verify.status
     *
     * @param status the value for verify.status
     *
     * @mbg.generated Sat Apr 20 09:20:33 CST 2019
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column verify.explanation
     *
     * @return the value of verify.explanation
     *
     * @mbg.generated Sat Apr 20 09:20:33 CST 2019
     */
    public String getExplanation() {
        return explanation;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column verify.explanation
     *
     * @param explanation the value for verify.explanation
     *
     * @mbg.generated Sat Apr 20 09:20:33 CST 2019
     */
    public void setExplanation(String explanation) {
        this.explanation = explanation == null ? null : explanation.trim();
    }
}