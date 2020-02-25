package com.zxz.bbs.service;



import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
/*
 //添加本地文件虚拟映射路径，上传到D:\img2\映射url为/img2/**；
 */
@Configuration
public class MyImgConfigurerAdapter2 implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {


//        registry.addResourceHandler("/img/**").addResourceLocations("file:C:\\bbsImg\\img");
        registry.addResourceHandler("/img2/**").addResourceLocations("file:C:\\img2\\");
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }
}

