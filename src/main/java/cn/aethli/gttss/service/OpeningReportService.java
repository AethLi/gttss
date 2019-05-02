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

@Service
public class OpeningReportService extends BaseService {

    @Autowired
    OpeningReportMapper openingReportMapper;
    @Autowired
    TopicStudentGroupMapper topicStudentGroupMapper;
    @Autowired
    TopicMapper topicMapper;
    @Autowired
    VerifyMapper verifyMapper;


    public Object getMyOpeningReport(SysUser sysUser) throws Exception {
        TopicStudentGroup topicStudentGroup = new TopicStudentGroup();
        topicStudentGroup.setStudentId(sysUser.getUserId());
        topicStudentGroup.setBatchId(getCurrentBatch().getBatchId());
        topicStudentGroup = topicStudentGroupMapper.selectByStudentId_BatchId(topicStudentGroup);
        if (topicStudentGroup == null) {
            throw new Exception("未选题");
        }
        OpeningReportWithBLOBs openingReport = new OpeningReportWithBLOBs();
        openingReport.setTopicId(topicStudentGroup.getTopicId());
        openingReport = openingReportMapper.selectByTopicId(openingReport);
        if (openingReport == null) {
            throw new Exception("未填写开题报告");
        }
        return openingReport;
    }

    public String saveMyOpeningReport(SysUser sysUser, Map<String, Object> params) throws Exception {
        TopicStudentGroup topicStudentGroup = new TopicStudentGroup();
        topicStudentGroup.setStudentId(sysUser.getUserId());
        topicStudentGroup.setBatchId(getCurrentBatch().getBatchId());
        topicStudentGroup = topicStudentGroupMapper.selectByStudentId_BatchId(topicStudentGroup);
        if (topicStudentGroup == null) {
            throw new Exception("未选题");
        }
        OpeningReportWithBLOBs openingReport = new OpeningReportWithBLOBs();
        openingReport.setTopicId(topicStudentGroup.getTopicId());
        openingReport = openingReportMapper.selectByTopicId(openingReport);
        if (topicStudentGroup != null) {
            openingReport = new OpeningReportWithBLOBs();
            openingReport.setTopicId(topicStudentGroup.getTopicId());
            openingReportMapper.deleteByTopicId(openingReport);
        }
        openingReport = new OpeningReportWithBLOBs();
        openingReport.setTopicId(topicStudentGroup.getTopicId());
        openingReport.setStatusAndTrends((String) params.get("statusAndTrends"));
        openingReport.setMainDestination((String) params.get("mainDestination"));
        openingReport.setPlan((String) params.get("plan"));
        openingReport.setIdeasAndSolutions((String) params.get("ideasAndSolutions"));
        openingReport.setCreateBy(sysUser.getUserId());
        openingReport.setCreateDt(new Date());
        openingReport.setStudentId(sysUser.getUserId());
        openingReportMapper.insertSelective(openingReport);
        return "保存成功";
    }

    public Object getOpeningReportById(Map<String, Object> params) throws Exception {
        OpeningReportWithBLOBs openingReport = new OpeningReportWithBLOBs();
        openingReport.setTopicId((String) params.get("id"));
        openingReport = openingReportMapper.selectByTopicId(openingReport);
        if (openingReport == null) {
            throw new Exception("未填写开题报告");
        }
        return openingReport;
    }

    public Object checkUserOpenAble(SysUser user) {
        Map<String, Boolean> result = new HashMap<>();
        result.put("topic", false);
        result.put("topicBook", false);
        result.put("openingReport", false);
        result.put("topicBookVerify", false);
        result.put("openingReportVerify", false);
        TopicStudentGroup topicStudentGroup = new TopicStudentGroup();
        topicStudentGroup.setStudentId(user.getUserId());
        topicStudentGroup.setBatchId(getCurrentBatch().getBatchId());
        topicStudentGroup = topicStudentGroupMapper.selectByStudentId_BatchId(topicStudentGroup);
        if (topicStudentGroup == null) {
            return result;
        }
        TopicWithBLOBs topic = new TopicWithBLOBs();
        topic.setId(topicStudentGroup.getTopicId());
        topic = topicMapper.selectById(topic);
        if (topic == null) {
            return result;
        }
        result.put("topic", true);
        OpeningReportWithBLOBs openingReport = new OpeningReportWithBLOBs();
        openingReport.setTopicId(topic.getId());
        openingReport = openingReportMapper.selectByTopicId(openingReport);
        if (openingReport != null) {
            result.put("openingReport", true);
            Verify verify;
            if (openingReport.getTeacherVerifyId() != null) {
                verify = new Verify();
                verify.setId(openingReport.getTeacherVerifyId());
                verify = verifyMapper.selectById(verify);
                if (verify != null && verify.getStatus() == 0) {
                    if (openingReport.getDefenseVerifyId() != null) {
                        verify = new Verify();
                        verify.setId(openingReport.getDefenseVerifyId());
                        verify = verifyMapper.selectById(verify);
                        if (verify != null && verify.getStatus() == 0) {
                            result.put("openingReportVerify", true);
                        }
                    }
                }
            }
        }
        TopicBook topicBook = new TopicBook();
        return result;
    }
}
