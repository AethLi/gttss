package cn.aethli.gttss.service;

import cn.aethli.gttss.dao.SysUserMapper;
import cn.aethli.gttss.dao.TeacherMapper;
import cn.aethli.gttss.dao.TopicMapper;
import cn.aethli.gttss.domain.Batch;
import cn.aethli.gttss.domain.SysUser;
import cn.aethli.gttss.domain.Teacher;
import cn.aethli.gttss.domain.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TopicService {
    @Autowired
    TopicMapper topicMapper;
    @Autowired
    TeacherMapper teacherMapper;
    @Autowired
    SysUserMapper sysUserMapper;

    public List<Map<String, Object>> queryCurrentTopic(Batch currentBatch) {
        Map<String, Object> result = null;
        List<Map<String, Object>> results = new ArrayList<>();
        List<Topic> topics = null;
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("batchId", currentBatch.getBatchId());
        topics = topicMapper.selectWithBatchId(queryMap);
        for (Topic t : topics) {
            result = new HashMap<>();
            result.put("id", t.getId());
            result.put("title", t.getName());
            result.put("statusName", t.getSelectStatus() == 0 ? "未被选取"
                    : t.getSelectStatus() == 1 ? "已被选择，未达人数要求，仍可选择"
                    : t.getSelectStatus() == 2 ? "人数已满" : "不可选择");
            try {
                Teacher teacher = new Teacher();
                teacher.setUserId(t.getTeacherId());
                teacher = teacherMapper.selectById(teacher);
                result.put("teacherName", teacher.getName());
            } catch (Exception e) {
                result.put("teacherName", "-");
                System.err.println("教师表-教师信息不正确");
//                e.printStackTrace();
            }
            try {
                SysUser sysUser = new SysUser();
                sysUser.setUserId(t.getTeacherId());
                sysUser = sysUserMapper.selectById(sysUser);
                result.put("connectionNum", sysUser.getPhoneNum());
            } catch (Exception e) {
                result.put("connectionNum", "-");
                System.err.println("用户表-教师信息不正确");
//                e.printStackTrace();
            }
            results.add(result);
        }
        return results;
    }
}
