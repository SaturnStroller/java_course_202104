package saturnstroller.geektime.starterdemo.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "teacher")
public class Teacher {
    private String name;
    private String age;
}
