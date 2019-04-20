package cn.aethli.gttss.controller;

import cn.aethli.gttss.domain.ResponseMessage;
import cn.aethli.gttss.domain.Topic;
import cn.aethli.gttss.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/topic")
public class TopicCtrl extends BaseController {
    @Autowired
    TopicService topicService;

    @RequestMapping(value = "/queryCurrentTopic")
    public Object queryCurrentTopic() {
        List<Map<String, Object>> topics = topicService.queryCurrentTopic(getCurrentBatch());
        return new ResponseMessage(ResponseMessage.STATUS_OK, topics);
    }
}
