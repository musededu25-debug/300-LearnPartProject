package demo3;

import com.fufu.demo3.BigStar;
import com.fufu.demo3.ProxyUtil;
import com.fufu.demo3.Star;
import org.junit.jupiter.api.Test;

public class BigStarTest {

    @Test
    public void test01(){
        BigStar bigStar = new BigStar("小美");
        bigStar.sing("大东北");
        bigStar.dance();
    }

    @Test
    public void test02(){
        Star starProxy = ProxyUtil.creatProxy(new BigStar("小美"));
        String sing = starProxy.sing("大东北");
        System.out.println(sing);
        starProxy.dance();
    }
}
