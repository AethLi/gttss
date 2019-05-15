package cn.aethli.gttss.service;

import cn.aethli.gttss.dao.MediumTermRecordMapper;
import cn.aethli.gttss.domain.MediumTermRecord;
import cn.aethli.gttss.domain.SysUser;
import cn.aethli.gttss.domain.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class MediumTermRecordService extends BaseService {
    @Autowired
    MediumTermRecordMapper mediumTermRecordMapper;
    @Autowired
    OpeningReportService openingReportService;

    public String saveMediumTermRecord(SysUser sysUser, Map<String, Object> params) throws Exception {
        Topic myTopic = getMyCurrentTopic(sysUser);
        Map<String, Object> userOpenAble = (Map<String, Object>) openingReportService.checkUserOpenAble(sysUser);
        if (userOpenAble.containsValue(false)) {
//            throw new Exception("无资格填写中期检查");
            //todo 完善资格检查
        }
        MediumTermRecord mediumTermRecord = new MediumTermRecord();
        mediumTermRecord.setId(myTopic.getId());
        mediumTermRecord = mediumTermRecordMapper.selectById(mediumTermRecord);
        if (mediumTermRecord != null) {
            mediumTermRecordMapper.deleteById(mediumTermRecord);
            if (!mediumTermRecord.getStatus().equals(0)) {
                throw new Exception("状态错误，无法保存");
            }
        } else {
            mediumTermRecord = new MediumTermRecord();
            mediumTermRecord.setId(myTopic.getId());
            mediumTermRecord.setComment(Integer.valueOf((String) params.get("comment")));
            mediumTermRecord.setSummary((String) params.get("summary"));
            mediumTermRecord.setCreateBy(sysUser.getUserId());
            mediumTermRecord.setCreateDt(new Date());
            mediumTermRecord.setStatus(0);
            mediumTermRecordMapper.insertSelective(mediumTermRecord);
        }
        return "保存成功";
    }

    public Object getMyMediumTermRecord(SysUser sysUser) throws Exception {
        Topic myTopic = getMyCurrentTopic(sysUser);
        MediumTermRecord mediumTermRecord = new MediumTermRecord();
        mediumTermRecord.setId(myTopic.getId());
        mediumTermRecord = mediumTermRecordMapper.selectById(mediumTermRecord);
        return mediumTermRecord;
    }

    public Object getMediumTermRecordById(SysUser sysUser, Map<String, Object> params) throws Exception {
        MediumTermRecord mediumTermRecord = new MediumTermRecord();
        mediumTermRecord.setId((String) params.get("id"));
        mediumTermRecord = mediumTermRecordMapper.selectById(mediumTermRecord);
        if (mediumTermRecord==null){
            throw new Exception("未填写中期检查");
        }
        return mediumTermRecord;
    }

}
