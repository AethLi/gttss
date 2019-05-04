package cn.aethli.gttss.service;

import cn.aethli.gttss.dao.ResultSubmitMapper;
import cn.aethli.gttss.dao.SysFileMapper;
import cn.aethli.gttss.domain.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

@Service
public class ResultSubmitService extends BaseService {

    @Autowired
    ResultSubmitMapper resultSubmitMapper;
    @Autowired
    SysFileMapper sysFileMapper;

    @SuppressWarnings("Duplicates")
    public Object resultSubmitUpload(MultipartFile file, SysUser sysUser) throws Exception {
        Topic myTopic = getMyCurrentTopic(sysUser);

        if (file == null) {
            throw new Exception("上传失败");
        }
        Properties properties = new Properties();
        String resourcePath = DefenseService.class.getClassLoader().getResource("").getPath();
        resourcePath += "/templates/config/path.properties";
        InputStream in = new FileInputStream(resourcePath);
        properties.load(in);
        String path = properties.getProperty("Result_Submit_Upload_Path");
        String desFileName = RandomStringUtils.randomAlphanumeric(32) + file.getOriginalFilename();
        path += desFileName;
        File desFile = new File(path);
        file.transferTo(desFile);
        ResultSubmit resultSubmit = new ResultSubmit();
        resultSubmit.setId(myTopic.getId());
        resultSubmit = resultSubmitMapper.selectById(resultSubmit);
        SysFile sysFile;
        if (resultSubmit != null) {
            if (resultSubmit.getStatus() == 0) {
                if (resultSubmit.getFileId() != null || resultSubmit.getFileId() != "") {
                    sysFile = new SysFile();
                    sysFile.setId(resultSubmit.getFileId());
                    sysFile.setStatus(1);
                    sysFileMapper.updateWithStatusById(sysFile);
                }
                sysFile = new SysFile();
                sysFile.setId(resultSubmit.getFileId());
                sysFile.setCreateBy(sysUser.getUserId());
                sysFile.setId(UUID.randomUUID().toString());
                sysFile.setFileName(desFileName);
                sysFile.setRealName(file.getOriginalFilename());
                sysFile.setCreateDt(new Date());
                sysFile.setType(0);
                sysFile.setStatus(0);
                sysFileMapper.insertSelective(sysFile);
                resultSubmit.setFileId(sysFile.getId());
                resultSubmitMapper.updateWithFileIdById(resultSubmit);
            } else {
                throw new Exception("保存失败，状态不正确");
            }
        } else {
            resultSubmit = new ResultSubmit();
            sysFile = new SysFile();
            sysFile.setId(resultSubmit.getFileId());
            sysFile.setCreateBy(sysUser.getUserId());
            sysFile.setId(UUID.randomUUID().toString());
            sysFile.setFileName(desFileName);
            sysFile.setRealName(file.getOriginalFilename());
            sysFile.setCreateDt(new Date());
            sysFile.setType(0);
            sysFile.setStatus(0);
            sysFileMapper.insertSelective(sysFile);
            resultSubmit.setFileId(sysFile.getId());
            resultSubmit.setId(getMyCurrentTopic(sysUser).getId());
            resultSubmit.setStatus(0);
            resultSubmitMapper.insertSelective(resultSubmit);
        }
        return "保存成功";
    }

    @SuppressWarnings("Duplicates")
    public Object getMyResultSubmit(SysUser sysUser) throws Exception {
        Map<String, Object> result = new HashMap<>();
        Topic myTopic = getMyCurrentTopic(sysUser);
        ResultSubmit resultSubmit = new ResultSubmit();
        resultSubmit.setId(myTopic.getId());
        resultSubmit = resultSubmitMapper.selectById(resultSubmit);
        if (resultSubmit == null) {
            throw new Exception("未上传答辩稿");
        } else {
            SysFile sysFile = new SysFile();
            sysFile.setId(resultSubmit.getFileId());
            sysFile = sysFileMapper.selectById(sysFile);
            result.put("dd", resultSubmit);
            if (sysFile == null) {
                throw new Exception("文件错误");
            } else {
                result.put("file", sysFile);
            }
        }
        return result;
    }
}
