package cn.aethli.gttss.service;

import cn.aethli.gttss.dao.SysFileMapper;
import cn.aethli.gttss.domain.SysFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Properties;

@Service
public class SysFileService extends BaseService {

    @Autowired
    SysFileMapper sysFileMapper;

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

}
