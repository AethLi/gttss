package cn.aethli.gttss.service;

import cn.aethli.gttss.dao.OpeningReportMapper;
import cn.aethli.gttss.dao.TopicStudentGroupMapper;
import cn.aethli.gttss.dao.VerifyMapper;
import cn.aethli.gttss.domain.OpeningReport;
import cn.aethli.gttss.domain.SysUser;
import cn.aethli.gttss.domain.TopicStudentGroup;
import cn.aethli.gttss.domain.Verify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class VerifyService extends BaseService {
    @Autowired
    TopicStudentGroupMapper topicStudentGroupMapper;
    @Autowired
    OpeningReportMapper openingReportMapper;
    @Autowired
    VerifyMapper verifyMapper;

    public Object getOpeningReportVerify(SysUser sysUser) throws Exception {
        Map<String, Object> result = new HashMap<>();
        TopicStudentGroup topicStudentGroup = new TopicStudentGroup();
        topicStudentGroup.setStudentId(sysUser.getUserId());
        topicStudentGroup.setBatchId(getCurrentBatch().getBatchId());
        topicStudentGroup = topicStudentGroupMapper.selectByStudentId_BatchId(topicStudentGroup);
        OpeningReport openingReport = new OpeningReport();
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
}
