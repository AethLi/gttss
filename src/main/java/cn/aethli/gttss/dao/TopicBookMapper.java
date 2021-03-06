package cn.aethli.gttss.dao;

import cn.aethli.gttss.domain.TopicBook;
import cn.aethli.gttss.domain.TopicBookWithBLOBs;
import org.springframework.stereotype.Component;

@Component
public interface TopicBookMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table topicbook
     *
     * @mbg.generated Wed May 01 23:27:43 CST 2019
     */
    int insert(TopicBookWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table topicbook
     *
     * @mbg.generated Wed May 01 23:27:43 CST 2019
     */
    int insertSelective(TopicBookWithBLOBs record);

    void deleteById(TopicBook topicBook);

    TopicBookWithBLOBs selectById(TopicBookWithBLOBs topicBook);

    void updateWithAdminVerifyIdById(TopicBookWithBLOBs topicBookWithBLOBs);

}