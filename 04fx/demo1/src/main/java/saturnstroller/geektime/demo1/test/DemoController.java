package saturnstroller.geektime.demo1.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import saturnstroller.geektime.starterdemo.service.DemoService;

import javax.annotation.Resource;

@SpringBootTest
public class DemoController {

    @Resource(name = "yourTeacher")
    private DemoService demoService;

    @Test
    public void test1(){
        demoService.m();
    }
}
