package com.fufu.proxy;

import com.fufu.dao.ProduceDao;
import com.fufu.dao.impl.ProduceDaoImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyUtil {

    public static ProduceDao creatProxy(ProduceDao produceDao){


        ProduceDao produceDao1 = (ProduceDao) Proxy.newProxyInstance(ProxyUtil.class.getClassLoader(),
                new Class[]{ProduceDao.class}, new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        if(method.getName().equals("buyProduce")){
                        }
                        return method.invoke(produceDao , args) ;
                    }
                });

        return produceDao1;
    }

}
