package cn.aethli.gttss.domain;

import java.io.Serializable;

public class TopicBookWithBLOBs extends TopicBook implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column topicbook.topicBasis
     *
     * @mbg.generated Thu May 02 10:23:42 CST 2019
     */
    private String topicBasis;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column topicbook.topicGoal
     *
     * @mbg.generated Thu May 02 10:23:42 CST 2019
     */
    private String topicGoal;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column topicbook.externalCondition
     *
     * @mbg.generated Thu May 02 10:23:42 CST 2019
     */
    private String externalCondition;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column topicbook.reference
     *
     * @mbg.generated Thu May 02 10:23:42 CST 2019
     */
    private String reference;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table topicbook
     *
     * @mbg.generated Thu May 02 10:23:42 CST 2019
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column topicbook.topicBasis
     *
     * @return the value of topicbook.topicBasis
     *
     * @mbg.generated Thu May 02 10:23:42 CST 2019
     */
    public String getTopicBasis() {
        return topicBasis;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column topicbook.topicBasis
     *
     * @param topicBasis the value for topicbook.topicBasis
     *
     * @mbg.generated Thu May 02 10:23:42 CST 2019
     */
    public void setTopicBasis(String topicBasis) {
        this.topicBasis = topicBasis == null ? null : topicBasis.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column topicbook.topicGoal
     *
     * @return the value of topicbook.topicGoal
     *
     * @mbg.generated Thu May 02 10:23:42 CST 2019
     */
    public String getTopicGoal() {
        return topicGoal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column topicbook.topicGoal
     *
     * @param topicGoal the value for topicbook.topicGoal
     *
     * @mbg.generated Thu May 02 10:23:42 CST 2019
     */
    public void setTopicGoal(String topicGoal) {
        this.topicGoal = topicGoal == null ? null : topicGoal.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column topicbook.externalCondition
     *
     * @return the value of topicbook.externalCondition
     *
     * @mbg.generated Thu May 02 10:23:42 CST 2019
     */
    public String getExternalCondition() {
        return externalCondition;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column topicbook.externalCondition
     *
     * @param externalCondition the value for topicbook.externalCondition
     *
     * @mbg.generated Thu May 02 10:23:42 CST 2019
     */
    public void setExternalCondition(String externalCondition) {
        this.externalCondition = externalCondition == null ? null : externalCondition.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column topicbook.reference
     *
     * @return the value of topicbook.reference
     *
     * @mbg.generated Thu May 02 10:23:42 CST 2019
     */
    public String getReference() {
        return reference;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column topicbook.reference
     *
     * @param reference the value for topicbook.reference
     *
     * @mbg.generated Thu May 02 10:23:42 CST 2019
     */
    public void setReference(String reference) {
        this.reference = reference == null ? null : reference.trim();
    }
}