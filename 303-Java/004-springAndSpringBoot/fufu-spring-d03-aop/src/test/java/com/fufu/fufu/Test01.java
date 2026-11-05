package com.fufu.fufu;


import com.fufu.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author M <musededu25@gmail.com>
 * @since 2026/11/04 23:19
 */

@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class Test01 {

    @Autowired
    UserService userService;

    @Test
    public void test01(){
        userService.delectUser();

        System.out.println("=====================");

        userService.updateUser();

        System.out.println("=====================");

        userService.findAll();
    }
}
