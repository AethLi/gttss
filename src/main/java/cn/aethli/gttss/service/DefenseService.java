package cn.aethli.gttss.service;

import cn.aethli.gttss.dao.DefenseApplyMapper;
import cn.aethli.gttss.dao.DefenseDraftMapper;
import cn.aethli.gttss.dao.SysFileMapper;
import cn.aethli.gttss.dao.VerifyMapper;
import cn.aethli.gttss.domain.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;

@Service
public class DefenseService extends BaseService {

    @Autowired
    SysFileMapper sysFileMapper;
    @Autowired
    DefenseDraftMapper defenseDraftMapper;
    @Autowired
    DefenseApplyMapper defenseApplyMapper;
    @Autowired
    VerifyMapper verifyMapper;

    @SuppressWarnings("Duplicates")
    public String defenseDraftUpload(MultipartFile file, SysUser sysUser) throws Exception {
        if (file == null) {
            throw new Exception("上传失败");
        }

        Properties properties = new Properties();
        String resourcePath = DefenseService.class.getClassLoader().getResource("").getPath();
        resourcePath += "/templates/config/path.properties";
        InputStream in = new FileInputStream(resourcePath);
        properties.load(in);
        String path = properties.getProperty("Defense_Draft_Upload_Path");
        String desFileName = RandomStringUtils.randomAlphanumeric(32) + file.getOriginalFilename();
        path += desFileName;

        File desFile = new File(path);
        file.transferTo(desFile);

        DefenseDraft defenseDraft = new DefenseDraft();
        defenseDraft.setId(getMyCurrentTopic(sysUser).getId());
        defenseDraft = defenseDraftMapper.selectById(defenseDraft);
        SysFile sysFile;
        if (defenseDraft != null) {
            if (defenseDraft.getStatus() == 0) {
                if (defenseDraft.getFileId() != null || defenseDraft.getFileId() != "") {
                    sysFile = new SysFile();
                    sysFile.setId(defenseDraft.getFileId());
                    sysFile.setStatus(1);
                    sysFileMapper.updateWithStatusById(sysFile);
                }
                sysFile = new SysFile();
                sysFile.setId(defenseDraft.getFileId());
                sysFile.setCreateBy(sysUser.getUserId());
                sysFile.setId(UUID.randomUUID().toString());
                sysFile.setFileName(desFileName);
                sysFile.setRealName(file.getOriginalFilename());
                sysFile.setCreateDt(new Date());
                sysFile.setType(0);
                sysFile.setStatus(0);
                sysFileMapper.insertSelective(sysFile);
                defenseDraft.setFileId(sysFile.getId());
                defenseDraftMapper.updateWithFileIdById(defenseDraft);
            } else {
                throw new Exception("保存失败，状态不正确");
            }
        } else {
            defenseDraft = new DefenseDraft();
            sysFile = new SysFile();
            sysFile.setId(defenseDraft.getFileId());
            sysFile.setCreateBy(sysUser.getUserId());
            sysFile.setId(UUID.randomUUID().toString());
            sysFile.setFileName(desFileName);
            sysFile.setRealName(file.getOriginalFilename());
            sysFile.setCreateDt(new Date());
            sysFile.setType(0);
            sysFile.setStatus(0);
            sysFileMapper.insertSelective(sysFile);
            defenseDraft.setFileId(sysFile.getId());
            defenseDraft.setId(getMyCurrentTopic(sysUser).getId());
            defenseDraft.setStatus(0);
            defenseDraftMapper.insertSelective(defenseDraft);
        }
        return "保存成功";
    }

    @SuppressWarnings("Duplicates")
    public Object getMyDefenseDraft(SysUser sysUser) throws Exception {
        Map<String, Object> result = new HashMap<>();
        Topic myTopic = getMyCurrentTopic(sysUser);
        DefenseDraft defenseDraft = new DefenseDraft();
        defenseDraft.setId(myTopic.getId());
        defenseDraft = defenseDraftMapper.selectById(defenseDraft);
        if (defenseDraft == null) {
            throw new Exception("未上传答辩稿");
        } else {
            SysFile sysFile = new SysFile();
            sysFile.setId(defenseDraft.getFileId());
            sysFile = sysFileMapper.selectById(sysFile);
            result.put("dd", defenseDraft);
            if (sysFile == null) {
                throw new Exception("文件错误");
            } else {
                result.put("file", sysFile);
            }
        }
        return result;
    }

    public String saveDefenseApply(SysUser sysUser, Map<String, Object> params) throws Exception {
        Topic myTopic = getMyCurrentTopic(sysUser);
        DefenseApply defenseApply = new DefenseApply();
        defenseApply.setId(myTopic.getId());
        defenseApply = defenseApplyMapper.selectById(defenseApply);
        if (defenseApply != null) {
            Verify verify;
            if (defenseApply.getTeacherVerify() != null) {
                verify = new Verify();
                verify.setId(defenseApply.getTeacherVerify());
                verify = verifyMapper.selectById(verify);
                if (verify != null) {
                    if (verify.getStatus() == 0) ;
                    {
                        verify = new Verify();
                        verify.setId(defenseApply.getAdminVerify());
                        verify = verifyMapper.selectById(verify);
                        if (verify != null) {
                            if (verify.getStatus() == 0) ;
                            {
                                throw new Exception("已通过无法更改");
                            }
                        }
                    }
                }
            }
            defenseApply.setReason((String) params.get("reason"));
            defenseApplyMapper.updateWithReasonById(defenseApply);
            return "保存成功";
        } else {
            defenseApply = new DefenseApply();
            defenseApply.setId(myTopic.getId());
            defenseApply.setCreateDt(new Date());
            defenseApply.setCreateBy(sysUser.getUserId());
            defenseApply.setReason((String) params.get("reason"));
            defenseApplyMapper.insertSelective(defenseApply);
            return "保存成功";
        }
    }

    public Object getMyDefenseApply(SysUser sysUser) throws Exception {
        Map<String, Object> result = new HashMap<>();
        Topic myTopic = getMyCurrentTopic(sysUser);
        DefenseApply defenseApply = new DefenseApply();
        defenseApply.setId(myTopic.getId());
        defenseApply = defenseApplyMapper.selectById(defenseApply);
        if (defenseApply == null) {
            throw new Exception("未填写答辩申请");
        }
        result.put("defenseApply", defenseApply);
        Verify verify;
        if (defenseApply.getTeacherVerify() != null) {
            verify = new Verify();
            verify.setId(defenseApply.getTeacherVerify());
            verify = verifyMapper.selectById(verify);
            if (verify != null) {
                result.put("teacherVerify", verify);
            }
        }
        if (defenseApply.getAdminVerify() != null) {
            verify = new Verify();
            verify.setId(defenseApply.getAdminVerify());
            verify = verifyMapper.selectById(verify);
            result.put("adminVerify", verify);
        }
        return result;
    }
}
