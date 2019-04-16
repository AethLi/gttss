package cn.aethli.gttss.dao;

import cn.aethli.gttss.domain.Topic;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TopicMapper {
    public List<Topic> selectAllTopic();

    public void insertWithAll(Topic t);
}
