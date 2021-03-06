package cn.aethli.gttss;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@SpringBootApplication
@MapperScan("cn.aethli.gttss.dao")
public class GraduationThesisTopicSelectSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(GraduationThesisTopicSelectSystemApplication.class, args);
//        autoOpenBrowser_Student();
//        autoOpenBrowser_Teacher();
    }

    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setDefaultEncoding("UTF-8");
        resolver.setResolveLazily(true);// resolveLazily属性启用是为了推迟文件解析
        resolver.setMaxInMemorySize(40960);
        resolver.setMaxUploadSize(50 * 1024 * 1024);// 上传文件大小 50M 50*1024*1024
        return resolver;
    }

    /**
     * 自动打开浏览器学生端 仅在Apollo生效
     */
    private static void autoOpenBrowser_Student() {
        String cmd = "C:\\Users\\93162\\AppData\\Local\\Google\\Chrome\\Application\\chrome.exe http://localhost";
//        String cmd = "C:\\Program Files (x86)\\Microsoft\\Edge Dev\\Application\\msedge.exe http://localhost";
        Runtime run = Runtime.getRuntime();
        try {
            run.exec(cmd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return;
    }

    /**
     * 自动打开浏览器教师端 仅在Apollo生效
     */
    private static void autoOpenBrowser_Teacher() {
        String cmd = "C:\\Program Files (x86)\\Microsoft\\Edge Dev\\Application\\msedge.exe http://localhost/t";
        Runtime run = Runtime.getRuntime();
        try {
            run.exec(cmd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return;
    }


}
