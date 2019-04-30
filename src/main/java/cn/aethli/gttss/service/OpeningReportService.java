package cn.aethli.gttss.service;

import cn.aethli.gttss.dao.OpeningReportMapper;
import cn.aethli.gttss.dao.TopicStudentGroupMapper;
import cn.aethli.gttss.domain.OpeningReportWithBLOBs;
import cn.aethli.gttss.domain.SysUser;
import cn.aethli.gttss.domain.TopicStudentGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class OpeningReportService extends BaseService {

    @Autowired
    OpeningReportMapper openingReportMapper;
    @Autowired
    TopicStudentGroupMapper topicStudentGroupMapper;

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
}
