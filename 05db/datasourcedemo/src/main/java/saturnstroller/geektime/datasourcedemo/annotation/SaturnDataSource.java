package saturnstroller.geektime.datasourcedemo.annotation;

import java.lang.annotation.*;

/**
 * @Description  数据源选择注解
 *
 * 主库：NULL
 * 从库：slave
 *
 * @Author SaturnStroller
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SaturnDataSource {
    String value() default "masterDataSource";
}
