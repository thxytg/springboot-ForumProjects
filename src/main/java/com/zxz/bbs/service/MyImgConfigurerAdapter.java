package com.zxz.bbs.service;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
/*
 //添加本地文件虚拟映射路径，上传到C:\img\映射url为/tmp/**；
 */
@Configuration
public class MyImgConfigurerAdapter implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {


//        registry.addResourceHandler("/img/**").addResourceLocations("file:C:\\bbsImg\\img");
        registry.addResourceHandler("/tmp/**").addResourceLocations("file:D:\\idea_space\\bbs\\src\\main\\resources\\tmp\\");
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }
}
