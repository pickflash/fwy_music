package com.Hao.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.MultipartConfigElement;

@Configuration
public class UploadConfig implements WebMvcConfigurer {
    //盘符路径
    @Value("${file.uploadFolder}")
    private String uploadFolder;
    @Bean
    public MultipartConfigElement multipartConfigElement(){
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //设置上传文件路径
        factory.setLocation(uploadFolder);
        //设置文件最大值
        factory.setMaxFileSize(DataSize.parse("5MB"));
        //设置文件上传数据总大小
        factory.setMaxRequestSize(DataSize.parse("100MB"));
        //创建MultipartConfigElement对象
        MultipartConfigElement element = factory.createMultipartConfig();
        return element;
    }
    @Value("${file.staticAccessPath}")
    private String staticAccessPath;
    @Override    //添加资源处理器
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //将E:/文件/java/Test/upload/ ---->映射成127.0.0.1:8080/upload/**
        registry.addResourceHandler(staticAccessPath).addResourceLocations("file:" + uploadFolder);
    }
}