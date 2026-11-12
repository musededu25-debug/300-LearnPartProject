package com.fufu.config;

//4.定义应该servlet容器启动的配置类，在里面加载Spring的配置类

import org.springframework.lang.Nullable;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

public class ServletContainersInitConfig extends AbstractDispatcherServletInitializer {
   //加载SpringMVC容器配置的
    @Override
    protected WebApplicationContext createServletApplicationContext() {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(SpringMVCConfig.class);

        return context;
    }
    //设置哪些请求归属springmvc
    @Override
    protected String[] getServletMappings() {
        //设置所有请求归springMVC
        return new String[]{"/"};
    }

    @Nullable
    @Override
    protected WebApplicationContext createRootApplicationContext() {
        return null;
    }
}
