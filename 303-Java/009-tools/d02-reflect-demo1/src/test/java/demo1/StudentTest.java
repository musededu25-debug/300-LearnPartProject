package demo1;

import com.fufu.demo1.Student;
import org.junit.jupiter.api.Test;

/**
 *
 * @author M <musededu25@gmail.com>
 * @since 2026/11/03 23:52
 */


public class StudentTest {

    @Test
    public void test() throws Exception {
        Class c1 = Student.class;
        System.out.println(c1.getName());
        System.out.println(c1.getSimpleName());

        Class c2 = Class.forName("com.fufu.demo1.Student");
        System.out.println(c1==c2);

        Student s = new Student();
        Class c3 = s.getClass();
        System.out.println(c2==c3);
    }
}
