package cn.aethli.gttss.service;

import cn.aethli.gttss.dao.*;
import cn.aethli.gttss.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class BaseService {

    @Autowired
    BatchMapper batchMapper;
    @Autowired
    TopicMapper topicMapper;
    @Autowired
    TopicStudentGroupMapper topicStudentGroupMapper;
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    SysUserMapper sysUserMapper;

    public Topic getMyCurrentTopic(SysUser user) throws Exception {
        if (user.getPermission() == 0) {
            TopicStudentGroup topicStudentGroup = new TopicStudentGroup();
            topicStudentGroup.setStudentId(user.getUserId());
            topicStudentGroup.setBatchId(getCurrentBatch().getBatchId());
            topicStudentGroup = topicStudentGroupMapper.selectByStudentId_BatchId(topicStudentGroup);
            if (topicStudentGroup != null) {
                Topic topic = new Topic();
                topic.setId(topicStudentGroup.getTopicId());
                topic = topicMapper.selectById(topic);
                if (topic != null) {
                    return topic;
                }
            }
            throw new Exception("未选题");
        }
        throw new Exception("当前账户不正确");
    }

    public Batch getCurrentBatch() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.YEAR, -1);
        calendar.set(Calendar.MONTH, 8);
        calendar.set(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Map<String, Object> queryMap = new HashMap();
        queryMap.put("date", new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime()));
        Batch batch = batchMapper.selectByDate(queryMap);
        return batch;
    }

    public List<Map<String, Object>> getMyStudentTopic(SysUser sysUser) {
        List<Map<String, Object>> results = new ArrayList<>();
        TopicWithBLOBs topic = new TopicWithBLOBs();
        topic.setTeacherId(sysUser.getUserId());
        topic.setValidityBatch(getCurrentBatch().getBatchId());
        List<TopicWithBLOBs> topics = topicMapper.selectByTeacherId_BatchId(topic);
        for (Topic t : topics) {
            if (t.getSelectStatus() != 0) {

                Map<String, Object> queryMao = new HashMap<>();
                queryMao.put("topicId", t.getId());
                List<TopicStudentGroup> topicStudentGroups = topicStudentGroupMapper.selectByTopicId(queryMao);
                for (TopicStudentGroup tsg : topicStudentGroups) {
                    Map<String, Object> result = new HashMap<>();
                    result.put("topicId", t.getId());
                    result.put("topicName", t.getName());
                    result.put("topicStatus", t.getStatus());
                    Student student = new Student();
                    student.setUserId(tsg.getStudentId());
                    student = studentMapper.selectById(student);
                    try {
                        result.put("studentName", student.getName());
                    } catch (Exception e) {

                    }
                    results.add(result);
                }
            }
        }
        return results;
    }

    public Object getAllTopicHasSelect() {
        List<Map<String, Object>> results = new ArrayList<>();
        TopicStudentGroup topicStudentGroup = new TopicStudentGroup();
        topicStudentGroup.setBatchId(getCurrentBatch().getBatchId());
        List<TopicStudentGroup> topicStudentGroups = topicStudentGroupMapper.selectByBatchId(topicStudentGroup);
        for (TopicStudentGroup tsg : topicStudentGroups) {
            Map<String, Object> result = new HashMap<>();
            Student student = new Student();
            student.setUserId(tsg.getStudentId());
            student = studentMapper.selectById(student);
            if (student == null) {
                continue;
            }
            Topic topic = new Topic();
            topic.setId(tsg.getTopicId());
            topic = topicMapper.selectById(topic);
            if (topic == null) {
                continue;
            }
            SysUser sysUser = new SysUser();
            sysUser.setUserId(tsg.getStudentId());
            sysUser = sysUserMapper.selectById(sysUser);
            if (sysUser == null) {
                continue;
            }
            result.put("topicId", tsg.getTopicId());
            result.put("topicName", topic.getName());
            result.put("studentName", student.getName());
            result.put("connectionNum", sysUser.getPhoneNum());
            result.put("studentId", student.getUserId());
            results.add(result);
        }
        return results;
    }
}
