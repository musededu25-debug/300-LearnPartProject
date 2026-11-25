package com.fufu.spring.p01.ioc;

import com.fufu.spring.p01.config.P01Config;
import com.fufu.spring.p01.ioc.service.BookService;
import com.fufu.spring.p01.lifecycle.LifecycleBean;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class LifecycleStep2Test {

    @Test
    public void testLifecycle() {
        AnnotationConfigApplicationContext applicationContext =new AnnotationConfigApplicationContext(P01Config.class);
        LifecycleBean bean = applicationContext.getBean(LifecycleBean.class);
        bean.use();

        applicationContext.close();
    }
}
