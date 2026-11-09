package com.fufu.jdbc.demo;

import com.fufu.jdbc.demo1.Test01;
import com.fufu.jdbc.demo1.Test02;
import com.fufu.jdbc.demo1.Test03;
import org.junit.Test;

public class DemoTest01 {

    @Test
    public void test(){
        Test01 te = new Test01();
        try {
            te.connectMysql();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void test02()  {
        Test02 te =new Test02();

        try {
            te.connectMysql();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void test03(){
        Test03 te =new Test03();
        try {
            te.connectMysql();
        } catch (Exception e) {}
    }
}
