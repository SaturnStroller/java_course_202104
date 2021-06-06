package saturnstroller.geektime.frame.scan;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import saturnstroller.geektime.frame.bean.Klass;
import saturnstroller.geektime.frame.scan2.ComponentDemo;

@Configuration
@ComponentScan(basePackageClasses = ComponentDemo.class)
public class ScanConfigDemo {
    @Bean
    public Klass m(){
        return new Klass();
    }
}
