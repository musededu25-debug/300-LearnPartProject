package demo2;

import com.fufu.demo2.Cat;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class CatTest {

    @Test
    public void test01(){
        Class c1 = Cat.class;

        Constructor[] constructors = c1.getDeclaredConstructors();

        for (Constructor constructor : constructors) {
            System.out.println(constructor.getName() + "----->"
               + constructor.getParameterCount());
        }
    }

    @Test
    public void test02() throws Exception {
        Class c2 = Cat.class;

        Constructor constructor1 = c2.getDeclaredConstructor(String.class, int.class);
        System.out.println(constructor1.getName() + "----->"
                + constructor1.getParameterCount());
        constructor1.setAccessible(true);
        Cat cat1 = (Cat) constructor1.newInstance("狸花猫",1);
        System.out.println(cat1);

        Constructor constructor2 = c2.getDeclaredConstructor();
        System.out.println(constructor2.getName() + "----->"
                + constructor2.getParameterCount());
        constructor2.setAccessible(true);
        Cat cat2 = (Cat) constructor2.newInstance();
        System.out.println(cat2);
    }


    @Test
    public void test03() throws Exception {
        Class c1 = Cat.class;
        Field[] fields = c1.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field + "------>" + field.getType());
        }

        Cat cat = new Cat();
        Field name = c1.getDeclaredField("name");
        name.setAccessible(true);

        name.set(cat ,"三花猫");
        System.out.println(name.get(cat));
    }
}
