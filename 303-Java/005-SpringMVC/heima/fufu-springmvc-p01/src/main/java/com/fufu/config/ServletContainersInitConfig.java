package com.fufu.config;

import jakarta.servlet.ServletConfig;
import org.springframework.lang.Nullable;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

//定义servlet容器启动的配置类
public class ServletContainersInitConfig extends AbstractDispatcherServletInitializer {

    //加载SpringMVC容器配置类
    @Override
    protected WebApplicationContext createServletApplicationContext() {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(SpringMvcConfig.class);
        return context;
    }

    //设置哪些权限归SpringMVC
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Nullable
    @Override
    protected WebApplicationContext createRootApplicationContext() {
        return null;
    }
}
