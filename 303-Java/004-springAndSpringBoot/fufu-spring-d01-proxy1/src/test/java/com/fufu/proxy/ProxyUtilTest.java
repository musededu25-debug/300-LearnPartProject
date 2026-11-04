package com.fufu.proxy;

import com.fufu.dao.ProduceDao;
import com.fufu.dao.impl.ProduceDaoImpl;
import org.junit.jupiter.api.Test;

public class ProxyUtilTest {

    @Test
    public void test01(){
        //自己购买电脑
        ProduceDaoImpl produceDao = new ProduceDaoImpl();
        produceDao.buyProduce("自己");

        //代理购买电脑

        ProduceDao produceProxy = ProxyUtil.creatProxy(produceDao);
        produceProxy.buyProduce("代理");
    }


}
