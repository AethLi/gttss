package cn.aethli.gttss.service;

import cn.aethli.gttss.dao.ResultSubmitMapper;
import cn.aethli.gttss.domain.DefenseDraft;
import cn.aethli.gttss.domain.SysFile;
import cn.aethli.gttss.domain.SysUser;
import cn.aethli.gttss.domain.Topic;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

@Service
public class ResultSubmitService extends BaseService {

    @Autowired
    ResultSubmitMapper resultSubmitMapper;

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



        SysFile sysFile;
//        if (defenseDraft != null) {
//            if (defenseDraft.getStatus() == 0) {
//                if (defenseDraft.getFileId() != null || defenseDraft.getFileId() != "") {
//                    sysFile = new SysFile();
//                    sysFile.setId(defenseDraft.getFileId());
//                    sysFile.setStatus(1);
//                    sysFileMapper.updateWithStatusById(sysFile);
//                }
//                sysFile = new SysFile();
//                sysFile.setId(defenseDraft.getFileId());
//                sysFile.setCreateBy(sysUser.getUserId());
//                sysFile.setId(UUID.randomUUID().toString());
//                sysFile.setFileName(desFileName);
//                sysFile.setRealName(file.getOriginalFilename());
//                sysFile.setCreateDt(new Date());
//                sysFile.setType(0);
//                sysFile.setStatus(0);
//                sysFileMapper.insertSelective(sysFile);
//                defenseDraft.setFileId(sysFile.getId());
//                defenseDraftMapper.updateWithFileIdById(defenseDraft);
//            } else {
//                throw new Exception("保存失败，状态不正确");
//            }
//        } else {
//            defenseDraft = new DefenseDraft();
//            sysFile = new SysFile();
//            sysFile.setId(defenseDraft.getFileId());
//            sysFile.setCreateBy(sysUser.getUserId());
//            sysFile.setId(UUID.randomUUID().toString());
//            sysFile.setFileName(desFileName);
//            sysFile.setRealName(file.getOriginalFilename());
//            sysFile.setCreateDt(new Date());
//            sysFile.setType(0);
//            sysFile.setStatus(0);
//            sysFileMapper.insertSelective(sysFile);
//            defenseDraft.setFileId(sysFile.getId());
//            defenseDraft.setId(getMyCurrentTopic(sysUser).getId());
//            defenseDraft.setStatus(0);
//            defenseDraftMapper.insertSelective(defenseDraft);
//        }
        return "保存成功";
    }
}
