package saturnstroller.geektime.shardingspheredemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "saturnstroller.geektime.shardingspheredemo")
public class ShardingspheredemoApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(ShardingspheredemoApplication.class, args);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
