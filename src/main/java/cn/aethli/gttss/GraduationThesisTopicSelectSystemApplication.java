package cn.aethli.gttss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@SpringBootApplication
public class GraduationThesisTopicSelectSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(GraduationThesisTopicSelectSystemApplication.class, args);
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
}
