package saturnstroller.geektime.starterdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import saturnstroller.geektime.starterdemo.bean.Teacher;
import saturnstroller.geektime.starterdemo.service.DemoService;

@Configuration
@EnableConfigurationProperties(Teacher.class)
public class SaturnStrollerAutoConfiguration {
    @Autowired
    private Teacher teacher;

    @Bean(name = "yourTeacher")
    public DemoService demoService(){
        System.out.println(teacher);
        return new DemoService(teacher.getName());
    }
}
