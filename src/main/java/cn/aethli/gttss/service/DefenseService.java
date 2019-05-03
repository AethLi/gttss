package cn.aethli.gttss.service;

import cn.aethli.gttss.dao.DefenseApplyMapper;
import cn.aethli.gttss.dao.DefenseDraftMapper;
import cn.aethli.gttss.dao.SysFileMapper;
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

    public Object downloadById(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String fileId = (String) request.getParameter("id");
        SysFile sysFile = new SysFile();
        sysFile.setId(fileId);
        sysFile = sysFileMapper.selectById(sysFile);
        if (sysFile == null) {
            throw new Exception("未找到该文件");
        }
        String path = "";
        Properties properties = new Properties();
        String resourcePath = DefenseService.class.getClassLoader().getResource("").getPath();
        resourcePath += "/templates/config/path.properties";
        InputStream in = new FileInputStream(resourcePath);
        properties.load(in);
        if (sysFile.getType() == 0) {
            path = properties.getProperty("Defense_Draft_Upload_Path");
        }
        path += sysFile.getFileName();
        if (path != null) {
            File file = new File(path);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                try {
                    response.addHeader("Content-Disposition",
                            "attachment; fileName=" + URLEncoder.encode(sysFile.getRealName(), "utf-8"));// 设置文件名
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return null;
    }

    public String saveDefenceApply(SysUser sysUser, Map<String, Object> params) throws Exception {
        Topic myTopic = getMyCurrentTopic(sysUser);
        DefenseApply defenseApply = new DefenseApply();
        defenseApply.setId(myTopic.getId());

        defenseApply.setCreateBy(sysUser.getUserId());
        defenseApply.setCreateDt(new Date());
        defenseApply.setReason((String) params.get("reason"));

        return null;
    }
}
