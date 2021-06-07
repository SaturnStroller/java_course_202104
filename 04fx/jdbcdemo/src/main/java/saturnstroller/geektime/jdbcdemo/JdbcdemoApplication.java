package saturnstroller.geektime.jdbcdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "saturnstroller.geektime.jdbcdemo")
public class JdbcdemoApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(JdbcdemoApplication.class, args);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
