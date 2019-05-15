package cn.aethli.gttss.service;

import cn.aethli.gttss.dao.*;
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
    @Autowired
    DefenseApplyMapper defenseApplyMapper;
    @Autowired
    TopicBookMapper topicBookMapper;

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

    @SuppressWarnings("Duplicates")
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

    @SuppressWarnings("Duplicates")
    public String customizeTopicAdminVerify(SysUser sysUser, Map<String, Object> params) throws Exception {
        TopicWithBLOBs topic = new TopicWithBLOBs();
        topic.setId((String) params.get("topicId"));
        topic = topicMapper.selectById(topic);
        if (topic.getStatus() == 4 || topic.getStatus() == 5) {
            topic.setStatus(Integer.valueOf((String) params.get("status")) == 0 ? 6 : topic.getStatus());
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
            topic.setAdminVerifyId(verify.getId());
            topicMapper.updateWithStatusAdminVerifyIdById(topic);
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

    public Object getVerifyById(Map<String, Object> params) throws Exception {
        Verify verify = new Verify();
        verify.setId((String) params.get("id"));
        verify = verifyMapper.selectById(verify);
        if (verify == null) {
            throw new Exception("未找到该审核");
        }
        return verify;
    }

    public String saveDefenseVerify(SysUser sysUser, Map<String, Object> params) throws Exception {
        DefenseApply defenseApply = new DefenseApply();
        defenseApply.setId((String) params.get("id"));
        defenseApply = defenseApplyMapper.selectById(defenseApply);
        if (defenseApply == null) {
            throw new Exception("未填写申请");
        }
        Verify verify = new Verify();
        if (defenseApply.getTeacherVerify() != null) {
            verify.setId(defenseApply.getTeacherVerify());
            verifyMapper.deleteById(verify);
        }
        verify.setExplanation((String) params.get("explanation"));
        verify.setType(1);
        verify.setStatus(Integer.valueOf((String) params.get("status")));
        verify.setId(UUID.randomUUID().toString());
        verify.setCreateDt(new Date());
        verify.setCreateBy(sysUser.getUserId());
        verifyMapper.insertSelective(verify);
        defenseApply.setTeacherVerify(verify.getId());
        defenseApplyMapper.updateWithTeacherVerifyIdById(defenseApply);
        return "保存成功";
    }

    public String saveTopicBookVerify(SysUser sysUser, Map<String, Object> params) throws Exception {
        TopicBookWithBLOBs topicBookWithBLOBs = new TopicBookWithBLOBs();
        topicBookWithBLOBs.setId((String) params.get("topicId"));
        topicBookWithBLOBs = topicBookMapper.selectById(topicBookWithBLOBs);
        if (topicBookWithBLOBs == null) {
            throw new Exception("未找到该选题，或未填写任务书");
        }
        Verify verify = new Verify();
        if (topicBookWithBLOBs.getAdminVerifyId() != null) {
            verify.setId(topicBookWithBLOBs.getAdminVerifyId());
            verify = verifyMapper.selectById(verify);
            if (verify != null) {
                if (verify.getStatus() == 0) {
                    throw new Exception("不是可审核状态");
                }
                verifyMapper.deleteById(verify);
            }
        }
        verify = new Verify();
        verify.setId(UUID.randomUUID().toString());
        verify.setCreateBy(sysUser.getUserId());
        verify.setCreateDt(new Date());
        verify.setStatus(Integer.valueOf((String) params.get("status")));
        verify.setType(1);
        verify.setExplanation((String) params.get("explanation"));
        verifyMapper.insertSelective(verify);
        topicBookWithBLOBs.setAdminVerifyId(verify.getId());
        topicBookMapper.updateWithAdminVerifyIdById(topicBookWithBLOBs);
        return "保存成功";
    }
}
