package saturnstroller.geektime.frame;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import saturnstroller.geektime.frame.bean.Klass;
import saturnstroller.geektime.frame.bean.Student;
import saturnstroller.geektime.frame.bean.Teacher;
import saturnstroller.geektime.frame.scan.ServiceDemo;
import saturnstroller.geektime.frame.scan2.ComponentDemo;

import javax.annotation.Resource;

@ContextConfiguration(locations = "classpath*:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class _Main {

    /**
     * 3.使用注解自动装配
     * 组件扫描：使用xml配置context:component-scan 或 @ComponentScan注解
     * 被装配Bean使用注解 @Service/@Mapper/@Component/@Repository
     * 自动装配使用注解@Autowired/@Resource
     */
    @Autowired
    private ServiceDemo serviceDemo;
    @Resource
    private ComponentDemo componentDemo;
    @Resource
    private Klass klass;

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        /**
         * 1.使用 XML-构造器-参数索引 方式
         */
        Student student0 = (Student) context.getBean("student0");
        System.out.println(student0.toString());
        /**
         * 使用 XML-构造器-参数名 方式
         */
        Student student1 = (Student) context.getBean("student1");
        System.out.println(student1.toString());
        /**
         * 2.使用 XML-setter 方式
         */
        Teacher teacher0 = (Teacher) context.getBean("teacher0");
        System.out.println(teacher0.toString());
    }

    @Test
    public void test() {
        serviceDemo.m();
        componentDemo.m();
        klass.m();
    }

}
