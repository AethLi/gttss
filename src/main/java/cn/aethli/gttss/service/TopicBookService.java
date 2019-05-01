package cn.aethli.gttss.service;

import cn.aethli.gttss.dao.TopicBookMapper;
import cn.aethli.gttss.domain.SysUser;
import cn.aethli.gttss.domain.TopicBookWithBLOBs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TopicBookService extends BaseService {
    @Autowired
    TopicBookMapper topicBookMapper;

    public Object getTopicBookById(Map<String, Object> params) throws Exception {
        TopicBookWithBLOBs topicBook = new TopicBookWithBLOBs();
        topicBook.setId((String) params.get("id"));
        topicBook = topicBookMapper.selectById(topicBook);
        if (topicBook == null) {
            throw new Exception("未填写任务书");
        }
        return topicBook;
    }

    public String saveTopicBook(SysUser sysUser, Map<String, Object> params) throws Exception {
        TopicBookWithBLOBs topicBook = new TopicBookWithBLOBs();
        topicBook.setId((String) params.get("id"));
        topicBookMapper.deleteById(topicBook);
        topicBook.setTopicBasis((String) params.get("topicBasis"));
        topicBook.setTopicGoal((String) params.get("topicGoal"));
        topicBook.setReference((String) params.get("reference"));
        topicBook.setExternalCondition((String) params.get("externalCondition"));
        topicBookMapper.insertSelective(topicBook);
        return "保存成功";
    }
}
