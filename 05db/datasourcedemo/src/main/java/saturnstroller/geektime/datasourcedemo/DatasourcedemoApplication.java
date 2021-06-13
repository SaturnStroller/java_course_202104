package saturnstroller.geektime.datasourcedemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//此处排除数据源自动配置
@SpringBootApplication(scanBasePackages = {"saturnstroller.geektime.datasourcedemo"},
exclude = {DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        JdbcTemplateAutoConfiguration.class})
@EnableTransactionManagement
@MapperScan("saturnstroller.geektime.datasourcedemo.mapper")
public class DatasourcedemoApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(DatasourcedemoApplication.class, args);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
