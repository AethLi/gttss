package cn.aethli.gttss.controller;

import cn.aethli.gttss.domain.ResponseMessage;
import cn.aethli.gttss.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
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

    @RequestMapping(value = "getTopicById")
    public Object getTopicById(@RequestBody Map<String, Object> params) {
        try {
            return new ResponseMessage(ResponseMessage.STATUS_OK, topicService.getTopicById((String) params.get("id")));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseMessage(ResponseMessage.STATUS_ERROR, e.getMessage());
        }
    }

    @RequestMapping(value = "selectTopic")
    public Object selectTopic(@RequestBody Map<String, Object> params, Model model) {
        try {
            return new ResponseMessage(ResponseMessage.STATUS_OK, topicService.selectTopic((String) params.get("id"), getSysUser(model)));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseMessage(ResponseMessage.STATUS_FAIL, e.getMessage());
        }
    }

    @RequestMapping(value = "cancelTopic")
    public Object cancelTopic(@RequestBody Map<String, Object> params, Model model) {
        try {
            return new ResponseMessage(ResponseMessage.STATUS_OK, topicService.cancelTopic((String) params.get("id"), getSysUser(model)));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseMessage(ResponseMessage.STATUS_FAIL, e.getMessage());
        }
    }

    @RequestMapping(value = "getMyTopic")
    public Object getMytopic(Model model) {
        try {
            return new ResponseMessage(ResponseMessage.STATUS_OK, topicService.getMyTopic(getSysUser(model)));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseMessage(ResponseMessage.STATUS_FAIL, e.getMessage());
        }
    }

    @RequestMapping(value = "getAllSelectForCustomize")
    public Object getAllSelectForCustomize() {
        try {
            return new ResponseMessage(ResponseMessage.STATUS_OK, topicService.getAllSelectForCustomize());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseMessage(ResponseMessage.STATUS_FAIL, e.getMessage());
        }
    }
}
