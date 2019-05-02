package cn.aethli.gttss.service;

import cn.aethli.gttss.dao.*;
import cn.aethli.gttss.domain.*;
import cn.aethli.gttss.util.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class TopicService extends BaseService {
    @Autowired
    TopicMapper topicMapper;
    @Autowired
    TeacherMapper teacherMapper;
    @Autowired
    SysUserMapper sysUserMapper;
    @Autowired
    TopicStudentGroupMapper topicStudentGroupMapper;
    @Autowired
    TopicOriginMapper topicOriginMapper;
    @Autowired
    TopicPropertyMapper topicPropertyMapper;
    @Autowired
    TopicTypeMapper topicTypeMapper;
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    VerifyMapper verifyMapper;


    @SuppressWarnings("Duplicates")
    public List<Map<String, Object>> queryCurrentTopic(Batch currentBatch) {
        Map<String, Object> result = null;
        List<Map<String, Object>> results = new ArrayList<>();
        List<Topic> topics = null;
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("batchId", currentBatch.getBatchId());
        topics = topicMapper.selectByBatchId(queryMap);
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

    public Map<String, Object> getTopicById(String id) throws Exception {
        Topic topic = new Topic();
        topic.setId(id);
        topic = topicMapper.selectById(topic);
        if (topic == null) {
            throw new Exception("未找到该课题");
        } else {
            Map<String, Object> result = ObjectUtils.convertBean(topic);
            try {
                Teacher teacher = new Teacher();
                teacher.setUserId(topic.getTeacherId());
                teacher = teacherMapper.selectById(teacher);
                result.put("teacherName", teacher.getName());
            } catch (Exception e) {
                result.put("teacherName", "");
            }
            try {
                SysUser sysUser = new SysUser();
                sysUser.setUserId(topic.getTeacherId());
                sysUser = sysUserMapper.selectById(sysUser);
                result.put("connectionNum", sysUser.getPhoneNum());
            } catch (Exception e) {
                result.put("connectionNum", "");
            }
            try {
                Teacher teacher = new Teacher();
                teacher.setUserId(topic.getTeacher2Id());
                teacher = teacherMapper.selectById(teacher);
                result.put("teacher2Name", teacher.getName());
            } catch (Exception e) {
                result.put("teacher2Name", "");
            }
            try {
                SysUser sysUser = new SysUser();
                sysUser.setUserId(topic.getTeacher2Id());
                sysUser = sysUserMapper.selectById(sysUser);
                result.put("connection2Num", sysUser.getPhoneNum());
            } catch (Exception e) {
                result.put("connection2Num", "");
            }
            result.put("statusName", topic.getSelectStatus() == 0 ? "未被选取"
                    : topic.getSelectStatus() == 1 ? "已被选择，未达人数要求，仍可选择"
                    : topic.getSelectStatus() == 2 ? "人数已满" : "不可选择");
            Map<String, Object> queryMap = new HashMap<>();
            queryMap.put("batchId", getCurrentBatch().getBatchId());
            queryMap.put("topicId", topic.getId());
            int count = topicStudentGroupMapper.selectCountByTopicId_BatchId(queryMap);
            result.put("selectedCount", count);
            try {
                TopicOrigin topicOrigin = new TopicOrigin();
                topicOrigin.setId(topic.getOriginId());
                topicOrigin = topicOriginMapper.selectById(topicOrigin);
                result.put("origin", topicOrigin.getName());
            } catch (Exception e) {
                result.put("origin", "");
            }
            try {
                TopicType topicType = new TopicType();
                topicType.setId(topic.getTypeId());
                topicType = topicTypeMapper.selectById(topicType);
                result.put("type", topicType.getName());
            } catch (Exception e) {
                result.put("type", "");
            }
            try {
                TopicProperty topicProperty = new TopicProperty();
                topicProperty.setId(topic.getPropertyId());
                topicProperty = topicPropertyMapper.selectById(topicProperty);
                result.put("property", topicProperty.getName());
            } catch (Exception e) {
                result.put("property", "");
            }
            return result;
        }
    }

    public String selectTopic(String id, SysUser sysUser) throws Exception {
        Map<String, Object> myTopic = (Map<String, Object>) getMyTopic(sysUser);
        if ((boolean) myTopic.get("hasSelect")) {
            throw new Exception("有未退选的课题");
        }
        Topic desTopic = new Topic();
        desTopic.setId(id);
        desTopic.setValidityBatch(getCurrentBatch().getBatchId());
        desTopic = topicMapper.selectById_BatchId(desTopic);
        if (desTopic.getSelectStatus() == 2) {
            throw new Exception("该课题已被选满");
        } else {
            TopicStudentGroup topicStudentGroup = new TopicStudentGroup();
            topicStudentGroup.setBatchId(getCurrentBatch().getBatchId());
            topicStudentGroup.setStudentId(sysUser.getUserId());
            topicStudentGroup.setCreateBy(sysUser.getUserId());
            topicStudentGroup.setStatus(0);
            topicStudentGroup.setTopicId(id);
            topicStudentGroupMapper.insertSelective(topicStudentGroup);
            Map<String, Object> queryMap = new HashMap<>();
            queryMap.put("topicId", id);
            int size = topicStudentGroupMapper.selectByTopicId(queryMap).size();
            if (size >= desTopic.getNeedStudent()) {
                desTopic.setSelectStatus(2);
            } else if (size > 0 && size < desTopic.getNeedStudent()) {
                desTopic.setSelectStatus(1);
            }
            topicMapper.updateWithSelectStatusById(desTopic);
        }
        return "选题成功";
    }

    public String cancelTopic(String id, SysUser sysUser) throws Exception {
        TopicStudentGroup topicStudentGroup = new TopicStudentGroup();
        topicStudentGroup.setTopicId(id);
        topicStudentGroup.setStudentId(sysUser.getUserId());
        topicStudentGroup = topicStudentGroupMapper.selectByTopicId_studentId(topicStudentGroup);
        if (topicStudentGroup == null) {
            throw new Exception("这题本来就不是你的");
        } else if (topicStudentGroup.getStatus() == 1) {
            throw new Exception("被指定的题目无法退选");
        } else if (topicStudentGroup.getStatus() == 2) {
            throw new Exception("自拟题目无法推选");
        } else {
            topicStudentGroupMapper.deleteByTopicId_studentId(topicStudentGroup);
            Map<String, Object> queryMap = new HashMap<>();
            queryMap.put("topicId", id);
            List<TopicStudentGroup> topicStudentGroups = topicStudentGroupMapper.selectByTopicId(queryMap);
            Topic topic = new Topic();
            topic.setId(id);
            topic = topicMapper.selectById(topic);
            if (topic.getNeedStudent() <= 1) {
                topic.setSelectStatus(0);
            } else if (topicStudentGroups.size() > 0 || topic.getNeedStudent() > 1) {
                topic.setSelectStatus(1);
            } else {
                topic.setSelectStatus(0);
            }
        }
        return "退选成功";
    }

    public Object getMyTopic(SysUser sysUser) throws Exception {
        Map<String, Object> result = new HashMap<>();
        result.put("hasSelect", false);
        result.put("topic", null);
        TopicStudentGroup topicStudentGroup = new TopicStudentGroup();
        try {
            topicStudentGroup.setStudentId(sysUser.getUserId());
            topicStudentGroup.setBatchId(getCurrentBatch().getBatchId());
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("未登录");
        }
        topicStudentGroup = topicStudentGroupMapper.selectByStudentId_BatchId(topicStudentGroup);
        if (topicStudentGroup == null) {
            return result;
        }
        Topic topic = new Topic();
        topic.setId(topicStudentGroup.getTopicId());
        topic = topicMapper.selectById(topic);
        if (topic != null) {
            result.put("hasSelect", true);
            result.put("topic", topic);
            try {
                Teacher teacher = new Teacher();
                teacher.setUserId(topic.getTeacherId());
                teacher = teacherMapper.selectById(teacher);
                result.put("teacher", teacher);
                try {
                    SysUser s = new SysUser();
                    s.setUserId(teacher.getUserId());
                    s = sysUserMapper.selectById(s);
                    result.put("phoneNum", s.getPhoneNum());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                Teacher teacher = new Teacher();
                teacher.setUserId(topic.getTeacher2Id());
                teacher = teacherMapper.selectById(teacher);
                result.put("teacher2", teacher);
                try {
                    SysUser s = new SysUser();
                    s.setUserId(teacher.getUserId());
                    s = sysUserMapper.selectById(s);
                    result.put("phone2Num", s.getPhoneNum());
                } catch (Exception e) {
//                    e.printStackTrace();
                }
            } catch (Exception e) {
//                e.printStackTrace();
            }
        }
        return result;
    }

    public Map<String, Object> getAllSelectForCustomize(SysUser currentUser) {
        Map<String, Object> result = new HashMap<>();
        if (currentUser.getPermission() == 1) {
            Teacher teacher = new Teacher();
            teacher.setUserId(currentUser.getUserId());
            teacher = teacherMapper.selectById(teacher);
            result.put("teacherName", teacher.getName());
        }
        result.put("topicTypes", topicTypeMapper.selectAll());
        result.put("topicOrigins", topicOriginMapper.selectAll());
        result.put("topicPropertis", topicPropertyMapper.selectAll());
        return result;
    }

    public String saveCustomizeTopic(SysUser sysUser, Map<String, Object> params) {

        //判断是否已经自拟题目
        TopicStudentGroup t = new TopicStudentGroup();
        t.setStudentId(sysUser.getUserId());
        t.setBatchId(getCurrentBatch().getBatchId());
        t = topicStudentGroupMapper.selectByStudentId_BatchId(t);
        if (t != null) {
            Topic to = new Topic();
            to.setId(t.getTopicId());
            topicMapper.deleteById(to);
            topicStudentGroupMapper.deleteByTopicId_studentId(t);
        }

        TopicWithBLOBs topic = new TopicWithBLOBs();
        topic.setId(UUID.randomUUID().toString());
        topic.setSelectStatus(2);
        topic.setNeedStudent(1);
        topic.setStatus(sysUser.getPermission() == 0 ? 3 : 4);
        if (topic.getStatus() == 4) {
            topic.setTeacherVerifyId("教师自评");
        }
        topic.setValidityBatch(getCurrentBatch().getBatchId());
        topic.setCompare(Integer.valueOf((String) params.get("compare")));
        topic.setCreateBy(sysUser.getUserId());
        topic.setCreateDt(new Date());
        topic.setOriginId((String) params.get("originId"));
        topic.setTypeId((String) params.get("typeId"));
        topic.setPropertyId((String) params.get("propertyId"));
        topic.setName((String) params.get("name"));
        topic.setContent((String) params.get("content"));
        topic.setGuide((String) params.get("guide"));
        topic.setResult((String) params.get("result"));
        topic.setPlan((String) params.get("plan"));
        topic.setReference((String) params.get("reference"));
        topicMapper.insertSelective(topic);
        TopicStudentGroup topicStudentGroup = new TopicStudentGroup();
        topicStudentGroup.setTopicId(topic.getId());
        topicStudentGroup.setStudentId(sysUser.getUserId());
        topicStudentGroup.setStatus(0);
        topicStudentGroup.setBatchId(getCurrentBatch().getBatchId());
        topicStudentGroup.setCreateBy(sysUser.getUserId());
        topicStudentGroup.setCreateDt(new Date());
        topicStudentGroupMapper.insertSelective(topicStudentGroup);
        return "保存成功";
    }

    @SuppressWarnings("Duplicates")
    public List<Map<String, Object>> querySelectAbleTopic(Batch currentBatch) {
        Map<String, Object> result;
        List<Map<String, Object>> results = new ArrayList<>();
        List<Topic> topics;
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("batchId", currentBatch.getBatchId());
        queryMap.put("status", "and status not in (3,4,5)");
        topics = topicMapper.selectByBatchIdWithSql(queryMap);
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

    @SuppressWarnings("Duplicates")
    public Object getTeacherHistoryTopic(SysUser sysUser) {
        List<Map<String, Object>> results = new ArrayList<>();
        Map<String, Object> result;
        TopicWithBLOBs topic = new TopicWithBLOBs();
        topic.setTeacherId(sysUser.getUserId());
        topic.setValidityBatch(getCurrentBatch().getBatchId());
        List<TopicWithBLOBs> topics = topicMapper.selectByTeacherId(topic);
        for (TopicWithBLOBs t : topics) {
            result = new HashMap<>();
            result.put("name", t.getName());
            result.put("date", new SimpleDateFormat("yyyy-MM-dd").format(t.getCreateDt()));
            result.put("topicId", t.getId());
            Map<String, Object> queryMap = new HashMap<>();
            queryMap.put("topicId", t.getId());
            List<TopicStudentGroup> topicStudentGroups = topicStudentGroupMapper.selectByTopicId(queryMap);
            result.put("selectedCount", topicStudentGroups.size());
            results.add(result);
        }
        return results;
    }

    @SuppressWarnings("Duplicates")
    public Object getTeacherVerifyTopic(SysUser sysUser) {
        List<Map<String, Object>> results = new ArrayList<>();
        Map<String, Object> result;
        TopicWithBLOBs topic = new TopicWithBLOBs();
        topic.setTeacherId(sysUser.getUserId());
        topic.setValidityBatch(getCurrentBatch().getBatchId());
        topic.setStatus(3);
        List<TopicWithBLOBs> topics = topicMapper.selectByTeacherId_BatchId_Status(topic);
        topic.setStatus(5);
        topics.addAll(topicMapper.selectByTeacherId_BatchId_Status(topic));
        topic.setStatus(6);
        topics.addAll(topicMapper.selectByTeacherId_BatchId_Status(topic));
        for (TopicWithBLOBs t : topics) {
            result = new HashMap<>();
            result.put("name", t.getName());
            result.put("date", new SimpleDateFormat("yyyy-MM-dd").format(t.getCreateDt()));
            result.put("topicId", t.getId());
            result.put("verifyStatus", t.getStatus() == 3 ?
                    "未审核" : t.getStatus() == 5 ?
                    "已通过教师审核" : t.getStatus() == 6 ?
                    "已通过管理员审核" : "状态有错误，请联系管理员");
            Map<String, Object> queryMap = new HashMap<>();
            queryMap.put("topicId", t.getId());
            List<TopicStudentGroup> topicStudentGroups = topicStudentGroupMapper.selectByTopicId(queryMap);
            if (topicStudentGroups.size() == 0) {
                continue;
            } else {
                Student student = new Student();
                student.setUserId(topicStudentGroups.get(0).getStudentId());
                student = studentMapper.selectById(student);
                if (student == null) {
                    continue;
                } else {
                    result.put("studentName", student.getName());
                }
                SysUser user = new SysUser();
                user.setUserId(topicStudentGroups.get(0).getStudentId());
                user = sysUserMapper.selectById(user);
                if (user == null) {
                    continue;
                } else {
                    result.put("connectionNum", user.getPhoneNum());
                }
            }
            results.add(result);
        }
        return results;
    }

    public Object getCustomizeTopicVerify(SysUser sysUser) throws Exception {
        TopicStudentGroup topicStudentGroup = new TopicStudentGroup();
        topicStudentGroup.setStudentId(sysUser.getUserId());
        topicStudentGroup.setBatchId(getCurrentBatch().getBatchId());
        topicStudentGroup = topicStudentGroupMapper.selectByStudentId_BatchId(topicStudentGroup);
        if (topicStudentGroup == null) {
            throw new Exception("未选题");
        }
        Topic topic = new Topic();
        topic.setId(topicStudentGroup.getTopicId());
        topic = topicMapper.selectById(topic);
        if (topic == null) {
            throw new Exception("选题异常");
        }
        if (topic.getStatus() == 3 || topic.getStatus() == 4 || topic.getStatus() == 5) {
            Map<String, Object> result = new HashMap<>();
            Verify verify = new Verify();
            verify.setId(topic.getTeacherVerifyId());
            verify = verifyMapper.selectById(verify);
            if (verify != null) {
                result.put("teacher", verify);
                verify = new Verify();
                verify.setId(topic.getAdminVerifyId());
                verify = verifyMapper.selectById(verify);
                if (verify != null) {
                    result.put("admin", verify);
                }
            }
            return result;
        } else {
            throw new Exception("非自拟题目");
        }
    }

    @SuppressWarnings("Duplicates")
    public Object teacherGetTopicVerifyCheck(SysUser sysUser) {
        List<Map<String, Object>> results = new ArrayList<>();
        Map<String, Object> result;
        TopicWithBLOBs topic = new TopicWithBLOBs();
        topic.setTeacherId(sysUser.getUserId());
        topic.setValidityBatch(getCurrentBatch().getBatchId());
        topic.setStatus(3);
        List<TopicWithBLOBs> topics = topicMapper.selectByTeacherId_BatchId_Status(topic);
        topic.setStatus(4);
        topics.addAll(topicMapper.selectByTeacherId_BatchId_Status(topic));
        topic.setStatus(5);
        topics.addAll(topicMapper.selectByTeacherId_BatchId_Status(topic));
        topic.setStatus(6);
        topics.addAll(topicMapper.selectByTeacherId_BatchId_Status(topic));
        for (TopicWithBLOBs t : topics) {
            result = new HashMap<>();
            result.put("name", t.getName());
            result.put("date", new SimpleDateFormat("yyyy-MM-dd").format(t.getCreateDt()));
            result.put("topicId", t.getId());
            result.put("verifyStatus", t.getStatus() == 3 ?
                    "未审核" : t.getStatus() == 4 ?
                    "未审核" : t.getStatus() == 5 ?
                    "已通过教师审核" : t.getStatus() == 6 ?
                    "已通过管理员审核" : "状态有错误，请联系管理员");
            Map<String, Object> queryMap = new HashMap<>();
            queryMap.put("topicId", t.getId());
            List<TopicStudentGroup> topicStudentGroups = topicStudentGroupMapper.selectByTopicId(queryMap);
            if (topicStudentGroups.size() == 0) {
                continue;
            } else {
                Student student = new Student();
                student.setUserId(topicStudentGroups.get(0).getStudentId());
                student = studentMapper.selectById(student);
                if (student == null) {
                    continue;
                } else {
                    result.put("studentName", student.getName());
                }
                SysUser user = new SysUser();
                user.setUserId(topicStudentGroups.get(0).getStudentId());
                user = sysUserMapper.selectById(user);
                if (user == null) {
                    continue;
                } else {
                    result.put("connectionNum", user.getPhoneNum());
                }
            }
            results.add(result);
        }
        return results;
    }

    @SuppressWarnings("Duplicates")
    public Object getMyTopicT(SysUser sysUser) {
        List<Map<String, Object>> results = new ArrayList<>();
        Map<String, Object> result;
        TopicWithBLOBs topic = new TopicWithBLOBs();
        topic.setTeacherId(sysUser.getUserId());
        topic.setValidityBatch(getCurrentBatch().getBatchId());
        topic.setStatus(0);
        List<TopicWithBLOBs> topics = topicMapper.selectByTeacherId_BatchId_Status(topic);
        topic.setStatus(2);
        topics.addAll(topicMapper.selectByTeacherId_BatchId_Status(topic));
        topic.setStatus(6);
        topics.addAll(topicMapper.selectByTeacherId_BatchId_Status(topic));
        for (TopicWithBLOBs t : topics) {
            result = new HashMap<>();
            result.put("name", t.getName());
            result.put("date", new SimpleDateFormat("yyyy-MM-dd").format(t.getCreateDt()));
            result.put("topicId", t.getId());
            Map<String, Object> queryMap = new HashMap<>();
            queryMap.put("topicId", t.getId());
            List<TopicStudentGroup> topicStudentGroups = topicStudentGroupMapper.selectByTopicId(queryMap);
            result.put("selectedCount", topicStudentGroups.size());
            results.add(result);
        }
        return results;
    }

    public Object getMyTopicStudentT(SysUser sysUser) {
        List<Map<String, Object>> results = new ArrayList<>();
        Map<String, Object> result;
        TopicWithBLOBs topic = new TopicWithBLOBs();
        topic.setTeacherId(sysUser.getUserId());
        topic.setValidityBatch(getCurrentBatch().getBatchId());
        topic.setStatus(0);
        List<TopicWithBLOBs> topics = topicMapper.selectByTeacherId_BatchId_Status(topic);
        topic.setStatus(6);
        topics.addAll(topicMapper.selectByTeacherId_BatchId_Status(topic));
        topic.setStatus(7);
        topics.addAll(topicMapper.selectByTeacherId_BatchId_Status(topic));
        for (TopicWithBLOBs t : topics) {
            try {
                result = ObjectUtils.convertBean(t);
                result.put("topicId", t.getId());
                List<TopicStudentGroup> topicStudentGroups = topicStudentGroupMapper.selectByTopicId(result);
                for (TopicStudentGroup topicStudentGroup : topicStudentGroups) {
                    Student student = new Student();
                    try {
                        result = ObjectUtils.convertBean(t);
                        student.setUserId(topicStudentGroup.getStudentId());
                        student = studentMapper.selectById(student);
                        HashMap<String, Object> studentResult = new HashMap<>();
                        studentResult.putAll(ObjectUtils.convertBean(student));
                        result.put("student", studentResult);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {
                        SysUser user = new SysUser();
                        user.setUserId(student.getUserId());
                        user = sysUserMapper.selectById(user);
                        user.setPassword("已去除密码");
                        Map<String, Object> userResult = new HashMap<>();
                        userResult.putAll(ObjectUtils.convertBean(user));
                        result.put("user", userResult);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    results.add(result);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return results;
    }
}
