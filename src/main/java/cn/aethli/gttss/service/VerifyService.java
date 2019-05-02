package cn.aethli.gttss.service;

import cn.aethli.gttss.dao.OpeningReportMapper;
import cn.aethli.gttss.dao.TopicMapper;
import cn.aethli.gttss.dao.TopicStudentGroupMapper;
import cn.aethli.gttss.dao.VerifyMapper;
import cn.aethli.gttss.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class VerifyService extends BaseService {
    @Autowired
    TopicStudentGroupMapper topicStudentGroupMapper;
    @Autowired
    OpeningReportMapper openingReportMapper;
    @Autowired
    VerifyMapper verifyMapper;
    @Autowired
    TopicMapper topicMapper;

    public Object getOpeningReportVerify(SysUser sysUser) throws Exception {
        Map<String, Object> result = new HashMap<>();
        TopicStudentGroup topicStudentGroup = new TopicStudentGroup();
        topicStudentGroup.setStudentId(sysUser.getUserId());
        topicStudentGroup.setBatchId(getCurrentBatch().getBatchId());
        topicStudentGroup = topicStudentGroupMapper.selectByStudentId_BatchId(topicStudentGroup);
        OpeningReportWithBLOBs openingReport = new OpeningReportWithBLOBs();
        openingReport.setTopicId(topicStudentGroup.getTopicId());
        openingReport = openingReportMapper.selectByTopicId(openingReport);
        try {
            Verify verify = new Verify();
            verify.setId(openingReport.getTeacherVerifyId());
            verify = verifyMapper.selectById(verify);
            result.put("teacherVerify", verify);
        } catch (NullPointerException e) {
            throw new Exception("还未填写开题报告");
        }
        try {
            Verify verify = new Verify();
            verify.setId(openingReport.getTeacherVerifyId());
            verify = verifyMapper.selectById(verify);
            result.put("defenseVerify", verify);
        } catch (NullPointerException e) {
            throw e;
        }
        return result;
    }

    public String customizeTopicTeacherVerify(SysUser sysUser, Map<String, Object> params) throws Exception {
        TopicWithBLOBs topic = new TopicWithBLOBs();
        topic.setId((String) params.get("topicId"));
        topic = topicMapper.selectById(topic);
        if (topic.getStatus() == 3) {
            topic.setStatus(Integer.valueOf((String) params.get("status")) == 0 ? 5 : 3);
            if (topic.getTeacherVerifyId() != null) {
                Verify v = new Verify();
                v.setId(topic.getTeacherVerifyId());
                verifyMapper.deleteById(v);
            }
            Verify verify = new Verify();
            verify.setId(UUID.randomUUID().toString());
            verify.setCreateBy(sysUser.getUserId());
            verify.setCreateDt(new Date());
            verify.setExplanation((String) params.get("explanation"));
            verify.setType(0);
            verify.setStatus(Integer.valueOf((String) params.get("status")));
            verifyMapper.insertSelective(verify);
            topic.setTeacherVerifyId(verify.getId());
            topicMapper.updateWithStatusTeacherVerifyIdById(topic);
        } else {
            return "该题不是可审核状态";
        }
        return "保存成功";
    }

    public String saveOpeningReportVerify(SysUser sysUser, Map<String, Object> params) {
        OpeningReportWithBLOBs openingReport = new OpeningReportWithBLOBs();
        openingReport.setTopicId((String) params.get("id"));
        openingReport = openingReportMapper.selectByTopicId(openingReport);
        if (openingReport.getTeacherVerifyId() != null) {
            Verify verify = new Verify();
            verify.setId(openingReport.getTeacherVerifyId());
            verifyMapper.deleteById(verify);
        }
        Verify verify = new Verify();
        verify.setId(UUID.randomUUID().toString());
        verify.setCreateBy(sysUser.getUserId());
        verify.setCreateDt(new Date());
        verify.setStatus(Integer.valueOf((String) params.get("status")));
        verify.setType(0);
        verify.setExplanation((String) params.get("explanation"));
        verifyMapper.insertSelective(verify);
        openingReport.setTeacherVerifyId(verify.getId());
        openingReportMapper.updateWithTeacherVerifyIdByTopicId(openingReport);
        return "保存成功";
    }

    public Object getOpeningReportVerifyById(Map<String, Object> params) throws Exception {
        OpeningReportWithBLOBs openingReport = new OpeningReportWithBLOBs();
        openingReport.setTopicId((String) params.get("id"));
        openingReport = openingReportMapper.selectByTopicId(openingReport);
        if (openingReport.getTeacherVerifyId() == null) {
            throw new Exception("未审核");
        }
        Verify verify = new Verify();
        verify.setId(openingReport.getTeacherVerifyId());
        verify = verifyMapper.selectById(verify);
        if (verify == null) {
            throw new Exception("未审核");
        }
        return verify;
    }
}
