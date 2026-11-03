import com.fufu.test.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author M <musededu25@gmail.com>
 * @since 2026/11/03 23:29
 */


public class UserTest {
    @Test
    public void testGetUserName(){
        User.getUserName("张三");
    }

    @BeforeEach
    public void init(){
        System.out.println("------> Before --------");
    }

    @Test
    public void testGetUserName2(){
        User.getUserName(null);
    }

    @Test
    public void testGetMaxIndex(){
        int maxIndex = User.getMaxIndex(null);
        Assertions.assertEquals(-1,maxIndex,"有bug");
    }

    @Test
    public void testGetMaxIndex2(){
        int maxIndex = User.getMaxIndex("admin");
        Assertions.assertEquals(4,maxIndex,"有bug");
    }
}
